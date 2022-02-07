package Builders;

import AbstractStructures.Chord;
import Classifiers.TriadClassifier;
import CommandLineApp.CommonView;
import ThreeNoteStructures.*;
import javafx.util.Pair;
import org.junit.jupiter.api.Assertions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to provide a framework for the concept of a
 * harmonic phrase (such as ii-V-I)
 */

public class ChordSequence<E extends Chord> implements Serializable {

    private ArrayList<E> sequence = new ArrayList<>(0); // ArrayList needed for dynamic size
    // i.e. represents ii-V-I ---> [d, g, c] or [2, 7, 0] (intValues)
    private ArrayList<Interval> progression = new ArrayList<>(0);

    private Note tonalCenter;

    private int size;

    // Constructors, overloaded
    public ChordSequence(){}

    /**
     * The purpose of this method is to construct a ChordSequence object from
     * two chords and a given tonal center (i.e. key -> ex: d minor is the
     * ii chord of C tonal center)
     * <p>Precondition: Two objects extending Chord exist</p>
     * <p>Postcondition: A ChordSequence object is created from the
     * two Chords given as arguments and the Note object defining tonal center</p>
     */
    public ChordSequence(E firstChord, E secondChord, Note tonalCenter){

        Collections.addAll(this.sequence, firstChord, secondChord);
        this.size = 2;
        this.tonalCenter = tonalCenter;
        this.setProgression();
    }

    /**
     * The purpose of this method is to construct a ChordSequence object from
     * three chords and a given tonal center (i.e. key -> ex: d minor is the
     * ii chord of C tonal center)
     * <p>Precondition: Three objects extending Chord exist</p>
     * <p>Postcondition: A ChordSequence object is created from the
     * three Chords given as arguments and the Note object defining tonal center</p>
     */
    public ChordSequence(E firstChord, E secondChord,
                        E thirdChord, Note tonalCenter){

        Collections.addAll(this.sequence, firstChord, secondChord, thirdChord);
        this.size = 3;
        this.tonalCenter = tonalCenter;
        this.setProgression();
    }

    /**
     * The purpose of this method is to construct a ChordSequence object from
     * four chords and a given tonal center (i.e. key -> ex: d minor is the
     * ii chord of C tonal center)
     * <p>Precondition: Four objects extending Chord exist</p>
     * <p>Postcondition: A ChordSequence object is created from the
     * four Chords given as arguments and the Note object defining tonal center</p>
     */
    public ChordSequence(E firstChord, E secondChord,
                         E thirdChord, E fourthChord,
                        Note tonalCenter){
        Collections.addAll(this.sequence, firstChord, secondChord, thirdChord, fourthChord);
        this.size = 4;
        this.tonalCenter = tonalCenter;
        this.setProgression();
    }

    public ChordSequence(ArrayList<E> chords, Note tonalCenter){
        for(int i = 0; i < chords.size(); i++){
            this.sequence.add(chords.get(i));
            this.size++;
        }
        this.tonalCenter = tonalCenter;
        setProgression();
    }
    /**
     * The purpose of this method is to return the ArrayList sequence
     * <p>Precondition: A ChordSequence object exists constructed with
     * one of the non empty constructors for substantive return</p>
     * <p>Postcondition: An ArrayList of objects E extends Chord are returned</p>
     *
     * @return An ArrayList of objects E extends Chord are returned
     */
    public ArrayList<E> getSequence() {
        return sequence;
    }

    /**
     * The purpose of this method is get the Note representing the tonalCenter
     * of this ChordSequence
     * <p>Precondition: A ChordSequence has been constructed with a tonalCenter
     * passed as argument to constructor</p>
     * <p>Postcondition: The Note object is returned</p>
     *
     * @return The Note object is returned
     */
    public Note getTonalCenter(){return tonalCenter; }

    public ArrayList<Interval> getProgression() {
        return progression;
    }

    /**
     * The purpose of this method is to return a chord in the sequence at
     * a an index provided as a parameter
     * <p>Precondition: A ChordSequence exists</p>
     * <p>Postcondition: The Chord at the given index is returned</p>
     *
     * @param index the index of the desired Chord
     * @return the Chord at the given index passed as an argument
     */
    public E getChord(int index) {
        IndexOutOfBoundsException e = new IndexOutOfBoundsException("Error: index must be in range 0-" + (this.size - 1));
            E chordTarget;
            if (index >= this.size) {
                throw e;
            } else if (index < 0) {
                throw e;
            } else {
                chordTarget = this.sequence.get(index);
            }
            return chordTarget;
        }

