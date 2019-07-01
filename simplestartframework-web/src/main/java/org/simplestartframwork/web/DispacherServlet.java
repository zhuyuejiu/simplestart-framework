package org.simplestartframwork.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.simplestartframwork.context.impl.AnntationContextManager;
import org.simplestartframwork.web.impl.ActionRelolver;
import org.simplestartframwork.web.views.JspViewRelover;


/**
 * 核心控制，用于拦截所有的请求，MVC框架的入口。
 * 
 * @author ranger
 * @date 2017-11-08
 *
 */
public class DispacherServlet extends HttpServlet {

	private static final long serialVersionUID = -5969172674760020122L;
	private AnntationContextManager anntationContextManager = null;
	

	/**
	 * 启动Web程序时，获得配置的参数
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		// 1.配置参数是配置类的类全名
		String parameter = config.getInitParameter("config");
		// 2.查看是否有参数了
		System.out.println(parameter);
		try {
			// 3.将字符串使用反射技术，转成一个Class类
			Class<?> classType = Class.forName(parameter);
			// 4.将对象加载到容器里面
			this.anntationContextManager = new AnntationContextManager(classType);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("-测试成功--");

		ActionRelolver rs = new ActionRelolver();
		try {
			 //1.根据路径执行Controller对应的映射方法
			String result = rs.execute(request, response, anntationContextManager.getContext());
             //2.执行视图解释器，返回视图
			JspViewRelover viewRelover=new JspViewRelover();
			viewRelover.toView(result, request, response);
		
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
