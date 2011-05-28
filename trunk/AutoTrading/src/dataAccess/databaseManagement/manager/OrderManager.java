package dataAccess.databaseManagement.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dataAccess.databaseManagement.ConnectionFactory;
import dataAccess.databaseManagement.entity.OrderEntity;

public class OrderManager {
	private Connection connection = null;
	private PreparedStatement ptmt = null;
	private ResultSet resultSet = null;

	public OrderManager() {
		
	}

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}

	public void add(OrderEntity orderEntity) {
		String queryString = "INSERT INTO order(order_id, order_type, user_id, date, asset_id, price, volume, matched) VALUES(?,?,?,?,?,?,?,?)";
		try {
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
			ptmt.setNull(1, java.sql.Types.INTEGER);
			ptmt.setBoolean(2, orderEntity.getOrderType());
			ptmt.setInt(3, orderEntity.getUserID());
			ptmt.setDate(4, orderEntity.getDate());
			ptmt.setInt(5, orderEntity.getAssetID());
			ptmt.setDouble(6, orderEntity.getPrice());
			ptmt.setDouble(7, orderEntity.getVolume());
			ptmt.setBoolean(8, orderEntity.isMatched());
			ptmt.executeUpdate();
			
			ResultSet rs = ptmt.getGeneratedKeys();
			int autoIncValue = -1;
			
			if (rs.next()) {
				autoIncValue = rs.getInt(1);
			}
			
			orderEntity.setOrderID(autoIncValue);
			
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

	public void update(OrderEntity orderEntity) {
		try {
			String queryString = "UPDATE order SET order_type=?, user_id=?, date=?, asset_id=?, price=?, volume=?, matched=? WHERE order_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setBoolean(1, orderEntity.getOrderType());
			ptmt.setInt(2, orderEntity.getUserID());
			ptmt.setDate(3, orderEntity.getDate());
			ptmt.setInt(4, orderEntity.getAssetID());
			ptmt.setDouble(5, orderEntity.getPrice());
			ptmt.setDouble(6, orderEntity.getVolume());
			ptmt.setBoolean(7, orderEntity.isMatched());
			ptmt.setInt(8, orderEntity.getOrderID());
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

	public void delete(int orderID) {
		try {
			String queryString = "DELETE FROM order WHERE order_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, orderID);
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

	public OrderEntity getOrderByID(int orderID) {
		try {
			OrderEntity orderEntity = null;
			
			String queryString = "SELECT * FROM order WHERE order_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, orderID);
			resultSet = ptmt.executeQuery();
			
			if (resultSet.next()) {
				orderEntity = new OrderEntity();
				orderEntity.setOrderID(orderID);
				orderEntity.setOrderType(resultSet.getBoolean("order_type"));
				orderEntity.setUserID(resultSet.getInt("user_id"));
				orderEntity.setDate(resultSet.getDate("date"));
				orderEntity.setAssetID(resultSet.getInt("asset_id"));
				orderEntity.setPrice(resultSet.getDouble("price"));
				orderEntity.setVolume(resultSet.getDouble("volume"));
				orderEntity.setMatched(resultSet.getBoolean("matched"));
			}
			
			return orderEntity;
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
	
	public ArrayList<OrderEntity> getAllOrders() {
		try {
			ArrayList<OrderEntity> listAllOrders = new ArrayList<OrderEntity>();
			
			String queryString = "SELECT * FROM order";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			resultSet = ptmt.executeQuery();
			
			while (resultSet.next()) {
				OrderEntity orderEntity = new OrderEntity();

				orderEntity.setOrderID(resultSet.getInt("order_id"));
				orderEntity.setOrderType(resultSet.getBoolean("order_type"));
				orderEntity.setUserID(resultSet.getInt("user_id"));
				orderEntity.setDate(resultSet.getDate("date"));
				orderEntity.setAssetID(resultSet.getInt("asset_id"));
				orderEntity.setPrice(resultSet.getDouble("price"));
				orderEntity.setVolume(resultSet.getDouble("volume"));
				orderEntity.setMatched(resultSet.getBoolean("matched"));
				
				listAllOrders.add(orderEntity);
			}
			
			return listAllOrders;
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
