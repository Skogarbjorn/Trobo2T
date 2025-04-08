package login;

import com.example.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;

public class LoginDialog extends Dialog<String> {
    @FXML
    private TextField fxUsernameInput;
    @FXML
    private DialogPane dialogPane;

    public LoginDialog() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("login-view.fxml"));
        loader.setController(this);
        
        try {
            DialogPane pane = loader.load();
            setDialogPane(pane);
            

            styleDialogButtons();
            
            fxUsernameInput.requestFocus();

            setResultConverter(e -> {
                if (e.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                    return fxUsernameInput.getText();
                }
                return null;
            });
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void styleDialogButtons() {
        Node signInButton = dialogPane.lookupButton(ButtonType.OK);
        if (signInButton != null) {
            signInButton.setStyle(
                "-fx-background-color: #4b6cb7; " +
                "-fx-text-fill: white; " +
                "-fx-font-weight: bold; " +
                "-fx-background-radius: 5; " +
                "-fx-padding: 5 15 5 15;"
            );
        }
        
        Node cancelButton = dialogPane.lookupButton(ButtonType.CANCEL);
        if (cancelButton != null) {
            cancelButton.setStyle(
                "-fx-background-color: #a8c0ff; " +
                "-fx-text-fill: #2a3f76; " +
                "-fx-font-weight: bold; " +
                "-fx-background-radius: 5; " +
                "-fx-padding: 5 15 5 15;"
            );
        }
    }
}