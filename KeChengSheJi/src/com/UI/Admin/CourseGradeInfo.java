package com.UI.Admin;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.DAL.*;

@SuppressWarnings("serial")
public class CourseGradeInfo extends JFrame implements ItemListener, ActionListener{
	private static CourseGradeInfo instance;

	public static CourseGradeInfo getInstance(){
		if(instance == null){
			instance = new CourseGradeInfo();
		}
		return instance;
	}
	
	private CourseGradeInfo(){
		init();
		List<String> stuList = ts.getAllTeachingCourseName(LoginForm.currentUserId);
		if(stuList != null){
			bindDataToCombo(cbb, stuList);
			//������
			prepare(this.cbb.getSelectedItem().toString());
		}
	}

	ChooseCourseService cs = new ChooseCourseService();
	TeacherTeachingService ts = new TeacherTeachingService();
	
	JButton btnModefy, btnAdd, btnDelete, btnClear = null;

	JComboBox<String> cbb = null;
	JTable tStudent = null;
	JScrollPane sp_Student = null;
			
	public void init() {
		//��������
		cbb = new JComboBox<String>();
		tStudent = new JTable();
		sp_Student = new JScrollPane();
		btnModefy = new JButton("�޸�");
		btnAdd = new JButton("����");
		btnDelete = new JButton("ɾ��");
		btnClear = new JButton("���");
		
		//��������
		this.setTitle("¼��ѧ���ɼ�");
		this.setSize(550, 550);
		this.setLocation(650, 300);
		this.setLayout(new FlowLayout());
		cbb.addItemListener(this);
		btnModefy.addActionListener(this);
		btnAdd.addActionListener(this);
		btnDelete.addActionListener(this);
		btnClear.addActionListener(this);
		
		//����
		this.add(cbb);
		sp_Student.add(tStudent);
		this.add(sp_Student);
		this.add(btnAdd);
		this.add(btnModefy);
		this.add(btnDelete);
		this.add(btnClear);
		this.setVisible(true);
	}
	
	//�����ݵ�JComboBox
	private void bindDataToCombo(JComboBox<String> obj, List<String> courseList){
		for(int i=0;i<courseList.size();i++){
			obj.addItem(courseList.get(i));
		}
	}
	
	//���ñ�������
	private void prepare(String courseName){
		DefaultTableModel dtm = cs.getStudentListModelByCourseName(courseName);
		tStudent.setModel(dtm);
		sp_Student.setViewportView(tStudent);
		tStudent.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		String s = e.getItem().toString();
		if(s != null){
			prepare(s);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAdd){
			
		}else if(e.getSource() == btnModefy){
			
		}else if(e.getSource() == btnDelete){
			
		}else if(e.getSource() == btnClear){
			
		}
	}
}