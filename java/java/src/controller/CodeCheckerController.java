package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import methodAndTool.ProjectVariable;
import methodAndTool.AnswerWriteTxt;

import frontEndView.*;

public class CodeCheckerController implements ActionListener {

	ProjectVariable PV;
	AnswerWriteTxt AWT;
	PythonCodeCheckerPage PyChecker_Page;

	/**
	 * Button 监听，响应，
	 */
	public CodeCheckerController() {
		PyChecker_Page = null;
		PV = new ProjectVariable();
		AWT = new AnswerWriteTxt();
		PyChecker_Page = new PythonCodeCheckerPage();
		System.out.print("studnet page");

		PV.getButton_Submit().addActionListener(this);
		PV.getButton_Submit().setActionCommand("Submit Answer Code");

		PV.getButton_Run().addActionListener(this);
		PV.getButton_Run().setActionCommand("Run the Code");

		PV.getButton_Score().addActionListener(this);
		PV.getButton_Score().setActionCommand("Check the Score");

		PV.getButton_Feedback().addActionListener(this);
		PV.getButton_Feedback().setActionCommand("Show the Feedback");

		PV.getButton_ReturnHomePage().addActionListener(this);
		PV.getButton_ReturnHomePage().setActionCommand("Return HomePage");

	}

	/**
	 * 按键逻辑
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == "Submit Answer Code") {
			// AWT.writeAnswerInTxt();
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

			System.out.println("Button is Working! Submit Answer Code");
			System.out.println("--- TEXT String Print ---:" + pyCodeAnswer);
			System.out.println(ProjectVariable.getFilenameTemp());
		}

		else if (e.getActionCommand() == "Run the Code") {
			String pyCodeAnswer = PV.getArea_1_String();
			char[] py_chars = pyCodeAnswer.toCharArray();

			try {
				AnswerWriteTxt.creatTxtFile("PyCodeAnswer");
			} catch (IOException e1) {

				e1.printStackTrace();
			}
			AWT.writeAnswerInTxt(py_chars, pyCodeAnswer);
			AnswerWriteTxt.run_python_code();

			// set Text Area_2 as user output
			try {
				String try_print = AnswerWriteTxt.readText("./java/src/txt/user_output.txt");

				System.out.print(try_print);
				PV.getArea_2().setText(try_print);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.out.println("Button is Working! Run the Code");
		}

		else if (e.getActionCommand() == "Check the Score") {
			System.out.println("Button is Working! Check the Score");
		}

		else if (e.getActionCommand() == "Show the Feedback") {
			System.out.println("Button is Working! Show the Feedback");
		}

		else if (e.getActionCommand() == "Return HomePage") {
			PyChecker_Page.setVisible(false);
			PyChecker_Page.dispose();
			// PyChecker_Page = null;
			PV.getArea_1().setText("");
			PV.getArea_2().setText("");
			new HomeController();
			System.out.println("Button is Working! Return HomePage");

		}

	}

}
