package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JFrame;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;

import JDBC.QNS.GroupTable.studentQns_T;
import JDBC.dbConnection.PythonCodeChecker_db;

import Type.markScheme;
import component.ChooseQuestionComponent;
import component.StudentWorkingComponent;
import javaswingdev.chart.PieChart;
import methodAndTool.ColorSet;

import methodAndTool.MessagePrintString;
import methodAndTool.ProjectVariable;
import methodAndTool.RunPythonCode;
import methodAndTool.ScreenUtils;
import methodAndTool.WriteAndRead;
import methodAndTool.keywordAnalysis;

public class PythonCodeCheckerPage {

        WriteAndRead WAR = new WriteAndRead();

        keywordAnalysis KA = new keywordAnalysis();
        MessagePrintString MPS = new MessagePrintString();

        // connection
        Connection conn = new PythonCodeChecker_db().get_connection();
        studentQns_T DIO = new studentQns_T(conn);
        ProjectVariable PV = new ProjectVariable();
        /**
         * Python Code Checker Page
         */
        JFrame frame = new JFrame("Python Code Chacker - Python Code Chacker Page");

        // 设置菜单栏
        JMenuBar manuBarStudent = new JMenuBar();
        // 设置菜单名
        JMenu manuStudent_Operation = new JMenu("OPERATION");
        JMenu manuStudent_Show = new JMenu("SHOW QUESTION");
        JMenu manuStudent_User = new JMenu("USER");

        // 设置菜单中物品 - OPERATION
        JMenuItem item_Submit = new JMenuItem("Submit Answer");
        JMenuItem item_Run = new JMenuItem("Run Code");
        // JMenuItem addInput = new JMenuItem("Add Input");
        // JMenuItem item_Feedback = new JMenuItem("Show Feedback");

        // 设置菜单中物品 - SHOW QUESTION
        // 上一题，下一题，随机一题。。。。。。
        JMenuItem item_PreviousQuestion = new JMenuItem("Previous Question");
        JMenuItem item_NextQuestion = new JMenuItem("Next Question");

        // 设置菜单中物品 - USER
        JMenuItem item_ChangeAccount = new JMenuItem("Change Account");
        JMenuItem item_ExitProgram = new JMenuItem("Exit Program");
        JMenuItem item_ChangeColor_White = new JMenuItem("Change Background White Color");
        JMenuItem item_ChangeColor_Black = new JMenuItem("Change Background Black Color");

        Font myFont1 = new Font("Arial", Font.PLAIN, 16);

        // 设置分割面板
        public static JSplitPane splitPane = new JSplitPane();

        // 初始化，组装界面
        public void init() {
                /**
                 * 设置窗口属性
                 */

                frame.setLocation((ScreenUtils.getScreenWidth() - ScreenUtils.getDesignWindow_width()) / 2,
                                (ScreenUtils.getScreenHeight() - ScreenUtils.getDesignWindow_heigh()) / 2); // 窗口位置
                frame.setSize(ScreenUtils.getDesignWindow_width(), ScreenUtils.getDesignWindow_heigh()); // 设置窗口（宽，高）
                ScreenUtils su = new ScreenUtils();
                frame.setIconImage(su.getItemPath("PythonLogo").getImage()); // Mac
                // 好像不太支持这个，Windows
                System.out.println("-- ImageIO is Working --");
                frame.setResizable(false); // 窗口锁定
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 违规操作关闭

                /**
                 * 组装零件
                 */
                // 菜单
                // 菜单子件加入对用的菜单名
                // Button_Item_ShowQuestionTable(item_ShowQuestionTable);
                Button_Item_SubmitAnswer(item_Submit);
                Button_Item_RunCode(item_Run);
                // Button_Item_ShowFeedback(item_Feedback);
                manuStudent_Operation.add(item_Submit);
                manuStudent_Operation.add(item_Run);

                ChooseQuestionComponent.Button_Item_PreviousQuestion(item_PreviousQuestion);
                ChooseQuestionComponent.Button_Item_NextQuestion(item_NextQuestion);

                manuStudent_Show.add(item_PreviousQuestion);
                manuStudent_Show.add(item_NextQuestion);

                Button_Item_ChangeAccount(item_ChangeAccount);
                Button_Item_ExitProgram(item_ExitProgram);
                Button_Item_ChangeBackgroundColor_White(item_ChangeColor_White);
                Button_Item_ChangeBackgroundColor_Black(item_ChangeColor_Black);
                manuStudent_User.add(item_ChangeAccount);
                manuStudent_User.add(item_ExitProgram);
                manuStudent_User.add(item_ChangeColor_White);
                manuStudent_User.add(item_ChangeColor_Black);

                // 菜单名加入菜单组
                manuBarStudent.add(manuStudent_Operation);
                manuBarStudent.add(manuStudent_Show);
                manuBarStudent.add(manuStudent_User);

                splitPane.setContinuousLayout(false); // 连续布局
                splitPane.setDividerLocation(1150); // 左右分屏初始位置
                splitPane.setDividerSize(10); // 分割线宽度

                // Right
                splitPane.setRightComponent(new ChooseQuestionComponent(DIO));
                // Left
                splitPane.setLeftComponent(new StudentWorkingComponent());

                // 将菜单栏加入窗口
                frame.setJMenuBar(manuBarStudent);
                frame.add(splitPane);

                // 窗口可见
                frame.setVisible(true);
                // frame.add(studnetButtonPanel, BorderLayout.SOUTH);

        }

