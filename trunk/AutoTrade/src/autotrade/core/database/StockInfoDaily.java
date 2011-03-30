/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package autotrade.core.database;

import autotrade.core.AutoTrade;
import java.sql.*;
import java.util.TreeMap;

/**
 *
 * @author Dinh
 */
public class StockInfoDaily {
    private String symbol;
    private long dateTime;
    private double openPrice, highPrice, lowPrice, closePrice;
    private int volume;

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public static StockInfoDaily getStockInfo(String symbol, long dateTime) {
        StockInfoDaily stockInfo = null;

        Date date = new Date(dateTime);

        try {
            Statement statement = AutoTrade.conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * "
                    + "FROM stock_price_daily "
                    + "WHERE date LIKE '" + date.toString() + "' "
                    + "AND symbol LIKE '" + symbol + "'");

            while (resultSet.next()) {
                stockInfo = new StockInfoDaily();
                stockInfo.setDateTime(dateTime);
                stockInfo.setSymbol(symbol);
                stockInfo.setOpenPrice(resultSet.getDouble("open"));
                stockInfo.setHighPrice(resultSet.getDouble("high"));
                stockInfo.setLowPrice(resultSet.getDouble("low"));
                stockInfo.setClosePrice(resultSet.getDouble("close"));
                stockInfo.setVolume(resultSet.getInt("volume"));
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return stockInfo;
    }

    public static TreeMap<String, StockInfoDaily> getMapSymbolStockInfo(long dateTime) {
        TreeMap<String, StockInfoDaily> mapSymbolStockInfo = new TreeMap<String, StockInfoDaily>();

        Date date = new Date(dateTime);

        try {
            Statement statement = AutoTrade.conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * "
                    + "FROM stock_price_daily "
                    + "WHERE date LIKE '" + date.toString() + "'");

            while (resultSet.next()) {
                StockInfoDaily stockInfo = new StockInfoDaily();
                stockInfo = new StockInfoDaily();
                stockInfo.setDateTime(dateTime);
                stockInfo.setSymbol(resultSet.getString("symbol"));
                stockInfo.setOpenPrice(resultSet.getDouble("open"));
                stockInfo.setHighPrice(resultSet.getDouble("high"));
                stockInfo.setLowPrice(resultSet.getDouble("low"));
                stockInfo.setClosePrice(resultSet.getDouble("close"));
                stockInfo.setVolume(resultSet.getInt("volume"));

                mapSymbolStockInfo.put(stockInfo.getSymbol(),stockInfo);
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return mapSymbolStockInfo;
    }

}
