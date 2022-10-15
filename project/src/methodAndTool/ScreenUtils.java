package methodAndTool;

import java.awt.Toolkit;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import java.awt.Font;
import java.util.Enumeration;

public class ScreenUtils {

	public static final int designWindow_width = 1000;
	public static final int designWindow_heigh = 618;

	/**
	 * 图片路径
	 */
	private static final String Imgs_PATH = "./src/imgs/";

	// 如果需要随意调整窗口大小的功能，启用这个方法，如果不需要，这个方法可以弃用。
	
	/**
	 * 获取当前电脑屏幕的宽度
	 */
	public static int getScreenWidth() {
		return Toolkit.getDefaultToolkit().getScreenSize().width;
	}

	/**
	 * 获取当前电脑屏幕的高度
	 */
	public static int getScreenHeight() {
		return Toolkit.getDefaultToolkit().getScreenSize().height;
	}

	/**
	 * 窗口大小
	 */
	public static int getDesignWindow_width() {
		return (int)(designWindow_width * 1.2);
	}

	public static int getDesignWindow_heigh() {
		return (int)(designWindow_heigh * 1.2);
	}

	/**
	 * 获取图片的方法
	 */
	public static String getItemPath(String ImgsName) {
		return Imgs_PATH + ImgsName + ".png";
	}

	/**
	 * 菜单栏中间方法
	 * StringBuffer buf = new StringBuffer();
	 * for (int i = 0; i < field.length; ++i) {
	 * buf.append(field[i]);
	 * }
	 * String s = buf.toString();
	 */
	public static String getBlankSpace(int num) {
		StringBuffer addBlankSpace = new StringBuffer();
		for (int a = 0; a < num; a++) {
			addBlankSpace.append("    ");
		}
		String BlankSpace = addBlankSpace.toString();
		return BlankSpace;
	}


	/**
	* 统一设置字体，父界面设置之后，所有由父界面进入的子界面都不需要再次设置字体
	*/
	public static void InitGlobalFont(Font font) {
		FontUIResource fontRes = new FontUIResource(font);
		for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource) {
				UIManager.put(key, fontRes);
			}
		}
	}

}
