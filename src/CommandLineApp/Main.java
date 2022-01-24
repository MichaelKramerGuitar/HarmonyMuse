package CommandLineApp;

import AbstractStructures.Chord;
import Builders.InvalidNoteException;
import Model.ReadFromFile;
import Model.WriteToFile;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Michael Kramer
 * Date: 1/23/2022
 * The purpose of this class is...
 */
public class Main {
    public static void main(String[] args) {
        ChordEntryView.welcome();
        WriteToFile.clearFile(); // reset file, this is temporary
        int num_notes = -1;
        boolean moreInput = true;
        while (moreInput) {
            while (num_notes < 0) {
                num_notes = ChordEntryView.getNumNotes();
            }
            System.out.println(num_notes);
            String[] chordInput = new String[num_notes];
            boolean goodInput = false;
            while (!goodInput) {
                try {
                    String[] rawInput = ChordEntryView.getNotes(num_notes, chordInput);
                    System.out.println(Arrays.toString(rawInput));
                    goodInput = true;
                    WriteToFile.writeToFile(rawInput);

                } catch (InvalidNoteException e) {
                    System.out.println(e);
                }
            }
            moreInput = ChordEntryView.moreChords();
        }
        ArrayList<Chord> chordsOnFile = ReadFromFile.readFile();
        ChordEntryView.displayChordsOnFile(chordsOnFile);
    }
}

    /*
    // Actor creates array of Objects that extend the Chord abstract class
    Chord[] progression = {majTriad, minTriad, dom};

    System.out.println();
    // iterate of the Chord array
    for (Chord chord : progression){
        Note r = chord.getRoot(); // polymorphically call getRoot on each chord in the progression
        System.out.println("here's my root: " + r);
        System.out.println("here are my intervals: " + chord.getIntervals());
        if (chord instanceof DominantSeventhChord){
            Note seventh = ((DominantSeventhChord) chord).getSeventh();
            System.out.println("\nHere is a seventh: " + seventh + ", here's this seventh's intValue: " + seventh.getIntValue());
        }

    }
     */