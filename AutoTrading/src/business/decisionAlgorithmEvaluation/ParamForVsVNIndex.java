package business.decisionAlgorithmEvaluation;

import java.util.ArrayList;

import Utility.ParamList;
import business.algorithm.decisionAlgorithm.Order;

public class ParamForVsVNIndex extends ParamList{
	double cash;
	double price;
	double volume;
	ArrayList<Order> orderList;

	public ParamForVsVNIndex(ArrayList<Order> orderList, double cash) {
		this.orderList = new ArrayList<Order>(orderList);
		this.cash = 0;
		for (int i = 0; i < orderList.size(); i++) {
			if (orderList.get(i).isOrderType()) {
				this.volume = cash / price;
				this.price = orderList.get(i).getPrice();
				
				this.cash = 0;
			} else {
				this.cash = this.price * this.volume;
				this.price = 0;
				this.volume = 0;
			}
		}
	}
}
