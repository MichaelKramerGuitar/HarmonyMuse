package GUI;

import AbstractStructures.Chord;
import Builders.ChordSequence;
import Builders.InvalidNoteException;
import Builders.Note;
import CommandLineApp.CharactersTable;
import FileHandling.WriteToJSON;
import ThreeNoteStructures.MajorTriad;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ChordSequenceEntryPage extends Application {

    CharactersTable ctable = new CharactersTable();
    private ArrayList<Chord> temp = new ArrayList<>(0);

    private ChordSequence chordSequence;

    private String filename;

    final Label fileLabel = new Label();

    final Label noteLabel = new Label();

    final Label accedentalLabel = new Label();

    private Note tonalCenter;

    public void setMajorTriad(Button chord){
        try {
            Note note = new Note(chord.getText());
            MajorTriad triad = new MajorTriad(note);
            temp.add(triad);
            tonalCenter = note; // temporary
        }catch (InvalidNoteException e){
            noteLabel.setText("Invalid Note");
        }
    }

    public ArrayList<Chord> getTemp(){return temp;}

    public void start(Stage mainStage)
    {

        Insets insets = new Insets(10, 40, 10, 10);

        BorderPane borderPane = new BorderPane();

        //Defining the Filename text field
        final TextField filenameField = new TextField();
        filenameField.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        filenameField.setPromptText("Enter a filename");
        filenameField.setPrefColumnCount(10);
        HBox fileEntry = new HBox();


        Button submitFileName = new Button("Submit Filename");
        submitFileName.setFont(Font.font("Verdana", FontWeight.BOLD, 11));
        //GridPane.setConstraints(submitFileName, 1, 0);

        fileEntry.getChildren().addAll(filenameField, submitFileName, fileLabel);
        borderPane.setTop(fileEntry);
        borderPane.setMargin(fileEntry, insets);

        ToggleGroup toggleGroupRoots = new ToggleGroup();


        RadioButton aButton = new RadioButton("a");
        RadioButton bButton = new RadioButton("b");
        RadioButton cButton = new RadioButton("c");
        RadioButton dButton = new RadioButton("d");
        RadioButton eButton = new RadioButton("e");
        RadioButton fButton = new RadioButton("f");
        RadioButton gButton = new RadioButton("g");

        aButton.setToggleGroup(toggleGroupRoots);
        bButton.setToggleGroup(toggleGroupRoots);
        cButton.setToggleGroup(toggleGroupRoots);
        dButton.setToggleGroup(toggleGroupRoots);
        eButton.setToggleGroup(toggleGroupRoots);
        fButton.setToggleGroup(toggleGroupRoots);
        gButton.setToggleGroup(toggleGroupRoots);


        VBox roots = new VBox();
        roots.getChildren().addAll( aButton, bButton, cButton, dButton, eButton, fButton, gButton, noteLabel);

        toggleGroupRoots.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                {

                    RadioButton rb = (RadioButton) toggleGroupRoots.getSelectedToggle();

                    if (rb != null) {
                        String s = rb.getText();

                        // change the label
                        noteLabel.setText(s + " selected");
                    }
                }
            }
        });

        borderPane.setLeft(roots);
        borderPane.setMargin(roots, insets);


        ToggleGroup toggleGroupAccidentals = new ToggleGroup();

        RadioButton sharp = new RadioButton(ctable.getSharp());
        RadioButton flat = new RadioButton(ctable.getFlat());
        RadioButton doubleSharp = new RadioButton(ctable.getDoubleSharp());
        RadioButton doubleFlat = new RadioButton(ctable.repeatStringNTimes(ctable.getFlat(), 2));

        sharp.setToggleGroup(toggleGroupAccidentals);
        flat.setToggleGroup(toggleGroupAccidentals);
        doubleSharp.setToggleGroup(toggleGroupAccidentals);
        doubleFlat.setToggleGroup(toggleGroupAccidentals);

        toggleGroupAccidentals.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                {

                    RadioButton rb = (RadioButton) toggleGroupAccidentals.getSelectedToggle();

                    if (rb != null) {
                        String s = rb.getText();

                        // change the label
                        accedentalLabel.setText(s + " selected");
                    }
                }
            }
        });

        VBox accidentals = new VBox();
        accidentals.getChildren().addAll(sharp, flat, doubleSharp, doubleFlat, accedentalLabel);

        borderPane.setCenter(accidentals);
        borderPane.setMargin(accidentals, insets);


        Button submitChord = new Button("Submit Chord");
        submitChord.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        HBox chordSubmit = new HBox(submitChord);
        borderPane.setBottom(chordSubmit);
        borderPane.setMargin(chordSubmit, insets);


        submitFileName.setOnAction( event ->
                {
                    this.filename = filenameField.getText();
                    if ((this.filename != null && !this.filename.isEmpty())){
                        fileLabel.setText(filenameField.getText() + " was accepted, Thank you!");
                    }
                    else {
                        fileLabel.setText("Please enter a filename");
                    }
                }
        );

        submitChord.setOnAction( event ->
            /*
            TODO how to unselect above radios on Chord submission
             */
                {

                    String newRoot = "";
                    RadioButton rts = (RadioButton) toggleGroupRoots.getSelectedToggle();
                    RadioButton accidents = (RadioButton) toggleGroupAccidentals.getSelectedToggle();

                    if (rts != null) {
                        String s = rts.getText();
                        // change the label
                        newRoot += s;
                    }
                    if (accidents != null) {
                        String s = accidents.getText();
                        // change the label
                        newRoot += s;
                    }

                    try{
                        Note rt = new Note(newRoot);
                        MajorTriad newChord = new MajorTriad(rt);
                        this.temp.add(newChord);
                        this.tonalCenter = rt;
                    }catch (InvalidNoteException e){
                        System.out.println(e);
                    }

                }
        );

        Scene scene = new Scene(borderPane, 500, 500, Color.FUCHSIA);
        mainStage.setScene(scene);
        mainStage.setTitle(ctable.getBeamedEighths() + " Welcome to HarmonyMuse Chord Sequence Entry " + ctable.getBeamedEighths());
        mainStage.show();
    }

    public void stop()
    {
        chordSequence = new ChordSequence(temp, tonalCenter);
        WriteToJSON writer = new WriteToJSON();
        if(this.filename != null && !filename.isEmpty()) {
            writer.writeSequenceToJSON(this.filename, this.chordSequence);
            System.out.println(filename + " in \\data folder");
        }else System.out.println("Nothing written to file");
    }
}
