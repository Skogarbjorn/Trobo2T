package group2F;

// controllerinn okkar
public class BookingService {
    private String bookingDbPath = "src\\main\\java\\com\\myCompany\\app\\SQL\\bookings.db";
		private String flightDbPath = "src\\main\\java\\com\\myCompany\\app\\SQL\\flights.db";

    private FlightRepo flightRepo = new SQLiteFlightRepo(flightDbPath);
    private BookingRepo bookingRepo = new SQLiteBookingRepo(bookingDbPath);

    public BookingService(BookingRepo bookingRepo, FlightRepo flightRepo) {
        this.bookingRepo = bookingRepo;
        this.flightRepo = flightRepo;
    }
    public BookingService() {
    }


    /**
     * byr til booking ef thad er laust saeti i flight objectinum
     * @param userID user identifier
     * @param flightID flight identifier
     * @return true ef booking var successful, false ef booking var ekki successful, auk thess ad bua til bookingID
     * @throws IllegalArgumentException ef enhv gerist, tharf ad accounta fyrir mismunandi errors
     */

    // this is just the same as the booking creator
    public void confirmBooking(String userID, String flightID) { 

        // annars getum vid buid til valid bookingID 
        Flight flight = flightRepo.getFlightById(flightID); // na i flight objectid

        if (flight == null) { // athugum hvort flugid se til
            System.out.println("Flugid er ekki til!!! booking ekki mogulegt!!!");
        }
        if (flight.getAvailableSeats() <= 0) { // athugum hvort laus saeti se til
            System.out.println("Engin laus saeti til! booking ekki mogulegt!!!");
        }

        Booking booking = new Booking(flightID, userID);
        if (bookingRepo.containsBooking(booking.getBookingID())) { // athugum hvort bookingID se til i databaseinu
            System.out.println("BookingID er til i databaseinu!!! booking mogulegt!!!");
            bookingRepo.confirmBooking(booking); // add booking to the database
            flightRepo.decrementAvailableSeats(flightID);
            return;
        }
        System.out.println("BookingID er ekki til i databaseinu!!! booking ekki mogulegt!!!");
 
    }

    public void deleteBooking(String bookingID) {
        bookingRepo.deleteBooking(bookingID); // delete booking from the database

        // decrementa valueid i database:
        flightRepo.incrementAvailableSeats(bookingID); // this should be the flightID, but we need to get it from the booking object
    }

    public Booking[] getBookings() {
        return bookingRepo.getBookings(); // aetti ad na i allar current bookings i databaseinu
    }


    public boolean containsBooking(String bookingID) {
        return bookingRepo.containsBooking(bookingID); // athugum hvort bookingID se til i databaseinu
    }
}
