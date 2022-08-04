package frontEndView;

import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;

import methodAndTool.ViewChange;

public class HomePage {
	
	ViewChange VC = new ViewChange();
	PythonCodeCheckerPage PCCP = new PythonCodeCheckerPage();
	
	public void createWindow () {
		
		JFrame frame = new JFrame ("Python Code Checker");	 	//创建窗口
		frame.setSize(VC.getItemWidthSize(1000), VC.getItemHeighSize(618));		// 设置窗口(宽, 高)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		// 违规操作关闭
		
		JPanel panel = new JPanel();		// 创建一个视图（有点像div标签）
		frame.add(panel);		// 添加
		
		PCCP.checkerPage(panel);
		
		frame.setVisible(true);		//窗口可见
		
	}

}
