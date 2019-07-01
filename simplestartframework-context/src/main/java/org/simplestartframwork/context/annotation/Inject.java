package org.simplestartframwork.context.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 
 *  Inject注解：用于给属性，方法，构造方法等参数以及变量注入容器中的对象
 *  1.可以实现通过普通方法注入对象
 *  2.可以通过构造方法注入对象
 *  指定可以在方法，成员变量,构造方法上面使用
 * 
 * </pre>
 * 
 * @author ranger
 * @version 1.0
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR })
@Documented
public @interface Inject {



	/**
	 * <pre>
	 * 
	 *  该属性用于判断，如果容器中没有返回对应类型的注入对象是否要报异常
	 *  1.如果为true，如果获得不了注入的对象立刻报异常
	 *  2.如果为false，如果获得不了注入的对象返回null值，不报异常
	 *  
	 * </pre>
	 * 
	 * @return
	 */
	boolean require() default false;

}
