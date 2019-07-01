package org.simplestartframwork.web.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.simplestartframwork.context.Context;
import org.simplestartframwork.web.annotation.PathMapping;



/**
 * 
 * @author ranger
 *
 */
public class ActionRelolver {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private ServletContext application;

	/**
	 * 根据请求的参数以及路径，执行对应Controller的方法
	 * 
	 * @param request
	 * @param response
	 * @param context
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public String execute(HttpServletRequest request, HttpServletResponse response, Context context)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// 1.设置Servlet需要的API对象
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		this.application = request.getServletContext();

		// 1.获得请求过来的路径
		String uri = request.getRequestURI();
		// 2.根据路径规则，获得映射路径
		String path = this.pathRule(uri);
		// 3.通过路径获得容器中对应的业务控制器的对象和执行方法
		MappingEnttiy mappingEnttiy = this.getRequestMethod(path, context);
		// 4.获得执行方法
		Method method = mappingEnttiy.getMethod();
		// 5.获得路径对应的业务控制器
		Object action = mappingEnttiy.getAction();

		// 6.执行方法，执行方法必须有request,response两个参数
		Parameter[] parameters = method.getParameters();
		// 7.给执行的方法，设置根据执行方法的参数赋予对应的ServletAPI对象
		Object[] objects = this.getparameterObjects(parameters);
		// 8.动态调用方法，将参数对应的对象赋值执行方法
		Object resultObject = method.invoke(action, objects);
		if (resultObject != null) {
			// 7.返回执行方法返回的映射字符串
			return (String) resultObject;
		}
		return null;
	}

	/**
	 * 命名空间规则： 如果Controller设置了命名空间那必须要验证命名空间 1.如果命名空间为/,表示跟目录 2.命名空间必须包括/
	 *  //TODO 没有完成
	 * @param namespace
	 * @param url
	 * @return
	 */
	
	private boolean isNamespace(Object controller) {
		String uri=request.getRequestURI();
		PathMapping pathMapping = controller.getClass().getDeclaredAnnotation(PathMapping.class);
		if (pathMapping != null && pathMapping.value() != null) {
			String namespace = pathMapping.value();
			if ("/".equals(namespace)) {
				return true;
			} else if (uri.indexOf(namespace) > 0 && namespace.contains("/")) {
				
				return true;
			}
		}else{
			return true;
		}
		return false;
	}

	private Object[] getparameterObjects(Parameter[] parameters) {
		// 1.创建设置一个参数对象的数组，参数的格式和参数类型的个一致
		Object[] parameterObjects = new Object[parameters.length];
		// 2.根据参数的类型，将对于的对象填在对象数组中
		for (int i = 0; i < parameters.length; i++) {

			// 3.根据判断方法参数的类型和我们制定的4个作用是否是父子类关系。如果是就赋值
			// ---绑定域对象request,reponse,session,application
			Object scopeData = ParameterDataBinding.bindScope(parameters[i], request, response, session, application);
			// ---绑定基础数据类型
			Object basicData = ParameterDataBinding.bindBasicData(parameters[i], request);

			// ---绑定数组数据
			Object arrayData = ParameterDataBinding.bindArray(parameters[i], request);
			// ---绑定Map数据
			Object mapData = ParameterDataBinding.bindMap(parameters[i], request);
			// ---绑定JavaBean
			//Object javaBeanData = ParameterDataBinding.bindJavaBean(parameters[i], request);
			if (scopeData != null) {
				parameterObjects[i] = scopeData;
			} else if (basicData != null) {
				parameterObjects[i] = basicData;
			} else if (arrayData != null) {
				parameterObjects[i] = arrayData;
			} else if (mapData != null) {
				parameterObjects[i] = mapData;
			} 
			//else if (javaBeanData != null) {
			//	parameterObjects[i] = javaBeanData;
			//}

		}
		return parameterObjects;
	}

	/**
	 * 设置路径的规则 路径规则为，保留/,去掉后缀。 如：
	 * 请求路径：http://localhost:8080/webmvc-demo-test-01/test.do 合法的映射路径为：/test
	 * 
	 * @param url
	 *            传入的为请求路径
	 * @return 符合规则的路径字符串
	 *
	 */
	private String pathRule(String url) {
		// 1.创建一个String构建字符串
		StringBuilder sb = new StringBuilder(url);
		System.out.println(url);
		// 2.删除路径最后一个/之前的所有字符
		sb.delete(0, url.lastIndexOf("/"));
		System.out.println(sb.toString() + "删除后的字符串长度：" + sb.length());
		// 3.删除(.)后面的后缀
		sb.delete(sb.lastIndexOf("."), sb.length());
		return sb.toString();
	}

	/**
	 * 通过路径获得对应的业务控制器对象和执行方法
	 * 
	 * @param path
	 * @param context
	 * @return
	 */
	private MappingEnttiy getRequestMethod(String path, Context context) {
		// 9.声明内部类MappingEnttiy来封装映射的方法和对象。创建这个类的对象
		MappingEnttiy entity =null;
	
		// 1.获得Controller所有的Controller对象
		Map<String, Object> actionObjects = context.getActionObjects();
		System.out.println("-getRequestMethod-业务控制器对象池：" + actionObjects);
		// 2.获得业务控制器池里面的所有值
		Collection<Object> values = actionObjects.values();
		// 3.遍历
		Iterator<Object> iterator = values.iterator();

		while (iterator.hasNext()) {
			// 4.获得业务控制器池中当前对象
			Object object = iterator.next();
			// 5.获得当前对象的类类类型
			Class<? extends Object> classType = object.getClass();
			boolean namespace = this.isNamespace(object);
			if(namespace){
				
			}
			// 6.通过对象的类类型，获得对象的方法列表
			Method[] methods = classType.getMethods();
			// 7.循环判断方法是否有RequestMapping注解
			for (Method method : methods) {
				PathMapping mapping = method.getDeclaredAnnotation(PathMapping.class);
				// 8.RequestMapping注解存在，而且等于映射路径，返回该方法的方法和当前对象
				if (mapping != null && mapping.value().equals(path)) {
					
					entity = new MappingEnttiy();
					entity.setController(object);
					entity.setMethod(method);
				}
			}

		}

		return entity;
	}

	/**
	 * 声明一个私有的内部类对象，用于存储执行检索业务控制器时返回的数据
	 * 
	 * @author ranger
	 *
	 */
	private class MappingEnttiy {
		// 1.当前映射路径对应的对象
		private Object action;
		// 2.当前映射路径对应的方法
		private Method method;

		public Object getAction() {
			return action;
		}

		public void setController(Object action) {
			this.action = action;
		}

		public Method getMethod() {
			return method;
		}

		public void setMethod(Method method) {
			this.method = method;
		}

	}

}
