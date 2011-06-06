package dataAccess.databaseManagement.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dataAccess.databaseManagement.ConnectionFactory;
import dataAccess.databaseManagement.entity.PriceEntity;

public class PriceManager {
	private Connection connection = null;
	private PreparedStatement ptmt = null;
	private ResultSet resultSet = null;

	public PriceManager() {

	}

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}

	public void add(PriceEntity priceEntity) {
		String queryString = "INSERT INTO price(price_id, asset_id, date, volume, open, close, high, low, delivery_date) VALUES(?,?,?,?,?,?,?,?,?)";
		if (priceEntity.getDeliveryDate() == null) {
			queryString = "INSERT INTO price(price_id, asset_id, date, volume, open, close, high, low) VALUES(?,?,?,?,?,?,?,?)";
		}
		try {
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
			ptmt.setNull(1, java.sql.Types.INTEGER);
			ptmt.setInt(2, priceEntity.getAssetID());
			ptmt.setDate(3, priceEntity.getDate());
			ptmt.setDouble(4, priceEntity.getVolume());
			ptmt.setDouble(5, priceEntity.getOpen());
			ptmt.setDouble(6, priceEntity.getClose());
			ptmt.setDouble(7, priceEntity.getHigh());
			ptmt.setDouble(8, priceEntity.getLow());
			
			if (priceEntity.getDeliveryDate() != null) {
				ptmt.setDate(9, priceEntity.getDeliveryDate());
			}
			
			ptmt.executeUpdate();
			
			ResultSet rs = ptmt.getGeneratedKeys();
			int autoIncValue = -1;
			
			if (rs.next()) {
				autoIncValue = rs.getInt(1);
			}
			
			priceEntity.setPriceID(autoIncValue);
			
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

	public void update(PriceEntity priceEntity) {
		try {
			String queryString = "UPDATE price SET asset_id=?, date=?, delivery_date=?, volume=?, open=?, close=?, high=?, low=? WHERE price_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, priceEntity.getAssetID());
			ptmt.setDate(2, priceEntity.getDate());
			ptmt.setDate(3, priceEntity.getDeliveryDate());
			ptmt.setDouble(4, priceEntity.getVolume());
			ptmt.setDouble(5, priceEntity.getOpen());
			ptmt.setDouble(6, priceEntity.getClose());
			ptmt.setDouble(7, priceEntity.getHigh());
			ptmt.setDouble(8, priceEntity.getLow());
			ptmt.setInt(9, priceEntity.getPriceID());
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

	public void delete(int priceID) {
		try {
			String queryString = "DELETE FROM price WHERE price_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, priceID);
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

	public PriceEntity getPriceByID(int priceID) {
		try {
			PriceEntity priceEntity = null;
			
			String queryString = "SELECT * FROM price WHERE price_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, priceID);
			resultSet = ptmt.executeQuery();
			
			if (resultSet.next()) {
				priceEntity = new PriceEntity();
				priceEntity.setPriceID(priceID);
				priceEntity.setAssetID(resultSet.getInt("asset_id"));
				priceEntity.setDate(resultSet.getDate("date"));
				priceEntity.setDeliveryDate(resultSet.getDate("delivery_date"));
				priceEntity.setVolume(resultSet.getDouble("volume"));
				priceEntity.setOpen(resultSet.getDouble("open"));
				priceEntity.setClose(resultSet.getDouble("close"));
				priceEntity.setHigh(resultSet.getDouble("high"));
				priceEntity.setLow(resultSet.getDouble("low"));
			}
			
			return priceEntity;
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
	
	public ArrayList<PriceEntity> getAllPrices() {
		try {
			ArrayList<PriceEntity> listAllPrices = new ArrayList<PriceEntity>();
			
			String queryString = "SELECT * FROM price";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			resultSet = ptmt.executeQuery();
			
			while (resultSet.next()) {
				PriceEntity priceEntity = new PriceEntity();

				priceEntity.setPriceID(resultSet.getInt("price_id"));
				priceEntity.setAssetID(resultSet.getInt("asset_id"));
				priceEntity.setDate(resultSet.getDate("date"));
				priceEntity.setDeliveryDate(resultSet.getDate("delivery_date"));
				priceEntity.setVolume(resultSet.getDouble("volume"));
				priceEntity.setOpen(resultSet.getDouble("open"));
				priceEntity.setClose(resultSet.getDouble("close"));
				priceEntity.setHigh(resultSet.getDouble("high"));
				priceEntity.setLow(resultSet.getDouble("low"));
				
				listAllPrices.add(priceEntity);
			}
			
			return listAllPrices;
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
