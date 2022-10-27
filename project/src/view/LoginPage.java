package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import methodAndTool.ScreenUtils;
import methodAndTool.WriteAndRead;

public class LoginPage {

	ScreenUtils SU = new ScreenUtils();

	/**
	 * Login Page
	 */
	final int Width_LoginPage = 500;
	final int Height_LoginPage = 300;

	JLabel prompt_user = new JLabel("User Name :");
	JLabel prompt_password = new JLabel(" Password  :");
	JTextField area_user = new JTextField(15);
	// JTextField area_password = new JTextField(15);
	JPasswordField area_password = new JPasswordField(15);
	JButton button_login = new JButton("Log In");
	JButton button_signUp = new JButton("Sign Up");
	JButton button_retuen = new JButton("Return");

	WriteAndRead WAR = new WriteAndRead();

	/**
	 * Button 监听
	 */
	protected void Button_Signin_Listener(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 进入注册页,
				new SignupPage().init();
				System.out.println("-- The Login Button is Working --");
			}
		});
	}

}
