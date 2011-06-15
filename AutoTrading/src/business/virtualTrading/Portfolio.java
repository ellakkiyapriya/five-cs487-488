package business.virtualTrading;

import java.sql.Date;
import java.sql.SQLException;

import java.util.ArrayList;

import dataAccess.databaseManagement.entity.PortfolioEntity;
import dataAccess.databaseManagement.manager.PortfolioManager;

/*
 * @Dinh: copy following codes into PortfolioManager.java
 
	public ArrayList<PortfolioEntity> getPortfolioByDate(Date date) {
		try {
			ArrayList<PortfolioEntity> listAllPortfolios = new ArrayList<PortfolioEntity>();
			
			String queryString = "SELECT * FROM portfolio WHERE date=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setDate(1, date);
			resultSet = ptmt.executeQuery();

			while (resultSet.next()) {
				PortfolioEntity portfolioEntity = new PortfolioEntity();
				portfolioEntity.setPortfolioID(resultSet.getLong("portfolio_id"));
				portfolioEntity.setUserID(resultSet.getLong("user_id"));
				portfolioEntity.setAssetID(resultSet.getLong("asset_id"));
				portfolioEntity.setPrice(resultSet.getDouble("price"));
				portfolioEntity.setVolume(resultSet.getDouble("volume"));
				portfolioEntity.setDate(date);

				listAllPortfolios.add(portfolioEntity);
			}

			return listAllPortfolios;
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
		Date date = Date.valueOf("1970-01-01");
		
		Portfolio portfolioSample = new Portfolio(1, 1, 1.22, 11, date);
//		portfolioSample.add();
		
		ArrayList<PortfolioEntity> t = portfolioSample.getPortfolioByDate(date);
		
	}

}
