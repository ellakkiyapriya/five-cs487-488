package business.dataUpdate;

import java.io.BufferedReader;
import java.util.Date;

public class DataStream{
	public BufferedReader br;
	public Date date;
	public DataStream(BufferedReader br1, Date date) {
		// TODO Auto-generated constructor stub
		br = br1;
		this.date = date;
	}
}
