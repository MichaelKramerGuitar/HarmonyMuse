package Builders;

import javafx.util.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is test proper instantiation of each Interval type
 * along with enharmonics as needed
 * as this functionality is imperitive for correctly classifying chords
 */
class IntervalTest {
    // copies from Interval class --- these are constants
    private int halfStepsInUnison;
    private int halfStepsInMinorSecond;
    private int halfStepsInMajorSecond;
    private int halfStepsInMinorThird;
    private int halfStepsInMajorThird;
    private int halfStepsInPerfectFourth;
    private int halfStepsInTritone;
    private int halfStepsInPerfectFifth;
    private int halfStepsInMinorSixth;
    private int halfStepsInMajorSixth;
    private int halfStepsInMinorSeventh;
    private int halfStepsInMajorSeventh;
    private int halfStepsInOctave;


    String[] intervalQualities;
    String[] enharmonicIntervals;

    // intervalQualities intervals

    Interval unison;
    Interval minorSecond;
    Interval majorSecond;
    Interval minorThird;
    Interval majorThird;
    Interval perfectFourth;
    Interval perfectFifth;
    Interval minorSixth;
    Interval majorSixth;
    Interval minorSeventh;
    Interval majorSeventh;

    // enharmonicIntervals intervals
    Interval diminishedSecond;
    Interval augmentedUnison;
    Interval diminishedThird;
    Interval augmentedSecond;
    Interval diminishedFourth;
    Interval augmentedThird;
    Interval diminishedFifth;
    Interval diminishedSixth;
    Interval augmentedFifth;
    Interval diminishedSeventh;
    Interval augmentedSixth;
    Interval augmentedSeventh;
    Interval augmentedFourth;


    @BeforeEach
    void setUp() {
        try {
            // Perfect 5th to start things off
            Note c = new Note("C");
            Note g = new Note("G");
            this.perfectFifth = new Interval(new Pair<>(c, g)); // construct interval perfect fifth
            // set class String[] attributes from an arbitrary interval instance for testing
            this.halfStepsInUnison = perfectFifth.getHalfStepsInUnison();
            this.halfStepsInMinorSecond = perfectFifth.getHalfStepsInMinorSecond();
            this.halfStepsInMajorSecond = perfectFifth.getHalfStepsInMajorSecond();
            this.halfStepsInMinorThird = perfectFifth.getHalfStepsInMinorThird();
            this.halfStepsInMajorThird = perfectFifth.getHalfStepsInMajorThird();
            this.halfStepsInPerfectFourth = perfectFifth.getHalfStepsInPerfectFourth();
            this.halfStepsInTritone = perfectFifth.getHalfStepsInTritone();
            this.halfStepsInPerfectFifth = perfectFifth.getHalfStepsInPerfectFifth();
            this.halfStepsInMinorSixth = perfectFifth.getHalfStepsInMinorSixth();
            this.halfStepsInMajorSixth = perfectFifth.getHalfStepsInMajorSixth();
            this.halfStepsInMinorSeventh = perfectFifth.getHalfStepsInMinorSeventh();
            this.halfStepsInMajorSeventh = perfectFifth.getHalfStepsInMajorSeventh();
            this.halfStepsInOctave = perfectFifth.getHalfStepsInOctave();

            this.intervalQualities = perfectFifth.getIntervalQualities();
            this.enharmonicIntervals = perfectFifth.getEnharmonicQualities();


            // Unison
            Note dSharp1 = new Note("d#");
            Note dSharp2 = new Note("D#");
            this.unison = new Interval(new Pair<>(dSharp1, dSharp2)); // construct interval1

            // Minor Second
            Note d = new Note("D");
            Note eFlat = new Note("e-");
            this.minorSecond = new Interval(new Pair<>(d, eFlat));

            // Major Second
            this.majorSecond = new Interval(new Pair<>(c, d));

            // Minor Third
            Note cSharp = new Note("c#");
            Note e = new Note("e");
            this.minorThird = new Interval(new Pair<>(cSharp, e));

            // Major Third
            Note dFlat = new Note("d-");
            Note f = new Note("f");
            this.majorThird = new Interval(new Pair<>(dFlat, f));

            // Perfect Fourth
            this.perfectFourth = new Interval(new Pair<>(d, g));

            // Tritone, DiminishedFifth
            this.diminishedFifth = new Interval(new Pair<>(cSharp, g));

            // Augmented Fourth, Tritone
            Note gSharp = new Note("g#");
            this.augmentedFourth = new Interval(new Pair<>(d, gSharp));

            // Minor Sixth
            Note b = new Note("b");
            this.minorSixth = new Interval(new Pair<>(b, g));

            // Major Sixth
            Note bFlat = new Note("b-");
            this.majorSixth = new Interval(new Pair<>(dFlat, bFlat));

            // Minor Seventh
            this.minorSeventh = new Interval(new Pair<>(cSharp, b));

            // Major Seventh
            this.majorSeventh = new Interval(new Pair<>(eFlat, d));

            //////////////////Begin Enharmonic Instantiations//////////////////////

            // Diminished Second
            Note cFlat = new Note("c-");
            this.diminishedSecond = new Interval(new Pair<>(b, cFlat));

            // Augmented Unison
            this.augmentedUnison = new Interval(new Pair<>(d, dSharp1));

            // Diminished Third
            this.diminishedThird = new Interval(new Pair<>(cSharp, eFlat));

            // Augmented Second
            this.augmentedSecond = new Interval(new Pair<>(dFlat, e));

            // Diminished Fourth
            Note aFlat = new Note("a-");
            this.diminishedFourth = new Interval(new Pair<>(e, aFlat));

            // Augmented Third
            Note fSharp = new Note("f#");
            this.augmentedThird = new Interval(new Pair<>(dFlat, fSharp));


            // Diminished Sixth
            this.diminishedSixth = new Interval(new Pair<>(cSharp, aFlat));

            // Augmented Fifth
            this.augmentedFifth = new Interval(new Pair<>(eFlat, b));

            // Diminished Seventh
            this.diminishedSeventh = new Interval(new Pair<>(d, cFlat));

            //Augmented Sixth
            this.augmentedSixth = new Interval(new Pair<>(eFlat, cSharp));
        }catch (InvalidNoteException e){
            System.out.println(e);
        }
    }

