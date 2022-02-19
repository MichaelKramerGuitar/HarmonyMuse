package database;
import java.sql.*;

public class BasicMetadata {
    public static void main(String[] args) throws SQLException {
        String connectionString = "jdbc:sqlite:C:/SQLite/Book.db";
        try (Connection connection = DriverManager.getConnection(connectionString)) {
            System.out.println("Database now connected.");
            DatabaseMetaData dbmd = connection.getMetaData();

            System.out.println("URL: " + dbmd.getURL());
            System.out.println("User name: " + dbmd.getUserName());
            System.out.println("Product name: " + dbmd.getDatabaseProductName());
            System.out.println("Product version: " + dbmd.getDatabaseProductVersion());
            System.out.println("Driver name: " + dbmd.getDriverName());
            System.out.println("Driver version: " + dbmd.getDriverVersion());
        }
    }
}