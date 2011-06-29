package business.decisionAlgorithmEvaluation;


public class Utility {
	public static final String[] predictionCriteriaList = {"VNIndex Comparision"};

    public static DecisionCriteria getPredictionAlgorithm(String str)	{
		if (str.equals("VNIndex Comparision"))		{
			return (new VsVNIndex());
		}
		return null;
	}

}
