package business.virtualTrading;



import dataAccess.databaseManagement.entity.AssetEntity;

/**
 * Class Name: Order
 * 
 * @version 1.5
 * @date June 1, 2011
 * @author Xuan Ngoc
 */

/*
 * TODO : Assume that names of all symbols are unique
 */

public class Order {

	private Asset asset;
	private boolean orderType;
	private double price;
	private double volume;
	private double value;
	private boolean matched;

	public Order() {

	}

	public Order(Asset asset, boolean orderType, double price, double volume) {
		this.setAsset(asset);
		this.setOrderType(orderType);
		this.setPrice(price);
		this.setVolume(volume);
		this.setValue(price * volume);
		this.setMatched(false);
	}
	
	public Order(AssetEntity asset, boolean orderType, double price, double volume) {
		this.asset = new Asset(asset);
		this.setOrderType(orderType);
		this.setPrice(price);
		this.setVolume(volume);
		this.setValue(price * volume);
		this.setMatched(false);
	}

	public void setOrderType(boolean orderType) {
		this.orderType = orderType;
	}

	public boolean isOrderType() {
		return orderType;
	}
	
	public boolean getOrderType() {
		return orderType;
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

	public void setValue(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	public void setAsset(Asset asset) {
		this.asset = new Asset(asset);
	}

	public Asset getAsset() {
		return asset;
	}

	public void setMatched(boolean matched) {
		this.matched = matched;
	}

	public boolean getMatched() {
		return matched;
	}
	
}
