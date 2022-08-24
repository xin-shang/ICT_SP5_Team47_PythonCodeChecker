package methodAndTool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.List;

public class AnswerWriteTxt {

	public static boolean creatTxtFile(String name) throws IOException {
		boolean flag = false;
		System.out.println("printed");
		// 这个写的是绝对路径，相对路径老报错。我搞不明白。先用结对路径。谁拉到本地后都改一下路径。
		ProjectVariable.setFilenameTemp("./java/src/txt/" + name + ".txt");

		File filename = new File(ProjectVariable.getFilenameTemp());
		if (!filename.exists()) {
			filename.createNewFile();
			flag = true;
		} else {
			System.out.print("file is exit");
		}
		return flag;
	}

	public void writeAnswerInTxt(char[] chars, String string) {
		try {
			BufferedWriter code_0 = new BufferedWriter(new FileWriter(ProjectVariable.getFilenameTemp()));
			for (int j = 0; j <= string.length() - 1; j++) {
				code_0.write(chars[j]);
				code_0.flush();
			}
			code_0.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void run_python_code(String python_path) {
		System.out.println("--------------------------------------------");
		try {
			ProcessBuilder pb = new ProcessBuilder("python", python_path);
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

	public static void run_python_code_TRY(String python_path) {

		ProcessBuilder processBuilder = new ProcessBuilder("python", ("hello.py"));
		processBuilder.redirectErrorStream(true);

		Process process = processBuilder.start();
		List<String> results = process.getInputStream();

		assertThat("Results should not be empty", results, is(not(empty())));
		assertThat("Results should contain output of script: ", results, hasItem(
				containsString("Hello Baeldung Readers!!")));

		int exitCode = process.waitFor();
		assertEquals("No errors should be detected", 0, exitCode);

	}

	public static String readText(String path) throws IOException {
		try {
			String content = new String(Files.readAllBytes(Paths.get(path)));
			return content;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "read text error";
		}

	}

	/*
	 * @param
	 * 
	 * @return User
	 */
	public static String getAbsolutePath() {
		try {

			String absolute = System.getProperty("user.dir");
			String path_r = Paths.get(absolute).getParent().toString();
			String new_path_r = path_r.replace('\\', '/');

			return new_path_r;

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return e.getMessage();
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
