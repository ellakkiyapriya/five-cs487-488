package business.algorithm.decisionAlgorithm;

public class DecisionAlgorithmAPI {
	public static final String[] decisionAlgorithmList = { "Moving Average" };

	public static AbstractDecisionAlgorithm getDecisionAlgorithm(String str) {
		if (str.equals("Moving Average")) {
			return new MovingAverage();
		}
		return null;
	}
}
