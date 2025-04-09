package com.example.Services.Hotels;

import group2H.Hotel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class HotelController {
	@FXML private HBox root;
	@FXML private Label hotelName;
	@FXML private Label hotelLocation;
	@FXML private Label hotelTotalBeds;
	@FXML private Label hotelPricePerNight;

	public HBox getRoot() {
		return root;
	}

	public void setHotel(Hotel hotel) {
		hotelName.setText(hotel.getName());
		hotelLocation.setText(hotel.getLocation());
		hotelTotalBeds.setText(Integer.toString(hotel.getTotalBeds()));
		hotelPricePerNight.setText(Double.toString(hotel.getPricePerNight()));
	}
}
