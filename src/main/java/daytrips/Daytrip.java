package daytrips;

import java.sql.Date;

public class Daytrip {
	public int id;
	public Date day;
	public String description;
	public String location;
	public int availability;

	public Daytrip(int id, Date day, String description, String location, int availability) {
		this.id = id;
		this.day = day;
		this.description = description;
		this.location = location;
		this.availability = availability;
	}
	public Daytrip(Date day, String description, String location, int availability) {
		this.day = day;
		this.description = description;
		this.location = location;
		this.availability = availability;
	}

	public String toString() {
		return id + ", " + day + ", " + description + ", " + location + ", " + availability;
	}
}
