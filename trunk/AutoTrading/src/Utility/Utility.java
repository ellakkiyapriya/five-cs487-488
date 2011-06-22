package Utility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Utility {
	public static Date increaseDate(Date currentDate)
	{
		Calendar cal = Calendar.getInstance();
		cal.set(currentDate.getYear(), currentDate.getMonth(), currentDate.getDate());
		cal.add(Calendar.DATE, 1);
		currentDate.setDate(cal.get(Calendar.DATE));
		currentDate.setMonth(cal.get(Calendar.MONTH));
		currentDate.setYear(cal.get(Calendar.YEAR));
		return currentDate;
	}
	
	public static double calculateVariance(ArrayList<Double> list)
	{
		double var = 0;
		double mean = calculateMean(list);
		for (Double d : list)
			var += Math.pow(d - mean, 2) / (list.size() - 1);
		return var;
	}
	
	public static double calculateMean(ArrayList<Double> list)
	{
		double mean = 0;
		for (int i = 0; i < list.size(); ++i)
			mean += list.get(i);
		mean = mean / list.size();
		return mean;
	}
}
