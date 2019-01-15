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

	//�������
	public static void main(String[] args) {
		new MainForm();
	}
	
	//��ӳ�Ա����
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
		m1 = new JMenu("ѧԺ��Ϣ����");
		mStudent = new JMenu("ѧ����Ϣ����");
		m3 = new JMenu("��ʦ��Ϣ����");
		m4 = new JMenu("�γ���Ϣ����");
		mScore = new JMenu("�ɼ���Ϣ����");
		m5 = new JMenu("���˲���");
		
		miQueryAcademyInfo = new JMenuItem("��ѯѧԺ��Ϣ");
		miInsertAcademyInfo = new JMenuItem("����ѧԺ��Ϣ");
		miModefyAcademyInfo = new JMenuItem("�޸�ѧԺ��Ϣ");
		miDeleteAcademyInfo = new JMenuItem("ɾ��ѧԺ��Ϣ");
		miQueryStudentInfo = new JMenuItem("��ѯѧ����Ϣ");
		miInsertStudentInfo = new JMenuItem("����ѧ����Ϣ");
		miModefyStudentInfo = new JMenuItem("�޸�ѧ����Ϣ");
		miDeleteStudentInfo = new JMenuItem("ɾ��ѧԱ��Ϣ");
		miQueryTeacherInfo = new JMenuItem("��ѯ��ʦ��Ϣ");
		miInsertTeacherInfo = new JMenuItem("�����ʦ��Ϣ");
		miModefyTeacherInfo = new JMenuItem("�޸Ľ�ʦ��Ϣ");
		miDeleteTeacherInfo = new JMenuItem("ɾ����ʦ��Ϣ");
		miQueryCourseInfo = new JMenuItem("��ѯ�γ���Ϣ");
		miInsertCourseInfo = new JMenuItem("��ӿγ���Ϣ");
		miModefyCourseInfo = new JMenuItem("�޸Ŀγ���Ϣ");
		miDeleteCourseInfo = new JMenuItem("ɾ���γ���Ϣ");
		miQueryScoreInfo = new JMenuItem("��ѯ�ɼ���Ϣ");
		miInsertScoreInfo = new JMenuItem("��ӳɼ���Ϣ");
		miModefyScoreInfo = new JMenuItem("�޸ĳɼ���Ϣ");
		miDeleteScoreInfo = new JMenuItem("ɾ���ɼ���Ϣ");
		miChooseCourse = new JMenuItem("ѧ��ѡ��");//ѧ��ѡ��
		miLogin = new JMenuItem("��¼");
		miLogOut = new JMenuItem("ע��");
		miExit = new JMenuItem("�˳�");
		miModefyPassword = new JMenuItem("�޸�����");
		
		this.setTitle("ѧԱ��Ϣ����ϵͳ");
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
		if(e.getSource() == miLogin){//��¼
			LoginForm.getInstance().setVisible(true);
		}else if(e.getSource() == miExit){//�˳�
			int result = QuestionDialog.ShowDialog(this, "�˳���ʾ", "���Ҫ�˳���");
			if(result == QuestionDialog.Dialog_OK){
				System.exit(0);
			}
		}else if(LoginForm.currentUser == null){//���ѵ�¼
			int dialog_result = new QuestionDialog(this, 
					"��¼��ʾ", "����û�е�¼�£����ȵ�¼��").Dialog_Result;
			if(dialog_result == QuestionDialog.Dialog_OK){
				LoginForm.getInstance().setVisible(true);
			}
		}else if(e.getSource() == miLogOut){//ע��
			LoginForm.currentUser = null;
			LoginForm.role=0;
			new QuestionDialog(this, "ע����ʾ", "ע���ɹ�����ӭ�´ε�¼��");
		}else if(e.getSource() == miModefyPassword){//�޸�����
			new ModefyPassword();
		}else if(e.getSource() == miQueryAcademyInfo){//ѧԺ
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
		}else if(e.getSource() == miQueryStudentInfo){//ѧ��
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
		}else if(e.getSource() == miChooseCourse){//ѡ��
			if(IsStudent()){
				new StuSelectCourseForm();
			}
		}else if(e.getSource() == miQueryScoreInfo){//�ɼ�
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
		}else if(e.getSource() == miQueryCourseInfo){//�γ�
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
		}else if(e.getSource() == miQueryTeacherInfo){//��ʦ
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
			new QuestionDialog(this, "��ʾ", "��Ǹ��ֻ�й���Ա���Է��ʴ˹��ܣ�");
			return false;
		}else{
			return true;
		}
	}
	
	private boolean IsStudent(){
		if(LoginForm.role != 3){
			new QuestionDialog(this, "��ʾ", "��Ǹ��ֻ��ѧ�����Բ鿴ѡ��ҳ�棡");
			return false;
		}else{
			return true;
		}
	}
	
	private boolean IsTeacher(){
		if(LoginForm.role != 2){
			new QuestionDialog(this, "��ʾ", "��Ǹ��ֻ�н�ʦ���Բ鿴ѡ��ҳ�棡");
			return false;
		}else{
			return true;
		}
	}
}
