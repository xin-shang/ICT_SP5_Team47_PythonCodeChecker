package component;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import JDBC.QNS.GroupTable.staffQns_T;
import methodAndTool.WriteAndRead;
import methodAndTool.markScheme;

public class ChangeQuestionComponent extends Box implements ActionListener {

        WriteAndRead WAR = new WriteAndRead();
        staffQns_T DIO = new staffQns_T();

        // int num = 0;

        // "ID", "Question-Stems", "Solution", "Answer", "ScorePoint"
        String question_id;
        JLabel cID, cQuestion, cSolution, cAnswer, cScorePoint;
        static JTextArea cQuestion0;
        static JTextArea cSolution0;
        static JTextArea cAnswer0;

        // 表格
        JTable cShowScorePoint;

        JPanel buttonPanel;
        JButton updateQuestion, addScorePoint, deleteScorePoint;

        //
        Object[][] questionScorePoint = new Object[0][3];

        //
        private Vector<Object> cTitleScorePoint = new Vector<Object>(); // Store the title 存储标题
        private static Vector<Vector<Object>> cDataScorePoint = new Vector<>(); // Store the data 存储数据

        public static DefaultTableModel cTableModelScorePoint;

        // value to compare
        final String question_before;
        final String solution_before;
        final List<markScheme> markSchemeList_before;

        public ChangeQuestionComponent() {

                super(BoxLayout.Y_AXIS);

                question_id = (String) QuestionManagerComponent
                                .getValueAt_Table(QuestionManagerComponent.getSelectedRow(), 0);
                List<markScheme> markSchemeList = DIO.getSelectedMarkScheme(question_id);

                /**
                 * 设置窗口内容
                 */

                // ID
                cID = new JLabel("Question ID: " + (question_id));

                // 问题
                cQuestion = new JLabel("Question Stem: ");
                cQuestion0 = new JTextArea(WAR.readString(DIO.getData(QuestionManagerComponent.getSelectedRow(), 1)),
                                10, 10);
                cQuestion0.setLineWrap(true); // 自动换行

                Box boxQuestion0 = Box.createHorizontalBox();
                JScrollPane scrollPane_Question0 = new JScrollPane(cQuestion0);
                boxQuestion0.add(scrollPane_Question0);

                // 解决方法
                cSolution = new JLabel("Solution of Question: ");
                cSolution0 = new JTextArea(WAR.readString(DIO.getData(QuestionManagerComponent.getSelectedRow(), 2)),
                                20, 10);
                cSolution0.setLineWrap(true); // 自动换行

                Box boxSolution0 = Box.createHorizontalBox();
                JScrollPane scrollPane_Solution0 = new JScrollPane(cSolution0);
                boxSolution0.add(scrollPane_Solution0);

                // 答案
                cAnswer = new JLabel("Answer of Question: ");
                cAnswer0 = new JTextArea(
                                WAR.readString(QuestionManagerComponent
                                                .getValueAt_Table(QuestionManagerComponent.getSelectedRow(), 3)),
                                10, 10);
                cAnswer0.setLineWrap(true); // 自动换行
                cAnswer0.setEditable(false);

                Box boxAnswer0 = Box.createHorizontalBox();
                JScrollPane scrollPane_Answer0 = new JScrollPane(cAnswer0);
                boxAnswer0.add(scrollPane_Answer0);

                // 得分点
                cScorePoint = new JLabel("Score Point of Question: ");

                Box ScorePointTable = Box.createHorizontalBox();

                cDataScorePoint.clear();

                for (int i = 0; i < KeywordManagerComponent.titles.length; i++) {
                        cTitleScorePoint.add(KeywordManagerComponent.titles[i]);
                }

                for (int i = 0; i < markSchemeList.size(); i++) {
                        Vector<Object> t = new Vector<Object>(); // <Vector> 用来接收二维数组中第二个维度的信息
                        for (int j = 0; j <= 2; j++) { // data[i].length 用来录入每个大数组中子数组的信息
                                if (j == 0) {
                                        t.add(i + 1);
                                } else if (j == 1) {
                                        t.add(markSchemeList.get(i).getKeyword());
                                } else if (j == 2) {
                                        t.add(markSchemeList.get(i).getScore());
                                }

                        }
                        cDataScorePoint.add(t); // 依次把第二维加入一维中
                }

                // 整合
                cTableModelScorePoint = new DefaultTableModel(cDataScorePoint, cTitleScorePoint);
                // 整合 & 让questionTable中的内容不可编辑
                cShowScorePoint = new JTable(cTableModelScorePoint) {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                                if (column == 2) {
                                        return true;
                                } else {
                                        return false;
                                }
                        }
                };

