package business.algorithm.predictAlgorithm;

import java.sql.Date;
import java.util.ArrayList;
import java.util.TreeMap;

import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.PriceEntity;
import dataAccess.databaseManagement.manager.PriceManager;

public class PredictAlgorithmAPI {
	
	public static final String[] predictionAlgorithmList = {"Auto Regression"};

    public static AbstractPredictAlgorithm getPredictionAlgorithm(String str)
	{
		if (str.equals("Auto Regression"))
		{
			return (new AutoRegression());
		}
		return null;
	}

    /*
    public TreeMap<String,Object> toParamOfPredictionCriteria(AssetEntity assetEntity ,Date startPredictingDate, OutputForAutoRegression output) {
		PriceManager priceManager = new PriceManager();
		ArrayList<PriceEntity> priceEntityList = priceManager.getPriceInInterval(assetEntity.getAssetID(), startPredictingDate, priceManager.getLatestDate());
		TreeMap<Date,Double> priceList = new TreeMap<Date,Double>();
		int i =0;
		for (double d : predictionPrice) {
			priceList.put(priceEntityList.get(i).getDate(), d);
			i++;
		}
		TreeMap<String, Object> map = new TreeMap<String, Object>();
		map.put("PriceList", priceList);
		map.put("Asset",assetEntity);
		return map;
	}*/
}
