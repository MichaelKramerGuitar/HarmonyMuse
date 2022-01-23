package HarmonyMuse;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ReadFromFile {

    public static ArrayList<Chord> readFile(){
        ArrayList<Chord> chordsOnFile = new ArrayList<>(0);
        ArrayList<String> container = new ArrayList<>(0);
        // open input.txt, read its contents and close the file
        try(Scanner input = new Scanner(Paths.get("data\\input.txt"))) {
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
                    } catch (IllegalArgumentException e) {
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
