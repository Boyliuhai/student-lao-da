package com.DAL;
import java.sql.*;
import com.Dao.*;

public class TeacherService {
	DBUtil db = new DBUtil();
	/**
	 * 插入教师信息
	 * @param teacher
	 * @return boolean
	 */
	public boolean AddTeacher(Teacher teacher) {
		String sql = "insert into Teacher(TeacherId,AcademyId,TeacherName,TeacherSex) values(?,?,?,?)";
		try {
			db.update(sql, teacher.getId(), teacher.getAcademyId(), 
					teacher.getName(), teacher.getSex());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * 修改教师信息
	 * @param teacher
	 * @return boolean
	 */
	public boolean ModefyTeacher(Teacher teacher) {
		String sql = "update Teacher set AcademyId=?, TeacherName=?, TeacherSex=? where TeacherId=?";
		try{
			db.update(sql, teacher.getAcademyId(), 
					teacher.getName(), teacher.getSex(), teacher.getId());
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return true;
	}
	
	
	/**
	 * 根据教师Id删除教师信息
	 * @param teacherId
	 * @return boolean
	 */
	public boolean DeleteTeacher(int teacherId) {
		String sql = "Delete from Teacher where TeacherId=？";
		try{
			db.update(sql, teacherId);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return true;
	}
	
	/**
	 * 根据教师编号查询教师信息
	 * @param id
	 * @return Teacher
	 */
	public Teacher QueryTeacher(int id) {
		String sql = "Select * from Teacher where TeacherId=?";
		Teacher teacher = new Teacher();
		teacher.setId(id);
		try{
			ResultSet rs = db.excuteQuery(sql, id);
			if(rs.next()){
				teacher.setAcademyId(rs.getInt(2));
				teacher.setName(rs.getString(3));
				teacher.setSex(rs.getString(4));
			}
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			db.close();
		}
		return teacher;
	}
}
