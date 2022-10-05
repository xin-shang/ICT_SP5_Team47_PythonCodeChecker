package main;

//import controller.HomeController;
import view.HomePage;
import methodAndTool.ScreenUtils;
import methodAndTool.WriteAndRead;

public class Main {

	// 程序入口
	public static void main(String[] args) {

		new HomePage().init();
		WriteAndRead WAR = new WriteAndRead();
		boolean a = WAR.createLocalFolder();
		System.out.println(a);
		System.out.println("Design Window Width: " + ScreenUtils.getDesignWindow_width() + "Design Window Heigh: "
				+ ScreenUtils.getDesignWindow_heigh());
		System.out.println(
				"Computer Width: " + ScreenUtils.getScreenWidth() + "Computer Heigh: " + ScreenUtils.getScreenHeight());
	}
}
