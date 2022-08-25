package view;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import methodAndTool.ScreenUtils;

public class PythonCodeChackerPage {
	
ScreenUtils SU = new ScreenUtils();
	
	/**
	 * Python Code Chacker Page
	 * */
	// 
	JFrame frame = new JFrame("Python Code Chacker - Python Code Chacker Page");
	
	// 初始化，组装界面
	public void init() {
		
		/**
		 * 设置窗口属性
		 * */
		frame.setLocation((ScreenUtils.getScreenWidth() - SU.getDesignWindow_width()) / 2, (ScreenUtils.getScreenHeight() - SU.getDesignWindow_heigh()) / 2);		// 窗口位置
		frame.setSize(SU.getDesignWindow_width(), SU.getDesignWindow_heigh());																					// 设置窗口（宽，高）
		try {
			frame.setIconImage(ImageIO.read(new File(ScreenUtils.getItemPath("PythonLogo"))));																	// Mac 好像不太支持这个，Windows 咋样要试试。
//			System.out.println("-- ImageIO is Working --");
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.setResizable(false);														// 窗口锁定
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);						// 违规操作关闭
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/**
		 * 设置窗口内容
		 * */
		
		/**
		 * 组装零件
		 * */
		
		// 窗口可见
		frame.setVisible(true);
		
	}
	
	/**
	 * Button 监听
	 * */
	
	/**
	 * 内容获取
	 * */

}
