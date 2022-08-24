package methodAndTool;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
	public static JLabel prompt_home = new JLabel("Welcome to Python Code Chacker");
	// Home Page 按键
	public static JButton button_Student = new JButton("I'm a Student");
	public static JButton button_Staff = new JButton("I'm a Staff");

	/**
	 * Python Code Chacker Page：
	 */
	// Python Code Checker Page 提示语
	public static JLabel prompt_1 = new JLabel("Please Input your Python Code in here: ");
	// Python Code Checker Page 题目
	public static JLabel prompt_Question = new JLabel("print 'Hello World' ");
	// Python Code Checker Page 文本框序号
	public static JLabel lineNumber = new JLabel();

	// Python Code Checker Page 行和列
	public static JTextArea area_1 = new JTextArea(100, 50); // 行和列

	// 评语
	public static JTextArea area_2 = new JTextArea(100, 50); // 行和列
	public static JLabel area_3 = new JLabel(); // 提示语

	// Python Code Checker Page 按键
	public static JButton button_Submit = new JButton("Submit");
	public static JButton button_Run = new JButton("Run");
	public static JButton button_Score = new JButton("Show Score");
	public static JButton button_Feedback = new JButton("Show Feedback");

	/**
	 * Python Question Edit Page
	 */
	// 提示语句
	public static JLabel prompt_2 = new JLabel("Welcome to Staff Page ");

	// 按钮
	public static JButton button_Add = new JButton("Add a New Question");
	public static JButton button_Check = new JButton("Check the Question Bank");
	public static JButton button_Change = new JButton("Change the Question");
	public static JButton button_Delete = new JButton("Delete a Question");

	/**
	 * Add a Question Page
	 */
	//
	public static JLabel prompt_3 = new JLabel("Add a New Question");

	// input ID
	public static JLabel prompt_QuestionID = new JLabel("Input ID: ");
	public static JTextField area_QuestionID = new JTextField(8);
	public static JLabel prompt_MaxQuestionID = new JLabel("Max: " + "MaxID"); // 字号小点，换个颜色。要getID。PDF第23页。

	// input Question
	public static JLabel prompt_QuestionContent = new JLabel("Input Question Content: ");
	public static JTextArea area_QuestionContent = new JTextArea(200, 200);

	// input Scoring Point
	public static JLabel prompt_ScoringPoint = new JLabel("Input Scoring Point: ");

	// button Scoring Point
	public static JButton Button_ScoringPoint = new JButton("Add Scoring Point");

	//
	JButton button_AddQuestionSubmit = new JButton("-- Submit this Question --");

	/**
	 * 
	 * */
	// 通用 return HomePage
	public static JButton button_ReturnHomePage = new JButton("Return HomePage");

	public int getScreenWidthSize() {
		int screenWidth = (int) screenSize.getWidth();
		return screenWidth;
	}

	public int getScreenHeighSize() {
		int screenHeigh = (int) screenSize.getHeight();
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
	 */
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
		ProjectVariable.prompt_1.setText(prompt_1);
		;
	}

	public void setPrompt_Question_String(String prompt_Question) {
		ProjectVariable.prompt_Question.setText(prompt_Question);
		;
	}

	public void setArea_1_String(String area_1) {
		ProjectVariable.area_1.setText(area_1);
		;
	}

	public void setArea_2_String(String area_2) {
		ProjectVariable.area_2.setText(area_2);
		;
	}

	public void setArea_3_String(String area_3) {
		ProjectVariable.area_3.setText(area_3);
		;
	}

	public void setLineNumber_String(String lineNumber) {
		ProjectVariable.lineNumber.setText(lineNumber);
	}

	/**
	 * Getter & Setter Button
	 */
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
		System.out.println("called 1");
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

	/**
	 * Add Question Page
	 */
	//
	public JLabel getPrompt_3() {
		return prompt_3;
	}

	public static void setPrompt_3(JLabel prompt_3) {
		ProjectVariable.prompt_3 = prompt_3;
	}

	public JLabel getPrompt_QuestionID() {
		return prompt_QuestionID;
	}

	public static void setPrompt_QuestionID(JLabel prompt_QuestionID) {
		ProjectVariable.prompt_QuestionID = prompt_QuestionID;
	}

	public JTextField getArea_QuestionID() {
		return area_QuestionID;
	}

	public static void setArea_QuestionID(JTextField area_QuestionID) {
		ProjectVariable.area_QuestionID = area_QuestionID;
	}

	public JLabel getPrompt_MaxQuestionID() {
		return prompt_MaxQuestionID;
	}

	public static void setPrompt_MaxQuestionID(JLabel prompt_MaxQuestionID) {
		ProjectVariable.prompt_MaxQuestionID = prompt_MaxQuestionID;
	}

	public JLabel getPrompt_QuestionContent() {
		return prompt_QuestionContent;
	}

	public static void setPrompt_QuestionContent(JLabel prompt_QuestionContent) {
		ProjectVariable.prompt_QuestionContent = prompt_QuestionContent;
	}

	public JTextArea getArea_QuestionContent() {
		return area_QuestionContent;
	}

	public static void setArea_QuestionContent(JTextArea area_QuestionContent) {
		ProjectVariable.area_QuestionContent = area_QuestionContent;
	}

	public JLabel getPrompt_ScoringPoint() {
		return prompt_ScoringPoint;
	}

	public static void setPrompt_ScoringPoint(JLabel prompt_ScoringPoint) {
		ProjectVariable.prompt_ScoringPoint = prompt_ScoringPoint;
	}

	public JButton getButton_ScoringPoint() {
		return Button_ScoringPoint;
	}

	public static void setButton_ScoringPoint(JButton button_ScoringPoint) {
		Button_ScoringPoint = button_ScoringPoint;
	}

	public JButton getButton_AddQuestionSubmit() {
		return button_AddQuestionSubmit;
	}

	public void setButton_AddQuestionSubmit(JButton button_AddQuestionSubmit) {
		this.button_AddQuestionSubmit = button_AddQuestionSubmit;
	}

}
