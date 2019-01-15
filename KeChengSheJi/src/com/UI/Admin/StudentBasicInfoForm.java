package com.UI.Admin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import com.DAL.*;
import com.Dao.StuBasicInfo;
import com.IObserver.IObservable;
import com.IObserver.IObserver;

@SuppressWarnings("serial")
public class StudentBasicInfoForm extends JFrame implements IObserver, ActionListener{
	StudentService ss = new StudentService();
	
	private static StudentBasicInfoForm instance = null;
	public static StudentBasicInfoForm getInstance(){
		if(instance == null){
			instance = new StudentBasicInfoForm();
		}
		return instance;
	}
	private StudentBasicInfoForm(){
		init();
		//����
		StuIDInput.getInstance().addObserver(this);
	}
	
	JRadioButton rbMale, rbFemale = null;
	JTextField txtStudentName, txtBirthday, txtAcademyName, txtIdNo, txtImageURL = null;
	JTextArea txtInstruction = null;
	JLabel lblMessage = null;
	ImageIcon img = null;
	JLabel imageLabel = null;
	JButton btnClear, btnAdd, btnModefy, btnDelete = null;
	
	//ѧ��������,���ڸ��£�ɾ��������
	private int hidden = -1;
	
	public void init() {
		//�������Ա𡢳������ڡ�����Ժϵ������֤�š���Ƭ������
		lblMessage = new JLabel("��ã�" + LoginForm.currentUser);
		JPanel p_name = new JPanel();
		JLabel lblStudentName = new JLabel("������");
		txtStudentName = new JTextField(16);
		
		JPanel p_sex = new JPanel();
		JLabel lblSex = new JLabel("�Ա�");
		ButtonGroup bg = new ButtonGroup();
		rbMale = new JRadioButton("��");
		rbFemale = new JRadioButton("Ů");
		
		JPanel p_birthday = new JPanel();
		JLabel lblBirthday = new JLabel("�������ڣ�");
		txtBirthday = new JTextField(16);
		
		JPanel p_academyName = new JPanel();
		JLabel lblAcademyName = new JLabel("Ժϵ��ţ�");
		txtAcademyName = new JTextField(16);
		
		JPanel p_IdNo = new JPanel();
		JLabel lblIdNo = new JLabel("����֤�ţ�");
		txtIdNo = new JTextField(16);
		
		JPanel p_img1 = new JPanel();
		JPanel p_img2 = new JPanel();
		img = new ImageIcon("");
		imageLabel = new JLabel(img);
		txtImageURL = new JTextField(16);
		
		JPanel p_instruction = new JPanel();
		JLabel lblInstruction = new JLabel("��飺");
		txtInstruction = new JTextArea(12, 40);
		
		btnAdd = new JButton("�ύ");
		btnClear = new JButton("���");
		btnModefy = new JButton("�޸�");
		btnDelete = new JButton("ɾ��");
		
		JPanel p_center = new JPanel();
		JPanel p_east = new JPanel();
		JPanel p_west = new JPanel();
		JPanel p_north = new JPanel();
		JPanel p_south = new JPanel();
		
		//���ö�������
		this.setTitle("����ѧ��������Ϣ");
		this.setSize(600, 500);
		this.setLocation(650, 300);
		this.setLayout(new BorderLayout());
		p_center.setLayout(new FlowLayout(1, 5, 15));
		img.setImage(img.getImage().getScaledInstance(210, 280, Image.SCALE_DEFAULT));//����ͼ���С
		p_west.setLayout(new GridLayout(2, 1));
		p_center.setLayout(new FlowLayout(1, 15, 15));
		this.setResizable(false);
		bg.add(rbFemale);
		bg.add(rbMale);
		p_sex.add(lblSex);
		p_sex.add(rbMale);
		p_sex.add(rbFemale);
		
		//����
		this.getContentPane().add("North", p_north);
		this.getContentPane().add("West", p_west);
		this.getContentPane().add("Center", p_center);
		this.getContentPane().add("East", p_east);
		this.getContentPane().add("South", p_south);
		p_north.add(lblMessage);
		p_name.add(lblStudentName);
		p_name.add(txtStudentName);
		p_sex.add(lblSex);
		p_sex.add(rbFemale);
		p_sex.add(rbMale);
		p_IdNo.add(lblIdNo);
		p_IdNo.add(txtIdNo);
		p_academyName.add(lblAcademyName);
		p_academyName.add(txtAcademyName);
		p_birthday.add(lblBirthday);
		p_birthday.add(txtBirthday);
		p_instruction.add(lblInstruction);
		p_instruction.add(txtInstruction);
		p_center.add(p_name);
		p_center.add(p_sex);
		p_center.add(p_IdNo);
		p_center.add(p_academyName);
		p_center.add(p_birthday);
		p_center.add(p_instruction);
		p_img1.add(imageLabel);
		p_img2.add(txtImageURL);
		p_west.add(p_img1);
		p_west.add(p_img2);
		p_south.add(btnAdd);
		p_south.add(btnModefy);
		p_south.add(btnDelete);
		p_south.add(btnClear);
		
		btnAdd.addActionListener(this);//���Ӽ����¼�
		btnClear.addActionListener(this);
		btnModefy.addActionListener(this);
		btnDelete.addActionListener(this);
	}

