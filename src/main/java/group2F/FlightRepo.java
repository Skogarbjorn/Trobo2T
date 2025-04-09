package group2F;

public interface FlightRepo {
    // modify flights
    void addFlight(Flight flight);
    void deleteFlight(String flightID);
    
    // get flights 
    Flight[] getFlights();
    Flight getFlightById(String flightID);
    Flight[] searchFlights(String destination, String date);

    // booking flights
    void selectFlightAndBook(String flightID, String userID);
    
    // get available seats for a flight
    int getAvailableSeats(String flightID);

    void decrementAvailableSeats(String flightID); // adferd til ad draga 1 saeti fra available seats
    void incrementAvailableSeats(String flightID); // adferd til ad auka 1 saeti fyrir available seats

}
