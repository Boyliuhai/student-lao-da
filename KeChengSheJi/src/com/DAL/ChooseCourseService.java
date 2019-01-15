package com.DAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import com.Dao.CourseInfo;
import com.Dao.StuBasicInfo;

public class ChooseCourseService {
	DBUtil db = new DBUtil();
	/**
	 * 选课
	 * @return
	 */
	public boolean ChooseCourse(int stuId, int courseId){
		String sql = "insert into ChooseCourse(StuId, CourseId) values(?,?)";
		return db.update(sql, stuId, courseId);
	}
	
	/**
	 * 退课
	 * @return
	 */
	public boolean QuitCourse(int stuId, int courseId){
		String sql = "delete ChooseCourse where StuId=? and CourseId = ?";
		return db.update(sql, stuId, courseId);
	}
	
	/**
	 * 根据学号返回已选的课程信息
	 * @param stuId
	 * @return
	 */
	public List<CourseInfo> getSelectingCourseInfoByProc(int stuId){
		String sql = "exec proc_StuSelectedCourseInfo ?";
		return getListByProc(sql, stuId);
	}
	
	/**
	 * 根据学号获取未选择课程信息列表
	 * @param stuId
	 * @return
	 */
	public List<CourseInfo> getCourseInfoByProc(int stuId){
		String sql = "exec proc_StuSelectingCourseInfo ?";
		return getListByProc(sql, stuId);
	}

	/**
	 * 获取课程列表
	 * @param stuId
	 * @return 
	 */
	public List<CourseInfo> getListByProc(String sql, Object...objects){
		List<CourseInfo> ciList = new ArrayList<CourseInfo>();
		try{
			ResultSet rs = db.queryByProc(sql, objects);
			while(rs.next()){
				CourseInfo ci = new CourseInfo();
				for(int i=0;i<rs.getMetaData().getColumnCount();i++){
					ci.setCourseId(rs.getInt(1));
					ci.setCourseName(rs.getString(2));
					ci.setCoursePower(rs.getInt(3));
					ci.setCourseTime(rs.getString(4));
					ci.setExists(rs.getInt(5));
					ci.setLimit(rs.getInt(6));
				}
				ciList.add(ci);
			}
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			db.close();
		}
		return ciList;
	}

	/**
	 * 通过课程Id获取学生表格的信息
	 * @param courseId
	 * @return DefaultTableModel
	 */
	public List<CourseInfo> getStudentListModelByCourseId(int courseId) {
		String sql = "select * from Student where StuId in "
				+ "(select StuId from ChooseCourse where CourseId = ?)";
		return new CourseService().getCourseList(db.excuteQuery(sql, courseId));
	}
	
	/**
	 * 通过课程Id获取学生表格的信息
	 * @param courseId
	 * @return DefaultTableModel
	 */
	public DefaultTableModel getStudentListModelByCourseName(String courseName) {
		String sql = "select * from Student where StuId in (select StuId from ChooseCourse cc inner join"
				+ " Course c on cc.CourseId=c.CourseId where c.CourseName = ?)";
		return db.getModel(db.excuteQuery(sql, courseName));
	}

	/**
	 * 根据课程编号查找到所有选课学生的信息
	 * @param courseId
	 * @return 学生信息列表
	 */
	public List<StuBasicInfo> getAllSelectCourseStudent(int courseId){
		String sql = "select * from Student where StuId in "
				+ "(select StuId from ChooseCourse where CourseId = ?)";
		ResultSet rs = db.excuteQuery(sql, courseId);
		return new StudentService().getStuList(rs);
	}
}
