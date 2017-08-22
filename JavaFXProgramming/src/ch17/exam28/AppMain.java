package ch17.exam28;


import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;





public class AppMain extends Application{
    
   public static Stage primaryStage; //   rootcontroller로 primarystage 보내는방법 1

    @Override
    public void start(Stage primaryStage) throws Exception {
        AppMain.primaryStage= primaryStage;   // rootcontroller로 primarystage 보내는방법 1
        
        
        //how2 컨트롤안에 primarystage를 넣어주는것. 원래 방식으로 하면 얻을 수가 없음. getcontroller를 통해서 primary stage를 얻고 setprimarystage를 이용해 넣어준다.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("root.fxml"));
        Parent parent = loader.load();
        RootController controller = loader.getController();
        controller.setPrimaryStage(primaryStage); //primarystage 넣기
        //
        
        
        //Parent parent = FXMLLoader.load(getClass().getResource("root.fxml")); //정적메소드 load, 첫번째 태그에 대한 객체를 만들어서 번지를 리턴,getrosource는 url을 리턴//controller객체가 만들어지고 initialize까지 실행이 된다.
        //HBox hbox = (HBox)FXMLLoader.load(getClass().getResource("root.fxml"));
        //Scene scene = new Scene(hbox);  도 동일함
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setTitle("AppMain"); //창제목
        primaryStage.show();
        primaryStage.setOnCloseRequest(event ->{
            System.out.println("끄지마");
        });
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
