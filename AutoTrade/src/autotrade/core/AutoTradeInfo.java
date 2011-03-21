/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package autotrade.core;

import autotrade.core.database.AutoTradeDatabaseManagement;
import java.sql.*;
import org.jfree.data.time.*;
import java.util.ArrayList;

/**
 *
 * @author Dinh
 */
public class AutoTradeInfo {

    public static ArrayList<String> LIST_ALL_JSC_SYMBOL = getAllJSCSymbol();

    public static long getLatestTimeInDatabase() {
        long latestTime = 0;

        try {
            Connection conn = AutoTradeDatabaseManagement.getConnectionWithDatabase();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT max(date) FROM stock_price_daily");
            resultSet.next();
            Date date = resultSet.getDate(1);
            latestTime = date.getTime();
            
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return latestTime;
    }

    public static long getEarliestTimeInDatabase() {
        long earliestTime = 0;

        try {
            Connection conn = AutoTradeDatabaseManagement.getConnectionWithDatabase();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT min(date) FROM stock_price_daily");
            resultSet.next();
            Date date = resultSet.getDate(1);
            earliestTime = date.getTime();

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return earliestTime;
    }


    public static ArrayList<String> getAllJSCSymbol() {
        ArrayList<String> allJSCSymbol = new ArrayList<String>();

        try {
            Connection conn = AutoTradeDatabaseManagement.getConnectionWithDatabase();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM joint_stock_company");
            while (resultSet.next()) {
                String symbol = resultSet.getString("symbol");
                allJSCSymbol.add(symbol);
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return allJSCSymbol;
    }

    public static TimeSeries getPriceSeries(long startTime, long endTime, String companySymbol, String priceType) {
        Date startDate = new Date(startTime);
        Date currentDate = new Date(endTime);

        TimeSeries priceSeries = new TimeSeries("Price Series");
        try {
            Connection conn = AutoTradeDatabaseManagement.getConnectionWithDatabase();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * "
                    + "FROM `stock_price_daily`"
                    + "WHERE `symbol` LIKE '" + companySymbol + "' "
                    + "AND `date` <= '" + currentDate.toString() + "' "
                    + "AND `date` >= '" + startDate.toString() + "'");

            while (resultSet.next()) {
                Double price = resultSet.getDouble(priceType);
                Date date = resultSet.getDate("date");
                priceSeries.add(new Day(date), price);
            }

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return priceSeries;
    }

    public static ArrayList<String> getAllJSCSymbolAtThisTime(Date current_date) {
        ArrayList<String> allJSCSymbol = new ArrayList<String>();

        try {
            Connection conn = AutoTradeDatabaseManagement.getConnectionWithDatabase();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM stock_price_daily WHERE date LIKE '" + current_date.toString() + "'");
            while (resultSet.next()) {
                String symbol = resultSet.getString("symbol");
                allJSCSymbol.add(symbol);
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return allJSCSymbol;
    }

    public static TimeSeries getVolumeSeries(long startTime, long endTime, String companySymbol) {
        Date startDate = new Date(startTime);
        Date currentDate = new Date(endTime);

        TimeSeries volumeSeries = new TimeSeries("Volume");
        try {
            Connection conn = AutoTradeDatabaseManagement.getConnectionWithDatabase();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * "
                    + "FROM `stock_price_daily`"
                    + "WHERE `symbol` LIKE '" + companySymbol + "' "
                    + "AND `date` <= '" + currentDate.toString() + "' "
                    + "AND `date` >= '" + startDate.toString() + "'");

//            Double volume1, volume2;
//            Date date1, date2;
//            long oneDay = 86400000; //milisecond
//
//            if (resultSet.next()) {
//                volume1 = resultSet.getDouble("volume");
//                date1 = resultSet.getDate("date");
//                volumeSeries.add(new Day(date1), volume1);
//
//                while (resultSet.next()) {
//                    volume2 = resultSet.getDouble("volume");
//                    date2 = resultSet.getDate("date");
//                    for (Date date = new Date(date1.getTime() + oneDay); date.compareTo(date2) < 0; date = new Date(date.getTime() + oneDay)) {
//                        volumeSeries.add(new Day(date), volume1);
//                    }
//
//                    volumeSeries.add(new Day(date2), volume2);
//                    date1 = date2;
//                    volume1 = volume2;
//                }
//            }

            Double volume;
            Date date;

            while (resultSet.next()) {
                volume = resultSet.getDouble("volume");
                date = resultSet.getDate("date");
                volumeSeries.add(new Day(date), volume);
            }

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return volumeSeries;
    }
    
    public static ArrayList<String> getListUserTypeName() {
        ArrayList<String> listUserTypeName = new ArrayList<String>();
        
        try {
            Connection conn = AutoTradeDatabaseManagement.getConnectionWithDatabase();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * "
                    + "FROM `user_type`");

            while (resultSet.next()) {
                String userTypeName = resultSet.getString("user_type_name");
                listUserTypeName.add(userTypeName);
            }

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return listUserTypeName;
    }

        public static ArrayList<String> getListTechnicalAnalysisMethod() {
        ArrayList<String> listTechnicalAnalysisMethod = new ArrayList<String>();

        try {
            Connection conn = AutoTradeDatabaseManagement.getConnectionWithDatabase();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * "
                    + "FROM `technical_analysis_method`");

            while (resultSet.next()) {
                String userTypeName = resultSet.getString("name");
                listTechnicalAnalysisMethod.add(userTypeName);
            }

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listTechnicalAnalysisMethod;
    }

}