package business.virtualTrading;

import java.sql.Date;
import java.sql.SQLException;

import java.util.ArrayList;

import dataAccess.databaseManagement.entity.PortfolioEntity;
import dataAccess.databaseManagement.manager.PortfolioManager;

/**
 * Class Name: Portfolio
 * @version 1.1 
 * @date  June 1, 2011
 * @author Xuan Ngoc
 */
public class Portfolio {
	private String assetSymbol;
	private double buyPrice;
	private double currentPrice;
	private double volume;
	private double profit;
	
	
	public Portfolio() {
		
	}
	
	public Portfolio(String assetSymbol, long buyPrice, double volume) {
		this.assetSymbol = assetSymbol;
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
