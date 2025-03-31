package api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import services.Services;

public class Flights implements ServiceAPI {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;

	public Flights() {
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + getClass().getResource("/data/" + Services.FLIGHTS.getDbName()));
			conn.setAutoCommit(true);
			stmt = conn.createStatement();
		} catch (SQLException e) {
			System.err.println("Failed to establish a connection to flights database");
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if (!rs.isClosed()) {
				rs.close();
			}
			if (!stmt.isClosed()) {
				stmt.close();
			}
			if (!conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet getAllEntries() {
			return query("SELECT * FROM flights");
	}

	public ResultSet getAllAvailable() {
		return query("SELECT * FROM flights,seats WHERE flights(id) = flights_id AND available = 1");
	}

	public void addFlight(String company, String from, String to, int num_seats, String dep, String arr) {
		rs = update("INSERT INTO flights (company, departure_location, arrival_location) VALUES ('" + company + "', '" + from + "', '" + to + "')");
		try {
			if (rs.next()) {
				Long flight_id = rs.getLong(1);
				update("INSERT INTO time (departure, arrival, flight_id) VALUES ('" + dep + "', '" + arr + "', " + flight_id + ")");
				for (int i = 0; i < num_seats; i++) {
					update("INSERT INTO seats (available, flight_id) VALUES (" + 1 + ", " + flight_id + ")");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private ResultSet query(String sql) {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			return stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private ResultSet update(String sql) {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			stmt.executeUpdate(sql);
			rs = stmt.getGeneratedKeys();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
