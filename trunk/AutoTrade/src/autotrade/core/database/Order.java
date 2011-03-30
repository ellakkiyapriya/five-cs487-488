/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package autotrade.core.database;

import autotrade.core.virtualsystem.AutoTrade;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Dinh
 */
public class Order {
    public static final int BUY = 0;
    public static final int SELL = 1;

    private int order_id;
    private int order_type;
    private int user_id;
    private long orderTime;
    private String symbol;
    private double price;
    private int volume;

    public int getOrder_type() {
        return order_type;
    }

    public void setOrder_type(int order_type) {
        this.order_type = order_type;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(long orderTime) {
        this.orderTime = orderTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public static void addOrder(Order order) {
        try {
            Statement statement = AutoTrade.conn.createStatement();

            String sqlStatement = "INSERT order VALUES(";
            sqlStatement += "NULL ,";
            sqlStatement += "'" + order.order_type + "',";
            sqlStatement += "'" + order.user_id + "',";
            sqlStatement += "'" + (new Date(order.orderTime)).toString() + "',";
            sqlStatement += "'" + order.symbol + "',";
            sqlStatement += "'" + order.price + "',";
            sqlStatement += "'" + order.volume + "');";

            statement.executeUpdate(sqlStatement);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void updateOrder(Order order) {
        try {
            Statement statement = AutoTrade.conn.createStatement();

            String sqlStatement = "UPDATE order SET ";
            sqlStatement += "order_type = '" + order.order_type + "',";
            sqlStatement += "user_id = '" + order.user_id + "',";
            sqlStatement += "date = '" + (new Date(order.orderTime)).toString() + "',";
            sqlStatement += "symbol = '" + order.symbol + "',";
            sqlStatement += "price = '" + order.price + "',";
            sqlStatement += "volume = '" + order.volume + "');";
            sqlStatement += "WHERE id = '" + order.getOrder_id() + "'";

            statement.executeUpdate(sqlStatement);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

//    public static UserPortfolio getUserPortfolios(int user_id, String symbol) {
//        UserPortfolio userPortfolio = null;
//
//        try {
//            AutoTrade.connection AutoTrade.conn = AutoTradeDatabaseManagement.getAutoTrade.connectionWithDatabase();
//            Statement statement = AutoTrade.conn.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * "
//                    + "FROM user_portfolio "
//                    + "WHERE user_id = '" + user_id + "' "
//                    + "AND symbol LIKE '" + symbol + "'");
//
//            while (resultSet.next()) {
//                userPortfolio = new UserPortfolio();
//                userPortfolio.setPortfolio_id(resultSet.getInt("id"));
//                userPortfolio.setUser_id(user_id);
//                userPortfolio.setSymbol(resultSet.getString("symbol"));
//                userPortfolio.setBuy_price(resultSet.getDouble("buy_price"));
//                userPortfolio.setVolume(resultSet.getInt("volume"));
//            }
//
//            AutoTrade.conn.close();
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//        return userPortfolio;
//    }

    public static void removeOrder(int orderID) {
        try {
            Statement statement = AutoTrade.conn.createStatement();

            String sqlStatement = "DELETE FROM order WHERE id = '" + orderID + "'";

            statement.executeUpdate(sqlStatement);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<Order> getListOrders(int user_id, long dateTime) {
        ArrayList<Order> listOrders = new ArrayList<Order>();

        try {
            Statement statement = AutoTrade.conn.createStatement();
            String sqlStatement = "SELECT * "
                    + "FROM `order` "
                    + "WHERE `user_id` = '" + user_id + "' "
                    + "AND `date` LIKE '" + (new Date(dateTime)).toString() + "'";
//            System.out.println(sqlStatement);
            ResultSet resultSet = statement.executeQuery(sqlStatement);


            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderTime(dateTime);
                order.setOrder_id(resultSet.getInt("id"));
                order.setOrder_type(resultSet.getInt("order_type"));
                order.setPrice(resultSet.getDouble("price"));
                order.setSymbol(resultSet.getString("symbol"));
                order.setUser_id(user_id);
                order.setVolume(resultSet.getInt("volume"));
                listOrders.add(order);
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listOrders;
    }

    public static Object[][] getOrderTableData(ArrayList<Order> listOrders) {
        Object[][] data = new Object[listOrders.size()][5];

        for (int i = 0; i < listOrders.size(); ++i) {
            Order order = listOrders.get(i);
            if (order.getOrder_type() == Order.BUY)
                data[i][0] = "Buy";
            else
                data[i][0] = "Sell";
            
            data[i][1] = order.getSymbol();
            data[i][2] = new Double(order.getPrice());
            data[i][3] = new Integer(order.getVolume());
            data[i][4] = (Double)data[i][2]*(Integer)data[i][3];
        }

        return data;
    }

    public static void emptyOrderTable() {
       try {
            Statement statement = AutoTrade.conn.createStatement();

            String sqlStatement = "TRUNCATE TABLE `order`";

            statement.executeUpdate(sqlStatement);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}
