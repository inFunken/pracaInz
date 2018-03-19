package gui;

import graph.Connections;
import graph.Graph;
import graph.Nodes;
import graph.NodesConnections;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import zoomAndDrag.NodesOnMap;
import zoomAndDrag.PannableCanvas;
import zoomAndDrag.SceneGestures;
import zoomAndDrag.NodesOnCircles;


public class Scenes {

    Object[][] nodesConnections;

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
//        todo change that to lambda expression - learn about lambda
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

    public javafx.scene.Scene graphSceneTable(Stage stage, int graphId){
        StackPane stackPane = new StackPane();
        Scene graphSceneTable = new Scene(stackPane, 1280, 720);
        graphSceneTable.getStylesheets().add("style.css");

        HBox graphMenu = new HBox();
        HBox table = new HBox();

        Button btnBack = new Button();
        btnBack.setPrefSize(270,30);
        btnBack.setText("Back");
        btnBack.setOnAction((ActionEvent event) -> {
            System.out.println("Back");
            stage.setScene(startingScene(stage));
        });

        Button btnConnectionsList = new Button();
        btnConnectionsList.setPrefSize(270,30);
        btnConnectionsList.setText("Connections list");
        btnConnectionsList.setOnAction((ActionEvent event) -> {
            System.out.println("Connections list");
            stage.setScene(graphSceneTable(stage, graphId));
        });

        Button btnNodesOnCircles = new Button();
        btnNodesOnCircles.setPrefSize(270,30);
        btnNodesOnCircles.setText("Nodes on circles");
        btnNodesOnCircles.setOnAction((ActionEvent event) -> {
            System.out.println("Nodes on circles");
            stage.setScene(onCirclesGraphScene(stage, graphId));
        });

        Button btnNodesOnMap = new Button();
        btnNodesOnMap.setPrefSize(270,30);
        btnNodesOnMap.setText("Nodes on map");
        btnNodesOnMap.setOnAction((ActionEvent event) -> {
            System.out.println("Nodes on map");
            stage.setScene(onMapGraphScene(stage, graphId));
        });

        Label lblList = new Label();
        lblList.setText("List of nodes and connections in this graph:");
        lblList.setFont(new Font(20));

        ObservableList<Connections> connectionList = Nodes.getConnections(graphId);

        TableColumn<Connections, Integer> connectionIdColumn = new TableColumn<>("Connection ID");
        connectionIdColumn.setMinWidth(50);
        connectionIdColumn.setCellValueFactory(new PropertyValueFactory<>("connectionId"));


        TableColumn<Connections, Integer> nodeId1Column = new TableColumn<>("Node1 ID");
        nodeId1Column.setMinWidth(50);
        nodeId1Column.setCellValueFactory(new PropertyValueFactory<>("nodeId1"));

        TableColumn<Connections, Integer> cityId1Column = new TableColumn<>("City1 ID");
        cityId1Column.setMinWidth(50);
        cityId1Column.setCellValueFactory(new PropertyValueFactory<>("cityId1"));

        TableColumn<Connections, String> cityName1Column = new TableColumn<>("City1 Name");
        cityName1Column.setMinWidth(100);
        cityName1Column.setCellValueFactory(new PropertyValueFactory<>("cityName1"));

        TableColumn<Connections, Double> cityHeight1Column = new TableColumn<>("City1 Height");
        cityHeight1Column.setMinWidth(50);
        cityHeight1Column.setCellValueFactory(new PropertyValueFactory<>("height1"));

        TableColumn<Connections, Double> cityWidth1Column = new TableColumn<>("City1 Width");
        cityWidth1Column.setMinWidth(50);
        cityWidth1Column.setCellValueFactory(new PropertyValueFactory<>("width1"));


        TableColumn<Connections, Integer> nodeId2Column = new TableColumn<>("Node2 ID");
        nodeId2Column.setMinWidth(50);
        nodeId2Column.setCellValueFactory(new PropertyValueFactory<>("nodeId2"));

        TableColumn<Connections, Integer> cityId2Column = new TableColumn<>("City2 ID");
        cityId2Column.setMinWidth(50);
        cityId2Column.setCellValueFactory(new PropertyValueFactory<>("cityId2"));

        TableColumn<Connections, String> cityName2Column = new TableColumn<>("City2 Name");
        cityName2Column.setMinWidth(100);
        cityName2Column.setCellValueFactory(new PropertyValueFactory<>("cityName2"));

        TableColumn<Connections, Double> cityHeight2Column = new TableColumn<>("City2 Height");
        cityHeight2Column.setMinWidth(50);
        cityHeight2Column.setCellValueFactory(new PropertyValueFactory<>("height2"));

        TableColumn<Connections, Double> cityWidth2Column = new TableColumn<>("City2 Width");
        cityWidth2Column.setMinWidth(50);
        cityWidth2Column.setCellValueFactory(new PropertyValueFactory<>("width2"));


        TableView<Connections> tableExistingGraph = new TableView();
        tableExistingGraph.getColumns().addAll(connectionIdColumn, nodeId1Column, cityId1Column, cityName1Column, cityHeight1Column, cityWidth1Column, nodeId2Column, cityId2Column, cityName2Column, cityHeight2Column, cityWidth2Column);
        tableExistingGraph.setItems(connectionList);
        //tableExistingGraph.setMaxWidth(350);


        int amountOfRecords = tableExistingGraph.getItems().size();
        nodesConnections = new Object[amountOfRecords][2];

        tableExistingGraph.getItems().forEach((o)
                        -> addConnectionDataToArray(nodesConnections,
                String.valueOf(nodeId1Column.getCellData(o)),
                String.valueOf(nodeId2Column.getCellData(o))
                )
        );
        recordNumber = 0;

        graphMenu.getChildren().addAll(btnBack, btnConnectionsList, btnNodesOnCircles, btnNodesOnMap);
        graphMenu.setAlignment(Pos.TOP_LEFT);
        graphMenu.setPadding(new Insets(30, 30, 30, 30));
        graphMenu.setSpacing(20);

        table.getChildren().addAll(tableExistingGraph);
        table.setAlignment(Pos.BOTTOM_LEFT);
        table.setPadding(new Insets(100, 30, 30, 30));
        table.setSpacing(20);

        stackPane.getChildren().addAll(table, graphMenu);
        return graphSceneTable;
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
                System.out.println("Selected Graph" + selectedGraph.getGraphId());
                stage.setScene(graphSceneTable(stage, selectedGraph.getGraphId()));
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







    public static Group generateNodes(Object[][] xy){
        Group group1 = new Group();
        int i = 0;
        double x = 300;
        double radius = 0;
        double deltaRadius;
        int amountOfRanks =(int) xy[xy.length - 1][6];
        int currentRank = 0;
        int ranks = 0;
        int rest = 0;
        int n = 0;
        int nodeId = 0;

        if (amountOfRanks > 10) {
            ranks = (amountOfRanks / 10);
            System.out.println(ranks);
            rest = amountOfRanks % 10;
            System.out.println(rest);
            amountOfRanks = 10;
        }

        //initialize and populate the array with circles and nodes info
        int[][] circles = new int[amountOfRanks][2];
        for (int j = 0; j < amountOfRanks; j++) {
            if (j < amountOfRanks - rest) {
                n++;
                circles[j][0] = n;
                circles[j][1] = n;
            }
            else {
                n++;
                circles[j][0] = n;
                n++;
                circles[j][1] = n;
            }
        }

        System.out.println("Amount of ranks: " + amountOfRanks);
        deltaRadius = 290 / (amountOfRanks - 1);

        while (i < circles.length) {
            Circle circle = new Circle();
            Circle circleRange = new Circle();

            int range1 = circles[i][0];
            int range2 = circles[i][1];

            currentRank = (int)xy[nodeId][6];

            while (currentRank >= range1 && currentRank <= range2 && nodeId < xy.length - 1) {
                if (nodeId == 0 || currentRank != (int)xy[nodeId - 1][6]) {
                    if (currentRank == range1 && currentRank > circles[0][1]) {
                        System.out.println(currentRank);
                        radius += deltaRadius;
                        circleRange = new Circle(x,x,radius);
                        circleRange.setFill(null);
                        circleRange.setStrokeWidth(1);
                        circleRange.setStroke(Color.BLACK);
                        group1.getChildren().addAll(circleRange);
                    }

                    NodesOnCircles node = NodesOnCircles.generateRandomNodes(300,300, radius);
                    circle = new Circle(node.getX(),node.getY(),2);
                    circle.setId(Integer.toString((int) xy[nodeId][0]));

                    group1.getChildren().addAll(circle);
                    Circle finalCircle = circle;
                    circle.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                        finalCircle.setFill(Color.RED);
                        System.out.println(finalCircle.getId());
                    });
                }
                else {
                    NodesOnCircles node = NodesOnCircles.generateRandomNodes(300,300, radius);
                    circle = new Circle(node.getX(),node.getY(),2);
                    circle.setId(Integer.toString((int) xy[nodeId][0]));
                    group1.getChildren().addAll(circle);
                    Circle finalCircle = circle;
                    circle.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                        finalCircle.setFill(Color.RED);
                        System.out.println(finalCircle.getId());
                    });
                }
                nodeId++;
                currentRank = (int)xy[nodeId][6];
            }
            i++;
        }
        return group1;
    }


    int recordNumber = 0;

    public void addDataToArray(Object multi[][], int amountOfRecords, String nodeId, String amount, String cityId,
                               String cityName, String geoWidth, String geoHeight, String ranking) {
        multi[recordNumber][0] = Integer.parseInt(nodeId);
        multi[recordNumber][1] = Integer.parseInt(amount);
        multi[recordNumber][2] = Integer.parseInt(cityId);
        multi[recordNumber][3] = cityName;
        multi[recordNumber][4] = Double.parseDouble(geoWidth);
        multi[recordNumber][5] = Double.parseDouble(geoHeight);
        multi[recordNumber][6] = Integer.parseInt(ranking);
        recordNumber++;
    }

    public void addConnectionDataToArray(Object nodesConnections[][], String node1Id, String node2Id) {
        nodesConnections[recordNumber][0] = Integer.parseInt(node1Id);
        nodesConnections[recordNumber][1] = Integer.parseInt(node2Id);
        recordNumber++;
    }

    public javafx.scene.Scene onCirclesGraphScene(Stage stage, int graphId){
        StackPane stackPane = new StackPane();
        Scene nodesOnCircles = new Scene(stackPane, 1280, 720);
        nodesOnCircles.getStylesheets().add("style.css");

        HBox graphMenu = new HBox();
        VBox table = new VBox();

        Button btnBack = new Button();
        btnBack.setPrefSize(270,30);
        btnBack.setText("Back");
        btnBack.setOnAction((ActionEvent event) -> {
            System.out.println("Back");
            stage.setScene(startingScene(stage));
        });

        Button btnConnectionsList = new Button();
        btnConnectionsList.setPrefSize(270,30);
        btnConnectionsList.setText("Connections list");
        btnConnectionsList.setOnAction((ActionEvent event) -> {
            System.out.println("Connections list");
            stage.setScene(graphSceneTable(stage, graphId));
        });

        Button btnNodesOnCircles = new Button();
        btnNodesOnCircles.setPrefSize(270,30);
        btnNodesOnCircles.setText("Nodes on circles");
        btnNodesOnCircles.setOnAction((ActionEvent event) -> {
            System.out.println("Nodes on circles");
            stage.setScene(onCirclesGraphScene(stage, graphId));
        });

        Button btnNodesOnMap = new Button();
        btnNodesOnMap.setPrefSize(270,30);
        btnNodesOnMap.setText("Nodes on map");
        btnNodesOnMap.setOnAction((ActionEvent event) -> {
            System.out.println("Nodes on map");
            stage.setScene(onMapGraphScene(stage, graphId));
        });

        Label lblList = new Label();
        lblList.setText("List of nodes and connections in this graph:");
        lblList.setFont(new Font(20));

        ObservableList<NodesConnections> nodesConnectionsList = Nodes.getNodes(graphId);

        TableColumn<NodesConnections, String> nodeIdColumn = new TableColumn<>("Node ID");
        nodeIdColumn.setMinWidth(50);
        nodeIdColumn.setCellValueFactory(new PropertyValueFactory<>("nodeId"));

        TableColumn<NodesConnections, String> amountColumn = new TableColumn<>("Connection amount");
        amountColumn.setMinWidth(50);
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn<NodesConnections, String> cityIdColumn = new TableColumn<>("City Id");
        cityIdColumn.setMinWidth(50);
        cityIdColumn.setCellValueFactory(new PropertyValueFactory<>("cityId"));

        TableColumn<NodesConnections, String> cityNameColumn = new TableColumn<>("City Name");
        cityNameColumn.setMinWidth(100);
        cityNameColumn.setCellValueFactory(new PropertyValueFactory<>("cityName"));

        TableColumn<NodesConnections, String> geoWidthColumn = new TableColumn<>("Geo width");
        geoWidthColumn.setMinWidth(50);
        geoWidthColumn.setCellValueFactory(new PropertyValueFactory<>("geoWidth"));

        TableColumn<NodesConnections, String> geoHeightColumn = new TableColumn<>("Geo height");
        geoHeightColumn.setMinWidth(50);
        geoHeightColumn.setCellValueFactory(new PropertyValueFactory<>("geoHeight"));

        TableColumn<NodesConnections, String> rankingColumn = new TableColumn<>("Ranking");
        rankingColumn.setMinWidth(50);
        rankingColumn.setCellValueFactory(new PropertyValueFactory<>("ranking"));

        TableView<NodesConnections> tableNodesConnections = new TableView();
        tableNodesConnections.getColumns().addAll(nodeIdColumn, amountColumn, cityIdColumn, cityNameColumn,
                geoWidthColumn, geoHeightColumn, rankingColumn);
        tableNodesConnections.setItems(nodesConnectionsList);
        tableNodesConnections.setMaxWidth(580);

        int amountOfRecords = tableNodesConnections.getItems().size();
        Object[][] multi = new Object[amountOfRecords][7];

        tableNodesConnections.getItems().forEach((o)
                        -> addDataToArray(multi, amountOfRecords,
                String.valueOf(nodeIdColumn.getCellData(o)),
                String.valueOf(amountColumn.getCellData(o)),
                String.valueOf(cityIdColumn.getCellData(o)),
                String.valueOf(cityNameColumn.getCellData(o)),
                String.valueOf(geoWidthColumn.getCellData(o)),
                String.valueOf(geoHeightColumn.getCellData(o)),
                String.valueOf(rankingColumn.getCellData(o))
                )
        );
        recordNumber = 0;


        Group group = new Group();
        PannableCanvas canvas = new PannableCanvas();
        canvas.getChildren().addAll(generateNodes(multi));

        group.getChildren().add(canvas);
        SubScene subScene = new SubScene(group, 800, 600);
        subScene.setTranslateX(400);
        subScene.setTranslateY(50);
        SceneGestures sceneGestures = new SceneGestures(canvas);
        subScene.addEventFilter( MouseEvent.MOUSE_PRESSED, sceneGestures.getOnMousePressedEventHandler());
        subScene.addEventFilter( MouseEvent.MOUSE_DRAGGED, sceneGestures.getOnMouseDraggedEventHandler());
        subScene.addEventFilter( ScrollEvent.ANY, sceneGestures.getOnScrollEventHandler());

        graphMenu.getChildren().addAll(btnBack, btnConnectionsList, btnNodesOnCircles, btnNodesOnMap);
        graphMenu.setAlignment(Pos.TOP_LEFT);
        graphMenu.setPadding(new Insets(30, 30, 30, 30));
        graphMenu.setSpacing(20);

        table.getChildren().addAll(tableNodesConnections);
        table.setAlignment(Pos.CENTER_LEFT);
        table.setPadding(new Insets(100, 30, 30, 30));
        table.setSpacing(20);

        stackPane.getChildren().addAll(table, graphMenu, subScene);
        return nodesOnCircles;
    }



    public javafx.scene.Scene onMapGraphScene(Stage stage, int graphId){
        StackPane stackPane = new StackPane();
        Scene nodesOnCircles = new Scene(stackPane, 1280, 720);
        nodesOnCircles.getStylesheets().add("style.css");

        HBox graphMenu = new HBox();
        VBox table = new VBox();

        Button btnBack = new Button();
        btnBack.setPrefSize(270,30);
        btnBack.setText("Back");
        btnBack.setOnAction((ActionEvent event) -> {
            System.out.println("Back");
            stage.setScene(startingScene(stage));
        });

        Button btnConnectionsList = new Button();
        btnConnectionsList.setPrefSize(270,30);
        btnConnectionsList.setText("Connections list");
        btnConnectionsList.setOnAction((ActionEvent event) -> {
            System.out.println("Connections list");
            stage.setScene(graphSceneTable(stage, graphId));
        });

        Button btnNodesOnCircles = new Button();
        btnNodesOnCircles.setPrefSize(270,30);
        btnNodesOnCircles.setText("Nodes on circles");
        btnNodesOnCircles.setOnAction((ActionEvent event) -> {
            System.out.println("Nodes on circles");
            stage.setScene(onCirclesGraphScene(stage, graphId));
        });

        Button btnNodesOnMap = new Button();
        btnNodesOnMap.setPrefSize(270,30);
        btnNodesOnMap.setText("Nodes on map");
        btnNodesOnMap.setOnAction((ActionEvent event) -> {
            System.out.println("Nodes on map");
            stage.setScene(onMapGraphScene(stage, graphId));
        });

        Label lblList = new Label();
        lblList.setText("List of nodes and connections in this graph:");
        lblList.setFont(new Font(20));

        ObservableList<NodesConnections> nodesConnectionsList = Nodes.getNodes(graphId);

        TableColumn<NodesConnections, String> nodeIdColumn = new TableColumn<>("Node ID");
        nodeIdColumn.setMinWidth(50);
        nodeIdColumn.setCellValueFactory(new PropertyValueFactory<>("nodeId"));

        TableColumn<NodesConnections, String> amountColumn = new TableColumn<>("Connection amount");
        amountColumn.setMinWidth(50);
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn<NodesConnections, String> cityIdColumn = new TableColumn<>("City Id");
        cityIdColumn.setMinWidth(50);
        cityIdColumn.setCellValueFactory(new PropertyValueFactory<>("cityId"));

        TableColumn<NodesConnections, String> cityNameColumn = new TableColumn<>("City Name");
        cityNameColumn.setMinWidth(100);
        cityNameColumn.setCellValueFactory(new PropertyValueFactory<>("cityName"));

        TableColumn<NodesConnections, String> geoWidthColumn = new TableColumn<>("Geo width");
        geoWidthColumn.setMinWidth(50);
        geoWidthColumn.setCellValueFactory(new PropertyValueFactory<>("geoWidth"));

        TableColumn<NodesConnections, String> geoHeightColumn = new TableColumn<>("Geo height");
        geoHeightColumn.setMinWidth(50);
        geoHeightColumn.setCellValueFactory(new PropertyValueFactory<>("geoHeight"));

        TableColumn<NodesConnections, String> rankingColumn = new TableColumn<>("Ranking");
        rankingColumn.setMinWidth(50);
        rankingColumn.setCellValueFactory(new PropertyValueFactory<>("ranking"));

        TableView<NodesConnections> tableNodesConnections = new TableView();
        tableNodesConnections.getColumns().addAll(nodeIdColumn, amountColumn, cityIdColumn, cityNameColumn,
                geoWidthColumn, geoHeightColumn, rankingColumn);
        tableNodesConnections.setItems(nodesConnectionsList);
        tableNodesConnections.setMaxWidth(580);

        int amountOfRecords = tableNodesConnections.getItems().size();
        Object[][] multi = new Object[amountOfRecords][7];

        tableNodesConnections.getItems().stream().forEach((o)
                        -> addDataToArray(multi, amountOfRecords,
                String.valueOf(nodeIdColumn.getCellData(o)),
                String.valueOf(amountColumn.getCellData(o)),
                String.valueOf(cityIdColumn.getCellData(o)),
                String.valueOf(cityNameColumn.getCellData(o)),
                String.valueOf(geoWidthColumn.getCellData(o)),
                String.valueOf(geoHeightColumn.getCellData(o)),
                String.valueOf(rankingColumn.getCellData(o))
                )
        );
        recordNumber = 0;

        Group group = new Group();
        PannableCanvas canvas = new PannableCanvas();
        canvas.setId("canvas");
        canvas.setMinWidth(700);
        canvas.setMinHeight(650);
        canvas.getChildren().addAll(NodesOnMap.generateNodesOnMap(multi, nodesConnections));

        group.getChildren().add(canvas);
        SubScene subScene = new SubScene(group, 800, 600);
        subScene.setTranslateX(400);
        subScene.setTranslateY(50);
        SceneGestures sceneGestures = new SceneGestures(canvas);
        subScene.addEventFilter( MouseEvent.MOUSE_PRESSED, sceneGestures.getOnMousePressedEventHandler());
        subScene.addEventFilter( MouseEvent.MOUSE_DRAGGED, sceneGestures.getOnMouseDraggedEventHandler());
        subScene.addEventFilter( ScrollEvent.ANY, sceneGestures.getOnScrollEventHandler());

        graphMenu.getChildren().addAll(btnBack, btnConnectionsList, btnNodesOnCircles, btnNodesOnMap);
        graphMenu.setAlignment(Pos.TOP_LEFT);
        graphMenu.setPadding(new Insets(30, 30, 30, 30));
        graphMenu.setSpacing(20);

        table.getChildren().addAll(tableNodesConnections);
        table.setAlignment(Pos.CENTER_LEFT);
        table.setPadding(new Insets(100, 30, 30, 30));
        table.setSpacing(20);

        stackPane.getChildren().addAll(table, graphMenu, subScene);
        return nodesOnCircles;
    }
}
