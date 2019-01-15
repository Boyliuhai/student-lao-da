package com.DAL;
import java.sql.*;
import java.util.*;

import javax.swing.table.DefaultTableModel;

import com.Dao.*;

public class AcademyService {
	DBUtil db = new DBUtil();
	/**
	 * ��ѯ����ѧԺ��Ϣ
	 * @param id
	 * @return String
	 */
	public Academy QueryAcademy(int id) {
		String sql = "select * from Academy where AcademyId=?";
		Academy a = new Academy();
		try{
			ResultSet rs = db.excuteQuery(sql, id);
			if(rs.next()){
				a.setAcademyId(rs.getInt(1));
				a.setName(rs.getString(2));
			}
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			db.close();
		}
		return a;
	}
	
	/**
	 * ��ѯ����ѧԺ���ģ��
	 * @param id
	 * @return String
	 */
	public DefaultTableModel getSingleDefaultTableModel(int id) {
		String sql = "select * from Academy where AcademyId=?";
		ResultSet rs = null;
		try{
			rs = db.excuteQuery(sql, id);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return db.getModel(rs);
	}
	
	/**
	 * �������е�ѧԺ��Ϣ�б�
	 * @return
	 */
	public List<Academy> getAllAcademy(){
		String sql = "select * from Academy";
		List<Academy> list = new ArrayList<Academy>();
		Academy a = new Academy();
		try{
			ResultSet rs = db.excuteQuery(sql);
			while(rs.next()){
				a.setAcademyId(rs.getInt(1));
				a.setName(rs.getString(2));
				list.add(a);
			}
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			db.close();
		}
		return list;
	}
	
	/**
	 * �������е�ѧԺ��Ϣ�����
	 * @return
	 */
	public ResultSet getAllAcademyResultSet(){
		String sql = "select * from Academy";
		ResultSet rs = null;
		try{
			rs = db.excuteQuery(sql);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return rs;
	}
	
	/**
	 * �������е�ѧԺ��Ϣ���ģ��
	 * @return
	 */
	public DefaultTableModel getAllDefaultTableModel(){
		String sql = "select * from Academy";
		ResultSet rs = null;
		try{
			rs = db.excuteQuery(sql);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return db.getModel(rs);
	}
	
	/**
	 * ����ѧԺ��Ϣ
	 * @param Academy
	 * @return boolean
	 */
	public boolean AddAcademy(Academy academy) {
		String sql = "insert into Academy(AcademyId, AcademyName) values(?,?)";
		try{
			db.update(sql, academy.getAcademyId(), academy.getName());
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return true;
	}

	/**
	 * �޸�ѧԺ��Ϣ
	 * @param academy
	 * @return boolean
	 */
	public boolean ModefyAcademy(Academy academy) {
		String sql = "Update Academy set AcademyName=? where AcademyId=?";
		try{
			db.update(sql, academy.getName(), academy.getAcademyId());
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return true;
	}
	
	/**
	 * ɾ��ָ����ŵ�ѧԺ��Ϣ
	 * @param academyId
	 * @return boolean
	 */
	public boolean DeleteAcademy(int academyId) {
		String sql = "Delete from Academy where AcademyId=?";
		try{
			db.update(sql, academyId);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return true;
	}
}
