package business.dataUpdate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;

public class DataStream{
	public BufferedReader br;
	public DataStream(BufferedReader br1) {
		// TODO Auto-generated constructor stub
		br = br1;
	}
	public DataStream(String fileName)
	{
		try 
		{
			br = new BufferedReader( new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
