package business.dataUpdate.DataProcessor;

import java.io.BufferedReader;
import java.util.Date;

import Utility.ParamList;

public class ParamForCophieu68DataProcessor extends ParamList {
	private BufferedReader br;
	private String exchangeName;
	private Date date;
	public ParamForCophieu68DataProcessor(BufferedReader br, Date date, String exchangeName) {
		super();
		this.br = br;
		this.date = date;
		this.exchangeName = exchangeName;
		numOfParam = 3;
		// TODO Auto-generated constructor stub
	}
	public BufferedReader getBr() {
		return br;
	}
	public void setBr(BufferedReader br) {
		this.br = br;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getExchangeName() {
		return exchangeName;
	}
	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}
	
}
