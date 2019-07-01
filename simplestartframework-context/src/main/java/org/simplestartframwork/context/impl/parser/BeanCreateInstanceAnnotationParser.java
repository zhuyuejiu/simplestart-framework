package org.simplestartframwork.context.impl.parser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simplestartframework.core.utils.NamedConversionUtils;
import org.simplestartframwork.context.Context;
import org.simplestartframwork.context.annotation.Bean;
import org.simplestartframwork.context.impl.parser.CreateInstanceAbstractParser;

/**
 * <pre>
 *  BeanAnnotationParser解释类：用于接收Bean注解，将对象注入到容器
 * </pre>
 * 
 * @author ranger
 * @version 1.0
 *
 */
public class BeanCreateInstanceAnnotationParser extends CreateInstanceAbstractParser {
	
	private static final Logger LOGGER=LogManager.getLogger(BeanCreateInstanceAnnotationParser.class.getName());

	/**
	 * <pre>
	 * 
	 *  该方法实现了使用@Bean在配置类创建对象，并且加入到容器里面
	 * 
	 * </pre>
	 * 
	 * @param configClassType 该参数为配置类的类类型
	 * @param context 该参数为容器对象
	 * @throws InstantiationException 创建对象异常
	 * @throws IllegalAccessException 修饰符访问权限异常
	 * @throws IllegalArgumentException 动态调用方法获得创建对象参数不匹配异常
	 * @throws InvocationTargetException 动态调用方法异常
	 */

	@Override
	public boolean handle()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		LOGGER.debug("handle:BeanCreateInstanceAnnotationParser-start");
		// 获得配置类信息
		List<?> configClassTypes = ParserParam.get(ParserParam.CONFIG_CLASS_TYPES, List.class);
		for (Object configClassTypeObject : configClassTypes) {
			Class<?> configClassType = (Class<?>) configClassTypeObject;
			// 获得容器
			Context context = ParserParam.get(ParserParam.OBJECT_CONTEXT, Context.class);
			// 1.获得容器所以对象
			Map<String, Object> objects = context.getObjects();
			// 2.通过配置类的Class类型反射创建配置类的对象
			Object configuration = configClassType.newInstance();
			// 3.获得配置类自身的所有方法，不包括父类的方法
			Method[] methods = configClassType.getDeclaredMethods();
			// 4.遍历配置类的自身的所有动态方法
			for (Method method : methods) {
				// 5.如果发现方法上面有@Bean注解
				Bean bean = method.getDeclaredAnnotation(Bean.class);
				if (bean != null) {
					// 5.调用动态调用该方法，注意，该方法不能有参数
					Object object = method.invoke(configuration);
					// 6.如果没有返回的对象，直接跳过
					if (object == null) {
						continue;
					}

					// 7.如果已经存在同名的对象，处理是覆盖原来的对象还是使用原来的对象
					if (bean.cover() == false) {
						if (context.getObject(null, object.getClass()) != null) {
							continue;
						}
					}
                    // 8.加入，如果有Init注解的方法，创建时调用该方法
					this.doValue(object);
					// 9.如果有Value注解，将Value注解的值赋予对应的字段
					this.doValue(object);
					// 10.如果有对象，那么将对象加入到容器中
					if (bean.name() != null && !"".equals(bean.name())) {
						// 使用自定义命名
						objects.put(bean.name(), object);
					} else {
						// 使用默认命名法命名
						objects.put(NamedConversionUtils.classNameToObjectName(object.getClass().getSimpleName()),
								object);
					}
					
					LOGGER.debug("create:"+object.getClass().getName());
				}
			}
		}
		LOGGER.debug("handle:BeanCreateInstanceAnnotationParser-end");
		return true;

	}

	@Override
	protected Object doCreate(Class<?> objectClassType) throws InstantiationException, IllegalAccessException {

		return null;
	}

}
