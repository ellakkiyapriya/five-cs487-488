package dataAccess.databaseManagement.entity;

import java.io.Serializable;

public class ExchangeEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5854103809456038716L;

	private int exchangeID;
	private String name;
	private double fluctuationRange;

	public ExchangeEntity() {
	}
	
	public ExchangeEntity(String name, double fluctuationRange) {
		this.name = name;
		this.fluctuationRange = fluctuationRange;
	}

	public void setExchangeID(int exchangeID) {
		this.exchangeID = exchangeID;
	}

	public int getExchangeID() {
		return exchangeID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setFluctuationRange(double fluctuationRange) {
		this.fluctuationRange = fluctuationRange;
	}

	public double getFluctuationRange() {
		return fluctuationRange;
	}
}
