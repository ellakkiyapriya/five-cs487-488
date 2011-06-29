package business.algorithm.decisionAlgorithm;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.PriceEntity;

public class ParamForMovingAverage extends ParamForDecisionAlgorithm {
	private int MA_period;

	public ParamForMovingAverage(TreeMap<AssetEntity, ArrayList<PriceEntity>> priceList, int MA_period, Date startDate, Double cash) {
		// TODO Auto-generated constructor stub
		this.priceList = priceList;
		this.MA_period = MA_period;
		this.startDate = startDate;
		this.cash = cash;
	}

	public int getMA_period() {
		return MA_period;
	}

	public void setMA_period(int mA_period) {
		MA_period = mA_period;
	}
}
