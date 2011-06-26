package business.algorithm.decisionAlgorithm;

import java.util.ArrayList;

import java.util.TreeMap;

public class MovingAverage extends AbstractDecisionAlgorithm {

    @Override
    public ArrayList<Order> runAlgorithm() {
        // TODO Auto-generated method stub

        ArrayList<Double> price = ((ParamForMovingAverage) parameters).getPrice();
        int MA_period = ((ParamForMovingAverage) parameters).getMA_period();

        ArrayList<Double> MA = new ArrayList<Double>();
        Double temp;
        for (int i = 0; i <= price.size() - MA_period; ++i) {
            temp = 0.0;
            for (int j = 0; j < MA_period; ++j) {
                temp += price.get(i + j);
            }
            MA.add(temp / MA_period);
        }

        for (int i = 0; i < MA_period - 1; ++i) {
            price.remove(0);

            
        }
        double todayPrice, yesterdayPrice, todayMA, yesterdayMA;
        ArrayList<Order> orderList = new ArrayList<Order>();

        for (int i = 0; i < price.size() - 1; ++i) {
            yesterdayPrice = price.get(i);
            todayPrice = price.get(i + 1);
            yesterdayMA = MA.get(i);
            todayMA = MA.get(i + 1);

            if ((todayPrice > yesterdayPrice) && (todayMA > yesterdayMA)
                    && (todayMA > todayPrice) && (yesterdayMA < yesterdayPrice)) {
                // issue buy order
                Order order = new Order(Order.ORDER_BUY, todayMA, i + 1);
                orderList.add(order);
            } else if ((todayPrice < yesterdayPrice) && (todayMA < yesterdayMA)
                    && (todayMA < todayPrice) && (yesterdayMA > yesterdayPrice)) {
                // issue sell order
                Order order = new Order(Order.ORDER_SELL, todayMA, i + 1);
                orderList.add(order);
            }
        }
        return orderList;
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
