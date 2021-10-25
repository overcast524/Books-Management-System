package com.oracle.domain;

import java.io.Serializable;

public class Book implements Serializable{

	
	private int id;
	private String flname;
	private String  name;
	private String money;
	private String press;
	private String state;
	private String  reader;
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(int id, String flname, String name, String money, String press, String state, String reader) {
		super();
		this.id = id;
		this.flname = flname;
		this.name = name;
		this.money = money;
		this.press = press;
		this.state = state;
		this.reader = reader;
	}
	public Book(String flname, String name, String money, String press, String state, String reader) {
		super();
		this.flname = flname;
		this.name = name;
		this.money = money;
		this.press = press;
		this.state = state;
		this.reader = reader;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFlname() {
		return flname;
	}
	public void setFlname(String flname) {
		this.flname = flname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getReader() {
		return reader;
	}
	public void setReader(String reader) {
		this.reader = reader;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", flname=" + flname + ", name=" + name + ", money=" + money + ", press=" + press
				+ ", state=" + state + ", reader=" + reader + "]";
	}
	 
 
	
}