    /**
     * The purpose of this method is to run a repeated test on the
     * interval named descriptively - enharmonics are tested where applicable
     * <p>Precondition: an Interval has been instantiated</p>
     * <p>Postcondition: The interval has the proper top and bottom notes,
     * quality, intValue and enharmonic where applicable</p>
     */
    @Test
    void UnisonTest(){
        /*
        Unison and Octave are bundled qualities with the Interval compression
        function
         */
        Assertions.assertEquals("d#", unison.getBottomNote().toString());
        Assertions.assertEquals("d#", unison.getTopNote().toString());
        Assertions.assertEquals(intervalQualities[halfStepsInUnison], unison.getQuality());
        Assertions.assertEquals(0, unison.getIntValue());
    }

    /**
     * The purpose of this method is to run a repeated test on the
     * interval named descriptively - enharmonics are tested where applicable
     * <p>Precondition: an Interval has been instantiated</p>
     * <p>Postcondition: The interval has the proper top and bottom notes,
     * quality, intValue and enharmonic where applicable</p>
     */
    @Test
    void MinorSecondTest(){
        // Minor Second
        Assertions.assertEquals("d", minorSecond.getBottomNote().toString());
        Assertions.assertEquals("e-", minorSecond.getTopNote().toString());
        Assertions.assertEquals(intervalQualities[halfStepsInMinorSecond], minorSecond.getQuality());
        Assertions.assertEquals(1, minorSecond.getIntValue());
    }

    /**
     * The purpose of this method is to run a repeated test on the
     * interval named descriptively - enharmonics are tested where applicable
     * <p>Precondition: an Interval has been instantiated</p>
     * <p>Postcondition: The interval has the proper top and bottom notes,
     * quality, intValue and enharmonic where applicable</p>
     */
    @Test
    void MajorSecondTest(){
        // Minor Second
        Assertions.assertEquals("c", majorSecond.getBottomNote().toString());
        Assertions.assertEquals("d", majorSecond.getTopNote().toString());
        Assertions.assertEquals(intervalQualities[halfStepsInMajorSecond], majorSecond.getQuality());
        Assertions.assertEquals(2, majorSecond.getIntValue());
    }

