package group2F;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteBookingRepo implements BookingRepo {
    private Connection connection;

    public SQLiteBookingRepo(String dbFilePath) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbFilePath);
            String sqlCreate = "CREATE TABLE IF NOT EXISTS bookings (" +
                               "bookingID TEXT PRIMARY KEY," +
                               "flightID TEXT," +
                               "userID TEXT" +
                               ");";
            Statement stmt = connection.createStatement();
            stmt.execute(sqlCreate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void confirmBooking(Booking booking) {
        String sqlInsert = "INSERT INTO bookings(bookingID, flightID, userID) VALUES(?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sqlInsert)) {
            pstmt.setString(1, booking.getBookingID());
            pstmt.setString(2, booking.getFlightID());
            pstmt.setString(3, booking.getUserID());
            pstmt.executeUpdate();
            System.out.println("Booking confirmed: " + booking.getBookingID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBooking(String bookingID) {
        String sqlDelete = "DELETE FROM bookings WHERE bookingID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sqlDelete)) {
            pstmt.setString(1, bookingID);
            pstmt.executeUpdate();
            System.out.println("Booking deleted: " + bookingID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Booking[] getBookings() {
        String sqlSelect = "SELECT * FROM bookings";
        List<Booking> bookingList = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sqlSelect)) {
            while (rs.next()) {
                bookingList.add(new Booking(
                        rs.getString("flightID"),
                        rs.getString("userID")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingList.toArray(new Booking[0]);
    }

    @Override
    public boolean containsBooking(String bookingID) {
        String sqlSelect = "SELECT * FROM bookings WHERE bookingID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sqlSelect)) {
            pstmt.setString(1, bookingID);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // returns true if a booking with targeted ID exists
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
