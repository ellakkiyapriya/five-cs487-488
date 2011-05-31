package business.dataUpdate.DataProcessor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.ExchangeEntity;
import dataAccess.databaseManagement.entity.PriceEntity;
import dataAccess.databaseManagement.manager.AssetManager;
import dataAccess.databaseManagement.manager.ExchangeManager;

import business.dataUpdate.Cophieu68OnlineResource;
import business.dataUpdate.DataStream;
import business.dataUpdate.InputForDataProcessor;
import business.dataUpdate.OnlineResource;
import business.dataUpdate.DataGetter.DataGetter;
import business.dataUpdate.DataGetter.DataGetterForCophieu68;

public class DataProcessorForCophieu68 extends DataProcessor {
	
	private AssetManager assetManager = new AssetManager();
	
	@Override
	protected void processData(InputForDataProcessor input) {
		
		// TODO Auto-generated method stub
		double open, high, low, close;
		int volume;
		DateFormat dateFormat2 = new SimpleDateFormat("yyyyMMdd");
		String str, symbol;
		String currentDate = dateFormat2.format(((DataStream)input).date);
		try {
			// Open an output stream
			String[] splitString;
			((DataStream)input).br.readLine();
			while ((str = ((DataStream)input).br.readLine()) != null) {
				// str is one line of text; readLine() strips the newline
				// character(s)

				// Print a line of text
				splitString = str.split(",");
				if (splitString[1].contentEquals(currentDate))
				{
					symbol = splitString[0];
					open = Double.valueOf(splitString[2]);
					high = Double.valueOf(splitString[3]);
					low = Double.valueOf(splitString[4]);
					close = Double.valueOf(splitString[5]);
					volume = Integer.valueOf(splitString[6]);
					//AssetEntity assetEntity = assetManager
					//PriceEntity priceEntity = new PriceEntity(assetID, date, deliveryDate, volume, close, open, high, low)
					//addNewStockCode(stockCode,dateFormat3.format(date),open, high, low, close, volume);
					for (int i = 0; i < splitString.length; i ++)
						System.out.println(splitString[i]);	
				}
				
			}
		}
		// Catches any error conditions
		catch (IOException e) {
			System.err.println("Unable to write to file");
			System.exit(-1);
		}

	}
	public static void main(String args[])
	{
		try
		{
			String symbol, exchangemarket, companyname;
			//csv file containing data
			String strFile = "company.csv";
 
			//create BufferedReader to read csv file
			BufferedReader br = new BufferedReader( new FileReader(strFile));
			String strLine = "";
			StringTokenizer st = null;
 
			//read comma separated file line by line
			
			AssetManager assetManager = new AssetManager();
			ExchangeManager exchangemanager = new ExchangeManager();
			ExchangeEntity hose = null;
			ExchangeEntity hastc = null;
			hose = exchangemanager.getExchangeByName("HOSE");
			hastc = exchangemanager.getExchangeByName("HASTC");
			while( (strLine = br.readLine()) != null)
			{
				//break comma separated line using ","
				st = new StringTokenizer(strLine, ",");
 
				symbol = st.nextToken();
				exchangemarket = st.nextToken();
				companyname = st.nextToken();
				System.out.println(symbol + " " + exchangemarket + " " + companyname);
				/*
				if (exchangemarket.equals("HOSE"))
					assetmanager.add(new AssetEntity(companyname, symbol, hose.getExchangeID(), "", 0.05));
				else
					assetmanager.add(new AssetEntity(companyname, symbol, hastc.getExchangeID(), "", 0.07));
					*/
				//reset token number
			}
			br.close();
		}
		catch (Exception e)
		{}
	}
}
