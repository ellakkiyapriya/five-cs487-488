package business.decisionAlgorithmEvaluation;

import java.sql.Date;

import Utility.ParamList;

public abstract class DecisionCriteria {
	ParamList paramList;
	Date beginDate;
	Date endDate;
	
	public DecisionCriteria(ParamList paramList, Date beginDate, Date endDate) {
		// TODO Auto-generated constructor stub
	}

	public abstract double evaluate();

}
