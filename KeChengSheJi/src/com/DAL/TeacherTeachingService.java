package com.DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherTeachingService {
	DBUtil db = new DBUtil();
	/**
	 * 获取教师所教授的所有课程
	 * @param teacherName
	 * @return List<CourseInfo>
	 */
	public List<String> getAllTeachingCourseName(int teacherId){
		List<String> courseList = new ArrayList<String>();
		String sql = "select CourseName from Course where CourseId in "
				+ "(select distinct CourseId from TeacherTeaching where TeacherId=?);";
		ResultSet rs = db.excuteQuery(sql, teacherId);
		try {
			while(rs.next()){
				courseList.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		return courseList;
	}
}
