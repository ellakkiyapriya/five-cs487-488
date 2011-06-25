package business.virtualTrading;

import java.sql.Date;
import java.util.ArrayList;

import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.PortfolioEntity;
import dataAccess.databaseManagement.manager.PortfolioManager;
import dataAccess.databaseManagement.manager.PriceManager;

/**
 * Class Name: Portfolio
 * 
 * @version 1.5
 * @date June 1, 2011
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

	public Portfolio(Asset asset, double buyPrice, double volume) {
		PriceManager priceManager = new PriceManager();
		this.setAsset(new Asset(asset));
		this.setBuyPrice(buyPrice);
		this.setVolume(volume);
		this.setProfit(0);
		this.setCurrentPrice(priceManager.getPriceByAssetIDAndDate(
				asset.getAssetID(), priceManager.getLatestDate()).getClose());
		
		
	}

	public Portfolio(AssetEntity asset, double buyPrice, double volume) {
		PriceManager priceManager = new PriceManager();
		this.setAsset(new Asset(asset));
		this.setBuyPrice(buyPrice);
		this.setVolume(volume);
		this.setProfit(0);
		this.setCurrentPrice(priceManager.getPriceByAssetIDAndDate(
				asset.getAssetID(), priceManager.getLatestDate()).getClose());

		
	}


	/**
	 * Delete portfolio by ID from database <li>Note: this method update
	 * database
	 */
	public void delete(long portfolioID) {
		PortfolioManager portfolioManager = new PortfolioManager();
		portfolioManager.delete(portfolioID);
	}

	
	

	public static void main(String args[]) {
		Date date = Date.valueOf("2001-01-02");

		// Portfolio portfolioSample = new Portfolio(1, 2, 1.22, 11, date);
		PortfolioManager portfolioManager = new PortfolioManager();
		Date ta = portfolioManager.getLatestDate();
		System.out.println(ta.toString());
		// portfolioSample.add();

		// ArrayList<PortfolioEntity> t =
		// portfolioSample.getPortfolioByDate(date);

	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public double getBuyPrice() {
		return buyPrice;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getVolume() {
		return volume;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public double getProfit() {
		return profit;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

}
