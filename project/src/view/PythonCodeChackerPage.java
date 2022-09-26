package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.JFrame;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;

import JDBC.QNS.GroupTable.studentQns_T;
import component.ChooseQuestionComponent;
import component.StudentWorkingComponent;
import methodAndTool.ScreenUtils;
import methodAndTool.WriteAndRead;
import methodAndTool.keywordAnalysis;
import methodAndTool.markScheme;

public class PythonCodeChackerPage {

        WriteAndRead WAR = new WriteAndRead();
        studentQns_T DIO = new studentQns_T();
        keywordAnalysis KA = new keywordAnalysis();

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
        JMenuItem item_Feedback = new JMenuItem("Show Feedback");

        // 设置菜单中物品 - SHOW QUESTION
        // 上一题，下一题，随机一题。。。。。。
        JMenuItem item_PreviousQuestion = new JMenuItem("Previous Question");
        JMenuItem item_NextQuestion = new JMenuItem("Next Question");
        JMenuItem item_RandomQuestion = new JMenuItem("Random Question");

        // 设置菜单中物品 - USER
        JMenuItem item_ChangeAccount = new JMenuItem("Change Account");
        JMenuItem item_ExitProgram = new JMenuItem("Exit Program");

        FeedbackPage feedbackPage = new FeedbackPage("Feedback", frame);

        // // 设置按钮
        // JPanel studnetButtonPanel;
        // JButton buttonSubmitAnswer = new JButton("Submit Answer");
        // JButton buttonRunCode = new JButton("Run Code");
        // JButton buttonShowFeedback = new JButton("Show Feedback");

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
                try {
                        frame.setIconImage(ImageIO.read(new File(ScreenUtils.getItemPath("PythonLogo")))); // Mac
                                                                                                           // 好像不太支持这个，Windows
                        System.out.println("-- ImageIO is Working --");
                } catch (IOException e) {
                        e.printStackTrace();
                }
                frame.setResizable(true); // 窗口锁定
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 违规操作关闭
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                /**
                 * 组装零件
                 */
                // 菜单
                // 菜单子件加入对用的菜单名
                // Button_Item_ShowQuestionTable(item_ShowQuestionTable);
                Button_Item_SubmitAnswer(item_Submit);
                Button_Item_RunCode(item_Run);
                Button_Item_ShowFeedback(item_Feedback);
                manuStudent_Operation.add(item_Submit);
                manuStudent_Operation.add(item_Run);
                manuStudent_Operation.add(item_Feedback);

                Button_Item_PreviousQuestion(item_PreviousQuestion);
                Button_Item_NextQuestion(item_NextQuestion);
                Button_Item_RandomQuestion(item_RandomQuestion);
                manuStudent_Show.add(item_PreviousQuestion);
                manuStudent_Show.add(item_NextQuestion);
                manuStudent_Show.add(item_RandomQuestion);

                Button_Item_ChangeAccount(item_ChangeAccount);
                Button_Item_ExitProgram(item_ExitProgram);
                manuStudent_User.add(item_ChangeAccount);
                manuStudent_User.add(item_ExitProgram);

                // 菜单名加入菜单组
                manuBarStudent.add(manuStudent_Operation);
                manuBarStudent.add(manuStudent_Show);
                manuBarStudent.add(manuStudent_User);

                //
                // Box box = Box.createVerticalBox(); 竖直
                // Box box = Box.createHorizontalBox(); 水平

                splitPane.setContinuousLayout(false); // 连续布局
                splitPane.setDividerLocation(900); // 左右分屏初始位置
                splitPane.setDividerSize(10); // 分割线宽度

                // Right
                splitPane.setRightComponent(new ChooseQuestionComponent());
                // Left
                splitPane.setLeftComponent(new StudentWorkingComponent());

                // 将菜单栏加入窗口
                frame.setJMenuBar(manuBarStudent);
                frame.add(splitPane);

                // // 按键栏
                // studnetButtonPanel = new JPanel();
                // studnetButtonPanel.setMaximumSize(new Dimension(800, 80));

                // Button_Item_SubmitAnswer(buttonSubmitAnswer);
                // Button_Item_RunCode(buttonRunCode);
                // Button_Item_ShowFeedback(buttonShowFeedback);

