package org.simplestartframwork.context.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * <pre>
 * 
 * Scope注解：用于声明指定创建的对象，是单例还是原型。
 * 1.单例对象启动是就创建，在容器没有关闭时，一直存在。
 * 2.原型对象，每次创建时都对象不同，在空闲时，会被Java的垃圾回收机制回收
 * 
 * </pre>
 * 
 * @author ranger
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.TYPE, ElementType.METHOD })
@Documented
public @interface Scope {

	/**
	 * <pre>
	 * 
	 * BeanDefinition枚举：作用是用于设置Scope注解中，对象的生命周期
	 * 1.SINGLETON，默认值，表示创建的对象是单列对象
	 * 2.PROTOTYPE，表示创建的对象是一个原型对象
	 * 
	 * 
	 * </pre>
	 * 
	 * @see Scope 注解
	 * @author ranger
	 * @version 1.0
	 */
	public enum BeanDefinition {

		/**
		 * <per>
		 * 
		 * 表示指定创建的对象是原型对象
		 * 
		 * </per>
		 */
		PROTOTYPE,

		/**
		 * <per>
		 * 
		 * 表示指定创建的对象是单例对象
		 * 
		 * </per>
		 */
		SINGLETON;

	}

	/**
	 * <pre>
	 * 
	 * 该属性用于指定创建的对象是单例还是原型对象，默认为单例对象
	 * 
	 * </pre>
	 * @return 返回对象类型的枚举
	 */
	BeanDefinition value() default BeanDefinition.SINGLETON;

}
