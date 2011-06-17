package business.virtualTrading;

/* TODO @Dinh : put getLatestDate() in PorfolioManager.java 
 * 		and	  execute() in OrderEntity.java	
 * 
 public Date getLatestDate() {
		try {
			Date date = null;
			String queryString = "SELECT max(date) as date FROM portfolio";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			resultSet = ptmt.executeQuery();
			
			if (resultSet.next()) {
				date = resultSet.getDate("date"); 
			}
			
			return date;
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

/*
 public void execute() {
		
		// Update user's cash
		 
		UserManager userManager = new UserManager();
		UserEntity user = userManager.getUserByID(userID);
		if (this.orderType)
			user.setCash(user.getCash() - price*volume);
		else
			user.setCash(user.getCash() + price*volume);
		
		
		// Update user's portfolio 
		
		PortfolioManager portfolioManager = new PortfolioManager();
		PortfolioEntity portfolioTemp;
		Double priceTemp;
		Double volumeTemp;
		ArrayList<PortfolioEntity> portfolios = portfolioManager.getPortfolioByDate(portfolioManager.getLatestDate());
		for(int i = 0; i < portfolios.size(); i++) {
			portfolioTemp = portfolios.get(i);
			portfolioTemp.setDate(date);
			if (portfolioTemp.getAssetID() == assetID) {
				if (orderType) { // buy
					priceTemp = (portfolioTemp.getPrice()* portfolioTemp.getVolume() + price*volume )/ (portfolioTemp.getVolume()+volume);
					volumeTemp = portfolioTemp.getVolume() + volume;
					portfolioTemp.setPrice(priceTemp);
					portfolioTemp.setVolume(volumeTemp);
					portfolioManager.add(portfolioTemp);
				} else { // sell
					if(portfolioTemp.getVolume() > volume) {
						priceTemp = (portfolioTemp.getPrice()* portfolioTemp.getVolume() - price*volume )/ (portfolioTemp.getVolume()+volume);
						volumeTemp = portfolioTemp.getVolume() - volume;
						portfolioTemp.setPrice(priceTemp);
						portfolioTemp.setVolume(volumeTemp);
						portfolioManager.add(portfolioTemp);
					}
				}
			} else
				portfolioManager.add(portfolioTemp);
		}
	}
  
 */

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
	
	public void execute(){
		orderEntity.execute();
	}
	
	public static void main(String args[]) {
//		AssetEntity asset = new AssetEntity("so xo", "SXS", 1, null, 0.05);
//		AssetManager assetManager = new AssetManager();
//		assetManager.add(asset);

		Date date = Date.valueOf("2001-01-05");
		Order order = new Order(true, 1, date,	1, 1.77, 22, true);
		order.add();
//		order.execute();
		
	}
	

}
