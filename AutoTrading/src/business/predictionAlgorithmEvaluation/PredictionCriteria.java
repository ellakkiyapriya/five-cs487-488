package business.predictionAlgorithmEvaluation;

import java.util.TreeMap;

import dataAccess.databaseManagement.entity.AssetEntity;

/*
 *  ArrayList <AbstractPredictAlgorithm> algorithmList;
 *  algorithmList.add(...);
 *  ArrayList<PredictionCriteria> criteriaList;
 *  criteriaList.add();
 *  
 *  for (AbstractPredictAlgorithm curAlgorithm :  algorithmList) 
 *  	for (PredictionCriteria curCriteria : criteriaList) {
 *  		curCriteria.setParamater(curAlgorithm.execute().toParamOfCriteria());
 * 			table[curAlgorithm][curCriteria] = curCriteria.evaluate();
 * 		}	     
 * 
 */


public abstract class PredictionCriteria {
	ParamForPredictCriteria paramForPredictList;
	AssetEntity assetEntity;
	
	
	public abstract TreeMap<String, Double> evaluate();
	
	public void setAssetEntity(AssetEntity assetEntity) {
		this.assetEntity = assetEntity;
	}
	public abstract void setParametersValue(TreeMap<String, Object> map);
	public abstract TreeMap<String, Class> getParametersList();
	public abstract String toString();

}
