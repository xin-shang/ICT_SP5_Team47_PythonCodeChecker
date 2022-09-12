package methodAndTool;

import java.awt.Font;

public class ProjectVariable {

	public int designWindow_width = 1000;
	public int designWindow_heigh = 618;

	public String filenameTemp;

	/**
	 * 临时文件名称
	 */
	public String getFilenameTemp() {
		return filenameTemp;
	}

	public void setFilenameTemp(String filenameTemp) {
		this.filenameTemp = filenameTemp;
	}

	/**
	 * 窗口大小
	 */
	public int getDesignWindow_width() {
		return designWindow_width;
	}

	public void setDesignWindow_width(int designWindow_width) {
		this.designWindow_width = designWindow_width;
	}

	public int getDesignWindow_heigh() {
		return designWindow_heigh;
	}

	public void setDesignWindow_heigh(int designWindow_heigh) {
		this.designWindow_heigh = designWindow_heigh;
	}

	/**
	 * set python command for mac os and windows
	 */
	private String getOSName() {
		String OS = System.getProperty("os.name");
		return OS;
	}

	public Font getUserTextfieldFontSize() {
		if (getOSName().startsWith("Windows")) {

			Font myFont3 = new Font("Arial", Font.PLAIN, 15);
			return myFont3;
		} else {
			Font myFont2 = new Font("Arial", Font.PLAIN, 14);
			return myFont2;
		}
	}

	public String getPythonName() {
		if (getOSName().startsWith("Windows")) {
			return "python";
		}
		return "python3";
	}

}
