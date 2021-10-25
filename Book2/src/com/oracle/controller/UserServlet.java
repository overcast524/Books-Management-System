package com.oracle.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oracle.domain.Admin;
import com.oracle.domain.PageBean;
import com.oracle.domain.User;
import com.oracle.factory.AdminServiceFactory;
import com.oracle.factory.UserServiceFactory;
 

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
	 
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String username = req.getParameter("username");
		String password=req.getParameter("password");
		String phone = req.getParameter("phone");
		String regtime = req.getParameter("regtime");
		int pageNew=this.getPageNew(req);
		String ids1 = req.getParameter("ids");

//		if ("showAllUser".equals(action)) {// 1.全查
//
//			List<User> list = UserServiceFactory.getUserServiceImpl().showUser();
//			
//			session.setAttribute("list", list);
//			resp.sendRedirect("finduser.jsp");
//			return;
//		}

		if ("delect".equals(action)) {// 2.删除
 
			String[] ids=ids1.split(",");
		 
			int[] arr=UserServiceFactory.getUserServiceImpl().delete(ids);		
			if (arr == null) {
				session.setAttribute("mag", "警告->删除异常！");
				resp.sendRedirect("UserServlet?action=showPasgeUser");
				return;
			}else{
			
			resp.sendRedirect("UserServlet?action=showPasgeUser&pageNew="+pageNew);
			return;
			}
		}

		
		
		if ("showOne".equals(action)) {// 单查 
			User s = UserServiceFactory.getUserServiceImpl().showOne(id);
			session.setAttribute("id", id);
			session.setAttribute("s", s);
			resp.sendRedirect("changeUser.jsp");
			return;
		}

       if ("updateUser".equals(action)) {// 3.单独修改用户    与单独修改头像一起用，备用

			    int id1=Integer.parseInt(id);
		       
				User stu = new User(id1,name, password, phone, regtime);
				//System.out.println(stu.toString());
				int i = UserServiceFactory.getUserServiceImpl().updateUser(stu);
				if (i > 0) {
					session.setAttribute("mag", "修改成功！");
					resp.sendRedirect("UserServlet?action=showPasgeUser");
					return;

				} else {
					session.setAttribute("mag","修改失败！");
					resp.sendRedirect("UserServlet?action=showPasgeUser");
					return;
				}
  
			

		} 
	/*	if ("updateUserTx".equals(action)) {// 3.修改用户头像一体

			 DiskFileItemFactory factory=new DiskFileItemFactory();
		      ServletFileUpload upload=new ServletFileUpload(factory);
		      //upload.setFileSizeMax(10*1024);
		       try {
				List<FileItem> list=upload.parseRequest(req);
				FileItem fileItem=list.get(0);
				
				String id2=fileItem.getString("utf-8");
				
				int id1=Integer.parseInt(id2);
			 
				
				FileItem fileItem2=list.get(2);
				String name1=fileItem2.getString("utf-8");
				
				FileItem fileItem3=list.get(3);
				String password1=fileItem3.getString("utf-8");
				
				 
			 
				FileItem fileItem4=list.get(4);
				String phone1=fileItem4.getString("utf-8");
			 
				FileItem fileItem5=list.get(5);	
				String regtime1=fileItem5.getString("utf-8");
				
				FileItem fileItem1=list.get(1);
				String filename=fileItem1.getName();
				//处理文件名】
				int index=filename.lastIndexOf("\\");
				if(index!=-1){
					filename=filename.substring(index+1);
				}
				String path=new File("E:\\JAVAWEB\\upalude").getAbsolutePath();
				 
				int hashCode=filename.hashCode();
				String hex=Integer.toHexString(hashCode);
				char c1=hex.charAt(0);
				char c2=hex.charAt(1);
				path=path+"/"+c1+"/"+c2;
		        File file=new File(path);
		        file.mkdirs();
		        String redlName=UUID.randomUUID().toString()+"_"+filename;
		        String savepath="/"+c1+"/"+c2+"/"+redlName;
		        File file1=new File(file,redlName);
				fileItem1.write(file1);
			 
				User stu = new User(id1,name1,password1,phone1,regtime1,savepath);
			 
				int i = UserServiceFactory.getUserServiceImpl().updateUser(stu);
				if (i > 0) {
					session.setAttribute("mag", "修改成功！");
					resp.sendRedirect("UserServlet?action=showPasgeUser");
					return;

				} else {
					session.setAttribute("mag","修改失败！");
					resp.sendRedirect("UserServlet?action=showPasgeUser");
					return;
				}
				
				} catch (FileUploadException e) {
					// TODO Auto-generated catch block
					 
					e.printStackTrace();
					 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			

		}
		*/
 	if ("updateTouxaing".equals(action)) {// 3.单独修改头像代码，备用

			 
		           String id1=(String) session.getAttribute("id");
		           int id2=Integer.parseInt(id1);
		           DiskFileItemFactory factory=new DiskFileItemFactory();
				      ServletFileUpload upload=new ServletFileUpload(factory);
				 	       
					List<FileItem> list;
					try {
						list = upload.parseRequest(req);
						FileItem fileItem=list.get(0);
						String filename=fileItem.getName();
			 
						int index=filename.lastIndexOf("\\");
						if(index!=-1){
							filename=filename.substring(index+1);
						}
					 
						 String path=new File("E:\\JAVAWEB\\upalude").getAbsolutePath();
					 
						int hashCode=filename.hashCode();
						String hex=Integer.toHexString(hashCode);
						char c1=hex.charAt(0);
						char c2=hex.charAt(1);
						path=path+"/"+c1+"/"+c2;
				        File file=new File(path);
				        file.mkdirs();
				        String redlName=UUID.randomUUID().toString()+"_"+filename;
				        String savepath="/"+c1+"/"+c2+"/"+redlName;
				        File file1=new File(file,redlName);
						fileItem.write(file1);
				         User stu = new User();
				         stu.setId(id2);
				         stu.setTouxiang(savepath);
				           //System.out.println(stu.toString());
				         int i = UserServiceFactory.getUserServiceImpl().updateTouxiang(stu);
				         if (i > 0) {
					       session.setAttribute("mag", "修改成功！");
					        resp.sendRedirect("UserServlet?action=showOne&id="+id1);
					           return;

				             } else {
				             	session.setAttribute("mag","修改失败！");
					        resp.sendRedirect("UserServlet?action=showOne&id="+id1);
					        return;
				           }
					} catch (FileUploadException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	 
						
 
			

		}
		
 
		if ("add".equals(action)) {// 4.添加
			

			int s = UserServiceFactory.getUserServiceImpl().dancha(username);
			if (s == 0) {
				 DiskFileItemFactory factory=new DiskFileItemFactory();
			      ServletFileUpload upload=new ServletFileUpload(factory);
			      //upload.setFileSizeMax(10*1024);
			       try {
					List<FileItem> list=upload.parseRequest(req);
					FileItem fileItem=list.get(0);
					String name1=fileItem.getString("utf-8");
					
					FileItem fileItem1=list.get(1);
					String username1=fileItem1.getString("utf-8");
					
					FileItem fileItem2=list.get(2);
					String password1=fileItem2.getString("utf-8");
					
					FileItem fileItem3=list.get(3);	
					String phone1=fileItem3.getString("utf-8");
					
					FileItem fileItem5=list.get(5);
					String regtime1=fileItem5.getString("utf-8");
				
					FileItem fileItem4=list.get(4);
					String filename=fileItem4.getName();
					//处理文件名】
					int index=filename.lastIndexOf("\\");
					if(index!=-1){
						filename=filename.substring(index+1);
					}
					//拿到要上传的目录
					 
					//String path=request.getServletContext().getRealPath("/WEB-INF/uplude");
					//目录打散、
					 String path=new File("E:\\JAVAWEB\\upalude").getAbsolutePath();
					 //System.out.println(path);
					int hashCode=filename.hashCode();
					String hex=Integer.toHexString(hashCode);
					char c1=hex.charAt(0);
					char c2=hex.charAt(1);
					path=path+"/"+c1+"/"+c2;
			        File file=new File(path);
			        file.mkdirs();
			        String redlName=UUID.randomUUID().toString()+"_"+filename;
			        String savepath="/"+c1+"/"+c2+"/"+redlName;
			        File file1=new File(file,redlName);
					fileItem4.write(file1);
					//System.out.println(savepath);
					User stu = new User(name1, username1, password1, phone1, regtime1, savepath);
					int s1 = UserServiceFactory.getUserServiceImpl().add(stu);
					if (s1 > 0) {
						
							session.setAttribute("mag","用户添加成功！");
							resp.sendRedirect("UserServlet?action=showPasgeUser");
							 
				 	  
					  } else {
						session.setAttribute("mag", "用户添加失败！");
						resp.sendRedirect("UserServlet?action=showPasgeUser");
					  	return;
					}

					
				} catch (FileUploadException e) {
					// TODO Auto-generated catch block
					 
					e.printStackTrace();
					 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else {

				session.setAttribute("mag", username + "用户名已存在");
				resp.sendRedirect("adduser.jsp");
				return;

			}
			
		}

	if("yanzheng".equals(action)){
		int a = UserServiceFactory.getUserServiceImpl().dancha(username);	  
		if (a == 0) {
			resp.getWriter().write("{\"valid\":\"true\"}");
			return;
		} else {
			resp.getWriter().write("{\"valid\":\"false\"}");
			return;
		}

		}
		
	 
	if("showPasgeUser".equals(action)){//查看
		 
		 int pageSize=5;
		 PageBean<User> pb=UserServiceFactory.getUserServiceImpl().showPesgefl(pageNew,pageSize);
		 
			session.setAttribute("pb", pb);
			req.getRequestDispatcher("showUser.jsp").forward(req, resp);
			//resp.sendRedirect("showUser.jsp");
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
