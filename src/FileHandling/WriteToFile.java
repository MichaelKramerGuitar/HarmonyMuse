package FileHandling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is write cleaned String data that can be
 * instantiated as a Builders.Note object to a file for persistent storage
 */
public class WriteToFile {

    /**
     * The purpose of this method is to flush a file of previous contents
     * <p>Precondition: a file exists with contents that needs to be
     * overwritten</p>
     * <p>Postcondition: the overwritten file is blank</p>
     */
    public static void clearFile(String filename){
        try {
            File file = new File("data\\" + filename + ".txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.flush();
        }catch (IOException e){
            System.out.println("Error flushing file");
            e.printStackTrace();
        }
    }

    /**
     * The purpose of this method is to write cleaned rawInput to a file
     * <p>Precondition: a String array has been populated with cleaned
     * rawInput that can later be instantiated as Note objects</p>
     * <p>Postcondition: the file has the contents of each (later to be
     * instantiated and classified) Chord on its own line</p>
     *
     * @param rawInput a String array that has been populated with cleaned
     * and can later be instantiated as Note objects
     */
    public static void writeToFile(String filename, String[] rawInput){

        try{
            File file = new File("data\\" + filename + ".txt");
            FileWriter fileWriter = new FileWriter(file, true);
            //BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            Formatter outfile = new Formatter(fileWriter);
            for(int i = 0; i < rawInput.length; i++){
                System.out.println(rawInput[i]);
                outfile.format("%s ", rawInput[i]);
                //bufferedWriter.write(rawInput[i]);
            }
            outfile.format("%n");
            outfile.close();
            //bufferedWriter.close();
        }catch (IOException e){
            System.out.println("Error writing to file");
            e.printStackTrace();
        }

    }
}
