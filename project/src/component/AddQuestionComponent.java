package component;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.text.PlainDocument;

import javax.swing.table.DefaultTableModel;

import JDBC.QNS.GroupTable.staffQns_T;
import JDBC.QNS.SingleTable.keywordAlternative_T;
import JDBC.dbConnection.PythonCodeChecker_db;
import methodAndTool.ProjectVariable;
import methodAndTool.RunPythonCode;
import methodAndTool.WriteAndRead;
import view.PythonQuestionEditPage;
import methodAndTool.ChangeTabToSpacesFilter;

public class AddQuestionComponent extends Box implements ActionListener {

        WriteAndRead WAR = new WriteAndRead();
        ProjectVariable PV = new ProjectVariable();
        staffQns_T DIO;
        keywordAlternative_T QKC;

        // "ID", "Question-Stems", "Solution", "Answer", "ScorePoint"
        JLabel newID, newQuestion, newSolution, newAnswer, newAnswerScore, newScorePoint;
        static JTextArea newQuestion0;
        static JTextArea newSolution0;
        public static JTextArea newAnswer0;
        JTextArea text0_SP;

        JComboBox<String> patternList;

        // 表格
        JTable showScorePoint;
        JPanel buttonPanel;
        JButton createNewQuestion, addSelectedScorePoint, deleteScorePoint, addInput;

        //
        Object[][] questionScorePoint = new Object[0][3];

        // value to compfirm user had filled the input
        boolean bInput;

        //
        private Vector<Object> titleScorePoint = new Vector<Object>(); // Store the title 存储标题
        private static Vector<Vector<Object>> dataScorePoint = new Vector<>(); // Store the data 存储数据

        public static DefaultTableModel tableModelScorePoint;

        public AddQuestionComponent(staffQns_T dio, keywordAlternative_T QKC) {

                super(BoxLayout.Y_AXIS);
                this.DIO = dio;
                this.QKC = QKC;
                /**
                 * 设置窗口内容
                 */
                //

                //
                newID = new JLabel("Add a New Question");

                //
                newQuestion = new JLabel("Please Write down Question Stem");
                newQuestion0 = new JTextArea(10, 10);
                newQuestion0.setLineWrap(true); // 自动换行

                newQuestion0.setTabSize(1);
                Box boxQuestion0 = Box.createHorizontalBox();
                JScrollPane scrollPane_Question0 = new JScrollPane(newQuestion0);
                boxQuestion0.add(scrollPane_Question0);

                //
                newSolution = new JLabel("Please Write down Solution of Question");
                newSolution0 = new JTextArea(20, 10);
                newSolution0.setLineWrap(true); // 自动换行

                int spaceCount = 4;
                ((PlainDocument) newSolution0.getDocument()).setDocumentFilter(new ChangeTabToSpacesFilter(spaceCount));

                Box boxSolution0 = Box.createHorizontalBox();
                JScrollPane scrollPane_Solution0 = new JScrollPane(newSolution0);
                boxSolution0.add(scrollPane_Solution0);

                newAnswer = new JLabel("Answer of Question (The Answer Will Be Calculated After Submit)");
                newAnswer0 = new JTextArea(10, 10);
                newAnswer0.setLineWrap(true); // 自动换行
                newAnswer0.setEditable(false);

                Box boxAnswer0 = Box.createHorizontalBox();
                JScrollPane scrollPane_Answer0 = new JScrollPane(newAnswer0);
                boxAnswer0.add(scrollPane_Answer0);

                newAnswerScore = new JLabel("Please Select A Score For Answer");
                patternList = new JComboBox<String>(PV.getAnswerScoreList());
                // enable edit for drop down list
                // patternList.setEditable(true);

                //
                newScorePoint = new JLabel("Please Write down Score Point of Question");

                Box ScorePointTable = Box.createHorizontalBox();
                /*
                 * _________________________________________________________________________________
                 */
                /*
                 * _________________________________________________________________________________
                 */

                dataScorePoint.clear();

                for (int i = 0; i < KeywordManagerComponent.titles.length; i++) {
                        titleScorePoint.add(KeywordManagerComponent.titles[i]);
                }

                // Button_Add_ScorePoint(addScorePoint);

                // 整合
                tableModelScorePoint = new DefaultTableModel(dataScorePoint, titleScorePoint);
                // 整合 & 让questionTable中的内容不可编辑
                showScorePoint = new JTable(tableModelScorePoint) {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                                if (column == 2) {
                                        return true;
                                } else if (column == 1) {
                                        return true;
                                } else {
                                        return false;
                                }
                        }
                };

