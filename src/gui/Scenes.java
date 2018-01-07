package gui;

import graph.Graph;
import graph.Nodes;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.sql.Date;

public class Scenes {

    public javafx.scene.Scene startingScene(Stage stage){
        StackPane stackPane = new StackPane();
        Scene startingScene = new Scene(stackPane, 1280, 720);
        startingScene.getStylesheets().add("style.css");
        VBox mainMenu = new VBox();

        Button btnNewGraph = new Button();
        btnNewGraph.setPrefSize(270,30);
        btnNewGraph.setText("Generate new graph");
        btnNewGraph.setOnAction((ActionEvent event) -> {
            System.out.println("Generate new graph");
            stage.setScene(newGraphScene(stage));
        });

        Button btnChooseExistingGraph = new Button();
        btnChooseExistingGraph.setPrefSize(270,30);
        btnChooseExistingGraph.setText("Choose existing graph");
        btnChooseExistingGraph.setOnAction((ActionEvent event) -> {
            System.out.println("Choose existing graph");
            stage.setScene(exisitingGraphScene(stage));
        });

        Button btnHelp = new Button();
        btnHelp.setPrefSize(270,30);
        btnHelp.setText("Help");
        btnHelp.setOnAction((ActionEvent event) -> {
            System.out.println("Help");
            Nodes.generateNodes(2);
            stage.setScene(newGraphScene(stage));
        });

        Button btnAboutAuthor = new Button();
        btnAboutAuthor.setPrefSize(270,30);
        btnAboutAuthor.setText("About author");
        btnAboutAuthor.setOnAction((ActionEvent event) -> {
            System.out.println("Info about author");
            Nodes.generateNodes(2);
            stage.setScene(newGraphScene(stage));
        });

        Button btnExit = new Button();
        btnExit.setPrefSize(270,30);
        btnExit.setText("Exit");
        btnExit.setOnAction(e -> Platform.exit());

        mainMenu.getChildren().addAll(btnNewGraph, btnChooseExistingGraph, btnHelp, btnAboutAuthor, btnExit);
        mainMenu.setAlignment(Pos.TOP_LEFT);
        mainMenu.setPadding(new Insets(30, 30, 30, 30));
        mainMenu.setSpacing(20);
        stackPane.getChildren().add(mainMenu);
        return startingScene;
    }

    public javafx.scene.Scene newGraphScene(Stage stage){
        StackPane stackPane = new StackPane();
        Scene newGraphScene = new Scene(stackPane, 1280, 720);
        newGraphScene.getStylesheets().add("style.css");

        VBox newGraphMenu = new VBox();

        Button btnBack = new Button();
        btnBack.setPrefSize(270,30);
        btnBack.setText("Back");
        btnBack.setOnAction((ActionEvent event) -> {
            System.out.println("Back");
            stage.setScene(startingScene(stage));
        });

        Label lblAmount = new Label();
        lblAmount.setText("Amount of nodes:");
        lblAmount.setFont(new Font(20));

        TextField txtfAmount = new TextField();
        txtfAmount.setMaxWidth(270);
        //todo change that to lambda expression - learn about lambda
        txtfAmount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtfAmount.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        Button btnGenerateNewGraph = new Button();
        btnGenerateNewGraph.setPrefSize(270,30);
        btnGenerateNewGraph.setText("Generate graph");
        btnGenerateNewGraph.setOnAction((ActionEvent event) -> {
            System.out.println("Generating graph " + txtfAmount.getText());
            Nodes.generateNodes(Integer.parseInt(txtfAmount.getText()));
            stage.setScene(startingScene(stage));
        });

        Circle circeTest = new Circle(21.02,52.12, 1);

        newGraphMenu.getChildren().addAll(btnBack, lblAmount, txtfAmount, btnGenerateNewGraph, circeTest);
        //newGraphMenu.getChildren().addAll(circeTest2);
        newGraphMenu.setAlignment(Pos.TOP_LEFT);
        newGraphMenu.setPadding(new Insets(30, 30, 30, 30));
        newGraphMenu.setSpacing(20);
        stackPane.getChildren().add(newGraphMenu);
        return newGraphScene;
    }

