package business.algorithm.predictAlgorithm;

import java.util.Date;
import java.util.TreeMap;

public abstract class PredictionAlgorithm {
	public abstract TreeMap<Date, Price> run(InputForPredictionAlgorithm input);
}
