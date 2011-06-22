package business.algorithm.predictAlgorithm;

import java.util.ArrayList;

import Utility.ParamList;

public class ParamForOutputOfAutoRegression extends ParamList {
	public ParamForOutputOfAutoRegression(ArrayList<Double> predictionPrice, Double lambda) { 
		// TODO Auto-generated constructor stub
		this.paramList.add(predictionPrice);
		this.paramList.add(lambda);
		this.numOfParam = 2;
	}
}
