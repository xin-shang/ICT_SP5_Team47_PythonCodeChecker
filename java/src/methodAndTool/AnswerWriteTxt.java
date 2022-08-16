package methodAndTool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AnswerWriteTxt {
	
	public static boolean creatTxtFile (String name) throws IOException {
		boolean flag = false;
		// 这个写的是绝对路径，相对路径老报错。我搞不明白。先用结对路径。谁拉到本地后都改一下路径。
		ProjectVariable.setFilenameTemp("/Users/xin/Desktop/111/java/src/txt/" + name + ".txt");
		File filename = new File(ProjectVariable.getFilenameTemp());
		if (! filename.exists()) {
			filename.createNewFile();
			flag = true;
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
	
}
