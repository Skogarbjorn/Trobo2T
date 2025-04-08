package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TravelApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create the tab pane
        TabPane tabPane = new TabPane();
        Tab flightTab = new Tab("Flights");
        Tab hotelTab = new Tab("Hotels");
        Tab daytripTab = new Tab("Daytrips");
        
        // Make tabs unclosable
        flightTab.setClosable(false);
        hotelTab.setClosable(false);
        daytripTab.setClosable(false);
        
        // Add content to tabs
        flightTab.setContent(createFlightContent());
        hotelTab.setContent(createHotelContent());
        daytripTab.setContent(createDaytripContent());
        
        tabPane.getTabs().addAll(flightTab, hotelTab, daytripTab);
        
        // Create search area
        VBox searchArea = new VBox(10);
        TextField searchField = new TextField();
        searchField.setPromptText("Search...");
        Button searchButton = new Button("Search");
        
        searchArea.getChildren().addAll(searchField, searchButton);
        
        // Main layout
        VBox root = new VBox(10);
        root.getChildren().addAll(tabPane, searchArea);
        
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Travel Booking System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private VBox createFlightContent() {
        VBox content = new VBox(10);
        content.getChildren().add(new Label("Flight search content goes here"));
        return content;
    }
    
    private VBox createHotelContent() {
        VBox content = new VBox(10);
        content.getChildren().add(new Label("Hotel search content goes here"));
        return content;
    }
    
    private VBox createDaytripContent() {
        VBox content = new VBox(10);
        content.getChildren().add(new Label("Daytrip search content goes here"));
        return content;
    }
}