                // 行高
                showScorePoint.setRowHeight(24);
                /*
                 * _________________________________________________________________________________
                 */
                /*
                 * _________________________________________________________________________________
                 */

                JScrollPane scrollPane_ScoreTable = new JScrollPane(showScorePoint);
                ScorePointTable.add(scrollPane_ScoreTable);

                //
                buttonPanel = new JPanel();
                buttonPanel.setMaximumSize(new Dimension(1000, 80));

                createNewQuestion = new JButton("Submit Question");
                createNewQuestion.addActionListener(this);

                addSelectedScorePoint = new JButton("Add Score Point");
                addSelectedScorePoint.addActionListener(this);

                deleteScorePoint = new JButton("Delete Score Point");
                deleteScorePoint.addActionListener(this);

                addInput = new JButton("Add Input");
                addInput.addActionListener(this);

                /**
                 * 组装零件
                 */
                Box box = Box.createVerticalBox();
                box.add(newID);
                box.add(Box.createVerticalStrut(10));
                box.add(newQuestion);
                box.add(boxQuestion0);
                box.add(Box.createVerticalStrut(10));
                box.add(newSolution);
                box.add(boxSolution0);
                box.add(Box.createVerticalStrut(10));
                box.add(newAnswer);
                box.add(boxAnswer0);

                box.add(Box.createVerticalStrut(10));
                box.add(newAnswerScore);
                box.add(patternList);

                box.add(Box.createVerticalStrut(10));
                box.add(newScorePoint);
                box.add(ScorePointTable);

                buttonPanel.add(addSelectedScorePoint);
                buttonPanel.add(createNewQuestion);
                buttonPanel.add(deleteScorePoint);
                buttonPanel.add(addInput);

