package business.virtualTrading;

import dataAccess.databaseManagement.entity.UserEntity;
import dataAccess.databaseManagement.manager.UserManager;

/**
 * Class Name: User
 * @version 1.1
 * @date  June 1, 2011
 * @author Xuan Ngoc
 */

public class User {
	UserEntity user;
	
	/**
	 * Constructor
	 * <li> userID is automatically created
	 */
	public User(String name, double cash) {
		user = new UserEntity(name, cash);
	}
	
	/**
	 * Constructor
	 * get user's information from database by userID
	 */
	public User(long userID) {
		UserManager userManager = new UserManager();
		user = userManager.getUserByID(userID);
	}
	
	public long getUserID() {
		return user.getUserID();
	}
	
	/**
	 * Add a user to database.
	 * <li> Note: this method update database
	 */
	public long add() {
		UserManager userManager = new UserManager();
		userManager.add(this.user);
		return this.user.getUserID();
	}
	
	/**
	 * Add cash to a user 
	 * <li> Note: this method update database
	 */
	public void addCash(double cash) {
		UserManager userManager = new UserManager();
		user.setCash(user.getCash()+ cash); 
		userManager.update(user);
	}

	
	public static void main(String args[]) {
		
		User uM = new User("u1.3", 1222);
		uM.add();
		
		System.out.println(uM.getUserID());
	}

}
