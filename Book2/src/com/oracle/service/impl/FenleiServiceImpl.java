package com.oracle.service.impl;

import java.util.List;


import com.oracle.dao.FenleiDao;
import com.oracle.dao.impl.FenleiDaoImpl;
import com.oracle.domain.Fenlei;
import com.oracle.domain.PageBean;
import com.oracle.service.FenleiService;
 

public class FenleiServiceImpl implements FenleiService {
  FenleiDao fl=new FenleiDaoImpl();
	@Override
	public Fenlei dancha(int id) {
		// TODO Auto-generated method stub
		return fl.dancha(id);
	}
	@Override
	public int addfl(String name) {
		// TODO Auto-generated method stub
		return fl.addfl(name);
	}
	@Override
	public List<Fenlei> findfl() {
		// TODO Auto-generated method stub
		return fl.findfl();
	}
	@Override
	public int[] delete(String[] ids) {
		// TODO Auto-generated method stub
		return  fl.delete(ids);
	}
	
	@Override
	public int update(Fenlei fl1) {
		// TODO Auto-generated method stub
		return fl.update(fl1);
	}
	@Override
	public int yanzheng(String name) {
		// TODO Auto-generated method stub
		return fl.yanzheng(name);
	}
	@Override
	public int update2(String oldname, String name) {
		// TODO Auto-generated method stub
		return fl.update2(oldname,name);
	}
	@Override
	public PageBean<Fenlei> showPesgefl(int pageNew, int pageSize) {
		// TODO Auto-generated method stub
		return fl.showPesgefl(pageNew,pageSize);
	}
	@Override
	public List<Fenlei> showIdsfl(String[] ids) {
		// TODO Auto-generated method stub
		return fl.showIdsfl(ids) ;
	}
}
