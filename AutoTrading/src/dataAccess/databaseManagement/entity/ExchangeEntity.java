package dataAccess.databaseManagement.entity;

import java.io.Serializable;

public class ExchangeEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5854103809456038716L;

	private String name;
	private double fluctuation_range;

	public ExchangeEntity() {
	}
	
	public ExchangeEntity(String name, double fluctuation_range) {
		this.name = name;
		this.fluctuation_range = fluctuation_range;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setFluctuation_range(double fluctuation_range) {
		this.fluctuation_range = fluctuation_range;
	}

	public double getFluctuation_range() {
		return fluctuation_range;
	}
}
