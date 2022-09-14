package main;

//import controller.HomeController;
import view.HomePage;
import methodAndTool.ScreenUtils;
import JDBC.*;

public class Main {

	// 程序入口
	public static void main(String[] args) {

		ScreenUtils SU = new ScreenUtils();

		new HomePage().init();

		keywordAlternative a = new keywordAlternative();
		String c = a.getData(1, 0).toString();
		System.out.println(c);

		System.out.println("Design Window Width: " + SU.getDesignWindow_width() + "Design Window Heigh: "
				+ SU.getDesignWindow_heigh());
		System.out.println(
				"Computer Width: " + ScreenUtils.getScreenWidth() + "Computer Heigh: " + ScreenUtils.getScreenHeight());
	}
}
