package dataAccess.databaseManagement;

import java.io.Serializable;

public class AssetEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1106350892914906725L;
	
	private int asset_id;
	private String name;
	private String symbol;
	private String exchange_name;
	private String asset_info;
	private double fluctuation_range;
	
	public AssetEntity() {
		
	}
	
	public AssetEntity(int asset_id, String name, String symbol, String exchange_name, String asset_info, double fluctuation_range) {
		this.asset_id = asset_id;
		this.name = name;
		this.symbol = symbol;
		this.exchange_name = exchange_name;
		this.asset_info = asset_info;
		this.fluctuation_range = fluctuation_range;
	}
	
	public void setAsset_id(int asset_id) {
		this.asset_id = asset_id;
	}
	public int getAsset_id() {
		return asset_id;
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
	public void setExchange_name(String exchange_name) {
		this.exchange_name = exchange_name;
	}
	public String getExchange_name() {
		return exchange_name;
	}
	public void setAsset_info(String asset_info) {
		this.asset_info = asset_info;
	}
	public String getAsset_info() {
		return asset_info;
	}
	public void setFluctuation_range(double fluctuation_range) {
		this.fluctuation_range = fluctuation_range;
	}
	public double getFluctuation_range() {
		return fluctuation_range;
	}
}
