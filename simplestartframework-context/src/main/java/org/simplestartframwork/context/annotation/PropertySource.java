package org.simplestartframwork.context.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 
 *  PropertySource注解：用于设置需要读取Properties文件的路径 。根据PropertySourceConfigurer类，获得该注解的路径可以实现
 *  使用Value注解，通过${key}获得Properties文件的值。
 * 
 * </pre>
 * 
 * @see PropertySourceConfigurer类，Value注解
 * @author ranger
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Documented
public @interface PropertySource {

	/**
	 * <pre>
	 * 
	 * 该属性用于判断，当找不到指定的Properties文件路径时，是否忽略报异常。
	 * 1.如果为true（默认），不报异常，忽略该功能
	 * 2.如果为false,直接报错
	 * 
	 * </pre>
	 * 
	 * @return 返回一个判断值
	 */
	boolean notFoundIgnore() default true;

	/**
	 * <pre>
	 *  该属性用于获得一组Properties文件路径。通过该路径PropertySourceConfigurer类实现${key}获得文件的值
	 * </pre>
	 * 
	 * @return 返回一组Properties文件路径
	 */
	String[] value();

}
