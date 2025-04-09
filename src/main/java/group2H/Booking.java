package group2H;

import java.time.LocalDate;

public class Booking {
    public int hotelId;
    public String hotelName;
    public int guests;
    public LocalDate checkIn;
    public LocalDate checkOut;

    public Booking(int hotelId, String hotelName, int guests, LocalDate checkIn, LocalDate checkOut) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.guests = guests;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

		public String toString() {
			return "HotelId: " + hotelId + ", Hotel Name: " + hotelName + ", Number of guests: " + guests + ", Check in: " + checkIn + ", Check out: " + checkOut;
		}
}
