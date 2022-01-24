package AbstractStructures;

import Builders.Interval;
import Builders.Note;
import CommandLineApp.CharactersTable;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * The abstract chord class' goal is to be flexible enough such that it might
 * provide tools to subclasses that handle various types of input data such as
 * strings, char arrays and eventually midi input hence the use of the
 * generic type in the class declaration.
 *
 * 1) getPermutations() should take input data and output all harmonic
 * possibilities
 *
 * 2) getDegrees() should map data to the respective chord degrees
 *
 * 3) digitize() should map characters
 * (c, c#, d-, d, d#, e-, e, f, f#, g-, g, g#, a-, a, a#, b-, b)
 * to digits in range 0-11 where enharmonic pitches are mapped to the same
 * digit:
 * c#/d- <--> 1
 * d#/e- <--> 3
 * f#/g- <--> 6
 * g#/a- <--> 8
 * a#/b- <--> 10
 *
 *
 * 5) quality() should remain abstract to be implemented by concrete subclasses
 */

public abstract class Chord {

    private Note[] rawData;
    private String quality; // i.e. "Major Triad", "Minor Seven"

    private ArrayList<Interval> intervals = new ArrayList<Interval>(0);
    //constructors
    public Chord() { }

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
