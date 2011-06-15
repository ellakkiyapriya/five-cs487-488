package business.dataUpdate.DataProcessor;

import java.io.BufferedReader;
import java.util.Date;

import Utility.ParamList;

public class ParamForCophieu68DataProcessor extends ParamList {
	public ParamForCophieu68DataProcessor(BufferedReader br, Date date) {
		super();
		paramList.add(br);
		paramList.add(date);
		numOfParam = 2;
		// TODO Auto-generated constructor stub
	}
}
