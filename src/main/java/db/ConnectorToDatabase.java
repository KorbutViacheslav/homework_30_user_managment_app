package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorToDatabase {
    private static final String URL = "jdbc:mysql://localhost:3306/pers";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException e) {
            System.out.println("SQL Error");
        }
        return connection;
    }
}
