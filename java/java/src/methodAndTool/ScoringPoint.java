package methodAndTool;

import javax.swing.JTextField;

public class ScoringPoint {
	
	int YAxial = 50;		// 暂时性，后面会根据UI更改。
	JTextField area_ScoringPoint_Details = new JTextField(28);
	JTextField area_ScoringPoint_Decimal = new JTextField(8);
	
	public void addScoringPoint () {
		
		area_ScoringPoint_Details.setBounds(50, YAxial, 600, 50);
		
	}
	
	public void addYAxial (int Y) {
		this.YAxial = Y + 100;
	}
	

}
