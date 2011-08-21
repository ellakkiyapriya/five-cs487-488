package business.dataUpdate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
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

public class Cophieu68DataUpdate extends AbstractDataUpdate {

	private Date latestDate;

	public Cophieu68DataUpdate() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		this.description = "cophieu68.com";
		this.exchangeNameList = new ArrayList<String>();
		this.exchangeNameList.add("HOSE");
		this.exchangeNameList.add("HASTC");

		// get lastest date
		ExchangeManager exchangeManager = new ExchangeManager();
		ExchangeEntity exchangeEntity = exchangeManager
				.getExchangeByName(this.exchangeNameList.get(0));
		PriceManager priceManager = new PriceManager();
		java.sql.Date date = priceManager
				.getLatestDateOfExchange(exchangeEntity.getExchangeID());
		if (date != null)
			this.latestDate = new java.util.Date(date.getTime());
		else
			try {
				this.latestDate = dateFormat.parse("1-1-2000");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.latestDate = null;
			}

		// set filename list
		this.fileNameList = new ArrayList<String>();
		this.fileNameList.add("HOSE.csv");
		this.fileNameList.add("HASTC.csv");
	}

	public static boolean initExchangeMarketsAndAssets(String fileName) {
		// TODO Auto-generated method stub
		String symbol, exchangemarket, companyname;

		// create BufferedReader to read csv file
		BufferedReader br;
		try {
			// String fileName = "company.csv";
			br = new BufferedReader(new FileReader(fileName));

			// TODO Auto-generated catch block
			String strLine = "";
			StringTokenizer st = null;
			// read comma separated file line by line
			AssetManager assetManager = new AssetManager();
			ExchangeManager exchangeManager = new ExchangeManager();
			exchangeManager.add(new ExchangeEntity("HOSE", 0.05));
			exchangeManager.add(new ExchangeEntity("HASTC", 0.07));
			ExchangeEntity hose = null;
			ExchangeEntity hastc = null;
			hose = exchangeManager.getExchangeByName("HOSE");
			hastc = exchangeManager.getExchangeByName("HASTC");
			while ((strLine = br.readLine()) != null) {
				// break comma separated line using ","
				st = new StringTokenizer(strLine, ",");
				symbol = "";
				exchangemarket = "";
				companyname = "";
				int numOfTokens = st.countTokens();
				for (int i = 0; i < numOfTokens; ++i) {
					if (i == 0)
						symbol = st.nextToken();
					else if (i == 1)
						exchangemarket = st.nextToken();
					else if (i == 2)
						companyname = st.nextToken();
				}
				System.out.println(symbol + " " + exchangemarket + " "
						+ companyname);
				if (exchangemarket.equals("HOSE"))
					assetManager.add(new AssetEntity(companyname, symbol, hose
							.getExchangeID(), "", 0.05));
				else
					assetManager.add(new AssetEntity(companyname, symbol, hastc
							.getExchangeID(), "", 0.07));
			}
			br.close();
			/*
			 * add VNINDEX and HASTC INDEX to asset table
			 */
			AssetEntity vnIndex = new AssetEntity("VNINDEX", "VNINDEX",
					hose.getExchangeID(), "VNINDEX", 0.05);
			AssetEntity HASTC_index = new AssetEntity("HASTCIndex",
					"HASTCINDEX", hastc.getExchangeID(), "HASTCINDEX", 0.07);
			assetManager.add(vnIndex);
			assetManager.add(HASTC_index);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateHistoricalData() {
		// TODO Auto-generated method stub
		String strLine = "";
		StringTokenizer st = null;
		DateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
		String symbol = "";
		String strDate = "";
		Date date = new Date();
		double open = 0;
		double close = 0;
		double high = 0;
		double low = 0;
		double volume = 0;

		PriceManager priceManager = new PriceManager();
		AssetManager assetManager = new AssetManager();
		AssetEntity assetEntity = null;
		for (int k = 0; k < this.fileNameList.size(); ++k) {
			String fileName = this.fileNameList.get(k);
			String exchangeName = this.exchangeNameList.get(k);
			try {
				// read comma separated file line by line
				BufferedReader br = new BufferedReader(new FileReader(fileName));
				int count = 0;
				strLine = br.readLine();
				while ((strLine = br.readLine()) != null) {
					count++;
					if (count > 1000)
						break;
					// break comma separated line using ","
					st = new StringTokenizer(strLine, ",");
					int numOfTokens = st.countTokens();
					for (int i = 0; i < numOfTokens; ++i) {
						if (i == 0) {
							symbol = st.nextToken();
							assetEntity = assetManager
									.getAssetBySymbolAndExchange(symbol,
											exchangeName);
						} else if (i == 1) {
							strDate = st.nextToken();
							date = dateformat.parse(strDate);
						} else if (i == 2)
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
					// System.out.println(symbol + " " + String.valueOf(date) +
					// " " + String.valueOf(open) + " " + String.valueOf(high) +
					// " " + String.valueOf(low) + " " + String.valueOf(close) +
					// " " + String.valueOf(volume));
					PriceEntity priceEntity = new PriceEntity(
							assetEntity.getAssetID(), new java.sql.Date(
									date.getTime()), null, volume, close, open,
							high, low);
					priceManager.add(priceEntity);
				}

				br.close();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean updateData() {
		AssetManager assetManager = new AssetManager();
		PriceManager priceManager = new PriceManager();

		Date currentDate = new Date();
		if (this.latestDate.after(currentDate))
			return false;
		this.latestDate = utility.Utility.increaseDate(this.latestDate);
		while (this.latestDate.before(currentDate)) {
			for (String exchangeName : this.exchangeNameList) {
				HttpURLConnection uc = initConnection(exchangeName,
						this.latestDate);
				try {
					BufferedReader br = new BufferedReader(
							new InputStreamReader(uc.getInputStream()));

					double open, high, low, close;
					double volume;
					DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
					String strLine, symbol;

					String strDate = dateFormat.format(this.latestDate);
					String[] splitString;
					// Open an output stream
					br.readLine();
					while ((strLine = br.readLine()) != null) {
						// strLine is one line of text; readLine() strips the
						// newline
						// character(s)

						// Print a line of text
						splitString = strLine.split(",");
						if (splitString[1].contentEquals(strDate)) {
							symbol = splitString[0];
							open = Double.valueOf(splitString[2]);
							high = Double.valueOf(splitString[3]);
							low = Double.valueOf(splitString[4]);
							close = Double.valueOf(splitString[5]);
							volume = Integer.valueOf(splitString[6]);
							AssetEntity assetEntity = assetManager
									.getAssetBySymbolAndExchange(symbol,
											exchangeName);
							if (assetEntity != null) {
								PriceEntity priceEntity = new PriceEntity(
										assetEntity.getAssetID(),
										new java.sql.Date(this.latestDate
												.getTime()),
										null, volume, close, open, high, low);

								priceManager.add(priceEntity);
							}
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			this.latestDate = utility.Utility.increaseDate(this.latestDate);
		}

		return updateIndexData();
	}

	public boolean updateIndexData() {
		AssetManager assetManager = new AssetManager();
		PriceManager priceManager = new PriceManager();
		String assetName[] = { "VNINDEX", "HASTCINDEX" };
		String exchangeName[] = { "HOSE", "HASTC" };
		for (int i = 0; i < 2; ++i) {
			AssetEntity assetEntity = assetManager.getAssetBySymbolAndExchange(
					assetName[i], exchangeName[i]);
			Date lastDate = priceManager.getLatestDateOfAsset((int) assetEntity
					.getAssetID());
			Date currentDate = new Date();
			Date tempDate;
			Double open, high, close, low, volume;
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			String splitString[];

			String fileLink = "http://www.cophieu68.com/export/metastock.php?id=^vnindex";
			try {
				URL url = new URL(fileLink);
				HttpURLConnection uc = (HttpURLConnection) url.openConnection();
				uc.setRequestProperty("User-Agent", "");

				BufferedReader br = new BufferedReader(new InputStreamReader(
						uc.getInputStream()));

				String strLine = br.readLine();
				while ((strLine = br.readLine()) != null) {
					splitString = strLine.split(",");
					tempDate = df.parse(splitString[1]);
					if ((tempDate.after(currentDate))
							|| (tempDate.before(lastDate)))
						break;
					open = Double.valueOf(splitString[2]);
					high = Double.valueOf(splitString[3]);
					low = Double.valueOf(splitString[4]);
					close = Double.valueOf(splitString[5]);
					volume = Double.valueOf(splitString[6]);
					PriceEntity priceEntity = new PriceEntity(
							(int) assetEntity.getAssetID(), new java.sql.Date(
									tempDate.getTime()), null, volume, close,
							open, high, low);
					priceManager.add(priceEntity);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public boolean updateDateFromDateToDate(AssetEntity assetEntity,
			Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		ExchangeManager exchangeManager = new ExchangeManager();
		ExchangeEntity exchangeEntity = exchangeManager
				.getExchangeByID(assetEntity.getExchangeID());
		PriceManager priceManager = new PriceManager();
		Date currentDate = fromDate;
		HttpURLConnection uc;
		// DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		while (!currentDate.after(toDate)) {
			uc = initConnection(exchangeEntity.getName(), currentDate);
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						uc.getInputStream()));

				double open, high, low, close;
				double volume;
				// DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				String strLine;
				// String symbol;

				// String strDate = dateFormat.format(this.latestDate);
				String[] splitString;
				// Open an output stream
				br.readLine();
				while ((strLine = br.readLine()) != null) {
					splitString = strLine.split(",");
					if (splitString[0].equals(assetEntity.getSymbol())) {
						// symbol = splitString[0];
						open = Double.valueOf(splitString[2]);
						high = Double.valueOf(splitString[3]);
						low = Double.valueOf(splitString[4]);
						close = Double.valueOf(splitString[5]);
						volume = Integer.valueOf(splitString[6]);
						PriceEntity priceEntity = new PriceEntity(
								assetEntity.getAssetID(), new java.sql.Date(
										currentDate.getTime()), null, volume,
								close, open, high, low);
						priceManager.add(priceEntity);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentDate = utility.Utility.increaseDate(currentDate);
		}
		return true;
	}

	public HttpURLConnection initConnection(String exchangeName, Date date) {
		try {
			String fileLink = null;
			if (exchangeName.equals("HASTC"))
				fileLink = "http://www.cophieu68.com/export/dailymetastock.php?stcid=2&date=";
			else
				fileLink = "http://www.cophieu68.com/export/dailymetastock.php?stcid=1&date=";
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String fileName = dateFormat.format(date);
			fileLink = fileLink.concat(fileName);
			URL url = new URL(fileLink);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			uc.setRequestProperty("User-Agent", "");
			return uc;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main (String[] args) {
		AssetManager assetManager = new AssetManager();
		ExchangeManager exchangeManager = new ExchangeManager();
		
		ArrayList<AssetEntity> listAssetEntities = assetManager.getAssetsByExchange(exchangeManager.getExchangeByName("HASTC").getExchangeID());
		
		for (AssetEntity assetEntity : listAssetEntities) {
			
		}
	}
}
