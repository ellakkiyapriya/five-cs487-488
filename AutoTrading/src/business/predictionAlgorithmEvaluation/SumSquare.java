package business.predictionAlgorithmEvaluation;

import java.sql.Date;
import java.util.ArrayList;
import java.util.TreeMap;

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
		Date beginDate = new java.sql.Date(paramForPredictList.getPriceList().get(0).getDate().getTime());
		Date endDate = new java.sql.Date(paramForPredictList.getPriceList().get(0).getDate().getTime());
		ArrayList<PriceEntity> realPrice = priceManager.getPriceInInterval(assetEntity.getAssetID(), beginDate, endDate);
		double sum = 0;
		double t;
		for (int i = 0; i < paramForPredictList.getPriceList().size(); i++) {
			t = (paramForPredictList.getPriceList().get(i).getPrice() - realPrice.get(i).getClose());
			sum += t * t;
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
		// TODO Auto-generated method stub
		
	}
	@Override
	public TreeMap<String, Class> getParametersList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
