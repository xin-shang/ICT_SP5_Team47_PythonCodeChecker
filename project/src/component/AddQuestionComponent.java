package component;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import methodAndTool.WriteAndRead;
import methodAndTool.StaffdataIO;

public class AddQuestionComponent extends Box implements ActionListener {

        WriteAndRead WAR = new WriteAndRead();

        // "ID", "Question-Stems", "Solution", "Answer", "ScorePoint"
        JLabel newID, newQuestion, newSolution, newAnswer, newScorePoint;
        static JTextArea newQuestion0;
        static JTextArea newSolution0;
        static JTextArea newAnswer0;
        JTextArea text0_SP;

        // 表格
        JTable showScorePoint;

        JTextField score;
        JPanel buttonPanel;
        JButton createNewQuestion, addScorePoint, deleteScorePoint;

        //
        Object[][] questionScorePoint = new Object[0][3];

        //
        private Vector titleScorePoint = new Vector(); // Store the title 存储标题
        private static Vector<Vector> dataScorePoint = new Vector<>(); // Store the data 存储数据

        public static DefaultTableModel tableModelScorePoint;

        public AddQuestionComponent() {

                super(BoxLayout.Y_AXIS);
                /**
                 * 设置窗口内容
                 */
                //
                newID = new JLabel("Add a New Question ID:" + (StaffdataIO.getDblength() + 1));

                //
                newQuestion = new JLabel("Please Write down Question Stem");
                newQuestion0 = new JTextArea(10, 10);
                newQuestion0.setLineWrap(true); // 自动换行

                Box boxQuestion0 = Box.createHorizontalBox();
                JScrollPane scrollPane_Question0 = new JScrollPane(newQuestion0);
                boxQuestion0.add(scrollPane_Question0);

                //
                newSolution = new JLabel("Please Write down Solution of Question");
                newSolution0 = new JTextArea(20, 10);
                newSolution0.setLineWrap(true); // 自动换行

                Box boxSolution0 = Box.createHorizontalBox();
                JScrollPane scrollPane_Solution0 = new JScrollPane(newSolution0);
                boxSolution0.add(scrollPane_Solution0);

                //
                newAnswer = new JLabel("Please Write down Answer of Question");
                newAnswer0 = new JTextArea(10, 10);
                newAnswer0.setLineWrap(true); // 自动换行
                newAnswer0.setEditable(false);

                Box boxAnswer0 = Box.createHorizontalBox();
                JScrollPane scrollPane_Answer0 = new JScrollPane(newAnswer0);
                boxAnswer0.add(scrollPane_Answer0);

                //
                Box ScorePointLabel = Box.createHorizontalBox();
                newScorePoint = new JLabel("Please Write down Score Point of Question");
                ScorePointLabel.add(newScorePoint);

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
                                return false;
                        }
                };
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
                buttonPanel.setMaximumSize(new Dimension(500, 80));

                createNewQuestion = new JButton("Create New Question");
                createNewQuestion.addActionListener(this);

                addScorePoint = new JButton("Add Score Point");
                addScorePoint.addActionListener(this);

                deleteScorePoint = new JButton("Delete Score Point");
                deleteScorePoint.addActionListener(this);

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
                box.add(ScorePointLabel);
                box.add(ScorePointTable);

                // JScrollPane scrollPane = new JScrollPane(box);

                buttonPanel.add(addScorePoint);
                buttonPanel.add(createNewQuestion);
                buttonPanel.add(deleteScorePoint);

                this.add(box);
                // this.add(scrollPane);
                this.add(buttonPanel, BorderLayout.SOUTH);

        }

        // /**
        // * 按钮监听
        // */
        @Override
        public void actionPerformed(ActionEvent e) {
                String actionCommand = e.getActionCommand();
                if (actionCommand.equals("Add Score Point")) {
                        try {
                                KeywordManagerComponent
                                                .setSelectedRow(KeywordManagerComponent.keywordTable.getSelectedRow());

                                // System.out.println(KeywordManagerComponent.getSelectedRow());

                                Vector t = new Vector<>();

                                for (int j = 0; j < titleScorePoint.size(); j++) {
                                        t.add(KeywordManagerComponent
                                                        .getValueAt_Table(KeywordManagerComponent.getSelectedRow(), j));
                                }
                                tableModelScorePoint.addRow(t);

                                // dataScorePoint.add(t);
                        } catch (Exception w) {
                                JOptionPane.showMessageDialog(this, "Please Select a Line");
                        }
                        System.out.println("-- The Create New Question is Working --");
                } else if (actionCommand.equals("Delete Score Point")) {
                        try {
                                tableModelScorePoint.removeRow(showScorePoint.getSelectedRow());
                        } catch (Exception w) {
                                JOptionPane.showMessageDialog(this, "Please Select a Line");
                        }
                        System.out.println("-- The Create New Question is Working --");
                } else if (actionCommand.equals("Create New Question")) {

                        if (bcheckUserInputValue() == true) {
                                String solution = getNewSolutionString();
                                boolean bsyntaxError = WAR.checkSolutionSytaxError(solution);

                                if (bsyntaxError == true) {
                                        String syntaxError = WAR.readText("./src/txt/PyCodeAnswer.txt");
                                        JOptionPane.showMessageDialog(this,
                                                        "Your Solution has SyntaxError: " + syntaxError);
                                        newAnswer0.setText(syntaxError);
                                } else {
                                        String answer = WAR.readText("./src/txt/PyCodeAnswer.txt");
                                        WAR.write2TextFileOutStream("./src/dbData/POST/dbAnswer_POST.txt", answer);

                                        StaffdataIO.PostNewQuestionString();
                                        StaffdataIO.PostNewSolutionString();
                                        StaffdataIO.PostNewAnswerString();
                                        WAR.run_python_code("./src/pythonDB/PYDb_addQuestion.py");
                                        getScorePointStringList();
                                        JOptionPane.showMessageDialog(this, "Upload Successful");
                                }

                        }

                        System.out.println("-- The Create New Question is Working --");
                }
        }

        public boolean bcheckUserInputValue() {
                boolean bmarkShceme = bcheckMarkSchemeEmpty();
                boolean question = getNewQuestionString().isEmpty();
                boolean solution = getNewSolutionString().isEmpty();

                if (bmarkShceme == true && question == false && solution == false) {
                        JOptionPane.showMessageDialog(this, "Please Insert Mark Scheme");
                        return false;
                } else if (bmarkShceme == false && question == true && solution == false) {
                        JOptionPane.showMessageDialog(this, "Please Insert Question");
                        return false;
                } else if (bmarkShceme == false && question == false && solution == true) {
                        JOptionPane.showMessageDialog(this, "Please Insert Solution");
                        return false;
                } else if (bmarkShceme == true && question == true && solution == true) {
                        JOptionPane.showMessageDialog(this, "Please Insert Question");
                        return false;
                } else if (bmarkShceme == false && question == false && solution == false) {
                        return true;
                } else if (bmarkShceme == true && question == true && solution == false) {
                        JOptionPane.showMessageDialog(this, "Please Insert Question");
                        return false;
                } else if (bmarkShceme == true && question == false && solution == true) {
                        JOptionPane.showMessageDialog(this, "Please Insert Solution");
                        return false;
                } else if (bmarkShceme == false && question == true && solution == true) {
                        JOptionPane.showMessageDialog(this, "Please Insert Mark Scheme");
                        return false;
                } else {
                        return false;
                }
        }

        /**
         * 内容获取
         */
        // String newAnswerString = newAnswer0.getText().trim();

        // Get New Questions 获取新问题
        public static String getNewQuestionString() {
                String newQuestionString = newQuestion0.getText().trim();
                return newQuestionString;
        }

        // Get New Solution 获取新解决方案
        public static String getNewSolutionString() {
                String newSolutionString = newSolution0.getText().trim();
                return newSolutionString;
        }

        public static String getNewAnswerString() {
                String newAnswerString = newAnswer0.getText().trim();
                return newAnswerString;
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

        public void getScorePointStringList() {
                for (int i = 0; i < getScorePointRowCount(); i++) {
                        for (int j = 0; j < getScorePointColumnCount(); j++) {
                                if (j == 1) {
                                        Object keyword = getValueAt(i, j);
                                        WAR.write2TextFileOutStream("./src/dbData/POST/markPoint/dbKeyWord_POST.txt",
                                                        keyword.toString());
                                } else if (j == 2) {
                                        Object score = (int) getValueAt(i, j);
                                        WAR.write2TextFileOutStream("./src/dbData/POST/markPoint/dbScore_POST.txt",
                                                        score.toString());
                                }

                        }
                        WAR.run_python_code("./src/pythonDB/PYDb_addMarkScheme.py");
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

        // public void Button_Add_ScorePoint(JButton button) {
        // button.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // KeywordManagerComponent.setSelectedRow(KeywordManagerComponent.keywordTable.getSelectedRow());
        // System.out.println(KeywordManagerComponent.getSelectedRow());
        // // forLoopSelectedRows();
        // // addScorePointInTable();
        // System.out.println("-- The Add ScorePoint Button is Working --");
        // }
        // });
        // }

        /**
         * 数据获取
         */
        // 用在ScorePointInTable
        // private int num = 0;

        // public void addScorePointInTable(){
        // Vector t = new Vector<>();
        // for(int i = 0; i < KeywordManagerComponent.getSelectedRows().length; i++){
        // for(int j = 0; j < titleScorePoint.size(); j++){
        // t.add(KeywordManagerComponent.getValueAt_Table(KeywordManagerComponent.getSelectedRows()[i],
        // j));
        // System.out.println("Y"+j);
        // }
        // System.out.println("X"+i);
        // }
        // dataScorePoint.add(t);
        // // num = num + 1;
        // }

        // public void forLoopSelectedRows(){
        // for(int i = 0; i < KeywordManagerComponent.getSelectedRows().length; i++){
        // num = KeywordManagerComponent.getSelectedRows()[i];
        // System.out.println(num);
        // }
        // System.out.println("----------");
        // }

}
