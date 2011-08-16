package business.dataUpdate;

import java.util.ArrayList;
import java.util.Date;

public abstract class AbstractDataUpdate {
	protected String description;
	protected Date lastestDate;
	protected ArrayList<String> exchangeNameList;
	protected ArrayList<String> fileNameList;
	
	public AbstractDataUpdate()
	{
		
	}
	
	public AbstractDataUpdate(String description, Date lastestDate,
			ArrayList<String> exchangeNameList, ArrayList<String> fileNameList) {
		super();
		this.description = description;
		this.lastestDate = lastestDate;
		this.exchangeNameList = exchangeNameList;
		this.fileNameList = fileNameList;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getLastestDate() {
		return lastestDate;
	}
	public void setLastestDate(Date lastestDate) {
		this.lastestDate = lastestDate;
	}
	public ArrayList<String> getExchangeNameList() {
		return exchangeNameList;
	}
	public void setExchangeNameList(ArrayList<String> exchangeNameList) {
		this.exchangeNameList = exchangeNameList;
	}
	public ArrayList<String> getFileNameList() {
		return fileNameList;
	}
	public void setFileNameList(ArrayList<String> fileNameList) {
		this.fileNameList = fileNameList;
	}
	public abstract boolean updateHistoricalData();
	public abstract boolean updateData();
	public abstract boolean initExchangeMarketsAndAssets();
}
