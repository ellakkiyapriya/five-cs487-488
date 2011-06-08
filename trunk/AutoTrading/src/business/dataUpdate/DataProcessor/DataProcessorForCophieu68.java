package business.dataUpdate.DataProcessor;

import java.util.ArrayList;
import java.util.Date;

import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.PriceEntity;
import dataAccess.databaseManagement.manager.AssetManager;
import dataAccess.databaseManagement.manager.PriceManager;

import business.dataUpdate.DataStream;
import business.dataUpdate.OnlineResource;
import business.dataUpdate.DataGetter.DataGetter;
import business.dataUpdate.DataGetter.DataGetterForCophieu68;

public class DataProcessorForCophieu68 extends DataProcessor {
	
	private AssetManager assetManager = new AssetManager();
	private PriceManager priceManager = new PriceManager();
	@Override
	protected void processData(DataStream input) {
		
		// TODO Auto-generated method stub
		double open, high, low, close;
		double volume;
		//DateFormat dateFormat2 = new SimpleDateFormat("yyyyMMdd");
		String str, symbol;
		//String currentDate = dateFormat2.format(new Date());
		String currentDate = "20110607";
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
					ArrayList<AssetEntity> listAssets = assetManager.getAssetsBySymbol(symbol);
					PriceEntity priceEntity = null;
					if (listAssets.size() > 0)
						priceEntity = new PriceEntity(listAssets.get(0).getAssetID(), new java.sql.Date(new Date().getTime()), null, volume, close, open, high, low);
					else
						System.out.println(symbol);
					priceManager.add(priceEntity);
					
					for (int i = 0; i < splitString.length; i++)
						System.out.println(splitString[i]);	
				}
			}
		}
		// Catches any error conditions
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String args[])
	{
		try
		{
			DataGetter dataGetter = new DataGetterForCophieu68();
			OnlineResource or = dataGetter.initOnlineResource();
			DataProcessor dataProcessor = new DataProcessorForCophieu68();
			dataProcessor.processData(dataGetter.getData(or));		
			System.out.println("Update sucessfully");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
