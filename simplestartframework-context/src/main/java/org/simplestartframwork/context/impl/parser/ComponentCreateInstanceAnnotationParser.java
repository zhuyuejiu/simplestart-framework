package org.simplestartframwork.context.impl.parser;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simplestartframework.core.utils.NamedConversionUtils;
import org.simplestartframwork.context.Context;
import org.simplestartframwork.context.impl.parser.CreateInstanceAbstractParser;
import org.simplestartframwork.context.impl.parser.ParserParam;
import org.simplestartframwork.context.utils.ReflectAnnotationUtils;

/**
 * ComponentLifecycleAnnotationParser解释类：
 *  1。用于实现通过组件注解创建对象
 *  组件注解包括： Action|Service|Persistent|Component
 *  
 * @author ranger
 * @version 1.0
 *
 */
public class ComponentCreateInstanceAnnotationParser extends CreateInstanceAbstractParser {

	// 1.创建一个Log4j的操作对象
	private static final Logger LOG = LogManager.getLogger(ComponentCreateInstanceAnnotationParser.class.getName());

	@Override
	public boolean handle()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		LOG.info("-ComponentLifecycleAnnotationParser-start");
		 Set<?> classNameTypes = ParserParam.get(ParserParam.ALL_COMPONENT_CLASS_TYPES, Set.class);
		 Context context = ParserParam.get(ParserParam.OBJECT_CONTEXT, Context.class);
		Set<Class<?>> readSet = new HashSet<Class<?>>();
		// 1.循环逐个遍历，获得每个类的类全限制名
		for (Object classNameType : classNameTypes) {
			Class<?> objectClassType = (Class<?>) classNameType;
			Object componentObject = this.doCreate(objectClassType);
			if (componentObject != null) {

				// 4.声明一个对象名遍历，作用为了修改默认对象支持首字符小写
				String objectName = null;
				// 5.获得组件注解的value属性值的自定义对象属性
				String componentOfValue = ReflectAnnotationUtils.getComponentOfValue(objectClassType);
				// 6.如果组件value属性为无值，那么使用默认命名法命名对象
				if ("".equals(componentOfValue) || componentOfValue == null) {

					objectName = NamedConversionUtils.classNameToObjectName(componentObject.getClass().getSimpleName());
					LOG.debug("默认命名组件对象名：" + objectName);
				} else {
					// 7.如果组件注解的name属性有值，使用自定义命名对象
					objectName = componentOfValue;
					LOG.debug("自定义组件对象名：" + objectName);
				}

				LOG.debug("组件对象：" + componentObject);
				//执行初始化方法
				this.doInitialize(componentObject);
				//将Value注解的值赋予给对应的变量
				this.doValue(componentObject);

				// 7.将对象加入到容器
				context.addObject(objectName, componentObject);
				// 8.将创建对象的类的类名加入到一个集合
				readSet.add(objectClassType);
			}
		}
		// 9.删除Set集合已经创建对象的组件类的类类型
		classNameTypes.removeAll(readSet);
		LOG.info("-ComponentLifecycleAnnotationParser-end");
		return true;
	}

	protected Object doCreate(Class<?> objectClassType) throws InstantiationException, IllegalAccessException {

		// 判断有参数的构造函数上面有注解，如果有不创建
		if (ReflectAnnotationUtils.hasConstructorInject(objectClassType) == false) {
			// 3.创建组件对象
			return objectClassType.newInstance();
		}
		return null;
	}



}
