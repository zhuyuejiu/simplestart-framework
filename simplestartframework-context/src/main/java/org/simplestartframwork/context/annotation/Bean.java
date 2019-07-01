package org.simplestartframwork.context.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * <pre>
 * 
 * Bean注解：作用将无法通过组件扫描创建对象的类通过方法，返回对象的方式，注入到框架容器中。
 * 1.注意：基于可维护性的考虑，框架限定了必须在声明有Config注解的配置类里面使用Bean注解。
 * </pre>
 * 
 * @see Cofing注解
 * @author ranger
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
@Documented
public @interface Bean {

	/**
	 * <pre>
	 * 
	 * 1.作用：如果容器已经存在相同类型的对象，是否覆盖
	 * 
	 * 默认为true
	 * 
	 * </pre>
	 * 
	 * @return 返回true，表示创建一个新对象，覆盖原来在容器中相同类型的对象；false表示容器如果已有同类型的对象，不再重新创建
	 */
	boolean cover() default true;

	/**
	 * <pre>
	 * 
	 * 1.作用：设置对象名的属性，使用该注解创建对象的类，会将对象名保存为该值。
	 * 2.对象默认命名规则为：类名的首字母小写；如：类名为UserBean,创建的对象默认命名为:userBean
	 * 
	 * </pre>
	 * 
	 * @return 返回对象名，如果不指定对象名，对象使用默认默认命名
	 * 
	 */
	String name() default "";

}
