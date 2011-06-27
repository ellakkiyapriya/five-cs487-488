package business.predictionAlgorithmEvaluation;

import java.util.ArrayList;

public abstract class ParamForPredictCriteria {
	ArrayList<Price> priceList;
	
	public ParamForPredictCriteria() {};
	public ParamForPredictCriteria(ArrayList<Price> priceList) {
		this.priceList = priceList;
	}
	
	public void add(Price price) {
		this.priceList.add(price);
	}
	
	public  ArrayList<Price> getPriceList() {
		return priceList;
	}

}
