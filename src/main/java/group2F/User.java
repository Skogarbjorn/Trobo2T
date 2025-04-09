package group2F;

public class User {
    private String userID;
    private String name;
    private String email;
    private String role; // admin eda passenger

    // constructor
    public User(String userID, String name, String email, String role) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    // breyta user variables
    public void updateUserInfo(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void deleteUserInfo(String userID){ // placeholder kóði, þarf aðgang af database og deleta gögnum þar
        this.userID = null;
        this.name = null;
        this.email = null;
        this.role = null;
    }

    public void createFlight(String flightID, String date, String departureLocation, String destination, String status, int availableSeats) {
        if (!"admin".equalsIgnoreCase(this.role)) { // role checking fyrir methods
            System.out.println("You do not have permission to create flights as you are not an admin.");
            return;
        }
        // flight object creator
        Flight newFlight = new Flight(flightID, date, departureLocation, destination, status, availableSeats);
        System.out.println("Flight created: " + newFlight.getDetails(flightID));
    }

    public void deleteFlight(String flightID) {
        if (!"admin".equalsIgnoreCase(this.role)) {
            System.out.println("You do not have permission to delete flights as you are not an admin.");
            return;
        }
        // Remove the flight from your system
        System.out.println("Flight with ID " + flightID + " has been deleted.");
    }

    // !!!FEATURES THAT ARE POSSIBLE BUT HAVENT BEEN ADDED!!!

    /*
    public void leaveReview(String flightID, String rating, String comment) {
        if (!"passenger".equalsIgnoreCase(this.role)) { // role checking fyrir methods
            System.out.println("You do not have permission to leave a review as you are not a passenger.");
            return;
        }
        // bara enhv review drasl placeholder, veit ekki hvort eg held afram med thetta
        System.out.println("Review left for flight " + flightID + ": Rating = " 
                           + rating + ", Comment = " + comment);
    }

    public void updateFlightStatus(String flightID, String status) {
        if (!"admin".equalsIgnoreCase(this.role)) { // role checking fyrir methods
            System.out.println("You do not have permission to update flight status as you are not an admin.");
            return;
        }
        // breytir status i flight objectinu, thurfum ad na i database til ad breyta statusi
        // placeholder kóði, þarf aðgang af database til að breyta statusi
        System.out.println("Flight " + flightID + " status updated to " + status);
    }

    public Flight modifyFlight(String flightID) {
        if (!"admin".equalsIgnoreCase(this.role)) {
            System.out.println("You do not have permission to modify flights as you are not an admin.");
            return null;
        }
        // Example logic: fetch the flight, change its details, etc.
        System.out.println("Modifying flight with ID: " + flightID);
        // Return the updated Flight object (stubbed here).
        return new Flight(flightID, "2025-12-31", "LocationA", "LocationB", "On Time", 100);
    }

    

    // setrole function
    public void setRole(String role) {
        this.role = role;
    }
    */
    // getters og setters

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * @return the role of the user (e.g., "admin", "passenger")
     */
    public String getRole() {
        return role;
    }

}
