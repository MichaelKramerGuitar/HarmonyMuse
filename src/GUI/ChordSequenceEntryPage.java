package GUI;

import AbstractStructures.Chord;
import Builders.InvalidNoteException;
import Builders.Note;
import FileHandling.WriteToJSON;
import ThreeNoteStructures.AugmentedTriad;
import ThreeNoteStructures.DiminishedTriad;
import ThreeNoteStructures.MajorTriad;
import ThreeNoteStructures.MinorTriad;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to allow a user to enter a ChordSequence that
 * can be written to file for later analysis
 */
public class ChordSequenceEntryPage extends Application {

    private ChordEntryGUIController controller = new ChordEntryGUIController();

    private ArrayList<Chord> temp = new ArrayList<>(0);

    private String filename;

    final Label fileLabel = new Label();

    final Label noteLabel = new Label();

    final Label accedentalLabel = new Label();

    final Label chordLabel = new Label();

    final Label tCenter = new Label();

    final Label quality = new Label();

    private Note tonalCenter;


    /**
     * The purpose of this method is purpose of this method is to override the
     * Application interfaces start method
     */
    public void start(Stage mainStage)
    {

        Insets insets = new Insets(10, 10, 10, 10);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(insets);

        //Defining the Filename text field
        final TextField filenameField = new TextField();
        filenameField.setFont(Font.font("Roboto", FontWeight.BOLD, 11));
        filenameField.setPromptText("Enter a filename");
        filenameField.setPrefColumnCount(10);
        HBox fileEntry = new HBox();


        Button submitFileName = new Button("Submit Filename");
        submitFileName.setFont(Font.font("Roboto", FontWeight.BOLD, 11));
        //GridPane.setConstraints(submitFileName, 1, 0);

        fileLabel.setText("enter a filename");
        fileLabel.setFont(Font.font("Roboto", FontWeight.BOLD, 12));
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

        noteLabel.setText("choose sequence tonal center");
        noteLabel.setFont(Font.font("Roboto", FontWeight.BOLD, 12));

        VBox roots = new VBox();
        roots.getChildren().addAll( aButton, bButton, cButton, dButton, eButton, fButton, gButton, noteLabel);

        borderPane.setLeft(roots);
        borderPane.setMargin(roots, insets);

        ComboBox tonalCenterSelection = new ComboBox();
        tonalCenterSelection.getItems().add("C");
        tonalCenterSelection.getItems().add("C" + controller.getCTable().getSharp());
        tonalCenterSelection.getItems().add("D" + controller.getCTable().getFlat());
        tonalCenterSelection.getItems().add("D");
        tonalCenterSelection.getItems().add("E" + controller.getCTable().getFlat());
        tonalCenterSelection.getItems().add("E");
        tonalCenterSelection.getItems().add("F");
        tonalCenterSelection.getItems().add("F" + controller.getCTable().getSharp());
        tonalCenterSelection.getItems().add("G" + controller.getCTable().getFlat());
        tonalCenterSelection.getItems().add("G");
        tonalCenterSelection.getItems().add("A" + controller.getCTable().getFlat());
        tonalCenterSelection.getItems().add("A");
        tonalCenterSelection.getItems().add("B" + controller.getCTable().getFlat());
        tonalCenterSelection.getItems().add("B");

        Button submitTonalCenter = new Button("choose tonal center");
        submitTonalCenter.setFont(Font.font("Roboto", FontWeight.BOLD, 12));
        VBox tonalCenters = new VBox(tCenter, tonalCenterSelection, submitTonalCenter);
        tonalCenters.setMinWidth(Control.USE_PREF_SIZE);
        borderPane.setRight(tonalCenters);
        borderPane.setMargin(tonalCenterSelection, insets);
        tCenter.setText("choose sequence tonal center");
        tCenter.setFont(Font.font("Roboto", FontWeight.BOLD, 12));



        ToggleGroup toggleGroupAccidentals = new ToggleGroup();

        RadioButton sharp = new RadioButton(controller.getCTable().getSharp());
        RadioButton flat = new RadioButton(controller.getCTable().getFlat());
        RadioButton doubleSharp = new RadioButton(controller.getCTable().getDoubleSharp());
        RadioButton doubleFlat = new RadioButton(controller.getCTable().repeatStringNTimes(controller.getCTable().getFlat(), 2));

        sharp.setToggleGroup(toggleGroupAccidentals);
        flat.setToggleGroup(toggleGroupAccidentals);
        doubleSharp.setToggleGroup(toggleGroupAccidentals);
        doubleFlat.setToggleGroup(toggleGroupAccidentals);

        toggleGroupRoots.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            /**
             * The purpose of this method is let the user know that the system
             * is tracking their entry
             */
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

        toggleGroupAccidentals.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            /**
             * The purpose of this method is let the user know that the system
             * is tracking their entry
             */
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

        accedentalLabel.setText("select an accidental");
        accedentalLabel.setFont(Font.font("Roboto", FontWeight.BOLD, 12));
        VBox accidentals = new VBox();
        accidentals.getChildren().addAll(sharp, flat, doubleSharp, doubleFlat, accedentalLabel);

        borderPane.setCenter(accidentals);
        borderPane.setMargin(accidentals, insets);


        ComboBox qualities = new ComboBox();
        qualities.getItems().add(controller.getTC().getTriadQualities()[2]);
        qualities.getItems().add(controller.getTC().getTriadQualities()[1]);
        qualities.getItems().add(controller.getTC().getTriadQualities()[0]);
        qualities.getItems().add(controller.getTC().getTriadQualities()[3]);
        VBox quals = new VBox(qualities, quality);

        quality.setFont(Font.font("Roboto", FontWeight.BOLD, 10));
        quality.setText("Choose a quality");


        Button submitChord = new Button("Submit Chord");
        submitChord.setFont(Font.font("Roboto", FontWeight.BOLD, 12));
        Button submitSequence = new Button("Submit Chord Sequence");
        submitSequence.setFont(Font.font("Roboto", FontWeight.BOLD, 12));
        chordLabel.setFont(Font.font("Roboto", FontWeight.BOLD, 12));
        HBox chordSubmit = new HBox(quals, submitChord, submitSequence, chordLabel);
        borderPane.setBottom(chordSubmit);
        borderPane.setMargin(chordSubmit, insets);

        /**
         * The purpose of this method is to remember the entered file name into
         * the given textfield
         * <p>Precondition: a user has entered a filename to the
         * appropriate field and clicked submit </p>
         * <p>Postcondition: The filename is remebered such that when the program
         * closes it can become the name of the file holding the inputted ChordSequence</p>
         */
        submitFileName.setOnAction( event ->
                {
                    this.filename = filenameField.getText();
                    if ((this.filename != null && !this.filename.isEmpty())){
                        fileLabel.setText(filenameField.getText() + " was accepted, Thank you!");
                        submitFileName.setVisible(false);
                    }
                    else {
                        fileLabel.setText("Please enter a filename");
                    }
                }
        );

        /**
         * The purpose of this method is to get the String information from the
         * Radio buttons to create a Note then a Chord
         *
         * <p>Precondition: The buttons have been selected and Submit Chord
         * has been clicked</p>
         * <p>Postcondition: the String information from the radio buttons is
         * used to create HarmonyMuse Note and Chord objects to be added to
         * a ChordSequence and written to file</p>
         */
        submitChord.setOnAction( event ->

                {

                    String q = (String) qualities.getValue();
                    if(q != "" && q != null){
                        String newRoot = "";
                        RadioButton rts = (RadioButton) toggleGroupRoots.getSelectedToggle();
                        RadioButton accidents = (RadioButton) toggleGroupAccidentals.getSelectedToggle();

                        String submission = "";

                        if (rts != null) {
                            String s = rts.getText();
                            // change the label
                            newRoot += s;
                            submission += s;
                        }
                        if (accidents != null) {
                            submission += accidents.getText();
                            if(accidents.getText().equals(controller.getCTable().getSharp())){
                                newRoot += "#";
                            }
                            else if(accidents.getText().equals(controller.getCTable().getFlat())){
                                newRoot += "-";
                            }
                            else if(accidents.getText().equals(controller.getCTable().getDoubleSharp())){
                                newRoot += "##";
                            }
                            else if(accidents.getText().equals(controller.getCTable().repeatStringNTimes(controller.getCTable().getFlat(), 2))){
                                newRoot += "--";
                            }
                        }

                        try{
                            Note rt = new Note(newRoot);
                            if(q.equals(controller.getTC().getTriadQualities()[0])) {
                                DiminishedTriad newChord = new DiminishedTriad(rt);
                                this.temp.add(newChord);
                            }
                            else if(q.equals(controller.getTC().getTriadQualities()[1])){
                                MinorTriad newChord = new MinorTriad(rt);
                                this.temp.add(newChord);
                            }
                            else if(q.equals(controller.getTC().getTriadQualities()[2])){
                                MajorTriad newChord = new MajorTriad(rt);
                                this.temp.add(newChord);
                            }
                            else if(q.equals(controller.getTC().getTriadQualities()[3])){
                                AugmentedTriad newChord = new AugmentedTriad(rt);
                                this.temp.add(newChord);
                            }

                        }catch (InvalidNoteException e){
                            System.out.println(e);
                        }
                        toggleGroupAccidentals.getToggles().stream().forEach((button) -> {
                            button.setSelected(false);
                        });
                        toggleGroupRoots.getToggles().stream().forEach((button) -> {
                            button.setSelected(false);
                        });

                        submitChord.setText("Submit Another Chord");
                        chordLabel.setText("Submission received: " + submission + " " + qualities.getValue().toString());
                    }
                    else {
                        quality.setText("Select a quality before submission");
                    }

                }


        );

        /**
         * The purpose of this method is to write the ChordSequence submitted
         * to file
         * <p>Precondition: a filename, tonal center and chords have successfully
         * been entered</p>
         * <p>Postcondition: the ChordSequence is written to the specified file
         * in the default location</p>
         */
        submitTonalCenter.setOnAction( event ->
                {
                    String tc = tonalCenterSelection.getValue().toString().toLowerCase();
                    if(tc.contains(controller.getCTable().getFlat())){
                        tc = tc.charAt(0) + "-";
                    }
                    else if(tc.contains(controller.getCTable().getSharp())){
                        tc = tc.charAt(0) + "#";
                    }
                    try {
                        this.tonalCenter = new Note(tc);
                        tCenter.setText("Tonal Center Accepted!");
                        submitTonalCenter.setVisible(false);
                    }catch (InvalidNoteException e){
                        System.out.println(e);
                    }
                }

        );

        submitSequence.setOnAction( event ->
        {
            if(this.filename != null && !filename.isEmpty() && this.tonalCenter != null && !this.temp.isEmpty()) {
                controller.setChordSequence(temp, tonalCenter);
                WriteToJSON writer = controller.getJSONWriter();
                writer.writeSequenceToJSON(this.filename, controller.getChordSequence());
                chordLabel.setFont(Font.font("Roboto", FontWeight.BOLD, 14));
                chordLabel.setText(filename + " in \\data folder");
            }else System.out.println("Nothing written to file");
        });

        noteLabel.setText("select root for next chord");
        Scene scene = new Scene(borderPane, 700, 400);
        scene.getStylesheets().add("file:assets/styles.css");
        borderPane.setId("border-pane-chordEntry");
        mainStage.setScene(scene);
        //mainStage.initStyle(StageStyle.TRANSPARENT);
        mainStage.setTitle(controller.getCTable().getBeamedEighths() + " Welcome to HarmonyMuse Chord Sequence Entry " + controller.getCTable().getBeamedEighths());
        mainStage.show();

    }

    public void stop()
    {
        System.out.printf("%n%s%n%s%n%s%n",
                controller.getCTable().repeatStringNTimes(controller.getCTable().getBeamedEighths(), 28),
                controller.getCTable().repeatStringNTimes(controller.getCTable().getGuitar(), 2) +
                " Thank you for using HarmonyMuse " +
                        controller.getCTable().repeatStringNTimes(controller.getCTable().getTrebleClef(), 2),
                controller.getCTable().repeatStringNTimes(controller.getCTable().getBeamedEighths(), 28));
    }
}