    /**
     * The purpose of this method is to run a repeated test on the
     * interval named descriptively - enharmonics are tested where applicable
     * <p>Precondition: an Interval has been instantiated</p>
     * <p>Postcondition: The interval has the proper top and bottom notes,
     * quality, intValue and enharmonic where applicable</p>
     */
    @Test
    void MinorThirdTest(){
        // Minor Third
        Assertions.assertEquals("c#", minorThird.getBottomNote().toString());
        Assertions.assertEquals("e", minorThird.getTopNote().toString());
        Assertions.assertEquals(intervalQualities[halfStepsInMinorThird], minorThird.getQuality());
        Assertions.assertEquals(3, minorThird.getIntValue());
    }

    /**
     * The purpose of this method is to run a repeated test on the
     * interval named descriptively - enharmonics are tested where applicable
     * <p>Precondition: an Interval has been instantiated</p>
     * <p>Postcondition: The interval has the proper top and bottom notes,
     * quality, intValue and enharmonic where applicable</p>
     */
    @Test
    void MajorThirdTest(){
        // Major Third
        Assertions.assertEquals("d-", majorThird.getBottomNote().toString());
        Assertions.assertEquals("f", majorThird.getTopNote().toString());
        Assertions.assertEquals(intervalQualities[halfStepsInMajorThird], majorThird.getQuality());
        Assertions.assertEquals(4, majorThird.getIntValue());
    }

    /**
     * The purpose of this method is to run a repeated test on the
     * interval named descriptively - enharmonics are tested where applicable
     * <p>Precondition: an Interval has been instantiated</p>
     * <p>Postcondition: The interval has the proper top and bottom notes,
     * quality, intValue and enharmonic where applicable</p>
     */
    @Test
    void PerfectFourth(){
        // Perfect Fourth
        Assertions.assertEquals("d", perfectFourth.getBottomNote().toString());
        Assertions.assertEquals("g", perfectFourth.getTopNote().toString());
        Assertions.assertEquals(intervalQualities[halfStepsInPerfectFourth], perfectFourth.getQuality());
        Assertions.assertEquals(5, perfectFourth.getIntValue());
    }

    /**
     * The purpose of this method is to run a repeated test on the
     * interval named descriptively - enharmonics are tested where applicable
     * <p>Precondition: an Interval has been instantiated</p>
     * <p>Postcondition: The interval has the proper top and bottom notes,
     * quality, intValue and enharmonic where applicable</p>
     */
    @Test
    void TritoneTest(){
        // Augmented Fourth
        Assertions.assertEquals("d", augmentedFourth.getBottomNote().toString());
        Assertions.assertEquals("g#", augmentedFourth.getTopNote().toString());
        // exceptional case
        Assertions.assertEquals(enharmonicIntervals[enharmonicIntervals.length - 1], augmentedFourth.getQuality());
        Assertions.assertEquals(6, augmentedFourth.getIntValue());

        // Diminished Fifth
        Assertions.assertEquals("c#", diminishedFifth.getBottomNote().toString());
        Assertions.assertEquals("g", diminishedFifth.getTopNote().toString());
        Assertions.assertEquals(enharmonicIntervals[halfStepsInTritone], diminishedFifth.getQuality());
        Assertions.assertEquals(6, diminishedFifth.getIntValue());
    }

