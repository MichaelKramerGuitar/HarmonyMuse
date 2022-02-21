package gui;

import database.Library;
import general.containers.Chord;
import builders.ChordSequence;
import commandline.app.CharactersTable;
import commandline.app.CommonView;
import file.handling.ReadFromJSON;

import java.util.stream.Stream;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to be the controller class betwenn the
 * ReadSequencePage GUI View and the Model/Business Logic of HarmonyMuse
 */
public class ChordReadGUIController {

    private CharactersTable ctable = new CharactersTable();

    private ReadFromJSON reader = new ReadFromJSON();

    private ChordSequence chordSequence;

    private static Library library = new Library();

    public static Library getLibrary() {
        return library;
    }

    // getters

    /**
     * The purpose of this method is to retrieve a characters table which is
     * a class attribute to this Controller to render
     * unicode characters like sharps and flats to the GUI
     */
    public CharactersTable getCTable() {
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
     * The purpose of this method is to get the ReadFromJSON reader class attribute
     * of this controller for serialization functions
     */
    public ReadFromJSON getReader() {
        return reader;
    }


    /**
     * The purpose of this method is to provide Roman Numeral Analysis of a ChordSequence
     * to the GUI
     * <p>Precondition: A ChordSequence has been read from file or DB</p>
     * <p>Postcondition: Roman Numeral Analysis of the sequence is returned as a String
     * for display</p>
     *
     * @return Roman Numeral Analysis of the sequence is returned as a String
     * for display
     */
    public String analyze(ChordSequence chordSequence){
        Stream<Chord> chordStream = chordSequence.chordSequenceToChordStream(chordSequence);
        System.out.println();
        chordStream.forEach(i -> System.out.println(i));
        System.out.println();
        String returnText = "";
        String[] romans = CommonView.addRomanNumeral(chordSequence);
        StringBuilder tonalCenter = CommonView.replaceAccidental(chordSequence.getTonalCenter());
        returnText += "Sequence tonal center: " + tonalCenter.toString().toUpperCase() + "\n";
        for (String roman: romans
        ) {
            returnText += roman + " ";
        }
        return returnText;
    }
}
