package com.DAL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.Dao.*;

public class StudentService {
	//�½�DBUtil
	DBUtil db = new DBUtil();
	/**
	 * ����ѧ����¼
	 * @param student
	 * @return boolean
	 */
	public boolean AddStudent(StuBasicInfo student) {
		String sql = "insert into Student(AcademyId, StuName, StuSex, "
				+ "Birthday, IdNo, StudentImage, Instruction) "
				+ "values(?,?,?,?,?,?,?)";
		try{
			db.update(sql, student.getAcademyId(),
					student.getName(), student.getSex(), student.getBirthday(), 
					student.getIdNo(), student.getImageURL(), student.getInstruction());
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return true;
	}
	
	/**
	 * �޸�ѧ����Ϣ
	 * @param studentid
	 * @return boolean
	 */
	public boolean ModefyStudent(StuBasicInfo student) {
		String sql = "Update Student set AcademyId=?,StuName=?,StuSex=?,"
				+ "Birthday=?,IdNo=?,StudentImage=?,Instruction=? "
				+ "where StuId=?";
		try{
			db.update(sql, student.getAcademyId(), student.getName(), student.getSex(), 
					student.getBirthday(), student.getIdNo(), student.getImageURL(), 
					student.getInstruction(), student.getStudentId());
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return true;
	}
	
	/**
	 * ����ѧ��ɾ��ѧ����Ϣ
	 * @param studentid
	 * @return boolean
	 */
	public boolean DeleteStudent(int studentId) {
		String sql = "Delete From Student where StuId=?";
		try{
			db.update(sql, studentId);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return true;
	}
	
	/**
	 * ��ѯ����ѧ������Ϣ
	 * @param studentid
	 * @return Student
	 */
	public StuBasicInfo QueryStudent(int studentId) {
		String sql = "Select StuId,AcademyId,StuName,StuSex,"
				+ "Birthday,IdNo,StudentImage,Instruction "
				+ "from Student where StuId=?";
		StuBasicInfo student = null;
		try{
			ResultSet rs = db.excuteQuery(sql, studentId);
			if(rs.next()){
				student = new StuBasicInfo();
				student.setStudentId(rs.getInt(1));
				student.setAcademyId(rs.getInt(2));
				student.setName(rs.getString(3).trim());
				student.setSex(rs.getString(4).trim());
				student.setBirthday(rs.getString(5).trim());
				student.setIdNo(rs.getString(6).trim());
				student.setImageURL(rs.getString(7).trim());
				student.setInstruction(rs.getString(8).trim());
			}
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			db.close();
		}
		return student;
	}
	
	/**
	 * �������е�ѧ������
	 * @return
	 */
	public List<String> getAllStudentNames(){
		String sql = "select StuName from Student";
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
	 * ��װResultSet��List
	 * @param rs
	 * @return
	 */
	public List<StuBasicInfo> getStuList(ResultSet rs){
		List<StuBasicInfo> list = new ArrayList<StuBasicInfo>();
		ResultSetMetaData rsmd;
		StuBasicInfo s = null;
		try {
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			//��װ����
			while(rs.next()){
				s = new StuBasicInfo();
				for(int i=0;i<cols;i++){
					s.setStudentId(rs.getInt(1));
					s.setName(rs.getString(2));
					s.setSex(rs.getString(3));
					s.setAcademyId(rs.getInt(4));
					s.setBirthday(rs.getString(5));
					s.setIdNo(rs.getString(6));
					s.setImageURL(rs.getString(7));
					s.setInstruction(rs.getString(8));
				}
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close();
		}
		return list;
	}
	
}