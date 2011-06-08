package business.algorithm.predictAlgorithm;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

public class AutoRegressivePredictionAlgorithm extends PredictionAlgorithm {

	public static final int MA_period = 3;
	public static final int AR_period = 3;
	@Override
	public TreeMap<Date, Price> run(InputForPredictionAlgorithm input) {
		// TODO Auto-generated method stub
		
		ArrayList<Double> listOfPrice = new ArrayList<Double>();
		for (Date date : ((InputForAutoRegressive)input).input.keySet())
		{
			listOfPrice.add(((InputForAutoRegressive)input).input.get(date).getPrice());
		}
		ArrayList<Double> MA_list = new ArrayList<Double>();
		for (int i = 0; i < listOfPrice.size() - MA_period; ++i)
		{
			double temp = 0;
			for (int j = 0; j < MA_period; ++j)
			{
				temp += listOfPrice.get(i+j);
			}
			temp = temp / MA_period;
		}
		
		return null;
	}

}
