package file.handling;

import general.containers.Chord;
import general.containers.Triad;
import builders.ChordBuilder;
import three.note.structures.MajorTriad;
import three.note.structures.MinorTriad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to test the file I/O to the default file serving
 * as first iteration database
 */
class FileTest {

    private String[] data;
    private String[] data1;
    private String[] data2;
    private String filename = "testInput";

    @BeforeEach
    void setUp() {
        String[] data = new String[]{"c", "e", "g"}; // create input data
        this.data = data;
        String[] data1 = new String[]{"d-", "f-", "a-"}; // create more input data
        this.data1 = data1;
        String[] data2 = new String[]{"c", "f#", "a#"}; // create more input data
        this.data2 = data2;


    }

    /**
     * The purpose of this method is to test the writeToFile method of the WriteToFile class
     * <p>Precondition: default file is cleared and String[]'s are instantiated</p>
     * <p>Postcondition: String[]'s written to file</p>
     */
    @Test
    void TestWriteToFile() {
        WriteToFile.clearFile(this.filename); // clear the default file
        WriteToFile.writeToFile(this.filename, data);
        WriteToFile.writeToFile(this.filename, data1);
        WriteToFile.writeToFile(this.filename, data2);
    }

    /**
     * The purpose of this method is to test the readFile method is the ReadFromFile class
     * <p>Precondition: The data defined in the private attributes of this
     * Test class are written to the default text file</p>
     * <p>Postcondition: The data is read into the chordsOnFile ArrayList<Chord>
     * </p>
     */
    @Test
    void TestReadFromFile(){

        ArrayList<Chord> chordsOnFile = ReadFromFile.readFile(this.filename);
        // successful major triad
        Assertions.assertTrue(chordsOnFile.get(0) instanceof MajorTriad);
        // successful minor triad
        Assertions.assertTrue(chordsOnFile.get(1) instanceof MinorTriad);
        // unsuccessful triad
        Assertions.assertFalse(chordsOnFile.get(2) instanceof Triad);
        // with more implementation of classification this will fail
        Assertions.assertTrue(chordsOnFile.get(2) instanceof ChordBuilder);
    }
}