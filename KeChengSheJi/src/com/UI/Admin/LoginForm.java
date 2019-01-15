package com.UI.Admin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.DAL.UserService;

@SuppressWarnings("serial")
public class LoginForm extends JFrame implements ActionListener {
	private static LoginForm instance = null;
	
	private LoginForm(){
		init();
	}
	
	public static LoginForm getInstance(){
		if(instance == null){
			instance = new LoginForm();
		}
		return instance;
	}
	
	/**
	 * 静态变量，保存用户的状态信息
	 */
	public static int currentUserId = 0;
	public static String currentUser = null;
	public static int role = 0;
	UserService us = new UserService();
	
	JButton btnLogin, btnCancel = null;
	JTextField txtUserId, txtPassword = null;
	JRadioButton rbTeacher, rbStudent, rbAdmin  = null;

	public void init(){
		//创建对象
		JPanel p1 = new JPanel(new FlowLayout());
		JLabel lblName = new JLabel("用户编号:");
		txtUserId = new JTextField(10);
		JPanel p2 = new JPanel(new FlowLayout());
		JLabel lblPassword = new JLabel("密        码:");
		txtPassword = new JTextField(10);
		JPanel p3 = new JPanel(new FlowLayout());
		btnLogin = new JButton("登陆");
		btnCancel = new JButton("清空");
		ButtonGroup bg = new ButtonGroup();
		JPanel p4 = new JPanel();
		rbTeacher = new JRadioButton("教师");
		rbStudent = new JRadioButton("学生");
		rbAdmin = new JRadioButton("管理员");
		
		this.setTitle("登录 ");
		this.setSize(300,250);
		this.setLayout(new GridLayout(4,1));
		this.setLocation(new Point(600,200));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		btnLogin.addActionListener(this);
		btnCancel.addActionListener(this);
		txtUserId.setText("95001");
		txtPassword.setText("123456");
		rbTeacher.setSelected(true);
		
		p1.add(lblName);
		p1.add(txtUserId);
		p2.add(lblPassword);
		p2.add(txtPassword);
		p3.add(btnLogin);
		p3.add(btnCancel);
		this.add(p1);
		this.add(p2);
		this.add(p4);
		bg.add(rbAdmin);
		bg.add(rbTeacher);
		bg.add(rbStudent);
		p4.add(rbAdmin);
		p4.add(rbTeacher);
		p4.add(rbStudent);
		this.add(p3);
	}
	
	//事件处理
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLogin){
			//获取用户输入的凭证
			int userId = Integer.parseInt(txtUserId.getText().trim());
			String password = txtPassword.getText().trim();
			//从数据库中读取密码
			if(us.getPassword(userId) != null){
				String ps = us.getPassword(userId).trim();
				currentUserId = userId;//保存当前用户
				currentUser = us.getUserName(userId).trim();
				role = us.getPower(userId);//获取用户权限
				//密码相同
				if(ps!="" && ps.equals(password)){
					//对应用户的权限
					if((rbAdmin.isSelected() && role == 1) || 
							(rbTeacher.isSelected() && role == 2) || 
							(rbStudent.isSelected() && role == 3)){
						this.dispose();
						String rName = "";
						switch(role){
							case 1:
								rName = "管理员";
								break;
							case 2:
								rName = "教师";
								break;
							case 3:
								rName = "学生";
								break;
						}
						new QuestionDialog(this, "提示信息", "登陆成功！ " + currentUser + " " + rName + "，欢迎您！");
					}else{
						new QuestionDialog(this, "提示信息", "您输入的信息有误，请重新输入!");
					}
				}
			}else{
				new QuestionDialog(this, "提示信息", "您输入的信息有误，请重新输入!");
			}
		}else if(e.getSource() == btnCancel){
			txtUserId.setText("");
			txtPassword.setText("");
		}
	}
}
