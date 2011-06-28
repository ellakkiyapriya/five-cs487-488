package business.dataUpdate;

import java.util.Calendar;
import java.util.Date;

import business.dataUpdate.DataGetter.AbstractDataGetter;
import business.dataUpdate.DataGetter.Cophieu68DataGetter;
import business.dataUpdate.DataGetter.ParamForCophieu68DataGetter;
import business.dataUpdate.DataProcessor.AbstractDataProcessor;
import business.dataUpdate.DataProcessor.Cophieu68DataProcessor;

public class UpdateData {
	public static boolean updateDataFromSpecificDate(Date date, AbstractDataGetter dataGetter, AbstractDataProcessor dataProcessor)
	{	
		Date currentDate = new Date();
		if (date.after(currentDate))
			return false;
		
		while (date.before(currentDate))
		{
			if (dataGetter instanceof Cophieu68DataGetter)
			{
				dataProcessor.processData(dataGetter.getData(new ParamForCophieu68DataGetter(date, "HOSE")));
				dataProcessor.processData(dataGetter.getData(new ParamForCophieu68DataGetter(date, "HASTC")));
			}
			date = Utility.increaseDate(date);
		}
		return true;
	}
	
	public static void main(String args[])
	{
		AbstractDataGetter dataGetter = new Cophieu68DataGetter();
		AbstractDataProcessor dataProcessor = new Cophieu68DataProcessor();
		updateDataFromSpecificDate(java.sql.Date.valueOf("2011-06-20"), dataGetter, dataProcessor);
	}
}
