package gui;

import database.Library;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to allow for ADMIN profiles to
 * enter metadata for songs in the data/concurrencyLib public library
 * folder (common songs for analysis)
 */
public class PublicLibraryEntryPageAdmin extends Application  {

    private Library library = LibraryEntryAdminGUIController.getLibrary();
    private StackPane root = new StackPane();
    //private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        library.createSongsTable();
        library.createComposerTable();
        Scene scene = new Scene(root,400,800);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("ADMIN ONLY: Library.db songs table entry form");
        primaryStage.setAlwaysOnTop(true);

        Button button = new Button("SUBMIT SONG");
        VBox vBox = new VBox();

        vBox.setSpacing(8);
        vBox.setPadding(new Insets(10,10,10,10));
        Label titleLabel = new Label("Song Title");
        TextField title = new TextField();
        Label composerFirstLabel = new Label("Composer First");
        TextField composerFirst = new TextField();
        Label composerLastLabel = new Label("Composer Last");
        TextField composerLast = new TextField();
        Label composerBandLabel = new Label("Composer Primary Ensemble");
        TextField composerBand = new TextField();
        Label composer2FirstLabel = new Label("Composer2 First");
        TextField composer2First = new TextField();
        Label composer2LastLabel = new Label("Composer2 Last");
        TextField composer2Last = new TextField();
        Label composer2BandLabel = new Label("Composer2 Primary Ensemble");
        TextField composer2Band = new TextField();
        Label composer3FirstLabel = new Label("Composer3 First");
        TextField composer3First = new TextField();
        Label composer3LastLabel = new Label("Composer3 Last");
        TextField composer3Last = new TextField();
        Label composer3BandLabel = new Label("Composer3 Primary Ensemble");
        TextField composer3Band = new TextField();
        Label albumLabel = new Label("Album");
        TextField album = new TextField();
        Label labelLabel = new Label("Label");
        TextField label = new TextField();
        Label releaseLabel = new Label("Release Date");
        final DatePicker releaseDate = new DatePicker();
        releaseDate.setOnAction(new EventHandler() {
            public void handle(Event t) {
                LocalDate date = releaseDate.getValue();
                System.err.println("Selected date: " + date);
            }
        });
        vBox.getChildren().addAll(
                titleLabel,
                title,
                composerFirstLabel,
                composerFirst,
                composerLastLabel,
                composerLast,
                composerBandLabel,
                composerBand,
                composer2FirstLabel,
                composer2First,
                composer2LastLabel,
                composer2Last,
                composer2BandLabel,
                composer2Band,
                composer3FirstLabel,
                composer3First,
                composer3LastLabel,
                composer3Last,
                composer3BandLabel,
                composer3Band,
                albumLabel,
                album,
                labelLabel,
                label,
                releaseLabel,
                releaseDate,
                button
        );

        root.getChildren().addAll(vBox);

        /**
         * the purpose of this method is to collect the metadata for all
         * songs entered into the data/cuncurrencyLib public library folder
         * and enter this data into the Library.db file into all appropriate
         * tables
         */
        button.setOnAction(actionEvent-> {
            if(!composerFirst.getText().isEmpty()
                    && !composerLast.getText().isEmpty()
                    && !composerBand.getText().isEmpty()
                    && composer2First.getText().isEmpty()){
                try (Connection conn = DriverManager.getConnection(library.getUrl())) {
                    library.insertComposer(conn,
                            composerFirst.getText(),
                            composerLast.getText(),
                            composerBand.getText());
                }catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try (Connection conn = DriverManager.getConnection(library.getUrl())) {
                    library.insertSong(conn,
                            title.getText(),
                            composerFirst.getText(),
                            composerLast.getText(),
                            album.getText(),
                            label.getText(),
                            Date.valueOf(releaseDate.getValue().toString()));

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                title.clear();
                composerFirst.clear();
                composerLast.clear();
                composerBand.clear();
                album.clear();
                label.clear();
                releaseDate.getEditor().clear();
            }
            if(!composerFirst.getText().isEmpty()
                    && !composerLast.getText().isEmpty()
                    && !composerBand.getText().isEmpty()
                    && !composer2First.getText().isEmpty()
                    && !composer2Last.getText().isEmpty()
                    && !composer2Band.getText().isEmpty()
                    && composer3First.getText().isEmpty()) {
                try (Connection conn = DriverManager.getConnection(library.getUrl())) {
                    library.insertComposer(conn,
                            composerFirst.getText(),
                            composerLast.getText(),
                            composerBand.getText());
                }catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try (Connection conn = DriverManager.getConnection(library.getUrl())) {
                    library.insertComposer(conn,
                            composer2First.getText(),
                            composer2Last.getText(),
                            composer2Band.getText());
                }catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try (Connection conn = DriverManager.getConnection(library.getUrl())) {
                    library.insertSong(conn,
                            title.getText(),
                            composerFirst.getText(),
                            composerLast.getText(),
                            composer2First.getText(),
                            composer2Last.getText(),
                            album.getText(),
                            label.getText(),
                            Date.valueOf(releaseDate.getValue().toString()));

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                title.clear();
                composerFirst.clear();
                composerLast.clear();
                composerBand.clear();
                composer2First.clear();
                composer2Last.clear();
                composer2Band.clear();
                album.clear();
                label.clear();
                releaseDate.getEditor().clear();
            }
            if(!composerFirst.getText().isEmpty()
                    && !composerLast.getText().isEmpty()
                    && !composerBand.getText().isEmpty()
                    && !composer2First.getText().isEmpty()
                    && !composer2Last.getText().isEmpty()
                    && !composer2Band.getText().isEmpty()
                    && !composer3First.getText().isEmpty()
                    && !composer3Last.getText().isEmpty()
                    && !composer3Band.getText().isEmpty()) {
                try (Connection conn = DriverManager.getConnection(library.getUrl())) {
                    library.insertComposer(conn,
                            composerFirst.getText(),
                            composerLast.getText(),
                            composerBand.getText());
                }catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try (Connection conn = DriverManager.getConnection(library.getUrl())) {
                    library.insertComposer(conn,
                            composer2First.getText(),
                            composer2Last.getText(),
                            composer2Band.getText());
                }catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try (Connection conn = DriverManager.getConnection(library.getUrl())) {
                    library.insertComposer(conn,
                            composer3First.getText(),
                            composer3Last.getText(),
                            composer3Band.getText());
                }catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try (Connection conn = DriverManager.getConnection(library.getUrl())) {
                    library.insertSong(conn,
                            title.getText(),
                            composerFirst.getText(),
                            composerLast.getText(),
                            composer2First.getText(),
                            composer2Last.getText(),
                            composer3First.getText(),
                            composer3Last.getText(),
                            album.getText(),
                            label.getText(),
                            Date.valueOf(releaseDate.getValue().toString()));

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                title.clear();
                composerFirst.clear();
                composerLast.clear();
                composerBand.clear();
                composer2First.clear();
                composer2Last.clear();
                composer2Band.clear();
                composer3First.clear();
                composer3Last.clear();
                composer3Band.clear();
                album.clear();
                label.clear();
                releaseDate.getEditor().clear();
            }
        });
    }

    @Override
    public void stop() {
        System.out.println();
        System.out.println("ALL CURRENT ENTRIES BELOW");
        System.out.println();
        try(Connection conn = DriverManager.getConnection(library.getUrl())) {
            library.queryComposers(conn);
            library.queryAllSongs(conn);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
