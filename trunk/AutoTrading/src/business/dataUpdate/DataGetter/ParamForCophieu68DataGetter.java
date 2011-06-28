package business.dataUpdate.DataGetter;

import java.util.Date;

import Utility.ParamList;

public class ParamForCophieu68DataGetter extends ParamList {
	private Date date;
	private String exchangeName;
	public ParamForCophieu68DataGetter(Date date, String exchangeName)
	{
		super();
		this.date = date;
		this.exchangeName = exchangeName;
		numOfParam = 2;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getExchangeName() {
		return exchangeName;
	}
	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}
	
}
