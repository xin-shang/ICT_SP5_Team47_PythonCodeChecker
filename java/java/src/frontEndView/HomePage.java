package frontEndView;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Font;

import methodAndTool.ProjectVariable;

public class HomePage extends JFrame {

	ProjectVariable PV = new ProjectVariable();
	public HomePage() {
		init();
	}

	private void init() {

		// 窗口

		this.setTitle("Python Code Checker"); // 窗口名称
		this.setSize(PV.getDesign_width(), PV.getDesign_heigh()); // 设置窗口（宽，高）
		this.setResizable(false); // 窗口锁定
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // 违规操作关闭

		// 版本
		JPanel homePanel = new JPanel();
		homePanel.setLayout(null);
		System.out.print("i am here");
		add(homePanel); // 添加

		// 字体
		Font myFont = new Font("Arial", Font.PLAIN, 30);

		PV.getPrompt_home().setFont(myFont);
		PV.getPrompt_home().setBounds(150, 10, 600, 100);
		homePanel.add(PV.getPrompt_home());

		PV.getButton_Student().setBounds(150, 200, 200, 200);
		homePanel.add(PV.getButton_Student());

		PV.getButton_Staff().setBounds(600, 200, 200, 200);
		homePanel.add(PV.getButton_Staff());

		setVisible(true);
	}

}
