package business.algorithm.decisionAlgorithm;

import java.util.ArrayList;
import Utility.ParamList;
import java.util.TreeMap;

public abstract class AbstractDecisionAlgorithm {
	public abstract ArrayList<Order> runAlgorithm(ParamList parameters);
        public abstract TreeMap<String, Class> getParametersList();
}
