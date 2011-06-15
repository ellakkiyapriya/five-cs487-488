package Utility;

import java.util.ArrayList;

public abstract class ParamList {
	protected int numOfParam;
	protected ArrayList<Object> paramList;
	
	public ParamList()
	{
		paramList = new ArrayList<Object>();
	}
	
	public int getNumOfParam() {
		return numOfParam;
	}
	public void setNumOfParam(int numOfParam) {
		this.numOfParam = numOfParam;
	}
	public ArrayList<Object> getParamList() {
		return paramList;
	}
	public void setParamList(ArrayList<Object> paramList) {
		this.paramList = paramList;
	}
}
