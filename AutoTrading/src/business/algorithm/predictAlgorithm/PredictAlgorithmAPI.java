package business.algorithm.predictAlgorithm;

public class PredictAlgorithmAPI {

	public static final String[] predictionAlgorithmList = { "Auto Regression" };

	public static AbstractPredictAlgorithm getPredictionAlgorithm(String str) {
		if (str.equals("Auto Regression")) {
			return (new AutoRegression());
		}
		return null;
	}

}
