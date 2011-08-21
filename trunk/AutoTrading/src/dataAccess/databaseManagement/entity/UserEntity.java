package dataAccess.databaseManagement.entity;

import java.io.Serializable;

public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8627210132728983694L;

	private long userID;
	private String name;
	private double cash;

	public UserEntity() {

	}

	public UserEntity(String name, double cash) {
		this.name = name;
		this.cash = cash;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public long getUserID() {
		return userID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public double getCash() {
		return cash;
	}

}
