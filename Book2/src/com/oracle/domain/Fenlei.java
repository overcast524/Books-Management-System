package com.oracle.domain;

import java.io.Serializable;

public class Fenlei implements Serializable{

	
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	public Fenlei() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Fenlei(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Fenlei(String name) {
		super();
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Fenlei [id=" + id + ", name=" + name + "]";
	}
 
	 
}
