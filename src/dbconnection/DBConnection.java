package dbconnection;

import gui.Popups;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBConnection {
    public static Connection connection = null;

    public static void connect() {
        Properties properties = new Properties();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }
        catch (ClassNotFoundException e) {
            Popups.genericError(e.getMessage());
        }

        try {
            properties.load(DBConnection.class.getClassLoader().getResourceAsStream("connection.properties"));
        }
        catch (IOException e) {
            Popups.genericError(e.getMessage());
        }

        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@"
                            + properties.getProperty("database.host") + ":"
                            + properties.getProperty("database.port") + ":"
                            + properties.getProperty("database.sid"),
                    properties.getProperty("database.user"), properties.getProperty("database.password"));
        }
        catch (SQLException e) {
            Popups.genericError(e.getMessage());
        }
    }
}
