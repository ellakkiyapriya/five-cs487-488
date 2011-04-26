package dataAccess.databaseManagement.manager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		String queryString = "INSERT INTO price(asset_id, date, delivery_date, volume, open, close, high, low)";
		try {
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, priceEntity.getAsset_id());
			ptmt.setDate(2, priceEntity.getDate());
			ptmt.setDate(3, priceEntity.getDelivery_date());
			ptmt.setDouble(4, priceEntity.getVolume());
			ptmt.setDouble(5, priceEntity.getOpen());
			ptmt.setDouble(6, priceEntity.getClose());
			ptmt.setDouble(7, priceEntity.getHigh());
			ptmt.setDouble(8, priceEntity.getLow());
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

	public void update(PriceEntity priceEntity) {
		try {
			String queryString = "UPDATE price SET volume=?, open=?, close=?, high=?, low=? WHERE asset_id=? AND date=? AND delivery_date=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setDouble(1, priceEntity.getVolume());
			ptmt.setDouble(2, priceEntity.getOpen());
			ptmt.setDouble(3, priceEntity.getClose());
			ptmt.setDouble(4, priceEntity.getHigh());
			ptmt.setDouble(5, priceEntity.getLow());
			ptmt.setInt(6, priceEntity.getAsset_id());
			ptmt.setDate(7, priceEntity.getDate());
			ptmt.setDate(8, priceEntity.getDelivery_date());
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

	public void delete(int asset_id, Date date, Date delivery_date) {
		try {
			String queryString = "DELETE FROM price WHERE asset_id=? AND date=? AND delivery_date=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, asset_id);
			ptmt.setDate(2, date);
			ptmt.setDate(3, delivery_date);
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

	public PriceEntity getPrice(int asset_id, Date date, Date delivery_date) {
		try {
			PriceEntity priceEntity = new PriceEntity();
			
			String queryString = "SELECT * FROM price WHERE asset_id=? AND date=? AND delivery_date=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, asset_id);
			ptmt.setDate(2, date);
			ptmt.setDate(3, delivery_date);
			resultSet = ptmt.executeQuery();
			
			while (resultSet.next()) {
				priceEntity.setAsset_id(asset_id);
				priceEntity.setDate(date);
				priceEntity.setDelivery_date(delivery_date);
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
	
	public ArrayList<PriceEntity> getAllPriceEntities() {
		try {
			ArrayList<PriceEntity> listAllPriceEntities = new ArrayList<PriceEntity>();
			
			String queryString = "SELECT * FROM price";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			resultSet = ptmt.executeQuery();
			
			while (resultSet.next()) {
				PriceEntity priceEntity = new PriceEntity();

				priceEntity.setAsset_id(resultSet.getInt("asset_id"));
				priceEntity.setDate(resultSet.getDate("date"));
				priceEntity.setDelivery_date(resultSet.getDate("delivery_date"));
				priceEntity.setVolume(resultSet.getDouble("volume"));
				priceEntity.setOpen(resultSet.getDouble("open"));
				priceEntity.setClose(resultSet.getDouble("close"));
				priceEntity.setHigh(resultSet.getDouble("high"));
				priceEntity.setLow(resultSet.getDouble("low"));
				
				listAllPriceEntities.add(priceEntity);
			}
			
			return listAllPriceEntities;
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
