package component;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import JDBC.QNS.GroupTable.studentQns_T;
import methodAndTool.MessagePrintString;
import methodAndTool.WriteAndRead;
import view.PythonCodeCheckerPage;

public class ChooseQuestionComponent extends Box {

	/**
	 * Python Code Checker Page - ChooseQuestionComponent
	 */
	Date dateNow = new Date();
	studentQns_T DIO = null;

	static MessagePrintString MPS = new MessagePrintString();

	// 创建一维数组，存储标题
	Object[] titlesChooseQuestion = { "ID", "Question-Stems" };

	// 表格
	public static JTable chooseQuestionTable;

	// 创建集合 操作集合比操作数组容易
	private static Vector<Object> titlesChooseQuestion_Table = new Vector<Object>(); // 存储标题
	private static Vector<Vector<Object>> dataChooseQuestion_Table = new Vector<>(); // 存储数据

	// 是不是可以在下面写一个左箭头一个右箭头？用来切换题目？

	// 用数据库的话，需要创建数据模型。
	public static DefaultTableModel chooseQuestionTableModel;
	private static int selectedRow = -1;
	public static int rowNum;
	private JButton showQuestionButton, nextButton, previousButton;

	public ChooseQuestionComponent(studentQns_T dio) {
		super(BoxLayout.Y_AXIS);

		this.DIO = dio;

		/**
		 * 组装零件
		 */
		// 清空原有数据，保证列表中无内容
		titlesChooseQuestion_Table.clear();
		dataChooseQuestion_Table.clear();

		// 写入数据
		for (int i = 0; i < titlesChooseQuestion.length; i++) {
			titlesChooseQuestion_Table.add(titlesChooseQuestion[i]);
		}

		for (int i = 0; i < studentQns_T.getDblength(); i++) {
			Vector<Object> t = new Vector<Object>();
			for (int j = 0; j < 2/* DIO.getRowlength() */; j++) {
				t.add(DIO.getData(i, j));
			}
			dataChooseQuestion_Table.add(t);
		}

		// 整合
		chooseQuestionTableModel = new DefaultTableModel(dataChooseQuestion_Table, titlesChooseQuestion_Table);

		// 整合 & 让questionTable中的内容不可编辑
		chooseQuestionTable = new JTable(chooseQuestionTableModel) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
				/* return super.isCellEditable(row, column) */
			}
		};

		// 每次选中一行
		chooseQuestionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// 设置列宽
		// 获取列
		TableColumn column1 = chooseQuestionTable.getColumn(titlesChooseQuestion[0]);
		// 设置列宽的最大像素
		column1.setMaxWidth(50);
		column1.setMinWidth(50);

		// 获取列
		TableColumn column2 = chooseQuestionTable.getColumn(titlesChooseQuestion[1]);
		column2.setMaxWidth(0);
		column2.setMinWidth(0);
		column2.setPreferredWidth(0);
		column2.setWidth(0);

		// 滚动条 套 列表 （questionTable）
		JScrollPane scrollPane = new JScrollPane(chooseQuestionTable);

		JPanel buttonStudentPanel = new JPanel();
		buttonStudentPanel.setMaximumSize(new Dimension(800, 80));

		previousButton = new JButton("<");
		Button_Item_PreviousQuestion(previousButton);
		// buttonStudentPanel.add(previousButton);

		showQuestionButton = new JButton("Show");
		Button_Item_ShowQuestionButton(showQuestionButton);
		buttonStudentPanel.add(showQuestionButton);

		nextButton = new JButton(">");
		Button_Item_NextQuestion(nextButton);
		// buttonStudentPanel.add(nextButton);

		this.add(scrollPane);
		this.add(buttonStudentPanel, BorderLayout.SOUTH);

	}

	/**
	 * 按键监听
	 */
	// 按钮监听 -- 展示问题
	public void Button_Item_ShowQuestionButton(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//
				rowNum = ChooseQuestionComponent.getSelectedRow();
				setSelectedRow(chooseQuestionTable.getSelectedRow());
				StudentWorkingComponent
						.setQuestionString(WriteAndRead.readQuestion(getValueAt_Table(getSelectedRow(), 1)));
				PythonCodeCheckerPage.splitPane.setLeftComponent(new StudentWorkingComponent());
				MPS.QuestionToString(StudentWorkingComponent.terminalArea);
				MPS.EditingToString(StudentWorkingComponent.terminalArea);
				System.out.println("-- The Show Button is Working --");

			}
		});
	}

	// Previous Question
        public static void Button_Item_PreviousQuestion(Object button) {
                ((AbstractButton) button).addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                               
				if (getSelectedRow() <= 0) {
					JFrame jf = new JFrame();
					JOptionPane.showMessageDialog(jf,
							"This is the first Question");
				}
				else {
					setSelectedRow(chooseQuestionTable.getSelectedRow() - 1);
					MPS.QuestionToString(StudentWorkingComponent.terminalArea);
					MPS.EditingToString(StudentWorkingComponent.terminalArea);
					StudentWorkingComponent.setQuestionString(
							WriteAndRead.readQuestion(getValueAt_Table(getSelectedRow(), 1)));
					PythonCodeCheckerPage.splitPane.setLeftComponent(new StudentWorkingComponent());
					chooseQuestionTable.setRowSelectionInterval(getSelectedRow(), getSelectedRow());
					chooseQuestionTable.scrollRectToVisible(chooseQuestionTable.getCellRect(getSelectedRow(), 0, true));
					System.out.println("-- The Previous Question Button is Working --");
				}
				
                        }
                });
        }

	// Next Question
        public static void Button_Item_NextQuestion(Object button) {
                ((AbstractButton) button).addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
				if(getSelectedRow() < studentQns_T.getDblength() - 1) {
					setSelectedRow(chooseQuestionTable.getSelectedRow() + 1);
					MPS.QuestionToString(StudentWorkingComponent.terminalArea);
					MPS.EditingToString(StudentWorkingComponent.terminalArea);
					StudentWorkingComponent.setQuestionString(
							WriteAndRead.readQuestion(getValueAt_Table(getSelectedRow(), 1)));
					PythonCodeCheckerPage.splitPane.setLeftComponent(new StudentWorkingComponent());
					chooseQuestionTable.setRowSelectionInterval(getSelectedRow(), getSelectedRow());
					chooseQuestionTable.scrollRectToVisible(chooseQuestionTable.getCellRect(getSelectedRow(), 0, true));
					System.out.println("-- The Next Question Button is Working --");
				}
				else {
					JFrame jf = new JFrame();
					JOptionPane.showMessageDialog(jf,
							"This is the last Question");
				}
                                
                        }
                });
        }



	/**
	 * 获取数据
	 */
	public static int getSelectedRow() {
		return selectedRow;
	}

	public static void setSelectedRow(int selectedRow) {
		ChooseQuestionComponent.selectedRow = selectedRow;
	}

	// 某个单元格中的内容 ID，Question 用这个
	public static Object getValueAt_Table(int rowIndex, int columnIndex) {
		return dataChooseQuestion_Table.get(rowIndex).get(columnIndex); // get(int) 返回位于Vector中指定位置的元素。
	}

	public static int getRow() {
		return rowNum;
	}

	public static String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String timestr = sdf.format(new Date());
		return timestr;
	}

}
