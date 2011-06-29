package business.algorithm.decisionAlgorithm;

import java.util.ArrayList;
import java.util.TreeMap;

public abstract class AbstractDecisionAlgorithm {
    public abstract OutputForDecisionAlgorithm runAlgorithm(ParamForDecisionAlgorithm param);
    public abstract TreeMap<String, Class> getParametersList();
    public abstract void setParametersValue(TreeMap<String, Object> map);
    public abstract void setPriceList(ArrayList<Double> prices);
}
