package org.simplestartframwork.context.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 
 * Config注解：作用声明类为配置类。配置类用于创建没有组件注解的对象。
 * 通过配置类，可以方便集成框架的各种可选组件，以及第三方框架的支持
 * 
 * 注意：Bean注解只能在Config注解里面使用。
 * 
 * </pre>
 * @see Bean注解
 * @author ranger
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Documented
public @interface Config {

}
