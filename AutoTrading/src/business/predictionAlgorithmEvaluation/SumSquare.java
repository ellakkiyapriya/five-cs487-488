package business.predictionAlgorithmEvaluation;

import java.sql.Date;
import java.util.ArrayList;
import java.util.TreeMap;

import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.PriceEntity;
import dataAccess.databaseManagement.manager.PriceManager;


public class SumSquare extends PredictionCriteria{

	public SumSquare() {
		
		
	}
	/**
	 * Residual Sum of Squares (RSS) is the sum of squares of residuals.
	 * It is a measure of the discrepancy between the data and an estimation model. 
	 * A small RSS indicates a tight fit of the model to the data.
	 */
	@Override
	public TreeMap<String,Double> evaluate() {
		PriceManager priceManager = new PriceManager();
		AssetEntity assetEntity = (AssetEntity) paramOfPreditionCriteria.get("Asset");
		@SuppressWarnings("unchecked")
		TreeMap<Date, Double> priceList = (TreeMap<Date, Double>) paramOfPreditionCriteria.get("PriceList");
		
			
		Date beginDate = new java.sql.Date(priceList.firstEntry().getKey().getTime());
		Date endDate = new java.sql.Date(priceList.lastEntry().getKey().getTime());
		ArrayList<PriceEntity> realPriceList = priceManager.getPriceInInterval(assetEntity.getAssetID(), beginDate, endDate);
		double sum = 0;
		double t;
		
		for(PriceEntity curEntity : realPriceList) {
			t = ( priceList.get(curEntity.getDate()) -  curEntity.getClose());
			sum = t * t;
		}
		
		TreeMap<String,Double> map = (new TreeMap<String, Double>());
		map.put("RSS", sum);
		return map;
	}

	@Override
	public String toString() {
		return "Residual Sum of Squares";
	}
	
	@Override
	public void setParametersValue(TreeMap<String, Object> map) {
		paramOfPreditionCriteria.put("PriceList",map.get("PriceList"));
		paramOfPreditionCriteria.put("Asset",map.get("Asset"));
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public TreeMap<String, Class> getParametersList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
