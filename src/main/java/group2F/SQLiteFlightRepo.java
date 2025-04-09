package group2F;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteFlightRepo implements FlightRepo {
    private Connection connection;
    
    public SQLiteFlightRepo(String dbFilePath) {
        try {
            Class.forName("org.sqlite.JDBC");
            
            // Connect to the SQLite database (it will create the file if it doesn't exist)
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbFilePath);
            // Explicitly load the SQLite JDBC driver (optional in newer versions)
            
            System.out.println("Connecting to database at: " + dbFilePath);
            // Create the flights table if it doesn't exist
            String sqlCreate = "CREATE TABLE IF NOT EXISTS flights (" +
                               "flightID TEXT PRIMARY KEY," +
                               "date TEXT," +
                               "departureLocation TEXT," +
                               "destination TEXT," +
                               "availableSeats INTEGER," +
                               "status TEXT" +
                               ");";
            Statement stmt = connection.createStatement();
            stmt.execute(sqlCreate);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // add flight object
    @Override
    public void addFlight(Flight flight) {
        String sqlInsert = "INSERT INTO flights(flightID, date, departureLocation, destination, availableSeats, status) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sqlInsert)) {
            pstmt.setString(1, flight.getFlightID());
            pstmt.setString(2, flight.getDate());
            pstmt.setString(3, flight.getDepartureLocation());
            pstmt.setString(4, flight.getDestination());
            pstmt.setInt(5, flight.getAvailableSeats());
            pstmt.setString(6, flight.getStatus());
            pstmt.executeUpdate();
            System.out.println("Flight added: " + flight.getFlightID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // delete flight object
    @Override
    public void deleteFlight(String flightID) {
        String sqlDelete = "DELETE FROM flights WHERE flightID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sqlDelete)) {
            pstmt.setString(1, flightID);
            pstmt.executeUpdate();
            System.out.println("Flight deleted: " + flightID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // get info of a flight object
    @Override
    public Flight getFlightById(String flightID) {
        String sqlSelect = "SELECT * FROM flights WHERE flightID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sqlSelect)) {
            pstmt.setString(1, flightID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Flight(
                        rs.getString("flightID"),
                        rs.getString("date"),
                        rs.getString("departureLocation"),
                        rs.getString("destination"),
                        rs.getString("status"),
                        rs.getInt("availableSeats")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    // thetta function a ad skila lista af flights sem fylgja queries sem eru settar inn
    @Override
    public Flight[] searchFlights(String destination, String date) {
        // Query includes a condition for departureLocation = 'reykjavik'
        String sqlSelect = "SELECT * FROM flights WHERE destination LIKE ? AND date = ? AND departureLocation = ?";
        List<Flight> flights = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(sqlSelect)) {
            // Use the LIKE operator for partial matching; 
            // for example, if the user enters "USA", this will match "USA", "United States", etc.
            pstmt.setString(1, "%" + destination + "%");
            pstmt.setString(2, date);
            pstmt.setString(3, "Reykjavik"); // fixed departure location
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                flights.add(new Flight(
                        rs.getString("flightID"),
                        rs.getString("date"),
                        rs.getString("departureLocation"),
                        rs.getString("destination"),
                        rs.getString("status"),
                        rs.getInt("availableSeats")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights.toArray(new Flight[0]);
    }

    @Override // deprecated function, ekki notad, gerdi manual booking call eftir ad searcha
    public void selectFlightAndBook(String flightID, String userID) {
        // temp placeholder logic for booking a flight
        System.out.println("Flight " + flightID + " booked for user " + userID);
    }

    @Override
    public int getAvailableSeats(String flightID) {
        String sqlSelect = "SELECT availableSeats FROM flights WHERE flightID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sqlSelect)) {
            pstmt.setString(1, flightID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("availableSeats");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // return 0 ef not found
    }

    @Override  
    public void decrementAvailableSeats(String flightID) {
        String sqlUpdate = "UPDATE flights SET availableSeats = availableSeats - 1 WHERE flightID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sqlUpdate)) {
            pstmt.setString(1, flightID);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Decremented available seats for flight " + flightID);
            } else {
                System.out.println("Flight " + flightID + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void incrementAvailableSeats(String flightID) {
        String sqlUpdate = "UPDATE flights SET availableSeats = availableSeats + 1 WHERE flightID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sqlUpdate)) {
            pstmt.setString(1, flightID);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Incremented available seats for flight " + flightID);
            } else {
                System.out.println("Flight " + flightID + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Flight[] getFlights() {
        String sqlSelect = "SELECT * FROM flights";
        List<Flight> flightList = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sqlSelect)) {
            while (rs.next()) {
                flightList.add(new Flight(
                        rs.getString("flightID"),
                        rs.getString("date"),
                        rs.getString("departureLocation"),
                        rs.getString("destination"),
                        rs.getString("status"),
                        rs.getInt("availableSeats")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flightList.toArray(new Flight[0]);
    }
}
