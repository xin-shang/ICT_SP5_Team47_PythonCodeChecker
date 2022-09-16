package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import component.StudentWorkingComponent;

public class FeedbackPage extends JDialog {
	
	private JLabel messageTextAreaLabel; // A label for showing prompt before text area
	private JTextArea messageTextArea; // A text area for showing feedback message
	private JButton returnButton; // A return button to go back to previous 
	
	private GridBagLayout layout; // A layout object for managing components
	private GridBagConstraints constraints; // Use for settings about the layout for each component
	
	private boolean syntaxErrorStatus = false; // A variable to store whether there are syntax error after running the code
	private String runResultMessage = ""; // To store the output that comes from running the program, either error message or output
	
	
	// Constructor to initialise the feedback dialog page
	public FeedbackPage(String title, JFrame parentFrame) {
		super(parentFrame, title, true);
		
		layout = new GridBagLayout();
		setLayout(layout);
		
		constraints = new GridBagConstraints();
		
		addComponents();
	}
	

	private void addComponents() {
		
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
		
		// set constraints for adding the label
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 0.05;
		constraints.insets = new Insets(10, 10, 10, 10);
		layout.setConstraints(messageTextAreaLabel, constraints);
		add(messageTextAreaLabel);
		
		
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 1;
		constraints.weighty = 0.9;
		constraints.insets = new Insets(10, 10, 10, 10);
		layout.setConstraints(messageTextArea, constraints);
		add(messageTextArea);

		
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.weightx = 1;
		constraints.weighty = 0.05;
		constraints.insets = new Insets(10, 10, 10, 10);
		layout.setConstraints(returnButton, constraints);
		add(returnButton);
		
	}
	
	
	// setter for instance variable : syntaxErrorStatus
	public void setSyntaxErrorStatus(boolean syntaxErrorStatus) {
		this.syntaxErrorStatus = syntaxErrorStatus;
	}
	
	// setter for instance variable : runResultMessage
	public void setRunResultMessage(String runResultMessage) {
		this.runResultMessage = runResultMessage;
	}
	
	// setter for assigning text to the message text area
	public void setTextMessageTextArea(String message) {
		messageTextArea.setText(message);
	}
	
	public void updateMessageTextArea() {
		messageTextArea.setText("");
		String message = "";
		if(syntaxErrorStatus == true) {
			message = message + "Your program has some syntax errors: \n";
			message = message + runResultMessage + "\n";
		}else {
			message = message + "Your program runs without any syntax error. \n";
			message = message + "The following is the output from console (if any): \n";
			message = message + runResultMessage + "\n";
			
		}
		messageTextArea.setText(message);
	}
	

}
