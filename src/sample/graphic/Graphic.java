package sample.graphic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Graphic extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Word Count");
        primaryStage.setScene(new Scene(root, 582, 458));
        primaryStage.show();
    }


    public static void show(String[] args) {
        launch(args);
    }
}