package dataAccess.databaseManagement.entity;

import java.io.Serializable;
import java.sql.Date;

public class PortfolioEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7320439639218180861L;

	private int portfolioID;
	private int userID;
	private int assetID;
	private double price;
	private double volume;
	private Date date;
	
	public PortfolioEntity() {
	}

	public PortfolioEntity(int userID, int assetID, double price, double volume, Date date) {
		this.userID = userID;
		this.assetID = assetID;
		this.price = price;
		this.volume = volume;
		this.date = date;
	}
	
	public void setPortfolioID(int portfolioID) {
		this.portfolioID = portfolioID;
	}
	public int getPortfolioID() {
		return portfolioID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getUserID() {
		return userID;
	}
	public void setAssetID(int assetID) {
		this.assetID = assetID;
	}
	public int getAssetID() {
		return assetID;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPrice() {
		return price;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public double getVolume() {
		return volume;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDate() {
		return date;
	}
	
	
}
