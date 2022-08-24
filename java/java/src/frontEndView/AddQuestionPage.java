package frontEndView;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import methodAndTool.ProjectVariable;

public class AddQuestionPage extends JFrame{
	
	ProjectVariable PV = new ProjectVariable();
	
	public AddQuestionPage() {
		init();
	}

	private void init() {
		
		// 窗口
		setTitle("Python Code Checker - Add Question Page");		// 窗口名称
		setSize(PV.getDesign_width(), PV.getDesign_heigh());		// 设置窗口（宽，高）
		setResizable(false);										// 窗口锁定
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);					// 违规操作关闭

		// 版本
		JPanel addQuestionPanel = new JPanel();
		addQuestionPanel.setLayout(null);
				
		add(addQuestionPanel);
		
		// 字体
		Font myFont1 = new Font("Arial", Font.PLAIN, 12);
		Font myFont2 = new Font("Arial", Font.PLAIN, 16);
		Font myFont3 = new Font("Arial", Font.PLAIN, 20);
		
		// 页面提示
		PV.getPrompt_3().setFont(myFont3);
		PV.getPrompt_3().setBounds(50, 0, 300, 50);
		addQuestionPanel.add(PV.getPrompt_3());
		
		// Input QuestionID 
		PV.getPrompt_QuestionID().setFont(myFont2);
		PV.getPrompt_QuestionID().setBounds(50, 70, 70, 50);
		addQuestionPanel.add(PV.getPrompt_QuestionID());
		
		PV.getArea_QuestionID().setFont(myFont2);
		PV.getArea_QuestionID().setBounds(120, 70, 200, 50);
		addQuestionPanel.add(PV.getArea_QuestionID());
		
		PV.getPrompt_MaxQuestionID().setFont(myFont1);
		PV.getPrompt_MaxQuestionID().setBounds(350, 70, 100, 50);
		addQuestionPanel.add(PV.getPrompt_MaxQuestionID());			//这里需要抓取ID的最大值
		
		// Input Question
		PV.getPrompt_QuestionContent().setFont(myFont2);
		PV.getPrompt_QuestionContent().setBounds(50, 150, 400, 50);
		addQuestionPanel.add(PV.getPrompt_QuestionContent());
		
		// 
		PV.getArea_QuestionContent().setFont(myFont2);
		PV.getArea_QuestionContent().setBounds(50, 200, 400, 350);
		addQuestionPanel.add(PV.getArea_QuestionContent());
		
		// 添加积分点提示
		PV.getPrompt_ScoringPoint().setFont(myFont2);
		PV.getPrompt_ScoringPoint().setBounds(550, 70, 150, 50);
		addQuestionPanel.add(PV.getPrompt_ScoringPoint());
		
		PV.getButton_ScoringPoint().setFont(myFont2);
		PV.getButton_ScoringPoint().setBounds(750, 70, 200, 50);
		addQuestionPanel.add(PV.getButton_ScoringPoint());
		
		//
		PV.getButton_AddQuestionSubmit().setFont(myFont2);
		PV.getButton_AddQuestionSubmit().setBounds(550, 500, 200, 50);
		addQuestionPanel.add(PV.getButton_AddQuestionSubmit());
		
		PV.getButton_ReturnEditQuestionPage().setFont(myFont2);
		PV.getButton_ReturnEditQuestionPage().setBounds(800, 500, 150, 50);
		addQuestionPanel.add(PV.getButton_ReturnEditQuestionPage());
		
		setVisible(true);
		
	}
	
	int YAxis = 120;
	
	public static void addScoringPoint() {
		
	}

}
