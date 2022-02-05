package AbstractStructures;

import Builders.Interval;
import Builders.Note;
import CommandLineApp.CharactersTable;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import javafx.util.Pair;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * @author Michael Kramer
 *
 * CS622 Spring 1, 2022
 * Advanced Programming Techniques.
 *
 *<p>
 * The purpose of this class is to provide a general framework for the chord
 * datastructure, a fundamental structure to this application</p>
 *
 */

public abstract class Chord implements JsonSerializer<Chord> {

    private Note[] rawData;
    private String quality; // i.e. "Major Triad", "Minor Seven"

    private ArrayList<Interval> intervals = new ArrayList<Interval>(0);

    //constructors
    /**
     * The purpose of this method is to generate an empty Chord object of extending
     * type
     * <p>Precondition: A subclass, such as a ChordBuilder needs instantiation</p>
     * <p>Postcondition: A subclass is instantiated with no parameters and all
     *       data fields null</p>
     */
    public Chord() { }

    /**
     * The purpose of this constructor is for a subclass to build a chord object out of note
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
    /**
     * The purpose of this method is to gain access to the Note objects in
     * this Chord object
     * <p>
     * Precondition: the individual Note objects need to be accessed from an
     * extending subclass
     * </p>
     *
     */
    public Note[] getNotes(){return rawData;}

    /**
     * The purpose of this method is to gain access to the Interval objects
     * in this Chord object
     * <p>
     * Precondition: The intervals of an extending subclass need to be accessed
     * </p>
     * @return an ArrayList of Interval objects
     */
    public ArrayList<Interval> getIntervals(){return intervals;}

    /**
     * The purpose of this method is to gain access to the root of this Chord
     * object
     * <p>
     * Precondition: The Note object representing an extending Chord objects
     * root is needed. Every Chord object has a root. This is a fundamental
     * definition for this system and the basis for analysis of other Notes in
     * this Chord in relation to this root
     * </p>
     * @return a Note object representation of the extending Chord objects
     * root
     */
    public abstract Note getRoot(); // every chord has to have a root

    public String getQuality() {
        return quality;
    }

    /**
     * The purpose of this method is to set the quality of this Chord object
     * <p>
     * Precondition: A Chord object has been instantiated in an extending subclass, most likely
     * a ChordBuilder and the quality attribute of the subsequent classified
     * </p>
     * Chord object needs to be set
     * <p>Postcondition: The Chord objects quality attribute is set</p>
     * @param quality a String representation of the quality of this Chord.
     *
     *
     */

    public void setQuality(String quality) {this.quality = quality;};

    /**
     * The purpose of this method is to replace system placeholders for academic
     * symbols with unicode strings, primarily for pretty printing in the UI
     * <p>Precondition: </p>
     * <p>Postcondition: </p>
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

    /**
     * The purpose of this method is to provide a string representation of
     * this chord
     * <p>Precondition: A Chord object has been instantiated</p>
     * <p>Postcondition: A String representation of this object is presented
     * to the caller</p>
     *
     * @return a String representation of the chord in array format (i.e. [c, e, g])
     */
    @Override
    public String toString(){
        return String.format(Arrays.toString(rawData));
    }

    private class ChordInstanceCreator implements InstanceCreator<Chord>{
        public Chord createInstance(Type type){
            return new Chord() {
                @Override
                public Note getRoot() {
                    return null;
                }

                @Override
                public JsonElement serialize(Chord chord, Type type, JsonSerializationContext jsonSerializationContext) {
                    return null;
                }
            }; // end ChordInstanceCreator class
        }
    }
}
