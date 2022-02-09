package builders;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to test the Instantiation of the ChordBuilder
 * class which extends the Chord class
 */
class ChordBuilderTest {

    private ChordBuilder rawData;

    @BeforeEach
    void setUp() {
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
        /*
        assign the ChordBuilder object with dummy data to the private
        class attribute rawData to test all class methods
         */
        this.rawData = rawData;
    }


    /**
     * The purpose of this method is test the getRoot() function behavior that
     * the ChordBuilder class overwrote from the Chord class
     * <p>Precondition: A ChordBuilder has been instantiated</p>
     * <p>Postcondition: null is returned from the getRoot() function as
     * this ChordBuilder has yet to be classified and re-instantiated as
     * another subclass of Chord with a classification</p>
     */
    @Test
    void ChordBuilderGetRootTest(){
        // inherited method overridden from Chord class should return null
        Assertions.assertEquals(null, rawData.getRoot());

    }

    /**
     * The purpose of this method is to test the behavior of the overriden
     * toString() method in the ChordBuilder class
     * <p>Precondition: a ChordBuilder has successfully been instantiated</p>
     * <p>Postcondition: the invoked toString() method returns [c, e, g]</p>
     */
    @Test
    void ChordBuilderToStringTest() {
        // toString method internally calls super.toString
        Assertions.assertEquals("[c, e, g]", rawData.toString());
    }

}