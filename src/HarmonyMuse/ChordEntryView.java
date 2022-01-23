package HarmonyMuse;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class handles input and output on the user interface for chord entry
 */
public class ChordEntryView {

    public static void welcome(){
        System.out.printf("%s%n%s%n%s%n%n%s%n%n", "**========================**", "** Welcome to HarmonyMuse **",
                "**========================**","Let's start classifying some chords, shall we?");
    }

    public static int getNumNotes(){
        Scanner num = new Scanner(System.in);
        System.out.printf("%s%n???", "How many notes in this chord?");
        try{
            int num_notes = num.nextInt();
            return num_notes;
        }catch (InputMismatchException e){
            System.out.println(e);
        }
        return -1;
    }

    public static String[] getNotes(int num_notes, String[] notes) throws InvalidNoteException{
        boolean goodNote = false;
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < num_notes; i++){
            System.out.println("Please enter note #" + (i + 1));
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
    public static boolean validateNotes(String[] notes) throws InvalidNoteException{
        boolean goodNote = false;
        for(int i = 0; i < notes.length; i++)
            if(notes[i].matches("[a-gA-G] | [a-gA-G]+[-#] | [a-gA-G]+[##] | [a-gA-G]+[--]")){
                goodNote = true;
            }
            else throw new InvalidNoteException("invalid note entry");

        return goodNote;
    }

    public static boolean moreChords(){
        Scanner query = new Scanner(System.in);
        System.out.printf("%s%n???", "More chords? (\"yes\" or \"no\")");
        String moreChords = query.next();
        return moreChords.toString().matches("[yesYESYes]");
    }

    public static void displayChordsOnFile(ArrayList<Chord> chordsOnFile){
        for (int i = 0; i < chordsOnFile.size(); i++){
            System.out.println(chordsOnFile.get(i));
        }
    }

    public static void main(String[] args) {

    }

}


