package methodAndTool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AnswerWriteTxt {
	
	/**
	 * 这个字符串变量功能完整时需要被学生输入的 Python 代码赋值。现在其他内容都没写好暂时直接赋值。
	 * 测试时变量pyCode可以被直接更改.
	 * */
	String pyCode = "#!/usr/bin/python\n"
			+ "# -*- coding: UTF-8 -*-\n"
			+ " \n"
			+ "i = 2\n"
			+ "while(i < 100):\n"
			+ "   j = 2\n"
			+ "   while(j <= (i/j)):\n"
			+ "      if not(i%j): break\n"
			+ "      j = j + 1\n"
			+ "   if (j > i/j) : print i, \" 是素数\"\n"
			+ "   i = i + 1\n"
			+ " \n"
			+ "print \"Good bye!\"";
	
	// 将String类型的内容改成char类型的数组
	char[] chars = pyCode.toCharArray();
	
	// txt文件
	File codeFile = new File ("src/javaTextCode/answer.txt");
	/**
	 * currentThread(): 返回对当前执行的线程对象的引用    return: 当前正在执行的线程
	 * getContextClassLoader(): 返回此线程的上下文ClassLoader。上下文ClassLoader由线程的创建者提供，供在加载类和资源时在该线程中运行的代码使用。如果没有设置，默认是父线程的ClassLoader上下文。原始线程的上下文ClassLoader通常被设置为用于加载应用程序的类加载器。
	 * getResource(): 查找具有给定名称的资源。资源是一些数据(图像、音频、文本等)，可以由类代码以一种独立于代码位置的方式访问。
	 * */
	// File codeFile = new File (Thread.currentThread().getContextClassLoader().getResource("answer.txt").getFile());
	
	public void writeAnswerInTxt () {
		
		try {
			
			codeFile.createNewFile();
			BufferedWriter code_0 = new BufferedWriter (new FileWriter (codeFile));
			for (int j = 0; j <= pyCode.length() - 1; j++) {
					
				code_0.write(chars [j]);
				code_0.flush();
					
			}
				
			code_0.close();
			System.out.println("answer_2 Successful");
			
		} catch (IOException e) {
			
			e.printStackTrace();
		
		}
		
	}

}
