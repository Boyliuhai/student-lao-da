package com.UI.Admin;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import com.DAL.CourseService;
import com.Dao.CourseInfo;
import com.IObserver.IObservable;
import com.IObserver.IObserver;

@SuppressWarnings("serial")
public class CourseIdInput extends JFrame implements ActionListener,IObservable{
	ArrayList<IObserver> observerList = null;
	private static CourseIdInput instance = null;
	private CourseIdInput(){
		init();
		//初始化观察者列表
		observerList = new ArrayList<IObserver>();
	}
	
	public static synchronized CourseIdInput getInstance(){
		if(instance == null){
			instance = new CourseIdInput();
		}
		return instance;
	}
	JButton btnQuery = null;
	JTextField txtCourseId = null;
	CourseInfo course = null;
	
	public void init() {
		//创建对象
		JLabel lblStudentId = new JLabel("请输入课程号：");
		txtCourseId = new JTextField(10);
		btnQuery = new JButton("查询");
		
		//设置对象属性
		this.setTitle("课程号输入");
		this.setSize(300, 100);
		this.setLocation(650, 300);
		this.setLayout(new GridLayout(4,4));
		this.setResizable(false);
		btnQuery.addActionListener(this);
		this.setLayout(new FlowLayout());
		
		//添加
		this.add(lblStudentId);
		this.add(txtCourseId);
		this.add(btnQuery);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnQuery){
			String cid = this.txtCourseId.getText().trim();
			if(!cid.equals("")){
				int courseId = Integer.parseInt(this.txtCourseId.getText().trim());
				setCourseInfo(courseId);
				//发布消息
				CourseInfoForm.getInstance();
				this.notifyAllObserver();
			}
		}
	}
	//添加观察者
	@Override
	public void addObserver(IObserver o) {
		if((o != null) && (!observerList.contains(o))){
			observerList.add(o);
		}
	}
	
	//移除观察者
	@Override
	public void deleteObserver(IObserver o) {
		if((o != null) && observerList.contains(o)){
			observerList.remove(o);
		}
	}
	//通知所有的观察者
	@Override
	public void notifyAllObserver() {
		for(int i=0;i<observerList.size();i++){
			IObserver o = observerList.get(i);
			o.update(this);
		}
	}
		
	//观察者观察的事件
	public void setCourseInfo(int courseId){
		this.course = new CourseService().QueryCourse(courseId);
		//判断课程信息是否存在
		if(course == null){
			QuestionDialog.ShowDialog(this, "查询提示", "课程信息不存在！");
		}
	}
}
