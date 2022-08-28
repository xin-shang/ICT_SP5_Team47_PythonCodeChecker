package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import methodAndTool.ProjectVariable;
import methodAndTool.ScreenUtils;
import methodAndTool.WriteAndRead;

public class PythonCodeChackerPage {

	ProjectVariable PV = new ProjectVariable();
	ScreenUtils SU = new ScreenUtils();
	WriteAndRead WAR = new WriteAndRead();

	private String num = "01 02 03 04 05 06 07 08 09 11 12 13 14 15 16 17 18 19 20";

	/**
	 * Python Code Chacker Page
	 */
	//
	JFrame frame = new JFrame("Python Code Chacker - Python Code Chacker Page");
	// Python Code Checker Page 提示语
	JLabel prompt_1 = new JLabel("Please Input your Python Code in here: ");
	// Python Code Checker Page 题目
	JLabel prompt_Question = new JLabel("print 'Hello World' ");
	// Python Code Checker Page 文本框序号
	JLabel lineNumber = new JLabel();

	// Python Code Checker Page 行和列
	JTextArea area_1 = new JTextArea(100, 50); // 行和列

	// Python Code Checker Page 评语
	JTextArea area_2 = new JTextArea(100, 50); // 行和列
	JLabel area_3 = new JLabel(); // 提示语

	// Python Code Checker Page 按键
	JButton button_Submit = new JButton("Submit");
	JButton button_Run = new JButton("Run");
	JButton button_Score = new JButton("Show Score");
	JButton button_Feedback = new JButton("Show Feedback");

	JButton button_Return = new JButton("Return");

	JScrollPane scroll = new JScrollPane(area_2);

