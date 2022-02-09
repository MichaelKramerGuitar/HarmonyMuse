package file.handling;


import builders.ChordSequence;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to serialize app native Objects to JSON
 */
public class WriteToJSON {

    /**
     * The purpose of this method is to serialize and write to file a ChordSequence
     * object
     * <p>Precondition: A ChordSequence object has been instantiated and
     * a valid filename passed to the system</p>
     * <p>Postcondition: The ChordSequence is serialized and written to
     * a file filename.json</p>
     *
     * @param filename a valid filename String
     * @param chordSequence a ChordSequence object
     */
    public void writeSequenceToJSON(String filename, ChordSequence chordSequence){
        try (Writer writer = new FileWriter("data\\" + filename +".json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(chordSequence, writer);
        }catch (IOException | NullPointerException e){
            System.out.println(e);
        }
    }
}