    /**
     * The purpose of this method is to run a repeated test on the
     * interval named descriptively - enharmonics are tested where applicable
     * <p>Precondition: an Interval has been instantiated</p>
     * <p>Postcondition: The interval has the proper top and bottom notes,
     * quality, intValue and enharmonic where applicable</p>
     */
    @Test
    void PerfectFifthTest(){
        // Perfect Fifth
        Assertions.assertEquals("c", perfectFifth.getBottomNote().toString());
        Assertions.assertEquals("g", perfectFifth.getTopNote().toString());
        Assertions.assertEquals(intervalQualities[halfStepsInPerfectFifth], perfectFifth.getQuality());
        Assertions.assertEquals(7, perfectFifth.getIntValue());

        try {
            // Perfect Fifth
            Note aFlat = new Note("a-");
            Note eFlat = new Note("e-");
            Interval newP5 = new Interval(new Pair<>(aFlat, eFlat));
            Assertions.assertEquals("a-", newP5.getBottomNote().toString());
            Assertions.assertEquals("e-", newP5.getTopNote().toString());
            Assertions.assertEquals(intervalQualities[halfStepsInPerfectFifth], newP5.getQuality());
            Assertions.assertEquals(7, newP5.getIntValue());
        }catch (InvalidNoteException e){
            System.out.println();
        }
    }

    /**
     * The purpose of this method is to run a repeated test on the
     * interval named descriptively - enharmonics are tested where applicable
     * <p>Precondition: an Interval has been instantiated</p>
     * <p>Postcondition: The interval has the proper top and bottom notes,
     * quality, intValue and enharmonic where applicable</p>
     */
    @Test
    void MinorSixthTest(){
        // Minor Sixth
        Assertions.assertEquals("b", minorSixth.getBottomNote().toString());
        Assertions.assertEquals("g", minorSixth.getTopNote().toString());
        Assertions.assertEquals(intervalQualities[halfStepsInMinorSixth], minorSixth.getQuality());
        Assertions.assertEquals(8, minorSixth.getIntValue());
    }

    /**
     * The purpose of this method is to run a repeated test on the
     * interval named descriptively - enharmonics are tested where applicable
     * <p>Precondition: an Interval has been instantiated</p>
     * <p>Postcondition: The interval has the proper top and bottom notes,
     * quality, intValue and enharmonic where applicable</p>
     */
    @Test
    void MajorSixthTest(){
        // Major Sixth
        Assertions.assertEquals("d-", majorSixth.getBottomNote().toString());
        Assertions.assertEquals("b-", majorSixth.getTopNote().toString());
        Assertions.assertEquals(intervalQualities[halfStepsInMajorSixth], majorSixth.getQuality());
        Assertions.assertEquals(9, majorSixth.getIntValue());
    }

    /**
     * The purpose of this method is to run a repeated test on the
     * interval named descriptively - enharmonics are tested where applicable
     * <p>Precondition: an Interval has been instantiated</p>
     * <p>Postcondition: The interval has the proper top and bottom notes,
     * quality, intValue and enharmonic where applicable</p>
     */
    @Test
    void MinorSeventhTest(){
        // Minor Seventh
        Assertions.assertEquals("c#", minorSeventh.getBottomNote().toString());
        Assertions.assertEquals("b", minorSeventh.getTopNote().toString());
        Assertions.assertEquals(intervalQualities[halfStepsInMinorSeventh], minorSeventh.getQuality());
        Assertions.assertEquals(10, minorSeventh.getIntValue());
    }

    /**
     * The purpose of this method is to run a repeated test on the
     * interval named descriptively - enharmonics are tested where applicable
     * <p>Precondition: an Interval has been instantiated</p>
     * <p>Postcondition: The interval has the proper top and bottom notes,
     * quality, intValue and enharmonic where applicable</p>
     */
    @Test
    void MajorSeventhTest(){
        // Major Seventh
        Assertions.assertEquals("e-", majorSeventh.getBottomNote().toString());
        Assertions.assertEquals("d", majorSeventh.getTopNote().toString());
        Assertions.assertEquals(intervalQualities[halfStepsInMajorSeventh], majorSeventh.getQuality());
        Assertions.assertEquals(11, majorSeventh.getIntValue());
    }

    /**
     * The purpose of this method is to run a repeated test on the
     * interval named descriptively - enharmonics are tested where applicable
     * <p>Precondition: an Interval has been instantiated</p>
     * <p>Postcondition: The interval has the proper top and bottom notes,
     * quality, intValue and enharmonic where applicable</p>
     */
    @Test
    void DiminishedSecondTest(){
        // Diminished Second
        Assertions.assertEquals("b", diminishedSecond.getBottomNote().toString());
        Assertions.assertEquals("c-", diminishedSecond.getTopNote().toString());
        Assertions.assertEquals(enharmonicIntervals[halfStepsInUnison], diminishedSecond.getQuality());
        Assertions.assertEquals(intervalQualities[halfStepsInUnison], diminishedSecond.getEnharmonic());
        Assertions.assertEquals(0, diminishedSecond.getIntValue());
    }

