package org.simplestartframwork.data.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 
 * SQL注解:用于设置SQL语句
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
public @interface SQL {
	
	/**
	 *  该属性用于设置SQL语句
	 * @return
	 */
	String value();
	
	SQLType type();
	
	enum SQLType{
		UPDATE,
		INSERT,
		DELETE,
		SELECT,
		CALL
	}

}


