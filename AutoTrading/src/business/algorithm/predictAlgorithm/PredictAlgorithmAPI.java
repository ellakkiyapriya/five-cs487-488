package business.algorithm.predictAlgorithm;


public class PredictAlgorithmAPI {

	public static final String[] predictionAlgorithmList = { "Auto Regression MA", "Auto Regression FE" };

	public static AbstractPredictAlgorithm getPredictionAlgorithm(String str) {
		if (str.equals("Auto Regression MA")) {
			return (new AutoRegressionMA());
		} else if (str.equals("Auto Regression FE")) {
			return (new AutoRegressionFE());
		}
		return null;
	}
	
	

}