    /**
     * The purpose of this method is to run a repeated test on the
     * interval named descriptively - enharmonics are tested where applicable
     * <p>Precondition: an Interval has been instantiated</p>
     * <p>Postcondition: The interval has the proper top and bottom notes,
     * quality, intValue and enharmonic where applicable</p>
     */
    @Test
    void AugmentedUnisonTest(){
        // Augmented Unison
        Assertions.assertEquals("d", augmentedUnison.getBottomNote().toString());
        Assertions.assertEquals("d#", augmentedUnison.getTopNote().toString());
        Assertions.assertEquals(enharmonicIntervals[halfStepsInMinorSecond], augmentedUnison.getQuality());
        Assertions.assertEquals(intervalQualities[halfStepsInMinorSecond], augmentedUnison.getEnharmonic());
        Assertions.assertEquals(1, augmentedUnison.getIntValue());
    }

    /**
     * The purpose of this method is to run a repeated test on the
     * interval named descriptively - enharmonics are tested where applicable
     * <p>Precondition: an Interval has been instantiated</p>
     * <p>Postcondition: The interval has the proper top and bottom notes,
     * quality, intValue and enharmonic where applicable</p>
     */
    @Test
    void DiminishedThirdTest(){
        // Diminished Third
        Assertions.assertEquals("c#", diminishedThird.getBottomNote().toString());
        Assertions.assertEquals("e-", diminishedThird.getTopNote().toString());
        Assertions.assertEquals(enharmonicIntervals[halfStepsInMajorSecond], diminishedThird.getQuality());
        Assertions.assertEquals(intervalQualities[halfStepsInMajorSecond], diminishedThird.getEnharmonic());
        Assertions.assertEquals(2, diminishedThird.getIntValue());
    }

    /**
     * The purpose of this method is to run a repeated test on the
     * interval named descriptively - enharmonics are tested where applicable
     * <p>Precondition: an Interval has been instantiated</p>
     * <p>Postcondition: The interval has the proper top and bottom notes,
     * quality, intValue and enharmonic where applicable</p>
     */
    @Test
    void AugmentedSecondTest(){
        // Augmented Second
        Assertions.assertEquals("d-", augmentedSecond.getBottomNote().toString());
        Assertions.assertEquals("e", augmentedSecond.getTopNote().toString());
        Assertions.assertEquals(enharmonicIntervals[halfStepsInMinorThird], augmentedSecond.getQuality());
        Assertions.assertEquals(intervalQualities[halfStepsInMinorThird], augmentedSecond.getEnharmonic());
        Assertions.assertEquals(3, augmentedSecond.getIntValue());
    }

    /**
     * The purpose of this method is to run a repeated test on the
     * interval named descriptively - enharmonics are tested where applicable
     * <p>Precondition: an Interval has been instantiated</p>
     * <p>Postcondition: The interval has the proper top and bottom notes,
     * quality, intValue and enharmonic where applicable</p>
     */
    @Test
    void DiminishedFourthTest(){
        // Diminished Fourth
        Assertions.assertEquals("e", diminishedFourth.getBottomNote().toString());
        Assertions.assertEquals("a-", diminishedFourth.getTopNote().toString());
        Assertions.assertEquals(enharmonicIntervals[halfStepsInMajorThird], diminishedFourth.getQuality());
        Assertions.assertEquals(intervalQualities[halfStepsInMajorThird], diminishedFourth.getEnharmonic());
        Assertions.assertEquals(4, diminishedFourth.getIntValue());
    }

