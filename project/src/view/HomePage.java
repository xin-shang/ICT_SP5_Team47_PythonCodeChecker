package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import methodAndTool.ScreenUtils;

public class HomePage {

	ScreenUtils SU = new ScreenUtils();

	/**
	 * Home Page：
	 **/
	// Home Page 窗口
	JFrame frame = new JFrame("Python Code Checker");
	// Home Page 提示语
	private JLabel prompt_home = new JLabel("Welcome to Python Code Checker");
	// Home Page 按键
	private JButton button_Student = new JButton("I'm a Student");
	private JButton button_Staff = new JButton("I'm a Staff");

	// 初始化，组装界面
	public void init() {

		/**
		 * 设置窗口属性
		 */
		frame.setLocation((ScreenUtils.getScreenWidth() - ScreenUtils.getDesignWindow_width()) / 2,
				(ScreenUtils.getScreenHeight() - ScreenUtils.getDesignWindow_heigh()) / 2); // 窗口位置
		frame.setSize(ScreenUtils.getDesignWindow_width(), ScreenUtils.getDesignWindow_heigh()); // 设置窗口（宽，高）
		try {
			frame.setIconImage(ImageIO.read(new File(ScreenUtils.getItemPath("PythonLogo")))); // Mac 好像不太支持这个，Windows
																								// 咋样要试试。
			// System.out.println("-- ImageIO is Working --");
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.setResizable(false); // 窗口锁定
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 违规操作关闭
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/**
		 * 设置窗口内容
		 */
		// 板面
		JPanel homePanel = new JPanel();
		homePanel.setLayout(null);
		// 字体
		Font myFont = new Font("Arial", Font.PLAIN, 30);

		/**
		 * 组装零件
		 */
		prompt_home.setFont(myFont);
		prompt_home.setBounds(150, 10, 600, 100);
		homePanel.add(prompt_home);

		button_Student.setBounds(150, 200, 200, 200);
		Button_Student_Listener(button_Student);
		homePanel.add(button_Student);

		button_Staff.setBounds(600, 200, 200, 200);
		Button_Staff_Listener(button_Staff);
		homePanel.add(button_Staff);

		//
		frame.add(homePanel);

		// 窗口可见
		frame.setVisible(true);

	}

	private void Button_Student_Listener(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 弹出学生登陆页面,当前页面消失
				new StudentLoginPage().init();
				frame.dispose();
				System.out.println("-- The Student Button is Working --");
			}
		});
	}

	private void Button_Staff_Listener(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 弹出员工登陆页面,当前页面消失
				new StaffLoginPage().init();
				frame.dispose();
				System.out.println("-- The Staff Button is Working --");
			}
		});
	}

}
