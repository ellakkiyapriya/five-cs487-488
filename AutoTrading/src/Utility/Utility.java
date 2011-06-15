package Utility;

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
}
