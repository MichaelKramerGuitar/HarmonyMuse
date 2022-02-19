package database;
import java.sql.*;

public class PreparedStatementExample {
    public static void main(String[] args) throws SQLException {
        String connectionString = "jdbc:sqlite:C:/SQLite/Book.db";
        try (Connection connection = DriverManager.getConnection(connectionString)) {
            System.out.println("Database now connected.");
            String sql =
                    "SELECT id,fname,lname " +
                            "FROM authors " +
                            "WHERE id > ? AND fname = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, 2);
                statement.setString(2, "John");
                ResultSet results =
                        statement.executeQuery();
                System.out.println("Here are the limited authors:");
                while (results.next()) {
                    System.out.printf("%d\t%-10s\t%-10s%n",
                            results.getInt(1), results.getString(2), results.getString(3));
                }
            }
        }
    }
}
