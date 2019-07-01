package org.simplestartframwork.context.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 
 * Initialize注解：用于声明在创建类的对象时，自动执行声明该注解的方法，我们称这样的方法为初始化方法。
 * 
 * </pre>
 * 
 * @author ranger
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value=ElementType.METHOD)
@Documented
public @interface Initialize {

}
