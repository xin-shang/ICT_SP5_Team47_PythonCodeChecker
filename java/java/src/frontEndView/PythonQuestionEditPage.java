package frontEndView;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import methodAndTool.ProjectVariable;

public class PythonQuestionEditPage extends JFrame {

        ProjectVariable PV = new ProjectVariable();

        public PythonQuestionEditPage () {
        	init();
        }

        private void init() {
                
            // 窗口
			setTitle("Python Code Checker - Staff Page");				// 窗口名称
			setSize(PV.getDesign_width(), PV.getDesign_heigh());		// 设置窗口（宽，高）
			setResizable(false);										// 窗口锁定
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);					// 违规操作关闭
	
			// 版本
			JPanel staffPanel = new JPanel();
			staffPanel.setLayout(null);	
			
			add(staffPanel);	
			
			//
			Font myFont1 = new Font("Arial", Font.PLAIN, 20);

            // 
			PV.getPrompt_2().setFont(myFont1);
            PV.getPrompt_2().setBounds(50, 10, 600, 50);
            staffPanel.add(PV.getPrompt_2());
            
            // 按钮
            PV.getButton_Add().setBounds(100, 73, 300, 200);
            staffPanel.add(PV.getButton_Add());
    		
    		PV.getButton_Delete().setBounds(600, 73, 300, 200);
    		staffPanel.add(PV.getButton_Delete());
    		
    		PV.getButton_Change().setBounds(100, 323, 300, 200);
    		staffPanel.add(PV.getButton_Change());
    		
    		PV.getButton_Check().setBounds(600, 323, 300, 200);
    		staffPanel.add(PV.getButton_Check());

            setVisible(true);

        }

}