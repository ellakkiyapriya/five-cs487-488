package business.algorithm.predictAlgorithm;




import java.util.ArrayList;

import Utility.ParamList;

public class ParamForAutoRegression extends ParamList {
	
	private ArrayList<Double> priceList;
	private int future_interval;
	private double confidence_level; 
	private int MA_period;
	private int AR_period;
	public ParamForAutoRegression(ArrayList<Double> priceList, int future_interval, double confidence_level,
			int MA_period, int AR_period, double training_ratio)
	{
		super();
		this.priceList = priceList;
		this.future_interval = future_interval;
		this.confidence_level = confidence_level;
		this.MA_period = MA_period;
		this.AR_period = AR_period;
		numOfParam = 6;
	}
	public ArrayList<Double> getPriceList() {
		return priceList;
	}
	public void setPriceList(ArrayList<Double> priceList) {
		this.priceList = priceList;
	}
	public int getFuture_interval() {
		return future_interval;
	}
	public void setFuture_interval(int future_interval) {
		this.future_interval = future_interval;
	}
	public double getConfidence_level() {
		return confidence_level;
	}
	public void setConfidence_level(double confidence_level) {
		this.confidence_level = confidence_level;
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
}
