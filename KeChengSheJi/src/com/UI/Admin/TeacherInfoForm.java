package com.UI.Admin;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.DAL.*;
import com.Dao.Teacher;
import com.IObserver.IObservable;
import com.IObserver.IObserver;

@SuppressWarnings("serial")
public class TeacherInfoForm extends JFrame implements IObserver, ActionListener{
	private static TeacherInfoForm instance = null;
	
	public static TeacherInfoForm getInstance(){
		if(instance == null){
			instance = new TeacherInfoForm();
		}
		return instance;
	}
	
	private TeacherInfoForm(){
		init();
		//订阅
		TeacherIdInput.getInstance().addObserver(this);
	}
	
	TeacherService ts = new TeacherService();
	
	JButton btnClear, btnAdd, btnModefy, btnDelete;
	
	JLabel lblTeacherId = new JLabel("教师编号");
	JTextField txtTeacherId = new JTextField(14);
	JLabel lblTeacherName = new JLabel("教师姓名");
	JTextField txtTeacherName = new JTextField(14);
	JLabel lblAcademyId = new JLabel("学院编号");
	JTextField txtAcademyId = new JTextField(14);
	JLabel lblSex = new JLabel("性别");
	JTextField txtSex = new JTextField(14);
	
	public void init() {
		btnAdd = new JButton("提交");
		btnClear = new JButton("清空");
		btnModefy = new JButton("修改");
		btnDelete = new JButton("删除");
		
		//设置对象属性
		this.setTitle("教师信息管理");
		this.setSize(250, 250);
		this.setLocation(650, 300);
		this.setLayout(new FlowLayout());
		
		//添加
		this.add(lblTeacherId);
		this.add(txtTeacherId);
		this.add(lblTeacherName);
		this.add(txtTeacherName);
		this.add(lblAcademyId);
		this.add(txtAcademyId);
		this.add(lblSex);
		this.add(txtSex);
		this.add(btnAdd);
		this.add(btnModefy);
		this.add(btnDelete);
		this.add(btnClear);
		
		btnAdd.addActionListener(this);//添加监听事件
		btnClear.addActionListener(this);
		btnModefy.addActionListener(this);
		btnDelete.addActionListener(this);
	}

	@Override
	public void update(IObservable o) {
		Teacher teacher = ((TeacherIdInput)o).teacher;
		if(teacher != null){
			this.txtAcademyId.setText(Integer.toString(teacher.getAcademyId()));
			this.txtSex.setText(teacher.getSex());
			this.txtTeacherId.setText(Integer.toString(teacher.getId()));
			this.txtTeacherName.setText(teacher.getName());
			this.setVisible(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnClear){
			clear();
		}else if(e.getSource() == btnAdd){
			addTeacherInfo(TeacherIdInput.getInstance().teacher);
		}else if(e.getSource() == btnModefy){
			modefyTeacherInfo(TeacherIdInput.getInstance().teacher);
		}else if(e.getSource() == btnDelete){
			deleteStudentInfo(TeacherIdInput.getInstance().teacher);
		}
	}
	
	//清空控件
	private void clear() {
		this.txtAcademyId.setText("");
		this.txtTeacherId.setText("");
		this.txtSex.setText("");
		this.txtTeacherName.setText("");
	}

	//修改学生信息
	private void modefyTeacherInfo(Teacher teacher) {
		if(ts.ModefyTeacher(teacher)){
			QuestionDialog.ShowDialog(this, "修改提示", "教师信息修改成功");
		}else{
			QuestionDialog.ShowDialog(this, "修改提示", "教师信息修改失败，请重试或联系管理员！");
		}
	}

	//删除学生信息
	private void deleteStudentInfo(Teacher teacher) {
		if(QuestionDialog.ShowDialog(this, "删除提示", "确定要删除吗？") == QuestionDialog.Dialog_OK){
			if(ts.DeleteTeacher(teacher.getId())){
				QuestionDialog.ShowDialog(this, "删除提示", "教师信息删除成功");
			}else{
				QuestionDialog.ShowDialog(this, "删除提示", "教师信息删除失败，请重试或联系管理员！");
			}
		}
	}

	//添加课程信息
	private void addTeacherInfo(Teacher teacher){
		if(ts.AddTeacher(teacher)){
			QuestionDialog.ShowDialog(this, "添加提示", "教师信息提交成功");
		}else{
			QuestionDialog.ShowDialog(this, "添加提示", "教师信息提交失败，请重试或联系管理员！");
		}
	}
}
