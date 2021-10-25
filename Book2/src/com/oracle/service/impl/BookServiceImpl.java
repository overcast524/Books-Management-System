package com.oracle.service.impl;

import java.util.List;


import com.oracle.dao.BookDao;
import com.oracle.dao.impl.BookDaoImpl;
import com.oracle.domain.Book;
import com.oracle.domain.Fenlei;
import com.oracle.domain.PageBean;
import com.oracle.service.BookService;

public class BookServiceImpl implements BookService {
 BookDao bk=new BookDaoImpl();

@Override
public List<Fenlei> findfl() {

	// TODO Auto-generated method stub
	return bk.findfl();
}

@Override
public int yanzheng(String name, String flname) {
	// TODO Auto-generated method stub
	return bk.yanzheng(name,flname);
}

@Override
public int addbook(Book book) {
	// TODO Auto-generated method stub
	return bk.addbook(book);
}

@Override
public PageBean<Book> showPesgefl(int pageNew, int pageSize) {
	// TODO Auto-generated method stub
	return bk.showPesgefl(pageNew,pageSize);
}

@Override
public int[] delete(String[] ids) {
	// TODO Auto-generated method stub
	return bk.delete(ids);
}

@Override
public Book dancha(int id1) {
	// TODO Auto-generated method stub
	return bk.dancha(id1);
}

@Override
public int update2(Book book) {
	// TODO Auto-generated method stub
	return bk.update2(book);
}

@Override
public List<Book> showBook() {
	// TODO Auto-generated method stub
	return bk.showBook();
}

@Override
public List<Book> showIdsBook(String[] ids) {
	// TODO Auto-generated method stub
	return bk.showIdsBook(ids);
}

@Override
public int jieshu(Book book) {
	// TODO Auto-generated method stub
	return bk.jieshu(book);
}

@Override
public int huanshu(Book book) {
	// TODO Auto-generated method stub
	return bk.huanshu(book);
}

@Override
public PageBean<Book> showPesgefind(Book where, int pageNew, int  pageSize){
	// TODO Auto-generated method stub
	return bk.showPesgefind(where,pageNew,pageSize);
}

 

 
 
	
}
