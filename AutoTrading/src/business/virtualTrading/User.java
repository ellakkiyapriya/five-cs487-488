package business.virtualTrading;


import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.OrderEntity;
import dataAccess.databaseManagement.entity.PortfolioEntity;
import dataAccess.databaseManagement.entity.UserEntity;
import dataAccess.databaseManagement.manager.AssetManager;
import dataAccess.databaseManagement.manager.OrderManager;
import dataAccess.databaseManagement.manager.PortfolioManager;
import dataAccess.databaseManagement.manager.UserManager;

/**
 * Class Name: User
 * 
 * @version 1.5
 * @date June 1, 2011
 * @author Xuan Ngoc
 */

public class User {
	UserEntity user;
	double profit;
	ArrayList<Order> curOrderList;
	ArrayList<PortfolioEntry> curPortfolioList;

	/**
	 * Constructor <li>userID is automatically created
	 */
	public User(String name, double cash) {
		user = new UserEntity(name, cash);
		curOrderList = new ArrayList<Order>();
		curPortfolioList = new ArrayList<PortfolioEntry>();
	}
	
	/**
	 * Constructor for user get from database <li> userID is automatically created
	 */
	public User(UserEntity userEntity,  ArrayList<PortfolioEntity> portfolioEntityList) {
		user = userEntity;
		curOrderList = new ArrayList<Order>();
		curPortfolioList = new ArrayList<PortfolioEntry>();
		for (int i = 0; i < portfolioEntityList.size(); i++) {
			curPortfolioList.add( new PortfolioEntry(portfolioEntityList.get(i)));
		}
		
	}
	
	/**
	 * Constructor <li>userID is automatically created
	 */
	public User(UserEntity userEntity) {
		user = userEntity;
		curOrderList = new ArrayList<Order>();
		curPortfolioList = new ArrayList<PortfolioEntry>();
	}

	/**
	 * Constructor get user's information from database by userID
	 */
	public User(long userID) {
		UserManager userManager = new UserManager();
		user = userManager.getUserByID(userID);
	}


	/**
	 * Add cash to a user 
	 */
	public void addCash(double cash) {
		user.setCash(user.getCash() + cash);
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
				.getOrderByDateAndUserID(user.getUserID(), date);
		ArrayList<Order> orderList = new ArrayList<Order>();
		Asset currentAsset;

		for (OrderEntity currentOrder : orderEntityList) {
			currentAsset = new Asset(assetmanager.getAssetByID(currentOrder
					.getAssetID()));
			orderList.add(new Order(currentAsset, currentOrder.getOrderType(),
					currentOrder.getPrice(), currentOrder.getVolume()));
		}
		return orderList;
	}
	
	
	/**
	 * Get portfolioList by date from database
	 */
	public ArrayList<PortfolioEntry> getPortfolioByDate(Date date) {
		ArrayList<PortfolioEntry> portfolioList = new ArrayList<PortfolioEntry>();
		PortfolioManager portfolioManager = new PortfolioManager();
		AssetManager assetManager = new AssetManager();
		ArrayList<PortfolioEntity> portfolioEntityList = portfolioManager
				. getPortfolioByDateAndUserID(user.getUserID(), date);
		PortfolioEntry curPortfolioEntry;
		AssetEntity assetEntity;

		for (PortfolioEntity portfolioEntity : portfolioEntityList) {
			assetEntity = assetManager.getAssetByID(portfolioEntity.getAssetID());
			curPortfolioEntry = new PortfolioEntry(new Asset(assetEntity), portfolioEntity.getPrice(), portfolioEntity.getVolume());
			portfolioList.add(curPortfolioEntry);
		}
		
		return portfolioList;
	}
	
	
	public void addPortfolioToDatabase() {
		PortfolioManager portfolioManager = new PortfolioManager();

		for (PortfolioEntry curPortfolioEntry : curPortfolioList) {
			portfolioManager.add(new PortfolioEntity(user.getUserID(),
					curPortfolioEntry.getAsset().getAssetID(), curPortfolioEntry
							.getBuyPrice(), curPortfolioEntry.getVolume(),
					new java.sql.Date(new java.util.Date().getTime())));
		}
	}


