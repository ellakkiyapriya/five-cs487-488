package business.dataUpdate.DataGetter;

import business.dataUpdate.DataStream;
import business.dataUpdate.OnlineResource;

public abstract class DataGetter {
	/* Input of getData method is an OnlineResource object
	 * It will return an object of type InputForDataProcessor*/
	public abstract DataStream getData(OnlineResource or) throws Exception;
	public abstract OnlineResource initOnlineResource() throws Exception;
}
