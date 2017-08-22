
package ch17.exam02;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppMain extends Application{
    public AppMain(){
        System.out.println("Constructor");
    }

    @Override
    public void start(Stage stage) throws Exception {
        VBox vbox = new VBox();
        vbox.setPrefWidth(400);
        vbox.setPrefHeight(300);
        
        Label label = new Label(); // label은 컨트롤
        label.setText("hello");
        vbox.getChildren().add(label);
        
        Button button = new Button();
        button.setText("확인");
        button.setPrefHeight(300);
        vbox.getChildren().add(button);
        
        Scene scene= new Scene(vbox);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args); 
        
    }
}
