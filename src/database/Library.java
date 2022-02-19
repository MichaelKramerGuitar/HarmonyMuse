package database;

import commandline.app.CharactersTable;
import commandline.app.CommonView;

import java.sql.*;


/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is ...
 */
public class Library {


    /**
     * The purpose of this method is ...
     * <p>Precondition: </p>
     * <p>Postcondition: </p>
     *
     * @return
     */
    private static void createSongsTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:C:/SQLite/Library.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS songs (\n"
                + "	song_id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	title varchar(100) NOT NULL,\n"
                + "	composer_first varchar(100) NOT NULL,\n"
                + "	composer_last varchar(100) NOT NULL,\n"
                + " composer_id integer NOT NULL,\n"
                + "	composer2_first varchar(100),\n"
                + "	composer2_last varchar(100),\n"
                + " composer2_id integer,\n"
                + "	composer3_first varchar(100),\n"
                + "	composer3_last varchar(100),\n"
                + " composer3_id integer,\n"
                + " album varchar(100),\n"
                + "	label varchar(100),\n"
                + "	release_date date,\n"
                + " foreign key (composer_id) references composers(composer_id)"
                + " foreign key (composer2_id) references composers(composer_id)"
                + " foreign key (composer3_id) references composers(composer_id)"
                + "UNIQUE(title)"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * The purpose of this method is ...
     * <p>Precondition: </p>
     * <p>Postcondition: </p>
     *
     * @return
     */
    private static void createComposerTable(){
        // SQLite connection string
        String url = "jdbc:sqlite:C:/SQLite/Library.db";
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS composers (\n"
                + "	composer_id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	composer_first varchar(100) NOT NULL,\n"
                + "	composer_last varchar(100) NOT NULL,\n"
                + "	primary_ensemble varchar(100) NOT NULL,\n"
                + "UNIQUE(composer_first, composer_last)"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void insertComposer(Connection conn, String fname, String lname, String band) throws SQLException {
        String sql = "INSERT INTO composers(composer_id, composer_first, composer_last, primary_ensemble) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(2, fname);
            pstmt.setString(3, lname);
            pstmt.setString(4, band);
            pstmt.executeUpdate();
        }
    }

    private static void queryComposers(Connection conn) throws SQLException {
        CharactersTable ct = CommonView.getCharTable();
        String sql = "SELECT composer_id, composer_first, composer_last, primary_ensemble FROM composers ORDER BY composer_last";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.printf("%s\t%-10s\t%-10s\t%-10s%n", "ID", "FIRST", "LAST", "ENSEMBLE");
            System.out.println(ct.repeatStringNTimes(ct.getTrebleClef(), 50));
            while (rs.next()) {

                System.out.printf("%d\t%-10s\t%-10s\t%-10s%n",
                        rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        }
    }

    private static int getComposerID(Connection conn, String fname, String lname) throws SQLException{
        String sql = "SELECT composer_id FROM composers WHERE composer_first = '" + fname + "' AND composer_last = '" + lname + "'";
        int composer_id = -1;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                composer_id = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return composer_id;
    }

    private static void insertSong(Connection conn, String title, String fname, String lname, String album, String label, Date release_date) {
        String composer2_first = null;
        String composer2_last = null;
        String composer3_first = null;
        String composer3_last = null;
        int neg = -1;
        String sql = "INSERT INTO songs(song_id, title, composer_first, composer_last, composer_id, composer2_first, composer2_last, composer2_id, composer3_first, composer3_last, composer3_id, album, label, release_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(2, title);
            pstmt.setString(3, fname);
            pstmt.setString(4, lname);
            try {
                pstmt.setInt(5, getComposerID(conn, fname, lname));
            }catch (SQLException e){
                System.out.println(e);
            }
            pstmt.setString(6, composer2_first);
            pstmt.setString(7, composer2_last);
            pstmt.setInt(8, neg);
            pstmt.setString(9, composer3_first);
            pstmt.setString(10, composer3_last);
            pstmt.setInt(11, neg);
            pstmt.setString(12, album);
            pstmt.setString(13, label);
            pstmt.setDate(14, release_date);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private static void querySongs(Connection conn, String query){
        CharactersTable ct = CommonView.getCharTable();
        String sql = "SELECT song_id, title, composer_first, composer_last, release_date FROM songs WHERE title = '" + query +"' OR composer_first = '" + query + "' OR composer_last = '" + query + "'";
        //String sql = "SELECT  song_id, title, composer_first, composer_last, release_date FROM songs WHERE title = '" + query + "'";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println();
            System.out.printf("%s\t%s\t\t\t\t\t%-10s\t\t%-10s\t%s%n", "ID", "TITLE", "FIRST", "LAST", "RELEASE DATE");
            System.out.println(ct.repeatStringNTimes(ct.getTrebleClef(), 80));
            while (rs.next()) {

                System.out.printf("%d\t%s\t\t%-10s\t\t%-10s\t%-10s%n",
                        rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) {

        createComposerTable();
        createSongsTable();
        String url = "jdbc:sqlite:C:/SQLite/Library.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            insertComposer(conn, "Bob", "Marley", "The Wailers");
            insertComposer(conn, "Ronnie", "Van Zant", "Lynyrd Skynyrd");
            insertComposer(conn, "Gary", "Rossington", "Lynyrd Skynyrd");
            insertComposer(conn, "Ed", "King", "Lynyrd Skynyrd");
            insertComposer(conn, "Paul", "McCartney", "The Beatles");
            insertComposer(conn, "John", "Lennon", "The Beatles");
            insertComposer(conn, "George", "Harrison", "The Beatles");
            insertComposer(conn, "Robert", "Plant", "Led Zeppelin");
            insertComposer(conn, "Jimmy", "Page", "Led Zeppelin");
            insertComposer(conn, "Johnny", "Green", "Film Score Composer");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(url)){
            queryComposers(conn);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(url)){
            insertSong(conn, "No Woman, No Cry", "Bob", "Marley", "Natty Dread", "Island/Tuff Gong", Date.valueOf("1974-10-25"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(url)){
            querySongs(conn, "No Woman, No Cry");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
