package business.dataUpdate.DataProcessor;

import java.util.Date;

import Utility.ParamList;

public abstract class AbstractDataProcessor {
	public abstract boolean processData(ParamList parameters); 
	/*
	 * return true if process data successfully
	 * return false if one or more exceptions are generated
	 * */
}
