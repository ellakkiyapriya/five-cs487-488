package business.dataUpdate.DataGetter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class YahooStockQuoteDataGetter extends AbstractDataGetter {

	public YahooStockQuoteDataGetter() {
		super(null, null);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BufferedReader getData() {
		// TODO Auto-generated method stub
		HttpURLConnection uc = initConnection(); 
		try 
		{
			return new BufferedReader(new InputStreamReader(uc.getInputStream()));
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public HttpURLConnection initConnection()
	{
		try
		{
			String fileLink =  null;
			
			fileLink = "http://www.cophieu68.com/export/dailymetastock.php?stcid=2&date=";
			
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String fileName = dateFormat.format(date);
			fileLink = fileLink.concat(fileName);
			URL url = new URL(fileLink);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			uc.setRequestProperty("User-Agent", "");
			return uc;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
