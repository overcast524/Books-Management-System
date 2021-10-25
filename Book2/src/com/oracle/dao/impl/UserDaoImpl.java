package com.oracle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.oracle.dao.UserDao;
import com.oracle.domain.Admin;
import com.oracle.domain.Book;
import com.oracle.domain.PageBean;
import com.oracle.domain.User;
import com.oracle.util.DBUtilsPlus;

public class UserDaoImpl implements UserDao {


	@Override
	public List<User> showUser() {//1.显示用户

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<User>();
		try {
			conn = DBUtilsPlus.getConnection();
			String sql = "select * from user";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				User s = new User();
				int id = rs.getInt(1);
				s.setId(id);
				//System.out.println(id);
				String name = rs.getString(2);
				s.setName(name);
				String username= rs.getString(3);
				s.setUsername(username);
				String password = rs.getString(4);
				s.setPassword(password);
				String phone = rs.getString(5);
				s.setPhone(phone);
				String regtime = rs.getString(6);
				s.setRegtime(regtime);
				String touxiang = rs.getString(7);
				s.setTouxiang(touxiang);
			 
				list.add(s);
			}
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtilsPlus.close(conn, ps, rs);
		}

		return list;
	}

	
//	@Override
//	public int update(User stu) {//�޸�
//		 int s=0;
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null; 
//		try {
//			conn = DBUtilsPlus.getConnection();
//			ps = conn.prepareStatement(
//					"update user set name=?,username=?,passwor=?,phone=?,regtime=? where id=? ");
//			ps.setString(1, stu.getXuehao());
//			ps.setString(2, stu.getName());
//			ps.setInt(3,stu.getAge());
//			ps.setString(4, stu.getPhone());
//			ps.setString(5, stu.getRxtime());
//			ps.setString(6,stu.getSchool());
//			ps.setString(7, stu.getZhuanye());
//			ps.setString(8, stu.getBanji());
//			ps.setInt(9, stu.getId());
//			s = ps.executeUpdate();
//		
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			DBUtilsPlus.close(conn, ps);
//		}
//		return s;
//	}

	@Override
	public int add(User stu) {//添加用户
		// TODO Auto-generated method stub
	 int s=0;
	 
	 Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtilsPlus.getConnection();
			ps = conn.prepareStatement("insert into user (name,username,password,phone,regtime,touxiang) values(?,?,?,?,?,?)");
			ps.setString(1, stu.getName());
			ps.setString(2, stu.getUsername());
			ps.setString(3,stu.getPassword());
			ps.setString(4, stu.getPhone());
			ps.setString(5, stu.getRegtime());
			ps.setString(6, stu.getTouxiang());
			 
			s = ps.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtilsPlus.close(conn, ps);
		}
		 return s;
	}

	@Override
	public User showOne(String id) {//单查用户
		// TODO Auto-generated method stub
		int id1 = Integer.parseInt(id);
		User s = new User();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		 
		try {
			conn = DBUtilsPlus.getConnection();
			ps = conn.prepareStatement("select * from user where id=?");
			ps.setInt(1, id1);		
			rs = ps.executeQuery();
			if(rs.next()) {				
				int id2 = rs.getInt(1);
				//s.setId(id2);
				String name = rs.getString(2);
				//s.setName(name);
				String username= rs.getString(3);
				//s.setUsername(username);
				String password = rs.getString(4);
				//s.setPassword(password);
				String phone = rs.getString(5);
				//s.setPhone(phone);
				String regtime = rs.getString(6);
				//s.setRegtime(regtime);
				String touxiang = rs.getString(7);
				s=new User(id2, name, username, password, phone, regtime, touxiang);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtilsPlus.close(conn, ps, rs);
		}

		return s;
	}

	@Override
	public int dancha(String username) {
		// TODO Auto-generated method stub
		 int i=0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

			try {
				conn = DBUtilsPlus.getConnection();
				ps = conn.prepareStatement("select * from user where username=?");
				ps.setString(1, username);		
				rs = ps.executeQuery();
			 if(rs.next()){
				 i=1;
			 }
				 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBUtilsPlus.close(conn, ps, rs);
			}

			return i;
			
	}

	@Override
	public int[] delect(String[] ids) {
		// TODO Auto-generated method stub	 
		int[] n=null;
		Connection conn = null;
		PreparedStatement ps = null;
	 
		try {
			 conn = DBUtilsPlus.getConnection();
			 conn.setAutoCommit(false);
			for(int i=0;i<ids.length;i++){			
			
			 ps = conn.prepareStatement("delete from user where id=?");
			 ps.setInt(1,Integer.parseInt(ids[i]));
			 
			 ps.addBatch();
			 n=ps.executeBatch();
			}
			 
		  conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			DBUtilsPlus.close(conn, ps);
		}
	 return n;
	}


	@Override
	public PageBean<User> showPesgefl(int pageNew, int pageSize) {
		PageBean<User> pb = new PageBean<User>();
		pb.setPageNew(pageNew);
		pb.setPageSize(pageSize);
		pb.setCounts(this.Count());
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<User>();
		try {
			conn = DBUtilsPlus.getConnection();
			String sql = "select * from user order by id desc limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (pageNew - 1) * 5);
			ps.setInt(2, pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {

				int id = rs.getInt(1);
				String name = rs.getString(2);
				String username= rs.getString(3);
				String password = rs.getString(4);
				String phone= rs.getString(5);
				String  regtime = rs.getString(6);
				String  touxiang = rs.getString(7);
	 
				User u= new User(id,name,username, password, phone, regtime,touxiang);

				list.add(u);
			}
			pb.setBeanList(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtilsPlus.close(conn, ps, rs);
		}

		return pb;
	}
	
	@Override
	public int Count() {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		try {
			conn = DBUtilsPlus.getConnection();
			String sql = "select count(*) from user ";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {

				i = rs.getInt(1);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtilsPlus.close(conn, ps, rs);
		}

		return i;
	}


	@Override
	public int updateUser(User stu) {
		// TODO Auto-generated method stub
		int s = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtilsPlus.getConnection();
			ps = conn.prepareStatement("update user set name=?,password=?,phone=?,regtime=? where id=? ");
			ps.setString(1,stu.getName());		 
			ps.setString(2, stu.getPassword());
			ps.setString(3,stu.getPhone());
			ps.setString(4, stu.getRegtime());
			ps.setInt(5, stu.getId());
			 
			s = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}


	@Override
	public int updateTouxiang(User stu) {//单独修改头像
		// TODO Auto-generated method stub
		int s = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtilsPlus.getConnection();
			ps = conn.prepareStatement(
					"update user set touxiang=? where id=? ");
			ps.setString(1,stu.getTouxiang());
	 
			ps.setInt(2, stu.getId());
			s = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}


	@Override
	public List<User> showUserByIds(String[] ids) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<User>();
		try {
			conn = DBUtilsPlus.getConnection();
			String sql =ByIds(ids);
			ps = conn.prepareStatement(sql);
			for(int i=0;i<ids.length;i++){
				ps.setInt(i+1, Integer.parseInt(ids[i]));
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				User s = new User();
				int id = rs.getInt(1);
				s.setId(id);
				 
				String name = rs.getString(2);
				s.setName(name);
				String username= rs.getString(3);
				s.setUsername(username);
				String password = rs.getString(4);
				s.setPassword(password);
				String phone = rs.getString(5);
				s.setPhone(phone);
				String regtime = rs.getString(6);
				s.setRegtime(regtime);
				String touxiang = rs.getString(7);
				s.setTouxiang(touxiang);
			 
				list.add(s);
			}
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtilsPlus.close(conn, ps, rs);
		}

		return list;
	}


	private String ByIds(String[] ids) {
		// TODO Auto-generated method stub
		StringBuilder str=new StringBuilder("select * from user where id in (") ;
		for(int i=0;i<ids.length;i++){
			
			str.append("?");
			if(i<ids.length-1){
				str.append(",");
			}
		}
		str.append(")");
		
		return str.toString();
	}


	@Override
	public User login(User a) {
		// TODO Auto-generated method stub
		 //登录
		User user=null;
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				conn = DBUtilsPlus.getConnection();
				ps = conn.prepareStatement("select username,password from user where username=?");
				ps.setString(1, a.getUsername());
	           rs=ps.executeQuery();
	           if(rs.next()){
					String userName=rs.getString(1);
					String password=rs.getString(2);
	              user=new User();
	              user.setUsername(userName);
	              user.setPassword(password);
	        	   }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBUtilsPlus.close(conn, ps);
			}
			return user;
		}


	@Override
	public PageBean<Book> showPesgeUsBook(int pageNew, int pageSize,String name) {
		// TODO Auto-generated method stub
		PageBean<Book> pb = new PageBean<Book>();
		pb.setPageNew(pageNew);
		pb.setPageSize(pageSize);
		pb.setCounts(this.Count2(name));
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Book> list = new ArrayList<Book>();
		try {
		 
			conn = DBUtilsPlus.getConnection();
			String sql = "select * from book  where name like '%"+name+"%' order by id desc limit  "+((pageNew - 1) * 5) +","+pageSize;
			ps = conn.prepareStatement(sql);
		 
			rs = ps.executeQuery();
			while (rs.next()) {

				int id = rs.getInt(1);
				String flname = rs.getString(2);
				String name1 = rs.getString(3);
				String money = rs.getString(4);
				String press = rs.getString(5);
				String state = rs.getString(6);
				String reader = rs.getString(7);
				// double money=Integer.parseInt(money1);
				Book b = new Book(id, flname, name1, money, press, state, reader);

				list.add(b);
			}
			pb.setBeanList(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtilsPlus.close(conn, ps, rs);
		}

		return pb;
	}


	private int Count2(String name) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		try {
			conn = DBUtilsPlus.getConnection();
			 
			String sql = "select count(*) from book where  name like '%"+name+"%'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {

				i = rs.getInt(1);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtilsPlus.close(conn, ps, rs);
		}

		return i;
	}
	 
	
	}


	 
 
