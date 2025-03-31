package login;

import com.example.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;

public class LoginDialog extends Dialog<String> {
	@FXML
	private TextField fxUsernameInput;

	public LoginDialog() {
		FXMLLoader loader = new FXMLLoader(App.class.getResource("login-view.fxml"));
		loader.setController(this);
		try {
			setDialogPane(loader.load());
		} catch (Exception e) {
			e.printStackTrace();
		}

		fxUsernameInput.requestFocus();

		setResultConverter(e -> {
			if (e.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
				return fxUsernameInput.getText();
			}
			return null;
		});
	}
}
