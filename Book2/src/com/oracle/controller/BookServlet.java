package com.oracle.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oracle.domain.Book;
import com.oracle.domain.Fenlei;
import com.oracle.domain.PageBean;
import com.oracle.factory.BookServiceFactory;
 
 
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		
		HttpSession session=req.getSession();
		String action=req.getParameter("action");
		
		 String id=req.getParameter("id");
	     String flname=req.getParameter("flname");
		 String  name=req.getParameter("name");
		 String  money=req.getParameter("money");
		 String press=req.getParameter("press");
		 String state=req.getParameter("state");
		 String  reader=req.getParameter("reader"); 
		 String ids1=req.getParameter("ids");
		 int pageNew=this.getPageNew(req);
//
//		if ("showfl".equals(action)) {//添加中选择分类
//		    
//			 List<Fenlei> list=BookServiceFactory.getBookServiceImpl().findfl();	
//			 
//			 session.setAttribute("list", list);
//				resp.sendRedirect("addbook.jsp");
//				return;
//			
//			
//			}
//		
	
		 if("gaojifindbook".equals(action)){
			 Book where=this.getWhere(req);
			 int pageSize=5;
	          String url=this.getUrl2(req);
			 PageBean<Book> pb=BookServiceFactory.getBookServiceImpl().showPesgefind(where,pageNew,pageSize);
			 // List<Fenlei> list=BookServiceFactory.getBookServiceImpl().findfl();	
                 pb.setUrl(url);
			 //session.setAttribute("list", list);
				session.setAttribute("pb", pb);
				session.setAttribute("showPesge","gao");
				//resp.sendRedirect("showBook.jsp");
				req.getRequestDispatcher("showBook.jsp").forward(req, resp);
				return;
			
		 }
		
		if("add".equals(action)){//添加图书	
			 //double money=Integer.parseInt(money1);
			Book book=new Book(flname, name, money, press, state, reader);
	 
				int s1=BookServiceFactory.getBookServiceImpl().addbook(book);
				if(s1>0){
					session.setAttribute("mag","图书添加成功!");
					resp.sendRedirect("BookServlet?action=showPasgeBook");
				}else{
					session.setAttribute("mag","图书添加失败!");
					resp.sendRedirect("addbook.jsp");
				}
 
			
		}
		
		if("yanzheng".equals(action)){  //分类名图书名校验，添加图书
			 
			 if("----请选择----".equals(flname)){
				 resp.getWriter().write("{\"valid\":\"false\",\"message\":\"请选择分类名\"}");
				 return;
			 }else{
				 
			int s =BookServiceFactory.getBookServiceImpl().yanzheng(name,flname);
          if(s==0){
        	  
        	  resp.getWriter().write("{\"valid\":\"true\"}");
        	  
				 return;
			 
          }else{
        	  resp.getWriter().write("{\"valid\":\"false\",\"message\":\"该分类下已存在此图书\"}");
				 return;
			 
          }
			 }	 
	 
			 
			
		}
		
		
		if("yanzhengxg".equals(action)){//分类名图书名校验，修改
			 String oldflname=req.getParameter("oldflname");
			 if("----请选择----".equals(flname)){
				 resp.getWriter().write("{\"valid\":\"false\",\"message\":\"请选择分类名\"}");
				 return;
			 }else if(oldflname.equals(flname)){
			 
				 resp.getWriter().write("{\"valid\":\"true\"}");
	        	  
				 return;
			 
		}else{
				 
			int s =BookServiceFactory.getBookServiceImpl().yanzheng(name,flname);
          if(s==0){
        	  
        	  resp.getWriter().write("{\"valid\":\"true\"}");
        	  
				 return;
			 
          }else{
        	  resp.getWriter().write("{\"valid\":\"false\",\"message\":\"该分类下已存在此图书\"}");
				 return;
			 
          }
			 }	 
	 
			 
			
		}
		

		if("delete".equals(action)){//删除
			
			
			 String[] ids=ids1.split(",");
			 
			int[] arr=BookServiceFactory.getBookServiceImpl().delete(ids);
			
			if (arr == null) {
				session.setAttribute("mag","警告->删除异常！");
				resp.sendRedirect("BookServlet?action=showPasgeBook&pageNew="+pageNew);
				return;
			}
			
			resp.sendRedirect("BookServlet?action=showPasgeBook&pageNew="+pageNew);
			
			return;
			 
			
		}	
		
	if ("showOne".equals(action)) {// 单查
            
			int id1 = Integer.parseInt(id);
			//System.out.println(id1);
			Book fl=BookServiceFactory.getBookServiceImpl().dancha(id1);
			if(fl!=null){
				 
				 List<Fenlei> list=BookServiceFactory.getBookServiceImpl().findfl();	
			  session.setAttribute("list", list);
				session.setAttribute("fl", fl);
				session.setAttribute("pageNew", pageNew);
				resp.sendRedirect("changeBook.jsp");
				return;
				
			} else {

				session.setAttribute("mag", name +"没有图书");
				resp.sendRedirect("BookServlet?action=showPasgeBook");
				return;

			}
	}
 	
