package org.simplestartframwork.context.impl.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.simplestartframwork.context.annotation.Config;
import org.simplestartframwork.context.annotation.Import;
import org.simplestartframwork.context.impl.parser.Parser;
import org.simplestartframwork.context.impl.parser.ParserParam;

/**
 * <pre>
 *  ConfigAnnotationParser类：用于解释Config注解，框架必须从配置类开始解释注解。
 *  如果配置类没有Config表示，该类非配置类，那么就要终止所以注解解释器的执行
 * </pre>
 * 
 * @author ranger
 * @version 1.0
 *
 */
public class ConfigAnnotationParser implements Parser {
	
	

	@Override
	public boolean handle() {
		List<Class<?>> configList = new ArrayList<Class<?>>();
		// 1.获得配置类的类类型
		Class<?>[] configClassTypes = ParserParam.get(ParserParam.CONFIG_CLASS_TYPES, Class[].class);
		// 1.判断配置类是否有Config注解
		for (int i = 0; i < configClassTypes.length; i++) {
			Class<?> configClassType = configClassTypes[i];
			Config configAnnotation = configClassType.getDeclaredAnnotation(Config.class);
			// 2.如果是有Config注解的配置类
			if (configAnnotation == null) {
				return false;
			} else {
				// 3.通过递归算法将所有Import的config获得
				configList.addAll(doImport(configClassType));
			}

		}

		ParserParam.set(ParserParam.CONFIG_CLASS_TYPES, configList);
  
		return true;
	}

	/**
	 * 无限级读取Import注解包含的配置类，使用递归算法 1.必须声明一个死循环 2.必须要找到结束循环的条件
	 * 
	 * @param configClassType
	 * @return
	 */
	private List<Class<?>> doImport(Class<?> configClassType) {
		List<Class<?>> configList = new ArrayList<Class<?>>();
		configList.add(configClassType);
		int count = 0;
		while (true) {

			Class<?>[] importConfigClassTypes = this.getImportClass(configList.get(count));
			if (importConfigClassTypes == null) {
				break;
			} else {
				List<Class<?>> importConfigClassTypesList = Arrays.asList(importConfigClassTypes);
				configList.addAll(importConfigClassTypesList);
				count++;
			}

		}

		return configList;

	}

	private Class<?>[] getImportClass(Class<?> configClassType) {
		Import importAnnotation = configClassType.getDeclaredAnnotation(Import.class);
		if (importAnnotation != null && importAnnotation.value() != null) {
			return importAnnotation.value();
		}
		return null;
	}

}
