package Builders;

import AbstractStructures.Chord;
import ThreeNoteStructures.InvalidTriadException;
import Classifiers.TriadClassifier;

import java.util.ArrayList;


/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to determine which classifier class to call for
 * this ChordBuilder object to be properly classified based on the number of
 * distinct notes
 */

public class ChordBuilder extends Chord {

    private String[] inversions = new String[]{ "root position",
                                                "first inversion",
                                                "second inversion",
                                                "third inversion"};


    // four note structures
    private String[] seventhQualities = new String[]{   "diminished 7",
                                                        "minor 7 b5",
                                                        "minor 7",
                                                        "dominant 7",
                                                        "major 7"};
    private String quality;
    // constructor
    public ChordBuilder(Note[] data) {
        super(data);
    }

    /**
     * The purpose of this class is to override the inherited abstract method
     * such that a ChordBuilder may be a concrete class which it necessarily
     * must be.
     * @return null
     */
    @Override
    public Note getRoot() {
        return null;  // this class is building the chord, the chord still has to identity
    }

    /**
     * The purpose of this method is to call the appropriate classifier class
     * from the Classifiers package in order for the ChordBuilder object parameter
     * to be appropriately classified and a Chord object of the classified
     * quality can then be instantiated and returned
     * <p>Precondition: A Chord builder object needs classification</p>
     * <p>Postcondition: A Chord object of type according to appropriate
     * classification is returned</p>
     *
     * @param chordBuilder A ChordBuilder object to be classified
     * @return classified chord object
     */
    public Chord classify(ChordBuilder chordBuilder){
        Chord classified = chordBuilder; // container to return classified chord
        ArrayList<Interval> intervals = chordBuilder.getIntervals();

        if(intervals.size() == 2){ // we've found three note chord, let's try to classify it as a triad
            Interval bottom_mid = intervals.get(0);
            Interval bottom_top = intervals.get(1);
            try {
                TriadClassifier triadClassifier = new TriadClassifier();
                classified = triadClassifier.classifyTriad(chordBuilder, bottom_mid, bottom_top);
            } catch (InvalidTriadException e){
                System.out.println(e);
                /*
                TODO define another classifier threeNoteNonTriad and threeNoteCluster and call
                 */
            }
        }
        return classified;
    }


    /**
     * The purpose of this method is to get the intervals in this unclassified
     * chord object to appropriately classify this object
     * <p>Precondition: A ChordBuilder object has been instantiated in
     * accordance with the overloaded Chord constructor that takes a Note[]
     * as a parameter and sets the intervals attribute accordingly</p>
     * <p>Postcondition: An Array List of Interval objects is returned
     * for this ChordBuilder for analysis and classification by the
     * appropriate classifier class</p>
     *
     * @return an ArrayList of Interval objects
     */
    public ArrayList<Interval> getIntervals(){return super.getIntervals();}

    /**
     * The purpose of this method is a getter which returns the String Array
     * attribute inversions
     * <p>Precondition: A ChordBuilder object quality has been determined by
     * the appropriate classifier class and the inversion (i.e. ordering
     * of the notes determined by the lowest sounding note in the chord) needs
     * to be set. (example: [c, e, g] and [e, g, c] are both C major triads
     * but different inversions</p>
     * <p>Postcondition: The String Array is returned </p>
     *
     * @return a String Array of Inversions
     */
    public String[] getInversions(){
        return inversions;
    }

    /**
     * The purpose of this method is override the Chord class' method such
     * that this ChordBuilder can be instantiated as a concrete class
     */
    public void setQuality(String quality){
        quality = null;
        this.quality = quality;
    }

    /**
     * The purpose of this method is a getter for this ChordBuilder's quality
     * attribute. If calling this method on a Chord[] polymophically the
     * null return value will either indicate that Chord object is a ChordBuilder
     * or an object that has not been fully classified upon instantiation
     */
    public String getQuality(){
        return quality; // return null
    }

    /**
     * The purpose of this method is to provide a String representation of this
     * ChordBuilder object
     * <p>Precondition: A ChordBuilder object has been instantiated</p>
     * <p>Postcondition: The String representation of this object is returned</p>
     *
     * @return a String representation of this object
     */
    @Override
    public String toString(){
        return String.format(super.toString());
    }
}
