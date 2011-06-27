package business.decisionAlgorithmEvaluation;

import business.algorithm.decisionAlgorithm.Order;
import business.virtualTrading.Asset;
import business.virtualTrading.User;

public abstract class ParamForDecisionCriteria {
	User user;
	
	public ParamForDecisionCriteria () { }
	public ParamForDecisionCriteria (User user) {
		this.user = user; 
	}
	
	public User getUser() {
		return user;
	}
	
	public void addOrder(Order order, Asset asset) {
		
		business.virtualTrading.Order curOrder = new business.virtualTrading.Order(asset, order.isOrderType(), order.getPrice(), 10000000);
		user.addOrder(curOrder);
	}
}
