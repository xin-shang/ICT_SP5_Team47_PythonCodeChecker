package methodAndTool;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ProjectVariable {
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public static int designWindow_width = 1000;
	public static int designWindow_heigh = 618;
	
	public static String feedback = "good!!!!!!";
	
	public static String filenameTemp;
	
	/**
	 * Home Page：
	 **/
	// Home Page 提示语
	public static JLabel prompt_home = new JLabel ("Welcome to Python Code Chacker");
	// Home Page 按键
	public static JButton button_Student = new JButton ("I'm a Student");
	public static JButton button_Staff = new JButton ("I'm a Staff");

	/**
	 * Python Code Chacker Page：
	 * */
	// Python Code Checker Page 提示语
	public static JLabel prompt_1 = new JLabel ("Please Input your Python Code in here: ");
	// Python Code Checker Page 题目
	public static JLabel prompt_Question = new JLabel ("print 'Hello World' ");
	// Python Code Checker Page 文本框序号
	public static JLabel lineNumber = new JLabel ();
	
	// Python Code Checker Page 行和列
	public static JTextArea area_1 = new JTextArea (100, 50);				// 行和列
	
	// 评语
	public static JTextArea area_2 = new JTextArea (100, 50);				// 行和列
	public static JLabel area_3 = new JLabel ();							// 提示语
	
	// Python Code Checker Page 按键
	public static JButton button_Submit = new JButton ("Submit");
	public static JButton button_Run = new JButton ("Run");
	public static JButton button_Score = new JButton ("Show Score");
	public static JButton button_Feedback = new JButton ("Show Feedback");
	
	
	/**
	 * Python Question Edit Page
	 * */
	// 提示语句
	public static JLabel prompt_2 = new JLabel ("Welcome to Staff Page ");
	
	// 按钮
	public static JButton button_Add = new JButton ("Add a New Question");
	public static JButton button_Check = new JButton ("Check the Question Bank");
	public static JButton button_Change = new JButton ("Change the Question");
	public static JButton button_Delete = new JButton ("Delete a Question");
	
	

	/**
	 * 
	 * */
	// 通用 return HomePage
	public static JButton button_ReturnHomePage = new JButton("Return HomePage");
	

	public int getScreenWidthSize () {
		int screenWidth = (int) screenSize.getWidth(); 
		return screenWidth;
	}
	
	public int getScreenHeighSize () {
		int  screenHeigh = (int) screenSize.getHeight();
		return screenHeigh;
	}
	
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
	public JLabel getPrompt_home() {
		return prompt_home;
	}
	
	public JLabel getPrompt_1() {
		return prompt_1;
	}

	public JLabel getPrompt_Question() {
		return prompt_Question;
	}

	public JLabel getLineNumber() {
		return lineNumber;
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
	public void setPrompt_home(JLabel prompt_home) {
		ProjectVariable.prompt_home = prompt_home;
	}

	public void setPrompt_1(JLabel prompt_1) {
		ProjectVariable.prompt_1 = prompt_1;
	}
	
	public void setPrompt_Question(JLabel prompt_Question) {
		ProjectVariable.prompt_Question = prompt_Question;
	}

	public void setLineNumber(JLabel lineNumber) {
		ProjectVariable.lineNumber = lineNumber;
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

	public String getLineNumber_String() {
		return lineNumber.getText();
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
	
	public void setLineNumber_String(String lineNumber) {
		ProjectVariable.lineNumber.setText(lineNumber);
	}
	
	
	
	
	/**
	 * Getter & Setter Button
	 * */
	public JButton getButton_Student() {
		return button_Student;
	}

	public void setButton_Student(JButton button_Student) {
		ProjectVariable.button_Student = button_Student;
	}

	public JButton getButton_Staff() {
		return button_Staff;
	}

	public void setButton_Staff(JButton button_Staff) {
		ProjectVariable.button_Staff = button_Staff;
	}

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

	public JButton getButton_ReturnHomePage() {
		return button_ReturnHomePage;
	}

	public void setButton_ReturnHomePage(JButton button_ReturnHomePage) {
		ProjectVariable.button_ReturnHomePage = button_ReturnHomePage;
	}
	
	
	
	
	
	/**
	 * 
	 * */
	public JLabel getPrompt_2() {
		return prompt_2;
	}

	public static void setPrompt_2(JLabel prompt_2) {
		ProjectVariable.prompt_2 = prompt_2;
	}

	public JButton getButton_Add() {
		return button_Add;
	}

	public static void setButton_Add(JButton button_Add) {
		ProjectVariable.button_Add = button_Add;
	}

	public JButton getButton_Check() {
		return button_Check;
	}

	public static void setButton_Check(JButton button_Check) {
		ProjectVariable.button_Check = button_Check;
	}

	public JButton getButton_Change() {
		return button_Change;
	}

	public static void setButton_Change(JButton button_Change) {
		ProjectVariable.button_Change = button_Change;
	}

	public JButton getButton_Delete() {
		return button_Delete;
	}

	public static void setButton_Delete(JButton button_Delete) {
		ProjectVariable.button_Delete = button_Delete;
	}
	
}
