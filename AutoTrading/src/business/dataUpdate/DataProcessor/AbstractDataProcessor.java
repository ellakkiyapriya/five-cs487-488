package business.dataUpdate.DataProcessor;

import java.io.BufferedReader;
import java.util.Date;

import dataAccess.databaseManagement.manager.AssetManager;
import dataAccess.databaseManagement.manager.PriceManager;

public abstract class AbstractDataProcessor {
	protected PriceManager priceManager = null;
	protected AssetManager assetManager = null;
	
	protected BufferedReader br;
	protected String exchangeName;
	protected Date date;
	
	public AbstractDataProcessor(BufferedReader br, Date date, String exchangeName)
	{
		priceManager = new PriceManager();
		assetManager = new AssetManager();
		
		this.br = br;
		this.date = date;
		this.exchangeName = exchangeName;
	}
	
	public BufferedReader getBr() {
		return br;
	}
	public void setBr(BufferedReader br) {
		this.br = br;
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
	
	public abstract boolean processData(); 
	/*
	 * return true if process data successfully
	 * return false if one or more exceptions are generated
	 * */
}
