package org.simplestartframwork.context.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 
 * DependOn注解：用于指定在创建一个对象的时候，必须强制检测依赖的对象是否已经创建
 * 1.如果没有在字段的类之前创建指定变量名的对象，那么就强制创建创建。
 * 
 * 该注解使用于通过方法
 * 
 * </pre>
 * 
 * @author ranger
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD,ElementType.TYPE,ElementType.CONSTRUCTOR})
@Documented
public @interface DependsOn {
	
	/**
	 * <per>
	 *  该字符串值必须要符合类的默认命名法，获得主键注解已经Bean注解声明的对象名。否则会报错
	 * </per> 
	 * @return
	 */
	String[] value();

}