    /**
     * The purpose of this method is to return the size of the sequence array
     * i.e. how many Chords are in the sequence
     * <p>Precondition: A ChordSequence is constructed with types E extend Chord
     * objects passed</p>
     * @return the size of ChordSequence as an int
     */
    public int getSize() {
        return size;
    }

    /**
     * The purpose of this method is to set the Interval structure of the
     * progression in relation to the passed tonalCenter. Since these are
     * Chords we'll like to render these intervals as roman numerals in the
     * UI, where uppercase is utilized for Chords with a major third and
     * lower case is utilized for Chords with a minor third.
     * <p>Precondition: A ChordSequence is being constructed with a constructor
     * that takes arguments</p>
     * <p>Postcondition: A ChordSequence is constructed and the Interval sequence
     * of the roots of each object of type E extends Chord in relation to
     * the Note tonalCenter passed is set</p>
     */
    public void setProgression(){
        for(int i = 0; i < this.sequence.size(); i++){
            Note note = this.sequence.get(i).getRoot();
            Interval interval = new Interval(new Pair<>(this.tonalCenter, note));
            this.progression.add(interval);
        }
    }

    public void setTonalCenter(Note note){this.tonalCenter = tonalCenter;}

    public void setSize(int size){this.size = size;}


    /**
     * The purpose of this method is to convert a ChordSequence object
     * into a Stream of Chords for manipulation
     * <p>Precondition: A ChordSequence has successfully been deserialized</p>
     * <p>Postcondition: a Stream of Chords is returned</p>
     *
     * @param chordSequence is a ChordSequence to be returned as a Stream
     *                             of Chords
     */
    public Stream<Chord> chordSequenceToChordStream(ChordSequence<Chord> chordSequence) {
        /*
        TODO account to for Chord qualities as that functionality is developed
         */
        TriadClassifier tc = new TriadClassifier();
        Chord[] chordsFromFile = new Chord[chordSequence.getSize()];
        for (int i = 0; i < chordSequence.getSize(); i++) {
            Assertions.assertTrue(chordSequence.getChord(i) instanceof ConcreteTriad);
            if (chordSequence.getChord(i).getQuality().equals(tc.getTriadQualities()[0])) {
                DiminishedTriad diminishedTriad = new DiminishedTriad(chordSequence.getChord(i).getRoot());
                chordsFromFile[i] = diminishedTriad;
            } else if (chordSequence.getChord(i).getQuality().equals(tc.getTriadQualities()[1])) {
                MinorTriad minorTriad = new MinorTriad(chordSequence.getChord(i).getRoot());
                chordsFromFile[i] = minorTriad;
            } else if (chordSequence.getChord(i).getQuality().equals(tc.getTriadQualities()[2])) {
                MajorTriad majorTriad = new MajorTriad(chordSequence.getChord(i).getRoot());
                chordsFromFile[i] = majorTriad;
            } else if (chordSequence.getChord(i).getQuality().equals(tc.getTriadQualities()[3])) {
                AugmentedTriad augmentedTriad = new AugmentedTriad(chordSequence.getChord(i).getRoot());
                chordsFromFile[i] = augmentedTriad;
            }
        }
        Stream<Chord> chordSequenceStream = Arrays.stream(chordsFromFile);
        return chordSequenceStream;
    }

    public static void main(String[] args) {

        try {
            Note c = new Note("c");
            MajorTriad C = new MajorTriad(c);

            Note e = new Note("e");
            MinorTriad E = new MinorTriad(e);

            Note gFlat = new Note("g-");
            DiminishedTriad Gflat = new DiminishedTriad(gFlat);

            Note eFlat = new Note("e-");
            AugmentedTriad Eflat = new AugmentedTriad(eFlat);

            ChordSequence chordSequence = new ChordSequence(C, E, Gflat, Eflat, c);

            for (int i = 0; i < chordSequence.getSize(); i++){
                System.out.println(chordSequence.getChord(i));
            }
            String[] romans = CommonView.addRomanNumeral(chordSequence);
            System.out.println("Sequence tonal center: " + chordSequence.getTonalCenter().toString().toUpperCase());
            for (String roman: romans
                 ) {
                System.out.print(roman + " ");
            }
            System.out.println();
        } catch (InvalidNoteException e){
            System.out.println(e);
        }
    }
}
