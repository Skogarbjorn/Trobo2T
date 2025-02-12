module com.example {
    requires javafx.controls;
    requires javafx.fxml;
		requires transitive java.sql;

    opens com.example to javafx.fxml;
    exports com.example;
		exports lib;
}
