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
	//数据表
	JTable tAcademy = null;
	JScrollPane sp1 = null;
	
	public void init() {
		btnDeleteCourse = new JButton("删除学院信息");
		btnModefyCourse = new JButton("修改学院信息");
		
		tAcademy = new JTable();
		sp1 = new JScrollPane();
		
		//设置对象属性
		this.setTitle("学院信息管理");
		this.setSize(600, 480);
		this.setLocation(300, 300);
		this.setResizable(false);
		btnDeleteCourse.addActionListener(this);
		btnModefyCourse.addActionListener(this);
		btnDeleteCourse.setVisible(false);
		btnModefyCourse.setVisible(false);
		this.setLayout(new FlowLayout());
		
		//添加对象
		this.add(sp1);
		this.add(btnDeleteCourse);
		this.add(btnModefyCourse);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnModefyCourse){
			//修改
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
						new QuestionDialog(this, "修改提示", cName + "修改成功！");
					}
				}
			}
		}else if(e.getSource() == btnDeleteCourse){
			if(new QuestionDialog(this, "删除提示", "确定要删除吗").Dialog_Result == QuestionDialog.Dialog_OK){
				//删除
				int selectRow = tAcademy.getSelectedRows().length;
				DefaultTableModel tableModel = (DefaultTableModel) tAcademy.getModel();
				if(selectRow>0){
					int[] selectRows = tAcademy.getSelectedRows();
					for(int i=0;i<selectRows.length;i++){
						int id = Integer.parseInt(tableModel.getValueAt(selectRows[i], 0).toString().trim());
						if(new AcademyService().DeleteAcademy(id)){
							String cName = (String) tableModel.getValueAt(selectRows[i], 1);
							new QuestionDialog(this, "删除提示", cName + "删除成功！");
						}
					}
				}
			}
		}
	}
	
	//设置表格数据
	private void prepare(int academyId){		
		DefaultTableModel dtmAcademy = new AcademyService().getSingleDefaultTableModel(academyId);
		tAcademy.setModel(dtmAcademy);//设置数据源
		sp1.setViewportView(tAcademy);
		tAcademy.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	}

	@Override
	public void update(IObservable o) {
		prepare(Integer.parseInt(AcademyIDInput.getInstance().txtAcademyId.getText()));
		this.setVisible(true);
	}
	
}
