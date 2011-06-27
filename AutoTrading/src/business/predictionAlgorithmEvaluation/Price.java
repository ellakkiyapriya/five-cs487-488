package business.predictionAlgorithmEvaluation;

import java.util.Date;

public class Price {
	Double price;
	Date date;
	public Price(Double price, Date date) {
		this.price = price;
		this.date = date;
	}
	
	
	public Date getDate() {
		return date;
	}
	
	public Double getPrice() {
		return price;
	}
}
