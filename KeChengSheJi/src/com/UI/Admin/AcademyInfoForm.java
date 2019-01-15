package com.UI.Admin;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.DAL.*;
import com.Dao.Academy;
import com.IObserver.IObservable;
import com.IObserver.IObserver;

@SuppressWarnings("serial")
public class AcademyInfoForm extends JFrame implements ActionListener, IObserver{
	private static AcademyInfoForm instance = null;
	public static AcademyInfoForm getInstance(){
		if(instance == null){
			instance = new AcademyInfoForm();
		}
		return instance;
	}
	private AcademyInfoForm(){
		init();
		AcademyIDInput.getInstance().observerList.add(this);
	}
	
	JLabel lblMessage = null;
	
	JButton btnDeleteCourse, btnModefyCourse = null;
	//���ݱ�
	JTable tAcademy = null;
	JScrollPane sp1 = null;
	
	public void init() {
		btnDeleteCourse = new JButton("ɾ��ѧԺ��Ϣ");
		btnModefyCourse = new JButton("�޸�ѧԺ��Ϣ");
		
		tAcademy = new JTable();
		sp1 = new JScrollPane();
		
		//���ö�������
		this.setTitle("ѧԺ��Ϣ����");
		this.setSize(600, 480);
		this.setLocation(300, 300);
		this.setResizable(false);
		btnDeleteCourse.addActionListener(this);
		btnModefyCourse.addActionListener(this);
		btnDeleteCourse.setVisible(false);
		btnModefyCourse.setVisible(false);
		this.setLayout(new FlowLayout());
		
		//��Ӷ���
		this.add(sp1);
		this.add(btnDeleteCourse);
		this.add(btnModefyCourse);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnModefyCourse){
			//�޸�
			Academy a = new Academy();
			int selectRow = tAcademy.getSelectedRows().length;
			DefaultTableModel tableModel = (DefaultTableModel) tAcademy.getModel();
			if(selectRow>0){
				int[] selectRows = tAcademy.getSelectedRows();
				for(int i=0;i<selectRows.length;i++){
					a.setAcademyId(Integer.parseInt(tableModel.getValueAt(selectRows[i], 0).toString().trim()));
					a.setName((String) tableModel.getValueAt(selectRows[i], 1));
					if(new AcademyService().ModefyAcademy(a)){
						String cName = a.getName();
						new QuestionDialog(this, "�޸���ʾ", cName + "�޸ĳɹ���");
					}
				}
			}
		}else if(e.getSource() == btnDeleteCourse){
			if(new QuestionDialog(this, "ɾ����ʾ", "ȷ��Ҫɾ����").Dialog_Result == QuestionDialog.Dialog_OK){
				//ɾ��
				int selectRow = tAcademy.getSelectedRows().length;
				DefaultTableModel tableModel = (DefaultTableModel) tAcademy.getModel();
				if(selectRow>0){
					int[] selectRows = tAcademy.getSelectedRows();
					for(int i=0;i<selectRows.length;i++){
						int id = Integer.parseInt(tableModel.getValueAt(selectRows[i], 0).toString().trim());
						if(new AcademyService().DeleteAcademy(id)){
							String cName = (String) tableModel.getValueAt(selectRows[i], 1);
							new QuestionDialog(this, "ɾ����ʾ", cName + "ɾ���ɹ���");
						}
					}
				}
			}
		}
	}
	
	//���ñ������
	private void prepare(int academyId){		
		DefaultTableModel dtmAcademy = new AcademyService().getSingleDefaultTableModel(academyId);
		tAcademy.setModel(dtmAcademy);//��������Դ
		sp1.setViewportView(tAcademy);
		tAcademy.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	}

	@Override
	public void update(IObservable o) {
		prepare(Integer.parseInt(AcademyIDInput.getInstance().txtAcademyId.getText()));
		this.setVisible(true);
	}
	
}
