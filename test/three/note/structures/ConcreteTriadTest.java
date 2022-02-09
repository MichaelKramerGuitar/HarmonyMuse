package three.note.structures;

import builders.InvalidNoteException;
import builders.Note;
import classifiers.TriadClassifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to Test the primal functionality of the
 * MajorTriad class - it's ability to take a Note parameter and build
 * a correct from the input of a valid note
 */
class ConcreteTriadTest {

    private TriadClassifier tc;
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
            ConcreteTriad C = new ConcreteTriad(c, tc.getTriadQualities()[2]); // major

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
            ConcreteTriad E = new ConcreteTriad(e, tc.getTriadQualities()[1]); // minor

            assertEquals(tc.getTriadQualities()[1], E.getQuality());
            assertEquals("root position", E.getInversion());
            assertEquals("e", E.getRoot().toString());
            assertEquals("g", E.getThird().toString());
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
            ConcreteTriad Gflat = new ConcreteTriad(gFlat, tc.getTriadQualities()[0]); // diminished

            assertEquals(tc.getTriadQualities()[0], Gflat.getQuality());
            assertEquals("root position", Gflat.getInversion());
            assertEquals("g-", Gflat.getRoot().toString());
            assertEquals("b--", Gflat.getThird().toString());
            assertEquals("d--", Gflat.getFifth().toString());
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
            ConcreteTriad Eflat = new ConcreteTriad(eFlat, tc.getTriadQualities()[3]); // augmented

            assertEquals(tc.getTriadQualities()[3], Eflat.getQuality());
            assertEquals("root position", Eflat.getInversion());
            assertEquals("e-", Eflat.getRoot().toString());
            assertEquals("g", Eflat.getThird().toString());
            assertEquals("b", Eflat.getFifth().toString());
        }catch (InvalidNoteException e){
            System.out.println(e);
        }
    }
}