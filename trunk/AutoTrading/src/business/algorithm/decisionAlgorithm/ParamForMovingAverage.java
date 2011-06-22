package business.algorithm.decisionAlgorithm;

import java.util.ArrayList;

import Utility.ParamList;

public class ParamForMovingAverage extends ParamList {
	private ArrayList<Double> price;
	public ParamForMovingAverage(ArrayList<Double> price) { 
		// TODO Auto-generated constructor stub
		this.price = price;
		this.numOfParam = 1;
	}
	public ArrayList<Double> getPrice() {
		return price;
	}
	public void setPrice(ArrayList<Double> price) {
		this.price = price;
	}
	
}
