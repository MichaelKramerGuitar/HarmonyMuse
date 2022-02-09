package file.handling;

import builders.ChordSequence;
import commandline.app.CommonView;

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

    private static final String tonalCenterIndicator = "*";
    private static final String sequenceBeginIndicator = "^";
    private static final String sequenceEndIndicator ="end-of-sequence";
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
            Formatter outfile = new Formatter(fileWriter);
            for(int i = 0; i < rawInput.length; i++){
                System.out.println(rawInput[i]);
                outfile.format("%s ", rawInput[i]);
                //bufferedWriter.write(rawInput[i]);
            }
            outfile.format("%n");
            outfile.close();
        }catch (IOException e){
            System.out.println("Error writing to file");
            e.printStackTrace();
        }

    }

    /**
     * The purpose of this method is to overload the writeToFile method name
     * with a version that writes a simple String, one character: perhaps an
     * indicator character or perhaps a String representation of a tonal center
     * note
     * <p>Precondition: The user has inputted a valid input</p>
     * <p>Postcondition: the appropriate String or character is
     * written to file</p>
     *
     * @param rawInput is the String of user input
     * @param filename a valid file name
     */
    public static void writeToFile(String filename, String rawInput){
        try{
            File file = new File("data\\" + filename + ".txt");
            FileWriter fileWriter = new FileWriter(file, true);
            Formatter outfile = new Formatter(fileWriter);
            outfile.format("%s%n", rawInput);
            outfile.close();
        }catch (IOException e){
            System.out.println("Error writing to file");
            e.printStackTrace();
        }

    }


    /**
     * The purpose of this method is to overload the writeToFile method name
     * with a version that writes a simple String, one character: perhaps an
     * indicator character or perhaps a String representation of a tonal center
     * note
     * <p>Precondition: The user has inputted a valid input</p>
     * <p>Postcondition: the appropriate String or character is
     * written to file</p>
     *
     * @param chordSequence is a ChordSequence
     * @param filename a valid file name
     */
    public static void writeToFile(String filename, ChordSequence chordSequence){
        ChordSequence chordSeq = ReadFromFile.readFile(filename, new ChordSequence());
        String[] romans = CommonView.addRomanNumeral(chordSequence);

        try{
            File file = new File("data\\" + filename + "-analysis" + ".txt");
            FileWriter fileWriter = new FileWriter(file, true);
            Formatter outfile = new Formatter(fileWriter);
            outfile.format("%s%n", "Sequence tonal center: " + chordSequence.getTonalCenter().toString().toUpperCase());
            for (String roman: romans
            ) {
                outfile.format("%s", roman + " ");
            }
            outfile.close();
        }catch (IOException e){
            System.out.println("Error writing to file");
            e.printStackTrace();
        }

    }
    /**
     * The purpose of this method is to get the arbitrary String representing
     * the beginning of a sequence to write to file
     * <p>Precondition: User has inputted valid tonal center</p>
     * <p>Postcondition: String is returned to write to file to mark the
     * beginning of a sequence</p>
     *
     * @return A String representing the beginning of a chord sequence
     */
    public static String getSequenceBeginIndicator() {
        return sequenceBeginIndicator;
    }
    /**
     * The purpose of this method is to get the arbitrary String representing
     * the tonal center of a sequence to write to file
     * <p>Precondition: User is welcomed to QuickEntryView </p>
     * <p>Postcondition: String is returned to write to file to mark the
     * tonal center of a sequence</p>
     *
     * @return A String representing the tonal center of a chord sequence
     */
    public static String getTonalCenterIndicator() {
        return tonalCenterIndicator;
    }

    /**
     * The purpose of this method is to get the arbitrary String representing
     * the end of a sequence to write to file
     * <p>Precondition: User is welcomed to QuickEntryView </p>
     * <p>Postcondition: String is returned to write to file to mark the
     * end of a sequence</p>
     *
     * @return A String representing the tonal center of a chord sequence
     */
    public static String getSequenceEndIndicator(){ return sequenceEndIndicator;}
}
