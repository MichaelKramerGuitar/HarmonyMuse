package file.handling;

import general.containers.Chord;
import builders.ChordSequence;
import three.note.structures.ConcreteTriad;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;

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
}
