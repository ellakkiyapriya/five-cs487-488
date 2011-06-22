package business.dataUpdate.DataGetter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import business.dataUpdate.DataProcessor.ParamForCophieu68DataProcessor;
import Utility.ParamList;

public class Cophieu68DataGetter extends AbstractDataGetter {

	@Override
	public ParamList getData(ParamList parameter) {
		// TODO Auto-generated method stub
		HttpURLConnection uc = initConnection(); 
		BufferedReader br;
		try 
		{
			br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			ParamList paramList = new ParamForCophieu68DataProcessor(br, (Date)((ParamForCophieu68DataGetter)parameter).getDate());
			return paramList;
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public HttpURLConnection initConnection()
	{
		try
		{
			Date date = new Date();
			String fileLink = "http://www.cophieu68.com/export/dailymetastock.php?stcid=1&amp;date=";
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String fileName = dateFormat.format(date);
			fileLink.concat(fileName);
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
