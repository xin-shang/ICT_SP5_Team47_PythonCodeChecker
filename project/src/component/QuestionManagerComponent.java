package component;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import view.PythonQuestionEditPage;

public class QuestionManagerComponent extends Box{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Python Question Edit Page - QuestionManagerComponent
	 * */
	
	/*——————————————————————————以下为测试用的数据，连接数据库后删除————————————————————————*/
	/*————————————————————————————————————————————————————————————————————————————————*/
	// 创建一维数组，存储标题
	Object [] titles = {"ID", "Type", "Question-Stems", "Result"};
		
	// 创建二维数组，储存数据内容
	Object [][] data = {
			{"01", "Print", "Print'Hello World'", "Hello World"},
			{"02", "Print", "Print'11'", "11"},
			{"03", "Loop", "for loop 1-5", "1\n2\n3\n4\n5"},
			{"04", "List", "List[1,2,3,4,5] print List", "1\n2\n3\n4\n5"},
			{"05", "Random", "Please write a program to output a random even number between 0 and 10 inclusive using random module and list comprehension.", "----"},
			{"06", "Calculate", "Write a program which accepts a sequence of comma separated 4 digit binary numbers as its input and then check whether they are divisible by 5 or not. The numbers that are divisible by 5 are to be printed in a comma separated sequence.'", "----"},
	};
	/*————————————————————————————————————————————————————————————————————————————————*/
	/*————————————————————————————————————————————————————————————————————————————————*/

	
	// 表格
	public static JTable questionTable;
	
	//用数据库的话，需要创建数据模型。
	public static DefaultTableModel tableModel;
	
	// 创建集合		操作集合比操作数组容易
	private static Vector titlesVector = new Vector(); // 存储标题
	private static Vector<Vector> dataVector = new Vector<>(); // 存储数据
	
	
	JPanel buttonPanel;
	JButton addQuestion, deleteQuestion, changeQuestion, showQuestion;
	
	public static int selectedRow = 0;

	// 代参构造，确定布局。本次是纵向布局
	public QuestionManagerComponent() {
		// 垂直布局
		super(BoxLayout.Y_AXIS);
		
		/**
		 * 组装零件
		 * */
		// 现在写的内容都是用来测试程序功能的。接上数据后一部分需要重写
		// title	暂时写了四个，如果只写ID和题目也可以。评分点暂时看不到。点击查看后才能看到
		// body		暂时写了6个，用来测试功能。
		/*——————————————————————————以下为测试用的数据，连接数据库后删除————————————————————————*/
		/*————————————————————————————————————————————————————————————————————————————————*/
		// 写入数据
		for (int i = 0; i < titles.length; i++) {
			titlesVector.add(titles[i]);
		}
		for (int i = 0; i< data.length; i++) {
			Vector t = new Vector<>();					// <Vector> 用来接收二维数组中第二个维度的信息
			for (int j = 0; j< data[i].length; j++) {	// data[i].length 用来录入每个大数组中子数组的信息
				t.add(data[i][j]);
			}
			dataVector.add(t);							// 依次把第二维加入一维中
		}
		/*————————————————————————————————————————————————————————————————————————————————*/
		/*————————————————————————————————————————————————————————————————————————————————*/
		
		// 清空
		
		// 刷新

		// 整合
		tableModel = new DefaultTableModel(dataVector, titlesVector);
		
		// 整合 & 让questionTable中的内容不可编辑
		questionTable = new JTable (tableModel){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;	
				/*return super.isCellEditable(row, column)*/
			}
		};

		// 每次选中一行
		questionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		/*——————————————————————————以下为测试用的数据，连接数据库后删除————————————————————————*/
		/*————————————————————————————————————————————————————————————————————————————————*/
		// 设置列宽
		// 获取列
		TableColumn column1 = questionTable.getColumn(titles[0]);
		// 设置列宽的最大像素
		column1.setMaxWidth(30);
		column1.setMinWidth(30);
		// 获取列
		TableColumn column2 = questionTable.getColumn(titles[1]);
		// 设置列宽的最大像素
		column2.setMaxWidth(90);
		column1.setMinWidth(30);
		// 获取列
		TableColumn column3 = questionTable.getColumn(titles[2]);
		// 设置列宽的最大像素
		column3.setMinWidth(620);
		/*————————————————————————————————————————————————————————————————————————————————*/
		/*————————————————————————————————————————————————————————————————————————————————*/
		
