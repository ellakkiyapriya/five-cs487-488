/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package autotrade.core;

import autotrade.core.database.AutoTradeDatabaseManagement;
import autotrade.core.technicalanalysismethod.TechnicalAnalysisMethod;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Dinh
 */
public class User {
    public static ArrayList<User> LIST_ALL_USER = getAllUsers();

    private int userID;
    private String userName;
    private int typeID;
    private String typeName;
    private TechnicalAnalysisMethod technicalAnalysisMethod;
    private double inital_cash;
    private double cash_remain;
    private double stock_value;
    private boolean active;
    private ArrayList<UserPortfolio> listUserPortfolios;
    private ArrayList<Order> listcenterDateOrders;
    private ArrayList<Order> listcurrentDateOrders;

    public double getStock_value() {
        return stock_value;
    }

    public void setStock_value(double stock_value) {
        this.stock_value = stock_value;
    }

    public double getInital_cash() {
        return inital_cash;
    }

    public void setInital_cash(double inital_cash) {
        this.inital_cash = inital_cash;
    }

    public ArrayList<Order> getListcenterDateOrders() {
        return listcenterDateOrders;
    }

    public void setListcenterDateOrders(ArrayList<Order> listcenterDateOrders) {
        this.listcenterDateOrders = listcenterDateOrders;
    }

    public ArrayList<Order> getListcurrentDateOrders() {
        return listcurrentDateOrders;
    }

    public void setListcurrentDateOrders(ArrayList<Order> listcurrentDateOrders) {
        this.listcurrentDateOrders = listcurrentDateOrders;
    }

    public ArrayList<UserPortfolio> getListUserPortfolios() {
        return listUserPortfolios;
    }

