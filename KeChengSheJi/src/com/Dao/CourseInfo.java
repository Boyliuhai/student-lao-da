package com.Dao;

public class CourseInfo {
	private int courseId;
	private String courseName;
	private int coursePower;
	private String courseTime;
	private int limit;
	private int exists;
	
	public int getCourseId(){
		return this.courseId;
	}
	public void setCourseId(int courseId){
		this.courseId = courseId;
	}
	
	public String getCourseName(){
		return this.courseName;
	}
	public void setCourseName(String name){
		this.courseName = name;
	}
	
	public String getCourseTime() {
		return courseTime;
	}
	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}
	
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public int getExists() {
		return exists;
	}
	public void setExists(int exists) {
		this.exists = exists;
	}
	
	public int getCoursePower() {
		return coursePower;
	}
	public void setCoursePower(int coursePower) {
		this.coursePower = coursePower;
	}
}
