package com.oracle.dao;

import java.util.List;

import com.oracle.domain.Book;
import com.oracle.domain.PageBean;
import com.oracle.domain.User;

public interface UserDao {

	List<User> showUser();
 
	User showOne(String id);

	int dancha(String username);

	int add(User stu);
	int Count();
	PageBean<User> showPesgefl(int pageNew, int pageSize);

	int[] delect(String[] ids);

	int updateUser(User stu);

	int updateTouxiang(User stu);

	List<User> showUserByIds(String[] ids);

	User login(User a);

	PageBean<Book> showPesgeUsBook(int pageNew, int pageSize, String name);

 

}
