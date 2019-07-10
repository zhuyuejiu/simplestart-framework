package org.simplestartframwork.context.impl.parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simplestartframework.core.utils.NamedConversionUtils;
import org.simplestartframework.core.utils.TypeConversionUtils;
import org.simplestartframwork.context.Context;
import org.simplestartframwork.context.annotation.Inject;
import org.simplestartframwork.context.annotation.Matching;
import org.simplestartframwork.context.annotation.Value;
import org.simplestartframwork.context.impl.parser.CreateInstanceAbstractParser;
import org.simplestartframwork.context.impl.parser.ParserParam;
import org.simplestartframwork.context.impl.parser.ScanAnnotationParser;

public class ConstructoCreateInstanceAnnotationParser extends CreateInstanceAbstractParser {

	// 1.创建一个Log4j的操作对象
	private static final Logger LOG = LogManager.getLogger(ScanAnnotationParser.class.getName());

	/**
	 * <pre>
	 * 
	 *   该方法实现创建通过构造注入关联对象的组件类对象
	 *   1.判断构造方法的参数的对象容器里有没有，如果有，直接将容器中的对象注入到构造方法对应的参数
	 *   2.如果构造方法的参数的对象容器中没有，那么创建该参数的对象，并放在容器中
	 * 
	 * </pre>
	 * 
	 * 
	 * @throws IllegalArgumentException 参数不匹配异常
	 * @throws IllegalAccessException 访问修饰符权限异常
	 * @throws InvocationTargetException 动态调用方法异常
	 * @throws NoSuchMethodException 动态调用方法，找不到方法异常
	 * @throws SecurityException 安全警告异常
	 * @throws InstantiationException 反射创建对象异常
	 * @throws ClassNotFoundException 找不到类异常
	 */
	@Override
	public boolean handle() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
		// 1.获得没有创建对象的组件类类型集合
		Set<?> componentClassTypes = ParserParam.get(ParserParam.ALL_COMPONENT_CLASS_TYPES, Set.class);
		// 2.获得容器
		Context context = ParserParam.get(ParserParam.OBJECT_CONTEXT, Context.class);
		LOG.debug("-injectConstructor方法:" + componentClassTypes);
		for (Object componentTypeObject : componentClassTypes) {
			Class<?> componentClassType = (Class<?>) componentTypeObject;
			// 3.获得创建的对象
			Object object = this.doCreate(componentClassType);
			// 4.执行Initialize注解的初始化方法
			this.doInitialize(object);
			String key = NamedConversionUtils.classNameToObjectName(object.getClass().getSimpleName());
			context.addObject(key, object);
		}
		return true;

	}

	@Override
	protected Object doCreate(Class<?> objectClassType)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
		// 1.获得容器
		Context context = ParserParam.get(ParserParam.OBJECT_CONTEXT, Context.class);
		// 2.获得该组件类的构造方法列表
		Constructor<?>[] constructors = objectClassType.getConstructors();
		// 3.遍历该组件类的构造方法列表
		for (int i = 0; i < constructors.length; i++) {
			// 4.获得该组件类的构造方法参数类型类别
			Parameter[] parameters = constructors[i].getParameters();
			// 5.判断参数是否大于0，必须要有构造方法参数的组件类才可以使用构造方法注入
			if (parameters.length > 0) {

				// 6.判断构造方法有没有注入的Inject注解
				Inject inject = constructors[i].getAnnotation(Inject.class);
				// 7.如果注入Inject注解不为空
				if (inject != null) {
					// 8.创建一个集合，用于存放构造方法的参数
					List<Object> paramObjects = new ArrayList<Object>();
					// 9.通过已经获得的构造方法参数类型列表，注入对象该对应的构造方法参数
					for (int j = 0; j < parameters.length; j++) {
						Parameter parameter = parameters[j];
						// 10.获得类型名
						String constructorName = parameter.getType().getName();
						LOG.debug("injectConstructor方法-参数名：" + constructorName + "==============================================");

						Matching matching = parameter.getDeclaredAnnotation(Matching.class);
						Value value = parameter.getDeclaredAnnotation(Value.class);

						Object methodObject = null;
						if (matching != null) {
							
							methodObject = context.getObject(matching.value(), parameter.getType());
						}  else if(value!=null) {
							//增加对Value的支持
							methodObject = TypeConversionUtils.StringToBasic(parameter.getType(), value.value());
						}
						
						else{
							// 11.判断容器里面是否有对应的对象
							String defaultObjectName = NamedConversionUtils.classAllNameToObjectName(constructorName);
							LOG.debug("injectConstructor方法-默认对象名-参数名：" + defaultObjectName);
							methodObject = context.getObject(defaultObjectName, parameter.getType());
						}

						if (methodObject == null) {
							// 12. 如果参数的对象容器里面没有，创建一个对象放在容器里面
							Class<?> parameterObject = Class.forName(constructorName);
							Object object = parameterObject.newInstance();
							if (matching != null) {
								context.addObject(matching.value(), object);
							} else {
								// 13. 获得对象的默认命名
								String objectName = NamedConversionUtils.classNameToObjectName(object.getClass().getSimpleName());
								// 14.将新创建的对象加入到容器
								context.addObject(objectName, object);
							}

							paramObjects.add(object);
						} else {
							paramObjects.add(methodObject);
						}
					}
					// 15.通过构造方法创建对象，将参数列表传入
					return constructors[i].newInstance(paramObjects.toArray());

				}

			}
		}

		return null;
	}

}