	/**
	 * Add orders to database and update portfolio in database <li>Note: this
	 * method updates database
	 */
	public void addOrderToDatabase() {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = Date.valueOf(dateFormat.format(cal.getTime()));

		OrderManager orderManager = new OrderManager();
		for (Order order : curOrderList) {
			orderManager.add(new OrderEntity(order.getOrderType(), user
					.getUserID(), date, order.getAsset().getAssetID(), order
					.getPrice(), order.getVolume(), true));
		}

		this.executeOrder();

		/*
		 * Update portfolio
		 */
		PortfolioManager portfolioManager = new PortfolioManager();
		Date latestDate = portfolioManager.getPortfolioLatestDateOfUserID(user.getUserID()); 

		if (latestDate.equals(date)) { // remove today's porfolio in database  
			ArrayList<PortfolioEntity> portfolioEntityList = portfolioManager
					.getPortfolioByDateAndUserID(user.getUserID(), portfolioManager.getLatestDate());
			for (int i = 0; i < portfolioEntityList.size(); i++) {
				portfolioManager.delete(portfolioEntityList.get(i)
						.getPortfolioID());
			}
		}
		
		for (PortfolioEntry curPortfolioEntry : curPortfolioList) { // add today's portfolio entries
			portfolioManager
					.add(new PortfolioEntity(user.getUserID(), curPortfolioEntry
							.getAsset().getAssetID(), curPortfolioEntry
							.getBuyPrice(), curPortfolioEntry.getVolume(), date));
		}

	}
	

	/**
	 * excute orders and update portfolio
	 */
	public void executeOrder() {
		for (Order curOrder : curOrderList) {
			
			/* Algorithm :
			 * 
			 * if (buyOrderType) then update cash & validVolume
			 * if (asset of order is not in portfolio) and (buyOrderType) then add Order to Portfolio
			 * if (portfolio has asset in order) then
			 * 		if (buyOrderType) then updatePortfolio(price, volume) 
			 * 		if (sellOrderType) then 
			 * 			if (portfolio.volume < order.volume)
			 * 				updateOrder  
			 * 				removePortfolioEntry
			 * 			updateCash 
			 */
			
			if (!curOrder.getMatched()) {
				// Update user's cash
				if (curOrder.getOrderType()) { // buyOrder Type
					double validCash = user.getCash() - curOrder.getValue();
					if (validCash >= 0)
						user.setCash(validCash);
					else {
						curOrder.setVolume(user.getCash() / curOrder.getPrice());
						user.setCash(0);
					}
				}

				// Update user's portfolio
				int i = 0;
				PortfolioEntry curPortfolioEntry = null;
				for (i = 0; i < curPortfolioList.size(); i++) {
					curPortfolioEntry = curPortfolioList.get(i);
					if (curPortfolioEntry.getAsset().getAssetID() == curOrder
							.getAsset().getAssetID())
					break;
				}
				
				// Portfolio does not have asset in the Order
				if ((i >= curPortfolioList.size())
						&& (curOrder.getOrderType())) { // buyOrder Type
					curPortfolioList.add(new PortfolioEntry(curOrder
							.getAsset(), curOrder.getPrice(), curOrder.getVolume()));
					curOrder.setMatched(true);
				}
				
				// Portfolio has the same asset in the Order
				if (i < curPortfolioList.size()) {
					if (curOrder.getOrderType()) { // buyOrder Type
						curPortfolioEntry.updatePortfolio(curOrder.getVolume(), curOrder.getPrice());
					} else { // sellOrder Type
						if (curPortfolioEntry.updatePortfolio(- curOrder.getVolume(), curOrder.getPrice()) > 0) {
							curOrder.setVolume(curPortfolioEntry.getVolume());
							curPortfolioList.remove(curPortfolioEntry);
						}
						user.setCash(user.getCash() + curOrder.getValue());
					}
					curOrder.setMatched(true);
				}
			}
		}
	}
	
