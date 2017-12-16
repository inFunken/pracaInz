package gui;

import graph.Nodes;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Scenes {

    public javafx.scene.Scene startingScene(Stage stage){
        StackPane stackPane = new StackPane();
        Scene startingScene = new Scene(stackPane, 1280, 720);


        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction((ActionEvent event) -> {
                System.out.println("Hello World!");
                //DBConnection.getCityData();
                Nodes.generateNodes(2);
                stage.setScene(newGraphScene(stage));
        });


        stackPane.getChildren().add(btn);
        return startingScene;
    }

    public javafx.scene.Scene newGraphScene(Stage stage){
        StackPane stackPane = new StackPane();
        Scene newGraphScene = new Scene(stackPane, 1280, 720);

        Button btn = new Button();
        btn.setText("Say 'Hello World 2'");
        btn.setOnAction((ActionEvent event) -> {
            System.out.println("Hello World 2!");
            //DBConnection.getCityData();
            Nodes.generateNodes(2);
            stage.setScene(startingScene(stage));
        });

        stackPane.getChildren().add(btn);
        return newGraphScene;
    }
}
