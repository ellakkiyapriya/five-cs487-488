/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autotrade.core.database;

import autotrade.core.AutoTrade;
import autotrade.core.AutoTradeLocalData;
import autotrade.core.database.AutoTradeDatabaseManagement;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Dinh
 */
public class UserPortfolio {

    private int portfolio_id;
    private int user_id;
    private String symbol;
    private int volume;
    private double buy_price;

    public double getBuy_price() {
        return buy_price;
    }

    public void setBuy_price(double buy_price) {
        this.buy_price = buy_price;
    }

    public int getPortfolio_id() {
        return portfolio_id;
    }

    public void setPortfolio_id(int portfolio_id) {
        this.portfolio_id = portfolio_id;
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

    public static void addUserPortfolios(UserPortfolio userPortfolio) {
        try {
            Statement statement = AutoTrade.conn.createStatement();

            String sqlStatement = "INSERT user_portfolio VALUES(";
            sqlStatement += "NULL ,";
            sqlStatement += "'" + userPortfolio.getUser_id() + "',";
            sqlStatement += "'" + userPortfolio.getSymbol() + "',";
            sqlStatement += "'" + userPortfolio.getVolume() + "',";
            sqlStatement += "'" + userPortfolio.getBuy_price() + "');";

            statement.executeUpdate(sqlStatement);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void updateUserPortfolios(UserPortfolio userPortfolio) {
        try {
            Statement statement = AutoTrade.conn.createStatement();

            String sqlStatement = "UPDATE user_portfolio SET ";
            sqlStatement += "user_id = '" + userPortfolio.getUser_id() + "',";
            sqlStatement += "symbol = '" + userPortfolio.getSymbol() + "',";
            sqlStatement += "volume = '" + userPortfolio.getVolume() + "',";
            sqlStatement += "buy_price = '" + userPortfolio.getBuy_price() + "' ";
            sqlStatement += "WHERE id = '" + userPortfolio.getPortfolio_id() + "'";

            statement.executeUpdate(sqlStatement);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static UserPortfolio getUserPortfolios(int user_id, String symbol) {
        UserPortfolio userPortfolio = null;

        try {
            Statement statement = AutoTrade.conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * "
                    + "FROM user_portfolio "
                    + "WHERE user_id = '" + user_id + "' "
                    + "AND symbol LIKE '" + symbol + "'");

            while (resultSet.next()) {
                userPortfolio = new UserPortfolio();
                userPortfolio.setPortfolio_id(resultSet.getInt("id"));
                userPortfolio.setUser_id(user_id);
                userPortfolio.setSymbol(resultSet.getString("symbol"));
                userPortfolio.setBuy_price(resultSet.getDouble("buy_price"));
                userPortfolio.setVolume(resultSet.getInt("volume"));
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return userPortfolio;
    }

    public static void removeUserPortfolio(int userportfolioID) {
        try {
            Statement statement = AutoTrade.conn.createStatement();

            String sqlStatement = "DELETE FROM user_portfolio WHERE id = '" + userportfolioID + "'";

            statement.executeUpdate(sqlStatement);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<UserPortfolio> getListUserPortfolios(int user_id) {
        ArrayList<UserPortfolio> listUserPortfolios = new ArrayList<UserPortfolio>();

        try {
            Statement statement = AutoTrade.conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * "
                    + "FROM user_portfolio "
                    + "WHERE user_id = '" + user_id + "'");

            while (resultSet.next()) {
                UserPortfolio userPortfolio = new UserPortfolio();
                userPortfolio.setPortfolio_id(resultSet.getInt("id"));
                userPortfolio.setUser_id(user_id);
                userPortfolio.setSymbol(resultSet.getString("symbol"));
                userPortfolio.setBuy_price(resultSet.getDouble("buy_price"));
                userPortfolio.setVolume(resultSet.getInt("volume"));
                listUserPortfolios.add(userPortfolio);
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listUserPortfolios;
    }

    public static Object[][] getUserPortfolioTableData(ArrayList<UserPortfolio> listUserPortfolios) {
        Object[][] data = new Object[listUserPortfolios.size()][5];

        for (int i = 0; i < listUserPortfolios.size(); ++i) {
            UserPortfolio userPortfolio = listUserPortfolios.get(i);
            data[i][0] = userPortfolio.getSymbol();
            data[i][1] = new Integer(userPortfolio.getVolume());
            data[i][2] = new Double(userPortfolio.getBuy_price());;
            data[i][3] = new Double(StockInfoDaily.getStockInfo(userPortfolio.getSymbol(), AutoTradeLocalData.load().getCurrentDate().getTime()).getClosePrice());
            data[i][4] = (((Double)data[i][3]/(Double)data[i][2]) - 1)*100;
        }

        return data;
    }

    public static void emptyUserPortfolioTable() {
       try {
            Statement statement = AutoTrade.conn.createStatement();

            String sqlStatement = "TRUNCATE TABLE  `user_portfolio`";

            statement.executeUpdate(sqlStatement);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
