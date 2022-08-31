package javaTextCode;

import methodAndTool.*;
import frontEndView.ErrorPage;
import frontEndView.HomePage;

public class Main {

	public static void main(String[] args) {
		
		AnswerWriteTxt A = new AnswerWriteTxt();
		ViewChange VC = new ViewChange();
		HomePage HP = new HomePage();
		
		System.out.println("------\tSTART\t------");
		
		A.writeAnswerInTxt();
		
		System.out.println("------ \tEND\t ------");
		
		
		HP.createWindow ();
		
		
		System.out.println("W: " + VC.getScreenWidthSize() + " H: " + VC.getScreenHeighSize());
		
		ErrorPage.printErrorMessageToConsole();

	}

}
