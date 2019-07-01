package org.simplestartframwork.context.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 
 * Matching注解：设置注入Inject注解注入对象给属性、方法参数、构造方法参数时
 * 用于指定对象在容器中的名名称
 * 
 * </pre>
 * 
 * @author ranger
 * @version 1.0
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {  ElementType.FIELD,  ElementType.PARAMETER})
@Documented
public @interface Matching {

	/**
	 * <pre>
	 * 
	 * 通过value属性可以指定同一父类不同对象对象名的对象
	 * 
	 * </pre>
	 * 
	 * @return 返回指定的对象名
	 */
	String value() default "";
}