                JScrollPane scrollPane_ScoreTable = new JScrollPane(cShowScorePoint);
                ScorePointTable.add(scrollPane_ScoreTable);

                //
                // JButton updateQuestion, addScorePoint, deleteScorePoint;
                buttonPanel = new JPanel();
                buttonPanel.setMaximumSize(new Dimension(500, 80));

                updateQuestion = new JButton("Update Question");
                updateQuestion.addActionListener(this);

                addScorePoint = new JButton("Add Score Point");
                addScorePoint.addActionListener(this);

                deleteScorePoint = new JButton("Delete Score Point");
                deleteScorePoint.addActionListener(this);

                /**
                 * 组装零件
                 */
                Box box = Box.createVerticalBox();
                box.add(cID);
                box.add(Box.createVerticalStrut(10));
                box.add(cQuestion);
                box.add(boxQuestion0);
                box.add(Box.createVerticalStrut(10));
                box.add(cSolution);
                box.add(boxSolution0);
                box.add(Box.createVerticalStrut(10));
                box.add(cAnswer);
                box.add(boxAnswer0);
                box.add(Box.createVerticalStrut(10));
                box.add(cScorePoint);
                box.add(ScorePointTable);

                buttonPanel.add(addScorePoint);
                buttonPanel.add(updateQuestion);
                buttonPanel.add(deleteScorePoint);

                this.add(box);
                this.add(buttonPanel, BorderLayout.SOUTH);

                question_before = cQuestion0.getText().trim();
                solution_before = cSolution0.getText().trim();
                markSchemeList_before = markSchemeList;
        }

        /**
         * 按钮监听ActionEvent e
         */
        @Override
        public void actionPerformed(ActionEvent e) {

                String actionCommand = e.getActionCommand();

                if (actionCommand.equals("Add Score Point")) {
                        try {
                                KeywordManagerComponent
                                                .setSelectedRow(KeywordManagerComponent.keywordTable.getSelectedRow());

                                Vector<Object> t = new Vector<Object>();

                                for (int j = 0; j < cTitleScorePoint.size(); j++) {
                                        t.add(KeywordManagerComponent
                                                        .getValueAt_Table(KeywordManagerComponent.getSelectedRow(), j));
                                }
                                cTableModelScorePoint.addRow(t);

                                // dataScorePoint.add(t);
                        } catch (Exception w) {
                                JOptionPane.showMessageDialog(this, "Please Select a Line");
                        }
                        System.out.println("-- The Create New Question is Working --");
                }

                else if (actionCommand.equals("Delete Score Point")) {
                        try {
                                cTableModelScorePoint.removeRow(cShowScorePoint.getSelectedRow());
                        } catch (Exception w) {
                                JOptionPane.showMessageDialog(this, "Please Select a Line");
                        }
                        System.out.println("-- The Create New Question is Working --");
                }

                else if (actionCommand.equals("Update Question")) {
                        System.out.println(question_before);
                }
        }

        public void ComfirmeChangedValue() {
                if (!this.question_before.equals(getUpdateQuestionString())) {

                }
                if (!this.solution_before.equals(getUpdateSolutionString())) {

                }

        }

        /**
         * 内容获取
         */
        public String getUpdateQuestionString() {
                String newQuestionString = cQuestion0.getText().trim();
                return newQuestionString;
        }

        public String getUpdateQuestionID() {
                return this.question_id;
        }

        // Get New Solution 获取新解决方案
        public String getUpdateSolutionString() {
                String newSolutionString = cSolution0.getText().trim();
                return newSolutionString;
        }

        public String getUpdateAnswerString() {
                String newAnswerString = cAnswer0.getText().trim();
                return newAnswerString;
        }

        // Getting Number of Row 获取行数
        public static int getScorePointRowCount() {
                return cDataScorePoint.size();
        }

        // Getting Number of Columns 获取列数
        public static int getScorePointColumnCount() {
                int dataScorePointColumnCount = cDataScorePoint.firstElement().size();
                return dataScorePointColumnCount;
        }

        // 获取指定格子中的数据
        public static Object getValueAt(int row, int column) {
                return cDataScorePoint.get(row).get(column);
        }

        // 通过列表的长度来判断用户是否有输入markScheme 没有：返回False， 有：返回True
        public boolean bcheckMarkSchemeEmpty() {
                int rows = cTableModelScorePoint.getRowCount();
                if (rows > 0) {
                        return false;
                } else {
                        return true;
                }
        }

}
