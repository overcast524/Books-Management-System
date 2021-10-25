package com.oracle.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.oracle.dao.FenleiDao;
import com.oracle.domain.Book;
import com.oracle.domain.Fenlei;
import com.oracle.domain.PageBean;
import com.oracle.util.DBUtilsPlus;

public class FenleiDaoImpl implements FenleiDao {

	@Override
	public Fenlei dancha(int id) {
		// TODO Auto-generated method stub
		 Fenlei fl=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=DBUtilsPlus.getConnection();
			ps=conn.prepareStatement("select * from fenlei where id=? ");
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()){
				int id1=rs.getInt(1);
				String name1=rs.getString(2);
				 fl=new Fenlei(id1, name1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return fl;
	}

	@Override
	public int addfl(String name) {
		// TODO Auto-generated method stub
		int s=0;
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=DBUtilsPlus.getConnection();
			ps=conn.prepareStatement("insert into fenlei (name) value(?) ");
			ps.setString(1, name);
			s=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public List<Fenlei> findfl() {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Fenlei> list = new ArrayList<Fenlei>();
		try {
			conn = DBUtilsPlus.getConnection();
			String sql = "select * from fenlei order by id desc";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
			
				int id = rs.getInt(1);	 
				String name = rs.getString(2);
				Fenlei s = new Fenlei(id,name);
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

	@Override
	public int[] delete(String[] ids) {
		// TODO Auto-generated method stub
		int[] n=null;
		Connection conn = null;
		PreparedStatement ps = null;
	 
		try {
			conn = DBUtilsPlus.getConnection();
			conn.setAutoCommit(false);
			for(int i=0;i<ids.length;i++){
		  // conn = DBUtilsPlus.getConnection();
			ps = conn.prepareStatement("delete from fenlei where id=?");
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
	public int update(Fenlei fl1) {//�޸ķ�����
		// TODO Auto-generated method stub
		int n=0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtilsPlus.getConnection();
			ps = conn.prepareStatement("update fenlei set name=? where id=? ");
			ps.setString(1,fl1.getName());
			ps.setInt(2, fl1.getId());
			n=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public int yanzheng(String name) {
		// TODO Auto-generated method stub
		int t=0;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=DBUtilsPlus.getConnection();
			ps=conn.prepareStatement("select * from fenlei where name=? ");
			ps.setString(1, name);
			rs=ps.executeQuery();
			if(rs.next()){
				 t=1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return t;
	}

	@Override
	public int update2(String oldname, String name) {
		// TODO Auto-generated method stub
		int n=0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtilsPlus.getConnection();
			ps = conn.prepareStatement("update fenlei set name=? where name=? ");
			ps.setString(1,name);
			ps.setString(2,oldname);
			n=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public PageBean<Fenlei> showPesgefl(int pageNew, int pageSize) { 
		PageBean<Fenlei> pb=new PageBean<Fenlei>();
	   pb.setPageNew(pageNew);
	   pb.setPageSize(pageSize);
	   pb.setCounts(this.Count());
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Fenlei> list = new ArrayList<Fenlei>();
		try {
			conn = DBUtilsPlus.getConnection();
			String sql = "select * from fenlei order by id desc limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,(pageNew-1)*5);
			ps.setInt(2,pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				
				int id = rs.getInt(1);	 
				String name = rs.getString(2);
				Fenlei s = new Fenlei(id, name);
				list.add(s);
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
		int i=0;
		try {
			conn = DBUtilsPlus.getConnection();
			String sql = "select count(*) from fenlei ";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
			
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
	public List<Fenlei> showIdsfl(String[] ids) {
		// TODO Auto-generated method stub
	 
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Fenlei> list = new ArrayList<Fenlei>();
		try {
			conn = DBUtilsPlus.getConnection();
			String sql =ByIds(ids);
			ps = conn.prepareStatement(sql);
			for(int i=0;i<ids.length;i++){
				ps.setInt(i+1, Integer.parseInt(ids[i]));
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String  name = rs.getString(2);
		 
				// double money=Integer.parseInt(money1);
				Fenlei b = new Fenlei(id,name);

				list.add(b);
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
		StringBuilder str=new StringBuilder("select * from fenlei where id in (") ;
		for(int i=0;i<ids.length;i++){
			
			str.append("?");
			if(i<ids.length-1){
				str.append(",");
			}
		}
		str.append(")");
		
		return str.toString();
	}
 

	
	
	
	
	
}
