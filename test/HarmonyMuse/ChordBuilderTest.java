package HarmonyMuse;

import Builders.ChordBuilder;
import Builders.Note;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
            } catch (IllegalArgumentException e){
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

    @Test
    void ChordBuilderGetRootTest(){
        // inherited method overridden from Chord class should return null
        Assertions.assertEquals(null, rawData.getRoot());

    }

    @Test
    void ChordBuilderToStringTest() {
        // toString method internally calls super.toString
        Assertions.assertEquals("I am a chord builder and here are my notes: [c, e, g]", rawData.toString());
    }

}