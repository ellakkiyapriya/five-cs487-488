package business.algorithm.predictAlgorithm;

import java.sql.Date;
import java.util.ArrayList;
import java.util.TreeMap;

import dataAccess.databaseManagement.entity.AssetEntity;

public abstract class OutputForPredictionAlgorithm {
	TreeMap<AssetEntity, ArrayList<Double>> predictionPriceList;
	Date startPredictionDate;

	public TreeMap<AssetEntity, ArrayList<Double>> getPredictionPriceList() {
		return predictionPriceList;
	}

	public void setPredictionPriceList(
			TreeMap<AssetEntity, ArrayList<Double>> predictionPriceList) {
		this.predictionPriceList = predictionPriceList;
	}

	public OutputForPredictionAlgorithm(
			TreeMap<AssetEntity, ArrayList<Double>> predictionPriceList,
			Date startPredictionDate) {
		super();
		this.predictionPriceList = predictionPriceList;
		this.startPredictionDate = startPredictionDate;
	}

	public Date getStartPredictionDate() {
		return startPredictionDate;
	}

	public void setStartPredictionDate(Date startPredictionDate) {
		this.startPredictionDate = startPredictionDate;
	}
	
	
	

}
