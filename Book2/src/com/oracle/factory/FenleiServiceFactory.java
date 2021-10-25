package com.oracle.factory;

 
import com.oracle.service.FenleiService;
import com.oracle.service.impl.FenleiServiceImpl;

public class FenleiServiceFactory {

	public static FenleiService getFenleiServiceImpl() {
		// TODO Auto-generated method stub
		return new FenleiServiceImpl();
	}

}
