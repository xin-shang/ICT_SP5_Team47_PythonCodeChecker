package frontEndView;

import java.awt.Font;
import javax.swing.JPanel;

import methodAndTool.ProjectVariable;

public class PythonCodeCheckerPage {
	
	ProjectVariable PV = new ProjectVariable();	
	
	public void checkerPage (JPanel panel) {
		
		// 无布局
		panel.setLayout(null);
		
		//字体
		Font myFont1 = new Font("Arial", Font.PLAIN, 16);
		Font myFont2 = new Font("Arial", Font.PLAIN, 20);
		
		// 提示语
		PV.getPrompt_1().setFont(myFont2);
		PV.getPrompt_1().setBounds(50, 10, 600, 50);
		panel.add(PV.getPrompt_1());
		
		// 问题
		PV.getPrompt_Question().setBounds(50, 60, 900, 50);
		panel.add(PV.getPrompt_Question());
		
		// 代码编辑框
		PV.getArea_1().setFont(myFont1);								// 字体
		PV.getArea_1().setLineWrap(true);								// 自动换行
		PV.getArea_1().setBounds(50, 123, 600, 310);
		panel.add(PV.getArea_1());
		
		PV.getButton_Submit().setBounds(700, 123, 150, 50);
		panel.add(PV.getButton_Submit());
		
		PV.getButton_Run().setBounds(700, 183, 150, 50);
		panel.add(PV.getButton_Run());
		
		PV.getButton_Score().setBounds(700, 243, 150, 50);
		panel.add(PV.getButton_Score());
		
		PV.getButton_Feedback().setBounds(700, 303, 150, 50);
		panel.add(PV.getButton_Feedback());
		
//		JLabel prompt_Score = new JLabel ("Grade: XXX");
//		prompt_Score.setBounds();
//		panel.add(prompt_Score);
		PV.getArea_2().setFont(myFont1);								// 字体
		PV.getArea_2().setLineWrap(true);								// 自动换行
		PV.getArea_2().setBounds(50, 443, 800, 135);
		panel.add(PV.getArea_2());
		
	}
	
}

	