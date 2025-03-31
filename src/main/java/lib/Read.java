package lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import services.Services;

public class Read {
	private Connection conn;
	private Statement stmt;
	private Services s;

	public Read(Services s) {
		try {
			this.conn = DriverManager.getConnection(
					"jdbc:sqlite:" + getClass().getResource("/data/" + s.getDbName()).toString());
			this.stmt = conn.createStatement();
		} catch (SQLException e) {
			System.err.println("Error trying to connect to database:");
			e.printStackTrace();
		}
	}

	public Connection getConn() {
		return this.conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public ResultSet readAll() {
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + s.getMainTable());
			return rs;
		} catch (SQLException e) {
			System.err.println(e);
			return null;
		}
	}
}

