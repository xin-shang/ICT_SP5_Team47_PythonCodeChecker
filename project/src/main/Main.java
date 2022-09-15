package main;

//import controller.HomeController;
import view.HomePage;
import JDBC.staffQns_T;
import JDBC.studentQns_T;
import methodAndTool.ScreenUtils;

public class Main {

	// 程序入口
	public static void main(String[] args) {

		new HomePage().init();

		new studentQns_T();
		System.out.println(studentQns_T.getDblength());

		System.out.println("Design Window Width: " + ScreenUtils.getDesignWindow_width() + "Design Window Heigh: "
				+ ScreenUtils.getDesignWindow_heigh());
		System.out.println(
				"Computer Width: " + ScreenUtils.getScreenWidth() + "Computer Heigh: " + ScreenUtils.getScreenHeight());
	}
}
