package business.dataUpdate;

public abstract class DataGetter {
	/* Input of getData method is an OnlineResource object
	 * It will return an object of type InputForDataProcessor*/
	protected abstract InputForDataProcessor getData(OnlineResource or) throws Exception;
	protected abstract OnlineResource initOnlineResource() throws Exception;
}
