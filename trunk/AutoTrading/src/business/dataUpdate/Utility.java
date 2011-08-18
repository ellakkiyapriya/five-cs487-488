package business.dataUpdate;

public class Utility {
	public static final String[] ONLINE_RESOURCES = {"Cophieu68", "YahooStockDataUpdate"};
	
	public static AbstractDataUpdate getDataUpdate(String str)
	{
		if (str.equals("Cophieu68"))
		{
			return new Cophieu68DataUpdate();
		}
		else if (str.equals("YahooStockDataUpdate"))
		{
			return new YahooStockDataUpdate();
		}
		return null;
	}
	
	public static void main(String args[])
	{

//		Cophieu68DataUpdate.initExchangeMarketsAndAssets("company.csv");
//		Cophieu68DataUpdate cophieu68 = new Cophieu68DataUpdate();
//		cophieu68.updateHistoricalData();		
	
		YahooStockDataUpdate.initExchangeMarketsAndAssets("NASDAQ_companylist.csv");
		YahooStockDataUpdate yahoo = new YahooStockDataUpdate();
		yahoo.updateHistoricalData();		
		
	}
}
