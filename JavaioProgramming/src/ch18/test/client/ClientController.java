/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch18.test.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class ClientController implements Initializable {

		@FXML
		private Button btnConn;
		@FXML
		private TextField txtInput;
		@FXML
		private Button btnSend;
		@FXML
		private TextArea txtDisplay;

		/**
		 * Initializes the controller class.
		 */
		@Override
		public void initialize(URL url, ResourceBundle rb) {
				// TODO
		}		
		
}
