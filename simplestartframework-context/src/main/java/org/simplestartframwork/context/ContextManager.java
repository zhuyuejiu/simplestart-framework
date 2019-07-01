package org.simplestartframwork.context;

/**
 * <pre>
 * 
 * 框架的容器操作接口
 * 
 * </pre>
 * 
 * @author ranger
 * @version 1.0
 */
public interface ContextManager {

	/**
	 * <pre>
	 * 通过容器里面的对象名，返回容器中的对象
	 * </pre>
	 * 
	 * @param objectName
	 * @return
	 */
	Object getBean(String objectName);

	/**
	 * </pre>
	 * 通过容器里面的对象名，返回容器中的对象,指定返回类型
	 * </pre>
	 * 
	 * @param objectName
	 * @param className
	 * @return
	 */
	<T> T getBean(String objectName, Class<T> className);

	/**
	 * </pre>
	 * 通过容器里面的类型，返回容器中的对象,指定返回类型
	 * </pre>
	 * 
	 * @param objectName
	 * @param className
	 * @return
	 */
	<T> T getBean(Class<T> className);
	
	/**
	 * <pre>
	 *  通过该方法，关闭容器。
	 * </pre>
	 */
	void close();

}
