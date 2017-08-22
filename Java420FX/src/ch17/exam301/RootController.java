/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch17.exam301;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class RootController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnChangeCSS;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anchorPane.getStylesheets().add(getClass().getResource("root.css").toString());
        
        btnChangeCSS.setOnAction(e->{
            anchorPane.getStylesheets().clear();
            anchorPane.getStylesheets().add(getClass().getResource("root2.css").toString());
        });
    
    
    
    }    
    
}
