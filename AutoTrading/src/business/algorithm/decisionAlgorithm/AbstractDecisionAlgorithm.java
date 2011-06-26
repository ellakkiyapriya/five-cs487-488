package business.algorithm.decisionAlgorithm;

import java.util.ArrayList;
import Utility.ParamList;
import java.util.TreeMap;

public abstract class AbstractDecisionAlgorithm {
    ParamList parameters;
    public abstract ArrayList<Order> runAlgorithm();
    public abstract TreeMap<String, Class> getParametersList();
    public abstract void setParametersValue(TreeMap<String, Object> map);
    public abstract void setPriceList(ArrayList<Double> prices);
}
