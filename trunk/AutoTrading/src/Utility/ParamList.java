package Utility;

import java.util.Date;
import java.util.TreeMap;

import dataAccess.databaseManagement.entity.AssetEntity;

//This can be replaced by the class Object
public abstract class ParamList {
	protected int numOfParam;
	
	public ParamList() {
	}
	
	public int getNumOfParam() {
		return numOfParam;
	}
	public void setNumOfParam(int numOfParam) {
		this.numOfParam = numOfParam;
	}
	
	public TreeMap<String,Object> toParamOfPredictionCriteria (AssetEntity assetEntity ,Date startPredictingDate) {
		return null;
	}
}
