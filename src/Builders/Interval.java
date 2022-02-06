package Builders;

import Utilities.CircularlyLinkedList;
import javafx.util.Pair;

import java.io.Serializable;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to serve as a fundamental data structure for
 * this system providing the ability to correctly classify Chord Objects
 */
public class Interval implements Serializable {

    private final int halfStepsInUnison = 0;
    private final int halfStepsInMinorSecond = 1;
    private final int halfStepsInMajorSecond = 2;
    private final int halfStepsInMinorThird = 3;
    private final int halfStepsInMajorThird = 4;
    private final int halfStepsInPerfectFourth = 5;
    private final int halfStepsInTritone = 6;
    private final int halfStepsInPerfectFifth = 7;
    private final int halfStepsInMinorSixth = 8;
    private final int halfStepsInMajorSixth = 9;
    private final int halfStepsInMinorSeventh = 10;
    private final int halfStepsInMajorSeventh = 11;
    private final int halfStepsInOctave = 12;
    // with exception of unison/octave all indices correspond with half steps in Interval quality
    private final String[] intervalQualities = new String[]{"unison/octave",
                                                            "minor second",
                                                            "major second",
                                                            "minor third",
                                                            "major third",
                                                            "perfect fourth",
                                                            "tritone",
                                                            "perfect fifth",
                                                            "minor sixth",
                                                            "major sixth",
                                                            "minor seventh",
                                                            "major seventh",
                                                            "octave"};
    // with exception of augmented fourth (last element) all indices correspond to half steps in Enharmonic Interval
    private final String[] enharmonicQualities = new String[]{  "diminished second",
                                                                "augmented unison",
                                                                "diminished third",
                                                                "augmented second",
                                                                "diminished fourth",
                                                                "augmented third",
                                                                "diminished fifth",
                                                                "diminished sixth",
                                                                "augmented fifth",
                                                                "diminished seventh",
                                                                "augmented sixth",
                                                                "augmented seventh",
                                                                "augmented fourth"};
    private final Pair<Note, Note> rawInterval; // a mapping of a bottom and top note
    // (0 = unison, 1 = minor second, 2 = major second, ... 11 = major seventh)

    private Integer intValue; // i.e. the number of half steps between two notes

    private int distance; // number of steps between letters in the musical alphabet (i.e. a to g is 7)
    private String quality; // (i.e. "major seventh", "minor third", "diminished fifth", "augmented fifth", "perfect fourth")
    private String enharmonic;


    // Constructor

    /**
     * The purpose of this method is to construct an interval object from
     * a javafx.util.Pair object of Notes, set the distance (i.e. any note
     * in the "a" family is a third lower than any note in the "c" family), set
     * the Intervals quality (i.e. "minor third"), set the rawInterval attribute
     * (i.e. simple a Pair that remembers the Note objects passed), and set
     * the intValue (see ReadMe "Examples of Interval Calculations" for more
     * information)
     * <p>Precondition: Two Note objects have been instantiated and these
     * are passed as a parameter to this constructor</p>
     * <p>Postcondition: The rawInterval, intValue, distance, and quality attributes
     * of this Interval object are instantiated </p>
     *
     * @param rawInterval a javafx.util.Pair object mapping two Note objects
     */
    public Interval(Pair<Note, Note> rawInterval){
        this.rawInterval = rawInterval;
        Note bottom = rawInterval.getKey();
        Note top = rawInterval.getValue();
        Integer bottomInt = bottom.getIntValue();
        Integer topInt = top.getIntValue();

        // See ReadMe "Examples of Interval Calculations"
        if (bottomInt < topInt){
            this.intValue = topInt - bottomInt;
        }
        else if(bottomInt == topInt){
            this.intValue = 0;
        }
        else{ // topInt > bottomInt
            this.intValue = (halfStepsInOctave - bottomInt) + topInt;
        }
        this.setDistance();
        this.setQuality();

        /* NEED TO DECIDE WHETHER SYSTEM COUNTS A UNISON/OCTAVE AS A LEGIT INTERVAL IN THIS SYSTEM
        if(this.getDistance() == 1 && this.getIntValue() < 2){
            throw new IllegalArgumentException("Error: " + bottom + " and " + top +
                    " cannot be made into an interval as they have the same letter name and " +
                    "are not larger than " + this.getIntValue() + " or a half step apart");
        }
         */
    }

