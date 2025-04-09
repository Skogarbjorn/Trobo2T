package group2F;

// helsta leidin til ad taka inputs
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        // separate files fyrir database
        String flightDbPath = "src\\main\\java\\com\\myCompany\\app\\SQL\\flights.db";
        String bookingDbPath = "src\\main\\java\\com\\myCompany\\app\\SQL\\bookings.db";

        // repo objects fyrir flights og booking
        FlightRepo flightRepo = new SQLiteFlightRepo(flightDbPath);
        BookingRepo bookingRepo = new SQLiteBookingRepo(bookingDbPath);


        Scanner scanner = new Scanner(System.in);
        System.out.println("Velkomin á flugsíðuna!");
        System.out.println("Vinsamlegast veldu aðgerð:");
        // basic terminal ui fyrir verkefnið? get svo bætt meiri options við, nota númer til að gera hluti og svo bara scanner fyrir aðrar variables
        while(true) {

            // basic menu
            System.out.println("\nMenu:");
            System.out.println("1. Create a Flight");
            System.out.println("2. Delete a Flight");
            System.out.println("3. Search Flights");
            System.out.println("4. Create a Booking");
            System.out.println("5. Delete a Booking");
            System.out.println("6. List Bookings");
            System.out.println("7. List Flights");
            System.out.println("8. populate DataBase");
            System.out.println("10. Exit");
            System.out.println();
            

            System.out.print("Enter your choice: ");

            // choice er valið sem notandi velur
            int choice = Integer.parseInt(scanner.nextLine());
            switch(choice) {
                case 1: // VIRKAR
                    System.out.print("Enter Flight ID: "); // flightID er yfirleitt enhv eins og "FL123"
                    String flightID = scanner.nextLine();
                    System.out.print("Enter Date (YYYY-MM-DD): "); // bara dagsettning ig? i string format
                    String date = scanner.nextLine();

                    System.out.print("Enter Destination: ");
                    String destination = scanner.nextLine();

                    // assumum bara að admin vilji alltaf on time flug og 100 available seats, þetta er einn flugvöllur þannig höfum departureLocation alltaf sem reykjavik
                    Flight flight = new Flight(flightID, date, "reykjavik", destination, "On Time", 100);
                    flightRepo.addFlight(flight);
                    break;

                case 2: // VIRKAR
                    System.out.print("Enter Flight ID to delete: ");
                    flightRepo.deleteFlight(scanner.nextLine());
                    break;

                case 3: // VIRKAR

                    System.out.print("Enter destination: ");
                    String searchDestination = scanner.nextLine();
                    System.out.print("Enter date: ");
                    String searchDate = scanner.nextLine();


                    Flight[] foundFlights = flightRepo.searchFlights(searchDestination, searchDate);
                    if (foundFlights.length == 0) {
                        System.out.println("No flights found.");
                        break;
                    }

                    System.out.println("Found Flights:");
                    for (int i = 0; i < foundFlights.length; i++) {
                        Flight f = foundFlights[i];

                        System.out.println((i + 1) + ": " + f.getFlightID() + " - " + f.getDate() +
                                        ", Destination: " + f.getDestination() +
                                        ", Available Seats: " + f.getAvailableSeats());
                    }
                    
                    System.out.print("Enter the flight number to book: ");
                    int selection;
                    try {
                        selection = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number entered.");
                        break;
                    }

                    if (selection < 1 || selection > foundFlights.length) {
                        System.out.println("Invalid selection. Please choose a number between 1 and " + foundFlights.length);
                        break;
                    }
                    
                    // get selected flight
                    Flight selectedFlight = foundFlights[selection - 1];
                    
                    // !!!!!!!!!!!super edge case checker thegar 2 manneskjur eru ad booka sidasta saeti og ein manneskjan naer saetinu fyrst, tha ma hin ekki booka!!!!!!!!!
                    if (selectedFlight.getAvailableSeats() <= 0) {
                        System.out.println("No available seats for this flight.");
                        break;
                    }
                    
                    // userID prompt
                    System.out.print("Enter your User ID: ");
                    String userID = scanner.nextLine();

                    // booking creating
                    Booking booking = new Booking(selectedFlight.getFlightID(), userID);
                    bookingRepo.confirmBooking(booking);
                    // decrement
                    flightRepo.decrementAvailableSeats(selectedFlight.getFlightID());
                    System.out.println("Booking confirmed for flight " + selectedFlight.getFlightID());
                    break;

                case 4: // VIRKAR
                    System.out.print("Enter Flight ID to book: ");
                    String bookingFlightID = scanner.nextLine();
                    System.out.print("Enter your User ID: ");
                    String myUserID = scanner.nextLine();
                    // tharf seat verification ef seats fyrir flug er ekki til
                    
                    Flight myFlight = flightRepo.getFlightById(bookingFlightID); // na i flight objectid
                    if (myFlight == null) { // athugum hvort flugid se til
                        System.out.println("Flugid er ekki til!!! booking ekki mogulegt!!!");
                        break;
                    }
                    else if (myFlight.getAvailableSeats() <= 0) { // athugum hvort laus saeti se til
                        System.out.println("Engin laus saeti til! booking ekki mogulegt!!!");
                        break;
                    }
                    // chekker fyrir ad ef booking var buid til

                    Booking myBooking123 = new Booking(bookingFlightID, myUserID);

                    bookingRepo.confirmBooking(myBooking123);
                    flightRepo.decrementAvailableSeats(bookingFlightID);
                    break;

                case 5: // VIRKAR
                    System.out.print("Enter flight ID of the booking: ");
                    String flightToBeIncremented = scanner.nextLine();
                    System.out.print("Enter booking ID to cancel: ");
                    String bookingToBeDeleted = scanner.nextLine();

                    Flight flight123 = flightRepo.getFlightById(flightToBeIncremented);
                    if (flight123 == null) {
                        System.out.println("Flight not found. Cannot cancel booking.");
                        break;
                    }
                    
                    if (!bookingRepo.containsBooking(bookingToBeDeleted)) {
                        System.out.println("Booking not found. Cannot cancel booking.");
                        break;
                    }

                    bookingRepo.deleteBooking(bookingToBeDeleted);
                    flightRepo.incrementAvailableSeats(flightToBeIncremented);
                    System.out.println("Booking has been deleted");
                    break;
                
                case 6: // VIRKAR
                    Booking[] bookings = bookingRepo.getBookings();
                    for (Booking b : bookings) {
                        System.out.println("Booking: " + b.getBookingID());
                    }
                    break;

                case 7: // VIRKAR
                    Flight[] flights = flightRepo.getFlights();
                    for (Flight f : flights) {
                        System.out.println("Flight: " + f.getFlightID());
                    }
                    break;

                case 8: // VIRKAR, nota thetta thegar thu notar verkefnid

                    Flight testFlight1 = new Flight("FL123", "2023-10-01", "Reykjavik", "New York", "On Time", 100);
                    Flight testFlight2 = new Flight("FL456", "2023-10-02", "Reykjavik", "London", "Delayed", 100);
                    Flight testFlight3 = new Flight("FL789", "2023-10-03", "Reykjavik", "Paris", "On Time", 100);
                    Flight testFlight4 = new Flight("FL101", "2023-10-04", "Reykjavik", "Berlin", "Cancelled", 100);    
                    Flight testFlight5 = new Flight("FL102", "2023-10-05", "Reykjavik", "Madrid", "On Time", 100);
                    Flight testFlight6 = new Flight("FL103", "2023-10-06", "Reykjavik", "Tokyo", "On Time", 100);
                    Flight testFlight7 = new Flight("FL104", "2023-10-02", "Reykjavik", "London", "On Time", 100);
                    Flight testFlight8 = new Flight("FL107", "2023-10-02", "Reykjavik", "London", "Delayed", 100);
                    flightRepo.addFlight(testFlight1);
                    flightRepo.addFlight(testFlight2);
                    flightRepo.addFlight(testFlight3);
                    flightRepo.addFlight(testFlight4);
                    flightRepo.addFlight(testFlight5);
                    flightRepo.addFlight(testFlight6);
                    flightRepo.addFlight(testFlight7);
                    flightRepo.addFlight(testFlight8);
                    System.out.println("Test data added to the database.");
                    break;

                case 10:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }


        }
    }
}
