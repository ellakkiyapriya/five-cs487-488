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

/**
 *
 * @author Dinh
 */
public class SimpleMovingAverage extends TechnicalAnalysisMethod{
    private int period;

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
        Calendar calendar = Calendar.getInstance();
        ArrayList<UserPortfolio> listUserPortfolios = AutoTrade.getListUserPortfolios(user_id);

        for (String symbol : AutoTrade.LIST_ALL_JSC_SYMBOL) {
            StockInfoDaily currentStockInfo = AutoTrade.getStockInfo(symbol, date.getTime());

            if (currentStockInfo != null) {
                int count = period;
                double maPrice = currentStockInfo.getClosePrice();
                calendar.setTime(date);
                while (count > 1 && calendar.getTimeInMillis() >= AutoTrade.earliestTime) {
                    calendar.add(Calendar.DAY_OF_MONTH, -1);

                    StockInfoDaily tempStockInfo = AutoTrade.getStockInfo(symbol, calendar.getTimeInMillis());
                    while (tempStockInfo == null && calendar.getTimeInMillis() >= AutoTrade.earliestTime) {
                        calendar.add(Calendar.DAY_OF_MONTH, -1);
                        tempStockInfo = AutoTrade.getStockInfo(symbol, calendar.getTimeInMillis());
                    }

                    if (tempStockInfo != null) {
                        maPrice += tempStockInfo.getClosePrice();
                        count--;
                    }
                }

                if (count == 1) {
                    maPrice /= period;
                    if (currentStockInfo.getClosePrice() > maPrice) {
                        Order buyOrder = new Order();
                        buyOrder.setOrder_type(Order.BUY);
                        buyOrder.setOrderTime(date.getTime());
                        buyOrder.setUser_id(user_id);
                        buyOrder.setSymbol(symbol);
                        buyOrder.setPrice(currentStockInfo.getClosePrice());
                        listBuyOrder.add(buyOrder);
                    } else {
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
        }

        User user = AutoTrade.getUserByID(user_id);
        ArrayList<Order> listOrder = new ArrayList<Order>();

        double priceForEachSymbol = user.getCash_remain()/listBuyOrder.size();
        for (Order order : listBuyOrder) {
            int volume = (int) (priceForEachSymbol / order.getPrice());
            volume = volume - (volume % 10);
            order.setVolume(volume);

            if (volume != 0) {
                listOrder.add(order);
            }
        }

        listOrder.addAll(listSellOrder);

        return listOrder;
    }
}
