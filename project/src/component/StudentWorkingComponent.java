package component;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.*;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import methodAndTool.ProjectVariable;
import view.PythonCodeChackerPage;

import javax.swing.event.*;
import javax.swing.text.Element;

public class StudentWorkingComponent extends Box {

        /**
         * 
        */
        PythonCodeChackerPage PCCP = new PythonCodeChackerPage();

        /**
         * 
        */
        Box box, midBox, topBox, downBox, editBox, lastBox;
        JScrollPane numListScrollPane, editScrollPane, terminalScrollPane;
        private static JTextArea lines;
        JLabel questionLabel;
        // 设置按钮
        JPanel studnetButtonPanel;
        JButton buttonSubmitAnswer = new JButton("Submit Answer");
        JButton buttonRunCode = new JButton("Run Code");
        JButton buttonShowFeedback = new JButton("Show Feedback");
        private static JTextArea editArea;
        public static JTextArea terminalArea;

        String[] data;

        int num = 1;
        public static String questionString = "<html><p>Are You Ready? Please Choose a Python Code Question: </p></html>";

        public StudentWorkingComponent() {
                super(BoxLayout.Y_AXIS);
                editScrollPane = new JScrollPane(editArea);
                lines = new JTextArea("1");
                lines.setBackground(Color.LIGHT_GRAY);
                lines.setEditable(false);

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

                editArea = new JTextArea();
                // editArea.setPreferredSize(new Dimension(700, 500));

                editArea.setLineWrap(true); // 自动换行

                editArea.setFont(myFont2);
                lines.setFont(myFont2);

                editArea.getDocument().addDocumentListener(new DocumentListener() {
                        public String getText() {
                                int caretPosition = editArea.getDocument().getLength();
                                Element root = editArea.getDocument().getDefaultRootElement();
                                String text = "1" + System.getProperty("line.separator");
                                for (int i = 2; i < root.getElementIndex(caretPosition) + 2; i++) {
                                        text += i + System.getProperty("line.separator");
                                }
                                return text;
                        }

                        @Override
                        public void changedUpdate(DocumentEvent de) {
                                lines.setText(getText());
                        }

                        @Override
                        public void insertUpdate(DocumentEvent de) {
                                lines.setText(getText());
                        }

                        @Override
                        public void removeUpdate(DocumentEvent de) {
                                lines.setText(getText());
                        }
                });

                editScrollPane.getViewport().add(editArea);
                editScrollPane.setRowHeaderView(lines);
                editScrollPane.setPreferredSize(new Dimension(700, 500));
                midBox.add(editScrollPane);

                this.add(midBox);

                //
                // downPanel = new JPanel();
                downBox = Box.createHorizontalBox();
                // 8,40
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

                // 按键栏
                studnetButtonPanel = new JPanel();
                studnetButtonPanel.setMaximumSize(new Dimension(800, 80));

                PCCP.Button_Item_SubmitAnswer(buttonSubmitAnswer);
                PCCP.Button_Item_RunCode(buttonRunCode);
                PCCP.Button_Item_ShowFeedback(buttonShowFeedback);

                studnetButtonPanel.add(buttonSubmitAnswer);
                studnetButtonPanel.add(buttonRunCode);
                studnetButtonPanel.add(buttonShowFeedback);

                this.add(studnetButtonPanel, BorderLayout.SOUTH);

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

        public static String getQusetionString() {
                return StudentWorkingComponent.questionString;
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
