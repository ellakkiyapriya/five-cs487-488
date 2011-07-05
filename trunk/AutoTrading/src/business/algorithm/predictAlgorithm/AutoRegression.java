package business.algorithm.predictAlgorithm;

import java.sql.Date;
import java.util.ArrayList;
import java.util.TreeMap;

import JSci.maths.statistics.TDistribution;
import Jama.Matrix;
import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.PriceEntity;

public class AutoRegression extends AbstractPredictAlgorithm {

	@Override
	public OutputForPredictionAlgorithm runAlgorithm(
			ParamForPredictAlgorithm parameters) throws Exception {
		// TODO Auto-generated method stub
		TreeMap<AssetEntity, ArrayList<PriceEntity>> map = ((ParamForAutoRegression) parameters)
				.getPriceList();
		AssetEntity asset = map.firstKey();
		ArrayList<PriceEntity> priceEntityList = map.get(map.firstKey());
		ArrayList<Double> priceList = Utility
				.convertPriceEntityListToPriceList(priceEntityList);
		int futureInterval = ((ParamForAutoRegression) parameters)
				.getFutureInterval();
		double confidenceLevel = ((ParamForAutoRegression) parameters)
				.getConfidenceLevel();
		int movingAveragePeriod = (Integer) ((ParamForAutoRegression) parameters)
				.getMA_period();
		int autoRegerssivePeriod = (Integer) ((ParamForAutoRegression) parameters)
				.getAR_period();

		/*
		 * Moving Average Step
		 */
		int nSample = priceList.size();
		ArrayList<Double> movingAverage = new ArrayList<Double>();
		double temp;
		for (int i = 0; i <= nSample - movingAveragePeriod; ++i) {
			temp = 0.0;
			for (int j = 0; j < movingAveragePeriod; ++j) {
				temp += priceList.get(i + j);
			}
			movingAverage.add(temp / movingAveragePeriod);
		}
		/*
		 * Auto Regression step
		 */
		int nMovingAverage = movingAverage.size();

		if (nMovingAverage - autoRegerssivePeriod < 0)
			throw new Exception("There are too few samples");

		double[][] x = new double[nMovingAverage - autoRegerssivePeriod][autoRegerssivePeriod + 1];
		double[] y = new double[nMovingAverage - autoRegerssivePeriod];
		for (int i = 0; i < (nMovingAverage - autoRegerssivePeriod); ++i) {
			for (int j = 0; j <= autoRegerssivePeriod; ++j) {
				if (j != autoRegerssivePeriod) {
					x[i][j] = movingAverage.get(i + j);
				} else {
					x[i][j] = 1;
				}
			}
			y[i] = movingAverage.get(i + autoRegerssivePeriod);
		}
		Matrix matrixX = new Matrix(x);
		Matrix matrixY = new Matrix(y, nMovingAverage - autoRegerssivePeriod);
		Matrix matrixB = matrixX.transpose();
		matrixB = matrixB.times(matrixX);

		try {
			matrixB = matrixB.inverse();
		} catch (Exception e) {
			throw new RuntimeException("Matrix is singular");
		}
		matrixB = matrixB.times(matrixX.transpose());
		matrixB = matrixB.times(matrixY);

		ArrayList<Double> predictionPriceList = new ArrayList<Double>();
		for (int i = autoRegerssivePeriod; i > 0; --i) {
			predictionPriceList
					.add(movingAverage.get(movingAverage.size() - i));
		}
		for (int i = 0; i < futureInterval; ++i) {
			temp = 0.0;
			for (int j = autoRegerssivePeriod; j >= 0; --j) {
				if (j != 0) {
					temp += predictionPriceList.get(predictionPriceList.size()
							- j)
							* matrixB.get(autoRegerssivePeriod - j, 0);
				} else {
					temp += matrixB.get(autoRegerssivePeriod, 0);
				}
			}
			predictionPriceList.add(temp);
		}
		for (int i = autoRegerssivePeriod; i > 0; --i) {
			predictionPriceList.remove(0);
		}

		Matrix matrixC = matrixX.transpose().times(matrixX).inverse();
		double variance = utility.Utility.variance(movingAverage);
		double s_b0 = Math.sqrt(variance * matrixC.get(0, 0));
		double t;
		TDistribution tDistribution = new TDistribution(
				autoRegerssivePeriod - 1);
		t = tDistribution.cumulative(1 - confidenceLevel);

		double lambda = t * s_b0;

		/*
		 * TreeMap<AssetEntity, ArrayList<Double>> predictionPriceMap = new
		 * TreeMap<AssetEntity, ArrayList<Double>>();
		 * predictionPriceMap.put(asset, predictionPriceList); return new
		 * OutputForAutoRegression(predictionPriceMap, startPredictionDate,
		 * lambda);
		 */

		Date lastDate = priceEntityList.get(priceEntityList.size() - 1)
				.getDate();
		Date startPredictionDate = (Date) utility.Utility.increaseDate(lastDate);
		ArrayList<PriceEntry> priceEntryList = Utility.constructPriceList(
				asset, predictionPriceList, startPredictionDate);
		TreeMap<AssetEntity, ArrayList<PriceEntry>> predictionPriceMap = new TreeMap<AssetEntity, ArrayList<PriceEntry>>();
		predictionPriceMap.put(asset, priceEntryList);
		return new OutputForAutoRegression(predictionPriceMap, lambda);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Auto Regression";
	}

	// need to revise here
	public Date setStartPredictionDate(Date lastDate) {
		return null;
	}

	public static void main(String args[]) {
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
		int futureInterval = 10;
		double confidence_level = 0.9;
		int MA_period = 3;
		int AR_period = 3;
		TreeMap<String, Object> map = new TreeMap<String, Object>();
		map.put("Future interval", futureInterval);
		map.put("Confidence level", confidence_level);
		map.put("MA period", MA_period);
		map.put("AR period", AR_period);
		// AutoRegression ar = new AutoRegression();
		/*
		 * ar.setParametersValue(map); ar.setPriceList(priceList);
		 */
		try {

			/*
			 * ParamList output = ar.runAlgorithm(); for (int i = 0; i <
			 * futureInterval; ++i) { OutputForAutoRegression temp =
			 * (OutputForAutoRegression) output;
			 * System.out.println(temp.getPredictionPrice().get(i)); }
			 */

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