    /**
     * The purpose of this method is to retrieve the top Note of this Interval
     * objects rawInterval attribute
     * <p>Precondition: This interval object has been instantiated and the
     * rawInterval attribute has been set</p>
     * <p>Postcondition: The appropriate Note object is returned</p>
     *
     * @return Note object, the higher voice of an interval
     */
    public Note getTopNote() {
        return rawInterval.getValue();
    }


    /**
     * The purpose of this method is to retrieve the bottom Note of this Interval
     * objects rawInterval attribute
     * <p>Precondition: This interval object has been instantiated and the
     * rawInterval attribute has been set</p>
     * <p>Postcondition: The appropriate Note object is returned</p>
     *
     * @return Note object, the lower voice of an interval
     */
    public Note getBottomNote() {
        return this.rawInterval.getKey();
    }

    /**
     * The purpose of this method is to retrieve the intValue of this Interval
     * object
     * <p>Precondition: This interval object has been instantiated and the
     * intValue has been set</p>
     * <p>Postcondition: The intValue attribute of this Interval object is
     * returned as an Integer object</p>
     *
     * @return an Integer object representing the intValue attribute on this
     * object
     */
    public Integer getIntValue() {
        return intValue;
    }

    /**
     * The purpose of this method is to get the enharmonic attribute associated
     * with this object
     * <p>Precondition: This Interval objects quality required its enharmonic
     * attribute to be instantiated. See the below setQuality() method</p>
     * <p>Postcondition: The enharmonic is returned as a String</p>
     *
     * @return a String representing the enharmonic interval (i.e. this Interval
     * has a quality that can be more clearly represented by its enharmonic,
     * an "augmented second" is better represented as "minor third")
     */
    public String getEnharmonic() {
        return enharmonic;
    }

    /**
     * The purpose of this method is to get this Interval objects quality attribute
     * <p>Precondition: This Interval object has been instantiated with the
     * quality attribute being set</p>
     * <p>Postcondition: The quality attribute is returned as a String</p>
     *
     * @return the String, quality attribute
     */
    public String getQuality() {
        return quality;
    }

    /**
     * The purpose of this method is to get the String array interval qualities
     * in order to set the appropriate quality
     * <p>Precondition: A Note object's intValue is set and used as a indexer
     * for this array, whose contents are organized such that the number of
     * halfsteps in a given quality corresponds to its index placement in the
     * array</p>
     * <p>Postcondition: the String array is returned</p>
     *
     * @return the String array is returned
     */
    public String[] getIntervalQualities(){
        return intervalQualities;
    }

    /**
     * The purpose of this method is to get the String array interval qualities
     * in order to set the appropriate enharmonic sounding interval
     * <p>Precondition: A Note object's intValue is set and used as a indexer
     * for this array, whose contents are organized such that the number of
     * halfsteps in a given enharmonic corresponds to its index placement in the
     * array</p>
     * <p>Postcondition: the String array is returned</p>
     *
     * @return the String array is returned
     */
    public String[] getEnharmonicQualities(){
        return enharmonicQualities;
    }

    /**
     * The purpose of this method is to access the constant attribute
     * representing half steps in a unison, 0
     *
     */
    public int getHalfStepsInUnison(){
        return halfStepsInUnison;
    }

    /**
     * The purpose of this method is to access the constant attribute
     * representing half steps in a minor second, 1
     *
     */
    public int getHalfStepsInMinorSecond(){
        return halfStepsInMinorSecond;
    }

    /**
     * The purpose of this method is to access the constant attribute
     * representing half steps in a major second, 2
     *
     */
    public int getHalfStepsInMajorSecond(){
        return halfStepsInMajorSecond;
    }

    /**
     * The purpose of this method is to access the constant attribute
     * representing half steps in a minor third, 3
     *
     */
    public int getHalfStepsInMinorThird(){
        return halfStepsInMinorThird;
    }

    /**
     * The purpose of this method is to access the constant attribute
     * representing half steps in a major third, 4
     *
     */
    public int getHalfStepsInMajorThird() {
        return halfStepsInMajorThird;
    }

    /**
     * The purpose of this method is to access the constant attribute
     * representing half steps in a perfect fourth, 5
     *
     */
    public int getHalfStepsInPerfectFourth() {return halfStepsInPerfectFourth;}

    /**
     * The purpose of this method is to access the constant attribute
     * representing half steps in a tritone, 6
     *
     */
    public int getHalfStepsInTritone() {
        return halfStepsInTritone;
    }

