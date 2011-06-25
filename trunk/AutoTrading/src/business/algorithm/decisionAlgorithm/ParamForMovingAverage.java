package business.algorithm.decisionAlgorithm;

import java.util.ArrayList;

import Utility.ParamList;

public class ParamForMovingAverage extends ParamList {
	private ArrayList<Double> price;
	private int MA_period;

	public ParamForMovingAverage(ArrayList<Double> price, int MA_period) {
		// TODO Auto-generated constructor stub
		this.price = price;
		this.MA_period = MA_period;
		this.numOfParam = 1;
	}

	public int getMA_period() {
		return MA_period;
	}

	public void setMA_period(int mA_period) {
		MA_period = mA_period;
	}

	public ArrayList<Double> getPrice() {
		return price;
	}

	public void setPrice(ArrayList<Double> price) {
		this.price = price;
	}

}
