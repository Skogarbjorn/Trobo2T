package com.example;

import java.sql.Date;

import com.example.Services.Daytrips.DaytripCell;
import com.example.Services.Hotels.HotelCell;
import group2H.Hotel;
import group2H.HotelRepo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import daytrips.Daytrips;
import daytrips.Daytrip;

public class TravelAppController {
	  @FXML private HBox contentField;
    @FXML private TabPane tabPane;
    @FXML private TextField searchField;
		@FXML private Button nextButton;
		@FXML private ListView<Hotel> hotelList;
		@FXML private ListView<Daytrip> daytripList;

		private int state;

		private HotelRepo hotelRepo;
		private Daytrips daytrips;

		private Filter filter;

		private Hotel selectedHotel;
		private Daytrip selectedDaytrip;
    
		@FXML
		void initialize() {
			// Adds a listener to the TabPane to check for changes to which tab is selected
			// dont ask why this seems not to be possible in fxml code
			tabPane.getSelectionModel().selectedIndexProperty().addListener(
					(observable, oldTab, newTab) -> {
						if (newTab != null) {
							hideOldTab(oldTab);
							handleTabChange(newTab);
						}
					}
			);

			hotelRepo = new HotelRepo();
			daytrips = new Daytrips();

			filter = new Filter();
			contentField.getChildren().add(filter.getView());

			populateDaytrips();
		}

		@FXML
		public void handleSearch() {
			filter.setService("flights");
		}

		public void handleTabChange(Number tab) {
			state = (int) tab;
			populate();
		}

		public void hideOldTab(Number tab) {
			switch ((int) tab) {
				case 0:
					break;
				case 1:
					hotelList.setVisible(false);
					break;
				case 2:
					daytripList.setVisible(false);
				default:
					break;
			}
		}

		private void populate() {
			switch (state) {
				case 0:
					nextButton.setText("Next");
					break;
				case 1:
					nextButton.setText("Next");
					hotelList.setVisible(true);

					ObservableList<Hotel> hotelObsList = FXCollections.observableList(hotelRepo.searchHotels("Reykjavik"));
					hotelList.setItems(hotelObsList);
					hotelList.setCellFactory(listView -> new HotelCell());

					break;
				case 2:
					nextButton.setText("Confirm and checkout");
					daytripList.setVisible(true);

					ObservableList<Daytrip> daytripObsList = FXCollections.observableList(daytrips.fetchDaytrips());
					daytripList.setItems(daytripObsList);
					daytripList.setCellFactory(listView -> new DaytripCell());

					break;
			}
		}

		private void populateDaytrips() {
			Daytrip one = new Daytrip(new Date(System.currentTimeMillis()), "very fun right now", "HÍ", 20);
			Daytrip two = new Daytrip(new Date(System.currentTimeMillis() + 10000), "zoo", "Reykjavik", 100);
			Daytrip three = new Daytrip(new Date(System.currentTimeMillis() + 100000), "sightseeing", "Greenland", 15);

			daytrips.addDaytrip(one);
			daytrips.addDaytrip(two);
			daytrips.addDaytrip(three);
		}

		@FXML
		public void handleNextTabOrConfirm() {
			if (tabPane.getSelectionModel().getSelectedIndex() == 2) {
				confirm();
				return;
			}
			tabPane.getSelectionModel().selectNext();
		}

		private void confirm() {
			System.out.println("booking confirmed");
			System.out.println(daytripList.getSelectionModel().getSelectedItem());
			System.out.println(hotelList.getSelectionModel().getSelectedItem());
		}
}
