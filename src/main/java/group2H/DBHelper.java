package group2H;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {
    private static final String DB_PATH = "src/main/java/group2H/hotels.db";

    public static Connection connect() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
        
        initializeDatabase(conn);
        
        return conn;
    }

    private static void initializeDatabase(Connection conn) throws SQLException {
        createTables(conn);
        seedDataIfEmpty(conn);
    }

    private static void createTables(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS hotels (" +
                    "hotel_id INTEGER PRIMARY KEY," +
                    "name VARCHAR (150) NOT NULL," +
                    "location VARCHAR (150) NOT NULL," +
                    "total_beds INTEGER NOT NULL," +
                    "price_per_night INT NOT NULL);" +
                    
                    "CREATE TABLE IF NOT EXISTS bookings (" +
                    "booking_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "hotel_id INTEGER," +
                    "guests INTEGER," +
                    "check_in DATE," +
                    "check_out DATE," +
                    "FOREIGN KEY (hotel_id) REFERENCES hotels(hotel_id));";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    private static void seedDataIfEmpty(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM hotels")) {
            
            if (rs.getInt(1) == 0) {
                System.out.println("Seeding initial data...");
                seedHotelsData(conn);
            } else {
                
            }
        }
    }

    private static void seedHotelsData(Connection conn) throws SQLException {
        String sql = "INSERT INTO hotels VALUES " +
                    "(1,'CenterHotel','Akureyri',15,18000)," +
                    "(2,'Hafdals Hotel','Akureyri',15,18000)," +
                    "(3,'Hotel Torfnes','Isafjordur',10,10000)," +
                    "(4,'Westman Islands Inn','Vestmannaeyjar',10,20000)," +
                    "(5,'Hilton Nordica','Reykjavik',50,15000)," +
                    "(6,'Hotel Exeter','Reykjavik',50,15000)," +
                    "(7,'22 Hill Hotel','Reykjavik',50,15000)," +
                    "(8,'Hotel Natura','Reykjavik',50,15000)," +
                    "(9,'CityHub Reykjavik','Reykjavik',50,15000)," +
                    "(10,'Hotel Cabin','Reykjavik',50,15000)," +
                    "(11,'Fosshotel Lind','Reykjavik',50,15000)," +
                    "(12,'Fosshotel Baron','Reykjavik',50,15000)," +
                    "(13,'Center Hotels Plaza','Reykjavik',50,15000);";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }
}
