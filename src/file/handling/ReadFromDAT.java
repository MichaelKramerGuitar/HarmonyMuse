package file.handling;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to facilitate the convenience of reading
 * .dat files from database
 */
public class ReadFromDAT {

    /**
     * The purpose of this method is to read serialized data
     * and print data to console
     * <p>Precondition: a filename.dat file exists in the specified directory</p>
     * <p>Postcondition: the contents of filename.dat are on the console</p>
     *
     * @param filename a valid filename to read
     */
    public void readChordSequenceFromDAT(String filename){
        try (ObjectInputStream infile = new ObjectInputStream(new FileInputStream("data\\" + filename +".dat"))){

            for(int i = 0; i < 4; i++){
                System.out.printf("%s%n", (infile.readObject()));
            }
        } catch(IOException | ClassNotFoundException e){
            System.out.println(e);
        }
    }
}
