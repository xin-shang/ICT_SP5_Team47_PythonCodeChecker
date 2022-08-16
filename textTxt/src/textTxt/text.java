package textTxt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class text {

	public static void main(String[] args) {
		
//		Scanner input = new Scanner (System.in);
//		System.out.println("Python Code: ");
		
		// 不知道为什么，这里只能接收中间没空格的一段文字。只要有空格就完蛋，后面的字符串 一个也收不到。
//		String pyCode_1 = input.next();

		//
//		System.out.println(pyCode_1);
//		System.out.println(pyCode_1.length());
//		System.out.println(pyCode_1.toCharArray());
//		System.out.println("---------------");
		
		// ****测试时直接更改变量pyCode_2.****
		
		String pyCode_2 = " my_string = \"ABCDE\"\n"
				+ "  reversed_string = my_string[::-1]\n"
				+ "  print(reversed_string)";
		char[] chars = pyCode_2.toCharArray();
		
//		System.out.println(pyCode_2);
//		System.out.println(pyCode_2.length());
//		System.out.println(chars);

		
		

//		FileWriter code;
//		
//		try {
//		
//			code = new FileWriter ("/Users/xin/eclipse-workspace/textTxt/txt/answer_1.txt");
//			code.write(pyCode);
//			code.flush();
//			code.close();
//			System.out.println("answer_1 Successful");
//		
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
		
		File codeFile = new File ("/Users/xin/eclipse-workspace/textTxt/txt/answer_2.txt"); 
		
		try {
			
			codeFile.createNewFile();
			BufferedWriter code_0 = new BufferedWriter (new FileWriter (codeFile));
			for (int j = 0; j <= pyCode_2.length() - 1; j++) {
				
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