    public void setListUserPortfolios(ArrayList<UserPortfolio> listUserPortfolios) {
        this.listUserPortfolios = listUserPortfolios;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getCash_remain() {
        return cash_remain;
    }

    public void setCash_remain(double cash_remain) {
        this.cash_remain = cash_remain;
    }

    public TechnicalAnalysisMethod getTechnicalAnalysisMethod() {
        return technicalAnalysisMethod;
    }

    public void setTechnicalAnalysisMethod(TechnicalAnalysisMethod technicalAnalysisMethod) {
        this.technicalAnalysisMethod = technicalAnalysisMethod;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return this.userName;
    }

    public Object[][] getListOrdersTableData() {
        if (AutoTradeLocalData.load().getCenter_date().before(AutoTradeLocalData.load().getCurrentDate())) {
            return Order.getOrderTableData(listcenterDateOrders);
        } else {
            return Order.getOrderTableData(listcurrentDateOrders);
        }
    }

    public Object[][] getUserPortfolioTableData() {
        return UserPortfolio.getUserPortfolioTableData(listUserPortfolios);
    }

    public static void addNewUser(String userName, int typeId, int tamiID, double initialCash) {
        try {
            Connection conn = AutoTradeDatabaseManagement.getConnectionWithDatabase();
            Statement statement = conn.createStatement();

            String sqlStatement = "INSERT user VALUES(";
            sqlStatement += "NULL ,";
            sqlStatement += "'" + userName + "',";
            sqlStatement += "'" + typeId + "',";
            sqlStatement += "'" + tamiID + "',";
            sqlStatement += "'" + initialCash + "',";
            sqlStatement += "'" + initialCash + "',";
            sqlStatement += "'" + 1 + "');";

            statement.executeUpdate(sqlStatement);

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void removeUser(int userID) {
        try {
            Connection conn = AutoTradeDatabaseManagement.getConnectionWithDatabase();
            Statement statement = conn.createStatement();
            int tamiID = 0;

            ResultSet resultSet = statement.executeQuery("SELECT * "
                    + "FROM `user` "
                    + "WHERE `id` = '" + userID + "'");

            while (resultSet.next()) {
                tamiID = resultSet.getInt("technical_analysis_method_instance_id");
            }

            AutoTrade.removeTAMI(tamiID);

            String sqlStatement = "DELETE FROM user WHERE id = '" + userID + "'";

            statement.executeUpdate(sqlStatement);

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void saveUserInfo(User user) {
        try {
            Connection conn = AutoTradeDatabaseManagement.getConnectionWithDatabase();
            Statement statement = conn.createStatement();

            String sqlStatement = "UPDATE user SET ";
            sqlStatement += "name = '" + user.getUserName() + "',";
            sqlStatement += "type_id = '" + user.getTypeID() + "',";
            sqlStatement += "inital_cash = '" + user.getInital_cash() + "',";
            sqlStatement += "cash_remain = '" + user.getCash_remain() + "',";
            if (user.isActive())
                sqlStatement += "active = '" + 1 + "' ";
            else
                sqlStatement += "active = '" + 0 + "' ";

            sqlStatement += "WHERE id = '" + user.getUserID() + "'";

            statement.executeUpdate(sqlStatement);

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static int getGreatestIDUser() {
        int greatestID = 0;

        try {
            Connection conn = AutoTradeDatabaseManagement.getConnectionWithDatabase();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT max(id) FROM user");
            resultSet.next();
            greatestID = resultSet.getInt(1);

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return greatestID;
    }

        public static User getUserByName(String userName) {
        User user = new User();

        try {
            Connection conn = AutoTradeDatabaseManagement.getConnectionWithDatabase();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * "
                    + "FROM `user` "
                    + "WHERE `name` LIKE '" + userName + "'");

            while (resultSet.next()) {
                user.setUserID(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("name"));
                user.setTypeID(resultSet.getInt("type_id"));
                user.setTypeName(AutoTrade.getUserTypeName(user.getTypeID()));
                user.setInital_cash(resultSet.getDouble("inital_cash"));
                user.setCash_remain(resultSet.getDouble("cash_remain"));
                user.setActive(resultSet.getBoolean("active"));
                user.setTechnicalAnalysisMethod(AutoTrade.getTAMI(resultSet.getInt("technical_analysis_method_instance_id")));
            }

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return user;
    }

        public static User getUserByID(int userID) {
        User user = new User();

        try {
            Connection conn = AutoTradeDatabaseManagement.getConnectionWithDatabase();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * "
                    + "FROM `user` "
                    + "WHERE `id` = '" + userID + "'");

            while (resultSet.next()) {
                user.setUserID(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("name"));
                user.setTypeID(resultSet.getInt("type_id"));
                user.setTypeName(AutoTrade.getUserTypeName(user.getTypeID()));
                user.setInital_cash(resultSet.getDouble("inital_cash"));
                user.setCash_remain(resultSet.getDouble("cash_remain"));
                user.setActive(resultSet.getBoolean("active"));
                user.setTechnicalAnalysisMethod(AutoTrade.getTAMI(resultSet.getInt("technical_analysis_method_instance_id")));
            }

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return user;
    }

    public static ArrayList<User> getAllUsers() {
        ArrayList<User> allUser = new ArrayList<User>();

        try {
            Connection conn = AutoTradeDatabaseManagement.getConnectionWithDatabase();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * "
                    + "FROM `user`");

            while (resultSet.next()) {
                User user = new User();
                user.setUserID(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("name"));
                user.setTypeID(resultSet.getInt("type_id"));
                user.setTypeName(AutoTrade.getUserTypeName(user.getTypeID()));
                user.setInital_cash(resultSet.getDouble("inital_cash"));
                user.setCash_remain(resultSet.getDouble("cash_remain"));
                user.setActive(resultSet.getBoolean("active"));
                user.setTechnicalAnalysisMethod(AutoTrade.getTAMI(resultSet.getInt("technical_analysis_method_instance_id")));
               
                user.listUserPortfolios = UserPortfolio.getListUserPortfolios(user.getUserID());
                user.calStockValue();
                user.listcenterDateOrders = Order.getListOrders(user.getUserID(), AutoTradeLocalData.load().getCenter_date().getTime());
                user.listcurrentDateOrders = user.getTechnicalAnalysisMethod().getOrders(AutoTradeLocalData.load().getCurrentDate(), user.getUserID());

                allUser.add(user);
            }

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return allUser;
    }

    public void calStockValue() {
        stock_value = 0;
        for (UserPortfolio userPortfolio : listUserPortfolios) {
            stock_value += StockInfoDaily.getStockInfo(userPortfolio.getSymbol(), AutoTradeLocalData.load().getCurrentDate().getTime()).getClosePrice()*userPortfolio.getVolume();
        }
    }

    public static Object[][] getUserTableData() {
        Object[][] data = new Object[User.LIST_ALL_USER.size()][6];

        for (int i = 0; i < User.LIST_ALL_USER.size(); ++i) {
            User user = User.LIST_ALL_USER.get(i);
            data[i][0] = new Integer(user.getUserID());
            data[i][1] = user.getUserName();
            data[i][2] = user.getTypeName();
            data[i][3] = user.getTechnicalAnalysisMethod().getName();
            data[i][4] = new Double(user.getCash_remain());
            data[i][5] = user.isActive();
        }

        return data;
    }
}
