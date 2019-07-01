package org.simplestartframwork.context.annotation.component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 
 *  Universal注解：用于声明一个非三层外的组件类
 *  1.UniversalScan会将加入该注解的类创建对象放到容器中
 *  2.注意：该注解必须要在实现类里面使用，如果加入到接口里面，会导致创建对象失败而报错
 * 
 * </pre>
 * @see UniversalScan注解
 * @author ranger
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Documented
public @interface Universal {

	/**
	 * <pre>
	 * 
	 * 1.作用：设置对象名的属性，使用该注解创建对象的组件类，会将对象名保存为该值。
	 * 2.对象默认命名规则为：类名的首字母小写；如：类名为UserUniversal,创建的对象默认命名为:userUniversal
	 * 
	 * </pre>
	 * @return 返回对象名，如果不指定对象名，对象使用默认默认命名
	 * 
	 */
	String value() default "";

}
