package database;
import java.sql.*;
public class UseDatabase {
    private static void insert(Connection conn) throws SQLException {
        String sql = "INSERT INTO Person(first_name, last_name, birth_date) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "Bob");
            pstmt.setString(2, "Smith");
            pstmt.setDate(3, Date.valueOf("1976-1-13"));
            pstmt.executeUpdate();

            pstmt.setString(1, "Jane");
            pstmt.setString(2, "Elizabeth");
            pstmt.setDate(3, Date.valueOf("1979-3-15"));
            pstmt.executeUpdate();
        }
    }

    private static void query(Connection conn) throws SQLException {
        String sql = "SELECT person_id, first_name, last_name, birth_date FROM Person";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("%d\t%-10s\t%-10s\t%tD%n",
                        rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4));
            }
        }
    }

    public static void delete(Connection conn, int id) {
        String sql = "DELETE FROM Person WHERE person_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:sqlite:C:/SQLite/GettingStarted.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            insert(conn);
            query(conn);
//            for(int i = 0; i < 27; i++){
//                delete(conn, i);
//            }

        }
    }
}
