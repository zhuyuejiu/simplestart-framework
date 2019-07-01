package org.simplestartframwork.context.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 
 *
 * </pre>
 * 
 * @author ranger
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value= {ElementType.METHOD,ElementType.TYPE})
@Documented
public @interface Async {
   //TODO 未实现
}
