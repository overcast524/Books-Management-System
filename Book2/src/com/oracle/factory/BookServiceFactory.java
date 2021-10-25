package com.oracle.factory;

import com.oracle.service.BookService;
import com.oracle.service.impl.BookServiceImpl;

public class BookServiceFactory {

	public static BookService getBookServiceImpl() {
		// TODO Auto-generated method stub
		return new BookServiceImpl();
	}

}
