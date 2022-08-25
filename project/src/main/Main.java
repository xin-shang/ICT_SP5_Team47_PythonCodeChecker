package main;

//import controller.HomeController;
import view.HomePage;
import methodAndTool.ScreenUtils;

public class Main {

	// 程序入口
	public static void main(String[] args) {
		
		ScreenUtils SU = new ScreenUtils();

		new HomePage().init();
		
		System.out.println("Design Window Width: " + SU.getDesignWindow_width() + "Design Window Heigh: " + SU.getDesignWindow_heigh());
		System.out.println("Computer Width: " + ScreenUtils.getScreenWidth() + "Computer Heigh: " + ScreenUtils.getScreenHeight());
		
	}
	
}
