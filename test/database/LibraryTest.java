package database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to test all methods in the database/Library class
 */
class LibraryTest {

    /**
     * The purpose of this method is to test is to test all methods in the
     * database/Library.java class
     */
    @Test
    void TestLibrary(){
        Library library = new Library();
        library.createComposerTable();
        library.createSongsTable();
        String url = library.getUrl();
        try (Connection conn = DriverManager.getConnection(url)) {
            library.insertComposer(conn, "Bob", "Marley", "The Wailers");
            library.insertComposer(conn, "Ronnie", "Van Zant", "Lynyrd Skynyrd");
            library.insertComposer(conn, "Gary", "Rossington", "Lynyrd Skynyrd");
            library.insertComposer(conn, "Ed", "King", "Lynyrd Skynyrd");
            library.insertComposer(conn, "Paul", "McCartney", "The Beatles");
            library.insertComposer(conn, "John", "Lennon", "The Beatles");
            library.insertComposer(conn, "George", "Harrison", "The Beatles");
            library.insertComposer(conn, "Robert", "Plant", "Led Zeppelin");
            library.insertComposer(conn, "Jimmy", "Page", "Led Zeppelin");
            library.insertComposer(conn, "Johnny", "Green", "Film Score Composer");
            library.insertComposer(conn, "Coleman", "Hawkins", "Solo Artist");
            library.insertComposer(conn, "Jonathan", "Cain", "Journey");


        } catch (SQLException throwables) {
            System.out.println("Some Entries Already in db");
        }
        try (Connection conn = DriverManager.getConnection(url)){
            library.queryComposers(conn);
        }catch (SQLException throwables) {
            System.out.println("Some Entries Already in db");
        }
        try (Connection conn = DriverManager.getConnection(url)){
            library.insertSong(conn, "No Woman, No Cry", "Bob", "Marley", "Natty Dread", "Island/Tuff Gong", Date.valueOf("1974-10-25"));
            library.insertSong(conn, "Let It Be", "John", "Lennon", "Paul", "McCartney", "Let It Be", "EMI", Date.valueOf("1970-05-08"));
            library.insertSong(conn, "Here Comes The Sun", "George", "Harrison", "Abbey Road", "Apple", Date.valueOf("1969-09-26"));
            library.insertSong(conn, "I Shot The Sherrif", "Bob", "Marley", "Put It On", "Island/Tuff Gong", Date.valueOf("1973-02-12"));
            library.insertSong(conn, "Stairway To Heaven", "Jimmy", "Page", "Robert", "Plant", "Led Zeppelin IV", "Atlantic", Date.valueOf("1971-11-08"));
            library.insertSong(conn, "Sweet Home Alabama", "Ed", "King", "Gary", "Rossington", "Ronnie", "Van Zant", "Second Helping", "MCA", Date.valueOf("1974-06-24"));
            library.insertSong(conn, "Don't Stop Believing", "Jonathan", "Cain", "Escape", "Columbia", Date.valueOf("1981-10-31"));
        } catch (SQLException throwables) {
            System.out.println("Some Entries Already in db");
        }
        try (Connection conn = DriverManager.getConnection(url)){
            Integer id = 3;
            library.querySongs(conn, "No Woman, No Cry");
            String title = library.querySongs(conn, id);
            System.out.println();
            System.out.println("Song ID: " + id + ", Title: " + title);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(url)){
            library.queryAllSongs(conn);
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(url)){
            library.joinComposerWithSongs(conn);
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(url)){
            library.getLatestReleaseInLib(conn);
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}