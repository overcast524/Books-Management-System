package com.oracle.dao;

import java.util.List;

import com.oracle.domain.Fenlei;
import com.oracle.domain.PageBean;

public interface FenleiDao {

	Fenlei dancha(int id);

	int addfl(String name);

	List<Fenlei> findfl();

	int[] delete(String[] ids);

	int update(Fenlei fl1);

	int yanzheng(String name);

	int update2(String oldname, String name);

	PageBean<Fenlei> showPesgefl(int pageNew, int pageSize);
     int Count();

	List<Fenlei> showIdsfl(String[] ids);
}
