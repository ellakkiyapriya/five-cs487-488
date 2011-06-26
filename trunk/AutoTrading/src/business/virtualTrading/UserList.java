package business.virtualTrading;

import java.util.ArrayList;

import dataAccess.databaseManagement.entity.PortfolioEntity;
import dataAccess.databaseManagement.entity.UserEntity;
import dataAccess.databaseManagement.manager.PortfolioManager;
import dataAccess.databaseManagement.manager.UserManager;

public class UserList {
	ArrayList<User> userList;
	
	public UserList() {
		UserManager userManager = new UserManager();
		PortfolioManager portfolioManager = new PortfolioManager();
		userList = new ArrayList<User>();
		UserEntity curUser;
		
		ArrayList<UserEntity> userEntityList = userManager.getAllUsers();
		for (int i = 0; i < userEntityList.size(); i++) {
			curUser = userEntityList.get(i);
			ArrayList<PortfolioEntity> portfolioEntityList = portfolioManager
					.getPortfolioByDateAndUserID(curUser.getUserID(),
							portfolioManager
									.getPortfolioLatestDateOfUserID(curUser
											.getUserID()));

			userList.add(new User(curUser, portfolioEntityList));
		}
	}
	
	public ArrayList<User> getUserList() {
		return userList;
	}
	
	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}
}
