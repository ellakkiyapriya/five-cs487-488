package business.dataUpdate.DataProcessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.ExchangeEntity;
import dataAccess.databaseManagement.entity.PriceEntity;
import dataAccess.databaseManagement.manager.AssetManager;
import dataAccess.databaseManagement.manager.ExchangeManager;
import dataAccess.databaseManagement.manager.PriceManager;

public class StaticDataProcessorForCophieu68 {

	
	public static void updateSymbolAndExchangeMarket(String strFile)
	{
		String symbol, exchangemarket, companyname;
 
		//create BufferedReader to read csv file
		BufferedReader br;
		try 
		{
			br = new BufferedReader( new FileReader(strFile));
		
			// TODO Auto-generated catch block
			String strLine = "";
			StringTokenizer st = null;
			//read comma separated file line by line
			AssetManager assetManager = new AssetManager();
			ExchangeManager exchangeManager = new ExchangeManager();
			exchangeManager.add(new ExchangeEntity("HOSE", 0.05));
			exchangeManager.add(new ExchangeEntity("HASTC", 0.07));
			ExchangeEntity hose = null;
			ExchangeEntity hastc = null;
			hose = exchangeManager.getExchangeByName("HOSE");
			hastc = exchangeManager.getExchangeByName("HASTC");
			while( (strLine = br.readLine()) != null)
			{
				//break comma separated line using ","
				st = new StringTokenizer(strLine, ",");
				symbol = "";
				exchangemarket = "";
				companyname = "";
				int numOfTokens = st.countTokens();
				for (int i = 0; i < numOfTokens; ++i)
				{
					if (i == 0)
						symbol = st.nextToken();
					else if (i == 1)
						exchangemarket = st.nextToken();
					else if (i == 2)
						companyname = st.nextToken();
				}
				System.out.println(symbol + " " + exchangemarket + " " + companyname);
				if (exchangemarket.equals("HOSE"))
					assetManager.add(new AssetEntity(companyname, symbol, hose.getExchangeID(), "", 0.05));
				else
					assetManager.add(new AssetEntity(companyname, symbol, hastc.getExchangeID(), "", 0.07));
			}
			
			br.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void updateHistoricalData(String file, String exchangeName)
	{
		String strLine = "";
		StringTokenizer st = null;
		DateFormat dateformat = new SimpleDateFormat("mm/dd/yyyy");
		String symbol = "";
		String strDate = "";
		Date date = new Date();
		double open = 0;
		double close = 0;
		double high = 0;
		double low = 0;
		double volume = 0;
		
		PriceManager priceManager = new PriceManager();
		ExchangeManager exchangeManager = new ExchangeManager();
		AssetManager assetManager = new AssetManager();
		AssetEntity assetEntity = null;
		try
		{
		//read comma separated file line by line
			BufferedReader br = new BufferedReader( new FileReader(file));
			strLine = br.readLine();
			while( (strLine = br.readLine()) != null)
			{
				//break comma separated line using ","
				st = new StringTokenizer(strLine, ",");
				int numOfTokens = st.countTokens();
				for (int i = 0; i < numOfTokens; ++i)
				{
					if (i == 0)
					{	
						symbol = st.nextToken();
						if (symbol.equals("^VNINDEX"))
							symbol = "VNINDEX";
						else if (symbol.equals("^HASTC"))
							symbol = "HASTC Index";
						assetEntity = assetManager.getAssetBySymbolAndExchange(symbol, exchangeName);
					}
					else if (i == 1)
					{
						strDate = st.nextToken();
						date = dateformat.parse(strDate);
					}
					else if (i == 2)
						open = Double.parseDouble(st.nextToken());
					else if (i == 3)
						high = Double.parseDouble(st.nextToken());
					else if (i == 4)
						low = Double.parseDouble(st.nextToken());
					else if (i == 5)
						close = Double.parseDouble(st.nextToken());
					else if (i == 6)
						volume = Double.parseDouble(st.nextToken());
				}
				System.out.println(symbol + " " + String.valueOf(date) + " " + String.valueOf(open) + " " + String.valueOf(high) + " " + String.valueOf(low) + " " + String.valueOf(close) + " " + String.valueOf(volume));
				PriceEntity priceEntity = new PriceEntity(assetEntity.getAssetID(), new java.sql.Date(date.getTime()), null, volume, close, open, high, low);
				priceManager.add(priceEntity);
			}
			
			br.close();
		}
		catch(Exception e)
		{}
	}
	
	public static void updateHistoricalData(String hose_file, String hastc_file, String vnindex_file, String hastcindex_file)
	{	
		AssetManager assetManager = new AssetManager();
		ExchangeManager exchangeManager = new ExchangeManager();
		ExchangeEntity hose = exchangeManager.getExchangeByName("HOSE");
		ExchangeEntity hastc = exchangeManager.getExchangeByName("HASTC");
		AssetEntity vnIndex = new AssetEntity("VNINDEX", "VNINDEX", hose.getExchangeID(), "VNINDEX", 0.05);
		AssetEntity HASTC_index = new AssetEntity("HASTC Index", "HASTC_INDEX", hastc.getExchangeID(), "HASTC_INDEX", 0.07);
		
		assetManager.add(vnIndex);
		assetManager.add(HASTC_index);
		
		updateHistoricalData(hose_file, "HOSE");
		updateHistoricalData(hastc_file, "HASTC");
		updateHistoricalData(vnindex_file, "HOSE");
		updateHistoricalData(hastcindex_file, "HASTC");
	}
	
	public static void main(String args[])
	{
		//updateSymbolAndExchangeMarket("company.csv");
		updateHistoricalData("hose.csv", "hnx.csv", "excel_^vnindex.csv", "excel_^hastc.csv");
	}
}
