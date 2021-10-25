package com.oracle.controller;

import java.io.IOException;
 
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oracle.domain.Fenlei;
import com.oracle.domain.PageBean;
 
import com.oracle.factory.FenleiServiceFactory;
 

import net.sf.json.JSONArray;

 
public class FenleiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		
		HttpSession session=req.getSession();
		String action=req.getParameter("action");
		String id=req.getParameter("id");
		String name=req.getParameter("name");
		String oldname=req.getParameter("oldname");
		String ids1=req.getParameter("ids");
		 int pageNew=this.getPageNew(req);
		//String back=req.getParameter("back");
		 
		if("add".equals(action)){//添加分类
			Fenlei fl=new Fenlei(name);
	 
				int s1=FenleiServiceFactory.getFenleiServiceImpl().addfl(name);
				if(s1>0){
					session.setAttribute("mag", "分类添加成功!");
					resp.sendRedirect("FenleiServlet?action=showPasgefl");
				}else{
					session.setAttribute("mag","分类添加失败!");
					resp.sendRedirect("addfl.jsp");
				}
 
			
		}
//		if("showfl".equals(action)){//查看
//			
//			 List<Fenlei> list=FenleiServiceFactory.getFenleiServiceImpl().findfl();
//			 
//				session.setAttribute("list", list);
//				resp.sendRedirect("findfl.jsp");
//				return;
//			
//		}
		
		if("delete".equals(action)){//删除
			
			String[] ids=ids1.split(",");
			//System.out.println(ids[0]+" "+ids[1]);
			int[] arr=FenleiServiceFactory.getFenleiServiceImpl().delete(ids);
			
			if (arr == null) {
				session.setAttribute("mag","警告->删除异常！");
				resp.sendRedirect("FenleiServlet?action=showPasgefl&pageNew="+pageNew);
				return;
			}
			
			resp.sendRedirect("FenleiServlet?action=showPasgefl&pageNew="+pageNew);
			return;
			 
			
		}
		
	if("yanzheng".equals(action)){//验证分类是否存在
			
			int a=FenleiServiceFactory.getFenleiServiceImpl().yanzheng(name);
			  
		    if(a==0){
	        	  
	        	  resp.getWriter().write("{\"valid\":\"true\"}");
	        	  
					 return;
				 
	          }else{
	        	  resp.getWriter().write("{\"valid\":\"false\",\"message\":\"该分类名已存在\"}");
					 return;
				 
	          }
				  
			 
		}
	 
		
		
		if ("showOne".equals(action)) {// 单查
            
			int id1 = Integer.parseInt(id);
			Fenlei fl=FenleiServiceFactory.getFenleiServiceImpl().dancha(id1);
			if(fl!=null){
				session.setAttribute("fl", fl);
				session.setAttribute("pageNew", pageNew);
				resp.sendRedirect("changeFenlei.jsp");
				return;
				
			} else {

				session.setAttribute("mag", name + "没有分类");
				resp.sendRedirect("FenleiServlet?action=showPasgefl&pageNew="+pageNew);
				return;

			}
			
		}

 	if ("update".equals(action)) {// 3.修改
 		
 		int id1 = Integer.parseInt(id);
 		Fenlei fl1=new Fenlei(id1,name);
 		int i = FenleiServiceFactory.getFenleiServiceImpl().update(fl1);
 		
 	 
		if (i > 0) {
			session.setAttribute("mag", "修改成功！");
			resp.sendRedirect("FenleiServlet?action=showPasgefl&pageNew="+pageNew);
			return;

		} else {
			session.setAttribute("mag", "修改失败！");
			resp.sendRedirect("FenleiServlet?action=update&pageNew="+pageNew);
			return;
		}
 	 	 
 	}
 	

if ("showOne2".equals(action)) {// 多项修改单查
	resp.setContentType("text/html;charset=utf-8");
	 List<Fenlei> list=FenleiServiceFactory.getFenleiServiceImpl().findfl();
	 JSONArray jsonArray=JSONArray.fromObject(list);
	 
//	   XStream  xs=new XStream();
//	  xs.alias("Fenlei", Fenlei.class);
//	  xs.omitField(Fenlei.class, "id");
//	  String xml= xs.toXML(list);
 	 // System.out.println(jsonArray.toString());
	 
	 resp.getWriter().write(jsonArray.toString());
		 return;
	
	
	}

if ("update2".equals(action)) {// 3.多项修改
  
 		int i = FenleiServiceFactory.getFenleiServiceImpl().update2(oldname,name);
 		
 	 
		if (i > 0) {
			session.setAttribute("mag","修改成功！");
			resp.sendRedirect("FenleiServlet?action=showPasgefl");
			return;

		} else {
			session.setAttribute("mag","修改失败！");
			resp.sendRedirect("FenleiServlet?action=showOne2");
			return;
		}
 	}
  



if("showPasgefl".equals(action)){//查看
 
	 int pageSize=5;
	 PageBean<Fenlei> pb=FenleiServiceFactory.getFenleiServiceImpl().showPesgefl(pageNew,pageSize);
	 
		session.setAttribute("pb", pb);
		//resp.sendRedirect("showFenlei.jsp");
		req.getRequestDispatcher("showFenlei.jsp").forward(req, resp);
		return;
	
}

	}
	
	public int getPageNew(HttpServletRequest req){
		
		String pageNew=req.getParameter("pageNew");
		
		 if(pageNew==null||pageNew.trim().isEmpty()){
			 return 1;
		 }
		return Integer.parseInt(pageNew);
	}

}
