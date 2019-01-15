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
		//初始化观察者列表
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
		//创建对象
		JLabel lblAcademyId = new JLabel("请输入学院编号：");
		txtAcademyId = new JTextField(10);
		btnQuery = new JButton("查询");
		
		//设置对象属性
		this.setTitle("学院编号输入");
		this.setSize(300, 100);
		this.setLocation(650, 300);
		this.setLayout(new GridLayout(4,4));
		this.setResizable(false);
		btnQuery.addActionListener(this);
		this.setLayout(new FlowLayout());
		
		//添加
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
				//发布消息
				AcademyInfoForm.getInstance();
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
	public void setAcademyInfo(int academyId){
		this.academy = as.QueryAcademy(academyId);
		//判断学生信息是否存在
		if(this.academy == null){
			QuestionDialog.ShowDialog(this, "查询提示", "学院信息不存在！");
		}
	}
	
}
