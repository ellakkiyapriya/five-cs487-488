package business.algorithm.predictAlgorithm;

import java.sql.Date;
import java.util.ArrayList;
import java.util.TreeMap;

import dataAccess.databaseManagement.entity.AssetEntity;

public abstract class OutputForPredictionAlgorithm {
	TreeMap<AssetEntity, ArrayList<PriceEntry>> predictionPriceList;

	public TreeMap<AssetEntity, ArrayList<PriceEntry>> getPredictionPriceList() {
		return predictionPriceList;
	}

	public void setPredictionPriceList(
			TreeMap<AssetEntity, ArrayList<PriceEntry>> predictionPriceList) {
		this.predictionPriceList = predictionPriceList;
	}

	public OutputForPredictionAlgorithm(
			TreeMap<AssetEntity, ArrayList<PriceEntry>> predictionPriceList) {
		super();
		this.predictionPriceList = predictionPriceList;
	}

	public TreeMap<String, Class> getOutputParameter()
	{
		TreeMap<String, Class> map = new TreeMap<String, Class>();
		map.put("Prediction price list", TreeMap.class);
		return map;
	}
	
}
