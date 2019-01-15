package com.UI.Admin;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.DAL.UserService;

@SuppressWarnings("serial")
public class ModefyPassword extends JFrame implements ActionListener{
	UserService us = new UserService();
	
	JLabel lblOld,lblNew2 , lblNew;
	JTextField txtOld, txtNew, txtNew2;
	JButton btnModefy = null;
	
	public void init(){
		//创建对象
		lblOld = new JLabel("旧密码：");
		lblNew = new JLabel("新密码：");
		lblNew2 = new JLabel("确认新密码：");
		txtOld = new JTextField(10);
		txtNew = new JTextField(10);
		txtNew2 = new JTextField(10);
		btnModefy = new JButton("修改");
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		
		//设置属性
		this.setLayout(new GridLayout(4,1));
		this.setSize(400, 300);
		this.setLocation(600, 500);
		this.setTitle("修改密码");
		
		//添加
		p1.add(lblOld);
		p1.add(txtOld);
		p2.add(lblNew);
		p2.add(txtNew);
		p3.add(lblNew2);
		p3.add(txtNew2);
		p4.add(btnModefy);
		this.add(p1);
		this.add(p2);
		this.add(p3);
		this.add(p4);
		btnModefy.addActionListener(this);
	}
	
	public ModefyPassword(){
		init();
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnModefy){
			int userId = LoginForm.currentUserId;
			//从数据库中获取旧的用户密码
			String oldPass = us.getPassword(userId).trim();
			String newPass = this.txtNew.getText().trim();
			String newPass2 = this.txtNew2.getText().trim();
			if(!newPass2.equals(newPass)){
				new QuestionDialog(this, "密码修改提示", "新密码和确认新密码不相同，请重新输入！");
			}else if(oldPass!=""){
				if(us.updatePassWord(userId, oldPass, newPass)){
					new QuestionDialog(this, "密码修改提示", "密码修改成功");
				}else{
					new QuestionDialog(this, "密码修改提示", "旧的用户密码有误，请重试！");
				}
			}
		}
	}
	
}
