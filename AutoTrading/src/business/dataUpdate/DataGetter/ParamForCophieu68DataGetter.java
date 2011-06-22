package business.dataUpdate.DataGetter;

import java.util.Date;

import Utility.ParamList;

public class ParamForCophieu68DataGetter extends ParamList {
	private Date date;
	public ParamForCophieu68DataGetter(Date date)
	{
		super();
		this.date = date;
		numOfParam = 1;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
