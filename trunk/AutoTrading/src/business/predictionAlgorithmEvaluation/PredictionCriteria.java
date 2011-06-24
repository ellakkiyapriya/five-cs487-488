package business.predictionAlgorithmEvaluation;

import java.util.ArrayList;

import dataAccess.databaseManagement.entity.PriceEntity;

public abstract class PredictionCriteria {
	ArrayList<PriceEntity> priceList;
	
	public PredictionCriteria(ArrayList<PriceEntity> priceList) {
		this.priceList = new ArrayList<PriceEntity>(priceList);
	}
	
	
	public abstract double evaluate();

}
