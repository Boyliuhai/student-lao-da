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
	 * ��̬�����������û���״̬��Ϣ
	 */
	public static int currentUserId = 0;
	public static String currentUser = null;
	public static int role = 0;
	UserService us = new UserService();
	
	JButton btnLogin, btnCancel = null;
	JTextField txtUserId, txtPassword = null;
	JRadioButton rbTeacher, rbStudent, rbAdmin  = null;

	public void init(){
		//��������
		JPanel p1 = new JPanel(new FlowLayout());
		JLabel lblName = new JLabel("�û����:");
		txtUserId = new JTextField(10);
		JPanel p2 = new JPanel(new FlowLayout());
		JLabel lblPassword = new JLabel("��        ��:");
		txtPassword = new JTextField(10);
		JPanel p3 = new JPanel(new FlowLayout());
		btnLogin = new JButton("��½");
		btnCancel = new JButton("���");
		ButtonGroup bg = new ButtonGroup();
		JPanel p4 = new JPanel();
		rbTeacher = new JRadioButton("��ʦ");
		rbStudent = new JRadioButton("ѧ��");
		rbAdmin = new JRadioButton("����Ա");
		
		this.setTitle("��¼ ");
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
	
	//�¼�����
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLogin){
			//��ȡ�û������ƾ֤
			int userId = Integer.parseInt(txtUserId.getText().trim());
			String password = txtPassword.getText().trim();
			//�����ݿ��ж�ȡ����
			if(us.getPassword(userId) != null){
				String ps = us.getPassword(userId).trim();
				currentUserId = userId;//���浱ǰ�û�
				currentUser = us.getUserName(userId).trim();
				role = us.getPower(userId);//��ȡ�û�Ȩ��
				//������ͬ
				if(ps!="" && ps.equals(password)){
					//��Ӧ�û���Ȩ��
					if((rbAdmin.isSelected() && role == 1) || 
							(rbTeacher.isSelected() && role == 2) || 
							(rbStudent.isSelected() && role == 3)){
						this.dispose();
						String rName = "";
						switch(role){
							case 1:
								rName = "����Ա";
								break;
							case 2:
								rName = "��ʦ";
								break;
							case 3:
								rName = "ѧ��";
								break;
						}
						new QuestionDialog(this, "��ʾ��Ϣ", "��½�ɹ��� " + currentUser + " " + rName + "����ӭ����");
					}else{
						new QuestionDialog(this, "��ʾ��Ϣ", "���������Ϣ��������������!");
					}
				}
			}else{
				new QuestionDialog(this, "��ʾ��Ϣ", "���������Ϣ��������������!");
			}
		}else if(e.getSource() == btnCancel){
			txtUserId.setText("");
			txtPassword.setText("");
		}
	}
}
