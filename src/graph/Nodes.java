package graph;

import dbconnection.DBConnection;
import gui.Popups;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Nodes extends DBConnection{

    public static void generateNodes(int amount){
        String selectCityData = "SELECT CITY_ID, CITY_NAME, GEO_WIDTH, GEO_HEIGHT, POPULATION, POPULATION_ROLLING_SUM FROM CITY_DATA order by 1";
        String selectCityCount = "Select count(*)  from CITY_DATA";

        PreparedStatement preparedStatement1, preparedStatement2;
        ArrayList<String[]> cityData = new ArrayList<>();
        int amountOfCities = 0;
        List<Integer> generatedNodes = new ArrayList<>();

        try {
            preparedStatement1 = connection.prepareStatement(selectCityCount);
            ResultSet rs1 = preparedStatement1.executeQuery(selectCityCount);
            while (rs1.next()) {
                amountOfCities = rs1.getInt("count(*)");
            }

            preparedStatement2 = connection.prepareStatement(selectCityData);
            ResultSet rs2 = preparedStatement2.executeQuery(selectCityData );
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
        /*
        System.out.println(amountOfCities);
        for (String[] p : cityData) {
            for (int i = 0; i < p.length; i++) {
                System.out.print(p[i] + "\t");
                if (i == p.length-1)
                    System.out.println();
            }
        }
        */
        int wholePopulation = Integer.parseInt(cityData.get(cityData.size()-1)[5]);

        System.out.println(wholePopulation);

        while(generatedNodes.size() < amount) {
            int randomNum = ThreadLocalRandom.current().nextInt(1, wholePopulation + 1);
            for (String[] p : cityData) {
                int populationRollingSum = Integer.parseInt(p[p.length-1]);
                int cityId = Integer.parseInt(p[0]);
                String cityName = p[1];
                if (randomNum > populationRollingSum) {
                }
                else {
                    generatedNodes.add(cityId);
                    System.out.println("random: " + randomNum + " population rolling sum: " + populationRollingSum + " city id: " + cityId + " city name: " + cityName);
                    break;
                }
            }
            for(int cityId: generatedNodes) {
                System.out.println(cityId);
            }
        }









    }
}
