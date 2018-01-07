package graph;

import dbconnection.DBConnection;
import gui.Popups;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Nodes extends DBConnection{

    public static void insertNewGraph(){
        String insertNewGraph = "insert into graph values (seqGraph.nextval, sysdate, null)";
        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(insertNewGraph);
            preparedStatement .executeUpdate();
        }
        catch (SQLException e) {
            Popups.genericError(e.toString());
        }
    }

    public static void insertNode(int nodeId, int cityId){
        String insertNode = "insert into node values (?, seqGraph.currval, ?)";
        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(insertNode);
            preparedStatement.setInt(1, nodeId);
            preparedStatement.setInt(2, cityId);
            preparedStatement .executeUpdate();
        }
        catch (SQLException e) {
            Popups.genericError(e.toString());
        }
    }


    public static void insertConnection(int connectionId, int node1, int node2){
        String insertConnection = "insert into connection values (?, seqGraph.currval, ?, ?)";
        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(insertConnection);
            preparedStatement.setInt(1, connectionId);
            preparedStatement.setInt(2, node1);
            preparedStatement.setInt(3, node2);
            preparedStatement .executeUpdate();
        }
        catch (SQLException e) {
            Popups.genericError(e.toString());
        }
    }

    public static void generateNodes(int amount){
        String selectCityData = "SELECT CITY_ID, CITY_NAME, GEO_WIDTH, GEO_HEIGHT, POPULATION, POPULATION_ROLLING_SUM FROM CITY_DATA order by 1";
        PreparedStatement preparedStatement;
        ArrayList<String[]> cityData = new ArrayList<>();
        List<Double[]> generatedNodes = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(selectCityData);
            ResultSet rs2 = preparedStatement.executeQuery(selectCityData );
            int columnCount = rs2.getMetaData().getColumnCount();
            while (rs2.next()) {
                String[] row = new String[columnCount];
                for (int i = 0; i < columnCount ; i++) {
                    row[i] = rs2.getString(i + 1);
                }
                cityData.add(row);
            }

        }
        catch (SQLException e) {
            Popups.genericError(e.toString());
        }

        int wholePopulation = Integer.parseInt(cityData.get(cityData.size()-1)[5]);

        System.out.println(wholePopulation);

        insertNewGraph();


        while(generatedNodes.size() < amount) {
            int randomNum = ThreadLocalRandom.current().nextInt(1, wholePopulation + 1);
            for (String[] p : cityData) {
                int populationRollingSum = Integer.parseInt(p[p.length-1]);
                double cityId = Integer.parseInt(p[0]);
                double geoWidth = Double.parseDouble(p[2]), geoHeight = Double.parseDouble(p[3]);
                String cityName = p[1];
                if (randomNum > populationRollingSum) {
                }
                else {
                    Double[] cityLocation = new Double[3];
                    for (int i = 0; i < 3; i++){
                        if (i==0)
                            cityLocation[i] = cityId;
                        else if (i==1)
                            cityLocation[i] = geoWidth;
                        else if (i==2)
                            cityLocation[i] = geoHeight;
                    }
                    generatedNodes.add(cityLocation);
                    System.out.println("random: " + randomNum + " population rolling sum: " + populationRollingSum + " city id: " + cityId + " city name: " + cityName);
                    insertNode(generatedNodes.size(), (int) cityId);
                    break;
                }
            }
        }
        for(Double[] cityList: generatedNodes) {
            for(double cityProperties: cityList) {
                System.out.print(cityProperties + "\t");
            }
            System.out.println();
        }

        generateConnections(50, generatedNodes);

    }

    public static void generateConnections(double probability, List<Double[]> nodes){

        double randomNum;
        int connectionId=1;
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = i + 1; j < nodes.size(); j++) {
                randomNum = ThreadLocalRandom.current().nextDouble(0, 100 + 1);
                if (randomNum <= probability) {
                    System.out.println("Connection   " + i + "\t" + j);
                    insertConnection(connectionId, i + 1, j + 1);
                    connectionId += 1;
                }
            }
        }
    }

    public static ObservableList getExistingGraphs(){
        String selectExistingGraphView = "select * from existingGraphView";
        PreparedStatement preparedStatement;
        //List<Object> existingGraphs = new ArrayList<>();
        ObservableList<Graph> existingGraphsObservableList = FXCollections.observableArrayList();
        try {
            preparedStatement = connection.prepareStatement(selectExistingGraphView);
            ResultSet resultSet = preparedStatement.executeQuery(selectExistingGraphView );
            while (resultSet.next()) {
                existingGraphsObservableList.add(new Graph(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getDate(4)));
                }
        }
        catch (SQLException e) {
            Popups.genericError(e.toString());
        }

        return existingGraphsObservableList;
    }
}
