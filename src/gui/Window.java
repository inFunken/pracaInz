package gui;

import dbconnection.DBConnection;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Window extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene startingScene = new Scenes().startingScene(primaryStage);
        primaryStage.setScene(startingScene);
        DBConnection.connect();
        primaryStage.setMinWidth(1280);
        primaryStage.setMinHeight(720);
        primaryStage.setTitle("Quasi-random graph generation");
        primaryStage.show();
    }
}
