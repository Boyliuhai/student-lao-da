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
		//��ʼ���۲����б�
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
		//��������
		JLabel lblStudentId = new JLabel("������γ̺ţ�");
		txtCourseId = new JTextField(10);
		btnQuery = new JButton("��ѯ");
		
		//���ö�������
		this.setTitle("�γ̺�����");
		this.setSize(300, 100);
		this.setLocation(650, 300);
		this.setLayout(new GridLayout(4,4));
		this.setResizable(false);
		btnQuery.addActionListener(this);
		this.setLayout(new FlowLayout());
		
		//���
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
				//������Ϣ
				CourseInfoForm.getInstance();
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
	public void setCourseInfo(int courseId){
		this.course = new CourseService().QueryCourse(courseId);
		//�жϿγ���Ϣ�Ƿ����
		if(course == null){
			QuestionDialog.ShowDialog(this, "��ѯ��ʾ", "�γ���Ϣ�����ڣ�");
		}
	}
}