if ("update2".equals(action)) {// 3.修改

	
 		
 
		// double money=Integer.parseInt(money1);
 		int id1=Integer.parseInt(id);
 		//System.out.println(id1);
 		Book book=new Book(id1,flname, name, money, press, state, reader);
 		int i =BookServiceFactory.getBookServiceImpl().update2(book); 
		if (i > 0) {
			session.setAttribute("mag",	"修改成功！");
			resp.sendRedirect("BookServlet?action=showPasgeBook&pageNew="+pageNew);
			return;

		} else {
			session.setAttribute("mag", "修改失败！");
			resp.sendRedirect("BookServlet?action=showPasgeBook&pageNew="+pageNew);
			return;
		}
 
 	}

		
		if("showPasgeBook".equals(action)){//查看
			
			// int pageNew=this.getPageNew(req);
			 int pageSize=5;
            
			 PageBean<Book> pb=BookServiceFactory.getBookServiceImpl().showPesgefl(pageNew,pageSize);
			 List<Fenlei> list=BookServiceFactory.getBookServiceImpl().findfl();	

			 session.setAttribute("list", list);
				session.setAttribute("pb", pb);
				session.setAttribute("showPesge","book");
				//resp.sendRedirect("showBook.jsp");
				req.getRequestDispatcher("showBook.jsp").forward(req, resp);
				return;
			
		}
		
		


			}
			
	 private String getUrl2(HttpServletRequest req) {
		// TODO Auto-generated method stub
		 String url=this.getUrl(req);
		 int index=url.lastIndexOf("&pageNew=");
		 if(index==-1){
			 return url;
		 }
		 url=url.substring(0, index);
		return url;
	}

	private String getUrl(HttpServletRequest req) {
		// TODO Auto-generated method stub
		 String path=req.getContextPath();
		 String servlet=req.getServletPath();
		 String param=req.getQueryString();
		 
		return path+servlet+"?"+param;
	}

	public Book getWhere(HttpServletRequest req) {
		// TODO Auto-generated method stub
			 String flname=req.getParameter("findflname");
			 if("----请选择----".equals(flname)){
				 flname=null;
				 
			 }
			 String  name=req.getParameter("name");
			 String  money=req.getParameter("money");
			  String press=req.getParameter("press");
			  String state=req.getParameter("state");
			 String  reader=req.getParameter("reader"); 
			 Book where=new Book();
			 
			 where.setFlname(flname);
			 where.setName(name);
			 where.setPress(press);
			 where.setState(state);
			 where.setReader(reader);
			 
					 
		return where;
	}

			public int getPageNew(HttpServletRequest req){
				
				String pageNew=req.getParameter("pageNew");
				
				 if(pageNew==null||pageNew.trim().isEmpty()){
					 return 1;
				 }
				return Integer.parseInt(pageNew);
			}
		
			
}		
