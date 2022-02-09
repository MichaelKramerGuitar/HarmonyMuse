package classifiers;

import builders.ChordBuilder;
import builders.Interval;
import builders.InvalidNoteException;
import builders.Note;
import general.containers.Chord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import three.note.structures.AugmentedTriad;
import three.note.structures.DiminishedTriad;
import three.note.structures.MajorTriad;
import three.note.structures.MinorTriad;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to Test the Triad Classifier as this class
 * is the central communicator between the ChordBuilder Class and all of the
 * triad classes in the ThreeNoteStructures package in addition to the applications
 * of the Note and Interval classes from the Builders package
 */
class TriadClassifierTest {

    private ChordBuilder dFlatMajor;
    private ChordBuilder eMinorSecondInv;
    private ChordBuilder gSharpDimFirstInv;
    private ChordBuilder bAugmentedRootPos;
    private ChordBuilder triadFail;
    private TriadClassifier tc;

    @BeforeEach
    void setUp() {
        this.tc = new TriadClassifier();
        // Test Root Position Major
        String[] data = new String[]{"d-", "f", "a-"}; // create input data
        // Create a ChordBuilder instance from input data called rawData
        Note[] notes = new Note[data.length];
        for (int i = 0; i < data.length; i++){
            try{
                notes[i] = new Note(data[i]);
            } catch (InvalidNoteException e){
                System.out.println(e);
            }
        }
        ChordBuilder rawData = new ChordBuilder(notes);
        /*
        assign the ChordBuilder object with dummy data to the private
        class attribute rawData to test all class methods
         */
        this.dFlatMajor = rawData;

        // Test Second Inversion Minor
        String[] data1 = new String[]{"b", "e", "g"}; // create input data
        // Create a ChordBuilder instance from input data called rawData
        Note[] notes1 = new Note[data1.length];
        for (int i = 0; i < data1.length; i++){
            try{
                notes1[i] = new Note(data1[i]);
            } catch (InvalidNoteException e){
                System.out.println(e);
            }
        }
        ChordBuilder rawData1 = new ChordBuilder(notes1);
        /*
        assign the ChordBuilder object with dummy data to the private
        class attribute rawData to test all class methods
         */
        this.eMinorSecondInv = rawData1;

        // Test First Inversion Diminished
        String[] data2 = new String[]{"b", "d", "g#"}; // create input data
        // Create a ChordBuilder instance from input data called rawData
        Note[] notes2 = new Note[data2.length];
        for (int i = 0; i < data2.length; i++){
            try{
                notes2[i] = new Note(data2[i]);
            } catch (InvalidNoteException e){
                System.out.println(e);
            }
        }
        ChordBuilder rawData2 = new ChordBuilder(notes2);
        /*
        assign the ChordBuilder object with dummy data to the private
        class attribute rawData to test all class methods
         */
        this.gSharpDimFirstInv = rawData2;

        // Test Root Position Augmented
        String[] data3 = new String[]{"b", "d#", "f##"}; // create input data
        // Create a ChordBuilder instance from input data called rawData
        Note[] notes3 = new Note[data3.length];
        for (int i = 0; i < data3.length; i++){
            try{
                notes3[i] = new Note(data3[i]);
            } catch (InvalidNoteException e){
                System.out.println(e);
            }
        }
        ChordBuilder rawData3 = new ChordBuilder(notes3);
        /*
        assign the ChordBuilder object with dummy data to the private
        class attribute rawData to test all class methods
         */
        this.bAugmentedRootPos = rawData3;

        // Test InvalidTriadException
        String[] data4 = new String[]{"b", "e", "a"}; // create input data
        // Create a ChordBuilder instance from input data called rawData
        Note[] notes4 = new Note[data4.length];
        for (int i = 0; i < data4.length; i++){
            try{
                notes4[i] = new Note(data4[i]);
            } catch (InvalidNoteException e){
                System.out.println(e);
            }
        }
        ChordBuilder rawData4 = new ChordBuilder(notes4);
        /*
        assign the ChordBuilder object with dummy data to the private
        class attribute rawData to test all class methods
         */
        this.triadFail = rawData4;
    }

