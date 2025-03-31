CREATE TABLE flight_time(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	departure TEXT,
	arrival TEXT,
	flight_id INTEGER NOT NULL,
	FOREIGN KEY (flight_id) REFERENCES flights(id));

CREATE TABLE flight_seats(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	available INTEGER,
	flight_id INTEGER NOT NULL,
	FOREIGN KEY (flight_id) REFERENCES flights(id));

CREATE TABLE flight_flights(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	company TEXT NOT NULL,
	departure_location TEXT NOT NULL,
	arrival_location TEXT NOT NULL);



CREATE TABLE hotel_services(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	max_people INTEGER,
	food INTEGER,
	acc_id INTEGER NOT NULL,
	FOREIGN KEY (acc_id) REFERENCES hotel_accommodations(id));

CREATE TABLE hotel_bookings(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	start_date TEXT,
	end_date TEXT,
	username TEXT NOT NULL,
	acc_id INTEGER NOT NULL,
	FOREIGN KEY (acc_id) REFERENCES hotel_accommodations(id));

CREATE TABLE hotel_accommodations(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	name TEXT NOT NULL,
	location TEXT);



CREATE TABLE daytrip_type(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	name TEXT NOT NULL,
	daytrip_id INTEGER NOT NULL,
	FOREIGN KEY (daytrip_id) REFERENCES daytrip_daytrips(id)
);

CREATE TABLE daytrip_bookings(
	daytrip_id INTEGER NOT NULL,
	user_id INTEGER NOT NULL,
	PRIMARY KEY (daytrip_id, user_id),
	FOREIGN KEY (daytrip_id) REFERENCES daytrip_daytrips(id),
	FOREIGN KEY (user_id) REFERENCES daytrip_users(id)
);

CREATE TABLE daytrip_reviews(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	review TEXT,
	rating INTEGER NOT NULL,
	daytrip_id INTEGER NOT NULL,
	user_id INTEGER NOT NULL,
	FOREIGN KEY (daytrip_id) REFERENCES daytrip_daytrips(id),
	FOREIGN KEY (user_id) REFERENCES daytrip_users(id)
);

CREATE TABLE daytrip_users(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	username VARCHAR(256) NOT NULL,
	email VARCHAR(256) NOT NULL,
	password VARCHAR(256) NOT NULL
);

CREATE TABLE daytrip_daytrips(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	day DATE NOT NULL,
	description TEXT,
	location TEXT NOT NULL,
	availability INTEGER NOT NULL
);