    /**
     * The purpose of this method is to run a repeated test on the
     * interval named descriptively - enharmonics are tested where applicable
     * <p>Precondition: an Interval has been instantiated</p>
     * <p>Postcondition: The interval has the proper top and bottom notes,
     * quality, intValue and enharmonic where applicable</p>
     */
    @Test
    void AugmentedThirdTest(){
        // Augmented Third
        Assertions.assertEquals("d-", augmentedThird.getBottomNote().toString());
        Assertions.assertEquals("f#", augmentedThird.getTopNote().toString());
        Assertions.assertEquals(enharmonicIntervals[halfStepsInPerfectFourth], augmentedThird.getQuality());
        Assertions.assertEquals(intervalQualities[halfStepsInPerfectFourth], augmentedThird.getEnharmonic());
        Assertions.assertEquals(5, augmentedThird.getIntValue());
    }

    // Tritone, Augmented Fourth, Diminished Fifth tested above


    /**
     * The purpose of this method is to run a repeated test on the
     * interval named descriptively - enharmonics are tested where applicable
     * <p>Precondition: an Interval has been instantiated</p>
     * <p>Postcondition: The interval has the proper top and bottom notes,
     * quality, intValue and enharmonic where applicable</p>
     */
    @Test
    void DiminishedSixthTest(){
        // Diminished Sixth
        Assertions.assertEquals("c#", diminishedSixth.getBottomNote().toString());
        Assertions.assertEquals("a-", diminishedSixth.getTopNote().toString());
        Assertions.assertEquals(enharmonicIntervals[halfStepsInPerfectFifth], diminishedSixth.getQuality());
        Assertions.assertEquals(intervalQualities[halfStepsInPerfectFifth], diminishedSixth.getEnharmonic());
        Assertions.assertEquals(7, diminishedSixth.getIntValue());
    }

    /**
     * The purpose of this method is to run a repeated test on the
     * interval named descriptively - enharmonics are tested where applicable
     * <p>Precondition: an Interval has been instantiated</p>
     * <p>Postcondition: The interval has the proper top and bottom notes,
     * quality, intValue and enharmonic where applicable</p>
     */
    @Test
    void AugmentedFifthTest(){
        // Augmented Fifth
        Assertions.assertEquals("e-", augmentedFifth.getBottomNote().toString());
        Assertions.assertEquals("b", augmentedFifth.getTopNote().toString());
        Assertions.assertEquals(enharmonicIntervals[halfStepsInMinorSixth], augmentedFifth.getQuality());
        Assertions.assertEquals(intervalQualities[halfStepsInMinorSixth], augmentedFifth.getEnharmonic());
        Assertions.assertEquals(8, augmentedFifth.getIntValue());
    }

    /**
     * The purpose of this method is to run a repeated test on the
     * interval named descriptively - enharmonics are tested where applicable
     * <p>Precondition: an Interval has been instantiated</p>
     * <p>Postcondition: The interval has the proper top and bottom notes,
     * quality, intValue and enharmonic where applicable</p>
     */
    @Test
    void DiminishedSeventhTest(){
        // Diminished Seventh
        Assertions.assertEquals("d", diminishedSeventh.getBottomNote().toString());
        Assertions.assertEquals("c-", diminishedSeventh.getTopNote().toString());
        Assertions.assertEquals(enharmonicIntervals[halfStepsInMajorSixth], diminishedSeventh.getQuality());
        Assertions.assertEquals(intervalQualities[halfStepsInMajorSixth], diminishedSeventh.getEnharmonic());
        Assertions.assertEquals(9, diminishedSeventh.getIntValue());

    }

    /**
     * The purpose of this method is to run a repeated test on the
     * interval named descriptively - enharmonics are tested where applicable
     * <p>Precondition: an Interval has been instantiated</p>
     * <p>Postcondition: The interval has the proper top and bottom notes,
     * quality, intValue and enharmonic where applicable</p>
     */
    @Test
    void AugmentedSixthTest(){
        // Augmented Sixth
        Assertions.assertEquals("e-", augmentedSixth.getBottomNote().toString());
        Assertions.assertEquals("c#", augmentedSixth.getTopNote().toString());
        Assertions.assertEquals(enharmonicIntervals[halfStepsInMinorSeventh], augmentedSixth.getQuality());
        Assertions.assertEquals(intervalQualities[halfStepsInMinorSeventh], augmentedSixth.getEnharmonic());
        Assertions.assertEquals(10, augmentedSixth.getIntValue());

    }
}