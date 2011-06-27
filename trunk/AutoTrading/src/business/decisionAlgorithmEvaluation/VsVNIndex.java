package business.decisionAlgorithmEvaluation;

import java.sql.Date;
import java.util.ArrayList;

import business.virtualTrading.User;
import dataAccess.databaseManagement.entity.PriceEntity;
import dataAccess.databaseManagement.manager.PriceManager;

public class VsVNIndex extends DecisionCriteria {

	public double vnIndexGrowth(Date beginDate, Date endDate) {
//TODO	long vnIndexID = assetManager.getAssetIDBySymbol("VNINDEX");

		long vnIndexID = 1;
		PriceManager priceManager = new PriceManager();
		// TODO priceManager.getStartedPriceOfAssetID(assetID)
		// TODO priceManager.getLatestPriceOfAssetID(assetID)
		ArrayList<PriceEntity> price = priceManager.getPriceInInterval(vnIndexID, beginDate, endDate); 
		return price.get(price.size()-1).getClose()/price.get(0).getClose();
	}
	
	@Override
	public double evaluate() {
		User user =  this.paramForDecisionCriteria.getUser();
//		return user.profit()/ vnIndexGrowth(user.getStartDate(), user.getLatestDate() );
		return 0;
	}

	
	

}
