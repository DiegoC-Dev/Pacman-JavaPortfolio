package utilities;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.DecimalFormat;

public class Utilities {
	public static Dimension getDysplaySize() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		return tk.getScreenSize();
	}
	public static Dimension getAdaptiveWindowSize() {
		Dimension screen = getDysplaySize();
		boolean isBrowserDisplay = screen.width <= 550;
		int maxH = isBrowserDisplay ? (screen.height - 4) : Math.min(580, screen.height - 90);
		int maxW = isBrowserDisplay ? (screen.width - 4) : (screen.width - 40);
		int h = Math.max(360, maxH);
		int w = (int) Math.round(h * (400.0 / 515.0));
		if (w > maxW) {
			w = Math.max(280, maxW);
			h = (int) Math.round(w * (515.0 / 400.0));
		}
		return new Dimension(w, h);
	}
	public static String toFormatDouble(double number) {
		DecimalFormat df2 = new DecimalFormat("#,###.##");
		return df2.format(number);
	}
	public static String toFormatInt(int number) {
		DecimalFormat df2 = new DecimalFormat("#,###");
		return df2.format(number);
	}
}