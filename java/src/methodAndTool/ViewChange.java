package methodAndTool;

import java.awt.Dimension;
import java.awt.Toolkit;

//import frontEndView.HomePage;

public class ViewChange {
	
	int design_width = 1000;
	int design_heigh = 618;
	
//	HomePage HP = new HomePage ();
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public int getScreenWidthSize () {
		int screenWidth = (int) screenSize.getWidth(); 
		return screenWidth;
	}
	
	public int getScreenHeighSize () {
		int  screenHeigh = (int) screenSize.getHeight();
		return screenHeigh;
	}
	
//	public int getViewWidthSize () {
//		int ViewWidth = (int) 
//	}
//	
//	public int getViewHeighSize () {
//		
//	}
	
	public int getItemWidthSize (int v) {
		int item_width = this.getScreenWidthSize() * v / 1000;
		return item_width;
	}
	
	public int getItemHeighSize (int v) {
		int item_heigh = this.getScreenHeighSize() * v / 618;
		return item_heigh;
	}

}
