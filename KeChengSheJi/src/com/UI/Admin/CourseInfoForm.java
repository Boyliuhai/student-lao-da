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
		//订阅
		CourseIdInput.getInstance().addObserver(this);
	}
	
	JButton btnClear, btnAdd, btnModefy, btnDelete = null;
	
	JLabel lblCourseName = new JLabel("课程名：");
	JTextField txtCourseName = new JTextField(14);
	JLabel lblCoursePower = new JLabel("学分");
	JTextField txtCoursePower = new JTextField(14);
	JLabel lblCourseTime = new JLabel("上课时间");
	JTextField txtCourseTime = new JTextField(14);
	JLabel lblLimit = new JLabel("限选人数");
	JTextField txtLimit = new JTextField(14);
	JLabel lblExists = new JLabel("已选人数");
	JTextField txtExists = new JTextField(14);
	
	public void init() {
		btnAdd = new JButton("提交");
		btnClear = new JButton("清空");
		btnModefy = new JButton("修改");
		btnDelete = new JButton("删除");
		
		//设置对象属性
		this.setTitle("添加学生基本信息");
		this.setSize(250, 250);
		this.setLocation(650, 300);
		this.setLayout(new FlowLayout());
		
		//添加
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
		
		btnAdd.addActionListener(this);//添加监听事件
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

	//绑定数据到窗体
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
	
	//清空控件
	private void clear() {
		this.txtCourseName.setText("");
		this.txtCoursePower.setText("");
		this.txtCourseTime.setText("");
		this.txtExists.setText("");
		this.txtLimit.setText("");
	}

	//修改学生信息
	private void modefyStudentInfo(CourseInfo c) {
		if(new CourseService().ModefyCourse(c)){
			QuestionDialog.ShowDialog(this, "修改提示", "课程信息修改成功");
		}else{
			QuestionDialog.ShowDialog(this, "修改提示", "课程信息修改失败，请重试或联系管理员！");
		}
	}

	//删除学生信息
	private void deleteStudentInfo(CourseInfo c) {
		if(QuestionDialog.ShowDialog(this, "删除提示", "确定要删除吗？") == QuestionDialog.Dialog_OK){
			if(new CourseService().DeleteCourse(c.getCourseId())){
				QuestionDialog.ShowDialog(this, "删除提示", "课程信息删除成功");
			}else{
				QuestionDialog.ShowDialog(this, "删除提示", "课程信息删除失败，请重试或联系管理员！");
			}
		}
	}

	//添加课程信息
	private void addStudentInfo(CourseInfo c){
		if(new CourseService().ModefyCourse(c)){
			QuestionDialog.ShowDialog(this, "添加提示", "学生信息提交成功");
		}else{
			QuestionDialog.ShowDialog(this, "添加提示", "学生信息提交失败，请重试或联系管理员！");
		}
	}
}
