package ch17.exam28;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RootController implements Initializable {

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {    // 두번째 방법 /primarystage 받아서 사용
        this.primaryStage = primaryStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void handleOpenFileChooser(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        //  File file = fileChooser.showOpenDialog(primaryStage); 두번째방법
        Button button = (Button) event.getSource();//세번째방법
        Scene scene = button.getScene();
        Stage stage = (Stage) scene.getWindow();
        File file = fileChooser.showOpenDialog(((Button) event.getSource()).getScene().getWindow()); //세번째 방법
        System.out.print(file.getPath());
    }

    @FXML
    private void handleSaveFileChooser(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(primaryStage);   // 두번째 방법
        System.out.print(file.getPath());
    }

    @FXML
    private void handleDirectoryChooser(ActionEvent event) { //event는 어디서 발생했는지 소스를 가지고 있음
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(((Button) event.getSource()).getScene().getWindow()); //세번째 방법 //버튼객체를 얻고 거기서 씬을 얻고 거기서 window를 얻어 stage를 얻어낸다.
        System.out.print(file.getPath());
    }

    @FXML
    private void handlePopup(ActionEvent event) throws Exception {
        //showNotification("알림","메세지가 도착했습니다.");
        showNotification("경고", "도둑이 침입했습니다.");

    }

    private void showNotification(String type, String message) throws Exception {
        Popup popup = new Popup();
        HBox hbox = (HBox) FXMLLoader.load(getClass().getResource("popup.fxml"));
        ImageView imgMessage = (ImageView) hbox.lookup("#imgMessage");
        Label lblMessage = (Label) hbox.lookup("#lblMessage");
        if (type.equals("알림")) {
            imgMessage.setImage(new Image(getClass().getResource("images/dialog-info.png").toString()));

        } else if (type.equals("경고")) {
            imgMessage.setImage(new Image(getClass().getResource("images/dialog-warning.png").toString()));
        }
        lblMessage.setText(message);
        popup.getContent().add(hbox);
        popup.show(AppMain.primaryStage);
        popup.setAutoHide(true);
    }

    @FXML
    private void handleCustom(ActionEvent event) throws IOException {

       showCustomDialog("error","에러다?");
      // showCustomDialog("info","정보다");
       // showCustomDialog("help","도와줘");
        //showCustomDialog("warning","경고다?");
        
    }
    private void showCustomDialog(String type,String message) throws IOException{
        Stage dialog = new Stage(StageStyle.UTILITY);
        Parent parent = FXMLLoader.load(getClass().getResource("custom-dialog.fxml"));
        
        ImageView icon = (ImageView)parent.lookup("#icon");
        Label lblmessage = (Label)parent.lookup("#message");
        Button btnOk = (Button)parent.lookup("#btnOk");
        
        if(type.equals("error")){
            icon.setImage(new Image(getClass().getResource("images/dialog-error.png").toString()));
        }else if(type.equals("help")){
            icon.setImage(new Image(getClass().getResource("images/dialog-help.png").toString()));
        }else if(type.equals("info")){
            icon.setImage(new Image(getClass().getResource("images/dialog-info.png").toString()));
        }else if(type.equals("warning")){
            icon.setImage(new Image(getClass().getResource("images/dialog-warning.png").toString()));
        }
        lblmessage.setText(message);
        btnOk.setOnAction(e->dialog.hide());
        
        Scene scene = new Scene(parent);
        dialog.setScene(scene);
        //scene.setFill(Color.TRANSPARENT); //투명창만들기,fxml의 색도 transparent로 설정
        dialog.initOwner(AppMain.primaryStage);
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.show();
        
    }
    

}
