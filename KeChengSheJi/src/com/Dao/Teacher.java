package com.Dao;

public class Teacher {
	private int id;
	private String name;
	private int academyId;
	private String sex;
	public String getSex(){
		return this.sex;
	}
	public void setSex(String sex){
		this.sex = sex;
	}
	public int getAcademyId(){
		return academyId;
	}
	public void setAcademyId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
}
