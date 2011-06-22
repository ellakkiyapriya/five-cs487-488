package business.algorithm.predictAlgorithm;

import java.util.ArrayList;
import java.util.Vector;

import business.algorithm.decisionAlgorithm.ParamForMovingAverage;

import Jama.Matrix;
import Utility.ParamList;
import Utility.Utility;

public class AutoRegression extends AbstractPredictAlgorithm {

	@Override
	public ParamList runAlgorithm(ParamList parameters) 
	{
		// TODO Auto-generated method stub
		ArrayList<Double> priceList = ((ParamForAutoRegression)parameters).getPriceList();
		int future_interval = ((ParamForAutoRegression)parameters).getFuture_interval();
		double confidence_level = ((ParamForAutoRegression)parameters).getConfidence_level();
		int MA_period = (Integer)((ParamForAutoRegression)parameters).getMA_period();
		int AR_period = (Integer)((ParamForAutoRegression)parameters).getAR_period();
		
		/*
		 * Moving Average Step
		 * */
		int n_sample = priceList.size();
		ArrayList<Double> MA = new ArrayList<Double>();
		Double temp;
		for (int i = 0; i <= n_sample - MA_period; ++i)
		{
			temp = 0.0;
			for (int j = 0; j < MA_period; ++j)
			{
				temp += priceList.get(i+j); 
			}
			MA.add(temp / MA_period);
		}
		/*
		 * Auto Regression step
		 * */
		int n_MA = MA.size();
		double[][] x = new double[n_MA - AR_period][AR_period + 1];
		double[] y = new double [n_MA - AR_period];
		for (int i = 0; i < (n_MA - AR_period); ++i)
		{
			for (int j = 0; j <= AR_period; ++j)
			{
				if (j != AR_period)
					x[i][j] = MA.get(i + j);
				else
					x[i][j] = 1;
			}
			y[i] = MA.get(i + AR_period);
		}
		Matrix matrixX = new Matrix(x);
		Matrix matrixY = new Matrix(y, n_MA - AR_period);
		Matrix matrixB = matrixX.transpose();
		matrixB = matrixB.times(matrixX);
		matrixB = matrixB.inverse();
		matrixB = matrixB.times(matrixX.transpose());
		matrixB = matrixB.times(matrixY);
		
		ArrayList<Double> predictionPriceList = new ArrayList<Double>();
		for (int i = AR_period; i > 0; --i)
		{
			predictionPriceList.add(MA.get(MA.size() - i));
		}
		for (int i = 0; i < future_interval; ++i)
		{
			temp = 0.0;
			for (int j = AR_period; j >= 0; --j)
			{
				if (j != 0)
					temp += predictionPriceList.get(predictionPriceList.size() - j) * matrixB.get(AR_period - j, 0);
				else
					temp += matrixB.get(AR_period, 0);
			}
			predictionPriceList.add(temp);  
		}
		for (int i = AR_period; i > 0; --i)
		{
			predictionPriceList.remove(0);
		}
		
		Matrix matrixC = matrixX.transpose().times(matrixX).inverse();
		double variance = Utility.calculateVariance(priceList);
		double s_b0 = Math.sqrt(variance * matrixC.get(0, 0));
		double t = 1; // need to revise here
		double lambda = t * s_b0;
		
		return new OutputOfPredictAlgorithm(predictionPriceList, lambda);
	}
	
	public static void main(String args[])
	{
		ArrayList<Double> priceList = new ArrayList<Double>();
		priceList.add(23.0);
		priceList.add(23.1);
		priceList.add(24.0);
		priceList.add(25.2);
		priceList.add(24.8);
		priceList.add(24.9);
		priceList.add(24.7);
		priceList.add(24.4);
		priceList.add(24.4);
		priceList.add(24.4);
		int future_interval = 10;
		double confidence_level = 0.9;
		int MA_period = 3;
		int AR_period = 3;
		double training_ratio = 0.7;
		ParamForAutoRegression input = new ParamForAutoRegression(priceList, future_interval, confidence_level, MA_period, AR_period, training_ratio);
		AutoRegression ar = new AutoRegression();
		ParamList output = ar.runAlgorithm(input);
		for (int i = 0; i < future_interval; ++i) {
			OutputOfPredictAlgorithm temp = (OutputOfPredictAlgorithm) output;
			System.out.println(temp.getPredictionPrice().get(i));
		}
	}
}
