package file.handling;

import builders.ChordSequence;
import builders.InvalidNoteException;
import builders.Note;
import classifiers.TriadClassifier;
import commandline.app.CommonView;
import general.containers.Chord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import three.note.structures.*;

import java.util.stream.Stream;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to test the JSON serialization and deserialization
 * of objects native to this application
 */
class WriteReadFromJSONTest {

    private ChordSequence chordSequence;
    private final String filename = "test";
    private final WriteToJSON writer = new WriteToJSON();
    private final TriadClassifier tc = new TriadClassifier();
    private final ReadFromJSON reader = new ReadFromJSON();

    /**
     * The purpose of this method is to initialize Chords of different types
     * and store these in a ChordSequence for JSON serialization and deserialization
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

    /**
     * The purpose of this method is to test the FileHandling.WriteToJSON class
     * <p>Precondition: a ChordSequence object has been initialized and a filename
     * has been passed to the system</p>
     * <p>Postcondition: The ChordSequence object is serialized to a JSON file
     * filename.json</p>
     */
    @Test
    void TestWriteToJSON(){
        writer.writeSequenceToJSON(this.filename, this.chordSequence);
    }

    /**
     * The purpose of this method is to test that a JSON serialized ChordSequence
     * is read back into memory as a ChordSequence, that each chord can be
     * handled back into their original qualities and that the Roman Numeral
     * Analysis fundamental to the ChordSequence type can be performed
     * <p>Precondition: There is a json file with a serialized ChordSequence</p>
     * <p>Postcondition: The ChordSequence has been read back into memory to
     * be operated on</p>
     */
    @Test
    void TestReadChordSequenceFromFromJSON(){

        ChordSequence deserializedSequence = reader.readChordSequenceFromJSON(this.filename);

        for (int i = 0; i < deserializedSequence.getSize(); i++) {
            Assertions.assertTrue(deserializedSequence.getChord(i) instanceof ConcreteTriad);
            if (deserializedSequence.getChord(i).getQuality().equals(tc.getTriadQualities()[0])) {
                DiminishedTriad diminishedTriad = new DiminishedTriad(deserializedSequence.getChord(i).getRoot());
                Assertions.assertTrue(diminishedTriad instanceof DiminishedTriad);
            } else if (deserializedSequence.getChord(i).getQuality().equals(tc.getTriadQualities()[1])) {
                MinorTriad minorTriad = new MinorTriad(deserializedSequence.getChord(i).getRoot());
                Assertions.assertTrue(minorTriad instanceof MinorTriad);
                //System.out.println("after cast: " + minorTriad);
            } else if (deserializedSequence.getChord(i).getQuality() == tc.getTriadQualities()[2]) {
                MajorTriad majorTriad = new MajorTriad(deserializedSequence.getChord(i).getRoot());
                Assertions.assertTrue(majorTriad instanceof MajorTriad);
            } else if (deserializedSequence.getChord(i).getQuality() == tc.getTriadQualities()[3]) {
                AugmentedTriad augmentedTriad = new AugmentedTriad(deserializedSequence.getChord(i).getRoot());
                Assertions.assertTrue(augmentedTriad instanceof AugmentedTriad);
            }
        }
        System.out.println("\nWriteReadFromJSONTest.TestReadChordSequenceFromJSON");
        String[] romans = CommonView.addRomanNumeral(chordSequence);
        System.out.println("Sequence tonal center: " + chordSequence.getTonalCenter().toString().toUpperCase());
        for (String roman: romans
        ) {
            System.out.print(roman + " ");
        }

    }

    /**
     * The purpose of this method is to test that a deserialized ChordSequence
     * can be converted to a Java Stream of Chords via the ReadFromJSON.deserializedJSONToChordStream method
     * and filtered out by quality
     * <p>Precondition: a json file of serialized chord sequences exists</p>
     * <p>Postcondition: the chords of specified quality are filtered out
     * of the Stream created from the object read back into memory</p>
     */
    @Test
    void TestChordStreamFilterInstanceof(){
        System.out.println("\nWriteReadFromJSONTest.TestChordStreamFilterInstanceof");
        ChordSequence deserializedSequence = reader.readChordSequenceFromJSON(this.filename);
        Stream<Chord> chordStream = deserializedSequence.chordSequenceToChordStream(deserializedSequence);
        chordStream.filter(i -> i instanceof AugmentedTriad).forEach(i -> System.out.println(i));
    }

    /**
     * The purpose of this method is simply to read a ChordSequence back into
     * memory and print each Chord in the Sequence to the console by way of
     * creating a Stream of Chords with the ReadFromJSON.deserializedJSONToChordStream
     * method
     * <p>Precondition: a json file with a serialized ChordStream exists</p>
     * <p>Postcondition: The ChordSequence is deserialized and converted to
     * a Stream of Chords which can be operated of as a Stream of Objects</p>
     */
    @Test
    void TestChordStreamForEach(){
        System.out.println("\nWriteReadFromJSONTest.TestChordStreamForEach:");
        ChordSequence deserializedSequence = reader.readChordSequenceFromJSON(this.filename);
        Stream<Chord> chordStream = deserializedSequence.chordSequenceToChordStream(deserializedSequence);
        chordStream.forEach(i -> System.out.println(i));
    }
}