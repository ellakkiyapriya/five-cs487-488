package business.algorithm.predictAlgorithm;

import java.util.ArrayList;
import java.util.TreeMap;

import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.PriceEntity;


public abstract class AbstractPredictAlgorithm {
	TreeMap<AssetEntity, ArrayList<PriceEntity>> priceList;
	Integer futureInterval;

	public TreeMap<AssetEntity, ArrayList<PriceEntity>> getPriceList() {
		return priceList;
	}

	public void setPriceEntityList(
			TreeMap<AssetEntity, ArrayList<PriceEntity>> priceList) {
		this.priceList = priceList;
	}

	public Integer getFutureInterval() {
		return futureInterval;
	}

	public void setFutureInterval(Integer futureInterval) {
		this.futureInterval = futureInterval;
	}
	
	@SuppressWarnings("rawtypes")
	public TreeMap<String, Class> getParametersList() {
		TreeMap<String, Class> map = new TreeMap<String, Class>();
		map.put("Price list", TreeMap.class);
		map.put("Future interval", Integer.class);
		return map;
	}

	@SuppressWarnings("unchecked")
	public void setParametersValue(TreeMap<String, Object> map) {
		this.priceList = (TreeMap<AssetEntity, ArrayList<PriceEntity>>) map
				.get("Price list");
		this.futureInterval = (Integer) map.get("Future interval");
	}

	
	
	public AbstractPredictAlgorithm(
			TreeMap<AssetEntity, ArrayList<PriceEntity>> priceList,
			Integer futureInterval) {
		super();
		this.priceList = priceList;
		this.futureInterval = futureInterval;
	}

	public abstract OutputForPredictionAlgorithm runAlgorithm() throws Exception;
}
