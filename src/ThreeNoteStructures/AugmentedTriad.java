package ThreeNoteStructures;


import AbstractStructures.Chord;
import Builders.ChordBuilder;
import AbstractStructures.Triad;
import Builders.Note;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to provide a data structure for Chord objects
 * that have the Interval's signature of an Augmented triad
 */
public class AugmentedTriad extends Chord implements Triad {

    private Note root;
    private Note third;
    private Note fifth;
    private String quality;
    private String inversion;

    /**
     * The purpose of this method is to instantiate an Augmented triad from
     * a ChordBuilder object with the appropriate Interval's signature as
     * determined by the Classifiers.TriadClassifier class
     * <p>Precondition: A three note ChordBuilder object has the
     * interval signature of an augmented triad</p>
     * <p>Postcondition: an Augmented Triad is instantiated</p>
     *
     * @param data is a ChordBuilder object with three Notes and two Intervals
     */
    public AugmentedTriad(ChordBuilder data) {
        super(data.getNotes());
        Note[] notes = data.getNotes();

    }

    /**
     * The purpose of this method is to get the Note root for this Triad
     * @return the Note object root
     */
    public Note getRoot() {return this.root;}

    /**
     * The purpose of this method is to get the Note third for this Triad
     * @return the Note object third
     */
    public Note getThird() {return this.third;}

    /**
     * The purpose of this method is to get the Note fifth for this Triad
     * @return the Note object fifth
     */
    public Note getFifth() {return this.fifth;}

    /**
     * The purpose of this method is to get the quality attribute for this
     * Triad
     * @return a String representation of the quality of this triad
     */
    public String getQuality() {return quality;}

    /**
     * The purpose of this method is to get the inversion attribute for this
     * Triad
     * @return a String representation of the inversion of this triad
     */
    public String getInversion() {return inversion;}

    /**
     * The purpose of this method is to set a Note object as the root of this
     * triad in the Classifiers.TriadClassifier class
     * @param root a Note object
     */
    public void setRoot(Note root) {this.root = root;}

    /**
     * The purpose of this method is to set a Note object as the third of this
     * triad in the Classifiers.TriadClassifier class
     * @param third a Note object
     */
    public void setThird(Note third) {this.third = third;}

    /**
     * The purpose of this method is to set a Note object as the fifth of this
     * triad in the Classifiers.TriadClassifier class
     * @param fifth a Note object
     */
    public void setFifth(Note fifth) {this.fifth = fifth;}

    /**
     * The purpose of this method is to set a Note object as the root of this
     * triad in the Classifiers.TriadClassifier class
     * @param  quality is a String
     */
    public void setQuality(String quality){this.quality = quality;}

    /**
     * The purpose of this method is to set a Note object as the root of this
     * triad in the Classifiers.TriadClassifier class
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
        StringBuilder root = replaceAccidental(rt);
        StringBuilder third = replaceAccidental(thrd);
        StringBuilder fifth = replaceAccidental(fif);


        return String.format(root.toString().toUpperCase() + " " +
                getQuality().toUpperCase() + " " + getInversion().toUpperCase() + " " +
                super.toString() + "\n" +
                "root: " + root + "\n" +
                "third: " + third + "\n" +
                "fifth: " + fifth);
    }
}
