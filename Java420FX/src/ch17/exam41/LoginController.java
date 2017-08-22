
package ch17.exam41;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class LoginController implements Initializable {

		@FXML
		private BorderPane login;
		@FXML
		private Button btnMain;

		@Override
		public void initialize(URL url, ResourceBundle rb) {
				btnMain.setOnAction(e->handleBtnMain(e));
		}		

		private void handleBtnMain(ActionEvent e) {
				
						//Parent parent=FXMLLoader.load(getClass().getResource("root"));
						
						//how1
						//RootController.rootPane.getChildren().remove(1);
						StackPane rootPane=(StackPane)btnMain.getScene().getRoot();
						rootPane.getChildren().remove(login);
						
						
		}
		
}
