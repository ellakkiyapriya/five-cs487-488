package business.algorithm.decisionAlgorithm;

import java.util.ArrayList;
import java.util.TreeMap;

import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.PriceEntity;

public abstract class AbstractDecisionAlgorithm {

	public abstract OutputForDecisionAlgorithm runAlgorithm();

	public AbstractDecisionAlgorithm(
			TreeMap<AssetEntity, ArrayList<PriceEntity>> priceList) {
		super();
		this.priceList = priceList;
	}

	TreeMap<AssetEntity, ArrayList<PriceEntity>> priceList;

	public TreeMap<AssetEntity, ArrayList<PriceEntity>> getPriceList() {
		return priceList;
	}

	public void setPriceList(
			TreeMap<AssetEntity, ArrayList<PriceEntity>> priceList) {
		this.priceList = priceList;
	}

	@SuppressWarnings("rawtypes")
	public TreeMap<String, Class> getParameterList() {
		TreeMap<String, Class> map = new TreeMap<String, Class>();
		map.put("Price list", TreeMap.class);
		return map;
	}

	@SuppressWarnings("unchecked")
	public void setParameterValue(TreeMap<String, Object> map) {
		this.priceList = (TreeMap<AssetEntity, ArrayList<PriceEntity>>) map
				.get("Price list");
	}
}
