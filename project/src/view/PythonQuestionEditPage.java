package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;

import component.KeywordManagerComponent;
import component.QuestionManagerComponent;
import methodAndTool.ScreenUtils;

public class PythonQuestionEditPage {

	ScreenUtils SU = new ScreenUtils();
	// QuestionManagerComponent QMC = new QuestionManagerComponent();

	String ArtUser = SU.getBlankSpace(54);

	/**
	 * Python Question Edit Page
	 */
	JFrame frame = new JFrame("Python Code Checker - Python Question Edit Page");

	// 设置菜单栏
	JMenuBar manuBar = new JMenuBar();
	// 设置菜单名
	JMenu manu_User = new JMenu("USER");
	JMenu manu_Show = new JMenu("SHOW");
	JMenu manu_Null = new JMenu(ArtUser);

	// 设置菜单中物品 - OPERATION
	JMenuItem item_ShowQuestionTable = new JMenuItem("Show Question Table");
	JMenuItem item_ShowKeywordTable = new JMenuItem("Show Keyword Table");

	// 设置菜单中物品 - USER
	JMenuItem item_ChangeAccount = new JMenuItem("Change Account");
	JMenuItem item_ExitProgram = new JMenuItem("Exit Program");

	// 设置分割面板
	public static JSplitPane splitPane = new JSplitPane();

	// 初始化，组装界面
	public void init() {

		/**
		 * 设置窗口属性
		 */
		frame.setLocation((ScreenUtils.getScreenWidth() - SU.getDesignWindow_width()) / 2,
				(ScreenUtils.getScreenHeight() - SU.getDesignWindow_heigh()) / 2); // 窗口位置
		frame.setSize(SU.getDesignWindow_width(), SU.getDesignWindow_heigh()); // 设置窗口（宽，高）
		try {
			frame.setIconImage(ImageIO.read(new File(ScreenUtils.getItemPath("PythonLogo")))); // Mac 好像不太支持这个，Windows
															// 咋样要试试。
			System.out.println("-- ImageIO is Working --");
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.setResizable(false); // 窗口锁定
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 违规操作关闭
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/**
		 * 设置窗口内容
		 */

		/**
		 * 组装零件
		 */
		// 菜单
		// 菜单子件加入对用的菜单名
		Button_Item_ShowQuestionTable(item_ShowQuestionTable);
		Button_Item_ShowKeyWordTable(item_ShowKeywordTable);
		manu_Show.add(item_ShowQuestionTable);
		manu_Show.add(item_ShowKeywordTable);

		Button_Item_ChangeAccount(item_ChangeAccount);
		Button_Item_ExitProgram(item_ExitProgram);
		manu_User.add(item_ChangeAccount);
		manu_User.add(item_ExitProgram);

		// 菜单名加入菜单组
		manuBar.add(manu_Show);
		manuBar.add(manu_Null);
		manuBar.add(manu_User);

		// 分割面板
		splitPane.setContinuousLayout(true); // 连续布局
		splitPane.setDividerLocation(800); // 左右分屏初始位置
		splitPane.setDividerSize(2); // 分割线宽度

		// splitPane Left
		splitPane.setLeftComponent(new QuestionManagerComponent());

		// splitPane Right 展示详细信息，点击左侧列表中的一行，像是对应的详细信息。

		//
		frame.add(splitPane);
		// 将菜单栏加入窗口
		frame.setJMenuBar(manuBar);
		// 窗口可见
		frame.setVisible(true);

	}

	/**
	 * Button 监听
	 */
	// 切换用户
	private void Button_Item_ChangeAccount(JMenuItem button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//
				new HomePage().init();
				frame.dispose();
				System.out.println("-- The Change Account Manu Button is Working --");
			}
		});
	}

	// 退出程序
	private void Button_Item_ExitProgram(JMenuItem button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//
				System.exit(0);
				System.out.println("-- The Exit Manu Button is Working --");
			}
		});
	}

	// 菜单栏按键监听
	private void Button_Item_ShowQuestionTable(JMenuItem button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//
				splitPane.setLeftComponent(new QuestionManagerComponent());
				System.out.println("-- The Show Question Table Manu Button is Working --");
			}
		});
	}

	private void Button_Item_ShowKeyWordTable(JMenuItem button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//
				// int selectedRow = QuestionManagerComponent.questionTable.getSelectedRow();
				splitPane.setLeftComponent(new KeywordManagerComponent());
				System.out.println("-- The Check Manu Button is Working --");
			}
		});
	}

	/**
	 * 内容获取
	 */

}