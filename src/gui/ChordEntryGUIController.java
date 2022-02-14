package gui;

import general.containers.Chord;
import builders.ChordSequence;
import builders.Note;
import classifiers.TriadClassifier;
import commandline.app.CharactersTable;
import file.handling.WriteToJSON;

import java.util.ArrayList;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to be the controller class between the Chord Entry
 * GUI and the Model/data classes in the app
 */
public class ChordEntryGUIController {

    private CharactersTable ctable = new CharactersTable();

    private ChordSequence chordSequence;

    private TriadClassifier tc = new TriadClassifier();

    private WriteToJSON writer = new WriteToJSON();

    // Getters

    /**
     * The purpose of this method is to retrieve a characters table which is
     * a class attribute to this Controller to render
     * unicode characters like sharps and flats to the GUI
     */
    public CharactersTable getCTable(){
        return ctable;
    }

    /**
     * The purpose of this method is to get the ChordSequence class attribute
     * data structure
     */
    public ChordSequence getChordSequence() {
        return chordSequence;
    }

    /**
     * The purpose of this method is to get the TriadClassifier class attribute
     * of this controller
     */
    public TriadClassifier getTC() {
        return tc;
    }

    /**
     * The purpose of this method is to get the WriteToJSON writer class attribute
     * of this controller for serialization functions
     */
    public WriteToJSON getJSONWriter(){
        return writer;
    }

    /**
     * The purpose of this method is to set the ChordSequence attribute of this
     * class by passing in a Note tonalCenter and an ArrayList of Chords
     * @param temp an Array List of Chords
     * @param tonalCenter a Note tonalCenter
     */
    public void setChordSequence(ArrayList<Chord> temp, Note tonalCenter){
        this.chordSequence = new ChordSequence(temp, tonalCenter);
    }


}
