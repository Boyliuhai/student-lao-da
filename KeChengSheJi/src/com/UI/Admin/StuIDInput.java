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
		//��ʼ���۲����б�
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
		//��������
		JLabel lblStudentId = new JLabel("������ѧ�ţ�");
		txtStudentId = new JTextField(10);
		btnQuery = new JButton("��ѯ");
		
		//���ö�������
		this.setTitle("ѧ������");
		this.setSize(300, 100);
		this.setLocation(650, 300);
		this.setLayout(new GridLayout(4,4));
		this.setResizable(false);
		btnQuery.addActionListener(this);
		this.setLayout(new FlowLayout());
		
		//���
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
				//������Ϣ
				this.notifyAllObserver();
			}
		}
	}

	//��ӹ۲���
	@Override
	public void addObserver(IObserver o) {
		if((o != null) && (!observerList.contains(o))){
			observerList.add(o);
		}
	}
	
	//�Ƴ��۲���
	@Override
	public void deleteObserver(IObserver o) {
		if((o != null) && observerList.contains(o)){
			observerList.remove(o);
		}
	}

	//֪ͨ���еĹ۲���
	@Override
	public void notifyAllObserver() {
		for(int i=0;i<observerList.size();i++){
			IObserver o = observerList.get(i);
			o.update(this);
		}
	}
	
	//�۲��߹۲���¼�
	public void setStuBasicInfo(int studentId){
		this.stu = ss.QueryStudent(studentId);
		//�ж�ѧ����Ϣ�Ƿ����
		if(stu == null){
			QuestionDialog.ShowDialog(this, "��ѯ��ʾ", "ѧ����Ϣ�����ڣ�");
		}
	}
	
}
