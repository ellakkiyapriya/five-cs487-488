package business.dataUpdate.DataGetter;

import business.dataUpdate.InputForDataProcessor;
import business.dataUpdate.OnlineResource;

public abstract class DataGetter {
	/* Input of getData method is an OnlineResource object
	 * It will return an object of type InputForDataProcessor*/
	public abstract InputForDataProcessor getData(OnlineResource or) throws Exception;
	public abstract OnlineResource initOnlineResource() throws Exception;
}
