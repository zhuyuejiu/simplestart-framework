package org.simplestartframwork.web.impl;


import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simplestartframwork.web.annotation.RequestParam;
import org.simplestartframwork.web.utils.ArrayUtils;

public class ParameterDataBinding {

	private static final Logger LOGGER = LogManager.getLogger(ParameterDataBinding.class.getName());

	private static String getParameterName(RequestParam requestParam, Parameter parameter) {

		if (requestParam != null && !"".equals(requestParam.value()) && requestParam.value() != null) {
			return requestParam.value();
		} else {
			return parameter.getName();
		}
	}

	/**
	 * 绑定作用域对象
	 * 
	 * @param typeClass
	 * @param request
	 * @param response
	 * @param session
	 * @param application
	 * @return
	 */
	public static Object bindScope(Parameter parameter, HttpServletRequest request, HttpServletResponse response, HttpSession session, ServletContext application) {
		// .获得参数的类型
		Class<?> typeClass = parameter.getType();
		LOGGER.debug(typeClass);
		if (ServletRequest.class.isAssignableFrom(typeClass)) {
			return request;
		} else if (ServletResponse.class.isAssignableFrom(typeClass)) {
			return response;
		} else if (HttpSession.class.isAssignableFrom(typeClass)) {
			return session;
		} else if (ServletContext.class.isAssignableFrom(typeClass)) {
			return application;
		}
		return null;
	}

	/**
	 * 绑定基础数据类型,包括八个基础数据类型和其包装类，String，Date
	 * 
	 * @param typeClass
	 * @param parameter
	 * @param request
	 * @return
	 */
	public static Object bindBasicData(Parameter parameter, HttpServletRequest request) {
		// .获得参数的类型
		Class<?> typeClass = parameter.getType();
		LOGGER.debug(typeClass);
		// 2.获得参数的数据绑定注解@Param注解
		RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
		// 3.获得参数名
		String parameterName = getParameterName(requestParam, parameter);
		LOGGER.debug("ParameterName:" + parameterName);
		// 获得参数值
		String parameterValue = request.getParameter(parameterName);
		Object object = DataConvertByType.converte(typeClass, parameterValue);
		return object;

	}

	/**
	 * 绑定数组
	 * 
	 * @param typeClass
	 * @param parameter
	 * @param request
	 * @return
	 */
	public static Object bindArray(Parameter parameter, HttpServletRequest request) {
		// .获得参数的类型
		Class<?> typeClass = parameter.getType();
		LOGGER.debug(typeClass);
		// 2.获得参数的数据绑定注解RequestParam注解
		RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
		// 3.获得绑定参数名
		String parameterName = getParameterName(requestParam, parameter);

			LOGGER.debug("--绑定字段名 :" + parameterName);

			String[] parameterValue = request.getParameterValues(parameterName);

			if (String[].class.isAssignableFrom(typeClass)) {
				// 4.判断基础数据类型的String类型
				return parameterValue;
			} else if (Long[].class.isAssignableFrom(typeClass)) {
				// 5.判断基础数据类型的Long类型

				return ArrayUtils.strToLong(parameterValue);

			} else if (Integer[].class.isAssignableFrom(typeClass)) {
				// 6.判断基础数据类型的Integer类型

				return ArrayUtils.strToInteger(parameterValue);

			} else if (Double[].class.isAssignableFrom(typeClass)) {
				// 7.判断基础数据类型的Double类型

				return ArrayUtils.strToDouble(parameterValue);

			} else if (Float[].class.isAssignableFrom(typeClass)) {
				// 8.判断基础数据类型的Float类型
				return ArrayUtils.strToFloat(parameterValue);

			} else if (Boolean[].class.isAssignableFrom(typeClass)) {
				// 9.判断基础数据类型的Boolean类型

				return ArrayUtils.strToBoolean(parameterValue);

			} else if (Character[].class.isAssignableFrom(typeClass)) {
				// 10.判断基础数据类型的Character类型

				return ArrayUtils.strToCharacter(parameterValue);

			} else if (Short[].class.isAssignableFrom(typeClass)) {
				// 11.判断基础数据类型的Short类型

				return ArrayUtils.strToShort(parameterValue);

			} else if (Byte[].class.isAssignableFrom(typeClass)) {
				// 12.判断基础数据类型的Byte类型

				return ArrayUtils.strToByte(parameterValue);

			} else if (long[].class.isAssignableFrom(typeClass)) {
				// 5.判断基础数据类型的long类型

				return ArrayUtils.strToBasicLong(parameterValue);

			} else if (int[].class.isAssignableFrom(typeClass)) {
				// 6.判断基础数据类型的int类型

				return ArrayUtils.strToBasicInteger(parameterValue);

			} else if (double[].class.isAssignableFrom(typeClass)) {
				// 7.判断基础数据类型的double类型

				return ArrayUtils.strToBasicDouble(parameterValue);

			} else if (float[].class.isAssignableFrom(typeClass)) {
				// 8.判断基础数据类型的float类型

				return ArrayUtils.strToBasicFloat(parameterValue);

			} else if (boolean[].class.isAssignableFrom(typeClass)) {
				// 9.判断基础数据类型的boolean类型

				return ArrayUtils.strToBasicBoolean(parameterValue);

			} else if (char[].class.isAssignableFrom(typeClass)) {

				// 10.判断基础数据类型的char类型

				return ArrayUtils.strToBasicCharacter(parameterValue);

			} else if (short[].class.isAssignableFrom(typeClass)) {
				// 11.判断基础数据类型的short类型

				return ArrayUtils.strToBasicShort(parameterValue);

			} else if (byte[].class.isAssignableFrom(typeClass)) {

				// 12.判断基础数据类型的byte类型
				ArrayUtils.strToBasicByte(parameterValue);
			}
		
		return null;

	}

