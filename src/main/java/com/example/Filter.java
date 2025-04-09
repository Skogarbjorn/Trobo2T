package com.example;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Filter {
	private HBox view;
	private FilterController controller;

	public Filter() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("filter.fxml"));
		try {
			view = loader.load();
			controller = loader.getController();
		} catch (IOException e) {
			System.out.println(e);
		}
		System.out.println("kkkkkkkk");
		System.out.println(controller.getLabel());
	}

	public Node getView() {
		return view;
	}

	public void setService(String service) {
		controller.getLabel().setText("flights");
	}
}
