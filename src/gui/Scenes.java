package gui;

import graph.Nodes;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Scenes {

    public javafx.scene.Scene startingScene(Stage stage){
        StackPane stackPane = new StackPane();
        Scene startingScene = new Scene(stackPane, 1280, 720);
        startingScene.getStylesheets().add("style.css");
        VBox mainMenu = new VBox();

        Button btnNewGraph = new Button();
        btnNewGraph.setPrefSize(250,30);
        btnNewGraph.setText("Generate new graph");
        btnNewGraph.setOnAction((ActionEvent event) -> {
                System.out.println("Generate new graph");
                Nodes.generateNodes(2);
                stage.setScene(newGraphScene(stage));
        });

        Button btnEditExistingGraph = new Button();
        btnEditExistingGraph.setPrefSize(250,30);
        btnEditExistingGraph.setText("Edit existing graph");
        btnEditExistingGraph.setOnAction((ActionEvent event) -> {
            System.out.println("Edit existing graph");
            Nodes.generateNodes(2);
            stage.setScene(newGraphScene(stage));
        });

        Button btnHelp = new Button();
        btnHelp.setPrefSize(250,30);
        btnHelp.setText("Help");
        btnHelp.setOnAction((ActionEvent event) -> {
            System.out.println("Help");
            Nodes.generateNodes(2);
            stage.setScene(newGraphScene(stage));
        });

        Button btnAboutAuthor = new Button();
        btnAboutAuthor.setPrefSize(250,30);
        btnAboutAuthor.setText("About author");
        btnAboutAuthor.setOnAction((ActionEvent event) -> {
            System.out.println("Info about author");
            Nodes.generateNodes(2);
            stage.setScene(newGraphScene(stage));
        });

        Button btnExit = new Button();
        btnExit.setPrefSize(250,30);
        btnExit.setText("Exit");
        btnExit.setOnAction(e -> Platform.exit());

        mainMenu.getChildren().addAll(btnNewGraph, btnEditExistingGraph, btnHelp, btnAboutAuthor, btnExit);
        mainMenu.setAlignment(Pos.TOP_LEFT);
        mainMenu.setPadding(new Insets(30, 30, 30, 30));
        mainMenu.setSpacing(20);
        stackPane.getChildren().add(mainMenu);
        return startingScene;
    }

    public javafx.scene.Scene newGraphScene(Stage stage){
        StackPane stackPane = new StackPane();
        Scene newGraphScene = new Scene(stackPane, 1280, 720);

        Button btn = new Button();
        btn.setText("Say 'Hello World 2'");
        btn.setOnAction((ActionEvent event) -> {
            System.out.println("Hello World 2!");
            Nodes.generateNodes(2);
            stage.setScene(startingScene(stage));
        });

        stackPane.getChildren().add(btn);
        return newGraphScene;
    }
}
