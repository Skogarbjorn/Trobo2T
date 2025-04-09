package com.example.Services.Hotels;

import group2H.Hotel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

public class HotelCell extends ListCell<Hotel> {
	  private FXMLLoader loader;
    private HotelController controller;
		private Node root;

    @Override
    public void updateItem(Hotel hotel, boolean empty) {
        super.updateItem(hotel, empty);

        if (empty || hotel == null) {
            setText(null);
            setGraphic(null);  
        } else {
            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("/com/example/Services/Hotels/hotel-cell.fxml"));
                try {
                    root = loader.load();  
										System.out.println(root);
                } catch (Exception e) {
									System.out.println("DIDNT LoAD THE FXML");
                    e.printStackTrace();
                }
								controller = loader.getController();
            }
            controller.setHotel(hotel);
            setGraphic(controller.getRoot());  
        }
    }
}
