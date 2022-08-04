package frontEndView;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JPanel;

import methodAndTool.ViewChange;

public class PythonCodeCheckerPage {
	
	ViewChange VC = new ViewChange();
	
	public void checkerPage (JPanel panel) {
		
		// 无布局
		panel.setLayout(null);
		
		// 提示语
		JLabel prompt_1 = new JLabel ("Please Input your Python Code in here: ");
		prompt_1.setBounds(VC.getItemWidthSize(50), VC.getItemHeighSize(0), VC.getItemWidthSize(120), VC.getItemHeighSize(100));
		panel.add(prompt_1);
		
		// 行和列
		JTextArea area_1 = new JTextArea (10, 10);
		area_1.setBounds(VC.getItemWidthSize(50), VC.getItemHeighSize(70), VC.getItemWidthSize(400), VC.getItemHeighSize(350));
		panel.add(area_1);
		
		JButton button_1 = new JButton ("Submit");
		button_1.setBounds(VC.getItemWidthSize(50), VC.getItemHeighSize(450), VC.getItemWidthSize(400), VC.getItemHeighSize(40));
		panel.add(button_1);
		
		JLabel prompt_Score = new JLabel ("Grade: XXX");
		prompt_Score.setBounds(VC.getItemWidthSize(550), VC.getItemHeighSize(450), VC.getItemWidthSize(400), VC.getItemHeighSize(40));
		panel.add(prompt_Score);
		
	}
	
	
	

}