    /**
     * The purpose of this method is to access the constant attribute
     * representing half steps in a perfect fifth, 7
     *
     */
    public int getHalfStepsInPerfectFifth() {
        return halfStepsInPerfectFifth;
    }

    /**
     * The purpose of this method is to access the constant attribute
     * representing half steps in a minor sixth, 8
     *
     */
    public int getHalfStepsInMinorSixth() {
        return halfStepsInMinorSixth;
    }

    /**
     * The purpose of this method is to access the constant attribute
     * representing half steps in a major sixth, 9
     *
     */
    public int getHalfStepsInMajorSixth() {
        return halfStepsInMajorSixth;
    }

    /**
     * The purpose of this method is to access the constant attribute
     * representing half steps in a minor seventh, 10
     *
     */
    public int getHalfStepsInMinorSeventh() {
        return halfStepsInMinorSeventh;
    }

    /**
     * The purpose of this method is to access the constant attribute
     * representing half steps in a major seventh, 11
     *
     */
    public int getHalfStepsInMajorSeventh() {
        return halfStepsInMajorSeventh;
    }

    /**
     * The purpose of this method is to access the constant attribute
     * representing half steps in an octave, 12
     *
     */
    public int getHalfStepsInOctave() {
        return halfStepsInOctave;
    }


    /**
     * The purpose of this method is get the distance of two Note objects
     * based on their alphabetical "name". This will be an int in range
     * 1 to 7
     * <p>Precondition: two Note objects have been instantated</p>
     * <p>Postcondition: the int representation of the distance between
     * the two notes is returned</p>
     *
     * @return an int representation of how many letters apart in the musical
     * alphabet two notes are, this indicates the intervals quality family
     * (i.e. "c" bottom to "d#" is some flavor second (augmented), 2 is returned
     */
    public int getDistance(){return distance;}

