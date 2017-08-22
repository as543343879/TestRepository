package ch17.exam41;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class RootController implements Initializable {

		@FXML
		private Button btnLogin;
		@FXML
		private StackPane stackPane;

		public static StackPane rootPane;//

		@Override
		public void initialize(URL url, ResourceBundle rb) {
				rootPane = stackPane;//
				btnLogin.setOnAction(e -> handleBtnLogin());

		}

		private void handleBtnLogin() {
				try {
						Parent parent = FXMLLoader.load(getClass().getResource("login.fxml"));
						stackPane.getChildren().add(parent); //이런식으로 하면 겹치는것
						
						
				} catch (IOException ex) {
						ex.printStackTrace();
				}
		}

}
