package com.Dao;

public class Score {
	private int studentId;
	private int score;
	private int courseId;
	public int getStudentId(){
		return this.studentId;
	}
	public void setStudentId(int studentId){
		this.studentId = studentId;
	}
	public int getScore(){
		return this.score;
	}
	public void setScore(int score){
		this.score = score;
	}
	public int getCourseId(){
		return this.courseId;
	}
	public void setCourseId(int courseId){
		this.courseId = courseId;
	}
}
