package business.predictionAlgorithmEvaluation;

import java.sql.Date;
import java.util.ArrayList;

import dataAccess.databaseManagement.entity.PriceEntity;
import dataAccess.databaseManagement.manager.PriceManager;

public class SumSquare extends PredictionCriteria{

	public SumSquare(ArrayList<PriceEntity> priceList) {
		super(priceList);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double evaluate() {
		long vnIndexID = 1;
		PriceManager priceManager = new PriceManager();
		Date beginDate = priceList.get(0).getDate();
		Date endDate = priceList.get(priceList.size()-1).getDate();
		ArrayList<PriceEntity> realPrice = priceManager.getPriceInInterval(vnIndexID, beginDate, endDate);
		double sum = 0;
		double t;
		for (int i = 0; i < priceList.size(); i++) {
			t = (priceList.get(i).getClose()-realPrice.get(i).getClose());
			sum += t*t;
			
		}
		
		// TODO Auto-generated method stub
		return sum;
	}

}
