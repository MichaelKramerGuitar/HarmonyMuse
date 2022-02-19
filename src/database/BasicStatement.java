package database;
import java.sql.*;

public class BasicStatement {


    public static void main(String[] args) throws SQLException {
        String connectionString = "jdbc:sqlite:C:/SQLite/Book.db";
        try (Connection connection = DriverManager.getConnection(connectionString)) {
            System.out.println("Database now connected.");
            try (Statement statement = connection.createStatement()) {
                ResultSet results =
                        statement.executeQuery("SELECT id,fname,lname FROM authors");
                System.out.println("Here are the authors:");
                while (results.next()) {
                    System.out.printf("%d\t%-10s\t%-10s%n",
                            results.getInt(1), results.getString(2), results.getString(3));
                }
            }
        }
    }
}
