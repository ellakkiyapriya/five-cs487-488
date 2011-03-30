/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package autotrade.core.database;

import autotrade.core.AutoTrade;
import autotrade.core.AutoTradeLocalData;
import autotrade.core.algorithm.Algorithm;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

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
    private Algorithm technicalAnalysisMethod;
    private double inital_cash;
    private double cash_remain;
    private double stock_value;
    private boolean active;
    private long currentDateTime;
    private ArrayList<UserPortfolio> listUserPortfolios;
    private ArrayList<Order> listcenterDateOrders;
    private ArrayList<Order> listcurrentDateOrders;

    public User() {
        currentDateTime = AutoTradeLocalData.load().getCurrentDate().getTime();
    }

    public long getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(long currentDate) {
        this.currentDateTime = currentDate;
    }

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

    public Algorithm getTechnicalAnalysisMethod() {
        return technicalAnalysisMethod;
    }

    public void setTechnicalAnalysisMethod(Algorithm technicalAnalysisMethod) {
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
            Statement statement = AutoTrade.conn.createStatement();

            String sqlStatement = "INSERT user VALUES(";
            sqlStatement += "NULL ,";
            sqlStatement += "'" + userName + "',";
            sqlStatement += "'" + typeId + "',";
            sqlStatement += "'" + tamiID + "',";
            sqlStatement += "'" + initialCash + "',";
            sqlStatement += "'" + initialCash + "',";
            sqlStatement += "'" + 1 + "');";

            statement.executeUpdate(sqlStatement);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void removeUser(int userID) {
        try {
            Statement statement = AutoTrade.conn.createStatement();
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

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void saveUserInfo(User user) {
        try {
            Statement statement = AutoTrade.conn.createStatement();

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

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static int getGreatestIDUser() {
        int greatestID = 0;

        try {
            Statement statement = AutoTrade.conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT max(id) FROM user");
            resultSet.next();
            greatestID = resultSet.getInt(1);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return greatestID;
    }

        public static User getUserByName(String userName) {
        User user = new User();

        try {
            Statement statement = AutoTrade.conn.createStatement();
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

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return user;
    }

        public static User getUserByID(int userID) {
        User user = new User();

        try {
            Statement statement = AutoTrade.conn.createStatement();
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

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return user;
    }

    public static ArrayList<User> getAllUsers() {
        ArrayList<User> allUser = new ArrayList<User>();

        try {
            Statement statement = AutoTrade.conn.createStatement();
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

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return allUser;
    }

    public void calStockValue() {
        stock_value = 0;
        for (UserPortfolio userPortfolio : listUserPortfolios) {
            stock_value += StockInfoDaily.getStockInfo(userPortfolio.getSymbol(), currentDateTime).getClosePrice()*userPortfolio.getVolume();
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

    public void updateUserInfoCurrentDateChange() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentDateTime);

        while (calendar.getTime().before(AutoTradeLocalData.load().getCurrentDate())) {
            for (Order order : listcurrentDateOrders) {
                if (order.getOrder_type() == Order.SELL) {
                    for (UserPortfolio userPortfolio : listUserPortfolios) {
                        if (userPortfolio.getSymbol().equals(order.getSymbol())) {
                            listUserPortfolios.remove(userPortfolio);
                        }
                    }
                    this.cash_remain += order.getPrice()*order.getVolume();
                    Order.addOrder(order);
                }
            }

            for (Order order : listcurrentDateOrders) {
                if (order.getOrder_type() == Order.BUY) {
                    boolean check = true;
                    for (UserPortfolio userPortfolio : listUserPortfolios) {
                        if (userPortfolio.getSymbol().equals(order.getSymbol())) {
                            check = false;
                            userPortfolio.setVolume(userPortfolio.getVolume() + order.getVolume());
                            double buyprice = (userPortfolio.getBuy_price()*userPortfolio.getVolume() + order.getPrice()*order.getVolume())/(userPortfolio.getVolume() + order.getVolume());
                            userPortfolio.setBuy_price(buyprice);
                        }
                    }

                    if (check) {
                        UserPortfolio userPortfolio = new UserPortfolio();
                        userPortfolio.setPortfolio_id(-1);
                        userPortfolio.setSymbol(order.getSymbol());
                        userPortfolio.setUser_id(this.userID);
                        userPortfolio.setBuy_price(order.getPrice());
                        userPortfolio.setVolume(order.getVolume());
                        listUserPortfolios.add(userPortfolio);
                    }

                    this.cash_remain -= order.getPrice()*order.getVolume();
                    Order.addOrder(order);
                }
            }

            for (UserPortfolio userPortfolio : listUserPortfolios) {
                if (userPortfolio.getPortfolio_id() == -1) {
                    UserPortfolio.addUserPortfolios(userPortfolio);
                } else {
                    UserPortfolio.updateUserPortfolios(userPortfolio);
                }
            }

            calendar.add(Calendar.DAY_OF_MONTH, 1);
            listcurrentDateOrders = technicalAnalysisMethod.getOrders(calendar.getTime(), userID);
        }

        currentDateTime = AutoTradeLocalData.load().getCurrentDate().getTime();
        this.calStockValue();
    }

    public void updateUserInfoCenterDateChange() {
        listcenterDateOrders = Order.getListOrders(userID, AutoTradeLocalData.load().getCenter_date().getTime());
    }

}
