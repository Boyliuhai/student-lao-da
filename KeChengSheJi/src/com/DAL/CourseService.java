package com.DAL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.Dao.*;

public class CourseService {
	DBUtil db = new DBUtil();
	/**
	 * 修改课程信息
	 * @param course
	 * @return boolean
	 */
	public boolean ModefyCourse(CourseInfo course) {
		String sql = "Update Course set CourseName=? where CourseId=?";
		try{
			db.update(sql, course.getCourseName(), course.getCourseId());
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return true;
	}
	
	/**
	 * 删除单条课程信息
	 * @param courseId
	 * @return boolean
	 */
	public boolean DeleteCourse(int courseId) {
		String sql = "Delete From Course where CourseId=?";
		try{
			db.update(sql, courseId);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return true;
	}
	
	/**
	 * 查询课程信息
	 * @param courseid
	 * @return Course
	 */
	public CourseInfo QueryCourse(int courseId) {
		String sql = "Select * from Course where CourseId=?";
		CourseInfo course = new CourseInfo();
		try{
			ResultSet rs = db.excuteQuery(sql, courseId);
			if(rs.next()){
				course.setCourseId(rs.getInt(1));
				course.setCourseName(rs.getString(2));
				course.setCoursePower(rs.getInt(3));
				course.setCourseTime(rs.getString(4));
				course.setLimit(rs.getInt(5));
				course.setExists(rs.getInt(6));
			}
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			db.close();
		}
		return course;
	}

	/**
	 * 返回所有的课程名称
	 * @return
	 */
	public List<String> getAllClassNames(){
		String sql = "select CourseName from Course";
		List<String> list = new ArrayList<String>();
		try{
			ResultSet rs = db.excuteQuery(sql);
			while(rs.next()){
				list.add(rs.getString(1));
			}
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			db.close();
		}
		return list;
	}
	
	/**
	 * 添加课程信息
	 * @param studentId
	 * @param courseId
	 * @param score
	 * @return boolean
	 */
	public boolean AddScore(CourseInfo course) {
		String sql = "insert into Course(CourseId,CourseName,CoursePower,CourseTime,Limit,Exist) "
				+ "values(?,?,?,?,?,?);";
		try{
			db.update(sql, course.getCourseId(), course.getCourseName(), course.getCoursePower(),
					course.getCourseTime(),course.getExists(),course.getLimit());
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return true;
	}
	
	/**
	 * 封装ResultSet到List
	 * @param rs
	 * @return
	 */
	public List<CourseInfo> getCourseList(ResultSet rs){
		List<CourseInfo> list = new ArrayList<CourseInfo>();
		ResultSetMetaData rsmd;
		CourseInfo c = null;
		try {
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			//封装对象
			while(rs.next()){
				c = new CourseInfo();
				for(int i=0;i<cols;i++){
					c.setCourseId(rs.getInt(1));
					c.setCourseName(rs.getString(2));
					c.setCoursePower(rs.getInt(3));
					c.setCourseTime(rs.getString(4));
					c.setExists(rs.getInt(5));
					c.setLimit(rs.getInt(6));
				}
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		return list;
	}
}
