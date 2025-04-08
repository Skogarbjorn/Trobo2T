package com.example;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import daytrips.Daytrip;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;
import lib.SearchResultWrapper;

public class PrimaryController {
	private User me;
	private DaytripController newController;
	private Daytrip test;
	private Review reviewTest;
	private ArrayList<Review> reviews;
	private HashMap<Integer, Integer> bookings;
	private ArrayList<SearchResultWrapper> result;
	private Pair<ArrayList<Daytrip>, ArrayList<Review>> props;

	@FXML
	private void handleCreateUser() throws IOException {
		me = new User("gamer", "gamer@gamer.com", "sigma");
		me.setDaytripsId(newController.createUser(me));
	}

	@FXML
	private void handleListUsers() throws IOException {
		ArrayList<User> users = newController.getUsers();
		users.forEach(thing -> {
			System.out.println("USER: " + thing);
		});
	}

	@FXML
	private void handleAddDaytrip() {
		test = new Daytrip(new Date(0), "sigma", "iceland", 20);
		newController.add(test);
	}

	@FXML
	private void handleBook() {
		newController.book(test, me);
	}

	@FXML
	private void handleReview() {
		reviewTest = new Review("its ok", 6, test);
		newController.review(reviewTest, me);
	}

	@FXML
	private void handleListReviews() {
		reviews = newController.getReviews();
		reviews.forEach(thing -> {
			System.out.println("REVIEW: " + thing);
		});
	}

	@FXML
	private void handleListBookings() {
		bookings = newController.getBookings();
		bookings.forEach((key, value) -> {
			System.out.println("BOOKING: user - " + key + ", daytripid - " + value);
		});
	}

	@FXML
	private void handleListDaytrips() {
		result = newController.searchAll();
		result.forEach(thing -> {
			System.out.println("DAYTRIP: " + thing);
		});
	}


    @FXML
    private void handleGoToTravelApp() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/travel-app.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Travel Booking");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@FXML
	private void handleUserProperties() {
		props = newController.getUserProperties(me);

		props.getKey().forEach(thing -> {
			System.out.println("you have ordered this: " + thing);
		});
		props.getValue().forEach(thing -> {
			System.out.println("you have reviewed this: " + thing);
		});
	}

	public void initialize() {
		newController = new DaytripController();
	}
}
