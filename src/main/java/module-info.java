module com.example {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.base;
		requires transitive java.sql;

    opens com.example to javafx.fxml;
		opens com.example.Services.Hotels to javafx.fxml;
		opens com.example.Services.Daytrips to javafx.fxml;
		opens login to javafx.fxml;
    exports com.example;
		exports lib;
		exports services;
		exports login;
		exports daytrips;
		exports group2H;
}
