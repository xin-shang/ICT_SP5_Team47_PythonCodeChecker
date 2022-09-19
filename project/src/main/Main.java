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
	
		  //测试用
		  String answer = "a = 0\nif a==0:\n    a+=1\n    print(a)\nelse:\n    a=-1\n    print(a)";

		  System.out.println(answer);


		  markScheme mk = new markScheme("1", "print", 10);
		  markScheme mk2 = new markScheme("2", "else", 40);
		  markScheme mk3 = new markScheme("3", "if", 40);
		  markScheme mk4 = new markScheme("4", "print", 10);


		  List<markScheme> mkl = new ArrayList<markScheme>();
		  mkl.add(mk);
		  mkl.add(mk2);
		  mkl.add(mk3);  
		  mkl.add(mk4);
		  
  
		  keywordAnalysis ka = new keywordAnalysis();
		  int score = ka.getKeyWordSocre(answer, mkl);
		  System.out.println(score);
			//////

		System.out.println("Design Window Width: " + ScreenUtils.getDesignWindow_width() + "Design Window Heigh: "
				+ ScreenUtils.getDesignWindow_heigh());
		System.out.println(
				"Computer Width: " + ScreenUtils.getScreenWidth() + "Computer Heigh: " + ScreenUtils.getScreenHeight());
	}
}
