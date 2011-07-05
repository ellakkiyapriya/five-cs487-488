package business.algorithm.decisionAlgorithm;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

import dataAccess.databaseManagement.entity.AssetEntity;

public class OutputForDecisionAlgorithm {
	ArrayList<Order> orderList;
	
	public OutputForDecisionAlgorithm (ArrayList<Order> orderList) {
		this.orderList = orderList;
	}
	
	/**
	 * set volume = -1 if the output of Decision does not include volume
	 * @override this method should be override if there are more than 2 asset  
	 * @param assetEntity
	 * @return
	 */
	/*
	public TreeMap<String, Object> toParamOfDecisionCriteria(AssetEntity assetEntity) {
		TreeMap<Date, ArrayList<business.virtualTrading.Order>> allOrderList = new TreeMap<Date, ArrayList<business.virtualTrading.Order>>();
		ArrayList<Date> dateList = getDateList();
		for (Date date : dateList) {
			ArrayList<business.virtualTrading.Order> curDateOrderList = new ArrayList<business.virtualTrading.Order>();
			for (Order curOrder :orderList){
				if (curOrder.getDate().equals(date))
					curDateOrderList.add(curOrder.toOrder(assetEntity));
			}
			allOrderList.put(date, curDateOrderList);
		}
		
		TreeMap<String, Object> map = new TreeMap<String, Object>();
		map.put("Order List", allOrderList);
		
		return map;
	}
	
	public ArrayList<Date> getDateList () {
		ArrayList<Date> dateList = new ArrayList<Date>();
		for (Order curOrder : orderList) {
			if (!dateList.contains(curOrder.getDate()))
				dateList.add(curOrder.getDate());
		}
		return dateList;
	}
	
	
	
	public void setOrderList (ArrayList<Order> orderList) {
		this.orderList = orderList;
	}
	
	public ArrayList<Order> getOrderList() {
		return orderList;
	}
	*/
}
