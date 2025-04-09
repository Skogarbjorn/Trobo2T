package group2H;

public class Hotel {
    public int id;
    public String name;
    public String location;
    public int totalBeds;
    public int pricePerNight;

    public Hotel(int id, String name, String location, int totalBeds, int pricePerNight) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.totalBeds = totalBeds;
        this.pricePerNight = pricePerNight;
    }
        // Getter for name
        public String getName() {
            return name;
        }
    
        // Getter for hotelId
        public int getHotelId() {
            return id;
        }
    
        // Getter for location
        public String getLocation() {
            return location;
        }
    
        // Getter for totalBeds
        public int getTotalBeds() {
            return totalBeds;
        }
    
        // Getter for pricePerNight
        public double getPricePerNight() {
            return pricePerNight;
        }
    }
