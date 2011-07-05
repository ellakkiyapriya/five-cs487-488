package business.algorithm.predictAlgorithm;

import java.sql.Date;
import java.util.ArrayList;
import java.util.TreeMap;

import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.PriceEntity;
import dataAccess.databaseManagement.manager.PriceManager;

public class PredictAlgorithmAPI {
	
	public static final String[] predictionAlgorithmList = {"Auto Regression"};

    public static AbstractPredictAlgorithm getPredictionAlgorithm(String str)
	{
		if (str.equals("Auto Regression"))
		{
			return (new AutoRegression());
		}
		return null;
	}

    
}
