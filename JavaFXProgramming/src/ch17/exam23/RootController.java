
package ch17.exam23;

import ch17.exam244.Phone;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RootController implements Initializable {

    @FXML
    private ListView<String> listView;
    @FXML
    private TableView<Phone> tableView;
    @FXML
    private ImageView imageView;
    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<String> data1=FXCollections.observableArrayList();
        data1.add("갤럭시s1");
        data1.add("갤럭시s2");
        data1.add("갤럭시s3");
        data1.add("갤럭시s4");
        data1.add("갤럭시s5");
        data1.add("갤럭시s6");
        listView.setItems(data1);
        
        //속성감시
        listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println(newValue.intValue());
                tableView.getSelectionModel().select(newValue.intValue());
                tableView.scrollTo(newValue.intValue());//해당인덱스까지 스크롤시켜라
            }
        });
        
        //--------------------------------------------------------------------------------
        
        
        TableColumn tc0=tableView.getColumns().get(0);
        TableColumn tc1=tableView.getColumns().get(1);
        
        
        tc0.setCellValueFactory(new PropertyValueFactory<Phone,String>("name")); //셀안에들어가는 값을 만드는 팩토리
        tc1.setCellValueFactory(new PropertyValueFactory<Phone,String>("image")); //<>에서 첫번째주어지는객체에 매개값필드의 값을 넣어준다.
        //<대상객체,매개값타입>
        
        ObservableList<Phone> list = FXCollections.observableArrayList(); //observablelist는 phone객체를 가지고 있는 컬렉션
        list.add(new Phone("phone01.png","갤럭시s1","첫번째모델"));
        list.add(new Phone("phone02.png","갤럭시s2","두번째모델"));
        list.add(new Phone("phone03.png","갤럭시s3","세번째모델"));
        list.add(new Phone("phone04.png","갤럭시s4","네번째모델"));
        list.add(new Phone("phone05.png","갤럭시s5","다섯번째모델"));
        list.add(new Phone("phone06.png","갤럭시s6","세번째모델"));
        
        tableView.setItems(list);
        
        
      
        
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Phone>() {
            @Override
            public void changed(ObservableValue<? extends Phone> observable, Phone oldValue, Phone newValue) {
                imageView.setImage(new Image(getClass().getResource("images/"+newValue.getImage()).toString()));
            
                
            }
        });
        
    }    
    
}
