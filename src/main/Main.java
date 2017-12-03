package main;

import dbconnection.DBConnection;
import graph.Nodes;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by Piotr on 26.11.2017.
 */
public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {

        Group group = new Group();

        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                //DBConnection.getCityData();
                Nodes.generateNodes(2);
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        DBConnection.connect();
        primaryStage.show();
    }
    public static void main(String args[]){
        launch(args);
    }
}
