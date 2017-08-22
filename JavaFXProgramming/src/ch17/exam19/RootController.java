
package ch17.exam19;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;


public class RootController implements Initializable {

    @FXML
    private ListView<String> listView;
//ListView컨트롤
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> value=FXCollections.observableArrayList();
        for(int i=0;i<80;i++){
            value.add("갤럭시S"+i);
        }
        
        listView.setItems(value);
        
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            
                System.out.println(newValue);
            }
        });
    }    
    
}
