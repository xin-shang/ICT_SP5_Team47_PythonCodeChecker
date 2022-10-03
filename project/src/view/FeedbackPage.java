package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

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
        suggestedAnswerTextArea.setEditable(false);

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
        layout.setConstraints(studentAnswerTextArea, constraints);
        add(studentAnswerTextArea);

        // set constraints for adding the suggested answer text area
        constraints.fill = GridBagConstraints.BOTH;
        setConstraintsForLayout(1, 1, 1, 1, 0.5, 0.65, 10);
        layout.setConstraints(suggestedAnswerTextArea, constraints);
        add(suggestedAnswerTextArea);

        // set constraints for adding feedback label
        constraints.fill = GridBagConstraints.BOTH;
        setConstraintsForLayout(2, 0, 1, 2, 1, 0.05, 10);
        layout.setConstraints(messageTextAreaLabel, constraints);
        add(messageTextAreaLabel);

        // set constraints for adding feedback text area
        constraints.fill = GridBagConstraints.BOTH;
        setConstraintsForLayout(3, 0, 1, 2, 1, 0.3, 10);
        layout.setConstraints(messageTextArea, constraints);
        add(messageTextArea);

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

    public void showFeedbackResult(String studentAnswer, String suggestedAnswer) {
        returnButton.setEnabled(false);

        studentAnswerTextArea.setText(studentAnswer);
        suggestedAnswerTextArea.setText(suggestedAnswer);

        showStudentRunCodeResult(studentAnswer);

        returnButton.setEnabled(true);

    }

    public void showStudentRunCodeResult(String studentAnswer) {

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
                        messageTextArea.append("Your program output is the same as the suggested answer");
                    } else {
                        messageTextArea.append("Your program has some differences from the suggested answer");
                    }

                } else {
                    messageTextArea.append("There is syntax error in the suggested answer");
                    messageTextArea.append(suggestedAnswerRPC.getErrorMessage());
                }
            }
        } else {
            messageTextArea.append("You haven't selected a question to answer");
        }

    }

}