    public javafx.scene.Scene graphScene(Stage stage){
        StackPane stackPane = new StackPane();
        Scene graphScene = new Scene(stackPane, 1280, 720);
        graphScene.getStylesheets().add("style.css");

        VBox graphMenu = new VBox();

        Button btnBack = new Button();
        btnBack.setPrefSize(270,30);
        btnBack.setText("Back");
        btnBack.setOnAction((ActionEvent event) -> {
            System.out.println("Back");
            stage.setScene(startingScene(stage));
        });

        graphMenu.getChildren().addAll(btnBack);
        graphMenu.setAlignment(Pos.TOP_LEFT);
        graphMenu.setPadding(new Insets(30, 30, 30, 30));
        graphMenu.setSpacing(20);
        stackPane.getChildren().add(graphMenu);
        return graphScene;
    }


    public javafx.scene.Scene exisitingGraphScene(Stage stage){
        StackPane stackPane = new StackPane();
        Scene newGraphScene = new Scene(stackPane, 1280, 720);
        newGraphScene.getStylesheets().add("style.css");

        VBox existingGraphMenu = new VBox();

        Button btnBack = new Button();
        btnBack.setPrefSize(270,30);
        btnBack.setText("Back");
        btnBack.setOnAction((ActionEvent event) -> {
            System.out.println("Back");
            stage.setScene(startingScene(stage));
        });

        Label lblList = new Label();
        lblList.setText("List of available graphs:");
        lblList.setFont(new Font(20));

        ObservableList<Graph> graphList = Nodes.getExistingGraphs();

        TableColumn<Graph, String> graphIdColumn = new TableColumn<>("Graph ID");
        graphIdColumn.setMinWidth(50);
        graphIdColumn.setCellValueFactory(new PropertyValueFactory<>("graphId"));

        TableColumn<Graph, String> nodeAmountColumn = new TableColumn<>("Nodes");
        nodeAmountColumn.setMinWidth(100);
        nodeAmountColumn.setCellValueFactory(new PropertyValueFactory<>("nodeAmount"));

        TableColumn<Graph, String> connectionAmountColumn = new TableColumn<>("Connections");
        connectionAmountColumn.setMinWidth(100);
        connectionAmountColumn.setCellValueFactory(new PropertyValueFactory<>("connectionAmount"));

        TableColumn<Graph, String> createDateColumn = new TableColumn<>("Create date");
        createDateColumn.setMinWidth(80);
        createDateColumn.setCellValueFactory(new PropertyValueFactory<>("createDate"));

        TableView<Graph> tableExistingGraph = new TableView();
        tableExistingGraph.getColumns().addAll(graphIdColumn, nodeAmountColumn, connectionAmountColumn, createDateColumn);
        tableExistingGraph.setItems(graphList);
        tableExistingGraph.setMaxWidth(350);

        Button btnLoadGraph = new Button();
        btnLoadGraph.setPrefSize(270,30);
        btnLoadGraph.setText("Load graph");
        btnLoadGraph.setOnAction((ActionEvent event) -> {
            try {
                Graph selectedGraph = tableExistingGraph.getSelectionModel().getSelectedItem();
                System.out.println("dupa" + selectedGraph.getGraphId());
                stage.setScene(graphScene(stage));
            }
            catch (NullPointerException e) {
                Popups.genericError("Choose one of the existing graphs!");
            }


        });

        existingGraphMenu.getChildren().addAll(btnBack, lblList, tableExistingGraph, btnLoadGraph);
        //newGraphMenu.getChildren().addAll(circeTest2);
        existingGraphMenu.setAlignment(Pos.TOP_LEFT);
        existingGraphMenu.setPadding(new Insets(30, 30, 30, 30));
        existingGraphMenu.setSpacing(20);
        stackPane.getChildren().add(existingGraphMenu);
        return newGraphScene;
    }
}
