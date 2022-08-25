package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import methodAndTool.ScreenUtils;

public class LoginPage {

	ScreenUtils SU = new ScreenUtils();
	
	/**
	 * Login Page
	 * */
	final int Width_LoginPage = 500;
	final int Height_LoginPage = 300;
	
	JLabel prompt_user = new JLabel("User Name :");
	JLabel prompt_password = new JLabel(" Password  :");
	JTextField area_user = new JTextField(15);
	JTextField area_password = new JTextField(15);
	JButton button_login = new JButton("Log In");
	JButton button_signin = new JButton("Sign In");
	
	// 方法要用的变量：没用的话就删掉
	boolean StudentN, StudentP, StaffN, StaffP;
	
	/**
	 * Button 监听
	 * */
	
	protected void Button_Signin_Listener (JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 进入注册页
				
				System.out.println("-- The Login Button is Working --");
				
			}
		});
	}
	
	
	/**
	 * 应该有一个方法将用户名和密码产送到后台，用于和已有的用户名和密码进行比较。
	 * 如果项目用数据库有后台服务器，会有专门的回调方法，可以直接将二者以特殊方式回调（想Josn类型）。
	 * 现在我会直接向后传送，用String对比的方法测试登陆。
	 * Student Page：student/student
	 * Staff Page： staff/staff
	 * */
	public boolean Post_Contrast_Username_Student (String username) {
		String TrueUsername = "student";
		if (TrueUsername.equals(username)) {
			return true;
		}
			return false;
	}
	
	public boolean Post_Contrast_Password_Student (String password) {
		String TruePassword = "student";
		if (TruePassword.equals(password)) {
			return true;
		}
		return false;
	}
	
	public boolean Post_Contrast_Username_Staff (String username) {
		String TrueUsername = "staff";
		if (TrueUsername.equals(username)) {
			return true;
		}
			return false;
	}
	
	public boolean Post_Contrast_Password_Staff (String password) {
		String TruePassword = "staff";
		if (TruePassword.equals(password)) {
			return true;
		}
		return false;
	}

}
