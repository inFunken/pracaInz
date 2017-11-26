package dbconnection;

import gui.Popups;

import java.sql.*;

public class DBConnection {
    public static Connection connection = null;

    public static void connect() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "ps", "12345");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("connected to db");
    }

    public static void getCityData(){
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
