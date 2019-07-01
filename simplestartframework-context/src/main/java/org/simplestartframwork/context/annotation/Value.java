package org.simplestartframwork.context.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * <pre>
 * 
 * Value注解：作用用于给成员属性、方法参数、注解属性，注入标量类型的值，
 * 1.标量类型=基础数据类+基础数据类型包装类+String类型
 * 2.注意：Value注解不能用于局部变量
 * 3.配置了@ProertySource注解，Value注解支持${key}获得Properties文件的值
 * 
 * </pre>
 * 
 * @author ranger
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value= {ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Documented
public @interface Value {
	
	/**
	 * 该字段用于成员属性、方法参数、注解属性指定值
	 * 配置了@ProertySource注解，Value注解支持${key}获得Properties文件的值
	 * @return 返回值
	 */
	String value();

}
