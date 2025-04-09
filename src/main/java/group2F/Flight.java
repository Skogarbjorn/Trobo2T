package group2F;

public class Flight {
    private String date;
    private String departureLocation;
    private String destination;
    private String flightID;
    private int availableSeats;
    private String status;
    private boolean full;
    
     /**
     * constructor fyrir flight objectinn
     * 
     * @param flightID         unique identifier for the flight
     * @param date             date/time of the flight (as a String or Date)
     * @param departureLocation starting location of the flight
     * @param destination       destination location of the flight
     * @param status           current status (e.g., 'On Time', 'Delayed', etc.)
     * @param availableSeats   number of seats available at creation
     */
    public Flight(String flightID, String date, String departureLocation, String destination, String status, int availableSeats) {
        this.flightID = flightID;
        this.date = date;
        this.departureLocation = departureLocation;
        this.destination = destination;
        this.status = status;
        this.availableSeats = availableSeats;
        // if no seats are available, mark as full
        this.full = (availableSeats <= 0);
    }

    // decrementar available seats um 1, ef engin laus saeti eru til, setjum full i true
    public void occupySeat() { 
        if (!full && availableSeats > 0) {
            availableSeats--;
            if (availableSeats <= 0) {
                full = true;
            }
        }
    }

    // incrementar available seats um 1, ef laus saeti eru til eda verda til, setjum full i false
    public void freeSeat() {
        availableSeats++;
        if (full) {
            full = false;
        }
    }

    // getDetails, leifir ad na i allar details um flugid gegnum flightID
    public String getDetails(String flightID) {
        if (!this.flightID.equals(flightID)) {
            return "Flight ID " + flightID + " not found in this Flight object.";
        }
        return "\n" +
               "Flight ID : ->" + this.flightID + "<- " +"\n" +
               "Date: " + this.date + "\n" +
               "Departure: " + this.departureLocation + "\n" +
               "Destination: " + this.destination + "\n" +
               "Status: " + this.status + "\n" +
               "Full: " + this.full + "\n" +
               "Available Seats: " + this.availableSeats;
    }

    // update status a flight object, on time eda delayed yfirleitt, en er customizeable til ad taka hvada status msg sem er
    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    // getters og setters, veit ekki hvad eg mun nota svo fukket
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
        this.full = (availableSeats <= 0);
    }

    public String getStatus() {
        return status;
    }

    public boolean isFull() {
        return full;
    }
}
