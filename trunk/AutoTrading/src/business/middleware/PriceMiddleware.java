package business.middleware;

import java.sql.Date;
import java.util.ArrayList;
import java.util.TreeMap;

import dataAccess.databaseManagement.entity.PriceEntity;
import dataAccess.databaseManagement.manager.PriceManager;

public class PriceMiddleware {
	private static PriceManager priceManager = new PriceManager();
	
	private static TreeMap<Date, PriceEntity> dateMap = null;
	
	public static void load() {
		if (dateMap == null) {
//			ArrayList<PriceEntity> priceList = pr;
		}
	}
	
}
