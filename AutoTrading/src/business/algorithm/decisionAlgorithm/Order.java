package business.algorithm.decisionAlgorithm;

import java.util.Date;
import java.util.TreeMap;

import dataAccess.databaseManagement.entity.AssetEntity;

public class Order {

    public static final boolean ORDER_BUY = true;
    public static final boolean ORDER_SELL = false;
    private boolean orderType;
    private double price;
    private int nth_day_in_future;
    private Date date;

    public Order(boolean orderType, double price, int nth_day_in_future) {
        super();
        this.orderType = orderType;
        this.price = price;
        this.nth_day_in_future = nth_day_in_future;
    }

    public boolean isOrderType() {
        return orderType;
    }

    public void setOrderType(boolean orderType) {
        this.orderType = orderType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNth_day_in_future() {
        return nth_day_in_future;
    }

    public void setNth_day_in_future(int nth_day_in_future) {
        this.nth_day_in_future = nth_day_in_future;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
	public  business.virtualTrading.Order toOrder(
			AssetEntity assetEntity) {
//		TreeMap<Date, business.virtualTrading.Order> order = new TreeMap<Date, business.virtualTrading.Order>();
//		order.put(date, new business.virtualTrading.Order(assetEntity,
//				orderType, price, -1));
		return new business.virtualTrading.Order(assetEntity, orderType, price, -1);
	}
}
