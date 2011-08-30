package business.algorithm.predictAlgorithm;

import java.util.ArrayList;
import java.util.Date;

import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.PriceEntity;
import dataAccess.databaseManagement.manager.PriceManager;

public class Utility {
	public static final PriceManager priceManager = new PriceManager();
	
	public static ArrayList<PriceEntry> constructPriceList(AssetEntity asset,
			ArrayList<Double> priceList, Date startPredictingDate) {
		ArrayList<PriceEntity> priceEntityList = priceManager
				.getPriceInInterval(asset.getAssetID(), new java.sql.Date(
						startPredictingDate.getTime()), new java.sql.Date(
						priceManager.getLatestDate().getTime()));

		ArrayList<PriceEntry> priceEntryList = new ArrayList<PriceEntry>();
		ArrayList<Date> dateList = new ArrayList<Date>();
		for (PriceEntity priceEntity : priceEntityList) {
			dateList.add(priceEntity.getDate());
		}

		if (dateList.size() == 0)
			dateList.add(startPredictingDate);

		while (dateList.size() < priceList.size())
			dateList.add(utility.Utility.increaseDate(dateList.get(dateList
					.size() - 1)));
		
//		System.out.println(String.valueOf(priceList.size()));
//		for (Date date : dateList)
//			System.out.println(date.toString());
		
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
