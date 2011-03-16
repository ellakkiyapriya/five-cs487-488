import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
/**
 *  Date get from http://www.cophieu68.com/
 *  Current version working with no proxy
 *  Output filename is "dd-MM-yyyy" with format
    <Ticker>,<DTYYYYMMDD>,<Open>,<High>,<Low>,<Close>,<Volume>
	AAM,20110316,19.2,19.8,19.2,19.8,20900
 */
public class AutoUpdateStock {
	public static void main(String[] args) {
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

			FileOutputStream fout; // Stream to write file
			try {
				// Open an output stream
				fout = new FileOutputStream(fileName);
				while ((str = in.readLine()) != null) {
					// str is one line of text; readLine() strips the newline
					// character(s)

					// Print a line of text
					new PrintStream(fout).println(str);

				}
				// Close our output stream
				fout.close();
			}
			// Catches any error conditions
			catch (IOException e) {
				System.err.println("Unable to write to file");
				System.exit(-1);
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
