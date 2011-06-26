package business.virtualTrading;

import java.util.ArrayList;

import dataAccess.databaseManagement.entity.UserEntity;
import dataAccess.databaseManagement.manager.UserManager;

public class UserList {
	ArrayList<User> userList;
	
	public UserList() {
		UserManager userManager = new UserManager();
		userList = new ArrayList<User>();
		ArrayList<UserEntity> userEntityList = userManager.getAllUsers();
		for (int i = 0; i < userEntityList.size(); i++) {
			userList.add(new User(userEntityList.get(i)));
		}
	}
	
	public ArrayList<User> getUserList() {
		return userList;
	}
}
