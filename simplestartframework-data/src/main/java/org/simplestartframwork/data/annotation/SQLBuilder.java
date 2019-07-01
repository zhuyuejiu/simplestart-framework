package org.simplestartframwork.data.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 
 * SQLProvider注解：用于实现动态SQL
 * 
 * </pre>
 * 
 * @author ranger
 * @version 1.0
 *
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SQLBuilder {

	/**
	 * <pre>
	 * 
	 * 该属性用于指定SQL提供类的类
	 * 
	 * </pre>
	 * 
	 * @return
	 */
	Class<?>[] classes();

	/**
	 * <pre>
	 * 
	 * 该属性用于指定执行的方法
	 * 
	 * </pre>
	 * 
	 * @return
	 */
	String method();

}
