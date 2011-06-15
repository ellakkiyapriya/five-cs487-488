package business.virtualTrading;

import java.sql.Date;
import java.util.ArrayList;

import dataAccess.databaseManagement.entity.OrderEntity;
import dataAccess.databaseManagement.manager.OrderManager;

/**
 * Class Name: Order
 * @version 1.1 
 * @date  June 1, 2011
 * @author Xuan Ngoc
 */
public class Order {
	OrderEntity orderEntity;
	
	public Order (boolean orderType, long userID, Date date, long assetID, double price, double volume, boolean matched) {
		orderEntity = new OrderEntity(orderType, userID, date, assetID, price, volume, matched);
	}
	
	/**
	 * Add order to database.
	 * <li> Note: this method update database
	 */
	public void add(){
		OrderManager orderManager = new OrderManager();
		orderManager.add(orderEntity);
	}
	
	/**
	 * Delete order to database.
	 * <li> Note: this method update database
	 */
	public void delete(long orderID) {
		OrderManager orderManager = new OrderManager();
		orderManager.delete(orderID);
	}
	
	/**
	 * Get orders by Date from database.
	 * @param date
	 * @return
	 */
	public ArrayList<OrderEntity> getOrderByDate(Date date) {
		OrderManager orderManager = new OrderManager();
		return orderManager.getOrderByDate(date);
	}
	
	

}
