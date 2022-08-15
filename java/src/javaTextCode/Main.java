package javaTextCode;

import methodAndTool.*;
import frontEndView.HomePage;
import controller.CodeCheckerController;

public class Main {

	public static void main(String[] args) {
		
		AnswerWriteTxt A = new AnswerWriteTxt();
		ViewChange VC = new ViewChange();
		HomePage HP = new HomePage();
		ProjectVariable PV = new ProjectVariable();
		CodeCheckerController CCC = new CodeCheckerController();
		
		System.out.println("------\tSTART\t------");
		
		A.writeAnswerInTxt();
		
		System.out.println("------ \tEND\t ------");
		
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				try {
					HP.createWindow();
					new CodeCheckerController();
				}catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
		
		System.out.println("W: " + VC.getScreenWidthSize() + " H: " + VC.getScreenHeighSize());
		System.out.println("Design Window Width: " + PV.getDesign_width() + "Design Window Heigh: " + PV.getDesign_heigh());
		System.out.println("Design Manager Width: " + PV.getDesign_width()/10 + "Design Manager Heigh: " + PV.getDesign_heigh());

	}

}
