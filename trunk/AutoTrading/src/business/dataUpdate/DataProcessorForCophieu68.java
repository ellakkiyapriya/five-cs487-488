package business.dataUpdate;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DataProcessorForCophieu68 extends DataProcessor {
	
	@Override
	protected void processData(InputForDataProcessor input) {
		
		// TODO Auto-generated method stub
		double open, high, low, close;
		int volume;
		DateFormat dateFormat2 = new SimpleDateFormat("yyyyMMdd");
		String str, stockCode;
		String currentDate = dateFormat2.format(((DataStream)input).date);
		try {
			// Open an output stream
			String[] splitString;
			((DataStream)input).br.readLine();
			while ((str = ((DataStream)input).br.readLine()) != null) {
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
					//addNewStockCode(stockCode,dateFormat3.format(date),open, high, low, close, volume);
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

	}

}
