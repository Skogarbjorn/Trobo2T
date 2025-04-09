package group2F;

public interface BookingRepo {
    void confirmBooking(Booking booking);
    void deleteBooking(String bookingID);
    Booking[] getBookings(); // aetti ad na i allar current bookings i databaseinu

    boolean containsBooking(String bookingID);

}
