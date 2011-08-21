package business.algorithm.predictAlgorithm;

public class PredictAlgorithmAPI {
	private static final String AUTOREGRESSION_MA = "Auto Regression MA";
	private static final String AUTOREGRESSION_FE = "Auto Regression FE";

	public static final String[] PREDICTION_ALGORITHM_LIST = {
			AUTOREGRESSION_MA, AUTOREGRESSION_FE };

	public static AbstractPredictAlgorithm getPredictionAlgorithm(String str) {
		if (str.equals(AUTOREGRESSION_MA)) {
			return (new AutoRegressionMA());
		} else if (str.equals(AUTOREGRESSION_FE)) {
			return (new AutoRegressionFE());
		}
		return null;
	}

}
