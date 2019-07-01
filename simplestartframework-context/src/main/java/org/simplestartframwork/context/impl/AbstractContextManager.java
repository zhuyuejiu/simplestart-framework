package org.simplestartframwork.context.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simplestartframwork.context.Context;
import org.simplestartframwork.context.ContextManager;
import org.simplestartframwork.context.impl.parser.BeanCreateInstanceAnnotationParser;
import org.simplestartframwork.context.impl.parser.ComponentCreateInstanceAnnotationParser;
import org.simplestartframwork.context.impl.parser.ConfigAnnotationParser;
import org.simplestartframwork.context.impl.parser.ConstructoCreateInstanceAnnotationParser;
import org.simplestartframwork.context.impl.parser.ObjectInjectAnnotationParser;
import org.simplestartframwork.context.impl.parser.Parser;
import org.simplestartframwork.context.impl.parser.ParserExecutor;
import org.simplestartframwork.context.impl.parser.ScanAnnotationParser;
import org.simplestartframwork.context.impl.ContextImpl;

/**
 * <pre>
 * 
 * 容器操作类的抽象类，容器操作的公用的代码写这里
 * 
 * <pre>
 * 
 * @author ranger
 * @version 1.0
 *
 */
public abstract class AbstractContextManager implements ContextManager {
	// 1.创建一个Log4j的操作对象
	private static final Logger LOG = LogManager.getLogger(AbstractContextManager.class.getName());
	// 2.声明一个全局的容器对象
	private static final Context CONTEXT = new ContextImpl();

	/**
	 * <pre>
	 * 创建一个字符串数组，用于存储@ComponentScan注解获得的属性值
	 * </pre>
	 */
	protected String[] basePackage = null;

	/**
	 * <pre>
	 * 
	 * 构造方法：对象创建时，就必须要调用构造方法的逻辑
	 * 
	 * 将容器操作加载创建对象的代码写抽象类里面，这样可以方便以后扩展多种实现。
	 * 
	 * <pre>
	 * 
	 * @param classType 存入配置类（带@Config的类）的类类型
	 */
	public AbstractContextManager(Class<?>[] configClassTypes) {

		try {

			List<Parser> parseres = new ArrayList<>();
            //1.调用ConfigAnnotationParser解释Config注解
			parseres.add(new ConfigAnnotationParser());
			//2.然后通过BeanAnnotationParser解释@Bean的方式创建的对象加入容器
			parseres.add(new BeanCreateInstanceAnnotationParser());

			//3.再通过ComponentScanAnnotationParser将由@ComponteScan注解获得组件类类型
			parseres.add(new ScanAnnotationParser());
			//4.通过组件注解创建对象对象，初始化，销毁
			parseres.add(new ComponentCreateInstanceAnnotationParser());

			//5.再将由构造方法注入（@inject）的对象加入
			parseres.add(new ConstructoCreateInstanceAnnotationParser());

			//6.通过@inject注入对象到属性或者方法。
			parseres.add(new ObjectInjectAnnotationParser());

			ParserExecutor executor = new ParserExecutor(this.getContext(), configClassTypes);
			// 执行所有注解解释
			executor.execute(parseres);

		} catch (Exception e) {
			LOG.error("AbstractContextManager类:" + e.fillInStackTrace());
			e.printStackTrace();
		}
	}

	/**
	 * </pre>
	 * 通过容器里面的对象名，返回容器中的对象,指定返回类型
	 * </pre>
	 * 
	 * @param objectName 对象名字符串
	 * @return 返回唯一的对象，如果出现多个相同名字对象，会报错
	 */
	@Override
	public Object getBean(String objectName) {

		return this.getBean(objectName, null);
	}

	/**
	 * </pre>
	 * 通过容器里面的类类型，返回容器中的对象,指定返回类型
	 * </pre>
	 * 
	 * @param className 对象的类型
	 * @return 返回唯一的对象，如果出现多个相同类型的对象，会报错
	 */
	@Override
	public <T> T getBean(Class<T> className) {

		return (T) this.getBean(null, className);
	}

	/**
	 * <pre>
	 * 
	 * 实现从容器通过对象名，获得对象
	 * 
	 * </pre>
	 * 
	 * @param objectName 对象名
	 * @param className 对象类类型
	 * @return 返回容器中的对象
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getBean(String objectName, Class<T> className) {

		// 2.通过Key获得容器中的对象

		return (T) this.getContext().getObject(objectName, className);

	}

	/**
	 * <pre>
	 * 获得容器
	 * </pre>
	 * 
	 * @return
	 */
	public Context getContext() {

		return CONTEXT;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 获得当前容器
	 * @return
	 */
	public static Context getCurrentContext() {

		return CONTEXT;
	}

}
