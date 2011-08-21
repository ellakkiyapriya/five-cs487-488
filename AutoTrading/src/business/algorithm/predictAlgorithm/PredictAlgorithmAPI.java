package business.algorithm.predictAlgorithm;


public class PredictAlgorithmAPI {

	public static final String[] predictionAlgorithmList = { "Auto Regression", "Finite Elements" };

	public static AbstractPredictAlgorithm getPredictionAlgorithm(String str) {
		if (str.equals("Auto Regression")) {
			return (new AutoRegressionMA());
		} else if (str.equals("Finite Elements")) {
			return (new AutoRegressionFE());
		}
		return null;
	}
	
	

}
