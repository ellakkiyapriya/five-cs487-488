package business.decisionAlgorithmEvaluation;

import java.sql.Date;
import java.util.ArrayList;

import dataAccess.databaseManagement.entity.PriceEntity;
import dataAccess.databaseManagement.manager.PriceManager;

import business.algorithm.decisionAlgorithm.Order;

public class VsVNIndex extends DecisionCriteria {
	

	public VsVNIndex(ArrayList<Order> orderList, double cash) {
		super(orderList, cash);
		
		// TODO Auto-generated constructor stub
	}
	
	public double vnIndexGrowth(Date beginDate, Date endDate) {
	//	long vnIndexID = assetManager.getAssetIDBySymbol("VNINDEX");
		long vnIndexID = 1;
		PriceManager priceManager = new PriceManager();
		ArrayList<PriceEntity> price = priceManager.getPriceInInterval(vnIndexID, beginDate, endDate);
		return price.get(price.size()-1).getClose()/price.get(0).getClose();
	}

	@Override
	public double evaluate() {
		// TODO Auto-generated method stub
	//	return this.execute()/this.vnIndexGrowth()
		return 0;
	}

	
	

}
