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
import com.oracle.domain.User;

import com.oracle.factory.BookServiceFactory;
import com.oracle.factory.UserServiceFactory;

/**
 * Servlet implementation class UserForegroundServlet
 */
public class UserForegroundServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html;charset=utf-8");

		HttpSession session = req.getSession();
		String action = req.getParameter("action");

		String id = req.getParameter("id");

		int pageNew = this.getPageNew(req);

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		if ("login".equals(action)) {// 1.登录

			session.setAttribute("qpassword", password);

			session.setAttribute("qusername", username);

			resp.sendRedirect("userindex.jsp");
			return;
		}

		if ("yanzhenglg".equals(action))

		{// 登录验证

			User a = new User();
			a.setUsername(username);
			a.setPassword(password);

			User user = UserServiceFactory.getUserServiceImpl().login(a);
			if (user == null) {
				resp.getWriter().write("{\"valid\":\"false\"}");

				return;
			}

			if (!user.getPassword().equals(a.getPassword())) {
				resp.getWriter().write("{\"valid\":\"false\"}");
				return;
			} else {

				resp.getWriter().write("{\"valid\":\"true\"}");

			}

			return;

		}

		if ("jieshu".equals(action)) {// 1.借书

			int id1 = Integer.parseInt(id);
			// System.out.println(id1);

			String qusername = req.getParameter("qusername");
			// System.out.println("借书qusername"+qusername);

			Book book = new Book();
			book.setId(id1);
			book.setReader(qusername);
			int b = BookServiceFactory.getBookServiceImpl().jieshu(book);
			session.setAttribute("qusername", qusername);
			session.setAttribute("mag", "借书成功");
			resp.sendRedirect("UserForegroundServlet?action=showPasgeBook");

			return;
		}

		// 1.还书
		if ("huanshu".equals(action)) {

			int id1 = Integer.parseInt(id);
			// System.out.println(id1);
			String qusername = req.getParameter("qusername");
			// System.out.println("还书qusername"+qusername);

			Book book = new Book();
			book.setId(id1);
			book.setReader("无");
			int b = BookServiceFactory.getBookServiceImpl().huanshu(book);
			session.setAttribute("qusername", qusername);

			session.setAttribute("mag", "还书成功");
			resp.sendRedirect("UserForegroundServlet?action=showPasgeBook");
			// resp.sendRedirect("userindex.jsp");
			return;
		}

		// 搜索
		if ("showPasgeUsBook".equals(action)) {

			// int pageNew=this.getPageNew(req);
			int pageSize = 5;
			String url = this.getUrl2(req);
			PageBean<Book> pb = UserServiceFactory.getUserServiceImpl().showPesgeUsBook(pageNew, pageSize, name);
			pb.setUrl(url);
			String qusername = req.getParameter("qusername");
			// System.out.println("搜索qusername"+qusername);
			session.setAttribute("qusername", qusername);
			session.setAttribute("showPesge","soushuo");
			session.setAttribute("pb", pb);
			  //resp.sendRedirect("showBook.jsp");
			req.getRequestDispatcher("userForeground.jsp").forward(req, resp);
			return;

		}

		if ("showPasgeBook".equals(action)) {// 查看

			// int pageNew=this.getPageNew(req);
			int pageSize = 5;

			PageBean<Book> pb = BookServiceFactory.getBookServiceImpl().showPesgefl(pageNew, pageSize);

			String q1 = (String) session.getAttribute("qusername");
			if (q1 != null) {
				session.setAttribute("qusername", q1);
				// System.out.println("页面q1"+q1);
			} else {
				String qusername = req.getParameter("qusername");
				// System.out.println("页面qusername"+qusername);
				session.setAttribute("qusername", qusername);
			}
			session.setAttribute("showPesge","xianshi");
			session.setAttribute("pb", pb);
			// resp.sendRedirect("showBook.jsp");

			req.getRequestDispatcher("userForeground.jsp").forward(req, resp);
			return;

		}

	}

	private String getUrl2(HttpServletRequest req) {
		// TODO Auto-generated method stub
		String url = this.getUrl(req);
		int index = url.lastIndexOf("&pageNew=");
		if (index == -1) {
			return url;
		}
		url = url.substring(0, index);
		return url;
	}

	private String getUrl(HttpServletRequest req) {
		// TODO Auto-generated method stub
		String path = req.getContextPath();
		String servlet = req.getServletPath();
		String param = req.getQueryString();

		return path + servlet + "?" + param;
	}

	public int getPageNew(HttpServletRequest req) {

		String pageNew = req.getParameter("pageNew");

		if (pageNew == null || pageNew.trim().isEmpty()) {
			return 1;
		}
		return Integer.parseInt(pageNew);
	}

}
