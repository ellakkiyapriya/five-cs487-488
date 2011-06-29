package business.algorithm.predictAlgorithm;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.PriceEntity;
import dataAccess.databaseManagement.manager.PriceManager;

public abstract class OutputOfPredictionAlgorithm {
	ArrayList<Double> predictionPrice;
	
	public TreeMap<String,Object> ToParamOfPredictionCriteria (AssetEntity assetEntity ,Date startPredictingDate) {
		PriceManager priceManager = new PriceManager();
		ArrayList<PriceEntity> priceEntityList = priceManager.getPriceInInterval(assetEntity.getAssetID(), (java.sql.Date) startPredictingDate, priceManager.getLatestDate());
		TreeMap<Date,Double> priceList = new TreeMap<Date,Double>();
		int i =0;
		for (double d : predictionPrice) {
			priceList.put(priceEntityList.get(i).getDate(), d);
		}
		TreeMap<String, Object> map = new TreeMap<String, Object>();
		map.put("PriceList", priceList);
		map.put("Asset",assetEntity);
		return map;
	}
	
	public void setPredictionPrice(ArrayList<Double> predictionPrice ){
		this.predictionPrice = predictionPrice;
	}
	
	public ArrayList<Double> getPricditionPrice() {
		return predictionPrice;
	}

}
