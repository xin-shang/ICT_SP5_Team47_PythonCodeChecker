package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;

public class FeedbackPage extends JDialog{

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

    private boolean syntaxErrorStatus = false;
    private String runResultMessage= "";

    public FeedbackPage(String title, JFrame parentFrame){
        super(parentFrame, title, true);

        //Create and set a layout for the dialog
        layout = new GridBagLayout();
        setLayout(layout);

        constraints = new GridBagConstraints();

        addComponents();
    }

    public void addComponents(){

        messageTextAreaLabel = new JLabel("Feedback: ");

        messageTextArea = new JTextArea();
        messageTextArea.setLineWrap(true);
        messageTextArea.setEditable(false);

        returnButton = new JButton("Return");
        returnButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);
            }
        });

        // Initialise the student answer label
        studentAnswerLabel = new JLabel("Student Answer");


        // Initialise the student answer text area, make it not editable
        studentAnswerTextArea = new JTextArea("student");
        studentAnswerTextArea.setEditable(false);

        //Initialise the suggested answer label
        suggestedAnswerLabel = new JLabel("Suggested Answer");

        //Initialise suggested answer text area, make it not editable
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
    
    private void setConstraintsForLayout(int row, int column, int rowspan, int columnspan, double widthPercent, double heightPercent, int insetSpacing){

        constraints.gridx = column; // specify which column to be placed
        constraints.gridy = row; // specify which row to be placed
        constraints.gridwidth = columnspan; // specify the number of rows occupied
        constraints.gridheight = rowspan; // specify the number of columns occupied 
        constraints.weightx = widthPercent; // specify the occupied percentage of grid's width
        constraints.weighty = heightPercent; // specify the occupied percentage of grid's height
        constraints.insets = new Insets(insetSpacing, insetSpacing, insetSpacing, insetSpacing); //specify the spacing around
    }
    
    public void setStudentAnswerTextArea(String studentAnswer) {
        studentAnswerTextArea.setText(studentAnswer);
    }


    public void setSyntaxErrorStatus(boolean syntaxErrorStatus){
        this.syntaxErrorStatus = syntaxErrorStatus;
    }

    public void setRunResultMessage(String runResultMessage){
        this.runResultMessage = runResultMessage;
    }

    public void setTextMessageTextArea(String message){
        messageTextArea.setText(message);
    }

    public void updateMessageTextArea(){
        messageTextArea.setText("");
        String message = "";
        if(syntaxErrorStatus == true){
            message = message + "Your program has some syntax errors: \n";
            message = message + runResultMessage + "\n";
        }else{
            message = message + "Your program runs without syntax errors. \n";
            message = message + "The following is the output from console (if any): \n";
            message = message + runResultMessage + "\n";
        }
        messageTextArea.setText(message);
    }

}