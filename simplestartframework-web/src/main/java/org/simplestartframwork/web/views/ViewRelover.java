package org.simplestartframwork.web.views;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 声明一个视图解释器接口，用于方便以后扩展支持多种视图，如：Freemarker,Velocity等 默认支持JSP
 * 
 * @author ranger
 * @date 2017-11-09
 */
public interface ViewRelover {
	/**
	 * 定义一个跳转到视图的方法
	 * 
	 * @param result
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	void toView(String result, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException;
}
