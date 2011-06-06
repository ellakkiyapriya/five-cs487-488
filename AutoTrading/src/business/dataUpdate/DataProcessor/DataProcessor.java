package business.dataUpdate.DataProcessor;

import business.dataUpdate.DataStream;

public abstract class DataProcessor {
	protected abstract void processData(DataStream input);
}
