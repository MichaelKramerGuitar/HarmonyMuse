package GUI;

import Builders.ChordSequence;
import FileHandling.ReadFromJSON;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadSequencePage extends Application {

    private Desktop desktop = Desktop.getDesktop();

    private ChordReadGUIController controller = new ChordReadGUIController();

    private final Label fileChooserLabel = new Label();

    private static void configureFileChooser(
            final FileChooser fileChooser) {
        fileChooser.setTitle("Open file");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.dir"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON", "*.json")
        );
    }

    private String openFile(File file) {
        try {
            CharSequence target = ".json";
            CharSequence empty = "";
            String filename = file.getName();
            String noExt = filename.replace(target, empty);
            desktop.open(file); // so we can see file contents, maybe remove?
            return noExt;
        } catch (IOException ex) {
            Logger.getLogger(ReadSequencePage.class.getName()).log(
                    Level.SEVERE, null, ex
            );
        }
        return null;
    }

    @Override
    public void start(Stage mainStage) throws Exception {

        Insets insets = new Insets(10, 10, 10, 10);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(insets);

        Text display = new Text();
        display.setFont(Font.font("Roboto", FontWeight.BOLD, 26));
        borderPane.setBottom(display);

        Button fileChooser = new Button();
        fileChooser.setText("Choose File");
        fileChooser.setFont(Font.font("Roboto", FontWeight.BOLD, 12));
        fileChooserLabel.setFont(Font.font("Roboto", FontWeight.BOLD, 12));
        HBox fileChooseButton = new HBox(fileChooser, fileChooserLabel);


        borderPane.setCenter(fileChooseButton);

        fileChooser.setOnAction( event ->
        {
            fileChooser.setVisible(false);
            FileChooser fileSelector = new FileChooser();
            configureFileChooser(fileSelector);
            fileSelector.setTitle("Open Resource File");

            File file = fileSelector.showOpenDialog(mainStage);

            if(file != null){
                String filename = openFile(file);
                if(filename != null && !filename.equals("")) {
                    ReadFromJSON reader = controller.getReader();
                    fileChooserLabel.setFont(Font.font("Roboto", FontWeight.BOLD, 18));
                    fileChooserLabel.setText("See console for individual chords");
                    ChordSequence deserializedSeq = reader.readChordSequenceFromJSON(filename);
                    String analysis = controller.analyze(deserializedSeq);
                    display.setText(analysis);
                }
            }
//            List<File> list = fileSelector.showOpenMultipleDialog(mainStage);
//            if (list != null){
//                for (File file : list){
//                    openFile(file);
//                }
//            }

//            if(file != null){
//                openFile(file);
//                ChordSequence deserializedSequence = controller.getReader().readChordSequenceFromJSON(file.getName());
//                controller.analyze(deserializedSequence);
//            }


        });



        Scene scene = new Scene(borderPane, 500, 400);
        scene.getStylesheets().add("file:assets/styles.css");
        borderPane.setId("border-pane-chordRead");
        mainStage.setScene(scene);
        //mainStage.initStyle(StageStyle.TRANSPARENT);
        mainStage.setTitle(controller.getCTable().getBeamedEighths() + " Welcome to HarmonyMuse Chord Sequence Analysis " + controller.getCTable().getBeamedEighths());
        mainStage.show();

    }


}
