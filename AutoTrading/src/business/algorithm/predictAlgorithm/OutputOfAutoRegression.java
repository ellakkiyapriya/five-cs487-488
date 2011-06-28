package business.algorithm.predictAlgorithm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeMap;

import javax.swing.DebugGraphics;

import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.PriceEntity;
import dataAccess.databaseManagement.manager.PriceManager;

import business.algorithm.predictAlgorithm.PredictionAlgorithmEntity.Entry;
import Utility.ParamList;
import Utility.Utility;

public class OutputOfAutoRegression extends ParamList {
	public ArrayList<Double> predictionPrice;
	public double lambda;
	public OutputOfAutoRegression(ArrayList<Double> predictionPrice, Double lambda) {
		// TODO Auto-generated constructor stub
		this.predictionPrice = predictionPrice;
		this.lambda = lambda;
		this.numOfParam = 2;
	}
	public ArrayList<Double> getPredictionPrice() {
		return predictionPrice;
	}
	public void setPredictionPrice(ArrayList<Double> predictionPrice) {
		this.predictionPrice = predictionPrice;
	}
	public double getLambda() {
		return lambda;
	}
	public void setLambda(double lambda) {
		this.lambda = lambda;
	}
	public PredictionAlgorithmEntity convertThis(Date startPredictingDate) {
		PredictionAlgorithmEntity entity = new PredictionAlgorithmEntity();
		entity.list = new ArrayList<PredictionAlgorithmEntity.Entry>();
		Date currentDate = startPredictingDate;
		for (double d : predictionPrice) {
			currentDate = increaseDate(currentDate);
			Entry entry = null;
			{
				entry = new Entry();
				entry.date = currentDate;
				entry.midValue = d;
				entry.lowValue = d - Math.abs(lambda);
				entry.highValue = d + Math.abs(lambda);
			}
			entry.date = currentDate;
			entity.list.add(entry);
		}
		return entity;
	}
	
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
	
	public static Date increaseDate(Date currentDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		cal.add(Calendar.DATE, 1);
		return cal.getTime();
	}
}
