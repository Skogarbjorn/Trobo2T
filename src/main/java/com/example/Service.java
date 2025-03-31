package com.example;

import java.util.ArrayList;
import java.util.HashMap;

import lib.SearchResultWrapper;

public interface Service {
	ArrayList<SearchResultWrapper> search(HashMap<String, String> filter);
	ArrayList<SearchResultWrapper> searchAll();
}