    /**
     * The purpose of this method is to factor out the quality of the interval from
     * the distance attribute of the Interval and also sets enharmonic attribute where appropriate.
     * This method uses the class attribute String arrays intervalQualities and enharmonicIntervals
     * by indexing them according to the half steps in the interval
     * <p>Precondition: An Interval object is in the process of being instantiated/p>
     * <p>Postcondition: The Interval being instantiated is given the appropriate
     * quality and where applicable enharmonic attributes according to their
     * distance and how many half steps separate them</p>
     */
    public void setQuality() {

        switch (this.getDistance()){
            case 1: // unison/octave quality family: Interval of notes with the same "pre-fix" (e.g "c", "c-", "c#")
                if(this.getIntValue() == halfStepsInMinorSecond){ // e.g. "c" is bottom and "c#" is top
                    this.quality = enharmonicQualities[halfStepsInMinorSecond];
                    this.enharmonic = intervalQualities[halfStepsInMinorSecond];
                    break;
                }
                else if (this.getIntValue() == halfStepsInMajorSeventh){ // e.g. "c#" is bottom and "c" is top
                    this.quality = "octave displaced augmented unison"; // edge case
                    this.enharmonic = intervalQualities[halfStepsInMajorSeventh];
                    break;
                }
                /* ***case halfStepsInOctave*** will never be reached if Note.getIntValue() used to
                calculate interval -> possible with Note.getMidiInt();
                */
                else if(this.getIntValue() == halfStepsInUnison || this.getIntValue() == halfStepsInOctave){
                    this.quality = intervalQualities[halfStepsInUnison];
                    break;
                }

            case 2: // second quality family: notes one letter name apart (e.g "c" to "d" or "g" to "a")
                // a case like "b#" as bottom and "c" as top
                if(this.getIntValue() == halfStepsInUnison){
                    this.quality = enharmonicQualities[halfStepsInUnison];
                    this.enharmonic = intervalQualities[halfStepsInUnison];
                    break;
                }
                else if(this.getIntValue() == halfStepsInMinorSecond){ // half step
                    this.quality = intervalQualities[halfStepsInMinorSecond];
                    break;
                }
                else if(this.getIntValue() == halfStepsInMajorSecond){ // whole step
                    this.quality = intervalQualities[halfStepsInMajorSecond];
                    break;
                }
                else if(this.getIntValue() == halfStepsInMinorThird){ // a case like "c" as bottom and "d#" as top
                    this.quality = enharmonicQualities[halfStepsInMinorThird];
                    this.enharmonic = intervalQualities[halfStepsInMinorThird];
                    break;
                }
            case 3: // third quality family: notes two letter names apart (e.g. "c" as bottom "e" as top or "g" as bottom and "b-" as top)
                if(this.getIntValue() == halfStepsInMajorSecond){ // e.g. "c" as bottom and "e--" as top
                    this.quality = enharmonicQualities[halfStepsInMajorSecond];
                    this.enharmonic = intervalQualities[halfStepsInMajorSecond];
                    break;
                }
                else if(this.getIntValue() == halfStepsInMinorThird){
                    this.quality = intervalQualities[halfStepsInMinorThird];
                    break;
                }
                else if(this.getIntValue() == halfStepsInMajorThird){
                    this.quality = intervalQualities[halfStepsInMajorThird];
                    break;
                }
                else if(this.getIntValue() == halfStepsInPerfectFourth){ // e.g. "c" as bottom and "e#" as top
                    this.quality = enharmonicQualities[halfStepsInPerfectFourth];
                    this.enharmonic = intervalQualities[halfStepsInPerfectFourth];
                    break;
                }
            case 4: // fourth quality family: (e.g. "d" as bottom "g" as top or "f" as bottom and "c#" as top)
                if(this.getIntValue() == halfStepsInMajorThird){ // e.g. "c" as bottom and "f-" as top
                    this.quality = enharmonicQualities[halfStepsInMajorThird];
                    this.enharmonic = intervalQualities[halfStepsInMajorThird];
                    break;
                }
                else if (this.getIntValue() == halfStepsInPerfectFourth){
                    this.quality = intervalQualities[halfStepsInPerfectFourth];
                    break;
                }
                else if (this.getIntValue() == halfStepsInTritone){
                    // exceptional case: "augmented fourth" location at end of enharmonicQualities String[]
                    this.quality = enharmonicQualities[enharmonicQualities.length - 1];
                    this.enharmonic = intervalQualities[halfStepsInTritone];
                    break;
                }
            case 5: // fifth quality family: (e.g. "d" as bottom and "a#" as top)
                if(this.getIntValue() == halfStepsInTritone){
                    this.quality = enharmonicQualities[halfStepsInTritone]; // tritone spelled as fifth
                    this.enharmonic = intervalQualities[halfStepsInTritone];
                    break;
                }
                else if(this.getIntValue() == halfStepsInPerfectFifth){
                    this.quality = intervalQualities[halfStepsInPerfectFifth];
                    break;
                }
                else if (this.getIntValue() == halfStepsInMinorSixth){
                    this.quality = enharmonicQualities[halfStepsInMinorSixth];
                    this.enharmonic = intervalQualities[halfStepsInMinorSixth];
                    break;
                }
            case 6: // sixth quality family (e.g. "c" bottom to "a-" top)
                if(this.getIntValue() == halfStepsInPerfectFifth){
                    this.quality = enharmonicQualities[halfStepsInPerfectFifth];
                    this.enharmonic = intervalQualities[halfStepsInPerfectFifth];
                    break;
                }
                else if(this.getIntValue() == halfStepsInMinorSixth){
                    this.quality = intervalQualities[halfStepsInMinorSixth];
                    break;
                }
                else if(this.getIntValue() == halfStepsInMajorSixth){
                    this.quality = intervalQualities[halfStepsInMajorSixth];
                    break;
                }
                else if(this.getIntValue() == halfStepsInMinorSeventh){
                    this.quality = enharmonicQualities[halfStepsInMinorSeventh];
                    this.enharmonic = intervalQualities[halfStepsInMinorSeventh];
                    break;
                }
            case 7: // seventh quality family (e.g. "c" bottom to "b-" top)
                if (this.getIntValue() == halfStepsInMajorSixth){
                    this.quality = enharmonicQualities[halfStepsInMajorSixth];
                    this.enharmonic = intervalQualities[halfStepsInMajorSixth];
                    break;
                }
                else if(this.getIntValue() == halfStepsInMinorSeventh){
                    this.quality = intervalQualities[halfStepsInMinorSeventh];
                    break;
                }
                else if(this.getIntValue() == halfStepsInMajorSeventh){
                    this.quality = intervalQualities[halfStepsInMajorSeventh];
                    break;
                }
                /* ***case halfStepsInOctave*** will never be reached if Note.getIntValue() used to
                calculate interval -> possible with Note.getMidiInt();
                */
                else if(this.getIntValue() == halfStepsInOctave){ // e.g. "c" to bottom "b#" top
                    this.quality = enharmonicQualities[halfStepsInOctave];
                    // edge case: only handling intervals < octave
                    this.enharmonic = intervalQualities[halfStepsInOctave];
                    break;
                }
        }

    }


