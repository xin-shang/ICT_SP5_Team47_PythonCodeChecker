package component;

import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import methodAndTool.WriteAndRead;
import view.PythonQuestionEditPage;
import JDBC.keywordAlternative_T;

public class KeywordManagerComponent extends Box {

	/**
	 * Python Question Edit Page - KeyWordManagerComponent
	 */

	WriteAndRead WAR = new WriteAndRead();
	keywordAlternative_T QKC = new keywordAlternative_T();

	// JFrame frameKMC = null;

	// Create a one-dimensional array to store the titles 创建一维数组，存储标题
	static Object[] titles = { "ID", "Keyword", "Score" };

	// Creating Collections Manipulating collections is easier than manipulating
	// arrays 创建集合 操作集合比操作数组容易
	private Vector<Object> title = new Vector<Object>(); // Store the title 存储标题
	private static Vector<Vector> data = new Vector<>(); // Store the data 存储数据
	private static int selectedRow = 0;
	private static int[] selectedRows = {};

	//
	JPanel buttonPanel;
	JButton addKeyword, deleteKeyword, ChangeKeyword;

	// table
	public static JTable keywordTable;

	// With a database, you need to create a data model.
	public static DefaultTableModel tableModel;

	public KeywordManagerComponent() {
		super(BoxLayout.Y_AXIS);

		//
		PythonQuestionEditPage.splitPane.setDividerLocation(500); // 左右分屏初始位置
		PythonQuestionEditPage.splitPane.setDividerSize(2); // 分割线宽度

		// Clear the original data to ensure that there is no content in the list
		// 清空原有数据，保证列表中无内容
		data.clear();

		// read-in data 写入数据
		for (int i = 0; i < titles.length; i++) {
			title.add(titles[i]);
		}

		for (int i = 0; i < QKC.getDblength(); i++) {
			Vector<Object> t = new Vector<Object>(); // <Vector> 用来接收二维数组中第二个维度的信息
			for (int j = 0; j <= 2; j++) { // data[i].length 用来录入每个大数组中子数组的信息
				t.add(QKC.getData(i, j));
			}
			data.add(t); // 依次把第二维加入一维中
		}

		// 整合
		tableModel = new DefaultTableModel(data, title);

		// 整合 & 让questionTable中的内容不可编辑
		keywordTable = new JTable(tableModel) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
				/* return super.isCellEditable(row, column) */
			}
		};

		// 每次选中一行
		keywordTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		// 滚动条 套 列表 （questionTable）
		JScrollPane scrollPane = new JScrollPane(keywordTable);

		// 设置列宽
		// 获取列
		TableColumn column1 = keywordTable.getColumn(titles[0]);
		// 设置列宽的最大像素
		column1.setMaxWidth(30);
		column1.setMinWidth(30);

		TableColumn column2 = keywordTable.getColumn(titles[2]);
		// 设置列宽的最大像素
		column2.setMaxWidth(50);
		column2.setMinWidth(50);

		// 添加
		this.add(scrollPane);

	}

	/**
	 * 数据获取
	 */
	// 获取数据
	public static int getSelectedRow() {
		return selectedRow;
	}

	public static void setSelectedRow(int selectedRow) {
		KeywordManagerComponent.selectedRow = selectedRow;
	}

	public static int[] getSelectedRows() {
		return selectedRows;
	}

	public static void setSelectedRows(int[] selectedRows) {
		KeywordManagerComponent.selectedRows = selectedRows;
	}

	// 某个单元格中的内容 ID，Question 用这个
	public static Object getValueAt_Table(int rowIndex, int columnIndex) {
		return data.get(rowIndex).get(columnIndex); // get(int) 返回位于Vector中指定位置的元素。
	}

}
