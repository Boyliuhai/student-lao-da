package com.DAL;

import java.sql.*;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class DBUtil {
	/**
	 * getConnection 获取数据库连接
	 * @return Connection
	 */
	private Connection conn = null;
	
	public void getConnection(){
		if(conn == null){
			//数据库驱动和数据连接
			String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=STUDENT";
			//填入数据库的用户名跟密码
			String username = "sa";
			String password = "11170407";
			try{
				Class.forName(driver);
				conn = DriverManager.getConnection(url, username, password);
			}catch(Exception ex){
				System.out.println("数据访问发生错误：" + ex.getMessage());
			}
		}
	}

	/**
	 * 关闭数据库连接
	 * @param conn
	 * @return boolean
	 */
	public boolean close(){
		try {
			conn.close();
			conn = null;
		} catch (Exception e) {
			System.out.println("数据访问发生错误：" + e.getMessage());
		}
		return true;
	}
	
	/**
	 * 更新
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
			System.out.println("数据访问发生错误：" + ex.getMessage());
		}finally{
			close();
		}
		return true;
	}
	
	/**
	 * 查询单一结果
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
			System.out.println("数据访问发生错误：" + ex.getMessage());
		}finally{
			close();
		}
		return obj;
	}
	
	/**
	 * 查询结果集
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
			System.out.println("数据访问发生错误：" + ex.getMessage());
		}
		return rs;
	}
	
	/**
	 * 将sql语句和可变参数拼接最终返回准备好的PrepareStatement对象
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
			System.out.println("数据访问发生错误：" + ex.getMessage());
		}
		return ps;
	}
	
	/**
	 * 执行存储过程返回结果集
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
	 * 执行存储过程返回单一结果
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
	 * 封装ResultSet到DefaultTableModel
	 * @param rs
	 * @return
	 */
	public DefaultTableModel getModel(ResultSet rs){
		DefaultTableModel dtm = new DefaultTableModel();
		ResultSetMetaData rsmd;
		try {
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			//获取列名
			for(int i=0;i<rs.getMetaData().getColumnCount();i++){
				String columnName = rsmd.getColumnName(i+1);
				dtm.addColumn(columnName);
			}
			//封装对象
			while(rs.next()){
				//每个对象对应着一个向量
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
