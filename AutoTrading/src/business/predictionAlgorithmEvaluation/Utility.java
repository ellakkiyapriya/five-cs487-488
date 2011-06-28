package business.predictionAlgorithmEvaluation;


public class Utility {
	public static final String[] predictionCriteriaList = {"Residual Sum of Squares"};

    public static PredictionCriteria getPredictionAlgorithm(String str)	{
		if (str.equals("Residual Sum of Squares"))		{
			return (new SumSquare());
		}
		return null;
	}
}
