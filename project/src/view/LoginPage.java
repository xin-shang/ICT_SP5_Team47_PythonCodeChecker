package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import javax.swing.JLabel;
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
	JTextField area_password = new JTextField(15);
	JButton button_login = new JButton("Log In");
	JButton button_signin = new JButton("Sign In");
	JButton button_retuen = new JButton("Return");

	WriteAndRead WAR = new WriteAndRead();

	/**
	 * Button 监听
	 */
	protected void Button_Signin_Listener(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 进入注册页

				System.out.println("-- The Login Button is Working --");

			}
		});
	}

	/**
	 * 前端发送用户名和密码给后端
	 * 后端返回用户名和密码布尔值
	 * Student Page：student/student
	 * Staff Page： staff/staff
	 */
	public void PostStudent_UserName_passowrd(String username, String password) {
		// 写入用户数据
		// java write data for python to read
		WAR.write2TextFileOutStream("./src/txt/StudentUserName.txt", username);
		WAR.write2TextFileOutStream("./src/txt/StudentUserPassword.txt", password);

		// python 阅读 用户数据
		// python read data from java
		WAR.run_python_code("./src/python/PYDb_StudentLogin.py");
	}

	/**
	 * 获取数据库返回的用户名布尔值
	 * get boolean for username from database
	 */
	public boolean getStudent_DbReturn_userName() {
		String busernameStudent = WAR.readText("./src/txt/StudentUserName.txt");
		if (busernameStudent.equals("True"))
			return true;
		return false;
	}

	/**
	 * 获取数据库返回的密码布尔值
	 * get boolean for password from database
	 */
	public boolean getStudent_DbReturn_password() {
		String bpasswordStudent = WAR.readText("./src/txt/StudentUserPassword.txt");
		if (bpasswordStudent.equals("True"))
			return true;
		return false;
	}

	public boolean Post_Contrast_Username_Staff(String username) {
		String TrueUsername = "staff";
		if (TrueUsername.equals(username)) {
			return true;
		}
		return false;
	}

	public boolean Post_Contrast_Password_Staff(String password) {
		String TruePassword = "staff";
		if (TruePassword.equals(password)) {
			return true;
		}
		return false;
	}

}
