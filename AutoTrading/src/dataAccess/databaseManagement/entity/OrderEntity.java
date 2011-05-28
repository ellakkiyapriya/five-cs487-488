package dataAccess.databaseManagement.entity;

import java.io.Serializable;
import java.sql.Date;

public class OrderEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2692898077054296095L;

	private int orderID;
	private boolean orderType;
	private int userID;
	private Date date;
	private int assetID;
	private double price;
	private double volume;
	private boolean matched;
	
	public OrderEntity() {
		
	}
	
	public OrderEntity(	boolean orderType, int userID, Date date, int assetID, double price, double volume, boolean matched) {
		this.orderType = orderType;
		this.userID = userID;
		this.date = date;
		this.assetID = assetID;
		this.price = price;
		this.volume = volume;
		this.matched = matched;
	}
	
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public int getOrderID() {
		return orderID;
	}
	
	/**
	 * @param orderType: True means it's buy order, otherwise, it's sell order
	 */
	public void setOrderType(boolean orderType) {
		this.orderType = orderType;
	}
	
	
	/**
	 * @return: Type of order. True means it's buy order, otherwise, it's sell order
	 */
	public boolean getOrderType() {
		return orderType;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getUserID() {
		return userID;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDate() {
		return date;
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
	public void setMatched(boolean matched) {
		this.matched = matched;
	}
	public boolean isMatched() {
		return matched;
	}

	
}
