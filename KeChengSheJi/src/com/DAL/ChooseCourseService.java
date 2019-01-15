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
	 * ѡ��
	 * @return
	 */
	public boolean ChooseCourse(int stuId, int courseId){
		String sql = "insert into ChooseCourse(StuId, CourseId) values(?,?)";
		return db.update(sql, stuId, courseId);
	}
	
	/**
	 * �˿�
	 * @return
	 */
	public boolean QuitCourse(int stuId, int courseId){
		String sql = "delete ChooseCourse where StuId=? and CourseId = ?";
		return db.update(sql, stuId, courseId);
	}
	
	/**
	 * ����ѧ�ŷ�����ѡ�Ŀγ���Ϣ
	 * @param stuId
	 * @return
	 */
	public List<CourseInfo> getSelectingCourseInfoByProc(int stuId){
		String sql = "exec proc_StuSelectedCourseInfo ?";
		return getListByProc(sql, stuId);
	}
	
	/**
	 * ����ѧ�Ż�ȡδѡ��γ���Ϣ�б�
	 * @param stuId
	 * @return
	 */
	public List<CourseInfo> getCourseInfoByProc(int stuId){
		String sql = "exec proc_StuSelectingCourseInfo ?";
		return getListByProc(sql, stuId);
	}

	/**
	 * ��ȡ�γ��б�
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
	 * ͨ���γ�Id��ȡѧ��������Ϣ
	 * @param courseId
	 * @return DefaultTableModel
	 */
	public List<CourseInfo> getStudentListModelByCourseId(int courseId) {
		String sql = "select * from Student where StuId in "
				+ "(select StuId from ChooseCourse where CourseId = ?)";
		return new CourseService().getCourseList(db.excuteQuery(sql, courseId));
	}
	
	/**
	 * ͨ���γ�Id��ȡѧ��������Ϣ
	 * @param courseId
	 * @return DefaultTableModel
	 */
	public DefaultTableModel getStudentListModelByCourseName(String courseName) {
		String sql = "select * from Student where StuId in (select StuId from ChooseCourse cc inner join"
				+ " Course c on cc.CourseId=c.CourseId where c.CourseName = ?)";
		return db.getModel(db.excuteQuery(sql, courseName));
	}

	/**
	 * ���ݿγ̱�Ų��ҵ�����ѡ��ѧ������Ϣ
	 * @param courseId
	 * @return ѧ����Ϣ�б�
	 */
	public List<StuBasicInfo> getAllSelectCourseStudent(int courseId){
		String sql = "select * from Student where StuId in "
				+ "(select StuId from ChooseCourse where CourseId = ?)";
		ResultSet rs = db.excuteQuery(sql, courseId);
		return new StudentService().getStuList(rs);
	}
}
