package com.example.Services.Daytrips;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import daytrips.Daytrip;

public class DaytripController {
	@FXML private HBox root;
	@FXML private Label dayLabel;
	@FXML private Label descriptionLabel;
	@FXML private Label locationLabel;
	@FXML private Label availabilityLabel;

	public HBox getRoot() {
		return root;
	}

	public void setDaytrip(Daytrip daytrip) {
		dayLabel.setText(daytrip.day.toString());
		descriptionLabel.setText(daytrip.description);
		locationLabel.setText(daytrip.location);
		availabilityLabel.setText(Integer.toString(daytrip.availability));
	}
}
