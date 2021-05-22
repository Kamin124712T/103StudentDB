package studentdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URI = "jdbc:derby://localhost:1527/studentdb";
    private static final String USERNAME = "app";
    private static final String PASSWORD = "app";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URI, USERNAME, PASSWORD);
        } catch ( SQLException ex) {
            System.out.println(ex);
        }
        return conn;
    }
}
