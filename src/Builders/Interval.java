package Builders;

import Utilities.CircularlyLinkedList;
import javafx.util.Pair;

public class Interval {

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
    public Interval(Pair<Note, Note> rawInterval) throws IllegalArgumentException{
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
     * @return Note, the higher voice of an interval
     */
    public Note getTopNote() {
        return rawInterval.getValue();
    }

    /**
     * @return and Integer the lower voice of an interval
     */
    public Note getBottomNote() {
        return this.rawInterval.getKey();
    }

    public Integer getIntValue() {
        return intValue;
    }

    public String getEnharmonic() {
        return enharmonic;
    }

    public String getQuality() {
        return quality;
    }

    public String[] getIntervalQualities(){
        return intervalQualities;
    }

    public String[] getEnharmonicQualities(){
        return enharmonicQualities;
    }

    public int getHalfStepsInUnison(){
        return halfStepsInUnison;
    }

    public int getHalfStepsInMinorSecond(){
        return halfStepsInMinorSecond;
    }

    public int getHalfStepsInMajorSecond(){
        return halfStepsInMajorSecond;
    }
    public int getHalfStepsInMinorThird(){
        return halfStepsInMinorThird;
    }

    public int getHalfStepsInMajorThird() {
        return halfStepsInMajorThird;
    }

    public int getHalfStepsInPerfectFourth() {
        return halfStepsInPerfectFourth;
    }

    public int getHalfStepsInTritone() {
        return halfStepsInTritone;
    }

    public int getHalfStepsInPerfectFifth() {
        return halfStepsInPerfectFifth;
    }

    public int getHalfStepsInMinorSixth() {
        return halfStepsInMinorSixth;
    }

    public int getHalfStepsInMajorSixth() {
        return halfStepsInMajorSixth;
    }

    public int getHalfStepsInMinorSeventh() {
        return halfStepsInMinorSeventh;
    }

    public int getHalfStepsInMajorSeventh() {
        return halfStepsInMajorSeventh;
    }

    public int getHalfStepsInOctave() {
        return halfStepsInOctave;
    }

    /**
     *
     * @return an int representation of how many letters apart in the musical
     * alphabet two notes are, this indicates the intervals quality family
     * (i.e. "c" bottom to "d#" is some flavor second (augmented), 2 is returned
     */
    public int getDistance(){return distance;}

    /**
     * Importantly, this method factors out the quality of the interval from
     * the distance of the interval and also sets enharmonic interval where appropriate.
     *
     * >>>Uses the class attribute String arrays intervalQualities and enharmonicIntervals
     * by indexing them according to the half steps in the interval
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



    public String toString(){
        return this.getQuality();
    }

    public static void main(String[] args) {
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

    }
}
