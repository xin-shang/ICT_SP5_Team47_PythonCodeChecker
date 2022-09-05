package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import methodAndTool.ScreenUtils;
import methodAndTool.WriteAndRead;

public class StudentLoginPage extends LoginPage {
	JFrame frame = new JFrame("Python Code Chacker - Student Login Page");
	WriteAndRead WAR = new WriteAndRead();

	// 初始化，组装界面
	public void init() {

		/**
		 * 设置窗口属性
		 */

		frame.setLocation((ScreenUtils.getScreenWidth() - Width_LoginPage) / 2,
				(ScreenUtils.getScreenHeight() - Height_LoginPage) / 2); // 窗口位置
		frame.setSize(Width_LoginPage, Height_LoginPage);
		frame.setResizable(false);
		try {
			frame.setIconImage(ImageIO.read(new File(ScreenUtils.getItemPath("PythonLogo")))); // Mac 好像不太支持这个，Windows
																								// 咋样要试试。
			System.out.println("-- ImageIO is Working --");
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 违规操作关闭
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/**
		 * 设置窗口内容
		 */
		// 面板
		JPanel LoginPanel = new JPanel();

		/**
		 * 组装零件
		 */
		Box Vertical_Box = Box.createVerticalBox();

		// 用户名Box
		Box User_Box = Box.createHorizontalBox();
		User_Box.add(Box.createHorizontalStrut(20));

		User_Box.add(prompt_user);
		User_Box.add(area_user);

		// 密码Box
		Box Password_Box = Box.createHorizontalBox();
		Password_Box.add(Box.createHorizontalStrut(20));

		Password_Box.add(prompt_password);
		Password_Box.add(area_password);

		// Button Box
		Box Button_Box = Box.createHorizontalBox();
		Button_Box.add(Box.createHorizontalStrut(20));

		Button_Box.add(button_login);
		this.Button_Login_Listener(button_login);

		Button_Box.add(button_signin);
		super.Button_Signin_Listener(button_signin);

		// Button Box2 Return
		Box Return_Box = Box.createHorizontalBox();

		Return_Box.add(button_retuen);
		this.Button_Retuen_Listener(button_retuen);

		// 加板面，盒子，零件
		Vertical_Box.add(Box.createVerticalStrut(60));
		Vertical_Box.add(User_Box);
		Vertical_Box.add(Box.createVerticalStrut(20));
		Vertical_Box.add(Password_Box);
		Vertical_Box.add(Box.createVerticalStrut(40));
		Vertical_Box.add(Button_Box);
		Vertical_Box.add(Box.createVerticalStrut(20));
		Vertical_Box.add(Return_Box);

		LoginPanel.add(Vertical_Box);

		frame.add(LoginPanel);

		// 窗口可见
		frame.setVisible(true);

	}

	/**
	 * Button 监听
	 */

	private void Button_Login_Listener(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 获取用户数据（登陆的步骤）暂时注释写在这里。 传到后台（理论上需要访问登陆接口，现在没有服务端。直接传到后面去就行）

				String usernameStudent = area_user.getText().trim();
				String passwordStudent = area_password.getText().trim();

				char[] usernameStudent_char = usernameStudent.toCharArray();
				char[] passwordStudent_char = passwordStudent.toCharArray();
				// 账号写进txt: write UI input in txt
				try {
					WAR.creatTxtFile("StudentUserName");
					WAR.writeAnswerInTxt(usernameStudent_char, usernameStudent);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				// 密码写进txt
				try {
					WAR.creatTxtFile("StudentUserPassword");
					WAR.writeAnswerInTxt(passwordStudent_char, passwordStudent);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				// 运行python读取上面的txt
				WAR.run_python_code("./src/python/PYDb_StudentLogin.py");

				String busernameStudent = WAR.readText("./src/txt/StudentUserName.txt");
				String bpasswordStudent = WAR.readText("./src/txt/StudentUserPassword.txt");

				if (Post_Contrast_Username_Student(busernameStudent) == true
						&& Post_Contrast_Password_Student(bpasswordStudent) == true) {
					// set an example question 设置样板问题
					WAR.run_python_code("./src/python/PYDb_QnS.py");
					// 进入学生页面 - Python Code Checker,当前页面消失
					new PythonCodeChackerPage().init();
					frame.dispose();
					System.out.println("--Go to the Student page - Python Code Chacker--");
				} else if (Post_Contrast_Username_Student(busernameStudent) == true
						&& Post_Contrast_Password_Student(bpasswordStudent) == false) {
					// 账户存在，密码错误 accout exit, password incorrect
					JOptionPane.showMessageDialog(frame,
							"The entered password is incorrect. Please re-enter again!");
				} else if (Post_Contrast_Username_Student(busernameStudent) == false
						&& Post_Contrast_Password_Student(bpasswordStudent) == true) {
					// 账号错误 password incorrect
					JOptionPane.showMessageDialog(frame,
							"The entered account is incorrect. Please re-enter again!");
				} else {
					// 弹窗提示，帐号和密码错误 account and password both incorrect
					JOptionPane.showMessageDialog(frame,
							"The entered account is incorrect. Please re-enter them!");
				}
				System.out.println("-- The Login Button is Working in StudentLoginPage Class --");

			}

		});
	}

	private void Button_Retuen_Listener(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 返回主页
				new HomePage().init();
				frame.dispose();
				System.out.println("-- The Return Button is Working --");
			}
		});
	}

	/**
	 * 内容获取
	 */
	// public String getUsernameStaff() {
	// return usernameStudent;
	// }
	//
	// public String getPasswordStaff() {
	// return passwordStudent;
	// }

}