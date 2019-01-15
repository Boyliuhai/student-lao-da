package com.UI.Admin;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.DAL.*;
import com.Dao.CourseInfo;
import com.IObserver.IObservable;
import com.IObserver.IObserver;

@SuppressWarnings("serial")
public class CourseInfoForm extends JFrame implements IObserver, ActionListener{
	private static CourseInfoForm instance = null;
	
	public static CourseInfoForm getInstance(){
		if(instance == null){
			instance = new CourseInfoForm();
		}
		return instance;
	}
	
	private CourseInfoForm(){
		init();
		//����
		CourseIdInput.getInstance().addObserver(this);
	}
	
	JButton btnClear, btnAdd, btnModefy, btnDelete = null;
	
	JLabel lblCourseName = new JLabel("�γ�����");
	JTextField txtCourseName = new JTextField(14);
	JLabel lblCoursePower = new JLabel("ѧ��");
	JTextField txtCoursePower = new JTextField(14);
	JLabel lblCourseTime = new JLabel("�Ͽ�ʱ��");
	JTextField txtCourseTime = new JTextField(14);
	JLabel lblLimit = new JLabel("��ѡ����");
	JTextField txtLimit = new JTextField(14);
	JLabel lblExists = new JLabel("��ѡ����");
	JTextField txtExists = new JTextField(14);
	
	public void init() {
		btnAdd = new JButton("�ύ");
		btnClear = new JButton("���");
		btnModefy = new JButton("�޸�");
		btnDelete = new JButton("ɾ��");
		
		//���ö�������
		this.setTitle("���ѧ��������Ϣ");
		this.setSize(250, 250);
		this.setLocation(650, 300);
		this.setLayout(new FlowLayout());
		
		//���
		this.add(lblCourseName);
		this.add(txtCourseName);
		this.add(lblCoursePower);
		this.add(txtCoursePower);
		this.add(lblCourseTime);
		this.add(txtCourseTime);
		this.add(lblExists);
		this.add(txtExists);
		this.add(lblLimit);
		this.add(txtLimit);
		this.add(btnAdd);
		this.add(btnModefy);
		this.add(btnDelete);
		this.add(btnClear);
		
		btnAdd.addActionListener(this);//��Ӽ����¼�
		btnClear.addActionListener(this);
		btnModefy.addActionListener(this);
		btnDelete.addActionListener(this);
	}

	@Override
	public void update(IObservable o) {
		CourseInfo course = ((CourseIdInput)o).course;
		if(course != null){
			dataBindToForm(course);
			this.setVisible(true);
		}
	}

	//�����ݵ�����
	private void dataBindToForm(CourseInfo course) {
		this.txtCourseName.setText(course.getCourseName());
		this.txtCoursePower.setText(Integer.toString(course.getCoursePower()));
		this.txtCourseTime.setText(course.getCourseTime());
		this.txtExists.setText(Integer.toString(course.getExists()));
		this.txtLimit.setText(Integer.toString(course.getLimit()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnClear){
			clear();
		}else if(e.getSource() == btnAdd){
			addStudentInfo(CourseIdInput.getInstance().course);
		}else if(e.getSource() == btnModefy){
			modefyStudentInfo(CourseIdInput.getInstance().course);
		}else if(e.getSource() == btnDelete){
			deleteStudentInfo(CourseIdInput.getInstance().course);
		}
	}
	
	//��տؼ�
	private void clear() {
		this.txtCourseName.setText("");
		this.txtCoursePower.setText("");
		this.txtCourseTime.setText("");
		this.txtExists.setText("");
		this.txtLimit.setText("");
	}

	//�޸�ѧ����Ϣ
	private void modefyStudentInfo(CourseInfo c) {
		if(new CourseService().ModefyCourse(c)){
			QuestionDialog.ShowDialog(this, "�޸���ʾ", "�γ���Ϣ�޸ĳɹ�");
		}else{
			QuestionDialog.ShowDialog(this, "�޸���ʾ", "�γ���Ϣ�޸�ʧ�ܣ������Ի���ϵ����Ա��");
		}
	}

	//ɾ��ѧ����Ϣ
	private void deleteStudentInfo(CourseInfo c) {
		if(QuestionDialog.ShowDialog(this, "ɾ����ʾ", "ȷ��Ҫɾ����") == QuestionDialog.Dialog_OK){
			if(new CourseService().DeleteCourse(c.getCourseId())){
				QuestionDialog.ShowDialog(this, "ɾ����ʾ", "�γ���Ϣɾ���ɹ�");
			}else{
				QuestionDialog.ShowDialog(this, "ɾ����ʾ", "�γ���Ϣɾ��ʧ�ܣ������Ի���ϵ����Ա��");
			}
		}
	}

	//��ӿγ���Ϣ
	private void addStudentInfo(CourseInfo c){
		if(new CourseService().ModefyCourse(c)){
			QuestionDialog.ShowDialog(this, "�����ʾ", "ѧ����Ϣ�ύ�ɹ�");
		}else{
			QuestionDialog.ShowDialog(this, "�����ʾ", "ѧ����Ϣ�ύʧ�ܣ������Ի���ϵ����Ա��");
		}
	}
}
