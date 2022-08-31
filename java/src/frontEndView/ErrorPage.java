package frontEndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ErrorPage {

	/**
	 * 
	 * 
	 * @param sourceFile           an absolute URL of the location of the Python
	 *                             file
	 * @param pythonExecutableFile an absolute URL of the location of the Python
	 *                             interpreter
	 * @param A                    list of string for returning errors or console
	 *                             output after executing the python file
	 * @return the value 0 if the program has syntax error; the value -1 if the
	 *         program does not has syntax error ; the value 1 if the path of
	 *         the source file is wrong;  the value 2 if the path of python executable is wrong.
	 */
	public static int interpretCodesFromPython(String sourceFile, String pythonExecutableFile,
			List<String> MessageFromPython) {
		String s = null;
		int result = 0;
		try {

			ProcessBuilder processBuilder = new ProcessBuilder(pythonExecutableFile, sourceFile);
			Process p = processBuilder.start();

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			while ((s = stdInput.readLine()) != null) {
				MessageFromPython.add(s);
				result = -1;
			}

			while ((s = stdError.readLine()) != null) {
				MessageFromPython.add(s);
				if (s.contains("No such file or directory")) {
					result = 1;	
				}
			}
		} catch (IOException e) {
			MessageFromPython.add(e.getMessage());
			result = 2;
		}

		return result;
	}
	
	public static void printErrorMessageToConsole() {
		String pythonExecutionFile = "C:\\Program Files\\Python310\\python.exe";
		String sourceFile = "source\\testCode.py";
		List<String> messageFromPython = new ArrayList<>();

		int errorNo = 0;

		errorNo = ErrorPage.interpretCodesFromPython(sourceFile, pythonExecutionFile, messageFromPython);
		System.out.println("The error code: " + errorNo);
		

		if (errorNo == -1) {
			System.out.println(
					"Your program runs without syntax error, the following is the output from console (if any): ");
			for (String line : messageFromPython) {
				System.out.println(line);
			}
		}else if (errorNo == 0) {
			System.out.println("Your program has some syntax error: ");
			//messageFromPython.remove(0);
			for (String line : messageFromPython) {
				System.out.println(line);
			}
		} else if (errorNo == 1) {
			System.out.println("Something wrong with the source file path:");
			for (String line : messageFromPython) {
				System.out.println(line);
			}
		}

		else {
			System.out.println("Something wrong with the python interpreter path: ");
			for (String line : messageFromPython) {
				System.out.println(line);
			}
		}
	}
}