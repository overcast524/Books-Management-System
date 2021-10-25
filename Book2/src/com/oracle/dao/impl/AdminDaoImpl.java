package com.oracle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.oracle.dao.AdminDao;
import com.oracle.domain.Admin;
import com.oracle.domain.User;
import com.oracle.util.DBUtilsPlus;

public class AdminDaoImpl implements AdminDao {

	@Override
	public Admin login(Admin a) {//登录
	Admin admin=null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtilsPlus.getConnection();
			ps = conn.prepareStatement("select username,password from admin where username=?");
			ps.setString(1, a.getUsername());
           rs=ps.executeQuery();
           if(rs.next()){
				String userName=rs.getString(1);
				String password=rs.getString(2);
              admin=new Admin();
              admin.setUsername(userName);
              admin.setPassword(password);
        	   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtilsPlus.close(conn, ps);
		}
		return admin;
	}

	@Override
	public int addAdmin(Admin a) { //添加用户
		// TODO Auto-generated method stub
	    int s=0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtilsPlus.getConnection();
			ps = conn.prepareStatement("insert into admin (name,username,password,phone,touxiang) values(?,?,?,?,?)");

			ps.setString(1, a.getName());

			ps.setString(2, a.getUsername());
			ps.setString(3, a.getPassword());
			ps.setString(4, a.getPhone());
			ps.setString(5, a.getTouxiang());
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
	public Admin showAdmin(String un) {//显示管理员
		// TODO Auto-generated method stub
		 Admin s=new Admin();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		 
		try {
			conn = DBUtilsPlus.getConnection();
			ps = conn.prepareStatement("select * from admin where username=?");
			ps.setString(1, un);
			rs = ps.executeQuery();
			if(rs.next()) {				
				int id = rs.getInt(1);
		 
				String name = rs.getString(2);
				 
				String username= rs.getString(3);
				 
				String password = rs.getString(4);
			 
				String phone = rs.getString(5);
				String touxiang = rs.getString(6);
             s=new Admin(id, name, username, password, phone,touxiang);
				 
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
	public int yanzheng(String username) {//ע验证用户
		int s=0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtilsPlus.getConnection();
			ps = conn.prepareStatement("select username from admin where username=?");
			ps.setString(1, username);
			rs = ps.executeQuery();
			if(rs.next()) {				
			 s=1; 
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
	public int updatemm(String newpassword, String un) {//修改密码
		// TODO Auto-generated method stub
		int s=0;
		Connection conn = null;
		PreparedStatement ps = null;
		 
		try {
			conn = DBUtilsPlus.getConnection();
			ps = conn.prepareStatement("update admin set password=?  where username=? ");
			ps.setString(1, newpassword);
			ps.setString(2, un);
			s= ps.executeUpdate();
			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtilsPlus.close(conn, ps);
		}

		return s;
	}


}
