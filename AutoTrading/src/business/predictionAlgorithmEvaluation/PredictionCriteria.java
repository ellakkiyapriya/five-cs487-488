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
 *  		curCriteria.setParamater(curAlgorithm.execute().toParamOfCriteria(assetEntity, startDate));
 * 			table[curAlgorithm][curCriteria] = curCriteria.evaluate();
 * 		}	     
 * 
 */


public abstract class PredictionCriteria {
	TreeMap<String, Object> paramOfPreditionCriteria = new TreeMap<String, Object>(); 

	public abstract TreeMap<String, Double> evaluate();
	public abstract void setParametersValue(TreeMap<String, Object> map);
	public abstract TreeMap<String, Class> getParametersList();
	public abstract String toString();

}
