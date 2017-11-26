package graph;

import dbconnection.DBConnection;
import gui.Popups;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Nodes extends DBConnection{


    public static void generateNodes(int amount, int graphId){
        String selectSQL = "SELECT count(*) FROM CITY_DATA";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery(selectSQL );
            while (rs.next()) {
                Integer count = rs.getInt("COUNT(*)");
                System.out.println(count);
            }
        }
        catch (SQLException e) {
            Popups.genericError(e.toString());
        }





    }
}
