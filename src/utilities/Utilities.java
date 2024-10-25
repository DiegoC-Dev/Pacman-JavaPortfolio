package utilities;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.DecimalFormat;

public class Utilities {
	public static Dimension getDysplaySize() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		return tk.getScreenSize();
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