                // studnetButtonPanel.add(buttonSubmitAnswer);
                // studnetButtonPanel.add(buttonRunCode);
                // studnetButtonPanel.add(buttonShowFeedback);

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
                                // TODO Auto-generated method stub
                                new HomePage().init();
                                frame.dispose();
                                System.out.println("-- The Change Account Manu Button is Working --");
                        }
                });
        }

        // 退出程序
        private void Button_Item_ExitProgram(JMenuItem button) {
                button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                // TODO Auto-generated method stub
                                System.exit(0);
                                System.out.println("-- The Exit Manu Button is Working --");
                        }
                });
        }

        public boolean detectWhileLoop(String path) {
                String code = WAR.readText(path);
                String UPcode = code.toUpperCase();
                boolean bWHile = UPcode.contains("WHILE");
                return bWHile;
        }

        // Submit Answer
        public void Button_Item_SubmitAnswer(Object button) {
                ((AbstractButton) button).addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                                // get row index id; 选择question
                                int y = ChooseQuestionComponent.getSelectedRow();
                                if (y == -1) {
                                        JFrame jf = new JFrame();
                                        JOptionPane.showMessageDialog(jf, "Please Select A Question");

                                } else {
                                        // get student input code
                                        String pyCodeSolution = "\n" + StudentWorkingComponent.getEditAnswerString();
                                        WAR.checkSolutionSytaxError(pyCodeSolution);

                                        String answer = WAR.readText("./src/txt/PyCodeAnswer.txt");
                                        StudentWorkingComponent.terminalArea.setText(answer);

                                        // get question id；把选择的question id抓出来
                                        String id = (String) DIO.getData_id(y);
                                        String correctAnswer = (String) DIO.getData(y, 3);

                                        // select the mark scheme by question id(empty list)
                                        List<markScheme> mkl = new ArrayList<markScheme>();
                                        // input the marking scheme into 'mkl'
                                        mkl = DIO.getSelectedMarkScheme(id);

                                        // System.out.println(mkl.get(0).getScore());

                                        int score = KA.getKeyWordSocre(pyCodeSolution, correctAnswer, mkl);

                                        System.out.println(score);

                                        System.out.println("Button is Working! Submit Answer Code");
                                        // System.out.println("--- TEXT String Print ---:" + pyCodeAnswer);

                                        System.out.println("-- The Submit Answer Button is Working --");
                                }

                        }
                });
        }

        // Run Code
        public void Button_Item_RunCode(Object button) {
                ((AbstractButton) button).addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                // TODO Auto-generated method stub
                                //
                                String pyCodeSolution = StudentWorkingComponent.getEditAnswerString();
                                WAR.checkSolutionSytaxError(pyCodeSolution);
                                String answer = WAR.readText("./src/txt/PyCodeAnswer.txt");
                                StudentWorkingComponent.terminalArea.setText(answer);
                                System.out.println("-- The Run Code Button is Working --");
                        }
                });
        }

        // Show Feedback
        public void Button_Item_ShowFeedback(Object button) {
                ((AbstractButton) button).addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                                String solution = StudentWorkingComponent.getEditAnswerString();

                                if (solution.length() > 0) {
                                        boolean hasSyntaxError = WAR.checkSolutionSytaxError(solution);
                                        feedbackPage.setSyntaxErrorStatus(hasSyntaxError);

                                        String runResultMessage = WAR.readText(".src/txt/PyCodeAnswer.txt");
                                        feedbackPage.setRunResultMessage(runResultMessage);

                                        feedbackPage.updateMessageTextArea();

                                        System.out.println("Has Syntax Error or not: " + hasSyntaxError);
                                        System.out.println("Output or error from Python: " + runResultMessage);

                                } else {
                                        feedbackPage.setTextMessageTextArea("The editor window's empty");
                                        System.out.println("The editor window's empty");
                                }

                                // Make the pop up dialog center align to parent window
                                feedbackPage.setLocationRelativeTo(frame);
                                // Show the feedback dialog
                                feedbackPage.setVisible(true);
                                System.out.println("-- The Show Feedback Button is Working --");

                        }
                });
        }

        // Previous Question
        private void Button_Item_PreviousQuestion(JMenuItem button) {
                button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                ChooseQuestionComponent.setSelectedRow(
                                                ChooseQuestionComponent.chooseQuestionTable.getSelectedRow() - 1);
                                StudentWorkingComponent.setQuestionString(
                                                WriteAndRead.readQuestion(ChooseQuestionComponent.getValueAt_Table(
                                                                ChooseQuestionComponent.getSelectedRow(), 1)));
                                PythonCodeChackerPage.splitPane.setLeftComponent(new StudentWorkingComponent());
                                System.out.println("-- The Previous Question Button is Working --");
                        }
                });
        }

        // Next Question
        private void Button_Item_NextQuestion(JMenuItem button) {
                button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                ChooseQuestionComponent.setSelectedRow(
                                                ChooseQuestionComponent.chooseQuestionTable.getSelectedRow() + 1);
                                StudentWorkingComponent.setQuestionString(
                                                WriteAndRead.readQuestion(ChooseQuestionComponent.getValueAt_Table(
                                                                ChooseQuestionComponent.getSelectedRow(), 1)));
                                PythonCodeChackerPage.splitPane.setLeftComponent(new StudentWorkingComponent());
                                System.out.println("-- The Next Question Button is Working --");
                        }
                });
        }

        // Random Question
        private void Button_Item_RandomQuestion(JMenuItem button) {
                button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                // TODO Auto-generated method stub
                                System.out.println("-- The Random Question Button is Working --");
                        }
                });
        }

}
