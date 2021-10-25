package com.oracle.domain;

import java.io.Serializable;
import java.util.List;

public class PageBean<T> implements Serializable{
	
    private int pageNew;//��ǰҳ
    private int counts;
  //  private int pages;//������ҳ
    private int pageSize;//dup
    private List<T> beanList;
    private String url;

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPageNew() {
		return pageNew;
	}
	public void setPageNew(int pageNew) {
		this.pageNew = pageNew;
	}
	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	
	public int getPages() {
		int pages=this.counts/this.pageSize;
		return (this.counts%this.pageSize==0)?pages:pages+1;
	}

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
	 
    
}
