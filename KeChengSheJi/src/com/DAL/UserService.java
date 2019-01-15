package com.DAL;

import com.UI.Admin.LoginForm;

public class UserService {
	DBUtil db = new DBUtil();
	/**
	 * �����û�ID��ȡ����
	 * @param userId
	 * @return
	 */
	public String getPassword(int userId) {
		String sql = "select PassWord from UserLogin where UserId=?";
		String password = null;
		try{
			password = (String)db.getSingle(sql, userId);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return password;
	}
	
	/**
	 * �����û�ID��ȡ��ɫ
	 * @param userId
	 * @return
	 */
	public int getPower(int userId){
		String sql = "select UserPower from UserLogin where userId=?";
		int power = -1;
		try{
			power = Integer.parseInt(db.getSingle(sql, userId).toString());
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return power;
	}
	
	/**
	 * ʹ���û�ID�;������޸�����
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	public boolean updatePassWord(int userId, String oldPassword, String newPassword){
		String sql = "update UserLogin set PassWord=? where UserId=? and PassWord=?";
		try{
			db.update(sql, newPassword, userId, oldPassword);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return true;
	}
	
	/**
	 * �����û���Ӧ��Ȩ��
	 * @return int
	 */
	public static int Authorization(){
		return LoginForm.role;
	}

	/**
	 * ��ȡָ���û�ID���û���
	 * @param userId
	 * @return
	 */
	public String getUserName(int userId) {
		String sql = "select UserName from UserLogin where UserId=?";
		return db.getSingle(sql, userId).toString();
	}
}
