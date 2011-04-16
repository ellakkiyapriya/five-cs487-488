package autotrade.core.importdata;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;
import autotrade.core.virtualsystem.AutoTrade;
import java.util.*;
import java.text.*;

public class DailyUpdate {
	public static void addNewStockCode(String stockCode, String date, double open, double high, double low, double close, int volume) {
        try {
            Statement statement = AutoTrade.conn.createStatement();

            String sqlStatement = "INSERT stock_price_daily VALUES(";
            sqlStatement += "'" + stockCode + "',";
            sqlStatement += "'" + date + "',";
            sqlStatement += "'" + open + "',";
            sqlStatement += "'" + high + "',";
            sqlStatement += "'" + low + "',";
            sqlStatement += "'" + close + "',";
            sqlStatement += "'" + volume + "');";
            statement.executeUpdate(sqlStatement);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
	public static void run() {
		try {
			// System.setProperty("http.proxyHost","myproxy");
			// System.setProperty("http.proxyPort", "myport");
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String fileLink = "http://www.cophieu68.com/export/dailymetastock.php?stcid=1&amp;date=";
			Date date = new Date();
			String fileName = dateFormat.format(date);
			fileLink.concat(fileName);
			URL url = new URL(fileLink);

			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			uc.setRequestProperty("User-Agent", "");
			
			// Read all the text returned by the server
			BufferedReader in = new BufferedReader(new InputStreamReader(uc
					.getInputStream()));
			String str;
			DateFormat dateFormat2 = new SimpleDateFormat("yyyyMMdd");
			DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");

			String currentDate = dateFormat2.format(date);
			String stockCode;
			try {
				Date toDate = dateFormat.parse(fileName);
				
				double open, high, low, close;
				int volume;
				try {
					// Open an output stream
					String[] splitString;
					in.readLine();
					while ((str = in.readLine()) != null) {
						// str is one line of text; readLine() strips the newline
						// character(s)

						// Print a line of text
						splitString = str.split(",");
						if (splitString[1].contentEquals(currentDate))
						{
							stockCode = splitString[0];
							open = Double.valueOf(splitString[2]);
							high = Double.valueOf(splitString[3]);
							low = Double.valueOf(splitString[4]);
							close = Double.valueOf(splitString[5]);
							volume = Integer.valueOf(splitString[6]);
							addNewStockCode(stockCode,dateFormat3.format(date),open, high, low, close, volume);
							for (int i = 0; i < splitString.length; i ++)
								System.out.println(splitString[i]);	
						}
						
					}
				}
				// Catches any error conditions
				catch (IOException e) {
					System.err.println("Unable to write to file");
					System.exit(-1);
				}
				
			} catch (ParseException e) {	
				e.printStackTrace();	
			}
			
			
			in.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	

}
