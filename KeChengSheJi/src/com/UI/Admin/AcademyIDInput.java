package com.UI.Admin;

import java.awt.*;
import com.DAL.*;
import com.Dao.*;
import com.IObserver.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class AcademyIDInput extends JFrame implements ActionListener, IObservable{
	ArrayList<IObserver> observerList = null;
	private static AcademyIDInput instance = null;

	private AcademyIDInput(){
		init();
		//��ʼ���۲����б�
		observerList = new ArrayList<IObserver>();
	}
	
	public static synchronized AcademyIDInput getInstance(){
		if(instance == null){
			instance = new AcademyIDInput();
		}
		return instance;
	}
	
	AcademyService as = new AcademyService();
	Academy academy = null;
	JButton btnQuery = null;
	JTextField txtAcademyId = null;
	
	public void init() {
		//��������
		JLabel lblAcademyId = new JLabel("������ѧԺ��ţ�");
		txtAcademyId = new JTextField(10);
		btnQuery = new JButton("��ѯ");
		
		//���ö�������
		this.setTitle("ѧԺ�������");
		this.setSize(300, 100);
		this.setLocation(650, 300);
		this.setLayout(new GridLayout(4,4));
		this.setResizable(false);
		btnQuery.addActionListener(this);
		this.setLayout(new FlowLayout());
		
		//���
		this.add(lblAcademyId);
		this.add(txtAcademyId);
		this.add(btnQuery);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnQuery){
			String sid = this.txtAcademyId.getText().trim();
			if(!sid.equals("")){
				int academyId = Integer.parseInt(sid);
				setAcademyInfo(academyId);
				//������Ϣ
				AcademyInfoForm.getInstance();
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
	public void setAcademyInfo(int academyId){
		this.academy = as.QueryAcademy(academyId);
		//�ж�ѧ����Ϣ�Ƿ����
		if(this.academy == null){
			QuestionDialog.ShowDialog(this, "��ѯ��ʾ", "ѧԺ��Ϣ�����ڣ�");
		}
	}
	
}
