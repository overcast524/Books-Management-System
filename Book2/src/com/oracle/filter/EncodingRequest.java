package com.oracle.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodingRequest extends HttpServletRequestWrapper {
	public HttpServletRequest request;
	public EncodingRequest(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
		this.request=request;
	}

	@Override
	public String getParameter(String name) {
		// TODO Auto-generated method stub
		String value=this.request.getParameter(name);
		try {
			if(value!=null){
			byte[] arr=value.getBytes("ISO-8859-1");
			value=new String(arr,"UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return value;
		
	}
	
	
}
