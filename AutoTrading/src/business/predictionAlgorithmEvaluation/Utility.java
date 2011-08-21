package business.predictionAlgorithmEvaluation;

public class Utility {
	private static final String RESIDUAL_SUM_SQUARES = "Residual Sum of Squares";
		
	public static final String[] PREDICTION_CRITERIA_LIST = { RESIDUAL_SUM_SQUARES };

	public static PredictionCriteria getPredictionAlgorithm(String str) {
		if (str.equals(RESIDUAL_SUM_SQUARES)) {
			return (new SumSquare());
		}
		return null;
	}
}
