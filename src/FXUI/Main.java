package FXUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static FXUI.AppStyles.getPicturesResourcePath;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Secret App");
//        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(new Scene(root, 560, 450));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("file:///" + getPicturesResourcePath() + "logo.png"));
        primaryStage.show();
    }

    public static void main(String args) {
        Application.launch(args);
    }


}
