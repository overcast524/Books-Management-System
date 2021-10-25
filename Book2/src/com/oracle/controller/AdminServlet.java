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

import com.oracle.factory.AdminServiceFactory;

import net.sf.json.JSONObject;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 管理员
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
	 
		if ("login".equals(action)) {// 1.登录

			 
			/*  Admin a=new Admin(); a.setUsername(username);
			  a.setPassword(password);
			   
			  Admin admin=AdminServiceFactory.getAdminServiceImpl().login(a);
			  if(admin==null){ 
				  
				  session.setAttribute("mag", "yhmxw！");
			  resp.sendRedirect("login.jsp"); return; }
			   
			 if(!admin.getPassword().equals(a.getPassword())){
				 session.setAttribute("mag", "密码 登录！");
			  resp.getWriter().write("{\"valid\":\"false\"}");
			  return; 
			  }  else{
			  session.setAttribute("password",password);
			   session.setAttribute("username", username);
			  resp.getWriter().write("{\"valid\":\"true\"}");
			 resp.sendRedirect("index.jsp");
			 }*/
			 session.setAttribute("password",password);
			 session.setAttribute("username", username);
			 
			   resp.sendRedirect("index.jsp");
			return;
			}
		 
	if("yanzhenglg".equals(action))

	{ //登录校验

		Admin a = new Admin();
		a.setUsername(username);
		a.setPassword(password);

		Admin admin = AdminServiceFactory.getAdminServiceImpl().login(a);
		if (admin == null) {
			resp.getWriter().write("{\"valid\":\"false\"}");

			return;
		}

		if (!admin.getPassword().equals(a.getPassword())) {
			resp.getWriter().write("{\"valid\":\"false\"}");
			return;
		} else {

			resp.getWriter().write("{\"valid\":\"true\"}");
			return; 
		}

	

	}

	if("addadmin1".equals(action))
	{// 2.注册

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		// upload.setFileSizeMax(10*1024);
		try {
			List<FileItem> list = upload.parseRequest(req);
			FileItem fileItem = list.get(0);
			String name1 = fileItem.getString("utf-8");
			FileItem fileItem1 = list.get(1);
			String phone1 = fileItem1.getString("utf-8");

			FileItem fileItem2 = list.get(2);
			String username1 = fileItem2.getString("utf-8");

			FileItem fileItem4 = list.get(4);
			String password1 = fileItem4.getString("utf-8");

			FileItem fileItem3 = list.get(3);
			String filename = fileItem3.getName();
			// 处理文件名】
			int index = filename.lastIndexOf("\\");
			if (index != -1) {
				filename = filename.substring(index + 1);
			}
			// 拿到要上传的目录

			// String
			// path=request.getServletContext().getRealPath("/WEB-INF/uplude");
			// 目录打散、
			String path = new File("E:\\JAVAWEB\\upalude").getAbsolutePath();
			// System.out.println(path);
			int hashCode = filename.hashCode();
			String hex = Integer.toHexString(hashCode);
			char c1 = hex.charAt(0);
			char c2 = hex.charAt(1);
			path = path + "/" + c1 + "/" + c2;
			File file = new File(path);
			file.mkdirs();
			String redlName = UUID.randomUUID().toString() + "_" + filename;
			String savepath = "/" + c1 + "/" + c2 + "/" + redlName;
			File file1 = new File(file, redlName);
			fileItem3.write(file1);

			Admin a = new Admin(name1, username1, password1, phone1, savepath);
			// Admin
			// admin=AdminServiceFactory.getAdminServiceImpl().login(a);
			int i = AdminServiceFactory.getAdminServiceImpl().addAdmin(a);
			if (i > 0) {
				session.setAttribute("mag", "注册成功");
				resp.sendRedirect("login.jsp");
				return;
			} else {
				session.setAttribute("mag", "注册失败");
				resp.sendRedirect("addAdmin1.jsp");
			}

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	 
	if("showAdmin".equals(action))
	{// 查看用户信息
         
		resp.setContentType("text/html;charset=utf-8");
		 
		String un = (String) session.getAttribute("username");
		 
	 
		Admin s = AdminServiceFactory.getAdminServiceImpl().showAdmin(un);
		JSONObject jsonObject = JSONObject.fromObject(s);
		// System.out.println(jsonObject.toString());
		resp.getWriter().write(jsonObject.toString());
		return;
	}

	if("yanzheng".equals(action))
	{// 管理员用户注册，用户名是否存在

		int a = AdminServiceFactory.getAdminServiceImpl().yanzheng(username);
		if (a == 0) {
			resp.getWriter().write("{\"valid\":\"true\"}");
			return;
		} else {
			resp.getWriter().write("{\"valid\":\"false\"}");
			return;
		}

	}

	if("yanzhengmm".equals(action))
	{// 验证原密码是否正确
		String pw = (String) session.getAttribute("password");

		String password2 = req.getParameter("password2");

		if (pw.equals(password2)) {
			resp.getWriter().write("{\"valid\":\"true\"}");
			return;
		} else {
			resp.getWriter().write("{\"valid\":\"false\"}");
			return;
			 
		}

	}

	if("updatemm".equals(action))
	{// 修改密码
		String newpassword = req.getParameter("newpassword");
		String un = (String) session.getAttribute("username");
 
		int a = AdminServiceFactory.getAdminServiceImpl().updatemm(newpassword, un);
		if (a > 0) {
			session.setAttribute("mag", "密码修改成功,需重新登录！");
			resp.sendRedirect("exit.jsp");
			return;
		} else {
			session.setAttribute("mag", "非常抱歉->修改失败了！");
			resp.sendRedirect("changeMm.jsp");
			return;
		}

	}

}}
