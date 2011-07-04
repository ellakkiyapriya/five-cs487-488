package business.algorithm.predictAlgorithm;

import java.util.ArrayList;
import java.util.TreeMap;

import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.PriceEntity;

public abstract class ParamForPredictAlgorithm {
	TreeMap<AssetEntity, ArrayList<PriceEntity>> priceList;
	int futureInterval;

	public TreeMap<AssetEntity, ArrayList<PriceEntity>> getPriceList() {
		return priceList;
	}

	public void setPriceList(
			TreeMap<AssetEntity, ArrayList<PriceEntity>> priceList) {
		this.priceList = priceList;
	}

	public int getFutureInterval() {
		return futureInterval;
	}

	public void setFutureInterval(int futureInterval) {
		this.futureInterval = futureInterval;
	}

	public ParamForPredictAlgorithm(
			TreeMap<AssetEntity, ArrayList<PriceEntity>> priceList,
			int futureInterval) {
		super();
		this.priceList = priceList;
		this.futureInterval = futureInterval;
	}

	public TreeMap<String, Class> getParametersList() {
		TreeMap<String, Class> map = new TreeMap<String, Class>();
		map.put("Price list", TreeMap.class);
		map.put("Future interval", Integer.class);
		return map;
	}

	public void setParametersValue(TreeMap<String, Object> map) {
		this.priceList = (TreeMap<AssetEntity, ArrayList<PriceEntity>>) map
				.get("Price list");
		this.futureInterval = (Integer) map.get("Future interval");
	}
}
