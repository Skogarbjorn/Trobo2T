package com.example;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class FilterController {
	@FXML
	private Label filterLabel;
	@FXML
	private HBox root;

	public Label getLabel() {
		return filterLabel;
	}

	public HBox getView() {
		return root;
	}
}
