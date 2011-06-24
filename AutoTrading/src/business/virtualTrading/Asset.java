package business.virtualTrading;

import dataAccess.databaseManagement.entity.AssetEntity;

public class Asset {
	AssetEntity assetEntity;

	public Asset(String name, String symbol, long exchangeID, String assetInfo,
			double fluctuationRange) {
		assetEntity = new AssetEntity(name, symbol, exchangeID, assetInfo,
				fluctuationRange);
	}

	public Asset(AssetEntity assetEntity) {
		this.assetEntity = new AssetEntity(assetEntity.getName(), assetEntity
				.getSymbol(), assetEntity.getExchangeID(), assetEntity
				.getAssetInfo(), assetEntity.getFluctuationRange());
	}
	
	public Asset(Asset assetEntity) {
		this.assetEntity = new AssetEntity(assetEntity.getName(), assetEntity
				.getSymbol(), assetEntity.getExchangeID(), assetEntity
				.getAssetInfo(), assetEntity.getFluctuationRange());
	}

	public void setAssetID(long assetID) {
		assetEntity.setAssetID(assetID);
	}

	public long getAssetID() {
		return assetEntity.getAssetID();
	}

	public void setName(String name) {
		assetEntity.setName(name);
	}

	public String getName() {
		return assetEntity.getName();
	}

	public void setSymbol(String symbol) {
		assetEntity.setSymbol(symbol);
	}

	public String getSymbol() {
		return assetEntity.getSymbol();
	}
	
	public long getExchangeID() {
		return assetEntity.getExchangeID();
	}
	
	public String getAssetInfo() {
		return assetEntity.getAssetInfo();
	}
	
	public double getFluctuationRange() {
		return assetEntity.getFluctuationRange();
	}

	@Override
	public String toString() {
		return assetEntity.getSymbol();
	}

}
