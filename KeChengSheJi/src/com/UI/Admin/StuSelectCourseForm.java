package com.UI.Admin;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.DAL.*;

@SuppressWarnings("serial")
public class StuSelectCourseForm extends JFrame implements ActionListener{
	CourseService cs = new CourseService();
	StudentService ss = new StudentService();
	ChooseCourseService css = new ChooseCourseService();
	
	public StuSelectCourseForm(){
		init();
		prepare(LoginForm.currentUserId);
		this.setVisible(true);
	}
	
	JLabel lblMessage, lblMessage1, lblMessage2 = null;
	
	JButton btnAddCourse, btnDeleteCourse = null;
	//���ݱ�
	JTable tSelected, tSelecting = null;
	JScrollPane sp1, sp2 = null;
	
	public void init() {
		JPanel p_center_left = new JPanel();
		JPanel p_center_right = new JPanel();
		lblMessage1 = new JLabel("��ѡ�γ�");
		lblMessage2 = new JLabel("δѡ�γ�");
		btnAddCourse = new JButton("ѡ��");
		btnDeleteCourse = new JButton("��ѡ");
		
		tSelected = new JTable();
		sp1 = new JScrollPane();
		tSelecting = new JTable();
		sp2 = new JScrollPane();
		
		//���ö�������
		this.setTitle("ѧ��ѡ��");
		this.setSize(1100, 550);
		this.setLocation(300, 300);
		this.setResizable(false);
		btnAddCourse.addActionListener(this);
		btnDeleteCourse.addActionListener(this);
		this.setLayout(new FlowLayout());
		
		//��Ӷ���
		p_center_left.add(lblMessage1);
		p_center_left.add(sp1);
		p_center_right.add(lblMessage2);
		p_center_right.add(sp2);
		this.add(p_center_left);
		this.add(p_center_right);
		this.add(btnAddCourse);
		this.add(btnDeleteCourse);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAddCourse){
			//ѡ��
			int selectRow = tSelecting.getSelectedRows().length;
			DefaultTableModel tableModel = (DefaultTableModel) tSelecting.getModel();
			if(selectRow>0){
				int[] selectRows = tSelecting.getSelectedRows();
				for(int i=0;i<selectRows.length;i++){
					int cId = (Integer) tableModel.getValueAt(selectRows[i], 0);
					int stuId = (LoginForm.currentUserId);
					if(css.ChooseCourse(stuId, cId)){
						String cName = cs.QueryCourse(cId).getCourseName();
						new QuestionDialog(this, "ѡ����ʾ", cName + "ѡ�γɹ���");
					}
				}
			}
			//���±��
			prepare(LoginForm.currentUserId);
		}else if(e.getSource() == btnDeleteCourse){
			//��ѡ
			int selectRow = tSelected.getSelectedRows().length;
			DefaultTableModel tableModel = (DefaultTableModel) tSelected.getModel();
			if(selectRow>0){
				int[] selectRows = tSelected.getSelectedRows();
				for(int i=0;i<selectRows.length;i++){
					int cId = (Integer) tableModel.getValueAt(selectRows[i], 0);
					int stuId = LoginForm.currentUserId;
					if(css.QuitCourse(stuId, cId)){
						String cName = cs.QueryCourse(cId).getCourseName();
						new QuestionDialog(this, "ѡ����ʾ", cName + "��ѡ�ɹ���");
					}
				}
			}
			prepare(LoginForm.currentUserId);
		}
	}
	
	//���ñ������
	private void prepare(int stuId){
		DBUtil db = new DBUtil();
		DefaultTableModel dtmSelected = db.getModel(db.queryByProc("exec proc_StuSelectedCourseInfo ?", stuId));
		tSelected.setModel(dtmSelected);
		sp1.setViewportView(tSelected);
		tSelected.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		DefaultTableModel dtmSelecting = db.getModel(db.queryByProc("exec proc_StuSelectingCourseInfo ?", stuId));
		tSelecting.setModel(dtmSelecting);//��������Դ
		sp2.setViewportView(tSelecting);
		tSelecting.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	}
	
}
