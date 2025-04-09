package group2F;

public class Booking {

    private String flightID;
    private String userID;
    private String bookingID;

    public Booking(String flightID, String userID) {
        this.flightID = flightID;
        this.userID = userID;
        
        // leidin til ad fa bookingID fyrir hvert booking, notum constructor til ad initializea thad
        this.bookingID = flightID + "-" + userID; // combination af flightID og userID
    }

    // adferd til ad fa bookingID, thurfum thetta til ad fa adgang af bookingID     
    public String getBookingID() {
        return bookingID;
    }

    public String getFlightID() {
        return flightID;
    }

    public String getUserID() {
        return userID;
    }
    // way to get the bookingID, flightID og userID as text
    @Override
    public String toString() {
        return "Booking{" +
                "bookingID='" + bookingID + '\'' +
                ", flightID='" + flightID + '\'' +
                ", userID='" + userID + '\'' +
                '}';
    }

}
