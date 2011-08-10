package business.dataUpdate;

import java.util.Date;

import business.dataUpdate.DataGetter.AbstractDataGetter;
import business.dataUpdate.DataGetter.Cophieu68DataGetter;
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
			dataGetter.setDate(date);
			dataProcessor.setDate(date);
			dataGetter.setExchangeName("HOSE");
			dataProcessor.setExchangeName("HOSE");
			
			dataProcessor.setBr(dataGetter.getData());
			dataProcessor.processData();
			
			dataGetter.setExchangeName("HASTC");
			dataProcessor.setExchangeName("HASTC");
			
			dataProcessor.setBr(dataGetter.getData());
			dataProcessor.processData();
			
			date = utility.Utility.increaseDate(date);
		}
		return true;
	}
	
	public static void main(String args[])
	{
		AbstractDataGetter dataGetter = new Cophieu68DataGetter();
		AbstractDataProcessor dataProcessor = new Cophieu68DataProcessor();
		updateDataFromSpecificDate(java.sql.Date.valueOf("2011-06-04"), dataGetter, dataProcessor);
	}
}
