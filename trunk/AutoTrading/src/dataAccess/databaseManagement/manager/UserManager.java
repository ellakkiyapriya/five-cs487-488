package dataAccess.databaseManagement.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dataAccess.databaseManagement.ConnectionFactory;
import dataAccess.databaseManagement.entity.UserEntity;

public class UserManager {
	private Connection connection = null;
	private PreparedStatement ptmt = null;
	private ResultSet resultSet = null;

	public UserManager() {
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}

	public void add(UserEntity userEntity) {
		String queryString = "INSERT INTO user(user_id, name, cash) VALUES(?,?,?)";
		try {
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
			ptmt.setNull(1, java.sql.Types.INTEGER);
			ptmt.setString(2, userEntity.getName());
			ptmt.setDouble(3, userEntity.getCash());
			ptmt.executeUpdate();
			
			ResultSet rs = ptmt.getGeneratedKeys();
			long autoIncValue = -1;
			
			if (rs.next()) {
				autoIncValue = rs.getLong(1);
			}
			
			userEntity.setUserID(autoIncValue);
			
			System.out.println("Data Added Successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ptmt != null) {
					ptmt.close();
				}

				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void update(UserEntity userEntity) {
		try {
			String queryString = "UPDATE user SET name=?, cash=? WHERE user_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, userEntity.getName());
			ptmt.setDouble(2, userEntity.getCash());
			ptmt.setLong(3, userEntity.getUserID());
			ptmt.executeUpdate();
			System.out.println("Table Updated Successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			}

			catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}

	public void delete(long userID) {
		try {
			String queryString = "DELETE FROM user WHERE user_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setLong(1, userID);
			ptmt.executeUpdate();
			System.out.println("Data deleted Successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			}

			catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}

	public UserEntity getUserByID(long userID) {
		try {
			UserEntity userEntity = null;

			String queryString = "SELECT * FROM user WHERE user_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setLong(1, userID);
			resultSet = ptmt.executeQuery();

			if (resultSet.next()) {
				userEntity = new UserEntity();
				userEntity.setUserID(userID);
				userEntity.setName(resultSet.getString("name"));
				userEntity.setCash(resultSet
						.getDouble("cash"));
			}

			return userEntity;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return null;
	}

	public ArrayList<UserEntity> getAllUsers() {
		try {
			ArrayList<UserEntity> listAllUsers = new ArrayList<UserEntity>();

			String queryString = "SELECT * FROM user";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			resultSet = ptmt.executeQuery();

			while (resultSet.next()) {
				UserEntity userEntity = new UserEntity();

				userEntity.setUserID(resultSet.getLong("user_id"));
				userEntity.setName(resultSet.getString("name"));
				userEntity.setCash(resultSet
						.getDouble("cash"));

				listAllUsers.add(userEntity);
			}

			return listAllUsers;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return null;
	}

}
