package business.algorithm.predictAlgorithm;

import java.util.ArrayList;
import java.util.Date;

public class PredictionAlgorithmEntity {
	public static class Entry {
		Date date;
		double midValue, lowValue, highValue;
	}
	ArrayList<Entry> list;
}