		// 滚动条 套 列表 （questionTable）
		JScrollPane scrollPane = new JScrollPane(questionTable);
		
		
		/*——————————————————————————以下为测试用的数据，连接数据库后删除————————————————————————*/
		/*————————————————————————————————————————————————————————————————————————————————*/
		// 按钮
		buttonPanel = new JPanel();
		buttonPanel.setMaximumSize(new Dimension(800,80));
		
		addQuestion = new JButton("Add Question");
		deleteQuestion = new JButton("Delets Question");
		changeQuestion = new JButton("Change Question");
		showQuestion = new JButton("Show Details");
		
		Button_Item_AddQuestion(addQuestion);
		Button_Item_DeleteQuestion(deleteQuestion);
		Button_Item_ChangeQuestion(changeQuestion);
		Button_Item_ShowQuestion(showQuestion);
		
		buttonPanel.add(addQuestion);
		buttonPanel.add(deleteQuestion);
		buttonPanel.add(changeQuestion);
		buttonPanel.add(showQuestion);
		
		/*————————————————————————————————————————————————————————————————————————————————*/
		/*————————————————————————————————————————————————————————————————————————————————*/

		
		// 添加
		this.add(scrollPane);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Button 监听  // 本来打算使用菜单按键来做增加，删除，修改的。调用问题暂时解决不了。最后很可能用按钮。
	 * */
	// 按钮监听 -- 添加问题按键监听
	private void Button_Item_AddQuestion (JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 项目中可以直接连接弹窗，然后在弹窗中输入信息。在点击提交时，判定，赋值，然后用addRow()方法。
				tableModel.addRow(new Object[] {"07","Print","Print'---'","---"});
				System.out.println("-- The Add Manu Button is Working --");
			}
		});
	}
	
	//按钮监听 -- 删除问题按键监听
	private void Button_Item_DeleteQuestion (JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 
				setSelectedRow(questionTable.getSelectedRow());
//				int selectedRow = questionTable.getSelectedRow();
				tableModel.removeRow(getSelectedRow());
				System.out.println("-- The Delete Manu Button is Working --");
			}
		});
	}
	
	// 按钮监听 -- 修改问题按键监听
	private void Button_Item_ChangeQuestion (JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 
				
				System.out.println("-- The Change Manu Button is Working --");
			}
		});
	}
	
	//按钮监听 -- 展示问题按键监听
	public void Button_Item_ShowQuestion (JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 
				setSelectedRow(questionTable.getSelectedRow());
				PythonQuestionEditPage.splitPane.setRightComponent(new QuestionDetailsComponent());
				System.out.println("-- The Delete Manu Button is Working --");
			}
		});
	}
	
	/**
	 * 内容获取
	 * */
	// 请求数据。很重要:get和post
	public void requestData(){}
	
	public static int getSelectedRow() {
		return selectedRow;
	}

	public static void setSelectedRow(int selectedRow) {
		QuestionManagerComponent.selectedRow = selectedRow;
	}
	
	// 某个单元格中的内容
	public static Object getValueAt(int rowIndex, int columnIndex) {
		return dataVector.get(rowIndex).get(columnIndex);		// get(int)	返回位于Vector中指定位置的元素。
	}

}













///**
//* 测试
//* */
//// body
//Vector <String> body1 = new Vector<>();
//Vector <String> body2 = new Vector<>();
//Vector <String> body3 = new Vector<>();
//Vector <String> body4 = new Vector<>();
//Vector <String> body5 = new Vector<>();
//Vector <String> body6 = new Vector<>();
//String[] bodyOne1 = {"01", "Print", "Print'Hello World'", "Hello World"};
//String[] bodyOne2 = {"02", "Print", "Print'11'", "11"};
//String[] bodyOne3 = {"03", "Loop", "for loop 1-5", "1\n2\n3\n4\n5"};
//String[] bodyOne4 = {"04", "List, Print", "List[1,2,3,4,5] print List", "1\n2\n3\n4\n5"};
//String[] bodyOne5 = {"05", "List, Random", "Please write a program to output a random even number between 0 and 10 inclusive using random module and list comprehension.", "----"};
//String[] bodyOne6 = {"06", "Calculate", "Write a program which accepts a sequence of comma separated 4 digit binary numbers as its input and then check whether they are divisible by 5 or not. The numbers that are divisible by 5 are to be printed in a comma separated sequence.'", "----"};
