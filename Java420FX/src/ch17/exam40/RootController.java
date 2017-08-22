package ch17.exam40;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RootController implements Initializable {

		@FXML
		private Button btnLogin;

		@Override
		public void initialize(URL url, ResourceBundle rb) {
				btnLogin.setOnAction(e->handleBtnLogin());
				
		}		

		private void handleBtnLogin() {
				try {
						Parent parent = FXMLLoader.load(getClass().getResource("login.fxml"));
						Scene scene = new Scene(parent);
						Stage primaryStage = (Stage)btnLogin.getScene().getWindow();
						primaryStage.setScene(scene);
						
				} catch (IOException ex) {
					ex.printStackTrace();
				}
		}
		
}
