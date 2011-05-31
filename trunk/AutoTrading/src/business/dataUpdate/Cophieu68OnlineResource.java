package business.dataUpdate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cophieu68OnlineResource extends OnlineResource {
	public HttpURLConnection uc;
	
	public Cophieu68OnlineResource(Date date) throws Exception {
		// TODO Auto-generated constructor stub
		description = "www.cophieu68.com";
		
		String fileLink = "http://www.cophieu68.com/export/dailymetastock.php?stcid=1&amp;date=";
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		String fileName = dateFormat.format(date);
		fileLink.concat(fileName);
		
		initConnection(fileLink);
	} 
	private void initConnection(String fileLink) throws Exception
	{
		URL url = new URL(fileLink);
		uc = (HttpURLConnection) url.openConnection();
		uc.setRequestProperty("User-Agent", "");
	}
}
