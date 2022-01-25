package FourNoteStructures;

import AbstractStructures.Chord;
import Builders.ChordBuilder;
import AbstractStructures.SeventhChord;
import AbstractStructures.Triad;
import Builders.Note;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to provide a data structure for Chord objects
 * that have the Interval's signature of a Dominant Seventh Chord
 */
public class DominantSeventhChord extends Chord implements Triad, SeventhChord {

    private Note root;
    private Note third;
    private Note fifth;
    private Note seventh;
    private String quality;
    private String inversion;
    // group the base triad
    private Note[] triad = new Note[]{root, third, fifth};

    /**
     * The purpose of this method is to instantiate a DominantSeventhChord from
     * a ChordBuilder object with the appropriate Interval's signature as
     * determined by the TODO Classifiers.SeventhChordClassifier class
     * <p>Precondition: A three note ChordBuilder object has the
     * interval signature of a Dominant Seventh Chord</p>
     * <p>Postcondition: a Dominant Seventh Chord is instantiated</p>
     *
     * @param data is a ChordBuilder object with four Notes and three Intervals
     */
    public DominantSeventhChord(ChordBuilder data) {
        super(data.getNotes());
        Note[] notes = data.getNotes();
    }

    /**
     * The purpose of this method is to get the Note root for this Seventh Chord
     * @return the Note object root
     */
    public Note getRoot() {return this.root;}

    /**
     * The purpose of this method is to get the Note third for this Seventh Chord
     * @return the Note object third
     */
    public Note getThird() {return this.third;}

    /**
     * The purpose of this method is to get the Note fifth for this Seventh Chord
     * @return the Note object fifth
     */
    public Note getFifth() {return this.fifth;}

    /**
     * The purpose of this method is to get the Note seventh for this Seventh Chord
     * @return the Note object seventh
     */
    public Note getSeventh() {return this.seventh;}

    /**
     * The purpose of this method is to get the quality attribute for this
     * Seventh Chord
     * @return a String representation of the quality of this Seventh Chord
     */
    public String getQuality() { return quality;}

    /**
     * The purpose of this method is to get the inversion attribute for this
     * Seventh Chord
     * @return a String representation of the inversion of this Seventh Chord
     */
    public String getInversion() {return inversion;}

    /**
     * The purpose of this method is to set a Note object as the root of this
     * Seventh Chord in the TODO Classifiers.SeventhChordClassifier class
     * @param root a Note object
     */
    public void setRoot(Note root) {this.root = root;}

    /**
     * The purpose of this method is to set a Note object as the third of this
     * Seventh Chord in the TODO Classifiers.SeventhChordClassifier class
     * @param third a Note object
     */
    public void setThird(Note third) {this.third = third;}

    /**
     * The purpose of this method is to set a Note object as the fifth of this
     * Seventh Chord in the TODO Classifiers.SeventhChordClassifier class
     * @param fifth a Note object
     */
    public void setFifth(Note fifth) {this.fifth = fifth;}

    /**
     * The purpose of this method is to set a Note object as the seventh of this
     * Seventh Chord in the TODO Classifiers.SeventhChordClassifier class
     * @param seventh a Note object
     */
    public void setSeventh(Note seventh){this.seventh = seventh;}

    /**
     * The purpose of this method is to set a Note object as the root of this
     * triad in the TODO Classifiers.SeventhChordClassifier class
     * @param  quality is a String
     */
    public void setQuality(String quality){this.quality = quality;}

    /**
     * The purpose of this method is to set a Note object as the root of this
     * triad in the TODO Classifiers.SeventhChordClassifier class
     * @param  inversion is a String
     */
    public void setInversion(String inversion){this.inversion = inversion;}


    /**
     * The purpose of this method is to return a human-readable String
     * representation of this object. Note that if accidentals are present
     * these are replaced with their unicode strings for a prettier print
     * @return a human-readable String representation of this object
     */
    @Override
    public String toString(){

        Note rt = getRoot();
        Note thrd = getThird();
        Note fif = getFifth();
        Note sev = getSeventh();
        StringBuilder root = replaceAccidental(rt);
        StringBuilder third = replaceAccidental(thrd);
        StringBuilder fifth = replaceAccidental(fif);
        StringBuilder seventh = replaceAccidental(sev);


        return String.format(root.toString().toUpperCase() + " " +
                getQuality().toUpperCase() + " " + getInversion().toUpperCase() + " " +
                super.toString() + "\n" +
                "root: " + root + "\n" +
                "third: " + third + "\n" +
                "fifth: " + fifth + "\n" +
                "seventh: " + seventh);
    }
}
