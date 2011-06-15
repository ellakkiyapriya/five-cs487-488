package business.algorithm.decisionAlgorithm;

import java.util.ArrayList;

import dataAccess.databaseManagement.entity.OrderEntity;

import Utility.ParamList;

public abstract class AbstractDecisionAlgorithm {
	
	public abstract ArrayList<OrderEntity> runAlgorithm(ParamList parameters);
	
}
