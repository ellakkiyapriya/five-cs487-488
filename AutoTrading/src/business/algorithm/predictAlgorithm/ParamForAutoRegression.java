package business.algorithm.predictAlgorithm;




import java.util.ArrayList;

import Utility.ParamList;

public class ParamForAutoRegression extends ParamList {
	
	public ParamForAutoRegression(ArrayList<Double> priceList, int future_interval, double confidence_level,
			int MA_period, int AR_period, double training_ratio)
	{
		super();
		paramList.add(priceList);
		paramList.add(future_interval);
		paramList.add(confidence_level);
		paramList.add(MA_period);
		paramList.add(AR_period);
		paramList.add(training_ratio);
		numOfParam = 6;
	}
}
