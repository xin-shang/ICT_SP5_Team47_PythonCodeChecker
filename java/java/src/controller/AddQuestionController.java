package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import frontEndView.AddQuestionPage;
import frontEndView.PythonQuestionEditPage;
import methodAndTool.ProjectVariable;

public class AddQuestionController implements ActionListener{
	
	ProjectVariable PV = new ProjectVariable();
	AddQuestionPage AQP = new AddQuestionPage();
	
	public AddQuestionController () {
		
		PV.getButton_ScoringPoint().addActionListener(this);
		PV.getButton_ScoringPoint().setActionCommand("Add Scoring Point");
		
		PV.getButton_AddQuestionSubmit().addActionListener(this);
		PV.getButton_AddQuestionSubmit().setActionCommand("Submit New Question");
		
		PV.getButton_ReturnEditQuestionPage().addActionListener(this);
		PV.getButton_ReturnEditQuestionPage().setActionCommand("Return Previous Page");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand() == "Add Scoring Point") 
		{
			System.out.println("-- Add Scoring Point Button is Working --");
		}
		
		else if (e.getActionCommand() == "Submit New Question")
		{
			System.out.println("-- Submit New Question Button is Working --");
		}
		
		else if (e.getActionCommand() == "Return Previous Page")
		{
			System.out.println("-- -- -- -- --");
			new PythonQuestionEditPage();
			AQP.setVisible(false);
			System.out.println("-- Return Previous Page Button is Working --");
		}
		
	}

}
