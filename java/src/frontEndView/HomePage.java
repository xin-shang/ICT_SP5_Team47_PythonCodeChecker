package frontEndView;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import methodAndTool.ViewChange;
import methodAndTool.ProjectVariable;

public class HomePage {
	
	ViewChange VC = new ViewChange();
	ProjectVariable PV = new ProjectVariable();
	PythonCodeCheckerPage PCCP = new PythonCodeCheckerPage();
	
	public void createWindow () {
		
		JFrame frame = new JFrame ("Python Code Checker");	 			//创建窗口
		frame.setSize(PV.getDesign_width(), PV.getDesign_heigh());		// 设置窗口(宽, 高)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 			// 违规操作关闭
		
		JPanel panel = new JPanel();									// 创建一个视图（有点像div标签）
		frame.add(panel);												// 添加
		
		PCCP.checkerPage(panel);
		
		frame.setVisible(true);		//窗口可见
		
	}

}
