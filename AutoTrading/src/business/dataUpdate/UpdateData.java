package business.dataUpdate;

import java.util.Date;

import business.dataUpdate.DataGetter.AbstractDataGetter;
import business.dataUpdate.DataGetter.Cophieu68DataGetter;
import business.dataUpdate.DataGetter.ParamForCophieu68DataGetter;
import business.dataUpdate.DataProcessor.AbstractDataProcessor;

public class UpdateData {
	public static boolean updateDataFromSpecificDate(Date date, AbstractDataGetter dataGetter, AbstractDataProcessor dataProcessor)
	{	
		Date currentDate = new Date();
		if (date.after(currentDate))
			return false;
		
		while (date.before(currentDate))
		{
			if (dataGetter instanceof Cophieu68DataGetter)
				dataProcessor.processData(dataGetter.getData(new ParamForCophieu68DataGetter(date)));
			date = Utility.increaseDate(date);
		}
		return true;
	}
}
