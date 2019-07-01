package org.simplestartframwork.context.impl;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simplestartframwork.context.Context;
import org.simplestartframwork.context.annotation.component.Action;

/**
 * <pre>
 * 
 *  实现框架容器，用于存储扫描注解创建的所有对象。
 * </pre>
 * 
 * @author ranger
 * @version 1.0
 */

public class ContextImpl implements Context {
	// 1.创建一个Log4j的操作对象
	private static Logger log = LogManager.getLogger(ContextImpl.class.getName());

	/**
	 * <pre>
	 * 使用Map来存储对象，为什么使用Map对象呢？
	 * 因为预留对象名可以设置的需要。
	 * </pre>
	 */
	private Map<String, Object> objects = new HashMap<String, Object>();

	/**
	 * <pre>
	 * 增加对象到容器中
	 * </pre>
	 */
	@Override
	public void addObject(String key, Object value) {
		objects.put(key, value);

	}

	/**
	 * <pre>
	 * 返回业务控制器Action注解类型的所有对象
	 * </pre>
	 */
	@Override
	public Map<String, Object> getActionObjects() {
		return this.getObjectsByComponentType(Action.class);
	}

	/**
	 * <pre>
	 * 通过对象类的类型，以及对象名获得容器中的对象
	 * </pre>
	 */
	@Override
	public Object getObject(String objectName, Class<?> classType) {
		// 1.判断是否有对应对象名的对象
		Object object = objects.get(objectName);

		// 2.如果容器中有对象名称的对象，而且类型也兼容，直接返回该对象。
		if (object != null) {
			return object;
		} else {
			// 3.如果没有对应对象名（objectName）的对象，那么就在容器里检索，是否有兼容类型的对象。
			Collection<Object> values = objects.values();
			Iterator<Object> iterator = values.iterator();
			int count = 0;
			Object currentObject = null;
			while (iterator.hasNext()) {
				Object nextObject = iterator.next();
				// 判断classType是否是nextObject.getClass()的兼容类型。
				boolean from = classType.isAssignableFrom(nextObject.getClass());
				if (from) {
					// 如果发现有对象，计数加1
					count++;
					// 并将对象赋予当前对象
					currentObject = nextObject;
					log.debug("当前对象" + currentObject + "，创建了第" + count + " 个对象");
				}
			}
			// 如果兼容类型的对象只有一个，返回这个对象。如果大于一个，返回null
			if (count < 1) {
				return null;
			} else if (count == 1) {
				return currentObject;
			} else {
				// 如果发现一个类型容器中有多个异常，抛异常
				log.error("容器的对象不是唯一的！请确认是否一个接口继承了多个类");
				throw new RuntimeException();
			}

		}

	}

	/**
	 * <pre>
	 * 获得容器中的所有对象
	 * </pre>
	 */
	@Override
	public Map<String, Object> getObjects() {

		return this.objects;
	}

	/**
	 * <pre>
	 * 
	 * 用于通过传入标识组件类型的注解，返回对应类型的注解 注解类型分别为： Action Service Persistent Component
	 * 
	 * </pre>
	 * 
	 * @param componentsType
	 * @return
	 */
	public Map<String, Object> getObjectsByComponentType(Class<? extends Annotation> componentsType) {
		// 1.创建一个存储指定组件类型的Map
		Map<String, Object> componentsObjects = new HashMap<String, Object>();
		// 2.获得所有的容器对象
		Map<String, Object> objects = this.getObjects();
		// 3.获得Map中所有的对象的Set集合
		Set<Entry<String, Object>> entrySet = objects.entrySet();
		// 4.获得Set集合迭代器
		Iterator<Entry<String, Object>> iterator = entrySet.iterator();
		// 5.循环判断
		while (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			// 6.获得当前对象的类类型，用于获得该类的类结构中的组件注解
			Class<?> classType = entry.getValue().getClass();
			// 7.容器里的对象是否是指定注解类型的,如果是加入到componentsObjects中
			Annotation annotation = classType.getDeclaredAnnotation(componentsType);
			if (annotation != null) {
				componentsObjects.put(entry.getKey(), entry.getValue());
			}

		}
		// 8.返回指定组件类型的对象
		return componentsObjects;
	}

	@Override
	public void removeObject(String objectName) {
		Map<String, Object> objects = this.getObjects();
		objects.remove(objectName);
	}

}
