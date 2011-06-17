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
	PortfolioEntity portfolioEntity;
	
	public Portfolio(long userID, long assetID, double price, double volume, Date date) {
		portfolioEntity = new PortfolioEntity(userID, assetID, price, volume, date);
	}
	
	
	/**
	 * Add portfolio to database
	 * <li> Note: this method update database
	 */
	public long add() {
		PortfolioManager portfolioManager = new PortfolioManager();
		portfolioManager.add(portfolioEntity);
		return portfolioEntity.getPortfolioID();
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
	
	public static void main(String args[]) {
		Date date = Date.valueOf("2001-01-02");
		
		Portfolio portfolioSample = new Portfolio(1, 2, 1.22, 11, date);
		PortfolioManager portfolioManager = new PortfolioManager();
		Date ta = portfolioManager.getLatestDate();
		System.out.println(ta.toString());
//		portfolioSample.add();
		
		ArrayList<PortfolioEntity> t = portfolioSample.getPortfolioByDate(date);
		
	}

}
