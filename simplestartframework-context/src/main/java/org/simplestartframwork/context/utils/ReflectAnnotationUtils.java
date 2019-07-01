package org.simplestartframwork.context.utils;

import java.lang.reflect.Constructor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simplestartframwork.context.annotation.Inject;
import org.simplestartframwork.context.annotation.component.Action;
import org.simplestartframwork.context.annotation.component.Persistent;
import org.simplestartframwork.context.annotation.component.Service;
import org.simplestartframwork.context.annotation.component.Universal;


public class ReflectAnnotationUtils {
	// 1.创建一个Log4j的操作对象
	private static final Logger LOG = LogManager.getLogger(ReflectAnnotationUtils.class.getName());

	/**
	 * <pre>
	 * 
	 *  获得组件设置的对象名，用于设置自定义的对象名
	 * 
	 * </pre>
	 * 
	 * @param classType 类的类型
	 * @return 返回组件类的注解值
	 */
	public static String getComponentOfValue(Class<?> componentClassType) {
		// 1.获得四个组件注解的对象
		Universal universal = componentClassType.getDeclaredAnnotation(Universal.class);
		Service service = componentClassType.getDeclaredAnnotation(Service.class);
		Action action = componentClassType.getDeclaredAnnotation(Action.class);
		Persistent persistent = componentClassType.getDeclaredAnnotation(Persistent.class);
		// 2.判断注解对象是否为空，注解对象的的name属性是否有值
		if (universal != null) {
			if (!"".equals(universal.value()) && universal.value() != null) {
				return universal.value();
			}
		}
		if (service != null) {
			if (!"".equals(service.value()) && service.value() != null) {
				return service.value();
			}
		}
		if (action != null) {
			if (!"".equals(action.value()) && action.value() != null) {
				return action.value();
			}
		}
		if (persistent != null) {
			if (!"".equals(persistent.value()) && persistent.value() != null) {
				return persistent.value();
			}
		}
		return null;

	}

	/**
	 * <pre>
	 * 
	 * 判断组件类是否有构造方法注入对象.
	 * 如果有：返回true
	 * 如果没有：返回false
	 * 
	 * </pre>
	 * 
	 * @param classType 判断是否是有注入对象参数的组件类
	 * @return 如果类有有参数的构造方法，并且该构造方法上有@Inject注解返回返回true,否则返回false
	 */
	public static boolean hasConstructorInject(Class<?> classType) {
		// 1.获得当前类的构造方法
		Constructor<?>[] constructors = classType.getDeclaredConstructors();
		for (Constructor<?> constructor : constructors) {
			Class<?>[] parameterTypes = constructor.getParameterTypes();

			// 2.如果发现构造方法有注入的注解，并且有参数，返回true
			Inject annotation = constructor.getAnnotation(Inject.class);
			if (annotation != null && parameterTypes.length > 0) {
				return true;
			}
		}

		return false;
	}

	/**
	 * <pre>
	 * 判断是否是组件
	 * </pre>
	 * 
	 * @param classType 类的类型
	 * @return 如果是组件类，返回true,否则返回false
	 */
	public static boolean isComponent(Class<?> classType) {
		// 1.如果是接口，就不能创建对象，直接返回false
		if (classType.isInterface()) {
			return false;
		}
		// 2.判断是否是Component组件类
		Universal universal = classType.getDeclaredAnnotation(Universal.class);
		// 3.判断是否是Service组件类
		Service service = classType.getDeclaredAnnotation(Service.class);
		// 4.判断是否是Controller组件类
		Action action = classType.getDeclaredAnnotation(Action.class);
		// 5.判断是否是Controller组件类
		Persistent persistent = classType.getDeclaredAnnotation(Persistent.class);
		// 6.判断只要有一个组件注解,就返回true
		if (universal != null || service != null || action != null || persistent != null) {
			return true;
		}
		return false;
	}

}
