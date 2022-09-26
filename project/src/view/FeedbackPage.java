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

        // set constraints for adding the label
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 0.05;
        constraints.insets = new Insets(10, 10, 10, 10);
        layout.setConstraints(messageTextAreaLabel, constraints);
        add(messageTextAreaLabel);

        // set constraints for adding the text area
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 1;
        constraints.weighty = 0.9;
        constraints.insets = new Insets(10, 10, 10, 10);
        layout.setConstraints(messageTextArea, constraints);
        add(messageTextArea);

        // set constraints for adding the button
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weightx = 1;
        constraints.weighty = 0.05;
        constraints.insets = new Insets(10, 10, 10, 10);
        layout.setConstraints(returnButton, constraints);
        
        add(returnButton);


       
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