        /**
         * Button 监听
         */
        // 切换用户
        private void Button_Item_ChangeAccount(JMenuItem button) {
                button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                try {
                                        conn.close();
                                } catch (SQLException e1) {
                                        e1.printStackTrace();
                                }
                                new HomePage().init();
                                frame.dispose();
                                System.out.println("-- The Change Account Manu Button is Working --");
                        }
                });
        }

        private void Button_Item_ChangeBackgroundColor_White(JMenuItem button) {
                button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                new ColorSet().setCurrent_color_set(2);

                        }
                });
        }

        private void Button_Item_ChangeBackgroundColor_Black(JMenuItem button) {
                button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                new ColorSet().setCurrent_color_set(1);

                        }
                });
        }

        // 退出程序
        private void Button_Item_ExitProgram(JMenuItem button) {
                button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                try {
                                        conn.close();
                                } catch (SQLException e1) {
                                        e1.printStackTrace();
                                }
                                System.exit(0);
                                System.out.println("-- The Exit Manu Button is Working --");
                        }
                });
        }

        // Submit Answer
        public void Button_Item_SubmitAnswer(Object button) {
                ((AbstractButton) button).addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                                int selectedRow = ChooseQuestionComponent.getSelectedRow();

                                if (selectedRow != -1) {

                                        final String solution = StudentWorkingComponent.getEditAnswerString();

                                        String emptyString = "";
                                        for (int i = 0; i < solution.length(); i++) {
                                                emptyString += " ";
                                        }

                                        if (!solution.equals(emptyString)) {
                                                if (solution.contains("input()")) {
                                                        RunPythonCode RP = new RunPythonCode();
                                                        RP.saveCodeFile(solution);
                                                        RP.runCode(DIO);

                                                } else {
                                                        // System.out.println("the selected row is: " + selectedRow);
                                                        String id = (String) DIO.getData_id(selectedRow);
                                                        // System.out.println(id);
                                                        List<markScheme> mkl = new ArrayList<markScheme>();
                                                        mkl = DIO.getSelectedMarkScheme(id);
                                                        RunPythonCode RP = new RunPythonCode();
                                                        RP.saveCodeFile(solution);
                                                        RP.runCode(1);

                                                        if (RP.getErrorMessage().equals("")) {

                                                                String userAnswer = RP.getOutputFromConsole();
                                                                String correctAnswer = DIO.getData(selectedRow, 3)
                                                                                .toString();
                                                                String suggestSolution = DIO.getData(selectedRow, 2)
                                                                                .toString();

                                                                int answerScore = PV
                                                                                .StringToInt(DIO.getData(selectedRow, 4)
                                                                                                .toString());
                                                                int passed_answerScore = KA.getAnswerScore(userAnswer,
                                                                                correctAnswer,
                                                                                answerScore);

                                                                int total_score = KA.getKeyWordSocre(solution,
                                                                                userAnswer,
                                                                                correctAnswer,
                                                                                answerScore, mkl);

                                                                PieChart keyword = PV.getKeywordPieChart(mkl,
                                                                                answerScore);

                                                                ArrayList<String> passedKeywordList = KA
                                                                                .getPassedKeywordlist(solution,
                                                                                                mkl);

                                                                PieChart passKeyword = PV.getPassedPieChart(solution,
                                                                                userAnswer,
                                                                                correctAnswer, answerScore,
                                                                                passed_answerScore,
                                                                                mkl);

                                                                ScorePage SP = new ScorePage(total_score,
                                                                                passedKeywordList,
                                                                                keyword, passKeyword,
                                                                                solution, suggestSolution);

                                                                MPS.SubmitSuccessToString(
                                                                                StudentWorkingComponent.terminalArea);
                                                                SP.init();

                                                        } else {
                                                                JFrame jf = new JFrame();
                                                                JOptionPane.showMessageDialog(jf,
                                                                                " ERROR !!");
                                                                StudentWorkingComponent.terminalArea
                                                                                .setText(">ErrorMessage: "
                                                                                                + RP.getErrorMessage()
                                                                                                + "\n");
                                                        }

                                                }

                                        } else {
                                                JFrame jf = new JFrame();
                                                JOptionPane.showMessageDialog(jf,
                                                                "EMPTY !!");
                                        }

                                } else {
                                        JFrame jf = new JFrame();
                                        JOptionPane.showMessageDialog(jf, "Please Select A Question");

                                }

                        }
                });
        }

        // Run Code
        public void Button_Item_RunCode(Object button) {
                ((AbstractButton) button).addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                // String answer = RunPythonCode.outputFromConsole;
                                final String solution = StudentWorkingComponent.getEditAnswerString();
                                RunPythonCode RP = new RunPythonCode();

                                RP.saveCodeFile(solution);
                                RP.runCode(2);

                                if (RP.getErrorMessage() != "") {
                                        StudentWorkingComponent.terminalArea.setText("> " + RP.getErrorMessage());
                                } else {
                                        StudentWorkingComponent.terminalArea.setText("> " + RP.getOutputFromConsole());
                                }

                        }
                });
        }

}
