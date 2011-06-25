package business.algorithm.predictAlgorithm;

import java.util.ArrayList;

import Utility.ParamList;

public class OutputOfAutoRegression extends ParamList {
	private ArrayList<Double> predictionPrice;
	private double lambda;
	public OutputOfAutoRegression(ArrayList<Double> predictionPrice, Double lambda) {
		// TODO Auto-generated constructor stub
		this.predictionPrice = predictionPrice;
		this.lambda = lambda;
		this.numOfParam = 2;
	}
	public ArrayList<Double> getPredictionPrice() {
		return predictionPrice;
	}
	public void setPredictionPrice(ArrayList<Double> predictionPrice) {
		this.predictionPrice = predictionPrice;
	}
	public double getLambda() {
		return lambda;
	}
	public void setLambda(double lambda) {
		this.lambda = lambda;
	}
}
