package com.UI.Admin;

import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MainForm extends JFrame implements ActionListener {
	StudentBasicInfoForm studentBasicInfoForm = null;
	
	private static MainForm instance = null;
	
	private MainForm(){
		init();
		this.setVisible(true);
		studentBasicInfoForm = StudentBasicInfoForm.getInstance();
	}
	
	public MainForm getInstance(){
		if(instance == null){
			instance = new MainForm();
		}
		return instance;
	}

	//程序入口
	public static void main(String[] args) {
		new MainForm();
	}
	
	//添加成员变量
	JMenuBar mb;
	JMenu m1, mStudent, m3, m4, mScore, m5;
	JMenuItem miQueryAcademyInfo, miInsertAcademyInfo, miModefyAcademyInfo, miDeleteAcademyInfo, 
	miQueryStudentInfo, miInsertStudentInfo, miModefyStudentInfo, miDeleteStudentInfo, 
	miQueryTeacherInfo, miInsertTeacherInfo, miModefyTeacherInfo, miDeleteTeacherInfo, 
	miQueryCourseInfo, miInsertCourseInfo, miModefyCourseInfo, miDeleteCourseInfo, 
	miQueryScoreInfo, miInsertScoreInfo, miModefyScoreInfo, miDeleteScoreInfo,
	miLogin, miLogOut, miExit, miModefyPassword, miChooseCourse;
	
	public void init(){
		mb = new JMenuBar();
		m1 = new JMenu("学院信息管理");
		mStudent = new JMenu("学生信息管理");
		m3 = new JMenu("教师信息管理");
		m4 = new JMenu("课程信息管理");
		mScore = new JMenu("成绩信息管理");
		m5 = new JMenu("个人操作");
		
		miQueryAcademyInfo = new JMenuItem("查询学院信息");
		miInsertAcademyInfo = new JMenuItem("插入学院信息");
		miModefyAcademyInfo = new JMenuItem("修改学院信息");
		miDeleteAcademyInfo = new JMenuItem("删除学院信息");
		miQueryStudentInfo = new JMenuItem("查询学生信息");
		miInsertStudentInfo = new JMenuItem("插入学生信息");
		miModefyStudentInfo = new JMenuItem("修改学生信息");
		miDeleteStudentInfo = new JMenuItem("删除学员信息");
		miQueryTeacherInfo = new JMenuItem("查询教师信息");
		miInsertTeacherInfo = new JMenuItem("插入教师信息");
		miModefyTeacherInfo = new JMenuItem("修改教师信息");
		miDeleteTeacherInfo = new JMenuItem("删除教师信息");
		miQueryCourseInfo = new JMenuItem("查询课程信息");
		miInsertCourseInfo = new JMenuItem("添加课程信息");
		miModefyCourseInfo = new JMenuItem("修改课程信息");
		miDeleteCourseInfo = new JMenuItem("删除课程信息");
		miQueryScoreInfo = new JMenuItem("查询成绩信息");
		miInsertScoreInfo = new JMenuItem("添加成绩信息");
		miModefyScoreInfo = new JMenuItem("修改成绩信息");
		miDeleteScoreInfo = new JMenuItem("删除成绩信息");
		miChooseCourse = new JMenuItem("学生选课");//学生选课
		miLogin = new JMenuItem("登录");
		miLogOut = new JMenuItem("注销");
		miExit = new JMenuItem("退出");
		miModefyPassword = new JMenuItem("修改密码");
		
		this.setTitle("学员信息管理系统");
		this.setSize(600, 500);
		this.setLocation(500, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		miQueryAcademyInfo.addActionListener(this);
		miInsertAcademyInfo.addActionListener(this);
		miModefyAcademyInfo.addActionListener(this);
		miDeleteAcademyInfo.addActionListener(this);
		miQueryStudentInfo.addActionListener(this);
		miInsertStudentInfo.addActionListener(this);
		miModefyStudentInfo.addActionListener(this);
		miDeleteStudentInfo.addActionListener(this);
		miQueryTeacherInfo.addActionListener(this);
		miInsertTeacherInfo.addActionListener(this);
		miModefyTeacherInfo.addActionListener(this);
		miDeleteTeacherInfo.addActionListener(this);
		miQueryCourseInfo.addActionListener(this);
		miInsertCourseInfo.addActionListener(this);
		miModefyCourseInfo.addActionListener(this);
		miDeleteCourseInfo.addActionListener(this);
		miQueryScoreInfo.addActionListener(this);
		miInsertScoreInfo.addActionListener(this);
		miModefyScoreInfo.addActionListener(this);
		miDeleteScoreInfo.addActionListener(this);
		miLogin.addActionListener(this);
		miLogOut.addActionListener(this);
		miExit.addActionListener(this);
		miModefyPassword.addActionListener(this);
		miChooseCourse.addActionListener(this);
		
		m1.add(miQueryAcademyInfo);
		m1.add(miInsertAcademyInfo);
		m1.add(miModefyAcademyInfo);
		m1.add(miDeleteAcademyInfo);
		mStudent.add(miQueryStudentInfo);
		mStudent.add(miInsertStudentInfo);
		mStudent.add(miModefyStudentInfo);
		mStudent.add(miDeleteStudentInfo);
		mStudent.add(miChooseCourse);
		m3.add(miQueryTeacherInfo);
		m3.add(miInsertTeacherInfo);
		m3.add(miModefyTeacherInfo);
		m3.add(miDeleteTeacherInfo);
		m4.add(miQueryCourseInfo);
		m4.add(miInsertCourseInfo);
		m4.add(miModefyCourseInfo);
		m4.add(miDeleteCourseInfo);
		m5.add(miLogin);
		m5.add(miLogOut);
		m5.add(miExit);
		m5.add(miModefyPassword);
		mScore.add(miQueryScoreInfo);
		mScore.add(miInsertScoreInfo);
		mScore.add(miModefyScoreInfo);
		mScore.add(miDeleteScoreInfo);
		mb.add(m1);
		mb.add(mStudent);
		mb.add(m3);
		mb.add(m4);
		mb.add(mScore);
		mb.add(m5);
		this.setJMenuBar(mb);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == miLogin){//登录
			LoginForm.getInstance().setVisible(true);
		}else if(e.getSource() == miExit){//退出
			int result = QuestionDialog.ShowDialog(this, "退出提示", "真的要退出吗？");
			if(result == QuestionDialog.Dialog_OK){
				System.exit(0);
			}
		}else if(LoginForm.currentUser == null){//提醒登录
			int dialog_result = new QuestionDialog(this, 
					"登录提示", "您还没有登录奥，请先登录！").Dialog_Result;
			if(dialog_result == QuestionDialog.Dialog_OK){
				LoginForm.getInstance().setVisible(true);
			}
		}else if(e.getSource() == miLogOut){//注销
			LoginForm.currentUser = null;
			LoginForm.role=0;
			new QuestionDialog(this, "注销提示", "注销成功！欢迎下次登录！");
		}else if(e.getSource() == miModefyPassword){//修改密码
			new ModefyPassword();
		}else if(e.getSource() == miQueryAcademyInfo){//学院
			AcademyIDInput.getInstance().setVisible(true);
		}else if(e.getSource() == miInsertAcademyInfo){
			if(IsAdmin()){
				new AcademyInfoInput();
			}
		}else if(e.getSource() == miModefyAcademyInfo){
			if(IsAdmin()){
				AcademyInfoForm.getInstance().btnDeleteCourse.setVisible(false);
				AcademyInfoForm.getInstance().btnModefyCourse.setVisible(true);
				AcademyIDInput.getInstance().setVisible(true);
			}
		}else if(e.getSource() == miDeleteAcademyInfo){
			if(IsAdmin()){
				AcademyInfoForm.getInstance().btnDeleteCourse.setVisible(true);
				AcademyInfoForm.getInstance().btnModefyCourse.setVisible(false);
				AcademyIDInput.getInstance().setVisible(true);
			}
		}else if(e.getSource() == miQueryStudentInfo){//学生
			StuIDInput.getInstance().setVisible(true);
		}else if(e.getSource() == miInsertStudentInfo){
			if(IsAdmin()){
				studentBasicInfoForm.btnAdd.setVisible(true);
				studentBasicInfoForm.btnDelete.setVisible(false);
				studentBasicInfoForm.btnModefy.setVisible(false);
				studentBasicInfoForm.setVisible(true);
			}
		}else if(e.getSource() == miModefyStudentInfo){
			if(IsAdmin()){
				StuIDInput.getInstance().setVisible(true);
				studentBasicInfoForm.btnAdd.setVisible(false);
				studentBasicInfoForm.btnDelete.setVisible(false);
				studentBasicInfoForm.btnModefy.setVisible(true);
			}
		}else if(e.getSource() == miDeleteStudentInfo){
			if(IsAdmin()){
				StuIDInput.getInstance().setVisible(true);
				studentBasicInfoForm.btnAdd.setVisible(false);
				studentBasicInfoForm.btnDelete.setVisible(true);
				studentBasicInfoForm.btnModefy.setVisible(false);
			}
		}else if(e.getSource() == miChooseCourse){//选课
			if(IsStudent()){
				new StuSelectCourseForm();
			}
		}else if(e.getSource() == miQueryScoreInfo){//成绩
			CourseGradeInfo.getInstance().btnAdd.setVisible(false);
			CourseGradeInfo.getInstance().btnDelete.setVisible(false);
			CourseGradeInfo.getInstance().btnModefy.setVisible(false);
			CourseGradeInfo.getInstance().btnClear.setVisible(false);
		}else if(e.getSource() == miInsertScoreInfo){
			if(IsTeacher()){
				CourseGradeInfo.getInstance();
			}
		}else if(e.getSource() == miModefyScoreInfo){
			IsTeacher();
		}else if(e.getSource() == miDeleteScoreInfo){
			IsTeacher();
		}else if(e.getSource() == miQueryCourseInfo){//课程
			CourseIdInput.getInstance().setVisible(true);
		}else if(e.getSource() == miInsertCourseInfo){
			if(IsAdmin()){
				CourseIdInput.getInstance().setVisible(true);
				CourseInfoForm.getInstance().btnAdd.setVisible(true);
				CourseInfoForm.getInstance().btnDelete.setVisible(false);
				CourseInfoForm.getInstance().btnModefy.setVisible(false);
			}
		}else if(e.getSource() == miModefyCourseInfo){
			if(IsAdmin()){
				CourseIdInput.getInstance().setVisible(true);
				CourseInfoForm.getInstance().btnAdd.setVisible(false);
				CourseInfoForm.getInstance().btnDelete.setVisible(false);
				CourseInfoForm.getInstance().btnModefy.setVisible(true);
			}
		}else if(e.getSource() == miDeleteCourseInfo){
			if(IsAdmin()){
				CourseIdInput.getInstance().setVisible(true);
				CourseInfoForm.getInstance().btnAdd.setVisible(false);
				CourseInfoForm.getInstance().btnDelete.setVisible(true);
				CourseInfoForm.getInstance().btnModefy.setVisible(false);
			}
		}else if(e.getSource() == miQueryTeacherInfo){//教师
			TeacherIdInput.getInstance().setVisible(true);
			TeacherInfoForm.getInstance().btnAdd.setVisible(false);
			TeacherInfoForm.getInstance().btnDelete.setVisible(false);
			TeacherInfoForm.getInstance().btnModefy.setVisible(false);
		}else if(e.getSource() == miInsertTeacherInfo){
			if(IsAdmin()){
				TeacherIdInput.getInstance().setVisible(true);
				TeacherInfoForm.getInstance().btnAdd.setVisible(true);
				TeacherInfoForm.getInstance().btnDelete.setVisible(false);
				TeacherInfoForm.getInstance().btnModefy.setVisible(false);
			}
		}else if(e.getSource() == miModefyTeacherInfo){
			if(IsAdmin()){
				TeacherIdInput.getInstance().setVisible(true);
				TeacherInfoForm.getInstance().btnAdd.setVisible(false);
				TeacherInfoForm.getInstance().btnDelete.setVisible(false);
				TeacherInfoForm.getInstance().btnModefy.setVisible(true);
			}
		}else if(e.getSource() == miDeleteTeacherInfo){
			if(IsAdmin()){
				TeacherIdInput.getInstance().setVisible(true);
				TeacherInfoForm.getInstance().btnAdd.setVisible(false);
				TeacherInfoForm.getInstance().btnDelete.setVisible(true);
				TeacherInfoForm.getInstance().btnModefy.setVisible(false);
			}
		}
	}
	
	private boolean IsAdmin(){
		if(LoginForm.role != 1){
			new QuestionDialog(this, "提示", "抱歉，只有管理员可以访问此功能！");
			return false;
		}else{
			return true;
		}
	}
	
	private boolean IsStudent(){
		if(LoginForm.role != 3){
			new QuestionDialog(this, "提示", "抱歉，只有学生可以查看选课页面！");
			return false;
		}else{
			return true;
		}
	}
	
	private boolean IsTeacher(){
		if(LoginForm.role != 2){
			new QuestionDialog(this, "提示", "抱歉，只有教师可以查看选课页面！");
			return false;
		}else{
			return true;
		}
	}
}
