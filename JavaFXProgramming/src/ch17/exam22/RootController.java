
package ch17.exam22;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RootController implements Initializable {

    @FXML
    private TableView <Phone>tableView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn tc0=tableView.getColumns().get(0);
        TableColumn tc1=tableView.getColumns().get(1);
        TableColumn tc2=tableView.getColumns().get(2);
        
        tc0.setCellValueFactory(new PropertyValueFactory<Phone,String>("name")); //셀안에들어가는 값을 만드는 팩토리
        tc1.setCellValueFactory(new PropertyValueFactory<Phone,String>("image")); //<>에서 첫번째주어지는객체에 매개값필드의 값을 넣어준다.
        tc2.setCellValueFactory(new PropertyValueFactory<Phone,String>("content"));//<대상객체,매개값타입>
        
        ObservableList<Phone> list = FXCollections.observableArrayList(); //observablelist는 phone객체를 가지고 있는 컬렉션
        list.add(new Phone("phone01.png","갤럭시s1","첫번째모델"));
        list.add(new Phone("phone02.png","갤럭시s1","두번째모델"));
        list.add(new Phone("phone03.png","갤럭시s1","세번째모델"));
        tableView.setItems(list);
        
        
    }    
    
}
