package component;

import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import JDBC.QNS.GroupTable.staffQns_T;
import JDBC.QNS.SingleTable.keywordAlternative_T;
import Type.markScheme;
import methodAndTool.WriteAndRead;

public class QuestionDetailsComponent extends Box {

	WriteAndRead WAR = new WriteAndRead();
	staffQns_T DIO;
	keywordAlternative_T QKC;

	// Create a one-dimensional array to store the titles 创建一维数组，存储标题
	static Object[] titles = { "ID", "Keyword", "Score" };

	// Creating Collections Manipulating collections is easier than manipulating
	// arrays 创建集合 操作集合比操作数组容易
	private Vector<Object> titleShowKeyword = new Vector<Object>(); // Store the title 存储标题
	private static Vector<Vector<Object>> dataShowKeyword = new Vector<>(); // Store the data 存储数据
	// table
	public static JTable showKeywordTable;
	// With a database, you need to create a data model.
	public static DefaultTableModel showKeywordTableModel;

	public static JLabel showID, showQuestion, showSolution, showAnswer, showAnswerScore, showScorePoint;

	// 不确定用提示组件好还是文本框组件好
	public static JTextArea showQuestion0, showSolution0, showAnswer0;

	public static JTextField showAnswerScore0;

	public QuestionDetailsComponent(staffQns_T dio) {
		super(BoxLayout.Y_AXIS);
		this.DIO = dio;

		// 暴露选择问题的id--------------------------------------------------------------
		String question_id = (String) QuestionManagerComponent
				.getValueAt_Table(QuestionManagerComponent.getSelectedRow(), 0);

		// list
		List<markScheme> markSchemeList = DIO.getSelectedMarkScheme(question_id);

		// -----------------------------------------------------------------------------

		showID = new JLabel("ID: " + question_id);
		showQuestion0 = new JTextArea(
				WAR.readString(QuestionManagerComponent.getValueAt_Table(QuestionManagerComponent.getSelectedRow(), 1)),
				10,
				10);

		showSolution = new JLabel("SOLUTION");
		showSolution0 = new JTextArea(
				WAR.readString(QuestionManagerComponent.getValueAt_Table(QuestionManagerComponent.getSelectedRow(), 2)),
				20,
				10);

		showAnswer = new JLabel("ANSWER");
		showAnswer0 = new JTextArea(
				WAR.readString(QuestionManagerComponent.getValueAt_Table(QuestionManagerComponent.getSelectedRow(), 3)),
				10, 10);

		showAnswerScore = new JLabel("ANSWER SCORE");
		showAnswerScore0 = new JTextField(WAR
				.readString(String.valueOf(dio.getData(QuestionManagerComponent.getSelectedRow(), 4))));

		showScorePoint = new JLabel("SCORE POINTS");

		// 设置
		showQuestion0.setEditable(false); // 设置不可编辑
		showSolution0.setEditable(false); // 设置不可编辑
		showAnswer0.setEditable(false); // 设置不可编辑
		showAnswerScore0.setEditable(false);

		// 分区

		Box box_Question = Box.createHorizontalBox();
		JScrollPane scrollPane_Question = new JScrollPane(showQuestion0);
		box_Question.add(scrollPane_Question);
		// box_Question.add(showQuestion);

		Box box_Solution = Box.createHorizontalBox();
		JScrollPane scrollPane_Solution = new JScrollPane(showSolution0);
		box_Solution.add(scrollPane_Solution);

		Box box_Answer = Box.createHorizontalBox();
		JScrollPane scrollPane_Answer = new JScrollPane(showAnswer0);
		box_Answer.add(scrollPane_Answer);

		Box box_ScorePoint = Box.createHorizontalBox();

		// Clear the original data to ensure that there is no content in the list
		// 清空原有数据，保证列表中无内容
		dataShowKeyword.clear();

		// read-in data 写入数据
		for (int i = 0; i < titles.length; i++) {
			titleShowKeyword.add(titles[i]);
		}

		for (int i = 0; i < markSchemeList.size(); i++) {
			Vector<Object> t = new Vector<Object>(); // <Vector> 用来接收二维数组中第二个维度的信息
			for (int j = 0; j <= 2; j++) { // data[i].length 用来录入每个大数组中子数组的信息
				if (j == 0) {
					t.add(i + 1);
				} else if (j == 1) {
					t.add(markSchemeList.get(i).getKeyword());
				} else if (j == 2) {
					t.add(markSchemeList.get(i).getScore());
				}

			}
			dataShowKeyword.add(t); // 依次把第二维加入一维中
		}

		showKeywordTableModel = new DefaultTableModel(dataShowKeyword, titleShowKeyword);
		showKeywordTable = new JTable(showKeywordTableModel) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
				/* return super.isCellEditable(row, column) */
			}
		};

		// 行高
		showKeywordTable.setRowHeight(24);

		JScrollPane scrollPane_ScorePoint = new JScrollPane(showKeywordTable);
		box_ScorePoint.add(scrollPane_ScorePoint);

		Box box1 = Box.createVerticalBox();
		box1.add(showID);
		box1.add(Box.createVerticalStrut(10));
		box1.add(box_Question);

		Box box2 = Box.createVerticalBox();

		box2.add(showSolution);
		box2.add(box_Solution);
		box2.add(Box.createVerticalStrut(10));
		box2.add(showAnswer);
		box2.add(box_Answer);
		box2.add(Box.createVerticalStrut(10));
		box2.add(showAnswerScore);
		box2.add(showAnswerScore0);

		Box box3 = Box.createVerticalBox();
		box3.add(showScorePoint);
		box3.add(box_ScorePoint);

		Box box = Box.createVerticalBox();
		box.add(box1);
		box.add(Box.createVerticalStrut(20));
		box.add(box2);
		box.add(Box.createVerticalStrut(20));
		box.add(box3);

		this.add(box);

	}

}