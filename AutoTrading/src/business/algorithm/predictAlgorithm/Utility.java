package business.algorithm.predictAlgorithm;

import java.sql.Date;
import java.util.ArrayList;

import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.PriceEntity;
import dataAccess.databaseManagement.manager.PriceManager;

public class Utility {
	public static ArrayList<PriceEntry> constructPriceList(AssetEntity asset, ArrayList<Double> priceList, Date startPredictingDate){
		PriceManager priceManager = new PriceManager();
		ArrayList<PriceEntity> priceEntityList = priceManager.getPriceInInterval(asset.getAssetID(), startPredictingDate, priceManager.getLatestDate());
		ArrayList<PriceEntry> priceEntryList = new ArrayList<PriceEntry>();
		int i = 0;
		for (double d : priceList) {
			priceEntryList.add(new PriceEntry(priceEntityList.get(i).getDate(), d));
			i++;
		}
		return priceEntryList;
	}
		
}
