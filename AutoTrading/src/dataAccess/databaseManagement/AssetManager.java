package dataAccess.databaseManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AssetManager {
	private Connection connection = null;
	private PreparedStatement ptmt = null;
	private ResultSet resultSet = null;

	public AssetManager() {

	}

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}

	public void add(AssetEntity assetEntity) {
		String queryString = "INSERT INTO asset(asset_id, name, symbol, exchange_name, asset_info, fluctuation_range)";
		try {
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setNull(1, java.sql.Types.INTEGER);
			ptmt.setString(2, assetEntity.getName());
			ptmt.setString(3, assetEntity.getSymbol());
			ptmt.setString(4, assetEntity.getExchange_name());
			ptmt.setString(5, assetEntity.getAsset_info());
			ptmt.setDouble(6, assetEntity.getFluctuation_range());
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

	public void update(AssetEntity assetEntity) {
		try {
			String queryString = "UPDATE asset SET name=?, symbol=?, exchange_name=?, asset_info=?, fluctuation_range=? WHERE asset_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, assetEntity.getName());
			ptmt.setString(2, assetEntity.getSymbol());
			ptmt.setString(3, assetEntity.getExchange_name());
			ptmt.setString(4, assetEntity.getAsset_info());
			ptmt.setDouble(5, assetEntity.getFluctuation_range());
			ptmt.setInt(6, assetEntity.getAsset_id());
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

	public void delete(int asset_id) {
		try {
			String queryString = "DELETE FROM asset WHERE asset_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, asset_id);
			ptmt.executeUpdate();
			System.out.println("Data deleted Successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public AssetEntity getAssetByID(int asset_id) {
		try {
			AssetEntity assetEntity = new AssetEntity();
			
			String queryString = "SELECT * FROM asset WHERE asset_id=";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, asset_id);
			resultSet = ptmt.executeQuery();
			
			while (resultSet.next()) {
				assetEntity.setAsset_id(asset_id);
				assetEntity.setName(resultSet.getString("name"));
				assetEntity.setSymbol(resultSet.getString("symbol"));
				assetEntity.setExchange_name(resultSet.getString("exchange_name"));
				assetEntity.setAsset_info(resultSet.getString("asset_info"));
				assetEntity.setFluctuation_range(resultSet.getDouble("fluctuation_range"));
			}
			
			return assetEntity;
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
	
	public ArrayList<AssetEntity> getAllAsset() {
		try {
			ArrayList<AssetEntity> listAllAssets = new ArrayList<AssetEntity>();
			
			String queryString = "SELECT * FROM asset";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			resultSet = ptmt.executeQuery();
			
			while (resultSet.next()) {
				AssetEntity assetEntity = new AssetEntity();

				assetEntity.setAsset_id(resultSet.getInt("asset_id"));
				assetEntity.setName(resultSet.getString("name"));
				assetEntity.setSymbol(resultSet.getString("symbol"));
				assetEntity.setExchange_name(resultSet.getString("exchange_name"));
				assetEntity.setAsset_info(resultSet.getString("asset_info"));
				assetEntity.setFluctuation_range(resultSet.getDouble("fluctuation_range"));
				
				listAllAssets.add(assetEntity);
			}
			
			return listAllAssets;
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
