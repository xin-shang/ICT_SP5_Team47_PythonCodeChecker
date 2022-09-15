package component;

import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import JDBC.staffQns_T;
import methodAndTool.WriteAndRead;
import methodAndTool.markScheme;

public class QuestionDetailsComponent extends Box {

	WriteAndRead WAR = new WriteAndRead();
	staffQns_T DIO = new staffQns_T();

	public static JLabel showID, showQuestion, showSolution, showAnswer, showScorePoint;

	// 不确定用提示组件好还是文本框组件好
	public static JTextArea showQuestion0, showSolution0, showAnswer0, showScorePoint0;

	public QuestionDetailsComponent() {
		super(BoxLayout.Y_AXIS);

		// 暴露选择问题的id--------------------------------------------------------------

		String question_id = (String) QuestionManagerComponent
				.getValueAt_Table(QuestionManagerComponent.getSelectedRow(), 0);

		// list
		List<markScheme> markSchemeList = DIO.getSelectedMarkScheme(question_id);

		String markShcemes = "";

		for (markScheme ms : markSchemeList) {
			String keywordid = ms.getkeywordID();
			String keyword = ms.getKeyword();
			int score = ms.getScore();

			markShcemes = markShcemes + keywordid + ", " + keyword + ", " + String.valueOf(score) + "\n";
		}

		// -----------------------------------------------------------------------------

		showID = new JLabel(
				"ID: " + question_id);
		showQuestion = new JLabel(WriteAndRead
				.readQuestion(QuestionManagerComponent.getValueAt_Table(QuestionManagerComponent.getSelectedRow(), 1)));

		showSolution = new JLabel("SOLUTION");
		showSolution0 = new JTextArea(WAR
				.readString(QuestionManagerComponent.getValueAt_Table(QuestionManagerComponent.getSelectedRow(), 2)));

		showAnswer = new JLabel("ANSWER");
		showAnswer0 = new JTextArea(WAR
				.readString(QuestionManagerComponent.getValueAt_Table(QuestionManagerComponent.getSelectedRow(), 3)));

		showScorePoint = new JLabel("SCORE POINTS");
		showScorePoint0 = new JTextArea(markShcemes);

		// 设置
		showSolution0.setEditable(false); // 设置不可编辑
		showAnswer0.setEditable(false); // 设置不可编辑
		showScorePoint0.setEditable(false); // 设置不可编辑

		// 分区

		Box box_Question = Box.createHorizontalBox();
		box_Question.add(showQuestion);

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

		Box box1 = Box.createVerticalBox();
		box1.add(showID);
		box1.add(Box.createVerticalStrut(10));
		box1.add(box_Question);

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

// //分区

// Box box_ID = Box.createHorizontalBox();
// box_ID.add(showID);
// box_ID.add(Box.createHorizontalStrut(400));

// Box box_Question = Box.createHorizontalBox();
// box_Question.add(showQuestion);

// Box box_Solution = Box.createHorizontalBox();
// box_Solution.add(showSolution);
// box_Solution.add(Box.createHorizontalStrut(500));

// Box box_Solution0 = Box.createHorizontalBox();
// JScrollPane scrollPane_Solution = new JScrollPane(showSolution0);
// box_Solution0.add(scrollPane_Solution);

// Box box_Answer = Box.createHorizontalBox();
// box_Answer.add(showAnswer);
// box_Answer.add(Box.createHorizontalStrut(500));

// Box box_Answer0 = Box.createHorizontalBox();
// JScrollPane scrollPane_Answer = new JScrollPane(showAnswer0);
// box_Answer0.add(scrollPane_Answer);

// Box box_ScorePoint = Box.createHorizontalBox();
// box_ScorePoint.add(showScorePoint);
// box_ScorePoint.add(Box.createHorizontalStrut(500));

// Box box_ScorePoint0 = Box.createHorizontalBox();
// JScrollPane scrollPane_ScorePoint = new JScrollPane(showScorePoint0);
// box_ScorePoint0.add(scrollPane_ScorePoint);
// // box_ScorePoint.add(scrollPane_ScorePoint);

// Box box1 = Box.createVerticalBox();

// box1.add(box_ID);
// box1.add(Box.createVerticalStrut(10));
// box1.add(box_Question);

// Box box2 = Box.createVerticalBox();

// box2.add(box_Solution);
// box2.add(box_Solution0);
// box2.add(Box.createVerticalStrut(10));
// box2.add(box_Answer);
// box2.add(box_Answer0);
// box2.add(Box.createVerticalStrut(10));
// box2.add(box_ScorePoint);
// box2.add(box_ScorePoint0);

// // JScrollPane scrollPane = new JScrollPane(box2);

// // Box box3 = Box.createHorizontalBox();
// // box3.add(scrollPane);

// Box box = Box.createVerticalBox();
// box.add(box1);
// box.add(Box.createVerticalStrut(20));
// box.add(box2);

// this.add(box);