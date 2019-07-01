package org.simplestartframwork.context.impl.parser;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simplestartframework.core.utils.PackageScanUtils;
import org.simplestartframwork.context.annotation.Scan;
import org.simplestartframwork.context.impl.parser.Parser;
import org.simplestartframwork.context.impl.parser.ParserParam;
import org.simplestartframwork.context.utils.ReflectAnnotationUtils;

public class ScanAnnotationParser implements Parser {
	// 1.创建一个Log4j的操作对象
	private static final Logger LOG = LogManager.getLogger(ScanAnnotationParser.class.getName());

	/**
	 * <pre>
	 * 
	 * 该方法用于实现，通过ComponentScan注解扫描组件类，实现将组件类对象创建，并且加入到容器中
	 * 
	 * </pre>
	 * 
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws ClassNotFoundException
	 */
	@Override
	public boolean handle() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, ClassNotFoundException {
		LOG.info("-ComponentScanAnnotationParser-start");
		// 1.获得配置类的类类型
		List<?> configClassTypes = ParserParam.get(ParserParam.CONFIG_CLASS_TYPES, List.class);
		List<String> allBasePackages = new ArrayList<String>();
		Set<Class<?>> allComponentClassTypes = new HashSet<Class<?>>();
		for (Object configClassTypeObject : configClassTypes) {
			Class<?> configClassType = (Class<?>) configClassTypeObject;
			// 3. 通过配置类的类类型，获得组件扫描注解
			Scan scan = configClassType.getDeclaredAnnotation(Scan.class);
			if (scan != null) {
				// 4. 通过扫描组件ComponentScan，获得注解属性的包名,
				String[] basePackages = scan.basePackages();

				// 5.将扫描多个包路径，获得该路径下的所有类的类全限制名
				Set<String> classNames = PackageScanUtils.getClassNames(basePackages, true);
				// 6.获得所有组件类的类类型
				Set<Class<?>> componentClassTypes = this.getAllComponentClassType(classNames);

				allBasePackages.addAll(Arrays.asList(basePackages));
				allComponentClassTypes.addAll(componentClassTypes);
			}
		}

		// 7.将包路径放在解释器全局参数里面
		ParserParam.set(ParserParam.BASE_PACKAGES_NAME, allBasePackages);
		// 8.将所有的组件类集合放在全局参数里面
		ParserParam.set(ParserParam.ALL_COMPONENT_CLASS_TYPES, allComponentClassTypes);

		LOG.info("-ComponentScanAnnotationParser-end");
		return true;

	}

	/**
	 * <pre>
	 *  根据类的全限制名，过滤出我们需要的组件类的类类型
	 * </pre>
	 * 
	 * @param classNames 该参数为类的全限制名字符串集合
	 * @return 返回的组件类的集合
	 * @throws ClassNotFoundException 动态反射技术，找不到对应类权限名的类文件
	 */
	private Set<Class<?>> getAllComponentClassType(Set<String> classAllNames) throws ClassNotFoundException {
		Set<Class<?>> allComponentClassType = new HashSet<Class<?>>();
		for (String className : classAllNames) {
			Class<?> classType = Class.forName(className);
			boolean flag = ReflectAnnotationUtils.isComponent(classType);
			if (flag) {
				allComponentClassType.add(classType);
			}
		}
		return allComponentClassType;
	}

}
