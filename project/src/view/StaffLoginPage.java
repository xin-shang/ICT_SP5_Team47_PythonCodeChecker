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

public class StaffLoginPage extends LoginPage{
	
	JFrame frame = new JFrame ("Python Code Chacker - Staff Login Page");
	
	// 初始化，组装界面
	public void init() {
		
		/**
		 * 设置窗口属性
		 * */
		frame.setLocation((ScreenUtils.getScreenWidth() - Width_LoginPage) / 2, (ScreenUtils.getScreenHeight() - Height_LoginPage) / 2);		// 窗口位置
		frame.setSize(Width_LoginPage, Height_LoginPage);
		frame.setResizable(false);
		try {
			frame.setIconImage(ImageIO.read(new File(ScreenUtils.getItemPath("PythonLogo"))));			// Mac 好像不太支持这个，Windows 咋样要试试。
			System.out.println("-- ImageIO is Working --");
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		// 违规操作关闭
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/**
		 * 设置窗口内容
		 * */
		// 面板
		JPanel LoginPanel = new JPanel();
		
		
		/**
		 * 组装零件
		 * */
		Box Vertical_Box = Box.createVerticalBox();
		
		// 用户名Box
		Box User_Box = Box.createHorizontalBox();
		
		User_Box.add(prompt_user);
		User_Box.add(Box.createHorizontalStrut(20));
		User_Box.add(area_user);
		
		// 密码Box
		Box Password_Box = Box.createHorizontalBox();
		
		Password_Box.add(prompt_password);
		Password_Box.add(Box.createHorizontalStrut(20));
		Password_Box.add(area_password);
		
		// Button Box
		Box Button_Box = Box.createHorizontalBox();
		
		Button_Box.add(button_login);		
		this.Button_Login_Listener(button_login);
		Button_Box.add(Box.createHorizontalStrut(20));
		Button_Box.add(button_signin);
		super.Button_Signin_Listener(button_signin);
		
		// 加板面，盒子，零件
		Vertical_Box.add(Box.createVerticalStrut(60));
		Vertical_Box.add(User_Box);
		Vertical_Box.add(Box.createVerticalStrut(20));
		Vertical_Box.add(Password_Box);
		Vertical_Box.add(Box.createVerticalStrut(40));
		Vertical_Box.add(Button_Box);
		
		LoginPanel.add(Vertical_Box);
		
		frame.add(LoginPanel);
		
		// 窗口可见
		frame.setVisible(true);
		
	}
	
	/**
	 * Button 监听
	 * */
	
	private void Button_Login_Listener (JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 获取用户数据（登陆的步骤）暂时注释写在这里。 传到后台（理论上需要访问登陆接口，现在没有服务端。直接传到后面去就行）
				// 进入员工页面 - Python Question Edit Page
				String username = area_user.getText().trim();
				String password = area_password.getText().trim();
				if (Post_Contrast_Username_Staff(username) == true && Post_Contrast_Password_Staff(password) == true) {
					System.out.println("--Go to the Staff page - Python Code Chacker--");
				}
				else {
					JOptionPane.showMessageDialog(frame, "The entered account and password are incorrect. Please re-enter them!");
				}
				System.out.println("-- The Login Button is Working in StudentLoginPage Class --");
			}
		});
	}
	
	/**
	 * 内容获取
	 * */

}
