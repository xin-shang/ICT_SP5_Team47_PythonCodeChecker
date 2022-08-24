package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import frontEndView.AddQuestionPage;
import frontEndView.HomePage;
import frontEndView.PythonQuestionEditPage;
import methodAndTool.ProjectVariable;

public class EditQuestionController implements ActionListener{
	
	ProjectVariable PV = new ProjectVariable();
	PythonQuestionEditPage PQEP = new PythonQuestionEditPage();
	
	public EditQuestionController () {
		
		PV.getButton_Add().addActionListener(this);
		PV.getButton_Add().setActionCommand("Add");
		
		PV.getButton_Delete().addActionListener(this);
		PV.getButton_Delete().setActionCommand("Delete");
		
		PV.getButton_Change().addActionListener(this);
		PV.getButton_Change().setActionCommand("Change");
		
		PV.getButton_Check().addActionListener(this);
		PV.getButton_Check().setActionCommand("Check");
		
		PV.getButton_ReturnHomePage().addActionListener(this);
		PV.getButton_ReturnHomePage().setActionCommand("Return Home Page");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand() == "Add") 
		{
			new AddQuestionPage();
			PQEP.setVisible(false);
			System.out.println("--Add Button is Working--");
		}
		
		else if (e.getActionCommand() == "Delete") 
		{
			System.out.println("--Delete Button is Working--");
		}
		
		else if (e.getActionCommand() == "Change") 
		{
			System.out.println("--Change Button is Working--");
		}
		
		else if (e.getActionCommand() == "Check") 
		{
			System.out.println("--Check Button is Working--");
		}
		
		else if (e.getActionCommand() == "Return Home Page") 
		{
			new HomePage();
			PQEP.setVisible(false);
			System.out.println("--Return Home Page Button is Working--");
		}
		
	}

}
