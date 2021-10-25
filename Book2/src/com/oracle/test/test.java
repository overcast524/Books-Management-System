package com.oracle.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import com.oracle.domain.Fenlei;
import com.oracle.domain.PageBean;
import com.oracle.factory.FenleiServiceFactory;
import com.oracle.util.DBUtilsPlus;


 

 

public class test {
	@Test
	public void Test(){
		try {
			Connection conn=DBUtilsPlus.getConnection();
			if(conn!=null){
				System.out.println("OK");
			}else{
				System.out.println("NO");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void Test2(){	
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=DBUtilsPlus.getConnection();
			conn.setAutoCommit(false);
			for(int i=1;i<100;i++){		
			ps=conn.prepareStatement("insert into fenlei (name) value(?) ");
			ps.setString(1,"������"+i);
			 ps.addBatch();	
				ps.executeUpdate();
			}
		
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void Test3(){	
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=DBUtilsPlus.getConnection();
			conn.setAutoCommit(false);
			for(int i=1;i<100;i++){		
				ps = conn.prepareStatement("insert into book (flname,name,money,press,state,reader) value(?,?,?,?,?,?)");
				ps.setString(1,"������"+i);
				ps.setString(2,"ͼ��"+i);
				ps.setString(3,"55");
				ps.setString(4,i+"������");
				ps.setString(5,"δ���");
				ps.setString(6,"��");	
			   ps.addBatch();	
			   ps.executeUpdate();
			}
			
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void Test4(){	
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=DBUtilsPlus.getConnection();
			conn.setAutoCommit(false);
			for(int i=1;i<100;i++){		
				ps = conn.prepareStatement("insert into user (name,username,password,phone,regtime) values(?,?,?,?,?)");
				ps.setString(1,"ʵ��Ʒ"+1+"��");
				ps.setString(2, "a"+i);
				ps.setString(3,i+"fff");
				ps.setString(4, "344567891");
				ps.setString(5, "2030-1-1");					
			   ps.addBatch();	
			   ps.executeUpdate();
			}
			
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void Test5(){
		PageBean<Fenlei>  pb=FenleiServiceFactory.getFenleiServiceImpl().showPesgefl(1,5);
	//	XStream xs=new XStream();
		 
		
	}
	
	
	}
