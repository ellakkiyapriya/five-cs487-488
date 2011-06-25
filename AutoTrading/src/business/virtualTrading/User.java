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
import dataAccess.databaseManagement.manager.PriceManager;
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
	ArrayList<Order> currentOderList;
	ArrayList<Portfolio> currentPortfolioList;

	/**
	 * Constructor <li>userID is automatically created
	 */
	public User(String name, double cash) {
		user = new UserEntity(name, cash);
		currentOderList = new ArrayList<Order>();
		currentPortfolioList = new ArrayList<Portfolio>();
	}

	/**
	 * Constructor get user's information from database by userID
	 */
	public User(long userID) {
		UserManager userManager = new UserManager();
		user = userManager.getUserByID(userID);
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
		currentOderList.add(order);
	}

	public void removeOrder(Order order) {
		currentOderList.remove(order);
	}

	public void addPortfolio(Portfolio portfolio) {
		currentPortfolioList.add(portfolio);
	}

	public void removePortfolio(Portfolio portfolio) {
		currentPortfolioList.remove(portfolio);
	}

	/**
	 * Add cash to a user <li>Note: this method update database
	 */
	public void addCash(double cash) {
		UserManager userManager = new UserManager();
		user.setCash(user.getCash() + cash);
		userManager.update(user);
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
		Asset currentAsset;

		for (int i = 0; i < orderEntityList.size(); i++) {
			currentOrder = orderEntityList.get(i);
			currentAsset = new Asset(assetmanager.getAssetByID(currentOrder
					.getAssetID()));
			Order order = new Order(currentAsset, currentOrder.getOrderType(),
					currentOrder.getPrice(), currentOrder.getVolume());
			orderList.add(order);
		}

		return orderList;
	}
	
	
	/**
	 * Get portfolios by date from database
	 */
	public ArrayList<Portfolio> getPortfolioByDate(Date date) {
		ArrayList<Portfolio> portfolioList = new ArrayList<Portfolio>();
		PortfolioManager portfolioManager = new PortfolioManager();
		AssetManager assetManager = new AssetManager();
		PriceManager priceManager = new PriceManager();
		ArrayList<PortfolioEntity> portfolioEntityList = portfolioManager
				.getPortfolioByDate(date); // TODO getPortfolioByDateAndUserID
		PortfolioEntity portfolioEntity;
		Portfolio currentPortfolio;
		AssetEntity assetEntity;
		

		for (int i = 0; i < portfolioEntityList.size(); i++) {
			
			portfolioEntity = portfolioEntityList.get(i);
			assetEntity = assetManager.getAssetByID(portfolioEntity.getAssetID());
			currentPortfolio = new Portfolio(new Asset(assetEntity), portfolioEntity.getPrice(), portfolioEntity.getVolume());
			double initialPrice = priceManager.getPriceByAssetIDAndDate(user.getUserID(), date).getClose(); // TODO change date to getOldestDate()
			currentPortfolio.setProfit( currentPortfolio.getCurrentPrice()/ initialPrice);

			/*
			 * TODO : ArrayList<OrderEntity> getOrderByUserID(long userID) {}
			 * 
			 * execute all oders -->initial cash --> profit =
			 */

		}

		return null;
	}


	/**
	 * Add orders to database and update portfolio in database <li>Note: this
	 * method updates database
	 */

	public void addOrdertoDatabase() {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = Date.valueOf(dateFormat.format(cal.getTime()));

		OrderManager orderManager = new OrderManager();
		for (int i = 0; i < currentOderList.size(); i++) {
			Order order = currentOderList.get(i);
			orderManager.add(new OrderEntity(order.getOrderType(), user
					.getUserID(), date, order.getAsset().getAssetID(), order
					.getPrice(), order.getVolume(), true));
		}

		this.execute();

		/*
		 * Update portfolio
		 */
		PortfolioManager portfolioManager = new PortfolioManager();
		Date latestDate = portfolioManager.getLatestDate();
		Portfolio currentPortfolio;

		if (latestDate.equals(date)) { // remove today's porfolio in database  
			ArrayList<PortfolioEntity> portfolioEntityList = portfolioManager
					.getPortfolioByDate(latestDate);
			for (int i = 0; i < portfolioEntityList.size(); i++) {
				portfolioManager.delete(portfolioEntityList.get(i)
						.getPortfolioID());
			}
		}
		
		for (int i = 0; i < currentPortfolioList.size(); i++) { // add today's portfolio entries
			currentPortfolio = currentPortfolioList.get(i);
			portfolioManager
					.add(new PortfolioEntity(user.getUserID(), currentPortfolio
							.getAsset().getAssetID(), currentPortfolio
							.getBuyPrice(), currentPortfolio.getVolume(), date));
		}

	}
	

	/**
	 * excute orders and update portfolio
	 */

	public void execute() {
		for (int j = 0; j < currentOderList.size(); j++) {

			Order currentOrder = currentOderList.get(j);
			double validVolume = currentOrder.getVolume();
			if (!currentOrder.getMatched()) {

				// Update user's cash
				if (currentOrder.getOrderType()) { // buy
					double validCash = user.getCash() - currentOrder.getPrice()
							* currentOrder.getVolume();
					if (validCash > 0)
						user.setCash(validCash);
					else {
						validVolume = user.getCash() / currentOrder.getPrice();
						user.setCash(0);
					}
				}

				double buyPrice;
				double buyVolumn;
				// Update user's portfolio
				int i = 0;
				
				Portfolio currentPortfolio = null;
				for (i = 0; i < currentPortfolioList.size(); i++) {
					currentPortfolio = currentPortfolioList.get(i);
					if (currentPortfolio.getAsset().getAssetID() == currentOrder
							.getAsset().getAssetID())
					break;
				}
				
				if ((i >= currentPortfolioList.size())
						&& (currentOrder.getOrderType())) // buy
					currentPortfolioList.add(new Portfolio(currentOrder
							.getAsset(), currentOrder.getPrice(), validVolume));
				if (i < currentPortfolioList.size()) {
					if (currentOrder.getOrderType()) { // buy
						buyVolumn = currentPortfolio.getVolume() + validVolume;
						buyPrice = (currentPortfolio.getVolume()
								* currentPortfolio.getBuyPrice() + validVolume
								* currentOrder.getPrice())
								/ buyVolumn;
						currentPortfolio.setVolume(buyVolumn);
						currentPortfolio.setBuyPrice(buyPrice);
					} else { // sell
						if (currentPortfolio.getVolume() > currentOrder
								.getVolume()) {
							buyVolumn = currentPortfolio.getVolume()
									- validVolume;
							buyPrice = (currentPortfolio.getVolume()
									* currentPortfolio.getBuyPrice() - validVolume
									* currentOrder.getPrice())
									/ buyVolumn;
						} else { // sell all of an asset
							validVolume = currentPortfolio.getVolume();
							currentPortfolioList.remove(currentPortfolio);
						}
						user.setCash(user.getCash() + currentOrder.getPrice()
								* validVolume);
					}
				}
			}
		}
	}
	
	public double portfolioValue(Date date) {
		double totalCash = 0;
		PortfolioManager portfolioManager = new PortfolioManager();
		PortfolioEntity portfolioEntity;
		ArrayList<PortfolioEntity> portfolioEntityList =  portfolioManager.getPortfolioByDate(date); // TODO getPortfolioByDateAndUserID()  
		for(int i = 0; i < portfolioEntityList.size(); i++) {
			portfolioEntity = portfolioEntityList.get(i);
			totalCash += portfolioEntity.getPrice() * portfolioEntity.getVolume();  
		}
		return totalCash;
	}

	
	public double spentCash() {
		OrderManager orderManager = new OrderManager();
		ArrayList<OrderEntity> orderEntityList = orderManager.getOrderByUserID(user.getUserID());
		double spentCash = 0;
		OrderEntity orderEntity;
		for (int i = 0; i < orderEntityList.size(); i++) {
			orderEntity = orderEntityList.get(i);
			if (orderEntity.getOrderType()) // buy
				spentCash -= orderEntity.getPrice() * orderEntity.getVolume();
			else
				spentCash += orderEntity.getPrice() * orderEntity.getVolume();
		}
		return spentCash;
	}
	
	
	public double spentCash(Date date) {
		OrderManager orderManager = new OrderManager();
//		ArrayList<OrderEntity> orderEntityList = orderManager.getOrderByDateAndUserID(user.getUserID(), Date date);
		ArrayList<OrderEntity> orderEntityList = orderManager.getOrderByUserID(user.getUserID());
		double spentCash = 0;
		OrderEntity orderEntity;
		for (int i = 0; i < orderEntityList.size(); i++) {
			orderEntity = orderEntityList.get(i);
			if (orderEntity.getOrderType()) // buy
				spentCash -= orderEntity.getPrice() * orderEntity.getVolume();
			else
				spentCash += orderEntity.getPrice() * orderEntity.getVolume();
		}
		return spentCash;
	}
	
	
	public double getCashByDate(Date date) {
		return user.getCash() - this.spentCash(date);
	}
	
	public double initialCash() {
		return user.getCash() - this.spentCash();
	}
	
	
	public double getCapitalByDate (Date date) {
		return getCashByDate(date) + portfolioValue(date);
	}
	
	public double profit() {
		double initialCash = initialCash();
		return (user.getCash() - initialCash) / initialCash;
	}
	



	public static void main(String args[]) {

		User uM = new User("u1.3", 1222);
		uM.add();

		System.out.println(uM.getUserID());
	}

	@Override
	public String toString() {
		return user.getName();
	}

}
