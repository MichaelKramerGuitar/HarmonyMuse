package FileHandling;

import AbstractStructures.Chord;
import Builders.ChordBuilder;
import Builders.InvalidNoteException;
import Builders.Note;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to read data from a file, currently representing
 * the applications database
 */
public class ReadFromFile {

    /**
     * The purpose of this method is to read in each line of a file, convert
     * that line to a ChordBuilder and ultimately return an ArrayList containing
     * each Chord in the file
     * <p>Precondition: A file exists with cleaned data that can be used
     * to instantiate Note objects</p>
     * <p>Postcondition: An ArrayList of all the Chords in the file is returned</p>
     *
     * @return An ArrayList of all the Chords in the file is returned
     */
    public static ArrayList<Chord> readFile(String filename){
        ArrayList<Chord> chordsOnFile = new ArrayList<>(0);
        ArrayList<String> container = new ArrayList<>(0);
        // open input.txt, read its contents and close the file
        try(Scanner input = new Scanner(Paths.get("data\\" + filename + ".txt"))) {
            // read record from file
            while (input.hasNext()) { // while there is more to read
                StringTokenizer st = new StringTokenizer(input.nextLine());
                while (st.hasMoreTokens()){
                    container.add(st.nextToken());
                }
                String[] data = new String[container.size()];
                for(int j = 0; j < container.size(); j++) {
                    data[j] = container.get(j);
                }
                Note[] notes = new Note[data.length];
                for (int i = 0; i < data.length; i++) {
                    try {
                        notes[i] = new Note(data[i]);
                    } catch (InvalidNoteException e) {
                            System.out.println(e);
                    }
                }
                ChordBuilder rawData = new ChordBuilder(notes); // build a chord from input data
                Chord chord = rawData.classify(rawData);
                chordsOnFile.add(chord);
                container.clear(); // clear for the next chord
            }
            // try(with recources IMPLICITLY calls input.close()
            } catch (IOException | NoSuchElementException |
                IllegalStateException e) {
            e.printStackTrace();
        }
        return chordsOnFile;
    }
}
