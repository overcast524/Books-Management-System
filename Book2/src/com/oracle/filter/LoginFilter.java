package com.oracle.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		//System.out.println("登陆过滤器。。。。");
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		
		if (username == null || username.isEmpty()) {
			String url = req.getRequestURI();

			String path = url.substring(url.lastIndexOf("/") + 1);

			if ("login.jsp".equals(path) || "addAdmin1.jsp".equals(path)) {
				chain.doFilter(request, response);
			} else {

				resp.sendRedirect("login.jsp");
			}
		} else {

			chain.doFilter(request, response);
		}

		// chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub

		//System.out.println("过滤器创建了。。。。。。。。。。。。。。");
	}

}
