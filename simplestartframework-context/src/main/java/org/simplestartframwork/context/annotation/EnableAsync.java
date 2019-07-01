package org.simplestartframwork.context.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 
 * EnableAsync注解:用于在配置类加入该注解表示支持异步方法。
 * 1.如果方法加入了Async注解，那么表示该方法异步方法
 * 2.如果类上面加入Async注解，那么表示该类的所有方法为异步
 * 
 * 注意：所有有Enable*开头的注解，所谓开启某个功能组件的注解，都必须在加了Cofing注解的配置类里面使用
 * </pre>
 * 
 * @author ranger
 * @version 1.0
 * @see Config配置注解
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Documented
public @interface EnableAsync {
	//TODO 未实现
}
