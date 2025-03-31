package daytrips;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.Review;
import com.example.User;

import javafx.util.Pair;

public class Daytrips {
	private Connection conn;

	public Daytrips() {}

	public int addDaytrip(Daytrip toBeAdded) {
		createConn();
		try {
			String insertSQL = "INSERT INTO daytrip_daytrips (day, description, location, availability) VALUES (?, ?, ?, ?) RETURNING id";
			PreparedStatement pstmt = conn.prepareStatement(insertSQL);
			pstmt.setDate(1, toBeAdded.day);
			pstmt.setString(2, toBeAdded.description);
			pstmt.setString(3, toBeAdded.location);
			pstmt.setInt(4, toBeAdded.availability);

			ResultSet rs = pstmt.executeQuery();

			int id = -1;

			while (rs.next()) {
				id = rs.getInt("id");
			}
			pstmt.close();
			conn.close();

			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public void leaveReview(Review review, User user) {
		createConn();
		try {
			String insertSQL = "INSERT INTO daytrip_reviews (review, rating, daytrip_id, user_id) VALUES (?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(insertSQL);

			pstmt.setString(1, review.review);
			pstmt.setInt(2, review.rating);
			pstmt.setInt(3, review.daytrip.id);
			pstmt.setInt(4, user.daytrips_id);

			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int createUser(User user) {
		createConn();
		try {
			String insertSQL = "INSERT INTO daytrip_users (username, email, password) VALUES (?, ?, ?) RETURNING id";
			PreparedStatement pstmt = conn.prepareStatement(insertSQL);
			pstmt.setString(1, user.username);
			pstmt.setString(2, user.email);
			pstmt.setString(3, user.password);

			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int id = rs.getInt("id");
			conn.close();

			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public ArrayList<User> fetchUsers() {
		createConn();
		try {
			String getSQL = "SELECT * FROM daytrip_users";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(getSQL);

			ArrayList<User> users = new ArrayList<>();

			while (rs.next()) {
				User user = new User(rs.getString("username"), rs.getString("email"), rs.getString("password"));
				user.setDaytripsId(rs.getInt("id"));
				users.add(user);
			}

			conn.close();
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void bookUser(Daytrip toBeBooked, User user) {
		createConn();
		try {
			String insertSQL = "INSERT INTO daytrip_bookings VALUES (?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(insertSQL);
			pstmt.setInt(1, toBeBooked.id);
			pstmt.setInt(2, user.daytrips_id);
			pstmt.executeUpdate();
			pstmt.close();

			pstmt = conn.prepareStatement("UPDATE daytrip_daytrips SET availability = ? WHERE id = ? ");
			pstmt.setInt(1, toBeBooked.availability-1);
			pstmt.setInt(2, toBeBooked.id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Pair<ArrayList<Daytrip>, ArrayList<Review>> fetchUserProperties(User user) {
		createConn();
		try {
			String getSQL = "SELECT * FROM daytrip_daytrips, daytrip_bookings WHERE daytrip_id = id AND user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(getSQL);
			pstmt.setInt(1, user.daytrips_id);
			ResultSet rs = pstmt.executeQuery();

			ArrayList<Daytrip> daytrips = new ArrayList<>();

			while (rs.next()) {
				Daytrip daytrip = new Daytrip(rs.getDate("day"), rs.getString("description"), rs.getString("location"), rs.getInt("availability"));

				daytrips.add(daytrip);
			}
			rs.close();
			pstmt.close();

			getSQL = "SELECT * FROM daytrip_reviews, daytrip_daytrips WHERE user_id = ? AND daytrip_daytrips.id = daytrip_id";
			pstmt = conn.prepareStatement(getSQL);
			pstmt.setInt(1, user.daytrips_id);
			rs = pstmt.executeQuery();

			ArrayList<Review> reviews = new ArrayList<>();

			while (rs.next()) {
				Daytrip daytrip = new Daytrip(rs.getDate("day"), rs.getString("description"), rs.getString("location"), rs.getInt("availability"));
				Review review = new Review(rs.getString("review"), rs.getInt("rating"), daytrip);

				reviews.add(review);
			}
			rs.close();
			pstmt.close();
			conn.close();

			return new Pair<ArrayList<Daytrip>, ArrayList<Review>>(daytrips, reviews);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Review> fetchReviews() {
		createConn();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM daytrip_reviews");

			ArrayList<Review> reviews = new ArrayList<>();

			while (rs.next()) {
				Review review = new Review(rs.getString("review"), rs.getInt("rating"), null);
				reviews.add(review);
			}

			conn.close();
			return reviews;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public HashMap<Integer, Integer> fetchBookings() {
		createConn();
		try {
			String getSQL = "SELECT * FROM daytrip_bookings";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(getSQL);

			HashMap<Integer, Integer> results = new HashMap<>();

			while (rs.next()) {
				results.put(rs.getInt("user_id"), rs.getInt("daytrip_id"));
			}

			conn.close();
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Daytrip> fetchDaytrips() {
		createConn();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM daytrip_daytrips");
			ArrayList<Daytrip> results = new ArrayList<>();
			while (rs.next()) {
				Daytrip currDaytrip = new Daytrip(
						rs.getInt("id"), 
						rs.getDate("day"), 
						rs.getString("description"), 
						rs.getString("location"),
						rs.getInt("availability"));

				results.add(currDaytrip);
			}

			conn.close();
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Daytrip> fetchDaytrips(HashMap<String, String> filter) {
		createConn();
		try {
			StringBuilder where_sql = new StringBuilder(" WHERE");
			filter.forEach((param, value) -> {
				where_sql.append(" ").append(param).append(" = ").append(value).append(",");
			});

			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM daytrip_daytrips" + where_sql.substring(0, where_sql.length()-1));

			ResultSet rs = pstmt.executeQuery();
			ArrayList<Daytrip> results = new ArrayList<>();
			while (rs.next()) {
				Daytrip currDaytrip = new Daytrip(
						rs.getInt("id"), 
						rs.getDate("day"), 
						rs.getString("description"), 
						rs.getString("location"),
						rs.getInt("availability"));

				results.add(currDaytrip);
			}

			conn.close();
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void createConn() {
		try {
			conn = DriverManager.getConnection(
						"jdbc:sqlite:" + getClass().getResource("/data/services.db").toString());
		} catch (SQLException e) { 
			System.err.println("Error connecting to database...");
			e.printStackTrace();
		}
	}
}
