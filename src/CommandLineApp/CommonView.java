package CommandLineApp;

import AbstractStructures.Triad;
import Builders.ChordSequence;
import Builders.Interval;
import Builders.Note;

import java.util.ArrayList;
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
    public static StringBuilder replaceAccidental(Note note){
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

    public static <T extends Triad> String[] addRomanNumeral(ChordSequence chordSequence){
        CharactersTable cTable = new CharactersTable();
        ArrayList<Interval> intervals = chordSequence.getProgression();
        String[] romans = new String[intervals.size()];

        for (int i = 0; i < chordSequence.getSize(); i++){
            Interval interval = (Interval) chordSequence.getProgression().get(i);
            T chord = (T) chordSequence.getChord(i);
            Note thrd = (Note) chord.getThird();
            int thirdInt = thrd.getIntValue();

            if (thirdInt == 3 && interval.getIntValue() == 0) { // minor third -> lower case, I chord
                    romans[i] = cTable.getOne().toLowerCase();
            }
            else if (interval.getIntValue() == 0) {
                romans[i] = cTable.getOne();
            }
            else if (thirdInt == 3 && interval.getIntValue() == 1) { // bII
                romans[i] = cTable.getFlat() + cTable.getTwo().toLowerCase();
            }
            else if (interval.getIntValue() == 1) {
                romans[i] = cTable.getFlat() + cTable.getTwo();
            }
            else if (thirdInt == 3 && interval.getIntValue() == 2) { // II
                romans[i] = cTable.getTwo().toLowerCase();
            }
            else if (interval.getIntValue() == 2) {
                romans[i] = cTable.getTwo();
            }
            else if (thirdInt == 3 && interval.getIntValue() == 3) { // bIII
                romans[i] = cTable.getFlat() + cTable.getThree().toLowerCase();
            }
            else if (interval.getIntValue() == 3) {
                romans[i] = cTable.getFlat() + cTable.getThree();
            }
            else if (thirdInt == 3 && interval.getIntValue() == 4) { // III
                romans[i] = cTable.getTwo().toLowerCase();
            }
            else if (interval.getIntValue() == 4) {
                romans[i] = cTable.getThree();
            }
            else if (thirdInt == 3 && interval.getIntValue() == 5) { // IV
                romans[i] = cTable.getFour().toLowerCase();
            }
            else if (interval.getIntValue() == 5) {
                romans[i] = cTable.getFour();
            }
            else if (thirdInt == 3 && interval.getIntValue() == 6) { // bV
                romans[i] = cTable.getFlat() + cTable.getFive().toLowerCase();
            }
            else if (interval.getIntValue() == 6) {
                romans[i] = cTable.getFlat() + cTable.getFive();
            }
            else if (thirdInt == 3 && interval.getIntValue() == 7) { // bV
                romans[i] = cTable.getFive().toLowerCase();
            }
            else if (interval.getIntValue() == 7) {
                romans[i] = cTable.getFive();
            }
            else if (thirdInt == 3 && interval.getIntValue() == 8) { // bVI
                romans[i] = cTable.getFlat() + cTable.getSix().toLowerCase();
            }
            else if (interval.getIntValue() == 8) {
                romans[i] = cTable.getFlat() + cTable.getSix();
            }
            else if (thirdInt == 3 && interval.getIntValue() == 9) { // bV
                romans[i] = cTable.getSix().toLowerCase();
            }
            else if (interval.getIntValue() == 9) {
                romans[i] = cTable.getSix();
            }
            else if (thirdInt == 3 && interval.getIntValue() == 10) { // bVII
                romans[i] = cTable.getFlat() + cTable.getSeven().toLowerCase();
            }
            else if (interval.getIntValue() == 10) {
                romans[i] = cTable.getFlat() + cTable.getSeven();
            }
            else if (thirdInt == 3 && interval.getIntValue() == 11) { // VII
                romans[i] = cTable.getSeven().toLowerCase();
            }
            else if (interval.getIntValue() == 11) {
                romans[i] = cTable.getSeven();
            }
            if(chord.getQuality().contains("aug")){
                romans[i] += cTable.getAugmented(); // augmented symbol
            }
            if(chord.getQuality().contains("dim")){
                romans[i] += cTable.getDiminished();
            }
        }
        return romans;
    }

    public static CharactersTable getCharTable() {
        return charTable;
    }
}
