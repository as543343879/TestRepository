
package ch17.exam16;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

public class RootController implements Initializable{
    
      
        @FXML private TextArea textArea1;
        
        @FXML private TextArea textArea2;
       
        

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //단방향 바인딩
        //textArea2.textProperty().bind(textArea1.textProperty());
        
        //양방향 바인딩
        
       // textArea2.textProperty().bindBidirectional(textArea1.textProperty());
       
       Bindings.bindBidirectional(textArea1.textProperty(),textArea2.textProperty());
       
        


         //textArea2.textProperty().unbind();언바인딩
    }
    
    

}
