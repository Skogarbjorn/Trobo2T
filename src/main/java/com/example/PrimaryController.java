package com.example;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import lib.Read;

public class PrimaryController {

	@FXML
	private void switchToSecondary(ActionEvent event) throws IOException {
		Button b = (Button) event.getSource();
		int x = GridPane.getRowIndex(b);
		int y = GridPane.getColumnIndex(b);
	}

	public void initalize() {
		try {
			Read gamer = new Read("test.db");
			System.out.println(gamer.getConn());
		} catch (SQLException e) {
			System.err.println(e.getErrorCode());
		}
	}
}
