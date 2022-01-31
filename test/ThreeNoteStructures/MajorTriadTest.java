package ThreeNoteStructures;

import Builders.ChordBuilder;
import Builders.InvalidNoteException;
import Builders.Note;
import Classifiers.TriadClassifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to Test the primal functionality of the
 * MajorTriad class - it's ability to take a Note parameter and build
 * a correct from the input of a valid note
 */
class MajorTriadTest {

    private TriadClassifier tc;
    private ChordBuilder cb;
    @BeforeEach
    void setUp(){
        this.tc = new TriadClassifier();
    }

    /**
     * The purpose of this method is to test the instantiation of a Triad
     * when the Note "c" is passed to the constructor
     * <p>Precondition: A Note has been properly instantiated </p>
     * <p>Postcondition: A root position
     * Triad of appropriate quality is built given the Note object input as root </p>
     */
    @Test
    void TestCRoot(){
        try {
            Note c = new Note("c");
            MajorTriad C = new MajorTriad(c);

            assertEquals(tc.getTriadQualities()[2], C.getQuality());
            assertEquals("root position", C.getInversion());
            assertEquals("c", C.getRoot().toString());
            assertEquals("e", C.getThird().toString());
            assertEquals("g", C.getFifth().toString());
        }catch (
                InvalidNoteException e){
            System.out.println(e);
        }
    }


    /**
     * The purpose of this method is to test the instantiation of a Triad
     * when the Note "e" is passed to the constructor
     * <p>Precondition: A Note has been properly instantiated </p>
     * <p>Postcondition: A root position
     * Triad of appropriate quality is built given the Note object input as root </p>
     */
    @Test
    void TestERoot(){
        try {
            Note e = new Note("e");
            MajorTriad E = new MajorTriad(e);

            assertEquals(tc.getTriadQualities()[2], E.getQuality());
            assertEquals("root position", E.getInversion());
            assertEquals("e", E.getRoot().toString());
            assertEquals("g#", E.getThird().toString());
            assertEquals("b", E.getFifth().toString());
        }catch (InvalidNoteException e){
            System.out.println(e);
        }
    }

    /**
     * The purpose of this method is to test the instantiation of a Triad
     * when the Note "g-" is passed to the constructor
     * <p>Precondition: A Note has been properly instantiated </p>
     * <p>Postcondition: A root position
     * Triad of appropriate quality is built given the Note object input as root </p>
     */
    @Test
    void TestGflatRoot(){
        try {
            Note gFlat = new Note("g-");
            MajorTriad Gflat = new MajorTriad(gFlat);

            assertEquals(tc.getTriadQualities()[2], Gflat.getQuality());
            assertEquals("root position", Gflat.getInversion());
            assertEquals("g-", Gflat.getRoot().toString());
            assertEquals("b-", Gflat.getThird().toString());
            assertEquals("d-", Gflat.getFifth().toString());
        }catch (InvalidNoteException e){
            System.out.println(e);
        }
    }

    /**
     * The purpose of this method is to test the instantiation of a Triad
     * when the Note "e-" is passed to the constructor
     * <p>Precondition: A Note has been properly instantiated </p>
     * <p>Postcondition: A root position
     * Triad of appropriate quality is built given the Note object input as root </p>
     */
    @Test
    void TestEflatRoot(){
        try {
            Note eFlat = new Note("e-");
            MajorTriad Eflat = new MajorTriad(eFlat);

            assertEquals(tc.getTriadQualities()[2], Eflat.getQuality());
            assertEquals("root position", Eflat.getInversion());
            assertEquals("e-", Eflat.getRoot().toString());
            assertEquals("g", Eflat.getThird().toString());
            assertEquals("b-", Eflat.getFifth().toString());
        }catch (InvalidNoteException e){
            System.out.println(e);
        }
    }

    /**
     * The purpose of this method is to test the instantiation of a Triad
     * when the Note "d#" is passed to the constructor
     * <p>Precondition: A Note has been properly instantiated </p>
     * <p>Postcondition: A root position
     * Triad of appropriate quality is built given the Note object input as root </p>
     */
    @Test
    void TestDsharpRoot(){
        try {
            Note dSharp = new Note("d#");
            MajorTriad Dsharp = new MajorTriad(dSharp);
            assertEquals(tc.getTriadQualities()[2], Dsharp.getQuality());
            assertEquals("root position", Dsharp.getInversion());
            assertEquals("d#", Dsharp.getRoot().toString());
            assertEquals("f##", Dsharp.getThird().toString());
            assertEquals("a#", Dsharp.getFifth().toString());
        }catch (InvalidNoteException e){
            System.out.println(e);
        }

    }
}