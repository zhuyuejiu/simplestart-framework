package org.simplestartframwork.context.annotation;

/**
 * <pre>
 * 
 * ComponentScan注解：用于设置扫描组件类的范围，排除那些包，包括哪些包。程序通过该注解指定的包路径创建组件类的对象到容器中
 *  组件类为声明了@Action|@Service|@Persistent|@Component注解的类
 * 
 * </pre>
 * 
 * @see Action,Service,Persistent,Component注解
 * @author ranger
 * @version 1.0
 */
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Documented
public @interface Scan {

	/**
	 * <pre>
	 * 
	 *  声明扫描组件注解ComponentScan的扫描过滤规则
	 *  通过该注解可以是实现通过类类型以及类名的正则表达是包括或者排除某些指定范围的包
	 * 
	 * </pre>
	 * 
	 * @author ranger
	 * @version 1.0
	 * 
	 */
	public @interface Fitler {

		/**
		 * <per>
		 * 
		 * 指定排除哪些包的正则表达式规则 注意：排除优先级别高于包含
		 * 
		 * </per>
		 * 
		 * @return 返回一个规则数组
		 */
		String[] exclude() default "";

		/**
		 * 指定扫描的包路径下，指定排除哪些类型的类通过组件注解创建对象
		 * @return
		 */
		Class<?>[] excludeClasses() default {};

		/**
		 * <per> 指定包含哪些包的正则表达式规则 </per>
		 * 
		 * @return 返回一个规则数组
		 */
		String[] include() default "";

		/**
		 * 指定扫描路径下，仅仅包含哪些类型的类通过组件注解创建对象
		 * @return
		 */
		Class<?>[] includeClasses() default {};

		/**
		 * <per>
		 * 
		 * 声明过滤规则的类型。分别为通过类类型以及正则表达式过滤
		 * 
		 * </per>
		 * 
		 * @see FitlerType枚举
		 * 
		 * @return 返回一个FitlerType枚举，用于判断是类类型还是正则表达
		 */
		FitlerType type();
	}

	/**
	 * <pre>
	 * 
	 * 
	 * </pre>
	 * 
	 * @author ranger
	 * @version 1.0
	 */
	public enum FitlerType {
		
		CLASSES,
		REGEX;

	}

	/**
	 * <pre>
	 * 
	 *  声明一个注解属性用于接收扫描的包路径,通过字符串数组，可以扫描多个包下面的注解类
	 * 
	 * </pre>
	 * 
	 * @return 返回包路径的数组
	 */
	String[] basePackages();
	
	/**
	 * <pre>
	 * 
	 * 通过该注解可以是实现通过类类型以及类名的正则表达是包括或者排除某些指定范围的包
	 * 
	 * </pre>
	 * 
	 * @return 返回排除或者包含的过滤规则
	 */
	Scan.Fitler[] fitlers() default {};


}