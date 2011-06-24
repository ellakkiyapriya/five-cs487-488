package business.virtualTrading;

/* TODO add to PriceManager.java
 * 
 public Date getLatestDate() {
        Date latestDate = null;

        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT max(date) FROM price");
            resultSet.next();
            latestDate = resultSet.getDate(1);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return latestDate;
    }
 */



import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.PortfolioEntity;
import dataAccess.databaseManagement.manager.PortfolioManager;

/**
 * Class Name: Portfolio
 * @version 1.1 
 * @date  June 1, 2011
 * @author Xuan Ngoc
 */
public class Portfolio {
	
	private Asset asset;
	private double buyPrice;
	private double currentPrice;
	private double volume;
	private double profit;
	
	
	public Portfolio() {
		
	}
	
	public Portfolio(Asset asset, long buyPrice, double volume) {
		this.asset = new Asset(asset);
		this.buyPrice = buyPrice;
		this.volume = volume;
		this.profit = 0;
		
//		this.getLatestPrice(		
	}
	
	public Portfolio(AssetEntity asset, long buyPrice, double volume) {
		this.asset = new Asset(asset);
		this.buyPrice = buyPrice;
		this.volume = volume;
		this.profit = 0;
		
//		this.getLatestPrice(		
	}
	
	
	/**
	 * Add portfolio to database
	 * <li> Note: this method update database
	 */
	public void add() {
		PortfolioManager portfolioManager = new PortfolioManager();
		
	}
	
	/**
	 * Delete portfolio by ID from database 
	 * <li> Note: this method update database
	 */
	public void delete(long portfolioID) {
		PortfolioManager portfolioManager = new PortfolioManager();
		portfolioManager.delete(portfolioID);
	}
	
	/**
	 * Get portfolios by date from database 
	 */
	public ArrayList<PortfolioEntity> getPortfolioByDate(Date date) {
		PortfolioManager portfolioManager = new PortfolioManager();
		return portfolioManager.getPortfolioByDate(date);
	}
	
	
	public static double portfolioProfit() {
		return 0;
	}
	
	
	public static void main(String args[]) {
		Date date = Date.valueOf("2001-01-02");
		
//		Portfolio portfolioSample = new Portfolio(1, 2, 1.22, 11, date);
		PortfolioManager portfolioManager = new PortfolioManager();
		Date ta = portfolioManager.getLatestDate();
		System.out.println(ta.toString());
//		portfolioSample.add();
		
//		ArrayList<PortfolioEntity> t = portfolioSample.getPortfolioByDate(date);
		
	}

}
