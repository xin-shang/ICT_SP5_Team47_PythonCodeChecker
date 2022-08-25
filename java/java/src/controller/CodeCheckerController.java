package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import methodAndTool.ProjectVariable;
import methodAndTool.AnswerWriteTxt;

import frontEndView.*;

public class CodeCheckerController implements ActionListener {

	ProjectVariable PV;
	AnswerWriteTxt AWT;
	PythonCodeCheckerPage PyChecker_Page;
	HomeController home_c;

	/**
	 * Button 监听，响应，
	 * 
	 * @throws IOException
	 */
	public CodeCheckerController() throws IOException {

		AWT = new AnswerWriteTxt();
		PyChecker_Page = new PythonCodeCheckerPage();

		PyChecker_Page.setVisible(true);

		PV = new ProjectVariable();

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

	public void delete() {
		home_c = null;
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

			AnswerWriteTxt.run_python_code("./java/src/python/PyController.py");

			System.out.println("Button is Working! Submit Answer Code");
			System.out.println("--- TEXT String Print ---:" + pyCodeAnswer);
			System.out.println(ProjectVariable.getFilenameTemp());
		}

		else if (e.getActionCommand() == "Run the Code") {
			String u_output;
			String a_output;

			String pyCodeAnswer = PV.getArea_1_String();
			char[] py_chars = pyCodeAnswer.toCharArray();

			try {
				AnswerWriteTxt.creatTxtFile("PyCodeAnswer");
			} catch (IOException e1) {

				e1.printStackTrace();
			}
			AWT.writeAnswerInTxt(py_chars, pyCodeAnswer);
			AnswerWriteTxt.run_python_code("./java/src/python/PyController.py");

			// set Text Area_2 as user output
			try {
				u_output = AnswerWriteTxt.readText("./java/src/txt/user_output.txt");
				a_output = AnswerWriteTxt.readText("./java/src/txt/rightsolution_output.txt");

				if (u_output.equals(a_output) == true) {
					JOptionPane.showMessageDialog(PyChecker_Page, "you working well!!");
				} else {
					JOptionPane.showMessageDialog(PyChecker_Page, "the answer is wrong");
				}
				System.out.println(a_output);
				System.out.println(u_output);
				PV.getArea_2().setText(u_output);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			System.out.println("Button is Working! Run the Code");
		}

		else if (e.getActionCommand() == "Check the Score") {
			System.out.println("Button is Working! Check the Score");
			AnswerWriteTxt.run_python_code("./java/src/python/db_C.py");
		}

		else if (e.getActionCommand() == "Show the Feedback") {
			JOptionPane.showMessageDialog(PyChecker_Page, "thank you for using java");
			System.out.println("Button is Working! Show the Feedback");
		}

		else if (e.getActionCommand() == "Return HomePage") {
			PyChecker_Page.setVisible(false);
			PyChecker_Page.dispose();
			PyChecker_Page = null;

			PV.getArea_1().setText("");
			PV.getArea_2().setText("");

			home_c = new HomeController();

			System.out.println("Button is Working! Return HomePage");

		}

	}

}
