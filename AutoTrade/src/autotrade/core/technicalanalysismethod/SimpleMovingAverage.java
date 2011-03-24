/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autotrade.core.technicalanalysismethod;

import autotrade.core.AutoTrade;
import autotrade.core.Order;
import autotrade.core.StockInfoDaily;
import autotrade.core.User;
import autotrade.core.UserPortfolio;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeMap;

/**
 *
 * @author Dinh
 */
public class SimpleMovingAverage extends TechnicalAnalysisMethod {

    private int period;

//    public SimpleMovingAverage(int period) {
//        this.period = period;
//    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    @Override
    public ArrayList<Order> getOrders(Date date, int user_id) {
        ArrayList<Order> listBuyOrder = new ArrayList<Order>();
        ArrayList<Order> listSellOrder = new ArrayList<Order>();
        ArrayList<Order> listOrder = new ArrayList<Order>();

        Calendar calendar = Calendar.getInstance();
        ArrayList<UserPortfolio> listUserPortfolios = UserPortfolio.getListUserPortfolios(user_id);
        TreeMap<Date, TreeMap<String, StockInfoDaily>> mapStockDaily = new TreeMap<Date, TreeMap<String, StockInfoDaily>>();

        int count = 0;
        calendar.setTime(date);
        while (count < period && calendar.getTimeInMillis() >= AutoTrade.earliestTime) {
            TreeMap<String, StockInfoDaily> mapSymbolStockInfo = StockInfoDaily.getMapSymbolStockInfo(date.getTime());
            if (!mapSymbolStockInfo.isEmpty()) {
                count++;
                mapStockDaily.put(calendar.getTime(), mapSymbolStockInfo);
            }
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        }

        if (count < period) {
            return listOrder;
        }

        for (String symbol : mapStockDaily.get(date).keySet()) {
            double maPrice = 0;
            int time = 0;
            StockInfoDaily currentStockInfo = mapStockDaily.get(date).get(symbol);
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            Date preDate = mapStockDaily.floorKey(calendar.getTime());
            StockInfoDaily previousStockInfo = mapStockDaily.get(preDate).get(symbol);

            for (Date tempDate : mapStockDaily.keySet()) {
                StockInfoDaily stockInfoDaily = mapStockDaily.get(tempDate).get(symbol);
                if (stockInfoDaily != null) {
                    maPrice += stockInfoDaily.getClosePrice();
                    time++;
                }
            }
            
            if (time == period) {
                maPrice /= time;
                if (currentStockInfo.getClosePrice() > maPrice && previousStockInfo.getClosePrice() <= maPrice) {
                    Order buyOrder = new Order();
                    buyOrder.setOrder_type(Order.BUY);
                    buyOrder.setOrderTime(date.getTime());
                    buyOrder.setUser_id(user_id);
                    buyOrder.setSymbol(symbol);
                    buyOrder.setPrice(currentStockInfo.getClosePrice());
                    listBuyOrder.add(buyOrder);
                } else if (currentStockInfo.getClosePrice() < maPrice && previousStockInfo.getClosePrice() >= maPrice) {
                    for (UserPortfolio userPortfolio : listUserPortfolios) {
                        if (userPortfolio.getSymbol().equals(symbol)) {
                            Order sellOrder = new Order();
                            sellOrder.setOrder_type(Order.SELL);
                            sellOrder.setOrderTime(date.getTime());
                            sellOrder.setUser_id(user_id);
                            sellOrder.setSymbol(symbol);
                            sellOrder.setPrice(currentStockInfo.getClosePrice());
                            sellOrder.setVolume(userPortfolio.getVolume());
                            listSellOrder.add(sellOrder);
                        }
                    }
                }
            }
        }

        User user = User.getUserByID(user_id);

        if (!listBuyOrder.isEmpty()) {
            double priceForEachSymbol = user.getCash_remain() / listBuyOrder.size();
            for (Order order : listBuyOrder) {
                int volume = (int) (priceForEachSymbol / order.getPrice());
                volume = volume - (volume % 10);
                order.setVolume(volume);

                if (volume != 0) {
                    listOrder.add(order);
                }
            }
        }

        listOrder.addAll(listSellOrder);

        return listOrder;
    }
}
