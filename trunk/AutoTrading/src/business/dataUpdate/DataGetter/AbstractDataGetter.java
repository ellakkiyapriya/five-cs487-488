package business.dataUpdate.DataGetter;

import java.io.BufferedReader;
import java.util.Date;


public abstract class AbstractDataGetter {
	protected Date date;
	protected String exchangeName;
	
	
	public AbstractDataGetter(Date date, String exchangeName) {
		super();
		this.date = date;
		this.exchangeName = exchangeName;
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
	
	public abstract BufferedReader getData();
}
