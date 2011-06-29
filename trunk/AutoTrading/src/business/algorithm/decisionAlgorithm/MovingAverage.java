package business.algorithm.decisionAlgorithm;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import java.util.TreeMap;

import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.PriceEntity;

public class MovingAverage extends AbstractDecisionAlgorithm {

	public static final int MIN_VOLUME = 10;
	
    @Override
    public OutputForDecisionAlgorithm runAlgorithm(ParamForDecisionAlgorithm param) {
        // TODO Auto-generated method stub
        ArrayList<Order> orderList = new ArrayList<Order>();
    	
    	TreeMap<AssetEntity, ArrayList<PriceEntity>> map = ((ParamForMovingAverage)param).getPriceList();
    	int MA_period = ((ParamForMovingAverage)param).getMA_period();
    	Double cash = ((ParamForMovingAverage)param).getCash();
    	Date startDate = ((ParamForMovingAverage)param).getStartDate();
    	
    	Set<AssetEntity> assetSet = map.keySet();
    	
    	for (AssetEntity asset : assetSet)
    	{
    		ArrayList<PriceEntity> priceList = map.get(asset);
    		ArrayList<Double> MA = new ArrayList<Double>();
    		
            Double temp;
            for (int i = 0; i <= priceList.size() - MA_period; ++i) {
                temp = 0.0;
                for (int j = 0; j < MA_period; ++j) {
                    temp += priceList.get(i + j).getClose();
                }
                MA.add(temp / MA_period);
            }

            for (int i = 0; i < MA_period - 1; ++i) {
                priceList.remove(0);
            }
            double todayPrice, yesterdayPrice, todayMA, yesterdayMA;

            boolean previous_buy_order = false;
            boolean previous_sell_order = false;
            for (int i = 0; i < priceList.size() - 1; ++i) {
                yesterdayPrice = priceList.get(i).getClose();
                todayPrice = priceList.get(i + 1).getClose();
                yesterdayMA = MA.get(i);
                todayMA = MA.get(i + 1);

                if (todayMA > yesterdayMA)
                {
                	if (todayPrice > todayMA)
                	{
                		if (previous_buy_order == false)
                		{
                			int volume = (int) (cash / yesterdayPrice);
                			if (volume > MIN_VOLUME) {
                				Order order = new Order(Order.ORDER_BUY, yesterdayPrice, priceList.get(i+1).getDate(), volume);
                				orderList.add(order);
                				previous_buy_order = true;
                				previous_sell_order = false;
                			}
                		}
                	}
                }
                else
                {
                	if (todayPrice < todayMA)
                	{
                		if (previous_sell_order == false)
                		{
                			Order order = new Order(Order.ORDER_SELL, yesterdayPrice, MA_period + i);
                			orderList.add(order);
                			previous_buy_order = false;
                			previous_sell_order = true;
                		}
                	}
                }
            }

    	}
    	return null;
    }

    @Override
    public TreeMap<String, Class> getParametersList() {
        TreeMap<String, Class> map = new TreeMap<String, Class>();
        // map.put("Price list", ArrayList.class);
        map.put("MA period", Integer.class);
        return map;
    }

    @Override
    public void setParametersValue(TreeMap<String, Object> map) {
        ArrayList<Double> priceList = null;
        Integer MA_period = (Integer) map.get("MA period");
        parameters = new ParamForMovingAverage(priceList, MA_period);
    }

    @Override
    public void setPriceList(ArrayList<Double> prices) {
        ((ParamForMovingAverage) parameters).setPrice(prices);
    }
}
