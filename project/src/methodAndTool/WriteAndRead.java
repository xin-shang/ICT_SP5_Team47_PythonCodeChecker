package methodAndTool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WriteAndRead {

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
			ProcessBuilder pb = new ProcessBuilder("python", path);
			Process p = pb.start();

			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String value = new String(in.readLine()).toString();

			if (value != null) {
				System.out.println("Returned Value is : " + value);
			} else {
				System.out.println("Returned Value is : empty");
			}

		} catch (Exception e) {
			System.out.println(e);
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
	public String readQuestion(String question) {
		String pythonQuestion = "<html>" + "<p>" + question + "</p>" + "</html>";
		return pythonQuestion;
	}

	// 方法： getCSVQuestionTitle （-> 传到readQuestion（String））

	//
	public String lineNumberString(String number) {
		String pythonQuestion = "<html>" + "<p>" + number + "</p>" + "</html>";
		return pythonQuestion;
	}

}
