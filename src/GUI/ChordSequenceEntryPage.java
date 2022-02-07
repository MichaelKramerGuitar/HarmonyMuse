package GUI;

import AbstractStructures.Chord;
import Builders.ChordSequence;
import Builders.InvalidNoteException;
import Builders.Note;
import CommandLineApp.CommonView;
import FileHandling.WriteToJSON;
import ThreeNoteStructures.MajorTriad;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ChordSequenceEntryPage extends Application {

    private ArrayList<Chord> temp = new ArrayList<>(0);

    private ChordSequence chordSequence;

    private String filename;

    final Label fileLabel = new Label();

    final Label noteLabel = new Label();

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
        //Creating a GridPane container
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
    //Defining the Filename text field
        final TextField filename = new TextField();
        filename.setPromptText("Enter a filename");
        filename.setPrefColumnCount(10);
        GridPane.setConstraints(filename, 0, 0);
        grid.getChildren().add(filename);

        Button submitFileName = new Button("Submit");
        GridPane.setConstraints(submitFileName, 1, 0);
        grid.getChildren().add(submitFileName);

        GridPane.setConstraints(fileLabel, 0, 3);
        GridPane.setColumnSpan(fileLabel, 3);
        grid.getChildren().add(fileLabel);

        GridPane.setConstraints(noteLabel, 5, 0);
        GridPane.setColumnSpan(noteLabel, 3);
        grid.getChildren().add(noteLabel);

        Button c = new Button("c");
        GridPane.setConstraints(c, 3, 0);
        grid.getChildren().add(c);

        Button f = new Button("f");
        GridPane.setConstraints(f, 4, 0);
        grid.getChildren().add(f);

        Button maj = new Button("major-triad");
        GridPane.setConstraints(maj, 4, 3);
        grid.getChildren().add(maj);

        Button sharp = new Button(CommonView.getCharTable().getSharp());
        GridPane.setConstraints(sharp, 3, 3);
        grid.getChildren().add(sharp);

        Button submitChord = new Button("Submit Chord");
        GridPane.setConstraints(submitChord, 5, 0);
        grid.getChildren().add(submitChord);

        submitFileName.setOnAction( event ->
                {
                    this.filename = filename.getText();
                    if ((this.filename != null && !this.filename.isEmpty())){
                        fileLabel.setText(filename.getText() + " was accepted, Thank you!");
                    }
                    else {
                        fileLabel.setText("Please enter a filename");
                    }
                }
        );

        c.setOnAction( event ->
                {
                    setMajorTriad(c);
                }
        );

        f.setOnAction( event ->
                {
                    setMajorTriad(f);
                }
        );

        Scene scene = new Scene(grid, 500, 500, Color.FUCHSIA);
        mainStage.setScene(scene);
        mainStage.setTitle("Welcome to HarmonyMuse Chord Sequence Entry!");
        mainStage.show();
    }

    public void stop()
    {
        chordSequence = new ChordSequence(temp, tonalCenter);
        WriteToJSON writer = new WriteToJSON();
        writer.writeSequenceToJSON(this.filename, this.chordSequence);
        System.out.println(filename + " in \\data folder");
    }
}
