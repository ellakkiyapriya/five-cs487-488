package business.dataUpdate.DataGetter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import business.dataUpdate.Cophieu68OnlineResource;
import business.dataUpdate.DataStream;
import business.dataUpdate.OnlineResource;

public class DataGetterForCophieu68 extends DataGetter {

	@Override
	public DataStream getData(OnlineResource or) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(((Cophieu68OnlineResource)or).uc.getInputStream()));
		return new DataStream(in);
	}

	@Override
	public OnlineResource initOnlineResource() throws Exception {
		// TODO Auto-generated method stub
		Cophieu68OnlineResource cophieu68 = new Cophieu68OnlineResource(new Date());
		return cophieu68;
	}
	
}
