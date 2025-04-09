package com.example.Services.Flights;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import group2F.Flight;

public class FlightCell extends ListCell<Flight> {
	  private FXMLLoader loader;
    private FlightController controller;
		private Node root;

    @Override
    public void updateItem(Flight flight, boolean empty) {
        super.updateItem(flight, empty);

        if (empty || flight == null) {
            setText(null);
            setGraphic(null);  
        } else {
            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("/com/example/Services/Flights/flight-cell.fxml"));
                try {
                    root = loader.load();  
										System.out.println("THE ROOT IS" + root);
                } catch (Exception e) {
									System.out.println("DIDNT LoAD THE FXML");
                    e.printStackTrace();
                }
								controller = loader.getController();
            }
            controller.setFlight(flight);
            setGraphic(controller.getRoot());  
        }
    }

		public FlightController getController() {
			return controller;
		}
}
