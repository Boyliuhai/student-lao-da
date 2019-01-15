package com.UI.Admin;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import com.DAL.AcademyService;
import com.Dao.Academy;

@SuppressWarnings("serial")
public class AcademyInfoInput extends JFrame implements ActionListener {
	JLabel lblAcademyName = new JLabel("学院名称");
	JTextField txtAcademyName = new JTextField(14);
	JLabel lblAcademyId = new JLabel("学院编号");
	JTextField txtAcademyId = new JTextField(14);
	JButton btnAdd = new JButton("添加");
	JButton btnClear = new JButton("清空");
	
	public AcademyInfoInput(){
		init();
		this.setVisible(true);
	}
	
	private void init(){
		this.setTitle("录入学院信息");
		this.add(lblAcademyId);
		this.add(txtAcademyId);
		this.add(lblAcademyName);
		this.add(txtAcademyName);
		this.add(btnAdd);
		this.add(btnClear);
		this.setLayout(new FlowLayout());
		this.setSize(200,200);
		this.setLocation(600, 400);
		this.btnAdd.addActionListener(this);
		this.btnClear.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAdd){
			Academy a = new Academy();
			String id = this.txtAcademyId.getText();
			if(!id.equals("")){
				a.setAcademyId(Integer.parseInt(id));
				a.setName(this.txtAcademyName.getText().trim());
				if(new AcademyService().AddAcademy(a)){
					new QuestionDialog(this, "添加提示", "学院信息添加成功");
				}else{
					new QuestionDialog(this, "添加提示", "学院信息添加失败");
				}
			}
		}else if(e.getSource() == btnClear){
			this.txtAcademyId.setText("");
			this.txtAcademyName.setText("");
		}
	}
	
}
