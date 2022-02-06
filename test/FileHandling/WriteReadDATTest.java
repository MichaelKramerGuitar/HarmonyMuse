package FileHandling;

import Builders.ChordSequence;
import Builders.InvalidNoteException;
import Builders.Note;
import ThreeNoteStructures.AugmentedTriad;
import ThreeNoteStructures.DiminishedTriad;
import ThreeNoteStructures.MajorTriad;
import ThreeNoteStructures.MinorTriad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to test the serialization and deserialization
 * of objects native to this application
 */
class WriteReadDATTest {
    private ChordSequence chordSequence;
    private final String filename = "test";
    private final WriteToDAT writer = new WriteToDAT();
    private final ReadFromDAT reader = new ReadFromDAT();

    /**
     * The purpose of this method is to initialize Chords of different types
     * and store these in a ChordSequence for serialization and deserialization
     */
    @BeforeEach
    void setUp(){
        Note c;
        Note fSharp;
        Note eNat;
        Note a;
        try{
            c = new Note("c");
            MajorTriad C = new MajorTriad(c);
            fSharp = new Note("f#");
            MinorTriad Fsharp = new MinorTriad(fSharp);
            eNat = new Note("e");
            AugmentedTriad E = new AugmentedTriad(eNat);
            a = new Note("a");
            DiminishedTriad A = new DiminishedTriad(a);
            this.chordSequence = new ChordSequence(C, Fsharp, E, A, c);

        }catch (InvalidNoteException e){
            System.out.println(e);
        }
    }

    @Test
    void TestWriter(){
        writer.writeChordSequenceToDAT(this.filename, this.chordSequence);
    }

    @Test
    void TestReader(){
        reader.readChordSequenceFromDAT(this.filename);
    }
}