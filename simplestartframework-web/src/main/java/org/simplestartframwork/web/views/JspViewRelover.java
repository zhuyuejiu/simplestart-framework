package org.simplestartframwork.web.views;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author ranger
 * @date 2017-11-09
 *
 */
public class JspViewRelover implements ViewRelover {
	
	
	@Override
	public  void toView(String result,HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		if (result != null) {
			StringBuilder sb = new StringBuilder(result);
			String substring = sb.substring(0, sb.indexOf(":") + 1);
			System.out.println("-返回类型:-" + substring);
			if (substring != null) {
				String dispacherStr = sb.substring(sb.indexOf(":") + 1, sb.length());
				if (substring.equals("redirect:")) {
					response.sendRedirect(request.getContextPath()+"/"+dispacherStr);
				} else if (substring.equals("forward:")) {
					request.getRequestDispatcher(dispacherStr).forward(request, response);
				} else {
					throw new RuntimeException("返回的映射路径格式错误！");
				}

			} else {
				request.getRequestDispatcher(result).forward(request, response);
			}

		}
	}

}
