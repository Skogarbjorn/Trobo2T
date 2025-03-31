package com.example;

import daytrips.Daytrip;

public class Review {
	public String review;
	public int rating;
	public Daytrip daytrip;

	public Review(String review, int rating, Daytrip daytrip) {
		this.review = review;
		this.rating = rating;
		this.daytrip = daytrip;
	}

	public String toString() {
		return review + ", " + rating + ", " + daytrip;
	}
}
