package org.simplestartframwork.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 
 * @author ranger
 *
 */
@Retention(RetentionPolicy.RUNTIME)

/*
 * 
 * 指定可以在方法以及类上面使用面使用
 * 1.在方法上面，指定请求路径
 * 2.在类上面用于指定命名空间
 */

@Target(value={ElementType.METHOD,ElementType.TYPE})
@Documented
public @interface PathMapping {
	/**
	 * 没有默认值，表示必须要填写
	 * @return
	 */
	String value();

}