	public void executeAlgorithmOrder() {
		if (curPortfolioList.get(0).getVolume() == -1) {
			for (Order curOrder : curOrderList){
				// Update Cash
				if (curOrder.getOrderType()) { // buy all
					curOrder.setVolume(user.getCash()/curOrder.getPrice());
					user.setCash(0);
				} 
				
				
				// Update user's portfolio
				int i = 0;
				PortfolioEntry curPortfolioEntry = null;
				for (i = 0; i < curPortfolioList.size(); i++) {
					curPortfolioEntry = curPortfolioList.get(i);
					if (curPortfolioEntry.getAsset().getAssetID() == curOrder
							.getAsset().getAssetID())
					break;
				}
				
				// Portfolio does not have asset in the Order
				if ((i >= curPortfolioList.size())
						&& (curOrder.getOrderType())) { // buyOrder Type
					curPortfolioList.add(new PortfolioEntry(curOrder
							.getAsset(), curOrder.getPrice(), curOrder.getVolume()));
					curOrder.setMatched(true);
				}
				
				// Portfolio has the same asset in the Order
				if (i < curPortfolioList.size()) {
					if (curOrder.getOrderType()) { // buyOrder Type
						curPortfolioEntry.updatePortfolio(curOrder.getVolume(), curOrder.getPrice());
					} else { // sellOrder Type
						if (curPortfolioEntry.updatePortfolio(- curOrder.getVolume(), curOrder.getPrice()) > 0) {
							curOrder.setVolume(curPortfolioEntry.getVolume());
							curPortfolioList.remove(curPortfolioEntry);
						}
						user.setCash(user.getCash() + curOrder.getValue());
					}
					curOrder.setMatched(true);
				}
				
			}
		} else
			executeOrder();
	}
	
	
	public double portfolioValue(Date date) {
		double totalCash = 0;
		PortfolioManager portfolioManager = new PortfolioManager();
		
		ArrayList<PortfolioEntity> portfolioEntityList = portfolioManager.getPortfolioByDateAndUserID(user.getUserID(), date); 
		for( PortfolioEntity portfolioEntity : portfolioEntityList) {
			totalCash += portfolioEntity.getPrice() * portfolioEntity.getVolume();  
		}
		return totalCash;
	}
	

	/**
	 * @return
	 * cash that used in trading until present
	 */
	public double spentCash() {
		OrderManager orderManager = new OrderManager();
		ArrayList<OrderEntity> orderEntityList = orderManager.getOrderByUserID(user.getUserID());
		double spentCash = 0;
		for (OrderEntity orderEntity : orderEntityList) {
			if (orderEntity.getOrderType()) // buy
				spentCash -= orderEntity.getPrice() * orderEntity.getVolume();
			else
				spentCash += orderEntity.getPrice() * orderEntity.getVolume();
		}
		return spentCash;
	}
	
	
	/**
	 * @param date
	 * @return
	 * cash that used in trading until a specific date
	 */
	public double spentCash(Date date) {
		OrderManager orderManager = new OrderManager();
		ArrayList<OrderEntity> orderEntityList = orderManager.getOrderUntilDateOfUserID(user.getUserID(), date);
		double spentCash = 0;
		for (OrderEntity orderEntity : orderEntityList) {
			if (orderEntity.getOrderType()) // buyOrder Type
				spentCash -= orderEntity.getPrice() * orderEntity.getVolume();
			else
				spentCash += orderEntity.getPrice() * orderEntity.getVolume();
		}
		return spentCash;
	}
	
	
	public double getCashByDate(Date date) {
		return user.getCash() + this.spentCash(date);
	}
	
	
	public double initialCash() {
		return user.getCash() + this.spentCash();
	}
	
	/**
	 * @param date
	 * @return
	 * total capital = cash + porfolio value
	 */
	public double getCapitalByDate (Date date) {
		return getCashByDate(date) + portfolioValue(date);
	}
	
	/**
	 * @return
	 */
	public double profit() { 
		PortfolioManager portfolioManager = new PortfolioManager();
		double initialCapital = getCapitalByDate(portfolioManager.getPortfolioStartDateOfUserID(user.getUserID()));
		double curCapital = getCapitalByDate(portfolioManager.getPortfolioLatestDateOfUserID(user.getUserID()));
		return (curCapital - initialCapital) / initialCapital;		
	}

        public void removeFromDatabase() {
            (new UserManager()).delete(user.getUserID());
        }
	
	public void setName(String name) {
		this.user.setName(name);
	}
	public long getUserID() {
		return user.getUserID();
	}

	/**
	 * Add a user to database. <li>Note: this method update database
	 */
	public long add() {
		UserManager userManager = new UserManager();
		userManager.add(this.user);
		return this.user.getUserID();
	}

	public void addOrder(Order order) {
		curOrderList.add(order);
	}

	public void removeOrder(Order order) {
		curOrderList.remove(order);
	}

	public void addPortfolio(PortfolioEntry portfolio) {
		curPortfolioList.add(portfolio);
	}

	public void removePortfolio(PortfolioEntry portfolio) {
		curPortfolioList.remove(portfolio);
	}

	public double getCash() {
		return user.getCash(); 
	}

	@Override
	public String toString() {
		return user.getName();
	}

	public ArrayList<Order> getCurOrderList() {
		return curOrderList;
	}
	
	public ArrayList<PortfolioEntry> getCurPortfolioList() {
		return curPortfolioList;
	}

}
