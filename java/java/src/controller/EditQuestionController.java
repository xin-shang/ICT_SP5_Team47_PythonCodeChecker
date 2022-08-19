package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import methodAndTool.ProjectVariable;

public class EditQuestionController implements ActionListener{
	
	ProjectVariable PV = new ProjectVariable();
	
	public EditQuestionController () {
		
		PV.getButton_Add().addActionListener(this);
		PV.getButton_Add().setActionCommand("Add");
		
		PV.getButton_Delete().addActionListener(this);
		PV.getButton_Delete().setActionCommand("Delete");
		
		PV.getButton_Change().addActionListener(this);
		PV.getButton_Change().setActionCommand("Change");
		
		PV.getButton_Check().addActionListener(this);
		PV.getButton_Check().setActionCommand("Check");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand() == "Add") 
		{
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
		
	}

}
