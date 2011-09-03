package business.algorithm.predictAlgorithm;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

import Jama.Matrix;
import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.PriceEntity;

public class LagrangeMA extends AbstractPredictAlgorithm{

	private int MA_period;
	
	public LagrangeMA() {
		super(null, null);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public TreeMap<String, Class> getParametersList() {
		TreeMap<String, Class> map = super.getParametersList();
		map.put("MA period", Integer.class);
		return map;
	}

	@Override
	public void setParametersValue(TreeMap<String, Object> map) {
		super.setParametersValue(map);
		this.setMA_period((Integer) map.get("MA period"));
	}	
	
	@Override
	public OutputForPredictionAlgorithm runAlgorithm() throws Exception {
		AssetEntity asset = priceList.firstKey();
		ArrayList<PriceEntity> priceEntityList = priceList.get(priceList
				.firstKey());
		ArrayList<Double> priceArrayList = Utility
				.convertPriceEntityListToPriceList(priceEntityList);

		/*
		 * Moving Average Step
		 */
		int nSample = priceArrayList.size();
		ArrayList<Double> movingAverage = new ArrayList<Double>();
		Double temp;
		for (int i = 0; i <= nSample - MA_period; ++i) {
			temp = 0.0;
			for (int j = 0; j < MA_period; ++j) {
				temp += priceArrayList.get(i + j);
			}
			movingAverage.add(temp / MA_period);
		}

		
		int n = movingAverage.size();
		Matrix A = null;
		//Lagrange extrapolation function
		{
			double[] y = new double[n];
			double[][] x = new double[n][];
			for (int i = 0; i < n; ++i) {
				y[i] = movingAverage.get(i);
				x[i] = new double[n];
				for (int j = 0; j < n; ++j) {
					x[i][j] = Math.pow(i, j);
				}
			}

			Matrix Y = new Matrix(y, 1);
			Y = Y.transpose();

			Matrix X = new Matrix(x);

			A = X.solve(Y);
		}
				
		ArrayList<Double> predictionPriceList = new ArrayList<Double>();
		
		//calculate predicted price
		for (int i = n; i < n + futureInterval; ++i) {
			double[] x = new double[n];
			for (int j = 0; j < n; ++j) {
				x[j] = Math.pow(i, j);
			}
			Matrix X = new Matrix(x, 1);
			Matrix dotProduct = X.times(A);
			predictionPriceList.add(dotProduct.get(0, 0));
		}
		
		predictionPriceList.add(0,
				priceEntityList.get(priceEntityList.size() - 1).getClose());
		
		Date startPredictionDate = priceEntityList.get(priceEntityList.size() - 1).getDate();
		
		ArrayList<PriceEntry> priceEntryList = Utility.constructPriceList(
				asset, predictionPriceList, startPredictionDate);
		TreeMap<AssetEntity, ArrayList<PriceEntry>> predictionPriceMap = new TreeMap<AssetEntity, ArrayList<PriceEntry>>();
		predictionPriceMap.put(asset, priceEntryList);
		return new CommonOutputForPredictionAlgorithm(predictionPriceMap);	
	}

	public int getMA_period() {
		return MA_period;
	}

	public void setMA_period(int mA_period) {
		MA_period = mA_period;
	}

}
