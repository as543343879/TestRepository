
package ch17.exam01;

import java.util.List;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppMain extends Application{
    public AppMain(){
        System.out.println("Constructor");
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println(Thread.currentThread().getName());
    
        stage.show();
    }

    @Override
    public void init() throws Exception {
     System.out.println("init실행");   
     Parameters params = getParameters(); // 메인클래스의 매개값을 얻는다.
     List<String> list = params.getRaw();
     for(String param:list){
      System.out.println(param);   
     }
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Stop");   
    }
    
    

    
    

    public static void main(String[] args) {
        launch(args); 
        
    }
}
