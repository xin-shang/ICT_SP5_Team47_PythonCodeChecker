package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import methodAndTool.ProjectVariable;
import frontEndView.*;

public class HomeController implements ActionListener{

        ProjectVariable PV = new ProjectVariable();
        HomePage HP = new HomePage();
        PythonCodeCheckerPage PCCP = new PythonCodeCheckerPage();

        public HomeController() {
                
                PV.getButton_Student().addActionListener(this);
		PV.getButton_Student().setActionCommand("I am a Student");
		
		PV.getButton_Staff().addActionListener(this);
		PV.getButton_Staff().setActionCommand("I am a Staff");
        }

        @Override
	public void actionPerformed(ActionEvent e) {
                
                if (e.getActionCommand() == "I am a Student") 
		{       
                        System.out.println("-- ******** --");
                        new PythonCodeCheckerPage();
                        System.out.println("-- ******** --");
                        new CodeCheckerController();
                        System.out.println("-- 'I am a Student' Working --");
                } 
                else if (e.getActionCommand() == "I am a Staff")
                {
                        new frontEndView.PythonQuestionEditPage();
                        //new 
                        System.out.println("--  'I am a Staff' Working  --");
                }
        }
        
}
