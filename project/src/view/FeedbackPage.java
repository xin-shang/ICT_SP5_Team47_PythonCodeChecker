package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class FeedbackPage extends JDialog {
	
	public FeedbackPage(String title, JFrame parentFrame) {
		super(parentFrame, title, true);
		addComponents();
	}

	private void addComponents() {
		JButton returnButton = new JButton("Return");
		
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}			 
		});
		add(returnButton);
	}

}
