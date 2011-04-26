package dataAccess.databaseManagement.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dataAccess.databaseManagement.ConnectionFactory;
import dataAccess.databaseManagement.entity.ExchangeEntity;

public class ExchangeManager {
	private Connection connection = null;
	private PreparedStatement ptmt = null;
	private ResultSet resultSet = null;

	public ExchangeManager() {
	}

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}

	public void add(ExchangeEntity exchangeEntity) {
		String queryString = "INSERT INTO exchange(name, fluctuation_range) VALUES(?,?)";
		try {
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, exchangeEntity.getName());
			ptmt.setDouble(2, exchangeEntity.getFluctuation_range());
			ptmt.executeUpdate();
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

	public void update(ExchangeEntity exchangeEntity) {
		try {
			String queryString = "UPDATE exchange SET fluctuation_range=? WHERE name=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setDouble(1, exchangeEntity.getFluctuation_range());
			ptmt.setString(2, exchangeEntity.getName());
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

	public void delete(String name) {
		try {
			String queryString = "DELETE FROM exchange WHERE name=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, name);
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

	public ExchangeEntity getExchangeByName(String name) {
		try {
			ExchangeEntity exchangeEntity = new ExchangeEntity();

			String queryString = "SELECT * FROM exchange WHERE name=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, name);
			resultSet = ptmt.executeQuery();

			while (resultSet.next()) {
				exchangeEntity.setName(name);
				exchangeEntity.setFluctuation_range(resultSet
						.getDouble("fluctuation_range"));
			}

			return exchangeEntity;
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

	public ArrayList<ExchangeEntity> getAllExchangeEntities() {
		try {
			ArrayList<ExchangeEntity> listAllExchangeEntities = new ArrayList<ExchangeEntity>();

			String queryString = "SELECT * FROM exchange";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			resultSet = ptmt.executeQuery();

			while (resultSet.next()) {
				ExchangeEntity exchangeEntity = new ExchangeEntity();

				exchangeEntity.setName(resultSet.getString("name"));
				exchangeEntity.setFluctuation_range(resultSet
						.getDouble("fluctuation_range"));

				listAllExchangeEntities.add(exchangeEntity);
			}

			return listAllExchangeEntities;
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
