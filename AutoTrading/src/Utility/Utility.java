package Utility;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Utility {
	public static double calculateVariance(ArrayList<Double> list) {
		double var = 0;
		double mean = calculateMean(list);
		for (Double d : list)
			var += Math.pow(d - mean, 2) / (list.size() - 1);
		return var;
	}

	public static double calculateMean(ArrayList<Double> list) {
		double mean = 0;
		for (int i = 0; i < list.size(); ++i)
			mean += list.get(i);
		mean = mean / list.size();
		return mean;
	}
	public static void debug(Object... os) {
		JOptionPane.showMessageDialog(new JFrame(), Arrays.deepToString(os));
	}
}
