package gui;

import builders.ChordSequence;
import file.handling.ReadFromJSON;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to allow a user to read in a file containing a
 * ChordSequence that has previously been written and display Roman Numeral
 * harmonic analysis of the ChordSequence from file to the GUI
 */
public class ReadSequencePage extends Application {

    private Desktop desktop = Desktop.getDesktop();

    private ChordReadGUIController controller = new ChordReadGUIController();

    private final Label fileChooserLabel = new Label();

    final Label pubLibLabel = new Label();

    private ComboBox filesInPubLib = new ComboBox();

    private Button readPubFile = new Button();

    private VBox publicLibrary = new VBox(filesInPubLib, pubLibLabel, readPubFile);

    public ComboBox getFilesInPubLib() {
        return filesInPubLib;
    }

    private void configurePubLibMenu(){
        ArrayList<String> files = new ArrayList<String>();

        String currentDir = System.getProperty("user.dir");
        Path path = Paths.get(currentDir, "data", "concurrencyLib");
        File[] fileList = new File(String.valueOf(path)).listFiles();
        for (File file:
                fileList ) {
            if(file.isFile()){
                filesInPubLib.getItems().add(file);
            }
        }
    }
    /**
     * The purpose of this method is to configure the FileChooser to only
     * look for files ending in the appropriate file extension and to
     * begin looking in the current user directory
     * the code comes in part from https://docs.oracle.com/javafx/2/ui_controls/file-chooser.htm
     */
    private static void configureFileChooserUserLib(
            final FileChooser fileChooser) {
        fileChooser.setTitle("Open file");
        String currentDir = System.getProperty("user.dir");
        Path path = Paths.get(currentDir, "data");
        File targetDir = path.toFile();
        fileChooser.setInitialDirectory(
                targetDir
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON", "*.json")
        );
    }

    private static void configureFileChooserPublicLib(
            final FileChooser fileChooser) {
        fileChooser.setTitle("Open file");
        String currentDir = System.getProperty("user.dir");
        Path path = Paths.get(currentDir, "data", "concurrencyLib");
        File targetDir = path.toFile();
        fileChooser.setInitialDirectory(
                targetDir
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON", "*.json")
        );
    }

    /**
     * The purpose of this method is to return the name of a file whose contents
     * are desired for harmonic analysis in the users personal library simulated
     * by the data folder
     * the code comes in part from https://docs.oracle.com/javafx/2/ui_controls/file-chooser.htm
     * <p>Precondition: The file exists</p>
     * <p>Postcondition: The file is opened and the files name minus the
     * extension is returned as a String</p>
     *
     * @return String filename minus the file extension
     */
    private String openFile(File file) {
        CharSequence target = ".json";
        CharSequence empty = "";
        String filename = file.getName();
        String noExt = filename.replace(target, empty);
        return noExt;
    }

    @Override
    public void start(Stage mainStage) throws Exception {

        Insets insets = new Insets(10, 10, 10, 10);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(insets);

        Text display = new Text();
        display.setFont(Font.font("Roboto", FontWeight.BOLD, 26));
        borderPane.setBottom(display);

        Button myLibrary = new Button();
        myLibrary.setText("My Library");
        myLibrary.setFont(Font.font("Roboto", FontWeight.BOLD, 12));
        fileChooserLabel.setFont(Font.font("Roboto", FontWeight.BOLD, 12));
        HBox fileChooseButton = new HBox(myLibrary, fileChooserLabel);


        borderPane.setCenter(fileChooseButton);

        configurePubLibMenu();
        readPubFile.setText("Read Library Selection");
        readPubFile.setFont(Font.font("Roboto", FontPosture.ITALIC, 12));
        pubLibLabel.setFont(Font.font("Roboto", FontWeight.BOLD, 14));
        pubLibLabel.setText("Public Library");
        borderPane.setRight(publicLibrary);

        /**
         * the purpose of this method is to search the users current directory
         * for the desired json file. For this project all data is in the
         * cwd/data folder.
         */
        myLibrary.setOnAction(event ->
        {
            myLibrary.setVisible(false);
            FileChooser fileSelector = new FileChooser();
            configureFileChooserUserLib(fileSelector);
            fileSelector.setTitle("Open Resource File");

            File file = fileSelector.showOpenDialog(mainStage);

            if (file != null) {
                String filename = openFile(file);
                if (filename != null && !filename.equals("")) {
                    ReadFromJSON reader = controller.getReader();
                    fileChooserLabel.setFont(Font.font("Roboto", FontWeight.BOLD, 18));
                    fileChooserLabel.setText("See console for individual chords in " + filename);
                    ChordSequence deserializedSeq = reader.readChordSequenceFromJSON(filename);
                    String analysis = controller.analyze(deserializedSeq);
                    display.setText(analysis);
                }
            }
        });

        readPubFile.setOnAction( event ->
        {
            String filePath = filesInPubLib.getValue().toString();
            if(filePath != "" && filePath != null){
                File path = new File(filePath);
                String fileName = path.getAbsolutePath().substring(path.getAbsolutePath().lastIndexOf("\\")+1);
                System.out.println(fileName);
            }
        });

        Scene scene = new Scene(borderPane, 800, 400);
        scene.getStylesheets().add("file:assets/styles.css");
        borderPane.setId("border-pane-chordRead");
        mainStage.setScene(scene);
        mainStage.setTitle(controller.getCTable().getBeamedEighths() + " Welcome to HarmonyMuse Chord Sequence Analysis " + controller.getCTable().getBeamedEighths());
        mainStage.show();

    }

    /**
     * the purpose of this method is to print a nicely formatted exit to the
     * console upon closing of this app
     */
    public void stop() {
        System.out.printf("%n%s%n%s%n%s%n",
                controller.getCTable().repeatStringNTimes(controller.getCTable().getBeamedEighths(), 28),
                controller.getCTable().repeatStringNTimes(controller.getCTable().getGuitar(), 2) +
                        " Thank you for using HarmonyMuse " +
                        controller.getCTable().repeatStringNTimes(controller.getCTable().getTrebleClef(), 2),
                controller.getCTable().repeatStringNTimes(controller.getCTable().getBeamedEighths(), 28));
    }

    public class ReadFromLibraryJSON implements Runnable {

        private ReadFromJSON reader = new ReadFromJSON();

        @Override
        public void run() {


        }
    }
}
