package com.murlwe.pidor_database;


import java.util.Calendar;

public class Entry {
	public int id;
	public String name;
	public String dept;
	public java.util.Date stamp;
	
	public Entry(int _id, String _name, String _dept, java.util.Date _stamp) {
		// TODO Auto-generated constructor stub
		id = _id;
		name = _name;
		dept = _dept;
		stamp = _stamp;
	}
	
	public Entry(int _id, String _name, String _dept) {
		// TODO Auto-generated constructor stub
		id = _id;
		name = _name;
		dept = _dept;
		stamp = Calendar.getInstance().getTime();
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int _id) {
		id = _id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String _name) {
		name = _name;
	}
	
	public String getDept() {
		return dept;
	}
	
	public void setDept(String _dept) {
		dept = _dept;
	}
	
	public java.util.Date getStamp() {
		return stamp;
	}
	
	public void setStamp() {
		stamp = Calendar.getInstance().getTime();
	}
}
