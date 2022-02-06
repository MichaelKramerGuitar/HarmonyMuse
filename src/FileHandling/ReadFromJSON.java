package FileHandling;

import AbstractStructures.Chord;
import Builders.ChordSequence;
import Classifiers.TriadClassifier;
import ThreeNoteStructures.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Assertions;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to deserialize JSON objects from file and
 * get them into Object forms that can be manipulated by the system
 */
public class ReadFromJSON<T extends Chord> {

    /**
     * The purpose of this method is to deserialize a ChordSequence object from
     * file
     * <p>Precondition: a file exists of the passed filename param with
     * a serialized ChordSequence Object</p>
     * <p>Postcondition: The ChordSequence is read back into memory and returned</p>
     *
     * @param filename the file to be read
     */
    public ChordSequence<Chord> readChordSequenceFromJSON(String filename){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            // you have to give it a concrete type
            Type type = new TypeToken<ChordSequence<ConcreteTriad>>(){}.getType();
            ChordSequence<Chord> deserializedSequence = gson.fromJson(new FileReader("data\\" + filename + ".json"), type);

            return deserializedSequence;
            } catch (IOException e){
            System.out.println(e);
        }
        return null;
    }

    /**
     * The purpose of this method is to convert a ChordSequence object that has
     * just been successfully read from file into a Stream of Chords for
     * manipulation
     * <p>Precondition: A ChordSequence has successfully been deserialized</p>
     * <p>Postcondition: a Stream of Chords is returned</p>
     *
     * @param deserializedSequence is a ChordSequence to be returned as a Stream
     *                             of Chords
     */
    public Stream<Chord> deserializedJSONToChordStream(ChordSequence<Chord> deserializedSequence) {
        TriadClassifier tc = new TriadClassifier();
        Chord[] chordsFromFile = new Chord[deserializedSequence.getSize()];
        for (int i = 0; i < deserializedSequence.getSize(); i++) {
            Assertions.assertTrue(deserializedSequence.getChord(i) instanceof ConcreteTriad);
            if (deserializedSequence.getChord(i).getQuality().equals(tc.getTriadQualities()[0])) {
                DiminishedTriad diminishedTriad = new DiminishedTriad(deserializedSequence.getChord(i).getRoot());
                chordsFromFile[i] = diminishedTriad;
            } else if (deserializedSequence.getChord(i).getQuality().equals(tc.getTriadQualities()[1])) {
                MinorTriad minorTriad = new MinorTriad(deserializedSequence.getChord(i).getRoot());
                chordsFromFile[i] = minorTriad;
            } else if (deserializedSequence.getChord(i).getQuality().equals(tc.getTriadQualities()[2])) {
                MajorTriad majorTriad = new MajorTriad(deserializedSequence.getChord(i).getRoot());
                chordsFromFile[i] = majorTriad;
            } else if (deserializedSequence.getChord(i).getQuality().equals(tc.getTriadQualities()[3])) {
                AugmentedTriad augmentedTriad = new AugmentedTriad(deserializedSequence.getChord(i).getRoot());
                chordsFromFile[i] = augmentedTriad;
            }
        }
        Stream<Chord> chordSequenceStream = Arrays.stream(chordsFromFile);
        return chordSequenceStream;
    }
}
