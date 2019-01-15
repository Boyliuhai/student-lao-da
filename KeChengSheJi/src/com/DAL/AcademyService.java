package com.DAL;
import java.sql.*;
import java.util.*;

import javax.swing.table.DefaultTableModel;

import com.Dao.*;

public class AcademyService {
	DBUtil db = new DBUtil();
	/**
	 * 查询单个学院信息
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
	 * 查询单个学院表格模型
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
	 * 返回所有的学院信息列表
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
	 * 返回所有的学院信息结果集
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
	 * 返回所有的学院信息表格模型
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
	 * 插入学院信息
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
	 * 修改学院信息
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
	 * 删除指定编号的学院信息
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
