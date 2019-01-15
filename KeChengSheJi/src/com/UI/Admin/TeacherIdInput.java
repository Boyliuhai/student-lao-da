package com.UI.Admin;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import com.DAL.TeacherService;
import com.Dao.Teacher;
import com.IObserver.IObservable;
import com.IObserver.IObserver;

@SuppressWarnings("serial")
public class TeacherIdInput extends JFrame implements ActionListener,IObservable{
		private static TeacherIdInput instance = null;

		private TeacherIdInput(){
			init();
			//初始化观察者列表
			observerList = new ArrayList<IObserver>();
		}
		
		public static synchronized TeacherIdInput getInstance(){
			if(instance == null){
				instance = new TeacherIdInput();
			}
			return instance;
		}
		
		//观察者队列
		ArrayList<IObserver> observerList = null;
		JButton btnQuery = null;
		JTextField txtTeacherId = null;
		Teacher teacher = null;
		
		public void init() {
			//创建对象
			JLabel lblStudentId = new JLabel("请输入教工号：");
			txtTeacherId = new JTextField(10);
			btnQuery = new JButton("查询");
			
			//设置对象属性
			this.setTitle("教师编号输入");
			this.setSize(300, 100);
			this.setLocation(650, 300);
			this.setLayout(new GridLayout(4,4));
			this.setResizable(false);
			btnQuery.addActionListener(this);
			this.setLayout(new FlowLayout());
			
			//添加
			this.add(lblStudentId);
			this.add(txtTeacherId);
			this.add(btnQuery);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnQuery){
				String tid = this.txtTeacherId.getText().trim();
				if(!tid.equals("")){
					int teacherId = Integer.parseInt(this.txtTeacherId.getText().trim());
					setTeacherInfo(teacherId);
					//发布消息
					TeacherInfoForm.getInstance();
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
		public void setTeacherInfo(int teacherId){
			this.teacher = new TeacherService().QueryTeacher(teacherId);
			//判断教师信息是否存在
			if(teacher == null){
				QuestionDialog.ShowDialog(this, "查询提示", "教师信息不存在！");
			}
		}
}
