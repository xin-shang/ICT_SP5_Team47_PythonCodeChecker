package methodAndTool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class AnswerWriteTxt {
	
	public static boolean creatTxtFile (String name) throws IOException {
		boolean flag = false;
		// 这个写的是绝对路径，相对路径老报错。我搞不明白。先用结对路径。谁拉到本地后都改一下路径。
		ProjectVariable.setFilenameTemp("./src/txt/" + name + ".txt");
		File filename = new File(ProjectVariable.getFilenameTemp());
		if (! filename.exists()) {
			filename.createNewFile();
			flag = true;
		}
		else {
			System.out.print("file is exit");
		}
		return flag;
	}
	
	
	public void writeAnswerInTxt (char[] chars, String string) {
		try {
			BufferedWriter code_0 = new BufferedWriter (new FileWriter (ProjectVariable.getFilenameTemp()));
			for (int j = 0; j <= string.length() - 1; j++) {		
				code_0.write(chars [j]);
				code_0.flush();		
			}
			code_0.close();
			System.out.println("answer_2 Successful");
			
		} catch (IOException e) {	
			e.printStackTrace();
		}
	}
	
	
	
	public static void create_pythonFile() {
		try {
			String python = "import sys\nprint ("+"\"cat\""+")\n";
			BufferedWriter out = new BufferedWriter(new FileWriter("./src/txt/" + "PythonFromJava.py"));
			out.write(python);
			out.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	public static void run_python_code() {

		try{
			ProcessBuilder pb = new ProcessBuilder("python", "./src/python/" + "PyController.py");
			Process p = pb.start();
			 
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			String value = new String(in.readLine()).toString();
			
			if(value != null) {
				System.out.println("Returned Value is : "+value);
			}
			else {
				System.out.println("Returned Value is : empty");
			}
			
		}catch(Exception e){
			System.out.println(e);
		}
		
	}
	
	public static String readText(String path) throws IOException {
		try {
			String content = new String(Files.readAllBytes(Paths.get(path)));
			return content;
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "read text error";
		}
		
		
	    		
	}
	
	
	
	
	
	
	
}