	/**
	 * 绑定数据到Map
	 * 
	 * @param typeClass
	 * @param parameter
	 * @param request
	 * @return
	 */
	public static Object bindMap(Parameter parameter, HttpServletRequest request) {

		// .获得参数的类型
		Class<?> typeClass = parameter.getType();
		LOGGER.debug(typeClass);
		// 2.获得参数的数据绑定注解RequestParam注解
		RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
		if (requestParam != null && Map.class.isAssignableFrom(typeClass)) {
			Map<String, Object> entity = new HashMap<String, Object>();
			String parameterName = getParameterName(requestParam, parameter);
			LOGGER.debug("--绑定的字段名 :" + parameterName);
			// 3.获得页面过滤的Map
			Map<String, String[]> parameterMap = request.getParameterMap();
			LOGGER.debug(parameterMap.keySet());
			// 4.获得map的每一个元素
			Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
			// 5.迭代判断Map的每一个元素
			Iterator<Entry<String, String[]>> iterator = entrySet.iterator();
			while (iterator.hasNext()) {
				// 6.循环时，获得当前的元素
				Entry<String, String[]> entry = iterator.next();
				// 7.获得元素的key
				String key = entry.getKey();
				StringBuilder sb = new StringBuilder(key);
				// 如果属性没有(.)直接跳过
				int index = sb.indexOf(".");
				if (index > 0) {
					// 8.获得key前缀
					String keyPrefix = sb.substring(0, sb.indexOf("."));
					LOGGER.debug("request parameter bind map prefix:"+keyPrefix);
				
					// 9.如果表单过滤的值的前缀后绑定的map的@RequestParam的value一致
					if (keyPrefix.equals(parameterName)) {
						// 10.将值赋予这个map.
						String newKey = sb.delete(0, key.indexOf(".") + 1).toString();
						// 11.主意赋予执行方法的map的可以，只取.后面的属性名
						// 如： user.username, 执行方法中的key为username
						LOGGER.debug("map newKey:" + newKey + ",value:" + entry.getValue()[0]);

						entity.put(newKey, entry.getValue()[0]);
					}

				}
			}
			return entity;
		}
		return null;

	}
    /*
	public static Object bindJavaBean(Parameter parameter, HttpServletRequest request) {
		try {
			// .获得参数的类型
			Class<?> typeClass = parameter.getType();

			LOGGER.debug(typeClass);
			// 2.获得参数的数据绑定注解@Param注解
			RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
			// 必须要指定不能是Map类型，因为Map类型和JavaBean类型的规则是一样的
			if (!typeClass.equals(Map.class)) {
				// 注意.对象必须要在判断参数注解值不为空的时候创建，不然会导致没有参数的方法出问题
				Object object = typeClass.newInstance();
				String parameterName = getParameterName(requestParam, parameter);
				LOGGER.debug("--绑定的字段名 :" + parameterName);
				// 3.获得页面过滤的Map
				Map<String, String[]> parameterMap = request.getParameterMap();
				LOGGER.debug(parameterMap.keySet());
				// 4.获得map的每一个元素
				Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
				// 5.迭代判断Map的每一个元素
				Iterator<Entry<String, String[]>> iterator = entrySet.iterator();
				while (iterator.hasNext()) {
					// 6.循环时，获得当前的元素
					Entry<String, String[]> entry = iterator.next();
					// 7.获得元素的key
					String key = entry.getKey();
					StringBuilder sb = new StringBuilder(key);
					// 8.获得key前缀
					int index = sb.indexOf(".");
					// 如果没有属性没有（.），跳过
					if (index > 0) {
						String keyPrefix = sb.substring(0, sb.indexOf("."));
						LOGGER.debug(keyPrefix + "----");
						// 9.如果表单过滤的值的前缀后绑定的参数名value一致
						if (keyPrefix.equals(parameterName)) {
							// 10.将值赋予这个map.
							String newKey = sb.delete(0, key.indexOf(".") + 1).toString();
							// 11.主意赋予执行方法的map的可以，只取.后面的属性名
							Method method = null;
							Field field = null;
							try {
								// getDeclaredField表示可以访问私有的属性
								field = typeClass.getDeclaredField(newKey);
								method = typeClass.getMethod("set" + NamedConversionUtils.classNameToObjectName(newKey), field.getType());
							} catch (Exception e) {
								// 如果报错不处理，返回null 就可以
							}

							if (method != null) {
								Object converteValue = DataConvertByType.converte(field.getType(), entry.getValue()[0]);
								method.invoke(object, converteValue);
							}

						}

					}
				}
				return object;
			}

		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}*/

}
