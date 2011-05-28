package dataAccess.databaseManagement.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dataAccess.databaseManagement.ConnectionFactory;
import dataAccess.databaseManagement.entity.PortfolioEntity;

public class PortfolioManager {
	private Connection connection = null;
	private PreparedStatement ptmt = null;
	private ResultSet resultSet = null;

	public PortfolioManager() {

	}

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}

	public void add(PortfolioEntity portfolioEntity) {
		String queryString = "INSERT INTO portfolio(portfolio_id, user_id, asset_id, price, volume, date) VALUES(?,?,?,?,?,?)";
		try {
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
			ptmt.setNull(1, java.sql.Types.INTEGER);
			ptmt.setInt(2, portfolioEntity.getUserID());
			ptmt.setInt(3, portfolioEntity.getAssetID());
			ptmt.setDouble(4, portfolioEntity.getPrice());
			ptmt.setDouble(5, portfolioEntity.getVolume());
			ptmt.setDate(6, portfolioEntity.getDate());
			ptmt.executeUpdate();
			
			ResultSet rs = ptmt.getGeneratedKeys();
			int autoIncValue = -1;
			
			if (rs.next()) {
				autoIncValue = rs.getInt(1);
			}
			
			portfolioEntity.setPortfolioID(autoIncValue);
			
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

	public void update(PortfolioEntity portfolioEntity) {
		try {
			String queryString = "UPDATE portfolio SET user_id=?, asset_id=?, price=?, volume=?, date=? WHERE portfolio_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, portfolioEntity.getUserID());
			ptmt.setInt(2, portfolioEntity.getAssetID());
			ptmt.setDouble(3, portfolioEntity.getPrice());
			ptmt.setDouble(4, portfolioEntity.getVolume());
			ptmt.setDate(5, portfolioEntity.getDate());
			ptmt.setInt(6, portfolioEntity.getPortfolioID());
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

	public void delete(int portfolioID) {
		try {
			String queryString = "DELETE FROM portfolio WHERE portfolio_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, portfolioID);
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

	public PortfolioEntity getPortfolioByID(int portfolioID) {
		try {
			PortfolioEntity portfolioEntity = null;

			String queryString = "SELECT * FROM portfolio WHERE portfolio_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, portfolioID);
			resultSet = ptmt.executeQuery();

			if (resultSet.next()) {
				portfolioEntity = new PortfolioEntity();
				portfolioEntity.setPortfolioID(portfolioID);
				portfolioEntity.setUserID(resultSet.getInt("user_id"));
				portfolioEntity.setAssetID(resultSet.getInt("asset_id"));
				portfolioEntity.setPrice(resultSet.getDouble("price"));
				portfolioEntity.setVolume(resultSet.getDouble("volume"));
				portfolioEntity.setDate(resultSet.getDate("date"));
			}

			return portfolioEntity;
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

	public ArrayList<PortfolioEntity> getAllPortfolios() {
		try {
			ArrayList<PortfolioEntity> listAllPortfolios = new ArrayList<PortfolioEntity>();

			String queryString = "SELECT * FROM portfolio";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			resultSet = ptmt.executeQuery();

			while (resultSet.next()) {
				PortfolioEntity portfolioEntity = new PortfolioEntity();

				portfolioEntity.setPortfolioID(resultSet.getInt("portfolio_id"));
				portfolioEntity.setUserID(resultSet.getInt("user_id"));
				portfolioEntity.setAssetID(resultSet.getInt("asset_id"));
				portfolioEntity.setPrice(resultSet.getDouble("price"));
				portfolioEntity.setVolume(resultSet.getDouble("volume"));
				portfolioEntity.setDate(resultSet.getDate("date"));

				listAllPortfolios.add(portfolioEntity);
			}

			return listAllPortfolios;
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
