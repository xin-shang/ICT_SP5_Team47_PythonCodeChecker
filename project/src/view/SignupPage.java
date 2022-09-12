package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import methodAndTool.ScreenUtils;

public class SignupPage extends LoginPage {

    JFrame frame = new JFrame("Python Code Checker - Sign Up Page");

    //define labels for input fields
    JLabel usernameLabel = new JLabel("Username: ");
    JLabel passwordLabel = new JLabel("Password: ");
    JLabel confirmPasswordLabel = new JLabel("Confirm password: ");
    JLabel userTypeLabel = new JLabel("User Type: ");

    //define the inputs
    JTextField username = new JTextField(15);
    JTextField password = new JTextField(15);
    JTextField confirmPassword = new JTextField(15);

    //define usertype selector
    JComboBox<String> userType = new JComboBox<String>(new String[]{"student", "staff"});

    //define signup button
    JButton signUpButton = new JButton("Sign up");


    public void init(){

        /**
		 * 设置窗口属性
		 */
        frame.setLocation((ScreenUtils.getScreenWidth() - Width_LoginPage) / 2,
				(ScreenUtils.getScreenHeight() - Height_LoginPage) / 2); // 窗口位置
		frame.setSize(Width_LoginPage, Height_LoginPage);
		frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 违规操作关闭
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel signUpFormPanel = new JPanel();

        /**
		 * 组装零件
		 */
		Box Vertical_Box = Box.createVerticalBox();

		// username Box
		Box User_Box = Box.createHorizontalBox();
		User_Box.add(Box.createHorizontalStrut(20));
        // add label
		User_Box.add(usernameLabel);
        // add input
		User_Box.add(username);

		// password box
		Box Password_Box = Box.createHorizontalBox();
		Password_Box.add(Box.createHorizontalStrut(20));

		Password_Box.add(passwordLabel);
		Password_Box.add(password);

        //confirmPassword box
        Box Confirm_Box = Box.createHorizontalBox();
		Confirm_Box.add(Box.createHorizontalStrut(20));

		Confirm_Box.add(confirmPasswordLabel);
		Confirm_Box.add(confirmPassword);

        //user type selector box
        Box Type_Box = Box.createHorizontalBox();
		Type_Box.add(Box.createHorizontalStrut(20));

		Type_Box.add(userTypeLabel);
		Type_Box.add(userType);

        //Button Box
        //Create buttons panel
        //JPanel buttonsPanel = new JPanel();
        Box Button_Box = Box.createHorizontalBox();
        Button_Box.add(Box.createHorizontalStrut(20));


        //add buttons to panel
        Button_Box.add(signUpButton);
        Button_Box.add(button_retuen);

        //add event linster to buttons
        this.Button_SignUp_Listener(signUpButton);
        this.Button_Retuen_Listener(button_retuen);

        //add all component to box
        Vertical_Box.add(Box.createVerticalStrut(40));
        Vertical_Box.add(User_Box);
        Vertical_Box.add(Box.createVerticalStrut(10));
        Vertical_Box.add(Password_Box);
        Vertical_Box.add(Box.createVerticalStrut(10));
        Vertical_Box.add(Confirm_Box);
        Vertical_Box.add(Box.createVerticalStrut(10));
        Vertical_Box.add(Type_Box);
        Vertical_Box.add(Box.createVerticalStrut(10));
        Vertical_Box.add(Button_Box);

        //add box to panel
        signUpFormPanel.add(Vertical_Box);
        //set the frame layout as BoxLayout
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        //add form and buttons panel to the mainPanel
        frame.add(signUpFormPanel);
        //frame.add(buttonsPanel);

        //窗口可见
        frame.add(Box.createVerticalGlue());
        frame.setVisible(true);


    }

    /**
	 * Button 监听
	 */
	private void Button_SignUp_Listener(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO implement sign up logic
                
                
                
			}
		});
	}

    private void Button_Retuen_Listener(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 返回主页
				// new HomePage().init();
				frame.dispose();
				System.out.println("-- The Return Button is Working --");
			}
		});
	}

    
}