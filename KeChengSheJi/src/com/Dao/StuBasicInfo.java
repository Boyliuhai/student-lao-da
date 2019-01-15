package com.Dao;

public class StuBasicInfo {
	private int studentId;
	private int academyId;
	private String name;
	private String sex;
	private String birthday;
	private String instruction;
	private String idNo;
	private String imageURL;
	
	public int getStudentId(){
		return this.studentId;
	}
	public void setStudentId(int studentId){
		this.studentId = studentId;
	}
	
	public int getAcademyId(){
		return this.academyId;
	}
	public void setAcademyId(int academyId){
		this.academyId = academyId;
	}
	
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public String getSex(){
		return this.sex;
	}
	public void setSex(String sex){
		this.sex = sex;
	}
	
	public String getBirthday(){
		return this.birthday;
	}
	public void setBirthday(String birthday){
		this.birthday = birthday;
	}
	
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
}
