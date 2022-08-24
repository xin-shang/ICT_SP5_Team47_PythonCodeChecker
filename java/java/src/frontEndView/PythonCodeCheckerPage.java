package frontEndView;

import java.awt.Font;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import methodAndTool.ProjectVariable;
import methodAndTool.AnswerWriteTxt;

public class PythonCodeCheckerPage extends JFrame {

	static ProjectVariable PV = new ProjectVariable();
	AnswerWriteTxt AWT = new AnswerWriteTxt();

	private String num = "01 02 03 04 05 06 07 08 09 11 12 13 14 15 16 17 18 19 20";

	public PythonCodeCheckerPage() throws IOException {
		init();
	}

	private void init() throws IOException {

		// 窗口
		this.setTitle("Python Code Checker - Student Page"); // 窗口名称
		this.setSize(PV.getDesign_width(), PV.getDesign_heigh()); // 设置窗口（宽，高）
		this.setResizable(false); // 窗口锁定
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // 违规操作关闭

		// 版本
		JPanel studentPanel = new JPanel();
		studentPanel.setLayout(null);

		this.add(studentPanel);

		// 字体
		Font myFont1 = new Font("Arial", Font.PLAIN, 16);
		Font myFont2 = new Font("Arial", Font.PLAIN, 20);

		System.out.println("-- ******** --");

		// 提示语
		PV.getPrompt_1().setFont(myFont2);
		PV.getPrompt_1().setBounds(50, 0, 600, 50);
		studentPanel.add(PV.getPrompt_1());

		// 问题
		AnswerWriteTxt.run_python_code("./java/src/dbSqlite/db_splite.py");
		String u_output = AnswerWriteTxt.readText("./java/src/dbSqlite/question_text.txt");

		PV.setPrompt_Question_String(AWT.readQuestion(u_output));
		PV.getPrompt_Question().setFont(myFont1);
		PV.getPrompt_Question().setBounds(50, 20, 900, 100);
		studentPanel.add(PV.getPrompt_Question());

		// 文档序号
		PV.setLineNumber_String(AWT.lineNumberString(num));
		PV.getLineNumber().setFont(myFont1);
		PV.getLineNumber().setBounds(50, 113, 25, 360);
		studentPanel.add(PV.getLineNumber());

		// 代码编辑框
		PV.getArea_1().setFont(myFont1); // 字体
		PV.getArea_1().setLineWrap(true); // 自动换行
		PV.getArea_1().setBounds(75, 113, 675, 360);
		studentPanel.add(PV.getArea_1());

		PV.getButton_Submit().setBounds(800, 123, 150, 50);
		studentPanel.add(PV.getButton_Submit());

		PV.getButton_Run().setBounds(800, 183, 150, 50);
		studentPanel.add(PV.getButton_Run());

		PV.getButton_Score().setBounds(800, 243, 150, 50);
		studentPanel.add(PV.getButton_Score());

		PV.getButton_Feedback().setBounds(800, 303, 150, 50);
		studentPanel.add(PV.getButton_Feedback());

		// JLabel prompt_Score = new JLabel ("Grade: XXX");
		// prompt_Score.setBounds();
		// panel.add(prompt_Score);
		PV.getArea_2().setFont(myFont1); // 字体
		PV.getArea_2().setLineWrap(true); // 自动换行
		PV.getArea_2().setBounds(50, 493, 700, 85);
		studentPanel.add(PV.getArea_2());

		// Return HomePage
		PV.getButton_ReturnHomePage().setBounds(800, 493, 150, 75);
		// PV.getButton_ReturnHomePage().setBackground(Color.getColor("#F8F8FF"));; //
		// 改变按钮颜色
		studentPanel.add(PV.getButton_ReturnHomePage());

		setVisible(true);

	}

}
