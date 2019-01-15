package com.DAL;

public class ScorceService {
	DBUtil db = new DBUtil();
	/**
	 * �޸�ѧ���ɼ�
	 * @param studentId
	 * @param courseId
	 * @param score
	 * @return boolean
	 */
	public boolean ModefyScore(int studentId, int courseId, int score) {
		String sql = "Update Score set Score=? where StudentId=? and CourseId=?";
		try{
			db.update(sql, studentId, score);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return true;
	}
	
	/**
	 * ���ѧ���ɼ�
	 * @param studentId
	 * @param courseId
	 * @param score
	 * @return boolean
	 */
	public boolean AddScore(int studentId, int courseId, int score) {
		String sql = "insert into Score(StudentId,Score,CourseId) values(?,?,?)";
		try{
			db.update(sql, studentId, courseId, score);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return true;
	}
	
	/**
	 * ɾ���ɼ���Ϣ
	 * @param studentId
	 * @param courseId
	 * @return boolean
	 */
	public boolean DeleteScore(int studentId, int courseId) {
		String sql = "Delete from Score where StudentId=? and CourseId=?";
		try{
			db.update(sql, studentId, courseId);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return true;
	}
	
	/**
	 * ��ѯ�����ɼ���Ϣ
	 * @param studentId
	 * @param courseId
	 * @return int
	 */
	public int QueryScore(int studentId, int courseId) {
		String sql = "select * from score where StudentId=? and CourseId=?";
		int score = 0;
		try{
			score = Integer.parseInt((String) db.getSingle(sql, studentId, courseId));
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			db.close();
		}
		return score;
	}
	
}
