package dataAccess.databaseManagement;

import java.io.Serializable;
import java.sql.Date;

public class PriceEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5374209810328051221L;
	
	private int asset_id;
	private Date date;
	private Date delivery_date;
	private double volume;
	private double close;
	private double open;
	private double high;
	private double low;
	
	public PriceEntity() {
	}

	public PriceEntity(int asset_id, Date date, Date delivery_date, double volume, double close, double open, double high, double low) {
		this.asset_id = asset_id;
		this.date = date;
		this.delivery_date = delivery_date;
		this.volume = volume;
		this.close = close;
		this.open = open;
		this.high = high;
		this.low = low;
	}
	
	public void setAsset_id(int asset_id) {
		this.asset_id = asset_id;
	}
	public int getAsset_id() {
		return asset_id;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDate() {
		return date;
	}
	public void setDelivery_date(Date delivery_date) {
		this.delivery_date = delivery_date;
	}
	public Date getDelivery_date() {
		return delivery_date;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public double getVolume() {
		return volume;
	}
	public void setClose(double close) {
		this.close = close;
	}
	public double getClose() {
		return close;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	public double getOpen() {
		return open;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public double getHigh() {
		return high;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public double getLow() {
		return low;
	}
}
