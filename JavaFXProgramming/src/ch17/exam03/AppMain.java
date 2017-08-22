
package ch17.exam03;
//프로그램적으로 레이아웃 만들기
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AppMain extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(40)); //hbox안쪽의 여백느낌
        hbox.setSpacing(10); //콘트롤 간의 간격
        
        TextField textField = new TextField();
        textField.setPrefWidth(200);
        textField.setPrefHeight(100);
        
        Button button = new Button("확인");
        
        ObservableList list =hbox.getChildren();
        list.add(textField);
        list.add(button);
        
        
        
        Scene scene = new Scene(hbox);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
