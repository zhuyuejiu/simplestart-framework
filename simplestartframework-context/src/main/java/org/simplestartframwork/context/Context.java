package org.simplestartframwork.context;

import java.util.Map;

/**
 * <pre>
 * 
 *  框架的容器接口,定义了容器的操作行为
 * 
 * </pre>
 * 
 * @author ranger
 * @version 1.0
 */
public interface Context {

	/**
	 * </pre>
	 * 
	 * 用于增加容器中的对象
	 * 
	 * </pre>
	 * 
	 * @param key
	 * @param value
	 */
	void addObject(String key, Object value);

	/**
	 * <pre>
	 * 
	 * 用于获得容器中的所有业务控制器对象
	 * 
	 * </pre>
	 * 
	 * @return
	 */
	Map<String, Object> getActionObjects();

	/**
	 * </pre>
	 * 
	 * 根据类的类型以及设置的对象名返回容器对象 如果传入的类型容器中有对应key的对象， 而且返回类型是兼容的，直接返回对应的对象。 如果传入的类型容器中有没有对应key的对象， 那么判断传入的类型是否和容器的对象的找到唯一配置的。 如果传入类型唯一匹配，返回对象。 如果没有或者配配多个对象，都报一个RuntimeException异常
	 * 
	 * </pre>
	 * 
	 * @param classType
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	Object getObject(String objectName, Class<?> classType);

	/**
	 * <pre>
	 * 
	 * 用于获得容器中的所有对象
	 * 
	 * </pre>
	 * 
	 * @return
	 */
	Map<String, Object> getObjects();

	/**
	 * <per> 通过对象名删除容器中的对象 </per>
	 * 
	 * @param objectName
	 */
	void removeObject(String objectName);

}
