package business.decisionAlgorithmEvaluation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import business.algorithm.decisionAlgorithm.Order;
import business.virtualTrading.User;
import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.PriceEntity;
import dataAccess.databaseManagement.manager.AssetManager;
import dataAccess.databaseManagement.manager.PriceManager;

public class VsVNIndex extends DecisionCriteria {

	@SuppressWarnings("unchecked")
	public double vnIndexGrowth() {
		
		long vnIndexID = (new AssetManager()).getAssetBySymbolAndExchange(
				"VNINDEX", "HOSE").getAssetID();
		PriceManager priceManager = new PriceManager();
		TreeMap<Date, ArrayList<business.virtualTrading.Order>> allOrderList = (TreeMap<Date, ArrayList<business.virtualTrading.Order>>) paramList
				.get("Order List");
		
		// TODO priceManager.getStartedPriceOfAssetID(assetID)
		// TODO priceManager.getLatestPriceOfAssetID(assetID)
                ArrayList<PriceEntity> price = priceManager.getPriceInInterval(vnIndexID, new java.sql.Date (allOrderList.firstKey().getTime()),  new java.sql.Date (allOrderList.lastKey().getTime()));
		return price.get(price.size()-1).getClose()/price.get(0).getClose();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TreeMap<String,Double> evaluate() {
		TreeMap<Date, ArrayList<business.virtualTrading.Order>> allOrderList = (TreeMap<Date, ArrayList<business.virtualTrading.Order>>) paramList
				.get("Order List");
		
		for(Date date : allOrderList.keySet()) {
			user.setCurOrderList(allOrderList.get(date));
			user.executeAlgorithmOrder();
		}
		
		TreeMap<String,Double> map = new TreeMap<String, Double>();
		map.put("Ratio", user.profit()/ vnIndexGrowth());

		return map;
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
                debug("orderList.size():", orderList.size());
                this.paramList = new TreeMap<String, Object>();
		this.user = user;
		TreeMap<Date, ArrayList<business.virtualTrading.Order>> allOrderList = new TreeMap<Date, ArrayList<business.virtualTrading.Order>>();
		
		// get DateList
		ArrayList<Date> dateList = new ArrayList<Date>();
		for (Order curOrder : orderList) {
			if (!dateList.contains(curOrder.getDate()))
				dateList.add((Date) curOrder.getDate());
		}
		
		// set TreeMap<Date, ArrayList<Order>>
		for(Date date : dateList) {
			ArrayList<business.virtualTrading.Order> curDateOrderList = new ArrayList<business.virtualTrading.Order>();
			for (Order curOrder :orderList){
				if (curOrder.getDate().equals(date))
//					curDateOrderList.add(curOrder.toOrder(assetEntity));
					curDateOrderList.add(new business.virtualTrading.Order(assetEntity, curOrder.isOrderType(), curOrder.getPrice(), business.virtualTrading.Order.USE_ALL_CASH));
			}
			allOrderList.put(date, curDateOrderList);
		}
		
		paramList.put("Order List", allOrderList);
	}

	
        public static void debug(Object... os) {
		JOptionPane.showMessageDialog(new JFrame(), Arrays.deepToString(os));
	}


}
