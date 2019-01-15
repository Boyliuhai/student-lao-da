package com.UI.Admin;

import java.awt.*;
import com.DAL.*;
import com.Dao.*;
import com.IObserver.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class StuIDInput extends JFrame implements ActionListener, IObservable{
	private static StuIDInput instance = null;
	ArrayList<IObserver> observerList = null;
	StudentService ss = new StudentService();

	private StuIDInput(){
		init();
		//初始化观察者列表
		observerList = new ArrayList<IObserver>();
	}
	
	public static synchronized StuIDInput getInstance(){
		if(instance == null){
			instance = new StuIDInput();
		}
		return instance;
	}
	
	JButton btnQuery = null;
	JTextField txtStudentId = null;
	StuBasicInfo stu = null;
	
	public void init() {
		//创建对象
		JLabel lblStudentId = new JLabel("请输入学号：");
		txtStudentId = new JTextField(10);
		btnQuery = new JButton("查询");
		
		//设置对象属性
		this.setTitle("学号输入");
		this.setSize(300, 100);
		this.setLocation(650, 300);
		this.setLayout(new GridLayout(4,4));
		this.setResizable(false);
		btnQuery.addActionListener(this);
		this.setLayout(new FlowLayout());
		
		//添加
		this.add(lblStudentId);
		this.add(txtStudentId);
		this.add(btnQuery);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnQuery){
			String sid = this.txtStudentId.getText().trim();
			if(!sid.equals("")){
				int stuId = Integer.parseInt(this.txtStudentId.getText().trim());
				setStuBasicInfo(stuId);
				//发布消息
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
	public void setStuBasicInfo(int studentId){
		this.stu = ss.QueryStudent(studentId);
		//判断学生信息是否存在
		if(stu == null){
			QuestionDialog.ShowDialog(this, "查询提示", "学生信息不存在！");
		}
	}
	
}
