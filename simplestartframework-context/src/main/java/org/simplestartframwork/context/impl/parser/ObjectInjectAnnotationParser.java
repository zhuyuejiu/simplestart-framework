package org.simplestartframwork.context.impl.parser;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simplestartframework.core.utils.NamedConversionUtils;
import org.simplestartframwork.context.Context;
import org.simplestartframwork.context.annotation.Inject;
import org.simplestartframwork.context.annotation.Matching;
import org.simplestartframwork.context.impl.parser.Parser;
import org.simplestartframwork.context.impl.parser.ParserParam;

public class ObjectInjectAnnotationParser implements Parser {

	// 1.创建一个Log4j的操作对象
	private static final Logger LOG = LogManager.getLogger(ObjectInjectAnnotationParser.class.getName());


	/**
	 * <pre>
	 * 
	 * 给对象的属性注入关联的对象
	 * 
	 * </pre>
	 * @throws InstantiationException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws InvocationTargetException 
	 */

	@Override
	public boolean handle() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		    //获得容器
		    Context context = ParserParam.get(ParserParam.OBJECT_CONTEXT, Context.class);
			// 1.获得容器中的所有对象。
			Map<String, Object> objects = context.getObjects();
			// 2.获得容器中所有的对象值
			Collection<Object> values = objects.values();
			for (Object object : values) {
				// 3.注入到属性
				this.injectField(object, context);
				// 4.注入到方法
				this.injectMethod(object, context);
			}
			
			return true;
	}

	/**
	 * <pre>
	 * 
	 * 该方法实现了通过属性注入对象给成员变量
	 * 
	 * </pre>
	 * 
	 * @param object 需求属性注入对象的对象
	 * @param context 框架容器
	 * @throws IllegalArgumentException 参数不匹配异常
	 * @throws IllegalAccessException 访问修饰符异常
	 * @throws InstantiationException
	 */
	private void injectField(Object object, Context context)
			throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		// 1.获得对象的类结构
		Class<? extends Object> classType = object.getClass();
		// 2.获得属性的结构
		Field[] fields = classType.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			// 3. 获得属性的Inject注解
			Inject inject = fields[i].getAnnotation(Inject.class);
			// 4.判断属性上是否有Inject注解，如果有才执行注入操作
			if (inject != null) {
				// 5.获得属性类型
				Class<?> fieldType = fields[i].getType();
				// 6.获得属性名
				String fieldName = fields[i].getName();
				// 7.通过属性的默认名和类型获得容器的对象

				Matching matching = fields[i].getDeclaredAnnotation(Matching.class);
				Object fieldObject = null;
				if (matching != null && !matching.value().equals("")) {
					// 8.判断是否有InjectName注解,如果用指定的对象名使用指定的
					fieldObject = context.getObject(matching.value(),fieldType);
				} else {
					fieldObject = context.getObject( fieldName,fieldType);

				}

				if (fieldObject != null) {
					// 8.允许反射的方式访问私有属性
					fields[i].setAccessible(true);
					// 9.将对象注入给该属性
					fields[i].set(object, fieldObject);
				}

			}
		}
	}

	/**
	 * <pre>
	 * 注意set方法的规范 限定方法只能注入一个参数，并且注入对象的方法只能有一个参数
	 * </pre>
	 * 
	 * @param object 需求属性注入对象的对象
	 * @param context 框架容器
	 * @throws IllegalArgumentException 参数不匹配异常
	 * @throws IllegalAccessException 访问修饰符异常
	 * @throws InvocationTargetException 方法动态调用异常
	 * @throws InstantiationException
	 */
	private void injectMethod(Object object, Context context)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException {
		// 1.获得对象的表结构
		Class<? extends Object> classType = object.getClass();
		// 2.获得类自身方法的结构
		Method[] methods = classType.getDeclaredMethods();
		// 3.遍历调用方法
		for (int i = 0; i < methods.length; i++) {
			// 4.获得方法上面的Inject注解
			Inject inject = methods[i].getAnnotation(Inject.class);
			// 5.如果方法上Inject注解不为null
			if (inject != null) {
				// 6.获得参数列表的类型
				Class<?>[] parameterTypes = methods[i].getParameterTypes();
				List<Object> methodObjects = new ArrayList<Object>();
				for (int j = 0; j < parameterTypes.length; j++) {
					// 7.获得参数类型类全名,并转成为对象名
					String objectName = NamedConversionUtils.classAllNameToObjectName(parameterTypes[i].getName());

					LOG.debug("injectMethod参数名：" + parameterTypes[i].getName() + "-对象名：" + objectName);
					// 8.获得方法的参数注解
					Annotation[][] annotations = methods[i].getParameterAnnotations();
					// 9.获得指定的参数
					Annotation[] injectNames = annotations[j];
					Matching matching = null;
					
					// 10.判断参数的注解，因为一个参数可以有多个注解，所以需要循环判断
					for (int k = 0; k < injectNames.length; k++) {
						// 11.获得注解的类型
						Class<? extends Annotation> annotationType = injectNames[k].annotationType();
						// 12.判断如果参数上面的注解和InjectName注解是否兼容
						boolean flag = annotationType.isAssignableFrom(Matching.class);
						// 13.如果注解兼容，那么InjectName注解接收
						if (flag == true) {
							matching = (Matching) injectNames[k];
							break;
						}
					}
					Object methodObject = null;
					if (matching != null && !matching.value().equals("")) {
						// 8.如果参使用InjectName注解指定自定义的对象名，使用指定的对象名
						LOG.debug("injectMethod,自定义参数参数名：" + matching.value());
						methodObject = context.getObject(matching.value(),parameterTypes[0]);
					} else {
						// 9. 如果没有指定对象使用默认命名
						methodObject = context.getObject(objectName,parameterTypes[0]);
					}
					methodObjects.add(methodObject);

				}

				if (methodObjects.size() > 0) {
					// 9.允许访问私有方法
					methods[i].setAccessible(true);
					// 10.将属性值赋予这个对象的属性
					methods[i].invoke(object, methodObjects.toArray());
				}

			}
		}
	}

}
