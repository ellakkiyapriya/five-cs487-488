package business.algorithm.decisionAlgorithm;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

import dataAccess.databaseManagement.entity.AssetEntity;

public class OutputOfDecisionAlgorithm {
	ArrayList<Order> orderList;
	
	public OutputOfDecisionAlgorithm () {
		
	}
	public OutputOfDecisionAlgorithm (ArrayList<Order> orderList) {
		this.orderList = orderList;
	}
	
	/**
	 * set volume = -1 if the output of Decision does not include volume 
	 * @param assetEntity
	 * @return
	 */
	public TreeMap<String, Object> toParamOfDecisionCriteria(AssetEntity assetEntity) {
		
		return null;
	}
	
	public ArrayList<Date> getDateList () {
		ArrayList<Date> dateList = new ArrayList<Date>();
		for (Order curOrder : orderList) {
			if (dateList.contains(curOrder.getDate()))
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

}
