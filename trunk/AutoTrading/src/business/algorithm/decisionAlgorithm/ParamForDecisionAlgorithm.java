package business.algorithm.decisionAlgorithm;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.PriceEntity;

public abstract class ParamForDecisionAlgorithm {
	TreeMap<AssetEntity, ArrayList<PriceEntity>> priceList;
	Date startDate;
	Double cash;
	
	public TreeMap<AssetEntity, ArrayList<PriceEntity>> getPriceList() {
		return priceList;
	}
	public void setPriceList(TreeMap<AssetEntity, ArrayList<PriceEntity>> priceList) {
		this.priceList = priceList;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Double getCash() {
		return cash;
	}
	public void setCash(Double cash) {
		this.cash = cash;
	}
	
}
