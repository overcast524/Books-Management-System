package com.oracle.factory;

import com.oracle.service.UserService;
import com.oracle.service.impl.UserServiceImpl;

public class UserServiceFactory {

public static UserService getUserServiceImpl(){
	   return new UserServiceImpl();
   }
}
