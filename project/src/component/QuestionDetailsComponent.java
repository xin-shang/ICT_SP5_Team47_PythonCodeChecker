package component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import methodAndTool.WriteAndRead;

public class QuestionDetailsComponent extends Box{
	
	WriteAndRead WAR = new WriteAndRead();
	
	public static JLabel showID, showType, showQuestion, showResult, showScorePoint;
	
	// 不确定用提示组件好还是文本框组件好
	public static JTextArea showQuestion0;
	
	public QuestionDetailsComponent() {
		super(BoxLayout.Y_AXIS);
		
		showID = new JLabel("ID:" + (QuestionManagerComponent.selectedRow + 1));
		showType = new JLabel("Type" + QuestionManagerComponent.getValueAt(QuestionManagerComponent.selectedRow, 1));
		
		showQuestion = new JLabel(WAR.readQuestion("Question:\n" + QuestionManagerComponent.getValueAt(QuestionManagerComponent.selectedRow, 2)));
		
		this.add(showID);
		this.add(showType);
		this.add(showQuestion);
		
	}

}
