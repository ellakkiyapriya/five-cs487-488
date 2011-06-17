package business.virtualTrading;

/*
 *public ArrayList<OrderEntity> getOrderByDate(Date date) {
		try {
			ArrayList<OrderEntity> listAllOrders = new ArrayList<OrderEntity>();
			
			String queryString = "SELECT * FROM order";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setDate(1, date);
			resultSet = ptmt.executeQuery();
			
			while (resultSet.next()) {
				OrderEntity orderEntity = new OrderEntity();

				orderEntity.setOrderID(resultSet.getLong("order_id"));
				orderEntity.setOrderType(resultSet.getBoolean("order_type"));
				orderEntity.setUserID(resultSet.getLong("user_id"));
				orderEntity.setDate(date);
				orderEntity.setAssetID(resultSet.getLong("asset_id"));
				orderEntity.setPrice(resultSet.getDouble("price"));
				orderEntity.setVolume(resultSet.getDouble("volume"));
				orderEntity.setMatched(resultSet.getBoolean("matched"));
				
				listAllOrders.add(orderEntity);
			}
			
			return listAllOrders;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return null;
	}
 */

import java.sql.Date;
import java.sql.SQLException;
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
