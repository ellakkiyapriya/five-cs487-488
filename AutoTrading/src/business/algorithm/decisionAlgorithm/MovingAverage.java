package business.algorithm.decisionAlgorithm;

import java.util.ArrayList;

import Utility.ParamList;
import dataAccess.databaseManagement.entity.OrderEntity;

public class MovingAverage extends AbstractDecisionAlgorithm {

	@Override
	public ArrayList<OrderEntity> runAlgorithm(ParamList parameters) {
		// TODO Auto-generated method stub
		/*
		ArrayList<Double> price = ((ParamForMovingAverage)parameters).getPrice();
		
		double todayPrice, yesterdayPrice, todayMA, yesterdayMA;
		ArrayList<OrderEntity> orderList = new ArrayList<OrderEntity>();
		
		for (int i = 0; i < price.size() - 1; ++i)
		{
			yesterdayPrice = price.get(i);
			todayPrice = price.get(i+1);
			if ((todayPrice > yesterdayPrice) && (todayMA > yesterdayMA)
					&& (todayMA > todayPrice) && (yesterdayMA < yesterdayPrice))
			{
				// issue buy order
				// true for buy
				// false for sell
				//OrderEntity order = new OrderEntity(true, null, date, assetID, price, volume, matched)
			}
			else if ((todayPrice < yesterdayPrice) && (todayMA < yesterdayMA)
					&& (todayMA < todayPrice) && (yesterdayMA > yesterdayPrice))
			{
				// issue sell order
			}

		}
		*/
		return null;
	}
}
