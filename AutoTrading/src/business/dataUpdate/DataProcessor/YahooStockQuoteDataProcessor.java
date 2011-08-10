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

public class YahooStockQuoteDataProcessor extends AbstractDataProcessor {

	public YahooStockQuoteDataProcessor(BufferedReader br, Date date,
			String exchangeName) {
		super(br, date, exchangeName);
		// TODO Auto-generated constructor stub
	}

	public static boolean addExchangeMarketsAndAssetsToDatabase(String strFile) {
		String symbol, companyname;

		// create BufferedReader to read csv file
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(strFile));

			// TODO Auto-generated catch block
			String strLine = "";
			StringTokenizer st = null;
			// read comma separated file line by line
			AssetManager assetManager = new AssetManager();
			ExchangeManager exchangeManager = new ExchangeManager();
			exchangeManager.add(new ExchangeEntity("NASDAQ", -1));
			ExchangeEntity nasdaq = null;
			nasdaq = exchangeManager.getExchangeByName("NASDAQ");
			strLine = br.readLine();
			while ((strLine = br.readLine()) != null) {
				// break comma separated line using ","
				st = new StringTokenizer(strLine, ",");
				symbol = st.nextToken();
				companyname = st.nextToken();
				System.out.println(symbol + " " + companyname);
				assetManager.add(new AssetEntity(companyname, symbol, nasdaq
						.getExchangeID(), "", -1));
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean processData() {
		// TODO Auto-generated method stub
		if (br == null)
			return false;
		
		double open, high, low, close;
		double volume;
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String strLine, symbol;

		ExchangeManager exchangeManager = new ExchangeManager();
		
		String strDate = dateFormat.format(date);
		String[] splitString;
		try {
			// Open an output stream
			br.readLine();
			while ((strLine = br.readLine()) != null) {
				// strLine is one line of text; readLine() strips the newline
				// character(s)

				// Print a line of text
				splitString = strLine.split(",");
				if (splitString[1].contentEquals(strDate))
				{
					symbol = splitString[0];
					open = Double.valueOf(splitString[2]);
					high = Double.valueOf(splitString[3]);
					low = Double.valueOf(splitString[4]);
					close = Double.valueOf(splitString[5]);
					volume = Integer.valueOf(splitString[6]);
					AssetEntity assetEntity = assetManager.getAssetBySymbolAndExchange(symbol, exchangeName);
					if (assetEntity != null)
					{
						PriceEntity priceEntity = new PriceEntity(assetEntity.getAssetID(), new java.sql.Date(date.getTime()), null, volume, close, open, high, low); 
						System.out.println(symbol);
						priceManager.add(priceEntity);
						
						for (int i = 0; i < splitString.length; i++)
							System.out.println(splitString[i]);
					}	
				}
			}
		}
		// Catches any error conditions
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void main(String args[]) {
		addExchangeMarketsAndAssetsToDatabase("NASDAQ_companylist.csv");
	}

}
