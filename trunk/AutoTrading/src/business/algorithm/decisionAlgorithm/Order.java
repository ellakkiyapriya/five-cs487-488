package business.algorithm.decisionAlgorithm;

import java.util.Date;

public class Order {

    public static final boolean ORDER_BUY = true;
    public static final boolean ORDER_SELL = false;
    private boolean orderType;
    private double price;
    private Date date;
    private int volume;

    public Order(boolean orderType, double price, Date date, int volume) {
        super();
        this.orderType = orderType;
        this.price = price;
        this.date = date;
        this.volume = volume;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}
    
    /*
	public  business.virtualTrading.Order toOrder(
			AssetEntity assetEntity) {
//		TreeMap<Date, business.virtualTrading.Order> order = new TreeMap<Date, business.virtualTrading.Order>();
//		order.put(date, new business.virtualTrading.Order(assetEntity,
//				orderType, price, -1));
		return new business.virtualTrading.Order(assetEntity, orderType, price, business.virtualTrading.Order.USE_ALL_CASH);
	}*/
}
