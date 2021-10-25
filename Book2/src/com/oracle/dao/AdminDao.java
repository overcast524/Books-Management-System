package com.oracle.dao;

import com.oracle.domain.Admin;

public interface AdminDao {

	Admin login(Admin a);

	int addAdmin(Admin a);

	Admin showAdmin(String un);

	int yanzheng(String username);

	int updatemm(String newpassword, String un);
 

}