                this.add(box);
                // this.add(scrollPane);
                this.add(buttonPanel, BorderLayout.SOUTH);

        }

        /**
         * 按钮监听
         */
        @Override
        public void actionPerformed(ActionEvent e) {
                String actionCommand = e.getActionCommand();
                if (actionCommand.equals("Add Score Point")) {
                        try {
                                KeywordManagerComponent
                                                .setSelectedRow(KeywordManagerComponent.keywordTable.getSelectedRow());

                                // System.out.println(KeywordManagerComponent.getSelectedRow());

                                Vector<Object> t = new Vector<Object>();

                                for (int j = 0; j < titleScorePoint.size(); j++) {
                                        t.add(KeywordManagerComponent
                                                        .getValueAt_Table(KeywordManagerComponent.getSelectedRow(), j));
                                }
                                tableModelScorePoint.addRow(t);

                                // dataScorePoint.add(t);
                        } catch (Exception w) {
                                JOptionPane.showMessageDialog(this, "Please Select A Keyword On the Left Pannel");
                        }
                        System.out.println("-- The Create New Question is Working --");
                }

                else if (actionCommand.equals("Delete Score Point")) {
                        try {
                                tableModelScorePoint.removeRow(showScorePoint.getSelectedRow());
                        } catch (Exception w) {
                                JOptionPane.showMessageDialog(this, "Please Select a Line");
                        }
                        System.out.println("-- The Create New Question is Working --");
                }

                else if (actionCommand.equals("Add Input")) {

                        if (getNewSolutionString().contains("input()")) {
                                String solution = getNewSolutionString();
                                RunPythonCode RP = new RunPythonCode();
                                RP.saveCodeFile(solution);
                                RP.runCode(3);
                                bInput = true;
                        } else {
                                JOptionPane.showMessageDialog(this, "your solution don't have input()");
                        }

                }

                else if (actionCommand.equals("Submit Question")) {

                        try {
                                String solution = getNewSolutionString();
                                Connection conn = new PythonCodeChecker_db().get_connection();

                                if (solution.contains("input()") && bInput != true) {
                                        JOptionPane.showMessageDialog(this,
                                                        "Please fill in the input first");
                                } else if (bInput == true) {
                                        if (checkBoxEmpty() == true) {
                                                String keywordNotInString = PV.bCheckKeywordNotInString(dataScorePoint,
                                                                solution);
                                                if (keywordNotInString == null) {
                                                        if (!RunPythonCode.errorMessage.equals("")) {
                                                                String errormessage = RunPythonCode.errorMessage;
                                                                JOptionPane.showMessageDialog(this,
                                                                                "Your Solution has SyntaxError: \n"
                                                                                                + errormessage);
                                                                newAnswer0.setText(errormessage);
                                                        } else {
                                                                String answer = RunPythonCode.outputFromConsole;
                                                                newAnswer0.setText(answer);

                                                                boolean b_score = checkSocre();

                                                                if (b_score == true) {
                                                                        boolean b_add_q;
                                                                        try {
                                                                                b_add_q = DIO.insertQuestion(conn,
                                                                                                this.getNewQuestionString(),
                                                                                                this.getNewSolutionString(),
                                                                                                answer,
                                                                                                this.getNewAnswerScore());
                                                                                if (b_add_q == true) {
                                                                                        getScorePointStringList(conn);
                                                                                        JOptionPane.showMessageDialog(
                                                                                                        this,
                                                                                                        "Added Successful");
                                                                                        PythonQuestionEditPage.splitPane
                                                                                                        .setLeftComponent(
                                                                                                                        new QuestionManagerComponent(
                                                                                                                                        new staffQns_T(conn),
                                                                                                                                        QKC));
                                                                                } else {
                                                                                        JOptionPane.showMessageDialog(
                                                                                                        this,
                                                                                                        "Question is already exit");
                                                                                }
                                                                        } catch (SQLException e1) {

                                                                                e1.printStackTrace();
                                                                        }
                                                                }
                                                        }
                                                }
                                        }

                                } else {

                                        if (checkBoxEmpty() == true) {
                                                String keywordNotInString = PV.bCheckKeywordNotInString(dataScorePoint,
                                                                solution);

                                                if (keywordNotInString == null) {

                                                        RunPythonCode RP = new RunPythonCode();
                                                        RP.saveCodeFile(solution);
                                                        RP.runCode(3);

                                                        if (!RunPythonCode.errorMessage.equals("")) {
                                                                String errormessage = RunPythonCode.errorMessage;
                                                                JOptionPane.showMessageDialog(this,
                                                                                "Your Solution has SyntaxError: \n"
                                                                                                + errormessage);
                                                                newAnswer0.setText(errormessage);
                                                        } else {
                                                                String answer = RunPythonCode.outputFromConsole;
                                                                newAnswer0.setText(answer);

                                                                boolean b_score = checkSocre();

                                                                if (b_score == true) {
                                                                        boolean b_add_q;
                                                                        try {
                                                                                b_add_q = DIO.insertQuestion(conn,
                                                                                                this.getNewQuestionString(),
                                                                                                this.getNewSolutionString(),
                                                                                                answer,
                                                                                                this.getNewAnswerScore());
                                                                                if (b_add_q == true) {
                                                                                        getScorePointStringList(conn);
                                                                                        JOptionPane.showMessageDialog(
                                                                                                        this,
                                                                                                        "Added Successful");
                                                                                        PythonQuestionEditPage.splitPane
                                                                                                        .setLeftComponent(
                                                                                                                        new QuestionManagerComponent(
                                                                                                                                        new staffQns_T(conn),
                                                                                                                                        QKC));

                                                                                } else {
                                                                                        JOptionPane.showMessageDialog(
                                                                                                        this,
                                                                                                        "Question is already exit");

                                                                                }
                                                                        } catch (SQLException e1) {

                                                                                e1.printStackTrace();
                                                                        }
                                                                }
                                                        }

                                                }

                                        }

                                        System.out.println("-- The Create New Question is Working --");

                                }
                                conn.close();

                        } catch (SQLException e1) {
                                e1.printStackTrace();
                        }
                }

        }

        private boolean checkBoxEmpty() {
                boolean b_markShceme = bcheckMarkSchemeEmpty();
                boolean b_question = getNewQuestionString().isEmpty();
                boolean b_solution = getNewSolutionString().isEmpty();

                if (PV.bcheckUserInputValue(b_markShceme, b_question, b_solution) == true) {
                        return true;
                } else {
                        return false;
                }
        }

        /**
         * 内容获取
         */
        // String newAnswerString = newAnswer0.getText().trim();

        // Get New Questions 获取新问题
        public String getNewQuestionString() {
                String newQuestionString = newQuestion0.getText().trim();
                return newQuestionString;
        }

        // Get New Solution 获取新解决方案
        public String getNewSolutionString() {
                String newSolutionString = newSolution0.getText().trim();
                return newSolutionString;
        }

        public String getNewAnswerString() {
                String newAnswerString = newAnswer0.getText().trim();
                return newAnswerString;
        }

        public int getNewAnswerScore() {
                int answerScore = PV.StringToInt(patternList.getSelectedItem().toString());
                return answerScore;
        }

        // Object[][] questionScorePoint V<V> dataScorePoint
        // Getting Number of String 获取字符串
        public static String getScorePointString() {
                String newDataScorePoint = dataScorePoint.toString().trim();
                return newDataScorePoint;
        }

        // Getting Number of Row 获取行数
        public static int getScorePointRowCount() {
                return dataScorePoint.size();
        }

        // Getting Number of Columns 获取列数
        public static int getScorePointColumnCount() {
                int dataScorePointColumnCount = dataScorePoint.firstElement().size();
                return dataScorePointColumnCount;
        }

        public boolean checkSocre() {
                int totalScore = 0;
                for (int i = 0; i < getScorePointRowCount(); i++) {

                        for (int j = 0; j < getScorePointColumnCount(); j++) {
                                if (j == 2) {
                                        Object SignlePoint = getValueAt(i, j);
                                        totalScore += PV.castObjectToInt(SignlePoint);
                                }
                        }
                }

                int answerScore = getNewAnswerScore();
                totalScore += answerScore;

                if (totalScore == 100) {
                        return true;
                } else {
                        JOptionPane.showMessageDialog(this,
                                        "Total Score Should Be 100" + "\nnow is: " + totalScore + " !!!");
                        return false;
                }

        }

        // Push score list to db
        public void getScorePointStringList(Connection conn) throws SQLException {
                Object keyword = null;
                Object score = null;
                for (int i = 0; i < getScorePointRowCount(); i++) {

                        for (int j = 0; j < getScorePointColumnCount(); j++) {
                                if (j == 1) {
                                        keyword = getValueAt(i, j);
                                } else if (j == 2) {
                                        score = getValueAt(i, j);
                                }
                        }
                        String keyword_s = (String) keyword;

                        int score_int = PV.castObjectToInt(score);

                        DIO.insertQuestionMarkSheme(conn, getNewQuestionString(), keyword_s, score_int);

                }
        }

        // 获取指定格子中的数据
        public static Object getValueAt(int row, int column) {
                return dataScorePoint.get(row).get(column);
        }

        // 通过列表的长度来判断用户是否有输入markScheme 没有：返回False， 有：返回True
        public boolean bcheckMarkSchemeEmpty() {
                int rows = tableModelScorePoint.getRowCount();
                if (rows > 0) {
                        return false;
                } else {
                        return true;
                }
        }

}
