package com.oracle.service;

import com.oracle.domain.Admin;

public interface AdminService {

	Admin login(Admin a);

	int addAdmin(Admin a);

	Admin showAdmin(String un);

	int yanzheng(String username);

	int updatemm(String newpassword, String un);

}
