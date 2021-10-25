package com.oracle.service.impl;

import com.oracle.dao.AdminDao;
import com.oracle.dao.impl.AdminDaoImpl;
import com.oracle.domain.Admin;
import com.oracle.service.AdminService;

public class AdminServiceImpl implements AdminService {
     AdminDao ad=new AdminDaoImpl();


	@Override
	public Admin login(Admin a) {
		// TODO Auto-generated method stub
		return ad.login(a);
	}

	@Override
	public int addAdmin(Admin a) {
		// TODO Auto-generated method stub
		return ad.addAdmin(a);
	}

	@Override
	public Admin showAdmin(String un) {
		// TODO Auto-generated method stub
		return ad.showAdmin(un);
	}

	@Override
	public int yanzheng(String username) {
		// TODO Auto-generated method stub
		return ad.yanzheng(username);
	}

	@Override
	public int updatemm(String newpassword, String un) {
		// TODO Auto-generated method stub
		return ad.updatemm(newpassword,un);
	}
 
}
