package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.plaf.DimensionUIResource;

import methodAndTool.RunPythonCode;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;

public class FeedbackPage extends JDialog {

    // Added 3 components in the feedback page
    private JLabel messageTextAreaLabel; // A label for showing prompt before text area
    private JTextArea messageTextArea; // A text area for showing the feedback message
    private JButton returnButton; // A return button to go back to the previous window

    // Added 2 labels and 2 text areas for showing the student and suggested answer
    private JLabel studentAnswerLabel;
    private JTextArea studentAnswerTextArea;
    private JLabel suggestedAnswerLabel;
    private JTextArea suggestedAnswerTextArea;

    private JScrollPane messageScrollPane;
    private JScrollPane studentAnswerScrollPane;
    private JScrollPane suggestedAnswerScrollPane;

    private GridBagLayout layout; // A layout object for managing components
    private GridBagConstraints constraints; // Used for the settings for the layout for each component

    private RunPythonCode studentAnswerRPC = new RunPythonCode();
    private RunPythonCode suggestedAnswerRPC = new RunPythonCode();

    public FeedbackPage(String title, JFrame parentFrame) {
        super(parentFrame, title, true);

        // Create and set a layout for the dialog
        layout = new GridBagLayout();
        setLayout(layout);

        constraints = new GridBagConstraints();

        addComponents();
    }

    public void addComponents() {

        messageTextAreaLabel = new JLabel("Feedback: ");

        messageTextArea = new JTextArea();
        messageTextArea.setLineWrap(true);
        messageTextArea.setEditable(false);

        returnButton = new JButton("Return");
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        // Initialise the student answer label
        studentAnswerLabel = new JLabel("Student Answer");

        // Initialise the student answer text area, make it not editable
        studentAnswerTextArea = new JTextArea("student");
        studentAnswerTextArea.setEditable(false);

        // Initialise the suggested answer label
        suggestedAnswerLabel = new JLabel("Suggested Answer");

        // Initialise suggested answer text area, make it not editable
        suggestedAnswerTextArea = new JTextArea("suggested");
        suggestedAnswerTextArea.setEditable(true);

        messageScrollPane = new JScrollPane(messageTextArea);
        messageScrollPane.setPreferredSize(new DimensionUIResource(this.getWidth(), (int) (this.getHeight() * 0.3)));
        messageScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        messageScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        studentAnswerScrollPane = new JScrollPane(studentAnswerTextArea);
        studentAnswerScrollPane
                .setPreferredSize(new DimensionUIResource(this.getWidth(), (int) (this.getHeight() * 0.65)));
        studentAnswerScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        studentAnswerScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        suggestedAnswerScrollPane = new JScrollPane(suggestedAnswerTextArea);
        suggestedAnswerScrollPane
                .setPreferredSize(new DimensionUIResource(this.getWidth(), (int) (this.getHeight() * 0.65)));
        suggestedAnswerScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        suggestedAnswerScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // set constraints for adding the student answer label
        constraints.fill = GridBagConstraints.BOTH;
        setConstraintsForLayout(0, 0, 1, 1, 0.5, 0.05, 10);
        layout.setConstraints(studentAnswerLabel, constraints);
        add(studentAnswerLabel);

        // set constraints for adding suggested answer label
        constraints.fill = GridBagConstraints.BOTH;
        setConstraintsForLayout(0, 1, 1, 1, 0.5, 0.05, 10);
        layout.setConstraints(suggestedAnswerLabel, constraints);
        add(suggestedAnswerLabel);

        // set constraints for adding the student answer text area
        constraints.fill = GridBagConstraints.BOTH;
        setConstraintsForLayout(1, 0, 1, 1, 0.5, 0.65, 10);
        layout.setConstraints(studentAnswerScrollPane, constraints);
        add(studentAnswerScrollPane);

        // set constraints for adding the suggested answer text area
        constraints.fill = GridBagConstraints.BOTH;
        setConstraintsForLayout(1, 1, 1, 1, 0.5, 0.65, 10);
        layout.setConstraints(suggestedAnswerScrollPane, constraints);
        add(suggestedAnswerScrollPane);

        // set constraints for adding feedback label
        constraints.fill = GridBagConstraints.BOTH;
        setConstraintsForLayout(2, 0, 1, 2, 1, 0.05, 10);
        layout.setConstraints(messageTextAreaLabel, constraints);
        add(messageTextAreaLabel);

        // set constraints for adding feedback text area
        constraints.fill = GridBagConstraints.BOTH;
        setConstraintsForLayout(3, 0, 1, 2, 1, 0.3, 10);
        layout.setConstraints(messageScrollPane, constraints);
        add(messageScrollPane);

        // set constraints for adding return button
        constraints.fill = GridBagConstraints.BOTH;
        setConstraintsForLayout(4, 1, 1, 0, 0.5, 0.05, 10);
        layout.setConstraints(returnButton, constraints);
        add(returnButton);

    }

