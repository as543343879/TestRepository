
package ch17.exam30;

import EX.*;
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
