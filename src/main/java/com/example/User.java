package com.example;

public class User {
	public String username;
	public String email;
	public String password;

	public int flights_id;
	public int accommodations_id;
	public int daytrips_id;

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public void setFlightsId(int id) {
		this.flights_id = id;
	}

	public void setAccommodationsId(int id) {
		this.accommodations_id = id;
	}

	public void setDaytripsId(int id) {
		this.daytrips_id = id;
	}

	public String toString() {
		return username + ", " + email + ", " + password + ", " + daytrips_id;
	}
}
