package business.algorithm.predictAlgorithm;

import java.util.ArrayList;
import java.util.TreeMap;

import dataAccess.databaseManagement.entity.AssetEntity;

public class OutputForParticleFilter extends OutputForPredictionAlgorithm{

	public OutputForParticleFilter(
			TreeMap<AssetEntity, ArrayList<PriceEntry>> predictionPriceList) {
		super(predictionPriceList);
	}

}
