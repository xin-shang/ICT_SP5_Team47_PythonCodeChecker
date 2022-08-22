package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import methodAndTool.ProjectVariable;
import methodAndTool.AnswerWriteTxt;

public class CodeCheckerController implements ActionListener{
	
	ProjectVariable PV = new ProjectVariable();
	AnswerWriteTxt AWT = new AnswerWriteTxt();
	
	/**
	 * Button 监听，响应，
	 * */
	public CodeCheckerController() {
		
		PV.getButton_Submit().addActionListener(this);
		PV.getButton_Submit().setActionCommand("Submit Answer Code");
		
		PV.getButton_Run().addActionListener(this);
		PV.getButton_Run().setActionCommand("Run the Code");
		
		PV.getButton_Score().addActionListener(this);
		PV.getButton_Score().setActionCommand("Check the Score");
		
		PV.getButton_Feedback().addActionListener(this);
		PV.getButton_Score().setActionCommand("Show the Feedback");
		
	}

	/**
	 * 按键逻辑 
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getActionCommand() == "Submit Answer Code") 
		{
//			AWT.writeAnswerInTxt(); 
			String pyCodeAnswer = PV.getArea_1_String();
			char[] py_chars = pyCodeAnswer.toCharArray();
			try {
				System.out.println("############");
				AnswerWriteTxt.creatTxtFile("PyCodeAnswer");
				
				System.out.println("************");
				
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			AWT.writeAnswerInTxt(py_chars, pyCodeAnswer);
			
			AnswerWriteTxt.run_python_code();
			
			System.out.println("Submit Button is Working! Submit Answer Code");
			System.out.println("--- TEXT String Print ---:" + pyCodeAnswer);
			System.out.println(ProjectVariable.getFilenameTemp());
		}
		
		else if (e.getActionCommand() == "Run the Code") 
		{
			System.out.println("Submit Button is Working! Run the Code");
			String pyCodeAnswer = PV.getArea_1_String();
			char[] py_chars = pyCodeAnswer.toCharArray();

			try { 
				AnswerWriteTxt.creatTxtFile("PyCodeAnswer");
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
			AWT.writeAnswerInTxt(py_chars, pyCodeAnswer);
			AnswerWriteTxt.run_python_code();
			
		
			
			
			//set Text Area_2 as user output
			try {
				String try_print = AnswerWriteTxt.readText("./java/src/txt/user_output.txt");
				
				System.out.print(try_print);
				PV.getArea_2().setText(try_print);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
				
		
			
		}
		
		else if (e.getActionCommand() == "Check the Score") 
		{
			System.out.println("Submit Button is Working! Check the Score");
		}
		
		else if (e.getActionCommand() == "Show the Feedback") 
		{
			System.out.println("Submit Button is Working! Show the Feedback");
		}
		
	}

}
