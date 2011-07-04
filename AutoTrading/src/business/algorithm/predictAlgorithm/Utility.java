package business.algorithm.predictAlgorithm;

import java.util.ArrayList;

import dataAccess.databaseManagement.entity.PriceEntity;

public class Utility {
	public static ArrayList<Double> convertPriceEntityListToPriceList(ArrayList<PriceEntity> priceEntityList){
		ArrayList<Double> priceList = new ArrayList<Double>();
		for (PriceEntity entity : priceEntityList)
		{
			priceList.add(entity.getClose());
		}
		return priceList;
	}
	
		
}
