package frontEndView;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;

import methodAndTool.ProjectVariable;

public class PythonCodeCheckerPage extends JFrame{
	
	ProjectVariable PV = new ProjectVariable();

	public PythonCodeCheckerPage() {
		init();
	}
	
	private void init(){

		// 窗口
		setTitle("Python Code Checker - Student Page");				// 窗口名称
		setSize(PV.getDesign_width(), PV.getDesign_heigh());		// 设置窗口（宽，高）
		setResizable(false);					// 窗口锁定
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);			// 违规操作关闭

		// 版本
		JPanel studentPanel = new JPanel();
		studentPanel.setLayout(null);
		
		add(studentPanel);
		
		//字体
		Font myFont1 = new Font("Arial", Font.PLAIN, 16);
		Font myFont2 = new Font("Arial", Font.PLAIN, 20);

		System.out.println("-- ******** --");
		// 提示语
		PV.getPrompt_1().setFont(myFont2);
		PV.getPrompt_1().setBounds(50, 10, 600, 50);
		studentPanel.add(PV.getPrompt_1());
		
		// 问题
		PV.getPrompt_Question().setBounds(50, 60, 900, 50);
		studentPanel.add(PV.getPrompt_Question());
		
		// 代码编辑框
		PV.getArea_1().setFont(myFont1);								// 字体
		PV.getArea_1().setLineWrap(true);								// 自动换行
		PV.getArea_1().setBounds(50, 123, 600, 310);
		studentPanel.add(PV.getArea_1());
		
		PV.getButton_Submit().setBounds(700, 123, 150, 50);
		studentPanel.add(PV.getButton_Submit());
		
		PV.getButton_Run().setBounds(700, 183, 150, 50);
		studentPanel.add(PV.getButton_Run());
		
		PV.getButton_Score().setBounds(700, 243, 150, 50);
		studentPanel.add(PV.getButton_Score());
		
		PV.getButton_Feedback().setBounds(700, 303, 150, 50);
		studentPanel.add(PV.getButton_Feedback());
		
//		JLabel prompt_Score = new JLabel ("Grade: XXX");
//		prompt_Score.setBounds();
//		panel.add(prompt_Score);
		PV.getArea_2().setFont(myFont1);								// 字体
		PV.getArea_2().setLineWrap(true);								// 自动换行
		PV.getArea_2().setBounds(50, 443, 800, 135);
		studentPanel.add(PV.getArea_2());

		setVisible(true);

	}
	
}

	