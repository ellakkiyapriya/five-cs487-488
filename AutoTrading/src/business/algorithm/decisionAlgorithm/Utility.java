/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package business.algorithm.decisionAlgorithm;

/**
 *
 * @author Dinh
 */
public class Utility {
    public static final String[] decisionAlgorithmList = {"Moving Average"};
    public static AbstractDecisionAlgorithm getDecisionAlgorithm(String str)
	{
		if (str.equals("Moving Average"))
		{
			return new MovingAverage();
		}
		return null;
	}
}

