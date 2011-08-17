package business.algorithm.predictAlgorithm;

import java.util.Date;
import java.util.ArrayList;

import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.PriceEntity;
import dataAccess.databaseManagement.manager.PriceManager;

public class Utility {
	public static ArrayList<PriceEntry> constructPriceList(AssetEntity asset, ArrayList<Double> priceList, Date startPredictingDate) 
	{
		PriceManager priceManager = new PriceManager();
		ArrayList<PriceEntity> priceEntityList = priceManager.getPriceInInterval(asset.getAssetID(), new java.sql.Date(startPredictingDate.getTime()), new java.sql.Date(priceManager.getLatestDate().getTime()));
		ArrayList<PriceEntry> priceEntryList = new ArrayList<PriceEntry>();
		ArrayList<Date> dateList = new ArrayList<Date>();
		for (PriceEntity priceEntity : priceEntityList)
			dateList.add(priceEntity.getDate());
		while (dateList.size() < priceList.size())
			dateList.add(utility.Utility.increaseDate(dateList.get(dateList.size() - 1)));				
		int i = 0;
		for (double d : priceList) {
			priceEntryList.add(new PriceEntry(dateList.get(i), d));
			i++;
		}
		return priceEntryList;
	}

	public static ArrayList<Double> convertPriceEntityListToPriceList(
			ArrayList<PriceEntity> priceEntityList) {
		ArrayList<Double> priceList = new ArrayList<Double>();
		for (PriceEntity entity : priceEntityList) {
			priceList.add(entity.getClose());
		}
		return priceList;
	}

}
