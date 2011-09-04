package business.algorithm.predictAlgorithm;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

import JSci.maths.statistics.TDistribution;
import Jama.Matrix;
import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.PriceEntity;

public class AutoRegression extends AbstractPredictAlgorithm{

	public AutoRegression() {
		super(null, null);
	}

	private double confidenceLevel;
	private int AR_period;

	public Double getConfidenceLevel() {
		return confidenceLevel;
	}

	public void setConfidenceLevel(Double confidenceLevel) {
		this.confidenceLevel = confidenceLevel;
	}

	public int getAR_period() {
		return AR_period;
	}

	public void setAR_period(Integer aR_period) {
		AR_period = aR_period;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public TreeMap<String, Class> getParametersList() {
		// TODO Auto-generated method stub
		TreeMap<String, Class> map = super.getParametersList();
		map.put("Confidence level", Double.class);
		map.put("AR period", Integer.class);
		return map;
	}

	@Override
	public void setParametersValue(TreeMap<String, Object> map) {
		// TODO Auto-generated method stub
		super.setParametersValue(map);
		this.confidenceLevel = (Double) map.get("Confidence level");
		this.AR_period = (Integer) map.get("AR period");
	}	
	
	@Override
	public OutputForPredictionAlgorithm runAlgorithm() throws Exception {
		AssetEntity asset = priceList.firstKey();
		ArrayList<PriceEntity> priceEntityList = priceList.get(priceList
				.firstKey());

		ArrayList<Double> priceList = Utility
				.convertPriceEntityListToPriceList(priceEntityList);

		/*
		 * Auto Regression step
		 */
		int nSmootingCurve = priceList.size();

		if (nSmootingCurve - AR_period < 0)
			throw new Exception("There are too few samples");

		double[][] x = new double[nSmootingCurve - AR_period][AR_period + 1];
		double[] y = new double[nSmootingCurve - AR_period];
		for (int i = 0; i < (nSmootingCurve - AR_period); ++i) {
			for (int j = 0; j <= AR_period; ++j) {
				if (j != AR_period) {
					x[i][j] = priceList.get(i + j);
				} else {
					x[i][j] = 1;
				}
			}
			y[i] = priceList.get(i + AR_period);
		}
		Matrix matrixX = new Matrix(x);
		Matrix matrixY = new Matrix(y, nSmootingCurve - AR_period);
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
		for (int i = AR_period; i > 0; --i) {
			predictionPriceList
					.add(priceList.get(priceList.size() - i));
		}

		for (int i = 0; i < futureInterval; ++i) {
			Double temp = 0.0;
			for (int j = AR_period; j >= 0; --j) {
				if (j != 0) {
					temp += predictionPriceList.get(predictionPriceList.size()
							- j)
							* matrixB.get(AR_period - j, 0);
				} else {
					temp += matrixB.get(AR_period, 0);
				}
			}
			predictionPriceList.add(temp);
		}

		for (int i = AR_period; i > 0; --i) {
			predictionPriceList.remove(0);
		}

		predictionPriceList.add(0,
				priceEntityList.get(priceEntityList.size() - 1).getClose());

		Matrix matrixC = matrixX.transpose().times(matrixX).inverse();
		Double variance = utility.Utility.variance(priceList);
		Double s_b0 = Math.sqrt(variance * matrixC.get(0, 0));
		Double t;
		TDistribution tDistribution = new TDistribution(AR_period - 1);
		t = tDistribution.cumulative(1 - confidenceLevel);

		Double lambda = t * s_b0;

		/*
		 * TreeMap<AssetEntity, ArrayList<Double>> predictionPriceMap = new
		 * TreeMap<AssetEntity, ArrayList<Double>>();
		 * predictionPriceMap.put(asset, predictionPriceList); return new
		 * OutputForAutoRegression(predictionPriceMap, startPredictionDate,
		 * lambda);
		 */

		Date startPredictionDate = priceEntityList.get(
				priceEntityList.size() - 1).getDate();
		// startPredictionDate = new
		// java.sql.Date(utility.Utility.increaseDate(startPredictionDate).getTime());
		ArrayList<PriceEntry> priceEntryList = Utility.constructPriceList(
				asset, predictionPriceList, startPredictionDate);
		TreeMap<AssetEntity, ArrayList<PriceEntry>> predictionPriceMap = new TreeMap<AssetEntity, ArrayList<PriceEntry>>();
		predictionPriceMap.put(asset, priceEntryList);
		return new OutputForAutoRegression(predictionPriceMap, lambda);
	}

}
