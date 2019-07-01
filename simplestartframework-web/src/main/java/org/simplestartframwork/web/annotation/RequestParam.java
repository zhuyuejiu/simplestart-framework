package org.simplestartframwork.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author ranger
 * @date 2017-11-09
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD,ElementType.PARAMETER})
@Documented
public @interface RequestParam {
	
	/**
	 * 指定表单参数的对应的字段名
	 * @return
	 */
	String value() default "" ;
	

}
