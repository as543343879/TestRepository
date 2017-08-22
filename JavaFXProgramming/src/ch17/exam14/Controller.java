
package ch17.exam14;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class Controller implements Initializable{
    
    @FXML private Button btn1;
    @FXML private Button btn2;
    @FXML private Button btn3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               System.out.println("ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ");
            }
        });
        
        btn2.setOnAction(event->{ 
        System.out.println("ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ");
        });
    }
    
    public void handle3(ActionEvent event){
     
        System.out.println("ㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷ");
    }
  

}
