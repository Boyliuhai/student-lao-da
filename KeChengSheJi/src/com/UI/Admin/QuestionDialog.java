package com.UI.Admin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class QuestionDialog extends JDialog implements ActionListener{

	JButton btnOk, btnClose = null;
	JLabel lblMessage = null;
	
	public static int Dialog_OK = 1;
	public static int Dialog_Cancel = 0;
	public int Dialog_Result = -1;
	
	public QuestionDialog(JFrame f, String s, String content){
		super(f, s, true);
		
		//创建对象
		lblMessage = new JLabel(content);
		btnOk = new JButton("确定");
		btnClose = new JButton("取消");
		JPanel p1 = new JPanel();
		
		//设置属性
		this.setLayout(new FlowLayout());
		this.setSize(300, 100);
		this.setLocation(600, 500);
		btnOk.addActionListener(this);
		btnClose.addActionListener(this);
		
		//添加
		this.add(lblMessage);
		p1.add(btnOk);
		p1.add(btnClose);
		this.add(p1);
		
		//显示
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnOk){
			Dialog_Result = Dialog_OK;
			//this.dispose();
		}else if(e.getSource() == btnClose){
			Dialog_Result = Dialog_Cancel;
		}
		this.dispose();
	}
	
	/**
	 * 显示对话框
	 * @param f
	 * @param title
	 * @param content
	 */
	public static int ShowDialog(JFrame f, String title, String content){
		QuestionDialog qd = new QuestionDialog(f, title, content);
		return qd.Dialog_Result;
	}
}
