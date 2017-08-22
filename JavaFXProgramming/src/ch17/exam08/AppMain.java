
package ch17.exam08;
//프로그램적으로 레이아웃 만들기

import ch17.exam05.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class AppMain extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("root.fxml")); //정적메소드 load, 첫번째 태그에 대한 객체를 만들어서 번지를 리턴,getrosource는 url을 리턴
        //HBox hbox = (HBox)FXMLLoader.load(getClass().getResource("root.fxml"));
        //Scene scene = new Scene(hbox);  도 동일함
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setTitle("AppMain"); //창제목
        primaryStage.show();
        
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
