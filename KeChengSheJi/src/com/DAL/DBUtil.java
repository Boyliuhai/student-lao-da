package com.DAL;

import java.sql.*;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class DBUtil {
	/**
	 * getConnection ��ȡ���ݿ�����
	 * @return Connection
	 */
	private Connection conn = null;
	
	public void getConnection(){
		if(conn == null){
			//���ݿ���������������
			String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=STUDENT";
			//�������ݿ���û���������
			String username = "sa";
			String password = "11170407";
			try{
				Class.forName(driver);
				conn = DriverManager.getConnection(url, username, password);
			}catch(Exception ex){
				System.out.println("���ݷ��ʷ�������" + ex.getMessage());
			}
		}
	}

	/**
	 * �ر����ݿ�����
	 * @param conn
	 * @return boolean
	 */
	public boolean close(){
		try {
			conn.close();
			conn = null;
		} catch (Exception e) {
			System.out.println("���ݷ��ʷ�������" + e.getMessage());
		}
		return true;
	}
	
	/**
	 * ����
	 * @param sql
	 * @param objects
	 * @return boolean
	 */
	public boolean update(String sql, Object... objects) {
		getConnection();
		try{
			PreparedStatement ps = prepareState(conn, sql, objects);
			ps.executeUpdate();
		}catch(Exception ex){
			System.out.println("���ݷ��ʷ�������" + ex.getMessage());
		}finally{
			close();
		}
		return true;
	}
	
	/**
	 * ��ѯ��һ���
	 * @param sql
	 * @param objects
	 * @return 
	 */
	public Object getSingle(String sql, Object... objects){
		getConnection();
		Object obj = null;
		ResultSet rs = null;
		try{
			PreparedStatement ps = prepareState(conn, sql, objects);
			rs = ps.executeQuery();
			if(rs.next()){
				obj = rs.getObject(1);
			}
		}catch(Exception ex){
			System.out.println("���ݷ��ʷ�������" + ex.getMessage());
		}finally{
			close();
		}
		return obj;
	}
	
	/**
	 * ��ѯ�����
	 * @param sql
	 * @param objects
	 * @return
	 */
	public ResultSet excuteQuery(String sql, Object... objects) {
		getConnection();
		ResultSet rs = null;
		try{
			PreparedStatement ps = prepareState(conn, sql, objects);
			rs = ps.executeQuery();
		}catch(Exception ex){
			System.out.println("���ݷ��ʷ�������" + ex.getMessage());
		}
		return rs;
	}
	
	/**
	 * ��sql���Ϳɱ����ƴ�����շ���׼���õ�PrepareStatement����
	 * @param conn
	 * @param sql
	 * @param objects
	 * @return PrepareStatement
	 */
	private PreparedStatement prepareState(Connection conn, String sql, Object...objects){
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			for(int i=1;i<objects.length+1;i++){
				ps.setObject(i, objects[i-1]);
			}
		} catch (Exception ex) {
			System.out.println("���ݷ��ʷ�������" + ex.getMessage());
		}
		return ps;
	}
	
	/**
	 * ִ�д洢���̷��ؽ����
	 * @param sql
	 * @param objects
	 * @return ResultSet
	 */
	public ResultSet queryByProc(String sql, Object...objects){
		ResultSet rs = null;
		try{
			getConnection();
			CallableStatement cs = conn.prepareCall(sql);
			for(int i=0;i<objects.length;i++){
				cs.setObject(i+1, objects[i]);
			}
			rs = cs.executeQuery();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return rs;
	}
	
	/**
	 * ִ�д洢���̷��ص�һ���
	 * @param sql
	 * @param objects
	 * @return ResultSet
	 */
	public Object getSingleByProc(String sql, Object...objects){
		ResultSet rs = queryByProc(sql, objects);
		Object obj = null;
		try {
			if(rs.next()){
				obj = rs.getObject(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return obj;
	}
	
	/**
	 * ��װResultSet��DefaultTableModel
	 * @param rs
	 * @return
	 */
	public DefaultTableModel getModel(ResultSet rs){
		DefaultTableModel dtm = new DefaultTableModel();
		ResultSetMetaData rsmd;
		try {
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			//��ȡ����
			for(int i=0;i<rs.getMetaData().getColumnCount();i++){
				String columnName = rsmd.getColumnName(i+1);
				dtm.addColumn(columnName);
			}
			//��װ����
			while(rs.next()){
				//ÿ�������Ӧ��һ������
				Vector<Object> rowData = new Vector<Object>();
				for(int i=0;i<cols;i++){
					rowData.add(i, rs.getObject(i+1));
				}
				dtm.addRow(rowData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return dtm;
	}
}
