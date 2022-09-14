package component;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

import methodAndTool.ProjectVariable;
import methodAndTool.WriteAndRead;
import view.PythonCodeChackerPage;

public class StudentWorkingComponent extends Box {

        /**
         * 
        */
        Box box, midBox, topBox, downBox, editBox, lastBox;
        JScrollPane numListScrollPane, editScrollPane, terminalScrollPane;
        JLabel questionLabel;
        private static JTextArea editArea;
        public static JTextArea terminalArea;

        JList numList = new JList();
        DefaultListModel numListModel = new DefaultListModel();

        int num = 1;
        public static String questionString = "<html><p>Are You Ready? Please Choose a Python Code Question: </p></html>";

        public StudentWorkingComponent() {
                super(BoxLayout.Y_AXIS);

                /**
                 * 
                */

                //
                Font myFont1 = new Font("Arial", Font.PLAIN, 16);

                ProjectVariable pv = new ProjectVariable();

                Font myFont2 = pv.getUserTextfieldFontSize();

                //
                PythonCodeChackerPage.splitPane.setDividerLocation(900);

                topBox = Box.createHorizontalBox();
                // questionLabel = new
                // JLabel(WriteAndRead.readQuestion(ChooseQuestionComponent.getValueAt_Table(ChooseQuestionComponent.getSelectedRow(),
                // 1)));
                questionLabel = new JLabel();
                questionLabel.setText(getQusetionString());
                questionLabel.setFont(myFont1);
                questionLabel.setPreferredSize(new Dimension(900, 50));
                topBox.add(questionLabel);

                //
                midBox = Box.createHorizontalBox();

                numListModel.clear();
                numListModel.addElement(1);
                numList.setModel(numListModel);
                numList = new JList<Integer>();
                numList.setPreferredSize(new Dimension(2, 500));
                numList.setFixedCellWidth(25);
                numList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 只能选一行
                numListScrollPane = new JScrollPane(numList);
                numListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

                editArea = new JTextArea(25, 100);
                editArea.setLineWrap(true); // 自动换行
                editArea.setFont(myFont2);
                editArea.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyTyped(KeyEvent e) {
                                if ((char) e.getKeyChar() == KeyEvent.VK_ENTER) {
                                        setNum(num + 1);
                                        addItem();
                                }

                                // 想写一个监听删除按钮后，对比文本框中的行数和变量num的值。List减掉最后一个数。
                                else if ((char) e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                                        System.out.println("--Delete--");
                                        if (editArea.getLineCount() < getNum()) {

                                                setNum(num - 1);
                                                deleteItem();
                                                System.out.println("--Delete--");
                                        }

                                }
                        }
                });

                editScrollPane = new JScrollPane(editArea);

                midBox.add(numListScrollPane);
                midBox.add(editScrollPane);

                this.add(midBox);

                //
                // downPanel = new JPanel();
                downBox = Box.createHorizontalBox();
                terminalArea = new JTextArea(8, 40);
                terminalArea.setLineWrap(true); // 自动换行
                terminalArea.setEditable(false);// 不可编辑

                terminalScrollPane = new JScrollPane(terminalArea);
                terminalScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                downBox.add(terminalScrollPane);

                box = Box.createVerticalBox();
                box.add(topBox, BorderLayout.NORTH);
                box.add(Box.createVerticalStrut(10));
                box.add(midBox);
                box.add(Box.createVerticalStrut(10));
                box.add(downBox, BorderLayout.SOUTH);

                lastBox = Box.createHorizontalBox();
                lastBox.add(Box.createHorizontalStrut(20));
                lastBox.add(box);

                this.add(lastBox);

        }

        /**
         * 数据获取
         */
        // private void setQuestionLabelString(String question) {
        // this.questionLabel.setText(question);
        // }

        // private String getQuestionString() {
        // return this.questionLabel.getText();
        // }

        public static void setQuestionString(String question) {
                StudentWorkingComponent.questionString = question;
        }

        public String getQusetionString() {
                return StudentWorkingComponent.questionString;
        }

        private void setNum(int num) {
                this.num = num;
        }

        private int getNum() {
                return this.num;
        }

        private void addItem() {
                numListModel.addElement(getNum());
                numList.setModel(numListModel);
        }

        private void deleteItem() {
                numListModel.remove(getNum());
                numList.setModel(numListModel);
        }

        public static String getEditAnswerString() {
                return editArea.getText();
        }

        // public static String getTerminalString() {
        // return terminalArea.getText();
        // }

        // public static void setTerminalString(String userOutput) {
        // StudentWorkingComponent.terminalArea.setText(userOutput);
        // }

}
