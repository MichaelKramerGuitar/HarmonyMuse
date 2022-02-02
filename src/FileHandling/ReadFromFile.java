package FileHandling;

import AbstractStructures.Chord;
import Builders.ChordBuilder;
import Builders.InvalidNoteException;
import Builders.Note;
import Builders.ChordSequence;
import Classifiers.TriadClassifier;
import ThreeNoteStructures.AugmentedTriad;
import ThreeNoteStructures.MajorTriad;
import ThreeNoteStructures.MinorTriad;
import ThreeNoteStructures.DiminishedTriad;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to read data from a file, currently representing
 * the applications database
 */
public class ReadFromFile {

    /**
     * The purpose of this method is to read in each line of a file, convert
     * that line to a ChordBuilder and ultimately return an ArrayList containing
     * each Chord in the file
     * <p>Precondition: A file exists with cleaned data that can be used
     * to instantiate Note objects</p>
     * <p>Postcondition: An ArrayList of all the Chords in the file is returned</p>
     *
     * @return An ArrayList of all the Chords in the file is returned
     */
    public static ArrayList<Chord> readFile(String filename){
        ArrayList<Chord> chordsOnFile = new ArrayList<>(0);
        ArrayList<String> container = new ArrayList<>(0);
        // open input.txt, read its contents and close the file
        try(Scanner input = new Scanner(Paths.get("data\\" + filename + ".txt"))) {
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
                    } catch (InvalidNoteException e) {
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

    /**
     * The purpose of this method is to read a sequence from a file where the
     * first line of the file is the tonal center and the preceding entries
     * are strings representing roots and qualities
     * <p>Precondition: The file is formatted properly</p>
     * <p>Postcondition: A ChordSequence is returned </p>
     *
     * @param filename the name of the file to read
     * @param chordSequence an empty chordSequence such that this method
     *                      can be overloaded properly
     */
    public static <E extends Chord> ChordSequence readFile(String filename, ChordSequence chordSequence){
        /*
        TODO refactor with pattern matcher https://stackoverflow.com/questions/37958457/match-a-string-in-file-java
         */
        // open input.txt, read its contents and close the file
        TriadClassifier tc = new TriadClassifier();
        Note tonalCenter = new Note();
        Note root = new Note();
        ArrayList<E> chords = new ArrayList<>(0);
        int count = 0;
        try(Scanner input = new Scanner(Paths.get("data\\" + filename + ".txt"))) {
            // read record from file
            while (input.hasNext()) { // while there is more to read
                if (count < 1) { // first line is tonal center
                    try {
                        tonalCenter = new Note(input.nextLine());
                        count++;
                    } catch (InvalidNoteException e) {
                        System.out.println(e);
                    }
                }
                else { // rest of file are sequences
                    String[] line = input.nextLine().split(" ");
                    if (line[1].equals(tc.getTriadQualities()[0])) {
                        try {
                            root = new Note(line[0]);
                            DiminishedTriad dimTriad = new DiminishedTriad(root);
                            chords.add((E) dimTriad);
                        } catch (InvalidNoteException e) {
                            System.out.println(e);
                        }
                    } else if (line[1].equals(tc.getTriadQualities()[1])) {
                        try {
                            root = new Note(line[0]);
                            MinorTriad minorTriad = new MinorTriad(root);
                            chords.add((E) minorTriad);
                        } catch (InvalidNoteException e) {
                            System.out.println(e);
                        }
                    } else if (line[1].equals(tc.getTriadQualities()[2])) {
                        try {
                            root = new Note(line[0]);
                            MajorTriad majorTriad = new MajorTriad(root);
                            chords.add((E) majorTriad);
                        } catch (InvalidNoteException e) {
                            System.out.println(e);
                        }
                    } else if (line[1].equals(tc.getTriadQualities()[3])) {
                        try {
                            root = new Note(line[0]);
                            AugmentedTriad augmentedTriad = new AugmentedTriad(root);
                            chords.add((E) augmentedTriad);
                        } catch (InvalidNoteException e) {
                            System.out.println(e);
                        }
                    }
                }
            }
            // try(with recources IMPLICITLY calls input.close()
        } catch (IOException | NoSuchElementException |
                IllegalStateException e) {
            e.printStackTrace();
        }

        ChordSequence chordSeq = new ChordSequence(chords, tonalCenter);

        return chordSeq;
    }

//    public static <E extends Chord> ChordSequence readFile(String filename, ChordSequence chordSequence){
//        /*
//        TODO refactor with pattern matcher https://stackoverflow.com/questions/37958457/match-a-string-in-file-java
//         */
//        // open input.txt, read its contents and close the file
//        TriadClassifier tc = new TriadClassifier();
//        Note tonalCenter = new Note();
//        Note root = new Note();
//        ArrayList<E> chords = new ArrayList<>(0);
//        boolean isTonalCenter = false;
//        boolean isSequence = false;
//        try(Scanner input = new Scanner(Paths.get("data\\" + filename + ".txt"))) {
//            // read record from file
//            if(input.hasNextLine()) { // while there is more to read
//                if (input.nextLine().equals(WriteToFile.getTonalCenterIndicator())) {// first line is tonal center
//                    isTonalCenter = true;
//                }
//                if (isTonalCenter) {
//                    try {
//                        tonalCenter = new Note(input.nextLine());
//                    } catch (InvalidNoteException e) {
//                        System.out.println(e);
//                    }
//                    isTonalCenter = false;
//                }
//                if (input.nextLine().equals(WriteToFile.getSequenceBeginIndicator())) {
//                    isSequence = true;
//                }
//                if (isSequence) {
//                    String[] line = input.nextLine().split(" ");
//                    System.out.println(line[0]);
//                    System.out.println((line[1]));
//                    if (line[1].equals(tc.getTriadQualities()[0])) {
//                        try {
//                            root = new Note(line[0]);
//                            DiminishedTriad dimTriad = new DiminishedTriad(root);
//                            chords.add((E) dimTriad);
//                        } catch (InvalidNoteException e) {
//                            System.out.println(e);
//                        }
//                    } else if (line[1].equals(tc.getTriadQualities()[1])) {
//                        try {
//                            root = new Note(line[0]);
//                            MinorTriad minorTriad = new MinorTriad(root);
//                            chords.add((E) minorTriad);
//                        } catch (InvalidNoteException e) {
//                            System.out.println(e);
//                        }
//                    } else if (line[1].equals(tc.getTriadQualities()[2])) {
//                        try {
//                            root = new Note(line[0]);
//                            MajorTriad majorTriad = new MajorTriad(root);
//                            chords.add((E) majorTriad);
//                        } catch (InvalidNoteException e) {
//                            System.out.println(e);
//                        }
//                    } else if (line[1].equals(tc.getTriadQualities()[3])) {
//                        try {
//                            root = new Note(line[0]);
//                            AugmentedTriad augmentedTriad = new AugmentedTriad(root);
//                            chords.add((E) augmentedTriad);
//                        } catch (InvalidNoteException e) {
//                            System.out.println(e);
//                        }
//                    }
//                }
//            }
//            // try(with recources IMPLICITLY calls input.close()
//        } catch (IOException | NoSuchElementException |
//                IllegalStateException e) {
//            e.printStackTrace();
//        }
//
//        ChordSequence chordSeq = new ChordSequence(chords, tonalCenter);
//
//        return chordSeq;
//    }
}
