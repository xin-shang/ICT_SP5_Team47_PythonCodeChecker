package javaTextCode;

import methodAndTool.*;

import controller.HomeController;

public class Main {

	public static void main(String[] args) {

		ProjectVariable PV = new ProjectVariable();

		// new HomePage();
		new HomeController();

		String a = AnswerWriteTxt.getAbsolutePath("Path");
		System.out.println(a);
		System.out.println(
				"Design Window Width: " + PV.getDesign_width() + "Design Window Heigh: " + PV.getDesign_heigh());
	}

}
