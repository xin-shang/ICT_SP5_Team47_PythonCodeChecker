package main;

//import controller.HomeController;
import view.HomePage;

import java.util.ArrayList;
import java.util.List;

import methodAndTool.ScreenUtils;
import methodAndTool.keywordAnalysis;
import methodAndTool.markScheme;

public class Main {

	// 程序入口
	public static void main(String[] args) {

		new HomePage().init();
	


		System.out.println("Design Window Width: " + ScreenUtils.getDesignWindow_width() + "Design Window Heigh: "
				+ ScreenUtils.getDesignWindow_heigh());
		System.out.println(
				"Computer Width: " + ScreenUtils.getScreenWidth() + "Computer Heigh: " + ScreenUtils.getScreenHeight());
	}
}
