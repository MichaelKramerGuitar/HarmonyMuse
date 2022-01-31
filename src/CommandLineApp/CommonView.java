package CommandLineApp;

import AbstractStructures.Chord;
import Builders.ChordSequence;
import Builders.Interval;
import Builders.Note;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to factor out common View methods shared by
 * different View classes to facilitate code re-use
 */
public class CommonView {

    private static CharactersTable charTable = new CharactersTable();
    /**
     * The purpose of this method is to get the desired file name for this
     * sequence of chords of user input
     */
    public static String getFileName(){
        boolean goodInput = false;
        Scanner input = new Scanner(System.in);
        System.out.printf("%s%n%18s%n%s%n%10s%n%s ",
                "What would you like to name", "this entry?",
                "multiple words?? use \"-\" or \"_\"",
                charTable.repeatStringNTimes(charTable.getThumbsUp(), 2)
                        + " Example: \"giant-steps\" " +
                        charTable.repeatStringNTimes(charTable.getThumbsUp(), 2),
                "Type into the mic ...... " + charTable.getMicrophone());
        try{
            String filename = input.next();
            goodInput = true;
            return filename;
        }catch (InputMismatchException e){
            System.out.println(e);
        }
        while (!goodInput){ //loop until user input is acceptable
            Scanner input1 = new Scanner(System.in);
            System.out.printf("%20s%n%s",
                    "Let's try again: example \"my song\"",
                    charTable.getMicrophone());
            try {
                String filename = input.next();
                goodInput = true;
                return filename;
            }catch (InputMismatchException e){
                System.out.println(e);
            }

        }
        return "input"; // we'll never get here but this can be our default
    }

    /**
     * The purpose of this method is to replace system placeholders for academic
     * symbols with unicode strings, primarily for pretty printing in the UI
     * <p>Precondition: There exists a chord object</p>
     * <p>Postcondition: THe accidentals in the Chord object are replaced with
     * their actual symbols represented as Unicode strings in the CharactersTable
     * </p>
     * @param note a Note object
     * @return A String builder with accidental replaced by unicode symbol
     */
    public StringBuilder replaceAccidental(Note note){
        CharactersTable cTable = new CharactersTable();
        String n = note.toString();
        StringBuilder temp = new StringBuilder(note.toString());
        if(n.contains("-")){
            temp.deleteCharAt(1);
            temp.append(cTable.getFlat());
        }
        if (n.contains("#")){
            temp.deleteCharAt(1);
            temp.append(cTable.getSharp());
        }
        if (n.contains("--")) {
            temp.deleteCharAt(1);
            temp.deleteCharAt(1);
            temp.append(cTable.repeatStringNTimes(cTable.getFlat(), 2));
        }
        if (n.contains("##")){
            temp.deleteCharAt(1);
            temp.deleteCharAt(1);
            temp.append(cTable.getDoubleSharp());
        }
        return temp;
    }

    public StringBuilder addRomanNumeral(ChordSequence chordSequence){
        CharactersTable cTable = new CharactersTable();
        StringBuilder temp = new StringBuilder(chordSequence.getProgression().toString());

        for (int i = 0; i < chordSequence.getSize(); i++){
            Interval interval = (Interval) chordSequence.getProgression().get(i);
            Chord chord = chordSequence.getChord(i);
            if (    chord.getQuality().contains("min") ||
                    chord.getQuality().contains("dim") &&
                    interval.getIntValue() == 0) {
                temp.deleteCharAt(i);
                temp.append(cTable.getOne().toLowerCase());
            }
            if (    chord.getQuality().contains("maj") ||
                    chord.getQuality().contains("dim") &&
                            interval.getIntValue() == 0) {
                temp.deleteCharAt(i);
                temp.append(cTable.getOne().toLowerCase());
            }
        }
        return temp;
    }

    public static CharactersTable getCharTable() {
        return charTable;
    }
}
