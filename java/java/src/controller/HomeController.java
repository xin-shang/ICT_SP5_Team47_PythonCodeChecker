package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import methodAndTool.AnswerWriteTxt;
import methodAndTool.ProjectVariable;
import frontEndView.*;

public class HomeController implements ActionListener {

        ProjectVariable PV;
        CodeCheckerController code_c;
        // create main page
        HomePage HP;
        int count;

        public HomeController() {
                // HP = null;
                HP = new HomePage();
                HP.setVisible(true);

                PV = new ProjectVariable();

                PV.getButton_Student().addActionListener(this);
                PV.getButton_Student().setActionCommand("I am a Student");

                PV.getButton_Staff().addActionListener(this);
                PV.getButton_Staff().setActionCommand("I am a Staff");
        }

        public void delete() {
                code_c = null;
        }

        public HomePage getHP() {
                return HP;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

                if (e.getActionCommand() == "I am a Student") {
                        AnswerWriteTxt.run_python_code("./java/src/python/db_splite.py");
                        HP.setVisible(false);
                        HP.dispose();
                        // HP = null;
                        delete();
                        try {
                                code_c = new CodeCheckerController();
                        } catch (IOException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                        }

                        System.out.println("-- 'I am a Student' Working --");
                } else if (e.getActionCommand() == "I am a Staff") {
                        // new PythonQuestionEditPage();
                        HP.setVisible(false);
                        HP.dispose();
                        new EditQuestionController();
                        System.out.println("--  'I am a Staff' Working  --");
                }
                HP = null;
        }

}
