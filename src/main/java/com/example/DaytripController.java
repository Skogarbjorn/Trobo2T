package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import daytrips.Daytrips;
import daytrips.Daytrip;
import javafx.util.Pair;
import lib.SearchResultWrapper;
import java.util.stream.Collectors;

public class DaytripController {
	private Daytrips daytrips;

	public ArrayList<SearchResultWrapper> searchAll() {
		ArrayList<Daytrip> results = daytrips.fetchDaytrips();
		return wrapResults(results);
	}

	public ArrayList<SearchResultWrapper> search(HashMap<String, String> filter) {
		ArrayList<Daytrip> results = daytrips.fetchDaytrips(filter);
		return wrapResults(results);
	}

	public void add(Daytrip daytrip) {
		daytrip.id = daytrips.addDaytrip(daytrip);
	}

	public void book(Daytrip daytrip, User user) {
		daytrips.bookUser(daytrip, user);
	}

	public void review(Review review, User user) {
		daytrips.leaveReview(review, user);
	}

	public Pair<ArrayList<Daytrip>, ArrayList<Review>> getUserProperties(User user) {
		return daytrips.fetchUserProperties(user);
	}

	public ArrayList<Review> getReviews() {
		return daytrips.fetchReviews();
	}

	public HashMap<Integer, Integer> getBookings() {
		return daytrips.fetchBookings();
	}

	public ArrayList<User> getUsers() {
		return daytrips.fetchUsers();
	}

	public int createUser(User user) {
		return daytrips.createUser(user);
	}

	public DaytripController() {
		this.daytrips = new Daytrips();
	}

	private ArrayList<SearchResultWrapper> wrapResults(ArrayList<Daytrip> toBeWrapped) {
		ArrayList<SearchResultWrapper> wrappedResults =
			(ArrayList<SearchResultWrapper>)
			toBeWrapped.stream()
			.map(daytrip -> new SearchResultWrapper(daytrip, "daytrip"))
			.collect(Collectors.toCollection(ArrayList::new));

		return wrappedResults;
	}
}
