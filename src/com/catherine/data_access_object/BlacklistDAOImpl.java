package com.catherine.data_access_object;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * DAO(Data Access
 * Object)：通过DAO介面存取数据，这边本来有一个BlacklistDAO接口，并且由BlacklistDAOImpl实现，
 * 但是为了用单例模式解决多线程修改数据的问题直接省略<br>
 * 
 * 用singleton避免多线程操作数据库<br>
 * static synchronized可以保证无论有多少线程，同时只会有一个线程执行该方法
 * 
 * @author Catherine
 *
 */
public class BlacklistDAOImpl {
	private static BlacklistOpenHelper dbHelper;

	private static class BlacklistDAOImplHolder {
		private static BlacklistDAOImpl instance = new BlacklistDAOImpl();
	}

	public static BlacklistDAOImpl getInstance() {
		return BlacklistDAOImplHolder.instance;
	}

	private BlacklistDAOImpl() {
		dbHelper = new BlacklistOpenHelper();
		dbHelper.create();
	}

	public static synchronized List<Contact> getBlacklist() {
		List<Contact> contacts = new LinkedList<>();
		try {
			ResultSet rs = dbHelper.getDatabase();
			while (rs.next()) {
				Contact contact = new Contact();
				contact.setName(rs.getString("name"));
				contact.setBlock(rs.getInt("block"));
				contact.setID(rs.getInt("_id"));
				contacts.add(contact);
			}
			return contacts;
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			e.printStackTrace();
			return null;
		} finally {
			dbHelper.disconnect();
		}
	}

	public static synchronized void add(Contact contact) {
		try {
			Statement statement = dbHelper.getStatement();
			statement.executeUpdate(String.format("insert into person values(%s, '%s', %d)", null, contact.getName(),
					contact.getBlock()));
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			e.printStackTrace();
		} finally {
			dbHelper.disconnect();
		}
	}

	public static synchronized void update(Contact contact) {
		try {
			Statement statement = dbHelper.getStatement();
			ResultSet rs = statement.executeQuery(String.format("select * from person where _id=%d", contact.getID()));
			if (rs.next()) {
				statement.executeUpdate(String.format("update person set name='%s', block=%d where _id=%d",
						contact.getName(), contact.getBlock(), contact.getID()));
			} else {
				statement.executeUpdate(String.format("insert into person values(%s, '%s', %d)", null,
						contact.getName(), contact.getBlock()));
			}
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			e.printStackTrace();
		} finally {
			dbHelper.disconnect();
		}
	}

	public static synchronized void delete(int ID) {
		try {
			Statement statement = dbHelper.getStatement();
			ResultSet rs = statement.executeQuery(String.format("select * from person where _id=%d", ID));
			if (rs.next()) {
				statement.executeUpdate(String.format("delete from person where _id=%d", ID));
			}
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			e.printStackTrace();
		} finally {
			dbHelper.disconnect();
		}
	}

}
