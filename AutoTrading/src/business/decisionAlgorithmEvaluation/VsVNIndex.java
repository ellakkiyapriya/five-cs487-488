package business.decisionAlgorithmEvaluation;

import java.sql.Date;
import java.util.ArrayList;
import java.util.TreeMap;

import business.algorithm.decisionAlgorithm.Order;
import business.virtualTrading.User;
import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.PriceEntity;
import dataAccess.databaseManagement.manager.AssetManager;
import dataAccess.databaseManagement.manager.PriceManager;

public class VsVNIndex extends DecisionCriteria {

	public double vnIndexGrowth(Date beginDate, Date endDate) {
		
		long vnIndexID = (new AssetManager()).getAssetBySymbolAndExchange("VNINDEX", "HOSE").getAssetID();
		PriceManager priceManager = new PriceManager();
		TreeMap<Date, ArrayList<business.virtualTrading.Order>> allOrderList = (TreeMap<Date, ArrayList<business.virtualTrading.Order>>) paramList.get("Orde List"); 
		
		// TODO priceManager.getStartedPriceOfAssetID(assetID)
		// TODO priceManager.getLatestPriceOfAssetID(assetID)
		ArrayList<PriceEntity> price = priceManager.getPriceInInterval(vnIndexID, allOrderList.firstKey(), allOrderList.lastKey()); 
		return price.get(price.size()-1).getClose()/price.get(0).getClose();
	}
	
	@Override
	public TreeMap<String,Double> evaluate() {
		TreeMap<Date, ArrayList<business.virtualTrading.Order>> allOrderList = (TreeMap<Date, ArrayList<business.virtualTrading.Order>>) paramList.get("Orde List");
		
//		User user =  this.paramForDecisionCriteria.getUser();
//		return user.profit()/ vnIndexGrowth(user.getStartDate(), user.getLatestDate() );
		return null;
	}

	@Override
	public TreeMap<String, Class> getParametersList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParametersValue(TreeMap<String, Object> map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "VNIndex Comparision";
	}

	@Override
	public void setParametersValue(User user, ArrayList<Order> orderList,
			AssetEntity assetEntity) {
		this.user = user;
		TreeMap<Date, ArrayList<business.virtualTrading.Order>> allOrderList = new TreeMap<Date, ArrayList<business.virtualTrading.Order>>(); 
	}

	
	

}
