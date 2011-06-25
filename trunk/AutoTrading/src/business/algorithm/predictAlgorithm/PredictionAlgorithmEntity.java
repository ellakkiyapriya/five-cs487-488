package business.algorithm.predictAlgorithm;

import java.util.ArrayList;
import java.util.Date;

public class PredictionAlgorithmEntity {
	public static class Entry {
		public Date date;
		public double midValue, lowValue, highValue;
	}
	public ArrayList<Entry> list;
}
