package business.algorithm.predictAlgorithm;

public class Price {
	private long assetID;
	private double price;
	public Price(long assetID, double price) {
		super();
		this.assetID = assetID;
		this.price = price;
	}
	public long getAssetID() {
		return assetID;
	}
	public void setAssetID(long assetID) {
		this.assetID = assetID;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
