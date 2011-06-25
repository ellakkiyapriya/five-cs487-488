/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package business.algorithm.predictAlgorithm;

/**
 *
 * @author Dinh
 */
public class Utility {
    public static final String[] predictionAlgorithmList = {"Auto Regression"};

    public static AbstractPredictAlgorithm getPredictionAlgorithm(String str)
	{
		if (str.equals("Auto Regression"))
		{
			return (new AutoRegression());
		}
		return null;
	}
}
