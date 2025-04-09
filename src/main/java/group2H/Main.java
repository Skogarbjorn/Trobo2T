package group2H;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
    
        HotelRepo hotelRepo = new HotelRepo();
        BookingRepo bookingRepo = new BookingRepo();
        

        try {
            DBHelper.connect();
        } catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
            return;
        }
        
        
        while (true) {
            System.out.println("\n=== Hotel Booking Portal ===");
            System.out.println("1. Search Hotels");
            System.out.println("2. Book a Hotel");
            System.out.println("3. View Bookings");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.print("Enter location: ");
                    String loc = scanner.nextLine().trim();
                    if (loc.isEmpty()) {
                        System.out.println("Location cannot be empty.");
                        continue;
                    }
                  
                    if (loc.length() > 150) {
                        System.out.println("Location is too long.");
                        continue;
                    }

                    System.out.print("Number of guests: ");
                    int guests = Integer.parseInt(scanner.nextLine());
                    System.out.print("Check-in date (YYYY-MM-DD): ");
                    LocalDate checkIn = LocalDate.parse(scanner.nextLine());
                    System.out.print("Check-out date (YYYY-MM-DD): ");
                    LocalDate checkOut = LocalDate.parse(scanner.nextLine());

                    List<Hotel> hotels = hotelRepo.searchHotels(loc);
                    System.out.println("\nAvailable Hotels:");
                    if (hotels.isEmpty()) {
                        System.out.println("No hotels found");
                    } else {
                        for (Hotel h : hotels) {
                            System.out.println("- " + h.name + " | " + h.pricePerNight + " ISK/night");
                        }
                    }
                    break;
                    
                case "2":
                    System.out.print("Enter hotel name: ");
                    String hotelName = scanner.nextLine();
                    System.out.print("Number of guests: ");
                    guests = Integer.parseInt(scanner.nextLine());
                    System.out.print("Check-in date (YYYY-MM-DD): ");
                    checkIn = LocalDate.parse(scanner.nextLine());
                    System.out.print("Check-out date (YYYY-MM-DD): ");
                    checkOut = LocalDate.parse(scanner.nextLine());

                    Hotel h = hotelRepo.getHotelByName(hotelName);
                    if (h != null) {
                        int available = h.totalBeds - bookingRepo.countBookedbeds(h.id, checkIn, checkOut);
                        if (available >= guests) {
                            bookingRepo.createBooking(h.id, guests, checkIn, checkOut);
                            System.out.println("\nBooking confirmed for " + h.name);
                        } else {
                            System.out.println("\nNot enough beds available.");
                        }
                    } else {
                        System.out.println("\nHotel not found.");
                    }
                    break;
                    
                case "3":
                    List<Booking> bookings = bookingRepo.getAllBookings();
                    if (bookings.isEmpty()) {
                        System.out.println("\nNo bookings yet.");
                    } else {
                        for (Booking b : bookings) {
                            System.out.println("- " + b.hotelName + " | Guests: " + b.guests + " | " + b.checkIn + " to " + b.checkOut);
                        }
                    }
                    break;
                    
                case "4":
                    System.out.println("Goodbye!");
                    return;
                    
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
