package business.virtualTrading;

/* @Dinh : put getLatestDate() in PorfolioManager.java 
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
import dataAccess.databaseManagement.manager.AssetManager;
import dataAccess.databaseManagement.manager.OrderManager;

/**
 * Class Name: Order
 * 
 * @version 1.1
 * @date June 1, 2011
 * @author Xuan Ngoc
 */

/*
 * TODO : Assume that names of all symbols are unique
 */

public class Order {

	private String assetSymbol;
	private boolean orderType;
	private double price;
	private double volume;

	public Order() {

	}

	public Order(String assetSymbol, boolean orderType, double price,
			double volume) {
		this.setAssetSymbol(assetSymbol);
		this.setOrderType(orderType);
		this.setPrice(price);
		this.setVolume(volume);
	}

	/**
	 * Add order to database. <li>Note: this method update database
	 */
	public void add() {
		OrderManager orderManager = new OrderManager();
	}

	/**
	 * Get orders by Date from database.
	 * 
	 * @param date
	 * @return
	 */
	public ArrayList<Order> getOrderByDate(Date date) {
		OrderManager orderManager = new OrderManager();
		AssetManager assetmanager = new AssetManager();
		ArrayList<OrderEntity> orderEntityList = orderManager
				.getOrderByDate(date);
		ArrayList<Order> orderList = new ArrayList<Order>();
		OrderEntity currentOrder;
		String currentAssetSymbol;
		
		for (int i = 0; i < orderEntityList.size(); i++) {
			currentOrder = orderEntityList.get(i);
			currentAssetSymbol = assetmanager.getAssetByID(
					currentOrder.getAssetID()).getSymbol();
			Order order = new Order(currentAssetSymbol, currentOrder
					.getOrderType(), currentOrder.getPrice(), currentOrder
					.getVolume());
			orderList.add(order);
		}
		
		return orderList;
	}

	public void execute() {
		// orderEntity.execute();
	}

	public static void main(String args[]) {
		// AssetEntity asset = new AssetEntity("so xo", "SXS", 1, null, 0.05);
		// AssetManager assetManager = new AssetManager();
		// assetManager.add(asset);

		Date date = Date.valueOf("2001-01-05");
		// order.execute();

	}

	public void setAssetSymbol(String assetSymbol) {
		this.assetSymbol = assetSymbol;
	}

	public String getAssetSymbol() {
		return assetSymbol;
	}

	public void setOrderType(boolean orderType) {
		this.orderType = orderType;
	}

	public boolean isOrderType() {
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

}
