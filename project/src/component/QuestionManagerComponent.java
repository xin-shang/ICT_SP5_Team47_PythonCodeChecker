package component;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class QuestionManagerComponent extends Box{
	
	/**
	 * Python Question Edit Page - QuestionManagerComponent
	 * */
	
	// 列表
	private JTable questionTable;
	private Vector <String> title;
	private Vector <Vector> tableData;
	
	//用数据库的话，需要创建数据模型。
	private TableModel tableModel;

	// 代参构造，确定布局。本次是纵向布局
	public QuestionManagerComponent() {
		// 垂直布局
		super(BoxLayout.Y_AXIS);
		
		/**
		 * 组装零件
		 * */
		// title	暂时写了四个，如果只写ID和题目也可以。评分点暂时看不到。点击查看后才能看到
		String[] titleArray = {"ID", "Question-Stems", "Type", "Result"};
		title = new Vector<>();
		for(String t : titleArray) {
			title.add(t);
		}
		
		// 清空
		// body 
		
		
		
		
		// 刷新
		

		// 整合
		tableModel = new DefaultTableModel(tableData, title);
		
		// 整合 & 让questionTable中的内容不可编辑
		questionTable = new JTable (tableModel){
			@Override
			public boolean isCellEditable(int row, int column) {
				return super.isCellEditable(row, column);	
			}
		};

		// 每次选中一行
		questionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// 滚动条 套 列表 （questionTable）
		JScrollPane scrollPane = new JScrollPane(questionTable);
		
		// 添加
		this.add(scrollPane);
		
	}

	// 请求数据。很重要
	public void requestData(){}

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
//
//for(String t : bodyOne1) {
//	body1.add(t);
//}
//for(String t : bodyOne2) {
//	body2.add(t);
//}
//for(String t : bodyOne3) {
//	body3.add(t);
//}
//for(String t : bodyOne4) {
//	body4.add(t);
//}
//for(String t : bodyOne5) {
//	body5.add(t);
//}
//for(String t : bodyOne6) {
//	body6.add(t);
//}
