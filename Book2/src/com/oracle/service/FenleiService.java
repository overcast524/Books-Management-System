package com.oracle.service;
 
import java.util.List;

import com.oracle.domain.Fenlei;
import com.oracle.domain.PageBean;

public interface FenleiService {
 

	int addfl(String name);

	List<Fenlei> findfl();

	int[] delete(String[] ids);

	int update(Fenlei fl1);

	int yanzheng(String name);

	Fenlei dancha(int id);

	int update2(String oldname, String name);

	PageBean<Fenlei> showPesgefl(int pageNew, int pageSize);

	List<Fenlei> showIdsfl(String[] ids);

	 

}