    private void setConstraintsForLayout(int row, int column, int rowspan, int columnspan, double widthPercent,
            double heightPercent, int insetSpacing) {

        constraints.gridx = column; // specify which column to be placed
        constraints.gridy = row; // specify which row to be placed
        constraints.gridwidth = columnspan; // specify the number of rows occupied
        constraints.gridheight = rowspan; // specify the number of columns occupied
        constraints.weightx = widthPercent; // specify the occupied percentage of grid's width
        constraints.weighty = heightPercent; // specify the occupied percentage of grid's height
        constraints.insets = new Insets(insetSpacing, insetSpacing, insetSpacing, insetSpacing); // specify the spacing
                                                                                                 // around
    }

    public void showFeedbackResult(String studentAnswer, String suggestedAnswer, int score, ArrayList<String> passedKeywordList) {
        returnButton.setEnabled(false);

        System.out.println("the suggest answer is: " + suggestedAnswer);

        studentAnswerTextArea.setText(studentAnswer);
        suggestedAnswerTextArea.setText(suggestedAnswer);

        boolean runCodeResult = showStudentRunCodeResult(studentAnswer);
        if (runCodeResult == true) {
            showCompareOutputResult(suggestedAnswer);
            messageTextArea.append("\nThe passed keyword(s) is/are:\n");
            for(String keyword : passedKeywordList){
                messageTextArea.append(keyword + "\n");
            }
            messageTextArea.append("\n Your score is: " + score + "\n");
        }
        returnButton.setEnabled(true);

    }

    public boolean showStudentRunCodeResult(String studentAnswer) {
        boolean runCodeResult = false;
        messageTextArea.setText("");

        if (studentAnswer.length() > 0) {
            messageTextArea.append("Your program is interpreting...\n");

            studentAnswerRPC.saveCodeFile(studentAnswer);
            boolean runStatus = studentAnswerRPC.runCode();

            if (runStatus == false) {
                messageTextArea.append("There is something wrong when running the program. \n");
                messageTextArea.append(studentAnswerRPC.getErrorMessage());
            } else {
                if (studentAnswerRPC.getErrorMessage().equals("")) {
                    messageTextArea.append("Your program runs without any syntax error. \n");
                    messageTextArea.append("The following is the output from console (if any): \n");
                    messageTextArea.append(studentAnswerRPC.getOutputFromConsole());
                    runCodeResult = true;
                } else {
                    messageTextArea.append("Your program has some syntax errors: \n");
                    messageTextArea.append(studentAnswerRPC.getErrorMessage());
                }
            }
            // returnButton.setEnabled(true);
            System.out.println("Run status: " + runStatus);
            System.out.println("Output: " + studentAnswerRPC.getOutputFromConsole());
            System.out.print(studentAnswerRPC.getErrorMessage());
        } else {

            messageTextArea.append("The editor window's is empty");
            System.out.println("The editor window's is empty.");
        }
        return runCodeResult;
    }

    public void showCompareOutputResult(String suggestedAnswer) {
        if (suggestedAnswer.length() > 0) {
            messageTextArea.append("Your program output is compared with the one of the suggested answers. \n");

            suggestedAnswerRPC.saveCodeFile(suggestedAnswer);
            boolean runStatus = suggestedAnswerRPC.runCode();

            if (runStatus == false) {
                messageTextArea.append("There is something wrong with the suggested answer. \n");
                messageTextArea.append(suggestedAnswerRPC.getErrorMessage());
            } else {
                if (suggestedAnswerRPC.getErrorMessage().equals("")) {
                    messageTextArea.append("This is the output from console of the suggested answer (if any): \n");
                    messageTextArea.append(suggestedAnswerRPC.getOutputFromConsole());

                    String suggestedAnswerOutput = suggestedAnswerRPC.getOutputFromConsole();
                    String studentAnswerOutput = studentAnswerRPC.getOutputFromConsole();
                    if (studentAnswerOutput.equals(suggestedAnswerOutput)) {
                        messageTextArea.append("Your program output is the same as the suggested answer\n");
                    } else {
                        messageTextArea.append("Your program has some differences from the suggested answer\n");
                    }

                } else {
                    messageTextArea.append("There is syntax error in the suggested answer\n");
                    messageTextArea.append(suggestedAnswerRPC.getErrorMessage());
                }
            }
        } else {
            messageTextArea.append("You haven't selected a question to answer\n");
        }

    }

}