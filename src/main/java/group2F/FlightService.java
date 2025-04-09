package group2F;

public class FlightService {
		private String flightDbPath = "src/main/java/group2F/SQL/flights.db";
    private FlightRepo flightRepo = new SQLiteFlightRepo(flightDbPath);

    public FlightService(FlightRepo flightRepo) {
        this.flightRepo = flightRepo;
    }

    public FlightService() {
    }

    public int getAvailableSeats(String flightID) {
        Flight flight = flightRepo.getFlightById(flightID);
        if (flight != null) {
            return flight.getAvailableSeats();
        }
        return 0;
    }

    public void decrementAvailableSeats(String flightID) {
        Flight flight = flightRepo.getFlightById(flightID);
        if (flight != null) {
            flight.occupySeat(); // decrement available seats by 1

        }
    }

    public void incrementAvailableSeats(String flightID) {
        Flight flight = flightRepo.getFlightById(flightID);
        if (flight != null) {
            flight.freeSeat(); // increment available seats by 1

        }
    }

    public Flight[] getFlights() {
        return flightRepo.getFlights(); // aetti ad na i allar current bookings i databaseinu
    }

    public FlightRepo getRepo() {
        return flightRepo;
    }
}
