package business.dataUpdate;

import business.dataUpdate.DataGetter.AbstractDataGetter;
import business.dataUpdate.DataGetter.Cophieu68DataGetter;
import business.dataUpdate.DataProcessor.AbstractDataProcessor;
import business.dataUpdate.DataProcessor.Cophieu68DataProcessor;

public class Utility {
	public static final String[] ONLINE_RESOURCES = {"Cophieu68"};
	
	public static AbstractDataGetter getDataGetter(String str)
	{
		if (str.equals("Cophieu68"))
		{
			return new Cophieu68DataGetter();
		}
		return null;
	}
	
	public static AbstractDataProcessor getDataProcessor(String str)
	{
		if (str.equals("Cophieu68"))
		{
			return new Cophieu68DataProcessor();
		}
		return null;
	}
}
