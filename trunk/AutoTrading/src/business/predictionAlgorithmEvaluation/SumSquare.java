package business.predictionAlgorithmEvaluation;

import java.sql.Date;
import java.util.ArrayList;

import dataAccess.databaseManagement.entity.PriceEntity;
import dataAccess.databaseManagement.manager.PriceManager;

public class SumSquare extends PredictionCriteria{


	@Override
	public double evaluate() {
		long vnIndexID = 1;
		PriceManager priceManager = new PriceManager();
		Date beginDate = new java.sql.Date(paramForPredictList.getPriceList().get(0).getDate().getTime());
		Date endDate = new java.sql.Date(paramForPredictList.getPriceList().get(0).getDate().getTime());
		ArrayList<PriceEntity> realPrice = priceManager.getPriceInInterval(vnIndexID, beginDate, endDate);
		double sum = 0;
		double t;
		for (int i = 0; i < paramForPredictList.getPriceList().size(); i++) {
			t = (paramForPredictList.getPriceList().get(i).getPrice() - realPrice.get(i).getClose());
			sum += t * t;
		}
		
		// TODO Auto-generated method stub
		return java.lang.Math.pow(sum, 0.5) / paramForPredictList.getPriceList().size();
	}

}
