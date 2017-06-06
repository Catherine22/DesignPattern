package com.catherine.data_access_object;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class BlacklistOpenHelper {
	private Connection connection;
	private final String DB_PATH = "jdbc:sqlite:blacklist.db";

	protected void create() {
		try {
			Statement statement = getStatement();
			ResultSet rs = statement.executeQuery("select * from person");
			while (rs.next()) {
				// read the result set
				System.out.println("Existed: _id = " + rs.getInt("_id") + ", name = " + rs.getString("name")
						+ ", block = " + rs.getString("block"));
			}
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				e.printStackTrace();
			}
		}
	}

	protected ResultSet getDatabase() throws SQLException {
		Statement statement = getStatement();
		ResultSet rs = statement.executeQuery("select * from person");
		return rs;
	}

	protected void disconnect() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			// connection close failed.
			e.printStackTrace();
		}
	}

	protected Statement getStatement() throws SQLException {
		connection = DriverManager.getConnection(DB_PATH);
		Statement statement = connection.createStatement();
		statement.setQueryTimeout(30); // set timeout to 30 sec.
		statement.executeUpdate(
				"create table if not exists person (_id integer primary key autoincrement, name string, block integer)");
		return statement;
	}
}
