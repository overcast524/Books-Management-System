package com.oracle.domain;

import java.io.Serializable;

public class User implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private  int id;
	
	 private  String name;
	 private  String username;
	 private  String password;
	 private  String phone;
	 private String regtime;
	 private String touxiang;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(int id, String name,String password, String phone, String regtime) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.regtime = regtime;
	}
	
	public User(int id, String name, String password, String phone, String regtime, String touxiang) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.regtime = regtime;
		this.touxiang = touxiang;
	}

	public User(int id, String name, String username, String password, String phone, String regtime, String touxiang) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.regtime = regtime;
		this.touxiang = touxiang;
	}
	public User(String name, String username, String password, String phone, String regtime, String touxiang) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.regtime = regtime;
		this.touxiang = touxiang;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	public String getTouxiang() {
		return touxiang;
	}
	public void setTouxiang(String touxiang) {
		this.touxiang = touxiang;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", phone="
				+ phone + ", regtime=" + regtime + ", touxiang=" + touxiang + "]";
	}
	 
}
