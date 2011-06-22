package business.algorithm.decisionAlgorithm;

import Utility.ParamList;

public class OutputOfDecisionAlgorithm extends ParamList {
	public static final boolean ORDER_BUY = true;
	public static final boolean ORDER_SELL = false;
	private boolean orderType;
	private double price;
	private int nth_day_in_future;
	public OutputOfDecisionAlgorithm(boolean orderType, double price,
			int nth_day_in_future) {
		super();
		this.orderType = orderType;
		this.price = price;
		this.nth_day_in_future = nth_day_in_future;
		this.numOfParam = 3;
	} 
	
}
