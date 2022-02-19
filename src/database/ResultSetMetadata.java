package database;
import java.sql.*;

public class ResultSetMetadata {
    public static void main(String[] args) throws SQLException {
        String connectionString = "jdbc:sqlite:C:/SQLite/Book.db";
        try (Connection connection = DriverManager.getConnection(connectionString)) {
            System.out.println("Database now connected.");
            try (Statement statement = connection.createStatement()) {
                ResultSet results =
                        statement.executeQuery("SELECT id,fname,lname FROM Authors");
                ResultSetMetaData rsmd = results.getMetaData();
                System.out.println("Here are the column names for the query.");
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    System.out.printf("%-20s%n", rsmd.getColumnName(i));
                }
            }
        }
    }
}