    /**
     * The purpose of this method is to test the ChordBuilder classify method
     * which in turn calls the TriadClassifier.classifyTriad() method
     * <p>Precondition: a Chord Builder has been instantiated with the
     * expected classification of a root position D Flat Major Triad</p>
     * <p>Postcondition: A D Flat Major triad is properly classified and
     * instantiated</p>
     */
    @Test
    void TestClassifyMajor(){

        Chord majTriad = dFlatMajor.classify(dFlatMajor);

        assertTrue(majTriad instanceof MajorTriad);
        assertEquals("d-", majTriad.getRoot().toString());
        assertEquals("f", ((MajorTriad) majTriad).getThird().toString());
        assertEquals("a-", ((MajorTriad) majTriad).getFifth().toString());
        assertEquals("root position", ((MajorTriad) majTriad).getInversion());
        assertEquals(tc.getTriadQualities()[2], ((MajorTriad) majTriad).getQuality());
    }

    /**
     * The purpose of this method is to test the ChordBuilder classify method
     * which in turn calls the TriadClassifier.classifyTriad() method
     * <p>Precondition: a Chord Builder has been instantiated with the
     * expected classification of a second inversion E Minor Triad</p>
     * <p>Postcondition: An E Minor Triad triad is properly classified and
     * instantiated</p>
     */
    @Test
    void TestClassifyMinor(){

        Chord minTriad = eMinorSecondInv.classify(eMinorSecondInv);

        assertTrue(minTriad instanceof MinorTriad);
        assertEquals("e", minTriad.getRoot().toString());
        assertEquals("g", ((MinorTriad) minTriad).getThird().toString());
        assertEquals("b", ((MinorTriad) minTriad).getFifth().toString());
        assertEquals("second inversion", ((MinorTriad) minTriad).getInversion());
        assertEquals(tc.getTriadQualities()[1], ((MinorTriad) minTriad).getQuality());
    }

    /**
     * The purpose of this method is to test the ChordBuilder classify method
     * which in turn calls the TriadClassifier.classifyTriad() method
     * <p>Precondition: a Chord Builder has been instantiated with the
     * expected classification of a first inversion G Sharp Diminished Triad</p>
     * <p>Postcondition: An G Sharp Diminished triad is properly classified and
     * instantiated</p>
     */
    @Test
    void TestClassifyDiminished(){

        Chord dimTriad = gSharpDimFirstInv.classify(gSharpDimFirstInv);

        assertTrue(dimTriad instanceof DiminishedTriad);
        assertEquals("g#", dimTriad.getRoot().toString());
        assertEquals("b", ((DiminishedTriad) dimTriad).getThird().toString());
        assertEquals("d", ((DiminishedTriad) dimTriad).getFifth().toString());
        assertEquals("first inversion", ((DiminishedTriad) dimTriad).getInversion());
        assertEquals(tc.getTriadQualities()[0], ((DiminishedTriad) dimTriad).getQuality());
    }

    /**
     * The purpose of this method is to test the ChordBuilder classify method
     * which in turn calls the TriadClassifier.classifyTriad() method
     * <p>Precondition: a Chord Builder has been instantiated with the
     * expected classification of a first inversion B Augmented Triad</p>
     * <p>Postcondition: An B Augmented triad is properly classified and
     * instantiated</p>
     */
    @Test
    void TestClassifyAugmented(){

        Chord augTriad = bAugmentedRootPos.classify(bAugmentedRootPos);

        assertTrue(augTriad instanceof AugmentedTriad);
        assertEquals("b", augTriad.getRoot().toString());
        assertEquals("d#", ((AugmentedTriad) augTriad).getThird().toString());
        assertEquals("f##", ((AugmentedTriad) augTriad).getFifth().toString());
        assertEquals("root position", ((AugmentedTriad) augTriad).getInversion());
        assertEquals(tc.getTriadQualities()[3], ((AugmentedTriad) augTriad).getQuality());
    }

    /**
     * The purpose of this method is to Test that the triadClassifier() method
     * throws the InvalidTriadException when given a Three Note Structure that
     * does not have the interval signature of a triad
     * <p>Precondition: a three note structure has been instantiated as a
     * ChordBuilder but does not have the interval signature of a triad</p>
     * <p>Postcondition: an InvalidTriad Exception is thrown</p>
     */
    @Test
    void TestThrowsInvalidTriadException(){

        ArrayList<Interval> intervals = triadFail.getIntervals();

        Interval bottom_mid = intervals.get(0);
        Interval bottom_top = intervals.get(1);

        TriadClassifier triadClassifier = new TriadClassifier();

        Exception thrown = assertThrows(InvalidTriadException.class, () -> {
            triadClassifier.classifyTriad(triadFail, bottom_mid, bottom_top);});
        assertEquals(triadFail + " is not a valid triad", thrown.getMessage());
    }


}