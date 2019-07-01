package org.simplestartframwork.context.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 
 * Import注解：用于将多个配置类的配置导入到一个配置类里面。
 * 
 * </pre>
 * 
 * @author ranger
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Documented
public @interface Import {

	/**
	 * <pre>
	 * 
	 * 该属性用于指定多个配置类的类类型，通过该属性可以让容器同时加载包含的配置类的配置
	 * 
	 * <pre>
	 * 
	 * @return 返回一组类类型
	 */
	Class<?>[] value();

}
