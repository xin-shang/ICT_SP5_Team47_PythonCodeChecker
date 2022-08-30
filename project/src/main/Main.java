package main;

//import controller.HomeController;
import view.HomePage;
import methodAndTool.ScreenUtils;

import methodAndTool.*;

public class Main {

	// 程序入口
	public static void main(String[] args) {

		ScreenUtils SU = new ScreenUtils();

		new HomePage().init();

		dataIO dio = new dataIO();
		dio.getDB()[0].getMarkList().get(0).getMarkPoint();

		System.out.println("Design Window Width: " + SU.getDesignWindow_width() + "Design Window Heigh: "
				+ SU.getDesignWindow_heigh());
		System.out.println(
				"Computer Width: " + ScreenUtils.getScreenWidth() + "Computer Heigh: " + ScreenUtils.getScreenHeight());

	}
}
