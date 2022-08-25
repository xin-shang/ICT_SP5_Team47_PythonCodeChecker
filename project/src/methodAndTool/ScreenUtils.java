package methodAndTool;

import java.awt.Toolkit;

public class ScreenUtils {
	
	public final int designWindow_width = 1000;
	public final int designWindow_heigh = 618;
	
	/**
	 * 图片路径
	 * */
	private static final String Imgs_PATH="./src/imgs/";
	
	
	//	如果需要随意调整窗口大小的功能，启用这个方法，如果不需要，这个方法可以弃用。
	/**
	 * 获取当前电脑屏幕的宽度
	 * */
	public static int getScreenWidth(){
	    return Toolkit.getDefaultToolkit().getScreenSize().width;
	}

	/**
	 * 获取当前电脑屏幕的高度
	 * */
	public static int getScreenHeight(){
	    return Toolkit.getDefaultToolkit().getScreenSize().height;
	}
	
	/**
	 * 窗口大小
	 * */
	public int getDesignWindow_width() {
		return designWindow_width;
	}

	public int getDesignWindow_heigh() {
		return designWindow_heigh;
	}
	
	
	/**
	 * 获取图片的方法
	 * */
    public static String getItemPath(String ImgsName){
        return Imgs_PATH + ImgsName + ".png";
    }

}
