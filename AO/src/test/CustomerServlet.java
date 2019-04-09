package test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.management.modelmbean.RequiredModelMBean;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author mulming
 * @time 2018年5月25日
 * @descript CustomerServlet
 * 
 * 模拟框架的web层
 * 
 */

public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String method=request.getServletPath();
		String mathodName=method.substring(1);
		mathodName=mathodName.substring(0, mathodName.length()-3);
		try {
			//利用反射获取mythodname对应的方法
			Method method1=getClass().getDeclaredMethod(mathodName, HttpServletRequest.class,HttpServletResponse.class);
			//利用反射调用对应的方法
			method1.invoke(this, request,response);
		} catch (Exception e) {
			response.sendRedirect("error.jsp");
		}
	}
	
}
