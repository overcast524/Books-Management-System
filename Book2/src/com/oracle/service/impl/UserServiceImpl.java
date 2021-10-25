package com.oracle.service.impl;

import java.util.List;


import com.oracle.dao.UserDao;
import com.oracle.dao.impl.UserDaoImpl;
import com.oracle.domain.Book;
import com.oracle.domain.PageBean;
import com.oracle.domain.User;
import com.oracle.service.UserService;

public class UserServiceImpl implements UserService {

	UserDao us=new UserDaoImpl();
	
	@Override
	public List<User> showUser() {
		// TODO Auto-generated method stub
		return us.showUser();
	}

 

	@Override
	public User showOne(String id) {
		// TODO Auto-generated method stub
		return us.showOne(id);
	}

	@Override
	public int dancha(String username) {
		// TODO Auto-generated method stub
		return us.dancha(username);
	}

	@Override
	public int add(User stu) {
		// TODO Auto-generated method stub
		return us.add(stu);
	}

	@Override
	public PageBean<User> showPesgefl(int pageNew, int pageSize) {
		// TODO Auto-generated method stub
		return us.showPesgefl(pageNew,pageSize);
	}



	@Override
	public int[] delete(String[] ids) {
		// TODO Auto-generated method stub
		return us.delect(ids);
	}



	@Override
	public int updateUser(User stu) {
		// TODO Auto-generated method stub
		return  us.updateUser(stu);
	}



	@Override
	public int updateTouxiang(User stu) {
		// TODO Auto-generated method stub
		return us.updateTouxiang(stu);
	}



	@Override
	public List<User> showUserByIds(String[] ids) {
		// TODO Auto-generated method stub
		return us.showUserByIds(ids);
	}



	@Override
	public User login(User a) {
		// TODO Auto-generated method stub
		return us.login(a);
	}



	@Override
	public PageBean<Book> showPesgeUsBook(int pageNew, int pageSize,String name) {
		// TODO Auto-generated method stub
		return us.showPesgeUsBook(pageNew,pageSize,name);
	}


 


 
}
