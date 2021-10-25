package com.oracle.filter;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class StaticFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		//System.out.println("页面静态化过滤器");
		// 拿到htmls目录
		String path = req.getServletContext().getRealPath("htmls");
		 
		// 拿到页码

		String action = req.getParameter("action"); 
		
		 if("showPasgefl".equals(action)||"showPasgeBook".equals(action)||"showPasgeUser".equals(action)){
			 
		 String pageNew = req.getParameter("pageNew");
		 if(pageNew==null||pageNew.trim().isEmpty()){
			 
			 pageNew="1";
			 
		      }
		String fileName = action+pageNew + ".html";
		// 创建文件
		File file = new File(path, fileName); 
		// 判断文件是否存在
		if(file.exists()){
			
			// 存在       拿到项目名称
			String url= req.getServletContext().getContextPath();
			 
			resp.sendRedirect(url + "/htmls/" + fileName);
			return;
			
		} else {

			StaticResponse staticResponse = new StaticResponse(resp, file.getAbsolutePath());
			chain.doFilter(request, staticResponse);
			String url = req.getServletContext().getContextPath();
			resp.sendRedirect(url + "/htmls/" + fileName);
		     }
		 }else{
			      File file=new File(path);
			      File[] arr=file.listFiles();
			      for(File f:arr){
			    	  f.delete();
			      }
			      chain.doFilter(request, response);
		 }

	}

	 
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
