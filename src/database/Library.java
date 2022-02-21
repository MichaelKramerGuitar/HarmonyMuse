package database;

import java.sql.*;


/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to provide methods to interact with the Public
 * Library feature corresponding to files housed in (data/concurrencyLib)
 */
public class Library {

    /**
     * The purpose of this method is to get the .db file location on the
     * current machine - note, this url will vary from machine to machine
     * reconfigure as appropriate
     */
    public String getUrl() {

        String url = "jdbc:sqlite:C:/SQLite/Library.db";
        return url;
    }

    /**
     * The purpose of this method is to create the songs table
     */
    public void createSongsTable() {
        // SQLite connection string
        String url = getUrl();
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
     * The purpose of this method is to create the composers table
     */
    public void createComposerTable(){
        // SQLite connection string
        String url = getUrl();
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

    /**
     * The purpose of this method is to insert a composer into the composers table
     * <p>Precondition: A composer table exists in the Library.db file</p>
     * <p>Postcondition: the composer entry is successfully inputted into the
     * composers table of the Library.db file</p>
     */
    public void insertComposer(Connection conn,
                                       String fname,
                                       String lname,
                                       String band) throws SQLException {

        String sql = "INSERT INTO composers(composer_id, composer_first, " +
                "composer_last, primary_ensemble) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(2, fname);
            pstmt.setString(3, lname);
            pstmt.setString(4, band);
            pstmt.executeUpdate();
        }
    }

    public void queryComposers(Connection conn) throws SQLException {

        String sql = "SELECT composer_id, composer_first, " +
                "composer_last, primary_ensemble FROM composers ORDER BY composer_last";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.printf(
                    "%s%n%s\t%-10s\t%-10s\t%-10s%n", "queryComposers:", "ID", "FIRST", "LAST", "ENSEMBLE");
            while (rs.next()) {

                System.out.printf(
                        "%d\t%-10s\t%-10s\t%-10s%n",
                        rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4));
            }
        }
    }

    /**
     * The purpose of this method is to get the composer_id given a composers
     * first and last name if exists in the composers table of Library.db
     * <p>Precondition: the composer exists in the composers table of Library.db</p>
     * <p>Postcondition: the id (pk) is returned</p>
     * @param conn a Connection object
     * @param fname String composers first name
     * @param lname String composers last name
     * @return id (pk) is returned
     */
    public int getComposerID(Connection conn, String fname, String lname) throws SQLException{

        String sql = "SELECT composer_id FROM composers WHERE composer_first = '"
                + fname + "' AND composer_last = '" + lname + "'";

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

    /**
     * The purpose of this method is to insert a song into the songs table of
     * Library.db
     * <p>Precondition: the songs table of Library.db exists</p>
     * <p>Postcondition: the song is inserted into the appropriate table</p>
     *
     * @param conn A Connection object to the Library.db url on the current machine
     * @param title String song title
     * @param fname composers first name
     * @param lname composers last name
     * @param album album song was originally realeased on
     * @param label label album was originally released on
     * @param release_date Date object representing original release date
     */
    public void insertSong(Connection conn, String title,
                                   String fname, String lname, String album,
                                   String label, Date release_date) {
        String compose_first = fname;
        String composer_last = lname;
        String composer2_first = null;
        String composer2_last = null;
        String composer3_first = null;
        String composer3_last = null;
        int neg = -1;
        String sql = "INSERT INTO songs(song_id, title, composer_first," +
                " composer_last, composer_id, composer2_first, composer2_last," +
                " composer2_id, composer3_first, composer3_last, composer3_id, " +
                "album, label, release_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(2, title);
            pstmt.setString(3, compose_first);
            pstmt.setString(4, composer_last);
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

    /**
     * The purpose of this method is to insert a song into the songs table of
     * Library.db, overloaded to support two composer credits
     * <p>Precondition: the songs table of Library.db exists</p>
     * <p>Postcondition: the song is inserted into the appropriate table</p>
     *
     * @param conn A Connection object to the Library.db url on the current machine
     * @param title String song title
     * @param fname composers first name
     * @param lname composers last name
     * @param fname2 second composers first name
     * @param lname2 second composers last name
     * @param album album song was originally realeased on
     * @param label label album was originally released on
     * @param release_date Date object representing original release date
     */
    public void insertSong(Connection conn, String title, String fname, String lname,
                                   String fname2, String lname2, String album, String label, Date release_date) {
        String compose_first = fname;
        String composer_last = lname;
        String composer2_first = fname2;
        String composer2_last = lname2;
        String composer3_first = null;
        String composer3_last = null;
        int neg = -1;
        String sql = "INSERT INTO songs(song_id, title, composer_first," +
                " composer_last, composer_id, composer2_first, composer2_last, " +
                "composer2_id, composer3_first, composer3_last, composer3_id," +
                " album, label, release_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(2, title);
            pstmt.setString(3, compose_first);
            pstmt.setString(4, composer_last);
            try {
                pstmt.setInt(5, getComposerID(conn, compose_first, composer_last));
            }catch (SQLException e){
                System.out.println(e);
            }
            pstmt.setString(6, composer2_first);
            pstmt.setString(7, composer2_last);
            try {
                pstmt.setInt(8, getComposerID(conn, composer2_first, composer2_last));
            }catch (SQLException e){
                System.out.println(e);
            }
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

    /**
     * The purpose of this method is to insert a song into the songs table of
     * Library.db, overloaded to support three composer credits
     * <p>Precondition: the songs table of Library.db exists</p>
     * <p>Postcondition: the song is inserted into the appropriate table</p>
     *
     * @param conn A Connection object to the Library.db url on the current machine
     * @param title String song title
     * @param fname composers first name
     * @param lname composers last name
     * @param fname2 second composers first name
     * @param lname2 second composers last name
     * @param fname3 third composers first name
     * @param lname3 third composers last name
     * @param album album song was originally realeased on
     * @param label label album was originally released on
     * @param release_date Date object representing original release date
     */
    public void insertSong(Connection conn, String title, String fname, String lname,
                                   String fname2, String lname2, String fname3, String lname3,
                                   String album, String label, Date release_date) {
        String compose_first = fname;
        String composer_last = lname;
        String composer2_first = fname2;
        String composer2_last = lname2;
        String composer3_first = fname3;
        String composer3_last = lname3;
        int neg = -1;
        String sql = "INSERT INTO songs(" +
                "song_id, title, composer_first, composer_last, composer_id," +
                " composer2_first, composer2_last, composer2_id, composer3_first, " +
                "composer3_last, composer3_id, album, label, release_date)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(2, title);
            pstmt.setString(3, compose_first);
            pstmt.setString(4, composer_last);
            try {
                pstmt.setInt(5, getComposerID(conn, compose_first, composer_last));
            }catch (SQLException e){
                System.out.println(e);
            }
            pstmt.setString(6, composer2_first);
            pstmt.setString(7, composer2_last);
            try {
                pstmt.setInt(8, getComposerID(conn, composer2_first, composer2_last));
            }catch (SQLException e){
                System.out.println(e);
            }
            pstmt.setString(9, composer3_first);
            pstmt.setString(10, composer3_last);
            pstmt.setInt(11, getComposerID(conn, composer3_first, composer3_last));
            pstmt.setString(12, album);
            pstmt.setString(13, label);
            pstmt.setDate(14, release_date);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * The purpose of this method is to query the songs database with either a
     * title or composers first name or last name
     * <p>Precondition: the song exists in the database</p>
     * <p>Postcondition: the song data is on the console</p>
     */
    public void querySongs(Connection conn, String query){

        String sql = "SELECT song_id, title, composer_first, composer_last, album, release_date FROM songs WHERE title = '"
                + query +"' OR composer_first = '" + query + "' OR composer_last = '" + query + "'";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println();
            System.out.printf("%s%n%s\t%-20s\t%-10s\t%-10s\t%-10s\t%-10s%n",
                    "querySongs:", "ID", "TITLE", "FIRST", "LAST", "ALBUM", "RELEASE DATE");
            while (rs.next()) {

                System.out.printf("%s\t%-20s\t%-10s\t%-10s\t%-10s\t%-10s%n",
                        rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getDate(6));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * The purpose of this method is to query the songs database given the
     * primary key song_id of the song
     * <p>Precondition: the song exists in Library.db songs table</p>
     * <p>Postcondition: the title String is returned</p>
     *
     * @return the Title given the ID
     */
    public String querySongs(Connection conn, Integer id){

        String sql = "SELECT song_id, title, composer_first, composer_last, release_date FROM songs WHERE song_id = " + id.toString();
        String title = null;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println();
            System.out.printf("%s%n%s\t%-20s\t%-10s\t%-10s\t%-10s\t%-10s%n",
                    "querySongs:", "ID", "TITLE", "FIRST", "LAST", "ALBUM", "RELEASE DATE");
            while (rs.next()) {
                title = rs.getString(2);
                System.out.printf("%s\t%-20s\t%-10s\t%-10s\t%-10s\t%-10s%n",
                        rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getDate(6));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return title;
    }

    /**
     * The purpose of this method is to return all the songs in the Library.db
     * songs table
     * <p>Precondition: there are entries in Library.db songs table and a
     * valid connection object is passed in</p>
     * <p>Postcondition: all songs in table are neatly on the console</p>
     */
    public void queryAllSongs(Connection conn){
        String sql = "SELECT * from songs";
        try(Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)){
            System.out.println();
            System.out.printf(
                    "%s%n%s\t%-20s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-20s\t%-20s\t%-10s%n",
                    "queryAllSongs:", "ID", "TITLE", "FIRST", "LAST", "COMP ID",
                    "FIRST2", "LAST2", "COMP2 ID", "FIRST3", "LAST3", "COMP3 ID",
                    "ALBUM", "LABEL", "RELEASE DATE");
            while(rs.next()){
                System.out.printf(
                        "%s\t%-20s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-20s\t%-20s\t%-10s%n",
                        rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5),
                        rs.getString(6), rs.getString(7), rs.getInt(8),
                        rs.getString(9), rs.getString(10), rs.getInt(11),
                        rs.getString(12), rs.getString(13), rs.getDate(14));
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    /**
     * The purpose of this method is to output the song, band and release date
     * of a given song by joining the songs and composers table on the
     * composers_id (pk to composers and foreign_key to songs)
     * <p>Precondition: the song exists in songs and the composer exists in composers</p>
     * <p>Postcondition: the output TITLE, BAND, RELEASE DATE is neatly on the console </p>
     */
    public void joinComposerWithSongs(Connection conn){

        String sql = "SELECT title, primary_ensemble, release_date FROM songs JOIN composers USING (composer_id)";
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            System.out.println();
            System.out.printf("%s%n%-20s\t%-20s\t%-10s%n",
                    "joinComposerWithSongs:", "TITLE", "BAND", "RELEASE DATE");
            while(rs.next()){
                System.out.printf("%-20s\t%-20s\t%-10s%n",
                        rs.getString(1), rs.getString(2), rs.getDate(3));

            }
    } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * The purpose of this method is to get the most recently released song
     * with band information from both songs and composers tables of
     * Library.db
     * <p>Precondition: there are entries in songs and composers tables with
     * Dates </p>
     * <p>Postcondition: the most recent song is on the console neatly like
     * so: TITLE, BAND, RELEASE DATE</p>
     */
    public void getLatestReleaseInLib(Connection conn){
        String sql = "SELECT title, primary_ensemble, release_date FROM songs " +
                "JOIN composers USING (composer_id)" +
                "WHERE release_date=(SELECT MAX(release_date) FROM songs) ";

        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            System.out.println();
            System.out.printf("%s%n%-20s\t%-10s\t\t%-10s%n",
                    "getLatestReleaseInLib:", "TITLE", "BAND", "LATEST RELEASE DATE");
            while(rs.next()){
                System.out.printf("%-20s\t%-10s\t\t%-10s%n",
                        rs.getString(1), rs.getString(2),
                        rs.getDate(3));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
