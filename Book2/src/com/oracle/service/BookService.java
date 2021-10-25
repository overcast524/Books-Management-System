package com.oracle.service;

import java.util.List;

import com.oracle.domain.Book;
import com.oracle.domain.Fenlei;
import com.oracle.domain.PageBean;

public interface BookService {

	 

	 List<Fenlei> findfl();

	int yanzheng(String name, String flname);

	int addbook(Book book);

	PageBean<Book> showPesgefl(int pageNew, int pageSize);

	int[] delete(String[] ids);

 

	Book dancha(int id1);

	int update2(Book book);

	List<Book> showBook();

	List<Book> showIdsBook(String[] ids);

	int jieshu(Book book);

	int huanshu(Book book);

	PageBean<Book> showPesgefind(Book where, int pageNew, int pageSize);

 
 

}
