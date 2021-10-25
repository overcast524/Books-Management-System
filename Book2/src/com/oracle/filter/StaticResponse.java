package com.oracle.filter;
 
 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class StaticResponse extends HttpServletResponseWrapper {
 //private PrintWriter out;
	private PrintWriter out;
	public StaticResponse(HttpServletResponse response, String fileName) {
		super(response);
		// TODO Auto-generated constructor stub
		try {
			out=new PrintWriter(fileName, "utf-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
       
	 @Override
		public PrintWriter getWriter() throws IOException {
			// TODO Auto-generated method stub
		 //把数据响应到浏览器中
		 //把数据保存在指定html文件中
			return out;
		}
                     
}
