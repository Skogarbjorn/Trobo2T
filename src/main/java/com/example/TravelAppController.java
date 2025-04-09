package com.example;

import com.example.Services.Hotels.HotelCell;

import group2H.Hotel;
import group2H.HotelRepo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class TravelAppController {
	  @FXML private HBox contentField;
    @FXML private TabPane tabPane;
    @FXML private TextField searchField;
		@FXML private ListView<Hotel> hotelList;

		private int state;

		private HotelRepo hotelRepo;

		private Filter filter;
    
		@FXML
		void initialize() {
			// Adds a listener to the TabPane to check for changes to which tab is selected
			// dont ask why this seems not to be possible in fxml code
			tabPane.getSelectionModel().selectedIndexProperty().addListener(
					(observable, oldTab, newTab) -> {
						if (newTab != null) {
							handleTabChange(newTab);
						}
					}
			);

			hotelRepo = new HotelRepo();

			hotelList.getSelectionModel().selectedItemProperty().addListener(
					(observable, oldthing, newthing) -> {
						System.out.println(newthing.getName());
			});

			filter = new Filter();
			contentField.getChildren().add(filter.getView());
		}

		@FXML
		public void handleSearch() {
			filter.setService("flights");
		}

		public void handleTabChange(Number tab) {
			state = (int) tab;
			populate();
		}

		private void populate() {
			switch (state) {
				case 0:
					break;
				case 1:
					ObservableList<Hotel> observableList = FXCollections.observableList(hotelRepo.searchHotels("Reykjavik"));
					observableList.forEach(thing -> System.out.println(thing));
					hotelList.setItems(observableList);
					hotelList.setCellFactory(listView -> new HotelCell());

					break;
				case 2:
					break;
			}
		}
}
