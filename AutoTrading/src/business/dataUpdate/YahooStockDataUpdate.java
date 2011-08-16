package business.dataUpdate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.ExchangeEntity;
import dataAccess.databaseManagement.entity.PriceEntity;
import dataAccess.databaseManagement.manager.AssetManager;
import dataAccess.databaseManagement.manager.ExchangeManager;
import dataAccess.databaseManagement.manager.PriceManager;

public class YahooStockDataUpdate extends AbstractDataUpdate {

	private Date oldestDate = new Date(1, 1, 1962);
	
	public YahooStockDataUpdate()
	{
		this.exchangeNameList = new ArrayList<String>();
		this.exchangeNameList.add("NASDAQ");		
		
		this.fileNameList = null;
		this.description = null;
	}
	
	@Override
	public boolean updateHistoricalData() {
		return updateDateFromDateToDate(oldestDate, new Date());
	}

	@Override
	public boolean updateData() {
		// TODO Auto-generated method stub
		return updateDateFromDateToDate(utility.Utility.increaseDate(this.lastestDate), new Date());
	}

	@Override
	public boolean initExchangeMarketsAndAssets() {
		// TODO Auto-generated method stub
		String symbol, companyname;

		// create BufferedReader to read csv file
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("NASDAQ_companylist.csv"));

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

	private boolean updateDateFromDateToDate(Date fromDate, Date toDate)
	{
		// TODO Auto-generated method stub
		AssetManager assetManager = new AssetManager();
		PriceManager priceManager = new PriceManager();
		ExchangeManager exchangeManager = new ExchangeManager();
		
		ExchangeEntity exchangeEntity = exchangeManager.getExchangeByName(this.exchangeNameList.get(0));
		if (fromDate.after(toDate))
			return false;
		
		ArrayList<AssetEntity> assetEntityList = assetManager.getAssetsByExchange(exchangeEntity.getExchangeID());
		for (AssetEntity assetEntity : assetEntityList)
		{
			HttpURLConnection uc = initConnection(assetEntity, fromDate, toDate);
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
				
				double open, high, low, close;
				double volume;
				DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				Date date;
				String strLine, strDate;
				String[] splitString;
				
				br.readLine();
				
				while ((strLine = br.readLine()) != null) {
					splitString = strLine.split(",");
					strDate = splitString[0];
					date = new Date(Date.parse(strDate));
					open = Double.valueOf(splitString[1]);
					high = Double.valueOf(splitString[2]);
					low = Double.valueOf(splitString[3]);
					close = Double.valueOf(splitString[4]);
					volume = Double.valueOf(splitString[5]);
					
					PriceEntity priceEntity = new PriceEntity(assetEntity.getAssetID(), new java.sql.Date(date.getTime()), null, volume, close, open, high, low);
					priceManager.add(priceEntity);
					
					for (int i = 0; i < splitString.length; i++)
						System.out.println(splitString[i]);
				}
			} 
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}

		}
		// TODO Auto-generated method stub
		return true;
	}
	
	public HttpURLConnection initConnection(AssetEntity assetEntity, Date fromDate, Date toDate) {
		try {
			String link = "http://ichart.finance.yahoo.com/table.csv?s=";
			link = link.concat(assetEntity.getSymbol() + "&d=");
			link = link.concat(String.valueOf(toDate.getMonth() - 1) + "&e=");
			link = link.concat(String.valueOf(toDate.getDay()) + "&f=");
			link = link.concat(String.valueOf(toDate.getYear()) + "&g=d&a=");
			link = link.concat(String.valueOf(fromDate.getMonth() - 1) + "&b=");
			link = link.concat(String.valueOf(fromDate.getDay()) + "&c=");
			link = link.concat(String.valueOf(fromDate.getYear()) + "&ignore=.csv");
			URL url = new URL(link);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			uc.setRequestProperty("User-Agent", "");
			return uc;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