	// 初始化，组装界面
	public void init() {

		/**
		 * 设置窗口属性
		 */
		frame.setLocation((ScreenUtils.getScreenWidth() - SU.getDesignWindow_width()) / 2,
				(ScreenUtils.getScreenHeight() - SU.getDesignWindow_heigh()) / 2); // 窗口位置
		frame.setSize(SU.getDesignWindow_width(), SU.getDesignWindow_heigh()); // 设置窗口（宽，高）
		try {
			frame.setIconImage(ImageIO.read(new File(ScreenUtils.getItemPath("PythonLogo")))); // Mac 好像不太支持这个，Windows
																								// 咋样要试试。
			System.out.println("-- ImageIO is Working --");
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.setResizable(false); // 窗口锁定
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 违规操作关闭
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/**
		 * 设置窗口内容
		 */
		// 面板
		JPanel studentPanel = new JPanel();
		// 无布局
		studentPanel.setLayout(null);
		// 字体
		Font myFont1 = new Font("Arial", Font.PLAIN, 16);
		Font myFont2 = new Font("Arial", Font.PLAIN, 24);

		/**
		 * 组装零件
		 */
		// 提示语
		prompt_1.setFont(myFont2);
		prompt_1.setBounds(50, 0, 600, 50);
		studentPanel.add(prompt_1);

		// 问题
		String PyCodeQuestion;

		PyCodeQuestion = WAR.readText("./src/txt/PyCodeQuestion.txt");

		setPrompt_Question_String(WAR.readQuestion(
				PyCodeQuestion));
		prompt_Question.setFont(myFont1);
		prompt_Question.setBounds(50, 20, 900, 100);
		studentPanel.add(prompt_Question);

		// 文档序号
		setLineNumber_String(WAR.lineNumberString(num));
		lineNumber.setFont(myFont1);
		lineNumber.setBounds(50, 113, 25, 360);
		studentPanel.add(lineNumber);

		// 代码编辑框
		area_1.setFont(myFont1); // 字体
		area_1.setLineWrap(true); // 自动换行
		area_1.setBounds(75, 113, 675, 360);
		studentPanel.add(area_1);

		button_Submit.setBounds(800, 123, 150, 50);
		studentPanel.add(button_Submit);
		Button_Submit_Listener(button_Submit);

		button_Run.setBounds(800, 183, 150, 50);
		studentPanel.add(button_Run);
		Button_Run_Listener(button_Run);

		button_Score.setBounds(800, 243, 150, 50);
		studentPanel.add(button_Score);
		Button_ShowScore_Listener(button_Score);

		button_Feedback.setBounds(800, 303, 150, 50);
		studentPanel.add(button_Feedback);
		Button_ShowFeedback_Listener(button_Feedback);

		// JLabel prompt_Score = new JLabel ("Grade: XXX");
		// prompt_Score.setBounds();
		// panel.add(prompt_Score);

		// 滚动条尝试失败
		// scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		// // 垂直滚动条自动出现

		// 这里应该向控制台一样，> $run结果，> $你已成功提交， > $正在干啥，请稍后， > &完成 显示这种提示
		area_2.setFont(myFont1); // 字体
		area_2.setLineWrap(true); // 自动换行
		// area_2.setEditable(false); // 文本框，不可编辑。可以选中，复制
		area_2.setBounds(50, 488, 700, 85);
		studentPanel.add(area_2);

		// Return HomePage
		button_Return.setBounds(800, 493, 150, 75);
		// button_Return.setBackground(Color.getColor("#F8F8FF"));; // 改变按钮颜色
		studentPanel.add(button_Return);
		Button_Retuen_Listener(button_Return);

		//
		frame.add(studentPanel);

		// 窗口可见
		frame.setVisible(true);

	}

	/**
	 * Button 监听
	 */
	private void Button_Submit_Listener(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String pyCodeAnswer = getArea_1_String();
				char[] py_chars = pyCodeAnswer.toCharArray();
				try {
					WAR.creatTxtFile("PyCodeAnswer");
					System.out.println("--Submit Answer Code Button is Working--");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				WAR.writeAnswerInTxt(py_chars, pyCodeAnswer);
				WAR.run_python_code("./src/python/PYRunCode.py");

				System.out.println("Button is Working! Submit Answer Code");
				System.out.println("--- TEXT String Print ---:" + pyCodeAnswer);
				System.out.println(PV.getFilenameTemp());
				System.out.println("-- The Submit Button is Working --");
			}
		});
	}

	private void Button_Run_Listener(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//
				String pyCodeAnswer = getArea_1_String();
				char[] py_chars = pyCodeAnswer.toCharArray();

				try {
					WAR.creatTxtFile("PyCodeAnswer");
				} catch (IOException e1) {

					e1.printStackTrace();
				}
				WAR.writeAnswerInTxt(py_chars, pyCodeAnswer);
				WAR.run_python_code("./src/python/PYRunCode.py");
				// set Text Area_2 as user output
				String UserOutput = WAR.readText("./src/txt/PyCodAnswer.txt");
				System.out.print(UserOutput);
				area_2.setText(UserOutput);

				System.out.println("-- The Run Button is Working, Run the Code --");
			}
		});
	}

	private void Button_ShowScore_Listener(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//

				System.out.println("-- The Show Score Button is Working --");
			}
		});
	}

	private void Button_ShowFeedback_Listener(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//

				System.out.println("-- The Show Feedback Button is Working --");
			}
		});
	}

	private void Button_Retuen_Listener(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//
				new HomePage().init();
				frame.setVisible(false);
				System.out.println("-- The Return Button is Working --");
			}
		});
	}

	/**
	 * 内容获取, 好些个用不上，项目最后后底线还是黄色，就删掉。
	 */
	private String getPrompt_Question_String() {
		return prompt_Question.getText();
	}

	private String getArea_1_String() {
		return area_1.getText();
	}

	private String getArea_2_String() {
		return area_2.getText();
	}

	private String getArea_3_String() {
		return area_3.getText();
	}

	private String getLineNumber_String() {
		return lineNumber.getText();
	}

	private void setPrompt_1_String(String prompt_1) {
		this.prompt_1.setText(prompt_1);
		;
	}

	private void setPrompt_Question_String(String prompt_Question) {
		this.prompt_Question.setText(prompt_Question);
		;
	}

	private void setArea_1_String(String area_1) {
		this.area_1.setText(area_1);
		;
	}

	private void setArea_2_String(String area_2) {
		this.area_2.setText(area_2);
		;
	}

	private void setArea_3_String(String area_3) {
		this.area_3.setText(area_3);
		;
	}

	private void setLineNumber_String(String lineNumber) {
		this.lineNumber.setText(lineNumber);
	}

}