	@Override
	public void update(IObservable o) {
		StuBasicInfo stu = ((StuIDInput)o).stu;
		this.hidden = Integer.parseInt(((StuIDInput)o).txtStudentId.getText().trim());
		
		if(stu != null){
			dataBindToForm(stu);
			this.setVisible(true);
		}
	}

	//��������䵽������
	private void dataBindToForm(StuBasicInfo s) {
		this.lblMessage.setText("���" + LoginForm.currentUser);
		this.txtStudentName.setText(s.getName());
		this.txtInstruction.setText(s.getInstruction());
		this.txtBirthday.setText(s.getBirthday());
		this.txtIdNo.setText(s.getIdNo());
		
		String imgURL = s.getImageURL();
		this.txtImageURL.setText(imgURL);
		ImageIcon img2 = new ImageIcon(imgURL);
		this.imageLabel.setIcon(img2);
		//����ͼƬ��С
		img2.setImage(img2.getImage().getScaledInstance(150, 200, Image.SCALE_DEFAULT));
		
		//��ѡ��ť������ʾ
		String sex = s.getSex();
		if(sex.equals("M")){
			this.rbMale.setSelected(true);
			this.rbFemale.setSelected(false);
		}else if(sex.equals("F")){
			this.rbFemale.setSelected(true);
			this.rbMale.setSelected(false);
		}
		this.txtAcademyName.setText(Integer.toString(s.getAcademyId()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnClear){
			clear();
		}else if(e.getSource() == btnAdd){
			StuBasicInfo stu = prepareModelFromForm();
			if(stu != null){
				addStudentInfo(stu);
			}
		}else if(e.getSource() == btnModefy){
			StuBasicInfo stu = prepareModelFromForm();
			if(stu != null){
				modefyStudentInfo(stu);
			}
		}else if(e.getSource() == btnDelete){
			StuBasicInfo stu = prepareModelFromForm();
			if(stu != null){
				deleteStudentInfo(stu);
			}
		}
	}
	
	//�޸�ѧ����Ϣ
	private void modefyStudentInfo(StuBasicInfo stu) {
		if(ss.ModefyStudent(stu)){
			QuestionDialog.ShowDialog(this, "�޸���ʾ", "ѧ����Ϣ�޸ĳɹ�");
		}else{
			QuestionDialog.ShowDialog(this, "�޸���ʾ", "ѧ����Ϣ�޸�ʧ�ܣ������Ի���ϵ����Ա��");
		}
	}

	//ɾ��ѧ����Ϣ
	private void deleteStudentInfo(StuBasicInfo stu) {
		if(QuestionDialog.ShowDialog(this, "ɾ����ʾ", "ȷ��Ҫɾ����") == QuestionDialog.Dialog_OK){
			if(ss.DeleteStudent(stu.getStudentId())){
				QuestionDialog.ShowDialog(this, "ɾ����ʾ", "ѧ����Ϣɾ���ɹ�");
			}else{
				QuestionDialog.ShowDialog(this, "ɾ����ʾ", "ѧ����Ϣɾ��ʧ�ܣ������Ի���ϵ����Ա��");
			}
		}
	}

	//����ѧ����Ϣ
	private void addStudentInfo(StuBasicInfo stu){
		if(ss.AddStudent(stu)){
			QuestionDialog.ShowDialog(this, "������ʾ", "ѧ����Ϣ�ύ�ɹ�");
		}else{
			QuestionDialog.ShowDialog(this, "������ʾ", "ѧ����Ϣ�ύʧ�ܣ������Ի���ϵ����Ա��");
		}
	}

	//��ҳ���ϵ���Ϣ��װ��ѧ��ʵ����
	private StuBasicInfo prepareModelFromForm(){
		StuBasicInfo stu = null;
		if(!this.txtAcademyName.getText().equals("")){
			stu = new StuBasicInfo();
			stu.setStudentId(hidden);
			stu.setAcademyId(Integer.parseInt(this.txtAcademyName.getText()));
			stu.setName(this.txtStudentName.getText());
			stu.setSex(this.rbFemale.isSelected()?"F":"M");
			stu.setImageURL(this.txtImageURL.getText());
			stu.setInstruction(this.txtInstruction.getText());
			stu.setBirthday(this.txtBirthday.getText());
			stu.setIdNo(this.txtIdNo.getText());
		}
		return stu;
	}
	
	//��ձ���
	public void clear(){
		this.txtAcademyName.setText("");
		this.txtBirthday.setText("");
		this.txtIdNo.setText("");
		this.txtImageURL.setText("");
		this.txtInstruction.setText("");
		this.txtStudentName.setText("");
		this.imageLabel.setIcon(null);
	}
}