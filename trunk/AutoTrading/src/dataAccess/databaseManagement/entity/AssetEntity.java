package dataAccess.databaseManagement.entity;

import java.io.Serializable;

public class AssetEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1106350892914906725L;
	
	private int assetID;
	private String name;
	private String symbol;
	private int exchangeID;
	private String assetInfo;
	private double fluctuationRange;
	
	public AssetEntity() {
		
	}
	
	public AssetEntity(String name, String symbol, int exchangeID, String assetInfo, double fluctuationRange) {
		this.name = name;
		this.symbol = symbol;
		this.exchangeID = exchangeID;
		this.assetInfo = assetInfo;
		this.fluctuationRange = fluctuationRange;
	}
	
	public void setAssetID(int assetID) {
		this.assetID = assetID;
	}
	public int getAssetID() {
		return assetID;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setExchangeID(int exchangeID) {
		this.exchangeID = exchangeID;
	}
	public int getExchangeID() {
		return exchangeID;
	}
	public void setAssetInfo(String assetInfo) {
		this.assetInfo = assetInfo;
	}
	public String getAssetInfo() {
		return assetInfo;
	}
	public void setFluctuationRange(double fluctuationRange) {
		this.fluctuationRange = fluctuationRange;
	}
	public double getFluctuationRange() {
		return fluctuationRange;
	}
}
