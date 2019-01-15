package com.UI.Admin;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.DAL.*;
import com.Dao.Teacher;
import com.IObserver.IObservable;
import com.IObserver.IObserver;

@SuppressWarnings("serial")
public class TeacherInfoForm extends JFrame implements IObserver, ActionListener{
	private static TeacherInfoForm instance = null;
	
	public static TeacherInfoForm getInstance(){
		if(instance == null){
			instance = new TeacherInfoForm();
		}
		return instance;
	}
	
	private TeacherInfoForm(){
		init();
		//����
		TeacherIdInput.getInstance().addObserver(this);
	}
	
	TeacherService ts = new TeacherService();
	
	JButton btnClear, btnAdd, btnModefy, btnDelete;
	
	JLabel lblTeacherId = new JLabel("��ʦ���");
	JTextField txtTeacherId = new JTextField(14);
	JLabel lblTeacherName = new JLabel("��ʦ����");
	JTextField txtTeacherName = new JTextField(14);
	JLabel lblAcademyId = new JLabel("ѧԺ���");
	JTextField txtAcademyId = new JTextField(14);
	JLabel lblSex = new JLabel("�Ա�");
	JTextField txtSex = new JTextField(14);
	
	public void init() {
		btnAdd = new JButton("�ύ");
		btnClear = new JButton("���");
		btnModefy = new JButton("�޸�");
		btnDelete = new JButton("ɾ��");
		
		//���ö�������
		this.setTitle("��ʦ��Ϣ����");
		this.setSize(250, 250);
		this.setLocation(650, 300);
		this.setLayout(new FlowLayout());
		
		//���
		this.add(lblTeacherId);
		this.add(txtTeacherId);
		this.add(lblTeacherName);
		this.add(txtTeacherName);
		this.add(lblAcademyId);
		this.add(txtAcademyId);
		this.add(lblSex);
		this.add(txtSex);
		this.add(btnAdd);
		this.add(btnModefy);
		this.add(btnDelete);
		this.add(btnClear);
		
		btnAdd.addActionListener(this);//��Ӽ����¼�
		btnClear.addActionListener(this);
		btnModefy.addActionListener(this);
		btnDelete.addActionListener(this);
	}

	@Override
	public void update(IObservable o) {
		Teacher teacher = ((TeacherIdInput)o).teacher;
		if(teacher != null){
			this.txtAcademyId.setText(Integer.toString(teacher.getAcademyId()));
			this.txtSex.setText(teacher.getSex());
			this.txtTeacherId.setText(Integer.toString(teacher.getId()));
			this.txtTeacherName.setText(teacher.getName());
			this.setVisible(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnClear){
			clear();
		}else if(e.getSource() == btnAdd){
			addTeacherInfo(TeacherIdInput.getInstance().teacher);
		}else if(e.getSource() == btnModefy){
			modefyTeacherInfo(TeacherIdInput.getInstance().teacher);
		}else if(e.getSource() == btnDelete){
			deleteStudentInfo(TeacherIdInput.getInstance().teacher);
		}
	}
	
	//��տؼ�
	private void clear() {
		this.txtAcademyId.setText("");
		this.txtTeacherId.setText("");
		this.txtSex.setText("");
		this.txtTeacherName.setText("");
	}

	//�޸�ѧ����Ϣ
	private void modefyTeacherInfo(Teacher teacher) {
		if(ts.ModefyTeacher(teacher)){
			QuestionDialog.ShowDialog(this, "�޸���ʾ", "��ʦ��Ϣ�޸ĳɹ�");
		}else{
			QuestionDialog.ShowDialog(this, "�޸���ʾ", "��ʦ��Ϣ�޸�ʧ�ܣ������Ի���ϵ����Ա��");
		}
	}

	//ɾ��ѧ����Ϣ
	private void deleteStudentInfo(Teacher teacher) {
		if(QuestionDialog.ShowDialog(this, "ɾ����ʾ", "ȷ��Ҫɾ����") == QuestionDialog.Dialog_OK){
			if(ts.DeleteTeacher(teacher.getId())){
				QuestionDialog.ShowDialog(this, "ɾ����ʾ", "��ʦ��Ϣɾ���ɹ�");
			}else{
				QuestionDialog.ShowDialog(this, "ɾ����ʾ", "��ʦ��Ϣɾ��ʧ�ܣ������Ի���ϵ����Ա��");
			}
		}
	}

	//��ӿγ���Ϣ
	private void addTeacherInfo(Teacher teacher){
		if(ts.AddTeacher(teacher)){
			QuestionDialog.ShowDialog(this, "�����ʾ", "��ʦ��Ϣ�ύ�ɹ�");
		}else{
			QuestionDialog.ShowDialog(this, "�����ʾ", "��ʦ��Ϣ�ύʧ�ܣ������Ի���ϵ����Ա��");
		}
	}
}
