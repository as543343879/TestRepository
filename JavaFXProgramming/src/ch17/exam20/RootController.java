
package ch17.exam20;

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
    private ListView<Phone> listView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listView.setCellFactory(new Callback<ListView<Phone>, ListCell<Phone>>(){ // 셀을 하나씩 만들어주는 cellfactory메소드를 호출하여 callback객체를 셀로하여 매개변수로 준다.
            @Override                                                                   //cellfactory(callback)은 fxml을 로딩하여 phone의 객체를 받아 listview에 cell을 만들어 넣는다.
            public ListCell<Phone> call(ListView<Phone> param) {   //phone객체가 셀에 대입될때 call 메소드가 실행되고 listcell객체 형태로 변형되어 들어간다.
             
                ListCell<Phone> listcell = new ListCell<Phone>(){
                    @Override
                    protected void updateItem(Phone item, boolean empty) {  
                        super.updateItem(item, empty);
                        if(empty)return;// empty가 트루면(null이 대입되니까) 실행하지 마라 phone객체 없으면 실행마라
                        try {
                        //Phone 객체를 받아서 listcell안에 어떤 내용이 들어갈지를 결정하는 메소드 updateItem
                        HBox hbox=(HBox)FXMLLoader.load(getClass().getResource("item.fxml"));     
                        ImageView phoneImage =(ImageView)hbox.lookup("#image");   //id로 fxml에 입력된 값을 가져 오려면 lookup을 사용
                        Label phoneName =(Label)hbox.lookup("#name");
                        Label phoneContent=(Label)hbox.lookup("#content");
                        
                        phoneImage.setImage(new Image(getClass().getResource("images/"+item.getImage()).toString()));
                        phoneName.setText(item.getName());
                        phoneContent.setText(item.getContent());
                        //Cell의 내용으로 설정
                        setGraphic(hbox);
                        
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        
                    
                    }
                    
                
                }; // listcell을 상속하는 익명객체를 만드는 코드
                return listcell;
            }
            
        });
        
        
        //선택 속성 감시
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Phone>(){
            @Override
            public void changed(ObservableValue<? extends Phone> observable, Phone oldValue, Phone newValue) {
            
                System.out.println(newValue.getName()+":"+newValue.getImage());
                
            }
        });
        
        
        //데이터셋팅
      ObservableList<Phone> value=FXCollections.observableArrayList();
      value.add(new Phone("phone01.png","갤럭시S1","삼성 스마트폰의 최초 모델입니다."));
      value.add(new Phone("phone02.png","갤럭시S2","삼성 스마트폰의 두번째 모델입니다."));
      value.add(new Phone("phone03.png","갤럭시S3","삼z성 스마트폰의 세번쨰 모델입니다."));
      listView.setItems(value);
    }    
    
}
