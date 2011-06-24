package business.decisionAlgorithmEvaluation;

import java.util.ArrayList;

import business.algorithm.decisionAlgorithm.Order;

public abstract class DecisionCriteria {
	double cash;
	ArrayList<Order> orderList;

	public DecisionCriteria(ArrayList<Order> orderList, double cash) {
		this.orderList = new ArrayList<Order>(orderList);
		this.cash = cash;
	}

	public abstract double evaluate();

}
