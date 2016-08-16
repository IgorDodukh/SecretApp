package FXUI;

import Settings.ReadConfigMain;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Secret App");
//        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(new Scene(root, 560, 450));
        primaryStage.show();

        ReadConfigMain.main();

        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("file:///C:/appFiles/pic/logo.png"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
