package AbstractStructures;

import Builders.Interval;
import Builders.Note;
import CommandLineApp.CharactersTable;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * @author Michael Kramer
 *
 * CS622 Spring 1, 2022
 * Advanced Programming Techniques.
 *
 * The purpose of this class is to provide a general framework for the chord
 * datastructure, a fundamental structure to this application
 *
 */

public abstract class Chord {

    private Note[] rawData;
    private String quality; // i.e. "Major Triad", "Minor Seven"

    private ArrayList<Interval> intervals = new ArrayList<Interval>(0);
    //constructors
    public Chord() { }

    /**
     * The purpose of this constructor is to build a chord object out of note
     * objects and determine the intervals of each note in the chord in relation
     * to the "lowest" sounding note
     * @param notes an array of Note objects
     */
    public Chord(Note[] notes){

        this.rawData = notes;
        for(int i = 0; i < (notes.length - 1); i++){ // ex: 4 note array >>> need: (0, 1), (0, 2), (0, 3)
            Interval interval = new Interval(new Pair<>(notes[0], notes[i + 1]));
            this.intervals.add(interval);
        }
    }

    // getters
    public Note[] getNotes(){return rawData;}

    public ArrayList<Interval> getIntervals(){return intervals;}

    public abstract Note getRoot(); // every chord has to have a root

    public abstract void setQuality(String quality);

    /**
     * The purpose of the method is to replace system placeholders for academic
     * symbols with unicode strings, primarily for pretty printing in the UI
     * @param note a Note object
     * @return A String builder with accidental replaced by unicode symbol
     */
    public StringBuilder replaceAccidental(Note note){
        CharactersTable cTable = new CharactersTable();
        String n = note.toString();
        StringBuilder temp = new StringBuilder(note.toString());
        if(n.contains("-")){
            temp.deleteCharAt(1);
            temp.append(cTable.getFlat());
        }
        if (n.contains("#")){
            temp.deleteCharAt(1);
            temp.append(cTable.getSharp());
        }
        if (n.contains("--")) {
            temp.deleteCharAt(1);
            temp.deleteCharAt(1);
            temp.append(cTable.repeatStringNTimes(cTable.getFlat(), 2));
        }
        if (n.contains("##")){
            temp.deleteCharAt(1);
            temp.deleteCharAt(1);
            temp.append(cTable.getDoubleSharp());
        }
        return temp;
    }



    @Override
    public String toString(){
        return String.format(Arrays.toString(rawData));
    }
}
