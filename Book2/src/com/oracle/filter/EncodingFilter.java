package com.oracle.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
 
public class EncodingFilter implements Filter {
  
	public void destroy() {
		// TODO Auto-generated method stub
	}

	 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		 HttpServletRequest req=(HttpServletRequest)request;
		 String method=req.getMethod();
		 
		 //System.out.println("编码过滤器....");
		 if(method.equalsIgnoreCase("POST")){
			 req.setCharacterEncoding("utf-8");
			 chain.doFilter(request, response);
			 return;
		 }
		 if(method.equalsIgnoreCase("GET")){
			 EncodingRequest encodingRequest=new EncodingRequest(req);
			 chain.doFilter(encodingRequest, response);
			 return;
		 }
		
	}
 
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
