package business.algorithm.predictAlgorithm;

import java.util.ArrayList;
import java.util.TreeMap;

import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.PriceEntity;


public class ParamForAutoRegression extends ParamForPredictAlgorithm {
	
	public double confidenceLevel;
	public int MA_period;
	public int AR_period;
	
	public ParamForAutoRegression(TreeMap<AssetEntity, ArrayList<PriceEntity>> priceList,
			int futureInterval, double confidenceLevel, int MA_period,
			int AR_period) {	
		super(priceList, futureInterval);
		this.confidenceLevel = confidenceLevel;
		this.MA_period = MA_period;
		this.AR_period = AR_period;
	}
	
	public double getConfidenceLevel() {
		return confidenceLevel;
	}
	public void setConfidenceLevel(double confidenceLevel) {
		this.confidenceLevel = confidenceLevel;
	}
	public int getMA_period() {
		return MA_period;
	}
	public void setMA_period(int mA_period) {
		MA_period = mA_period;
	}
	public int getAR_period() {
		return AR_period;
	}
	public void setAR_period(int aR_period) {
		AR_period = aR_period;
	}

	@Override
	public TreeMap<String, Class> getParametersList() {
		// TODO Auto-generated method stub
		TreeMap<String, Class> map = super.getParametersList();
		map.put("Confidence level", Double.class);
		map.put("MA period", Integer.class);
		map.put("AR period", Integer.class);
		return map;
	}

	@Override
	public void setParametersValue(TreeMap<String, Object> map) {
		// TODO Auto-generated method stub
		super.setParametersValue(map);
		this.confidenceLevel = (Double) map.get("Confidence level");
		this.MA_period = (Integer) map.get("MA period");
		this.AR_period = (Integer) map.get("AR period");
	}
	
	
}
