package com.oracle.factory;

import com.oracle.service.AdminService;
import com.oracle.service.impl.AdminServiceImpl;

public class AdminServiceFactory {
		public static AdminService getAdminServiceImpl(){
			return new AdminServiceImpl();
		}
}
