/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package autotrade.core.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Dinh
 */
public class AutoTradeDatabaseManagement {
    private static String url = "jdbc:mysql://localhost:3306/";
    private static String dbName = "autotradeproject_v1";
    private static String userName = "root";
    private static String password = "YES";

    public static Connection getConnectionWithDatabase() {
        try {
            return DriverManager.getConnection(url + dbName, userName, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
