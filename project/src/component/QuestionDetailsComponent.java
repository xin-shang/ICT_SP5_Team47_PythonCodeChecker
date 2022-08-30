package component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import methodAndTool.WriteAndRead;

public class QuestionDetailsComponent extends Box{
	
	WriteAndRead WAR = new WriteAndRead();
	
	public static JLabel showID, showQuestion, showSolution, showAnswer, showScorePoint;
	
	// 不确定用提示组件好还是文本框组件好
	public static JTextArea showQuestion0, showSolution0, showAnswer0, showScorePoint0;
	
	public QuestionDetailsComponent() {
		super(BoxLayout.Y_AXIS);
		
		showID = new JLabel("ID: " + (QuestionManagerComponent.selectedRow + 1));
		showQuestion = new JLabel(WAR.readQuestion(QuestionManagerComponent.getValueAt_Table(QuestionManagerComponent.selectedRow, 1)));

		showSolution = new JLabel("SOLUTION");
		showSolution0 = new JTextArea(WAR.readString(QuestionManagerComponent.getValueAt_Table(QuestionManagerComponent.selectedRow, 2)));
		
		showAnswer = new JLabel("ANSWER");
		showAnswer0 = new JTextArea(WAR.readString(QuestionManagerComponent.getValueAt_Table(QuestionManagerComponent.selectedRow, 3)));
		
		showScorePoint = new JLabel("SCORE POINTS");
		showScorePoint0 = new JTextArea("NULL");

		// 设置
		showSolution0.setEditable(false);	// 设置不可编辑
		showAnswer0.setEditable(false);	// 设置不可编辑
		showScorePoint0.setEditable(false);	// 设置不可编辑


		//分区
		Box box1 = Box.createVerticalBox();
		box1.add(showID);
		box1.add(Box.createVerticalStrut(10));
		box1.add(showQuestion);

		
		Box box_Solution = Box.createHorizontalBox();
		JScrollPane scrollPane_Solution = new JScrollPane(showSolution0);
		box_Solution.add(scrollPane_Solution);
		
		Box box_Answer = Box.createHorizontalBox();
		JScrollPane scrollPane_Answer = new JScrollPane(showAnswer0);
		box_Answer.add(scrollPane_Answer);

		Box box_ScorePoint = Box.createHorizontalBox();
		JScrollPane scrollPane_ScorePoint = new JScrollPane(showScorePoint0);
		box_ScorePoint.add(scrollPane_ScorePoint);
		box_ScorePoint.add(scrollPane_ScorePoint);


		Box box2 = Box.createVerticalBox();

		box2.add(showSolution);
		box2.add(box_Solution);
		box2.add(Box.createVerticalStrut(10));
		box2.add(showAnswer);
		box2.add(box_Answer);
		box2.add(Box.createVerticalStrut(10));
		box2.add(showScorePoint);
		box2.add(box_ScorePoint);

		// JScrollPane scrollPane = new JScrollPane(box2);

		// Box box3 = Box.createHorizontalBox();
		// box3.add(scrollPane);

		Box box = Box.createVerticalBox();
		box.add(box1);
		box.add(Box.createVerticalStrut(20));
		box.add(box2);

		this.add(box);

		
	}

}
