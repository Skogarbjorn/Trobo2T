package com.example.Services.Hotels;

import java.time.LocalDate;

import group2H.Hotel;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class HotelController {
	@FXML private HBox root;
	@FXML private Label hotelName;
	@FXML private Label hotelLocation;
	@FXML private Label hotelTotalBeds;
	@FXML private Label hotelPricePerNight;
	@FXML private TextField numberField;
	@FXML private DatePicker checkInField;
	@FXML private DatePicker checkOutField;

	public HBox getRoot() {
		return root;
	}

	public void setHotel(Hotel hotel) {
		hotelName.setText(hotel.getName());
		hotelLocation.setText(hotel.getLocation());
		hotelTotalBeds.setText(Integer.toString(hotel.getTotalBeds()));
		hotelPricePerNight.setText(Double.toString(hotel.getPricePerNight()));
	}

	public int getGuestNum() {
		return Integer.parseInt(numberField.getText());
	}

	public LocalDate getCheckIn() {
		System.out.println(checkInField.getValue());
		return checkInField.getValue();
	}

	public LocalDate getCheckOut() {
		return checkOutField.getValue();
	}
}
