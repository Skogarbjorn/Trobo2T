package com.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;
import login.LoginDialog;

import java.io.IOException;

public class PrimaryController {
    private User me;
    @FXML
    private Button travelButton;
    private DaytripController newController;

    @FXML
    private void handleCreateUser() throws IOException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("DayTrip Planner Login");

        // 2. Load the FXML content
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/login-view.fxml"));
        // 3. Set the dialog pane from FXML
        dialog.setDialogPane(loader.load());

        // 4. Optional: Get the controller if you need to access UI elements
        LoginDialog controller = loader.getController();

        // 5. Show the dialog and wait for response
        dialog.showAndWait().ifPresent(response -> {
            System.out.println(response);
            if (response.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                // Handle OK button click
                me = new User("gamer", "gamer@gamer.com", "sigma");
                CurrentUser.setUser(me);
                travelButton.setDisable(false);
                // If you need the username:
                // String username = controller.getUsernameInput();
            } else {
                // Handle Cancel button click
                System.out.println("User canceled");
            }
        });
    }

    @FXML
    private void handleGoToTravelApp() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/travel-app.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Travel Booking");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        newController = new DaytripController();
    }
}
