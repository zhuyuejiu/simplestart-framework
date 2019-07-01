package org.simplestartframwork.context.impl.parser;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import org.simplestartframework.core.utils.TypeConversionUtils;
import org.simplestartframwork.context.annotation.Initialize;
import org.simplestartframwork.context.annotation.Value;
import org.simplestartframwork.context.impl.parser.Parser;

/**
 * CreateInstanceAbstractParser解释类：是框架所有创建对象的方式的父类。
 * 
 * 1.通过Initialize注解，实现创建对象执行初始方法
 * 2.通过Value注解，实现给属性、方法、构造方法初始值
 * 
 * @author ranger
 * @version 1.0
 * @since
 */
public abstract class CreateInstanceAbstractParser implements Parser {

	/**
	 * <pre>
	 *  在创建对象后，执行初始化方法
	 * </pre>
	 * 
	 * @param componentObject
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	protected void doInitialize(Object componentObject)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<? extends Object> componentObjectClass = componentObject.getClass();
		Method[] methods = componentObjectClass.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			Initialize initialize = method.getDeclaredAnnotation(Initialize.class);
			if (initialize != null) {
				// 注意：初始化参数不用插入参数
				method.invoke(componentObject);
			}
		}
	}

	/**
	 * <pre>
	 * 
	 * 该方法实现将在创建对象之前，如果该对象的类声明有@Value注解。将@Value注解的值赋予该注解对应的字段
	 * 
	 * <pre>
	 * 
	 * @param objectClassType 传入创建对象的类的类类型
	 * @return 返回已经设置
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	protected void doValue(Object componentObject) {

		try {
			valueToField(componentObject);
			valueToMethodIncludeParam(componentObject);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * <pre>
	 * Value注解对属性的支持
	 * </pre>
	 * 
	 * @param componentObject
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private void valueToField(Object componentObject) throws IllegalArgumentException, IllegalAccessException {
		Class<? extends Object> componentObjectClass = componentObject.getClass();
		// 1.注意：如果要获得对象的私有属性，必须使用getDeclaredFields
		// 属性支持Value注解
		Field[] fields = componentObjectClass.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			Value valueAnnotation = field.getDeclaredAnnotation(Value.class);
			if (valueAnnotation != null) {
				field.setAccessible(true);
				// 支持标量类型，String + 基础类型+基础类型的包装类
				String value = valueAnnotation.value();
				Class<?> type = field.getType();
				Object object = TypeConversionUtils.StringToBasic(type, value);
				field.set(componentObject, object);
			}
		}
	}

	/**
	 * <pre>
	 * 
	 * Value注解对方法以及方法参数的支持
	 * 
	 * </pre>
	 * 
	 * @param componentObject
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private void valueToMethodIncludeParam(Object componentObject)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Class<? extends Object> componentObjectClass = componentObject.getClass();

		// 2.方法以及方法参数支持支持Value注解
		// 注意，如果要获得私有方法，必须要使用getDeclaredMethods方法
		Method[] methods = componentObjectClass.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			// 私有方法也支持Value注解
			method.setAccessible(true);
			Value valueAnnotation = method.getDeclaredAnnotation(Value.class);
			Class<?>[] parameterTypes = method.getParameterTypes();
			// 设置方法的Value赋予第一个参数，如果有多个参数那么就无法设置值
			if (valueAnnotation != null) {

				if (parameterTypes.length == 1) {
					Object param = TypeConversionUtils.StringToBasic(parameterTypes[0], valueAnnotation.value());
					method.invoke(componentObject, param);
				}
			}
			
			// 设置方法参数的Value注解的值
			this.doValueToParam(method, componentObject);

		}
	}
	
	/**
	 * <pre>
	 *   该方法实现给方法的的Value注解注入值
	 * </pre>
	 * 
	 * @param method
	 * @param componentObject
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	protected void doValueToParam(Method method,Object componentObject) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// 设置方法参数的Value注解的值

		Parameter[] parameters = method.getParameters();

		// 创建一个集合存放值Value注解的值
		List<Object> values = new ArrayList<Object>();
		if (this.paramHasValue(parameters)) {
			for (int j = 0; j < parameters.length; j++) {

				Parameter parameter = parameters[j];
				Value value = parameter.getDeclaredAnnotation(Value.class);
				if (value != null) {
					//注意，获得的参数类型，getType()
					Object param = TypeConversionUtils.StringToBasic(parameter.getType(), value.value());
					System.out.println(param.getClass().getTypeName());
					values.add(param);
				} else {

					Object nullParam = TypeConversionUtils.StringToBasic(parameter.getType(), null);
					values.add(nullParam);
				}

				
			}
			method.invoke(componentObject, values.toArray());
		}
	}
	
	/**
	 * 通过O
	 */
	public Object doScope(Object componentObject) {
		
		
		return null;
	}
	
	

	/**
	 * <pre>
	 *   判断方法是否包括有Value注解的参数
	 * </pre>
	 * 
	 * @param parameters
	 * @return
	 */
	private boolean paramHasValue(Parameter[] parameters) {
		for (int i = 0; i < parameters.length; i++) {
			Parameter parameter = parameters[i];
			Value value = parameter.getDeclaredAnnotation(Value.class);
			if (value != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <pre>
	 * 
	 * 定义了一个创建对象的抽象方法
	 * 
	 * </pre>
	 * 
	 * @param objectClassType
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	protected abstract Object doCreate(Class<?> objectClassType) throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, IllegalArgumentException, InvocationTargetException;

}
