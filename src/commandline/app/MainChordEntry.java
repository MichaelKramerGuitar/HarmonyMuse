package commandline.app;

import general.containers.Chord;
import builders.ChordBuilder;
import builders.InvalidNoteException;
import builders.Note;
import file.handling.ReadFromFile;
import file.handling.WriteToFile;
import four.note.structures.DominantSeventhChord;
import three.note.structures.MajorTriad;
import utilities.ArrayMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;


/**
 * @author Michael Kramer
 * Date: 1/23/2022
 * The purpose of this class is to be the controller class in the MVC design
 * pattern for the ChordEntryView where users first tell the system how many
 * notes are in the next chord and then proceed to enter each note one by one.
 * The system responds by writing to file, then reading file, analyzing and
 * classifying input and neatly displaying to the console
 */
public class MainChordEntry {

    public static void main(String[] args) {
        ChordEntryView.welcome();
        ChordEntryView.acceptedInput();
        String filename = CommonView.getFileName();
        int num_notes = -1;
        boolean goodNotes = false;
        while (!goodNotes)
            while (num_notes < 0) {
                try {
                    num_notes = ChordEntryView.getNumNotes();
                } catch (InputMismatchException | InvalidNotesNumberException e) {
                    System.out.println(e);
                }
                goodNotes = true;
            }
        boolean moreInput = true;
        while (moreInput) {
            String[] chordInput = new String[num_notes];
            boolean goodInput = false;
            while (!goodInput) {
                try {
                    String[] rawInput = ChordEntryView.getNotes(num_notes, chordInput);
                    System.out.println(Arrays.toString(rawInput));
                    goodInput = true;
                    WriteToFile.writeToFile(filename, rawInput);

                } catch (InvalidNoteException e) {
                    System.out.println(e);
                }
            }
            moreInput = CommonView.moreChords();
            goodNotes = false;
            if (moreInput) {
                while (!goodNotes) {
                    try {
                        num_notes = ChordEntryView.getNumNotes();
                    } catch (InputMismatchException | InvalidNotesNumberException e) {
                        System.out.println(e);
                    }
                    goodNotes = true;
                }
            }
        }
        ArrayList<Chord> chordsOnFile = ReadFromFile.readFile(filename);
        ChordEntryView.displayChordsOnFile(chordsOnFile);



        // Test Dominant Seventh Chord
        String[] data = new String[]{"c", "e", "g", "b-"}; // create input data
        // Create a ChordBuilder instance from input data called rawData
        Note[] notes = new Note[data.length];
        for (int i = 0; i < data.length; i++) {
            try {
                notes[i] = new Note(data[i]);
            } catch (InvalidNoteException e) {
                System.out.println(e);
            }
        }
        ChordBuilder rawData = new ChordBuilder(notes);
        ChordBuilder chordSlice = ArrayMethods.getSlice(rawData, 0, 2);
        MajorTriad majorTriad = new MajorTriad(chordSlice);
        majorTriad.setRoot(rawData.getNotes()[0]);
        majorTriad.setThird(rawData.getNotes()[1]);
        majorTriad.setFifth(rawData.getNotes()[2]);
        System.out.println(rawData.getNotes()[3]);
        Note seventh = rawData.getNotes()[3];
        DominantSeventhChord domSev = new DominantSeventhChord(majorTriad, seventh);
        domSev.setQuality("Dominant Seventh");
        domSev.setInversion("root position");
        System.out.println(domSev);
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