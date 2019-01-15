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
			//��ʼ���۲����б�
			observerList = new ArrayList<IObserver>();
		}
		
		public static synchronized TeacherIdInput getInstance(){
			if(instance == null){
				instance = new TeacherIdInput();
			}
			return instance;
		}
		
		//�۲��߶���
		ArrayList<IObserver> observerList = null;
		JButton btnQuery = null;
		JTextField txtTeacherId = null;
		Teacher teacher = null;
		
		public void init() {
			//��������
			JLabel lblStudentId = new JLabel("������̹��ţ�");
			txtTeacherId = new JTextField(10);
			btnQuery = new JButton("��ѯ");
			
			//���ö�������
			this.setTitle("��ʦ�������");
			this.setSize(300, 100);
			this.setLocation(650, 300);
			this.setLayout(new GridLayout(4,4));
			this.setResizable(false);
			btnQuery.addActionListener(this);
			this.setLayout(new FlowLayout());
			
			//���
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
					//������Ϣ
					TeacherInfoForm.getInstance();
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
		public void setTeacherInfo(int teacherId){
			this.teacher = new TeacherService().QueryTeacher(teacherId);
			//�жϽ�ʦ��Ϣ�Ƿ����
			if(teacher == null){
				QuestionDialog.ShowDialog(this, "��ѯ��ʾ", "��ʦ��Ϣ�����ڣ�");
			}
		}
}
