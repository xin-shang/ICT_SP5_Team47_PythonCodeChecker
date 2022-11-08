package component;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.*;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import methodAndTool.ProjectVariable;
import methodAndTool.ColorSet;

import view.PythonCodeCheckerPage;

import javax.swing.event.*;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Element;

public class StudentWorkingComponent extends Box {

        /**
         * 
        */
        PythonCodeCheckerPage PCCP = new PythonCodeCheckerPage();

        /**
         * 
        */
        Box box, midBox, topBox, downBox, editBox, lastBox;
        JScrollPane numListScrollPane, editScrollPane, terminalScrollPane;
        private static JTextPane lines;
        JLabel questionLabel;
        // 设置按钮
        JPanel studnetButtonPanel;
        JButton buttonSubmitAnswer = new JButton("Submit Answer");
        JButton buttonRunCode = new JButton("Run Code");

        JButton buttonInput = new JButton("Add Input");

        public static JTextPane editTextPane;

        public static JTextArea terminalArea;
        String[] data;
        JList<Integer> numList = new JList<Integer>(); // 改好了
        DefaultListModel<Integer> numListModel = new DefaultListModel<Integer>();

        int num = 1;
        public static String questionString = "<html><p>Are You Ready? Please Choose a Python Code Question: </p></html>";

        public StudentWorkingComponent() {
                super(BoxLayout.Y_AXIS);

                ProjectVariable pv = new ProjectVariable();

                Font myFont2 = pv.getUserTextfieldFontSize();
                Font myFont1 = new Font("Arial", Font.PLAIN, 16);

                editScrollPane = new JScrollPane();

                lines = new JTextPane();
                lines.setText("  1  ");

                lines.setBackground(Color.LIGHT_GRAY);
                lines.setEditable(false);
                lines.setFont(myFont2);

                PythonCodeCheckerPage.splitPane.setDividerLocation(1100);

                topBox = Box.createHorizontalBox();

                questionLabel = new JLabel();
                questionLabel.setText(getQusetionString());
                questionLabel.setFont(myFont1);
                questionLabel.setPreferredSize(new Dimension(1100, 50));
                topBox.add(questionLabel);
                midBox = Box.createHorizontalBox();

                editTextPane = new JTextPane();
                editTextPane.setFont(myFont2);
                // set color for textPane background
                new ColorSet();
                // add lines count
                editTextPane.getDocument().addDocumentListener(new DocumentListener() {
                        public String getText() {
                                int caretPosition = editTextPane.getDocument().getLength();

                                Element root = editTextPane.getDocument().getDefaultRootElement();
                                String text = "  1  " + System.getProperty("line.separator");
                                for (int i = 2; i < root.getElementIndex(caretPosition) + 2; i++) {
                                        if (i < 10) {
                                                text += "  " + i + "  " + System.getProperty("line.separator");
                                        } else {
                                                text += i + System.getProperty("line.separator");
                                        }
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

                editScrollPane.setRowHeaderView(lines);
                editScrollPane.getViewport().add(editTextPane);
                editScrollPane.setPreferredSize(new Dimension(700, 500));
                editScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                // midBox.add(numListScrollPane);
                midBox.add(editScrollPane);

                this.add(midBox);

                //
                // downPanel = new JPanel();
                downBox = Box.createHorizontalBox();
                // 8,40
                terminalArea = new JTextArea(8, 40);

                DefaultCaret caret = (DefaultCaret) terminalArea.getCaret();
                caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

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
                studnetButtonPanel.setMaximumSize(new Dimension(10000, 80));

                PCCP.Button_Item_SubmitAnswer(buttonSubmitAnswer);
                PCCP.Button_Item_RunCode(buttonRunCode);

                // PCCP.Button_Item_ShowFeedback(buttonShowFeedback);

                studnetButtonPanel.add(Box.createHorizontalStrut(0));
                studnetButtonPanel.add(buttonSubmitAnswer);
                studnetButtonPanel.add(Box.createHorizontalStrut(100));
                studnetButtonPanel.add(buttonRunCode);
                studnetButtonPanel.add(Box.createHorizontalStrut(900));
                studnetButtonPanel.add(buttonInput);

                this.add(studnetButtonPanel, BorderLayout.SOUTH);

        }

        public static void setQuestionString(String question) {
                StudentWorkingComponent.questionString = question;
        }

        public static String getQusetionString() {
                return StudentWorkingComponent.questionString;
        }

        public static String getEditAnswerString() {
                return editTextPane.getText();
        }

        public static String getTerminal() {
                return terminalArea.getText();
        }

}
