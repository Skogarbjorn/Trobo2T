package lib;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;


public class Read {
	private Connection conn;

	public Read(String PATH) throws SQLException {
		this.conn = DriverManager.getConnection("jdbc:sqlite:" + PATH);
	}

	public Connection getConn() {
		return this.conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public ResultSet readAvailable() {
	}
}