    /**
     * The purpose of this method is to set the distance attribute of this Interval
     * object. We utilize the Utilities.CircularlyLinkedList to accomplish this.
     * <p>Precondition: this Interval object is being instantiated </p>
     * <p>Postcondition: this Interval objects distance attribute is set</p>
     */
    public void setDistance(){
        // Use Circularly Linked List to get distance
        CircularlyLinkedList cll = new CircularlyLinkedList();
        //Adds all possible note prefixes to the list
        cll.add("a");
        cll.add("b");
        cll.add("c");
        cll.add("d");
        cll.add("e");
        cll.add("f");
        cll.add("g");

        char n = this.getBottomNote().getName().charAt(0);
        char n1 = this.getTopNote().getName().charAt(0);

        try {
            int distance = cll.distance(Character.toString(n), Character.toString(n1)); // need to convert the chars to Strings
            this.distance = distance;
        }catch(Exception e){
            System.out.println(e);
        }
    }

    /**
     * The purpose of this method is to provide a String representation of this
     * Interval object
     *
     * @return a human readable String representation of this Interval object
     */
    public String toString(){
        return this.getQuality();
    }

    public static void main(String[] args) {
        try {
            Note note = new Note("C#");
            System.out.println("name: " + note.getName() + " Enharmonics: " + note.getEnharmonics()
                    + "\n" + "intValue: " + note.getIntValue());
            Note note1 = new Note("G--");
            System.out.println("name: " + note1.getName() + " Enharmonics: " + note1.getEnharmonics()
                    + "\n" + "intValue: " + note1.getIntValue());

            Interval interval = new Interval(new Pair<>(note, note1));

            System.out.println("Testing getBottomNote(): " + interval.getBottomNote());
            System.out.println("Testing getTopNote(): " + interval.getTopNote());
            System.out.println("Testing interval intValue: " + interval.intValue);

            System.out.println(interval);

            Note note2 = new Note("c#");
            System.out.println("name: " + note2.getName() + " Enharmonics: " + note2.getEnharmonics()
                    + "\n" + "intValue: " + note2.getIntValue());
            Note note3 = new Note("c");
            System.out.println("name: " + note3.getName() + " Enharmonics: " + note3.getEnharmonics()
                    + "\n" + "intValue: " + note3.getIntValue());

            Interval interval1 = new Interval(new Pair<>(note2, note3));

            System.out.println("Testing getBottomNote(): " + interval1.getBottomNote());
            System.out.println("Testing getTopNote(): " + interval1.getTopNote());
            System.out.println("Testing interval intValue: " + interval1.intValue);

            System.out.println(interval1);

            Note note4 = new Note("b#");
            System.out.println("name: " + note4.getName() + " Enharmonics: " + note4.getEnharmonics()
                    + "\n" + "intValue: " + note4.getIntValue());
            Note note5 = new Note("c");
            System.out.println("name: " + note3.getName() + " Enharmonics: " + note3.getEnharmonics()
                    + "\n" + "intValue: " + note3.getIntValue());

            Interval interval2 = new Interval(new Pair<>(note4, note5));

            System.out.println("Testing getBottomNote(): " + interval2.getBottomNote());
            System.out.println("Testing getTopNote(): " + interval2.getTopNote());
            System.out.println("Testing interval intValue: " + interval2.intValue);

            System.out.println(interval2);

            System.out.println(interval2.getBottomNote().getEnharmonics().contains(interval2.getTopNote().getName()));

            System.out.println(interval1.getBottomNote().getName().charAt(0) == interval1.getTopNote().getName().charAt(0));

            Note note6 = new Note("c");
            System.out.println("name: " + note6.getName() + " Enharmonics: " + note6.getEnharmonics()
                    + "\n" + "intValue: " + note6.getIntValue());
            Note note7 = new Note("d#");
            System.out.println("name: " + note7.getName() + " Enharmonics: " + note7.getEnharmonics()
                    + "\n" + "intValue: " + note7.getIntValue());

            Interval interval3 = new Interval(new Pair<>(note6, note7));

            System.out.println("Testing getBottomNote(): " + interval3.getBottomNote());
            System.out.println("Testing getTopNote(): " + interval3.getTopNote());
            System.out.println("Testing interval intValue: " + interval3.intValue);

            System.out.println(interval3);
        }catch (InvalidNoteException e){
            System.out.println(e);
        }

    }
}
