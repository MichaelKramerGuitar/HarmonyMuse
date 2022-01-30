package Builders;

import AbstractStructures.Chord;
import ThreeNoteStructures.MajorTriad;
import ThreeNoteStructures.MinorTriad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to test the construction a class methods of
 * the Chord Sequence Test, a generic class where type E extends Chord
 */
class ChordSequenceTest {

    private ChordSequence chordSequence;

    @BeforeEach
    void setUp(){
        // I Chord
        String[] data = new String[]{"c", "e", "g"}; // create input data
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
        MajorTriad one = new MajorTriad(rawData);
        one.setRoot(rawData.getNotes()[0]);
        one.setThird(rawData.getNotes()[1]);
        one.setFifth(rawData.getNotes()[2]);
        one.setQuality("major triad");
        one.setInversion("root position");

        // ii Chord
        String[] data1 = new String[]{"d", "f", "a"}; // create input data
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
        MinorTriad two = new MinorTriad(rawData1);
        two.setRoot(rawData1.getNotes()[0]);
        two.setThird(rawData1.getNotes()[1]);
        two.setFifth(rawData1.getNotes()[2]);
        two.setQuality("minor triad");
        two.setInversion("root position");

        // V Chord
        String[] data2 = new String[]{"g", "b", "d"}; // create input data
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
        MajorTriad five = new MajorTriad(rawData2);
        five.setRoot(rawData2.getNotes()[0]);
        five.setThird(rawData2.getNotes()[1]);
        five.setFifth(rawData2.getNotes()[2]);
        five.setQuality("major triad");
        five.setInversion("root position");

        this.chordSequence = new ChordSequence(two, five, one, one.getRoot());
    }

    /**
     * The purpose of this method is to test the method constructor where
     * three objects of generic type E extends Chord are passed plus one Note
     * argument representing the tonalCenter is passed
     * <p>Precondition: A ChordSequence object was created from three
     * objects of type E extends Chord and one Note object for tonalCenter</p>
     * <p>Postcondition: The Object passed as the third argument was properly
     * classified, it's root should correspond with the tonalCenter of this
     * ChordSequence</p>
     */
    @Test
    void TestSequenceBuild(){

        Chord one = chordSequence.getChord(2);

        assertTrue(one instanceof MajorTriad);
        assertEquals("c", one.getRoot().toString());
        assertEquals("e", ((MajorTriad) one).getThird().toString());
        assertEquals("g", ((MajorTriad) one).getFifth().toString());
        assertEquals("root position", ((MajorTriad) one).getInversion());
        assertEquals("major triad", ((MajorTriad) one).getQuality());
        assertEquals(one.getRoot(), this.chordSequence.getTonalCenter());
    }

    /**
     * The purpose of this method is to test the progression (ex: ii-V-I) is
     * properly set for the chords in the setUp function. Here we query the
     * int values so (ex: ii-V-I) should correspond to [2, 7, 0] or
     * [major second, perfect fifth, unison/octave] without .getIntValue()
     * <p>Precondition: A ChordSequence was created with Chords that have
     * a ii-V-I relationship</p>
     * <p>Postcondition: The ii-V-I relationship is reflected in the
     * int values of the Interval relationships/classifications
     * of each chords root to the tonalCenter passed to the ChordSequence object</p>
     */
    @Test
    void TestProgressionSet(){
        ArrayList<Integer> progression = new ArrayList<>(3);
        for(int i = 0; i < chordSequence.getProgression().size(); i++){
            Interval interval = (Interval) chordSequence.getProgression().get(i);
            progression.add(interval.getIntValue());
        }
        assertEquals("[2, 7, 0]", progression.toString());
    }

    /**
     * The purpose of this method is to ensure an IndexOutOfBoundsException
     * is thrown should an attempt to get a Chord out of the range of the
     * chordSequence.sequence ArrayList attribute is called
     * <p>Precondition: A ChordSequence of length 3 has been instantiated </p>
     * <p>Postcondition: An IndexOutOfBoundsException is thrown when
     * chordSequence.getChord(int not in range 0-2) is called </p>
     *
     * @return
     */
    @Test
    void TestIndexOutOfBoundsThrown(){
        Exception thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
            this.chordSequence.getChord(3);});
        assertEquals("Error: index must be in range 0-" + (this.chordSequence.getSize() - 1), thrown.getMessage());

        Exception thrown1 = assertThrows(IndexOutOfBoundsException.class, () -> {
            this.chordSequence.getChord(-1);});
        assertEquals("Error: index must be in range 0-" + (this.chordSequence.getSize() - 1), thrown1.getMessage());
    }

}