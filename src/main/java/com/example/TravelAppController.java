package com.example;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.Services.Daytrips.*;
import com.example.Services.Hotels.*;
import com.example.Services.Flights.*;

import group2H.BookingRepo;
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
import group2F.FlightService;
import daytrips.Daytrip;
import group2F.*;

public class TravelAppController {
	  @FXML private HBox contentField;
    @FXML private TabPane tabPane;
    @FXML private TextField searchField;
		@FXML private Button nextButton;
		@FXML private Button confirmButton;
		@FXML private ListView<Hotel> hotelList;
		@FXML private ListView<Daytrip> daytripList;
		@FXML private ListView<Flight> flightList;

		private int state = 0;

		private HotelRepo hotelRepo;
		private Daytrips daytrips;
		private FlightService flightService;

		private BookingService flightsBookingService;
		private BookingRepo hotelBookingRepo;

		private Filter filter;

		private Map<Hotel, HotelController> controllerMap = new HashMap<>();
    
		@FXML
		void initialize() {
			confirmButton.setDisable(true);

			tabPane.getSelectionModel().selectedIndexProperty().addListener(
					(observable, oldTab, newTab) -> {
						if (newTab != null) {
							hideOldTab(oldTab);
							handleTabChange(newTab);
						}
					}
			);

			hotelList.getSelectionModel().selectedItemProperty().addListener(
					(obs, o, n) -> {
						updateCheckoutButton();
					});
			daytripList.getSelectionModel().selectedItemProperty().addListener(
					(obs, o, n) -> {
						updateCheckoutButton();
					});
			flightList.getSelectionModel().selectedItemProperty().addListener(
					(obs, o, n) -> {
						updateCheckoutButton();
					});

			hotelRepo = new HotelRepo();
			daytrips = new Daytrips();
			flightService = new FlightService();

			flightsBookingService = new BookingService();
			hotelBookingRepo = new BookingRepo();

			filter = new Filter();
			contentField.getChildren().add(filter.getView());

			populateDaytrips();
			populateFlights();
			populate();
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
					flightList.setVisible(false);
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
			if (
					daytripList.getItems().isEmpty() ||
					daytripList.getSelectionModel().getSelectedItem() == null ||
					hotelList.getItems().isEmpty() ||
					hotelList.getSelectionModel().getSelectedItem() == null ||
					flightList.getItems().isEmpty() ||
					flightList.getSelectionModel().getSelectedItem() == null
					) {
				nextButton.setDisable(true);
			} else {
				nextButton.setDisable(false);
			}

			switch (state) {
				case 0:
					nextButton.setDisable(false);
					flightList.setVisible(true);

					if (flightList.getItems().isEmpty()) {
						ObservableList<Flight> flightObsList = FXCollections.observableList(new ArrayList<>());
						Flight[] flightArray = flightService.getFlights();
						for (int i = 0; i < flightArray.length; i++) {
							flightObsList.add(flightArray[i]);
						}
						flightList.setItems(flightObsList);
						flightList.setCellFactory(listView -> new FlightCell());
					}

					break;
				case 1:
					nextButton.setDisable(false);
					hotelList.setVisible(true);

					if (hotelList.getItems().isEmpty()) {
						ObservableList<Hotel> hotelObsList = FXCollections.observableList(hotelRepo.searchHotels("Reykjavik"));
						hotelList.setItems(hotelObsList);
						hotelList.setCellFactory(listView -> new HotelCell(controllerMap));
					}

					break;
				case 2:
					nextButton.setDisable(true);
					daytripList.setVisible(true);

					if (daytripList.getItems().isEmpty()) {
						ObservableList<Daytrip> daytripObsList = FXCollections.observableList(daytrips.fetchDaytrips());
						daytripList.setItems(daytripObsList);
						daytripList.setCellFactory(listView -> new DaytripCell());
					}

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
		private void populateFlights() {
			System.out.println("ran once");
		}

		@FXML
		public void handleNextTab() {
			tabPane.getSelectionModel().selectNext();
		}

		@FXML
		private void handleConfirm() {
			HotelController hotelController = controllerMap.get(hotelList.getSelectionModel().getSelectedItem());

			try {
				hotelBookingRepo.createBooking(
						hotelList.getSelectionModel().getSelectedItem().getHotelId(),
						hotelController.getGuestNum(), 
						hotelController.getCheckIn(),
						hotelController.getCheckOut());

				flightsBookingService.confirmBooking(CurrentUser.user.username, flightList.getSelectionModel().getSelectedItem().getFlightID());

				daytrips.bookUser(daytripList.getSelectionModel().getSelectedItem(), CurrentUser.user);
			} catch (Exception e) {
				System.out.println(e);
			}

			hotelBookingRepo.getAllBookings().forEach(booking -> System.out.println(booking));
		}

		private void updateCheckoutButton() {
			if (
					daytripList.getSelectionModel().getSelectedItem() != null &&
					flightList.getSelectionModel().getSelectedItem() != null &&
					hotelList.getSelectionModel().getSelectedItem() != null
			 ) {
				confirmButton.setDisable(false);
		 } else {
			 confirmButton.setDisable(true);
		 }
		}
}
