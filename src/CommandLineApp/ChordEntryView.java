package CommandLineApp;

import AbstractStructures.Chord;
import Builders.InvalidNoteException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to be the View in the MVC design pattern
 * for the command line interface for the page on which users can enter chords
 * note by note and see them immediately analyzed and classified
 */
public class ChordEntryView {

    private static CharactersTable charTable = CommonView.getCharTable();

    /**
     * The purpose of this method is print a friendly and inviting welcome
     * message to the console of the command line UI
     */
    public static void welcome(){
        System.out.printf("%s%n%s%n%s%n%n%s%n%s%n%n", charTable.repeatStringNTimes(charTable.getBeamedEighths(), 20),
                charTable.getTrebleClef() + "  Welcome to HarmonyMuse  " + charTable.getTrebleClef(),
                charTable.repeatStringNTimes(charTable.getBeamedEighths(), 20),
                charTable.getPiano() + " Let's start classifying "
                + charTable.getPiano(),
                charTable.getGuitar() +
                " some chords, shall we?  " + charTable.getGuitar());
    }

    /**
     * The purpose of this method is to clarify to the user how the system
     * expects input in a pleasant manner and to further provide brief examples
     * <p>Precondition: The user has started the app and been presented a
     * welcome message</p>
     * <p>Postcondition: This polite and neatly formatted key of guidelines
     * for accidental input and examples is on the console</p>
     */
    public static void acceptedInput(){
        System.out.printf("%s%n%18s%n%20s%n%18s%n%20s%n%s%n%n", charTable.repeatStringNTimes(charTable.getSmiley(), 5) +
                                                    " Please use " + charTable.repeatStringNTimes(charTable.getSmiley(), 5),
                                                    "\"#\" for " + charTable.getSharp(),
                                                    "\"##\" for " + charTable.getDoubleSharp(),
                                                    "\"-\" for " + charTable.getFlat(),
                                                    "\"--\" for " + charTable.repeatStringNTimes(charTable.getFlat(), 2),
                charTable.repeatStringNTimes(charTable.getThumbsUp(), 2) +
                " Example \"c#\" or \"a-\" " + charTable.repeatStringNTimes(charTable.getThumbsUp(), 2));
    }


    /**
     * The purpose of this method is to retrieve an integer representing the
     * number of notes in the next chord the user will enter
     * <p>Precondition: The user has been welcomed and the acceptable input
     * has been printed to the console</p>
     * <p>Postcondition: an int is return with the users response for number
     * of notes in the next chord</p>
     *
     * @return an int is return with the user's response for number
     * of notes in the next chord
     */
    public static int getNumNotes() throws InputMismatchException, InvalidNotesNumberException{
        Scanner num = new Scanner(System.in);
        System.out.printf("%n%s%n%s ", "How many notes in this chord?", "Type into the mic ...... " + charTable.getMicrophone());
        try{
            int num_notes = num.nextInt();
            if(num_notes > 7 || num_notes < 1){
                throw new InvalidNotesNumberException(num_notes + " is not a number between 1 and 7, please try again.");
            }
            return num_notes;
        }catch (InputMismatchException e){
            System.out.println(e);
        }
        return -1;
    }

    /**
     * The purpose of this method is to collect the notes in the next chord the
     * user is entering and to ensure the user does not give illegal input
     * <p>Precondition: the accepted input format has been provided to the
     * user</p>
     * <p>Postcondition: clean input, handleable by the system</p>
     *
     * @return a String array of data that can be easily converted to Note objects
     */
    public static String[] getNotes(int num_notes, String[] notes) throws InvalidNoteException {
        boolean goodNote = false;
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < num_notes; i++){
            System.out.printf("%s ", "\nPlease enter note " + charTable.getEighthNote() + (i + 1) + " ... " + charTable.getMicrophone());
            String next_note = sc.nextLine();
            if(next_note.toString().matches("[a-gA-G]")){
                goodNote = true;
                notes[i] = next_note.toString();
            }
            else if(next_note.toString().matches("[a-gA-G]+[#-]")){
                goodNote = true;
                notes[i] = next_note.toString();
            }
            else if(next_note.toString().matches("[a-gA-G]##")){
                goodNote = true;
                notes[i] = next_note.toString();
            }
            else if(next_note.toString().matches("[a-gA-G]--")){
                goodNote = true;
                notes[i] = next_note.toString();
            }
            else throw new InvalidNoteException("invalid note entry");
        }
        return notes;
    }

    /**
     * The purpose of this method is to determine whether the user would like
     * to continue entering chords
     * <p>Precondition: The user has entered at least one chord</p>
     * <p>Postcondition: either the user is prompted to enter more chords or
     * the system proceeds to read the chords entered from the file and
     * present the classification and analysis to the user</p>
     *
     * @return a boolean indicating whether the user wishes to continue to enter
     * chords or not
     */
    public static boolean moreChords(){
        Scanner query = new Scanner(System.in);
        System.out.printf("%s%n???", "More chords? (\"y\" or \"n\")");
        String moreChords = query.next();
        return moreChords.toString().matches("[yY]");
    }

    /**
     * The purpose of this method is to print the chords read from file
     * <p>Precondition: chords have been inputted by a user and written to file</p>
     * <p>Postcondition: chords analysis and classification are on the console</p>
     */
    public static void displayChordsOnFile(ArrayList<Chord> chordsOnFile){
        for (int i = 0; i < chordsOnFile.size(); i++){
            System.out.println(chordsOnFile.get(i));
        }
    }
}


