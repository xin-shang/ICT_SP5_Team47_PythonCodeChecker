package methodAndTool;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WriteAndRead {
	// set python command as the system return
	String pythonName = PV.getPythonName();
	static ProjectVariable PV = new ProjectVariable();

	public boolean creatTxtFile(String name) throws IOException {
		boolean flag = false;
		System.out.println("printed");
		// 这个写的是绝对路径，相对路径老报错。我搞不明白。先用结对路径。谁拉到本地后都改一下路径。
		PV.setFilenameTemp("./src/txt/" + name + ".txt");

		File filename = new File(PV.getFilenameTemp());
		if (!filename.exists()) {
			filename.createNewFile();
			flag = true;
		}
		return flag;
	}

	public boolean creatTxtFileDBData(String name) throws IOException {
		boolean flag = false;
		System.out.println("printed");
		// 数据流统一在dbData里
		PV.setFilenameTemp("./src/dbData/POST/" + name + ".txt");

		File filename = new File(PV.getFilenameTemp());
		if (!filename.exists()) {
			filename.createNewFile();
			flag = true;
		}
		return flag;
	}

	public void write2TextFileOutStream(String path, String content) {
		File f = new File(path);
		try {
			FileOutputStream outStr = new FileOutputStream(f);
			BufferedOutputStream buf = new BufferedOutputStream(outStr);
			buf.write(content.getBytes());
			buf.flush();
			buf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeAnswerInTxt(char[] chars, String string) {
		try {
			BufferedWriter code_0 = new BufferedWriter(new FileWriter(PV.getFilenameTemp()));
			for (int j = 0; j <= string.length() - 1; j++) {
				code_0.write(chars[j]);
				code_0.flush();
			}
			code_0.close();
			System.out.println("Write in txt Successful");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run_python_code(String path) {

		try {
			ProcessBuilder pb = new ProcessBuilder(pythonName, path);
			Process p = pb.start();

			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));

			new String(in.readLine()).toString();

		} catch (Exception e) {
			// System.out.println(e);
		}

	}

	public String getPythonOutPut(String path) {
		try {
			ProcessBuilder pb = new ProcessBuilder(pythonName, path);
			Process p = pb.start();
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String value = new String(in.readLine()).toString();
			if (value != null) {
				return value;
			} else {
				return value;
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public String readText(String path) {
		try {
			String content = new String(Files.readAllBytes(Paths.get(path)));
			return content;
		} catch (IOException e1) {
			e1.printStackTrace();
			return "read text error";
		}
	}

	// 问题如果太长，直接使用JLabel，没法换行。需要添加<html></html>才能换行。
	public static String readQuestion(Object object) {
		String pythonQuestion = "<html>" + "<p>" + object + "</p>" + "</html>";
		return pythonQuestion;
	}

	public String readString(Object sentence) {
		String string = (String) (sentence);
		return string;
	}

	// 方法： getCSVQuestionTitle （-> 传到readQuestion（String））

	//
	public String lineNumberString(String number) {
		String pythonQuestion = "<html>" + "<p>" + number + "</p>" + "</html>";
		return pythonQuestion;
	}

	// /**
	// * 返回是否有语法错误的同时，还会存储在./src/txt/sytaxError_b.txt 里面
	// * return true for syntaxError, fasle for no syntaxError, if systaxError,
	// * the result will save in ./src/txt/PyCodeAnswer.txt
	// */
	public boolean checkSolutionSytaxError(String solution) {
		write2TextFileOutStream("./src/txt/PyCodeAnswer.txt", solution);
		run_python_code("./src/python/PYRunPythonCode.py");
		String errorResult = readText("./src/txt/sytaxError_b.txt");
		if (errorResult.equals("True")) {
			return true;
		} else {
			return false;
		}
	}

	// method to transfer string to int
	public int StringToInt(String string_int) {
		try {
			String str = string_int;
			int number = Integer.parseInt(str);
			return number;
		} catch (NumberFormatException ex) {
			return 0;
		}
	}
}