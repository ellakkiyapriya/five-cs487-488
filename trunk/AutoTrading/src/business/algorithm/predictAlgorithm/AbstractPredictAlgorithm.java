package business.algorithm.predictAlgorithm;

import Utility.ParamList;
import java.util.TreeMap;

public abstract class AbstractPredictAlgorithm {
	public abstract ParamList runAlgorithm(ParamList parameters);
	public abstract TreeMap<String, Class> getParametersList();
	public abstract ParamList setParametersValue(TreeMap<String, Object> map);
}
