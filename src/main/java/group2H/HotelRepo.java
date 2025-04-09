package group2H;

import java.lang.module.Configuration;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelRepo {

    public List<Hotel> searchHotels(String location) {
        List<Hotel> result = new ArrayList<>();
        String searchTerm = location.trim();
        
        try (Connection conn = DBHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(
                 "SELECT * FROM hotels WHERE LOWER(TRIM(location)) = LOWER(?)")) {
            
            pstmt.setString(1, searchTerm);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                result.add(new Hotel(
                    rs.getInt("hotel_id"),
                    rs.getString("name"),
                    rs.getString("location"),
                    rs.getInt("total_beds"),
                    rs.getInt("price_per_night")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Search error: " + e.getMessage());
        }
        return result;
    }

    
    public Hotel getHotelByName(String name) {
        String sql = "SELECT * FROM hotels WHERE LOWER(name) = LOWER(?)";
        try (Connection conn = DBHelper.connect(); 
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Hotel(
                        rs.getInt("hotel_id"),
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getInt("total_beds"),
                        rs.getInt("price_per_night")
                );
            }
        } catch (SQLException e) {
            System.out.println("Hotel lookup error: " + e.getMessage());
        }
        return null;
    }

}
