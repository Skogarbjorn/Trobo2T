package com.example.Services.Flights;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import daytrips.Daytrip;
import group2F.Flight;

public class FlightController {
	@FXML private HBox root;
	@FXML private Label date;
	@FXML private Label departureLocation;
	@FXML private Label destination;
	@FXML private Label availableSeats;
	@FXML private Label status;

	public HBox getRoot() {
		return root;
	}

	public void setFlight(Flight flight) {
		date.setText(flight.getDate());
		departureLocation.setText(flight.getDepartureLocation());
		destination.setText(flight.getDestination());
		availableSeats.setText(Integer.toString(flight.getAvailableSeats()));
		status.setText(flight.getStatus());
	}
}
