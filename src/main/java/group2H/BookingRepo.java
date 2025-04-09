package group2H;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingRepo {
    public int countBookedbeds(int hotelId, LocalDate checkIn, LocalDate checkOut) {
        String sql = "SELECT SUM(guests) FROM bookings WHERE hotel_id = ? AND NOT (? >= check_out OR ? <= check_in)";
        try (Connection conn = DBHelper.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, hotelId);
            pstmt.setString(2, checkIn.toString());
            pstmt.setString(3, checkOut.toString());
            ResultSet rs = pstmt.executeQuery();
            return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println("Booking count error: " + e.getMessage());
            return 0;
        }
    }

    public void createBooking(int hotelId, int guests, LocalDate checkIn, LocalDate checkOut) {
        String sql = "INSERT INTO bookings (hotel_id, guests, check_in, check_out) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBHelper.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, hotelId);
            pstmt.setInt(2, guests);
            pstmt.setString(3, checkIn.toString());
            pstmt.setString(4, checkOut.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Booking creation error: " + e.getMessage());
        }
    }

    public List<Booking> getAllBookings() {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT b.*, h.name FROM bookings b JOIN hotels h ON b.hotel_id = h.hotel_id";
        try (Connection conn = DBHelper.connect(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(new Booking(
                        rs.getInt("hotel_id"),
                        rs.getString("name"),
                        rs.getInt("guests"),
                        LocalDate.parse(rs.getString("check_in")),
                        LocalDate.parse(rs.getString("check_out"))
                ));
            }
        } catch (SQLException e) {
            System.out.println("Booking retrieval error: " + e.getMessage());
        }
        return list;
    }
}
