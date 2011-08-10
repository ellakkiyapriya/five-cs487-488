package business.algorithm.predictAlgorithm;

import java.util.ArrayList;
import java.util.TreeMap;

import dataAccess.databaseManagement.entity.AssetEntity;

public class OutputForFiniteElements extends OutputForPredictionAlgorithm{

	public OutputForFiniteElements(
			TreeMap<AssetEntity, ArrayList<PriceEntry>> predictionPriceList) {
		super(predictionPriceList);
		// TODO Auto-generated constructor stub
	}

}
