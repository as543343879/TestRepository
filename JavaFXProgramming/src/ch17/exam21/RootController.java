
package ch17.exam21;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class RootController implements Initializable {

    @FXML
    private ListView<Food> ListView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ListView.setCellFactory(new Callback<ListView<Food>, ListCell<Food>>() {
            @Override
            public ListCell<Food> call(ListView<Food> param) {
                ListCell<Food> listcell=new ListCell<Food>(){
                    @Override
                    protected void updateItem(Food item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty)return;// empty가 트루면(null이 대입되니까) 실행하지 마라 phone객체 없으면 실행마라
                        try {
                            super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                            if(empty)return;
                            HBox hbox=(HBox)FXMLLoader.load(getClass().getResource("review.fxml"));
                            ImageView image=(ImageView)hbox.lookup("#image");
                             Label Name =(Label)hbox.lookup("#name");
                             ImageView scoreimage=(ImageView)hbox.lookup("#scoreImage");
                            Label description=(Label)hbox.lookup("#description");
                            
                            image.setImage(new Image(getClass().getResource("image/"+item.getImage()).toString()));
                            Name.setText(item.getName());
                            scoreimage.setImage(new Image(getClass().getResource("image/star"+item.getScore()+".png").toString()));
                            description.setText(item.getDescription());
                            setGraphic(hbox);
                        } catch (IOException ex) {
                            
                        }
                        
                    }
               
                };
                return listcell;
                
            }
        });
        
        
                ListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Food>() {
            @Override
            public void changed(ObservableValue<? extends Food> observable, Food oldValue, Food newValue) {
                System.out.println(newValue.getName()+"의 평점은 "+newValue.getScore()+"점입니다.");
            }
        });
                
                //클릭 이벤트 처리
             //   ListView.setOnMouseClicked(e->{
              //  Phone phone=
             //   });
        
        
      ObservableList<Food> value=FXCollections.observableArrayList();
      value.add(new Food("food01.png","삼겹살",1,"맛있다."));
      value.add(new Food("food03.png","삼겹살",2,"맛있다."));
      value.add(new Food("food04.png","삼겹살",3,"맛있다."));
      value.add(new Food("food05.png","삼겹살",4,"맛있다."));
      value.add(new Food("food06.png","삼겹살",5,"맛있다."));
     
      ListView.setItems(value);
        
    }    
    
}
