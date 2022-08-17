package methodAndTool;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ProjectVariable {
	
	public static int designWindow_width = 1000;
	public static int designWindow_heigh = 618;
	
	public static String feedback = "good!!!!!!";
	
	public static String filenameTemp;
	

	//提示语
	public static JLabel prompt_1 = new JLabel ("Please Input your Python Code in here: ");
	// 题目
	public static JLabel prompt_Question = new JLabel ("print 'Hello World' ");
	// 行和列
	public static JTextArea area_1 = new JTextArea (100, 50);				// 行和列
	
	// 评语
	public static JTextArea area_2 = new JTextArea (100, 50);				// 行和列
	public static JLabel area_3 = new JLabel ();							// 提示语
	
	// 按键
	public static JButton button_Submit = new JButton ("Submit");
	public static JButton button_Run = new JButton ("Run");
	public static JButton button_Score = new JButton ("Show Score");
	public static JButton button_Feedback = new JButton ("Show Feedback");
	


	public int getDesign_width() {
		return designWindow_width;
	}

	public static void setDesign_width(int design_width) {
		ProjectVariable.designWindow_width = design_width;
	}

	public int getDesign_heigh() {
		return designWindow_heigh;
	}

	public static void setDesign_heigh(int design_heigh) {
		ProjectVariable.designWindow_heigh = design_heigh;
	}
	
	
	
	/**
	 * 
	 * */
	public static String getFilenameTemp() {
		return filenameTemp;
	}

	public static void setFilenameTemp(String filenameTemp) {
		ProjectVariable.filenameTemp = filenameTemp;
	}

	
	
	
	
	/**
	 * Getter
	 * */
	public JLabel getPrompt_1() {
		return prompt_1;
	}

	public JLabel getPrompt_Question() {
		return prompt_Question;
	}

	public JTextArea getArea_1() {
		return area_1;
	}

	public JTextArea getArea_2() {
		return area_2;
	}

	public JLabel getArea_3() {
		return area_3;
	}

	// Setter
	public void setPrompt_1(JLabel prompt_1) {
		ProjectVariable.prompt_1 = prompt_1;
	}
	
	public void setPrompt_Question(JLabel prompt_Question) {
		ProjectVariable.prompt_Question = prompt_Question;
	}

	public void setArea_1(JTextArea area_1) {
		ProjectVariable.area_1 = area_1;
	}

	public void setArea_2(JTextArea area_2) {
		ProjectVariable.area_2 = area_2;
	}

	public void setArea_3(JLabel area_3) {
		ProjectVariable.area_3 = area_3;
	}
	
	// Getter Show String
	public String getPrompt_1_String() {
		return prompt_1.getText();
	}
	
	public String getPrompt_Question_String() {
		return prompt_Question.getText();
	}
	
	public String getArea_1_String() {
		return area_1.getText();
	}
	
	public String getArea_2_String() {
		return area_2.getText();
	}
	
	public String getArea_3_String() {
		return area_3.getText();
	}
	
	// Setter Show String 
	public void setPrompt_1_String(String prompt_1) {
		ProjectVariable.prompt_1.setText(prompt_1);;
	}
	
	public void setPrompt_Question_String(String prompt_Question) {
		ProjectVariable.prompt_Question.setText(prompt_Question);;
	}

	public void setArea_1_String(String area_1) {
		ProjectVariable.area_1.setText(area_1);;
	}

	public void setArea_2_String(String area_2) {
		ProjectVariable.area_2.setText(area_2);;
	}

	public void setArea_3_String(String area_3) {
		ProjectVariable.area_3.setText(area_3);;
	}
	
	
	
	
	
	/**
	 * Getter & Setter Button
	 * */
	public JButton getButton_Submit() {
		return button_Submit;
	}

	public void setButton_Submit(JButton button_Submit) {
		ProjectVariable.button_Submit = button_Submit;
	}

	public JButton getButton_Run() {
		return button_Run;
	}

	public void setButton_Run(JButton button_Run) {
		ProjectVariable.button_Run = button_Run;
	}

	public JButton getButton_Score() {
		return button_Score;
	}

	public void setButton_Score(JButton button_Score) {
		ProjectVariable.button_Score = button_Score;
	}

	public JButton getButton_Feedback() {
		return button_Feedback;
	}

	public void setButton_Feedback(JButton button_Feedback) {
		ProjectVariable.button_Feedback = button_Feedback;
	}
	
}
