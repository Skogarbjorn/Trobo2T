package com.example.Services.Daytrips;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import daytrips.Daytrip;

public class DaytripCell extends ListCell<Daytrip> {
	  private FXMLLoader loader;
    private DaytripController controller;
		private Node root;

    @Override
    public void updateItem(Daytrip daytrip, boolean empty) {
        super.updateItem(daytrip, empty);

        if (empty || daytrip == null) {
            setText(null);
            setGraphic(null);  
        } else {
            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("/com/example/Services/Daytrips/daytrip-cell.fxml"));
                try {
                    root = loader.load();  
										System.out.println(root);
                } catch (Exception e) {
									System.out.println("DIDNT LoAD THE FXML");
                    e.printStackTrace();
                }
								controller = loader.getController();
            }
            controller.setDaytrip(daytrip);
            setGraphic(controller.getRoot());  
        }
    }

		public DaytripController getController() {
			return controller;
		}
}
