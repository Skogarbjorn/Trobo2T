module com.example {
    requires javafx.controls;
    requires javafx.fxml;
		requires transitive java.sql;

    opens com.example to javafx.fxml;
		opens login to javafx.fxml;
    exports com.example;
		exports lib;
		exports services;
		exports login;
		exports daytrips;
}
