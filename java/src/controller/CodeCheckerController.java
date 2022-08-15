package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
			AWT.writeAnswerInTxt(); 
			System.out.println("Submit Button is Working! Submit Answer Code");
		}
		
		else if (e.getActionCommand() == "Run the Code") 
		{
			System.out.println("Submit Button is Working! Run the Code");
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
