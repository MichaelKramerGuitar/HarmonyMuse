package GUI;

import AbstractStructures.Chord;
import Builders.ChordSequence;
import Builders.Note;
import Classifiers.TriadClassifier;
import CommandLineApp.CharactersTable;
import FileHandling.WriteToJSON;

import java.util.ArrayList;

public class ChordEntryGUIController {

    private CharactersTable ctable = new CharactersTable();

    private ChordSequence chordSequence;

    private TriadClassifier tc = new TriadClassifier();

    private WriteToJSON writer = new WriteToJSON();

    // Getters
    public CharactersTable getCTable(){
        return ctable;
    }


    public ChordSequence getChordSequence() {
        return chordSequence;
    }

    public TriadClassifier getTC() {
        return tc;
    }

    public WriteToJSON getJSONWriter(){
        return writer;
    }
    public void setChordSequence(ArrayList<Chord> temp, Note tonalCenter){
        this.chordSequence = new ChordSequence(temp, tonalCenter);
    }


}
