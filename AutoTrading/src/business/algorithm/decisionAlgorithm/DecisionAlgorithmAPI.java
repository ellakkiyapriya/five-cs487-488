package business.algorithm.decisionAlgorithm;

public class DecisionAlgorithmAPI {
	public static final String[] decisionAlgorithmList = { "Moving Average", "Perfect Algorithm" };

	public static AbstractDecisionAlgorithm getDecisionAlgorithm(String str)
	{
		if (str.equals("Moving Average"))
			return new MovingAverage();
		else if (str.equals("Perfect Algorithm"))
			return new PerfectAlgorithm();
		return null;
	}
}
