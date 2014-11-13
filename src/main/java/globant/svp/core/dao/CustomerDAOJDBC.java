package globant.svp.core.dao;

import globant.svp.core.domain.Customer;
import globant.svp.core.domain.Entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("CustomerDAOJDBC")
public class CustomerDAOJDBC implements GenericDAO {

	@Autowired
	private Customer entity;

	public void addEntity(Entity entity) {
		Connection conn;
		try {
			conn = DataBaseUtil.createConnection();
			Statement stmt = conn.createStatement();

			StringBuffer sqlQuery = new StringBuffer(
					"INSERT INTO CUSTOMER (ECN, ACCOUNT, FIRSTNAME, MIDDLEINITIAL, LASTNAME, PHONE, CITY, STATE, ZIP) VALUES("
							+ "'"
							+ ((Customer) entity).getEcn()
							+ "',"
							+ ((Customer) entity).getAccount()
							+ ","
							+ "'"
							+ ((Customer) entity).getFirstName()
							+ "',"
							+ "'"
							+ ((Customer) entity).getMiddleInitial()
							+ "',"
							+ "'"
							+ ((Customer) entity).getLastName()
							+ "',"
							+ "'"
							+ ((Customer) entity).getPhone()
							+ "',"
							+ "'"
							+ ((Customer) entity).getCity()
							+ "',"
							+ "'"
							+ ((Customer) entity).getState()
							+ "',"
							+ ((Customer) entity).getZip() + ")");

			System.out.println("__________________________________");

			System.out.println(sqlQuery.toString());

			@SuppressWarnings("unused")
			int count = stmt.executeUpdate(sqlQuery.toString());

			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<? extends Entity> getAllEntity() {
		Connection conn;
		List<Customer> list = new ArrayList<Customer>();
		try {
			conn = DataBaseUtil.createConnection();
			Statement stmt = conn.createStatement();

			ResultSet rs;
			rs = stmt.executeQuery("SELECT * FROM CUSTOMER");
			while (rs.next()) {
				entity = new Customer();
				entity.setEcn(rs.getString("ECN"));
				entity.setAccount(rs.getLong("Account"));
				entity.setFirstName(rs.getString("FIRSTNAME"));
				entity.setMiddleInitial(rs.getString("MIDDLEINITIAL"));
				entity.setLastName(rs.getString("LASTNAME"));
				entity.setDob(rs.getDate("DOB"));
				entity.setPhone(rs.getString("PHONE"));
				entity.setCity(rs.getString("CITY"));
				entity.setState(rs.getString("STATE"));
				entity.setZip(rs.getLong("ZIP"));
				list.add(entity);
			}
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public void deleteEntityById(String id, String value) {
		Connection conn;
		try {
			conn = DataBaseUtil.createConnection();
			Statement stmt = conn.createStatement();

			String sqlQuery = new String("DELETE FROM CUSTOMER WHERE " + id
					+ "=" + (value instanceof String ? "'" : "") + value
					+ (value instanceof String ? "'" : ""));

			stmt.executeUpdate(sqlQuery);

			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<? extends Entity> getAllEntityByRequestParam(
			Map<String, String> requestParam) {
		Connection conn;
		List<Customer> list = new ArrayList<Customer>();
		try {
			conn = DataBaseUtil.createConnection();
			Statement stmt = conn.createStatement();

			ResultSet rs;
			StringBuffer sqlQuery = new StringBuffer(
					"SELECT * FROM CUSTOMER WHERE ");
			boolean firstTime = true;
			if (!requestParam.isEmpty()) {
				Iterator<String> keyIter = requestParam.keySet().iterator();
				while (keyIter.hasNext()) {
					String key = keyIter.next();
					String value = requestParam.get(key);
					if (!firstTime) {
						sqlQuery.append(" or ");
					} else {
						firstTime = false;
					}
					sqlQuery.append(key.toUpperCase() + " like '%" + value
							+ "%'");
				}
			}

			rs = stmt.executeQuery(sqlQuery.toString());
			while (rs.next()) {
				entity = new Customer();

				entity.setEcn(rs.getString("ECN"));
				entity.setAccount(rs.getLong("Account"));
				entity.setFirstName(rs.getString("FIRSTNAME"));
				entity.setMiddleInitial(rs.getString("MIDDLEINITIAL"));
				entity.setLastName(rs.getString("LASTNAME"));
				entity.setDob(rs.getDate("DOB"));
				entity.setPhone(rs.getString("PHONE"));
				entity.setCity(rs.getString("CITY"));
				entity.setState(rs.getString("STATE"));
				entity.setZip(rs.getLong("ZIP"));
				list.add(entity);
			}
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public void deleteEntity(Entity entity) {
		// TODO Auto-generated method stub
	}

}
