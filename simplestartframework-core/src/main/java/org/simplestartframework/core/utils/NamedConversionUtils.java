package org.simplestartframework.core.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * <pre>
 *  创建命名规则帮助类
 *  1.组件类-默认对象名的实现
 *  2.方法注入-方法名转对象名的实现
 * </pre>
 * 
 * @author ranger
 * @version 1.0
 *
 */
public class NamedConversionUtils {
	// 1.创建一个Log4j的操作对象
	private static final Logger LOG = LogManager.getLogger(NamedConversionUtils.class.getName());

	/**
	 * <pre>
	 *  该方法实现将一个类全限制名修改为一个默认命名规则的对象名
	 * </pre>
	 * 
	 * @param classAllName 类全限制名
	 * @return 默认命名对象
	 */
	public static String classAllNameToObjectName(String classAllName) {
		StringBuilder sb = new StringBuilder(classAllName);
		 sb.delete(0, sb.lastIndexOf(".")+1);
		// 修改首字符为小写
		sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
		return sb.toString();
	}

	/**
	 * <pre>
	 * 
	 * 将类名修改为对象名，首字母小写
	 * 
	 * </pre>
	 * 
	 * @param className 该参数为类名
	 * @return 返回默认的对象名
	 */
	public static String classNameToObjectName(String className) {
		StringBuilder sb = new StringBuilder(className);
		// 修改首字符为小写
		sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
		return sb.toString();
	}
	
	/**
	 * <pre>
	 * 
	 * 将类名修改为对象名，首字母大写
	 * 
	 * </pre>
	 * 
	 * @param objectName 该参数为对象名
	 * @return 返回类名
	 */
	public static String ObjectNameToclassName(String objectName) {
		StringBuilder sb = new StringBuilder(objectName);
		// 修改首字符为小写
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		return sb.toString();
	}
	

	/**
	 * <pre>
	 *   设置将set方法的方法名修改为对应规范的对象名
	 * </pre>
	 * 
	 * @param methodName 方法名
	 * @return 对象名
	 */
	public static String setMethodToObjectName(String methodName) {
		
		int index = methodName.indexOf("set");
		System.out.println(index);
		LOG.debug("setMethodToObjectName-set单词的位置："+index);
		StringBuilder sb = new StringBuilder(methodName);
		//1.如果set单词在第一位，删除set单词
		if(index==0) {
			//1.删除set单词
			sb.delete(0, 3);
		}
		// 2.再将首字符修改为为小写
		sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
		return sb.toString();
	}

}
