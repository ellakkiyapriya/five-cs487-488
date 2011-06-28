package business.algorithm.predictAlgorithm;
import Utility.ParamList;
import java.util.ArrayList;
import java.util.TreeMap;

public abstract class AbstractPredictAlgorithm {
	ParamList parameters;
	public abstract ParamList runAlgorithm() throws Exception;
	public abstract TreeMap<String, Class> getParametersList();
	public abstract void setParametersValue(TreeMap<String, Object> map);
	public abstract void setPriceList(ArrayList<Double> prices);
}
