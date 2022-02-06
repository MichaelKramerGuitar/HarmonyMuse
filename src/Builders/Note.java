package Builders;

// https://newt.phys.unsw.edu.au/jw/notes.html

// could use midi numbers instead

// How to create midi messages
//https://docs.oracle.com/javase/tutorial/sound/MIDI-messages.html

/*
 - We imagine note input data to our system could come in a variety of types
 - We also might imagine Octave information might eventually come in with a note data
 - This class we can imagine gives an integer value as a compression function
 to a note (between 0-11) regardless of type or octave
 - The compression function will allow the Voicing functions to do their work
 and worry about Octave
 */


import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to provide a data structure foundation for one
 * of the main building blocks of harmony representation, the individual note.
 */
public class Note implements Serializable {

    /*
    Philosophy for lower case: note names are lower case, chord names are upper case.
    "c" is the note c.
    "C" is the triad ["c", "e", "g"]
     */
    private String name; // i.e "c" or "e-"
    // intValue is the compressed value of a pitch between 0-11
    private int intValue;
    /* 21 is A0 the lowest note on piano, 108 is C8 the highest note on a piano
    Technically midi allocates a whole byte of memory for this so range is 0-127
     */
    private int midiInt; // for midi data
    private int velocity; // for midi data, velocity is loudness of a note range 0-127
    private int octave; // this represents the number indicating octave i.e. "c4" - middle c on the piano

    private boolean goodNote = false;

    private ArrayList<String> enharmonics = new ArrayList<>(0);

    private Map<Integer, ArrayList<String>> notesMap;

    // constructors

    /**
     * The purpose of this method is to instantiate an empty Note object
     */
    public Note() {
    }

    /**
     * The purpose of this method is to instantiate a Note object from a
     * given String (i.e. "c#"). We use "#" for sharps and "-" for flats
     * for computational easy and these symbols can be replaced with their
     * appropriate unicode characters for pretty printing in the UI
     * <p>Precondition: A string has been provided from user input</p>
     * <p>Postcondition: A note object is created from the input and the name,
     * intValue and where applicable, enharmonic attributes are set</p>
     * @throws IllegalArgumentException for invalid input. Input not in the
     * character range "a" through "g" are illegal and if these are preceeded
     * by accidental representations other than "#" or "-" they are illegal
     */
    @Contract(pure = true)
    public Note(@NotNull String name) throws InvalidNoteException {
        String n = name.toLowerCase(); // force input to lower case
        buildNotesMap();
        for (int i = 0; i < this.notesMap.size(); i++) {
            if (notesMap.get(i).contains(n)) {
                this.name = n;
                this.intValue = i;
                this.goodNote = true;
                ArrayList<String> enharmonics = notesMap.get(i);
                for (int j = 0; j < enharmonics.size(); j++){
                    String enharm = enharmonics.get(j);
                    if(!n.equals(enharm)){
                        this.enharmonics.add(enharm);
                    }
                }
            }
        }
        if(!this.goodNote){
            throw new InvalidNoteException("Could not create note from input->>> " + name);
        }
    }


    // getters
    /**
     * The purpose of this method is to get the name attribute of this Note object
     */
    public String getName() {
        return name;
    }

    /**
     * The purpose of this method is to get the intValue attribute of this Note object
     */
    public int getIntValue() {
        return intValue;
    }

    /**
     * The purpose of this method is to get the enharmonics attribute of this Note object
     */
    public ArrayList<String> getEnharmonics() {return enharmonics;}

    /**
     * The purpose of this method is to get the midiInt attribute of this Note object
     */
    public int getMidiInt() {
        return midiInt;
    }

    /**
     * The purpose of this method is to get the velocity attribute of this Note object
     */
    public int getVelocity() {
        return velocity;
    }

    /**
     * The purpose of this method is to get the octave attribute of this Note object
     */
    public int getOctave(){
        return octave;
    }

    /**
     * The purpose of this method is to get the goodNote attribute of this Note object
     */
    public boolean getIsGoodNote(){
        return goodNote;
    }

    /**
     * The purpose of this method is to get a notesMap
     * @return Map -> Integer:ArrayList of Strings notesMap
     */
    public Map<Integer, ArrayList<String>> getNotesMap() { return notesMap; }

    // setters
    /**
     * The purpose of this method is to set the name attribute of this Note object
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The purpose of this method is to set the intValue attribute of this Note object
     */
    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    /**
     * The purpose of this method is to set the midiInt attribute of this Note object
     */
    public void setMidiInt(int midiInt) {
        this.midiInt = midiInt;
    }

    /**
     * The purpose of this method is to set the velocity attribute of this Note object
     */
    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    /**
     * The purpose of this method is to set the octave attribute of this Note object
     */
    public void setOctave(int octave) {
        this.octave = octave;
    }

    /**
     * The purpose of this method is build a map to enforce the input standards
     * for Strings representing notes to the system. This map ensures that Notes
     * are not instantiated from Invalidly formatted Strings.
     * <p>Precondition: a Note is being instantiated </p>
     * <p>Postcondition: a Note is instantiated as it corresponded to a
     * value in this map </p>
     */
    public void buildNotesMap(){

        Integer[] chromaticDigits = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        Map<Integer, ArrayList<String>> notesMap = new HashMap<Integer, ArrayList<String>>(12);

        for(int i = 0; i < chromaticDigits.length; i++){
            // create keys from the chromaticDigits array
            notesMap.put(i, new ArrayList<>());
        }

        // notes enharmonic to c
        notesMap.get(0).add("c");
        notesMap.get(0).add("b#");
        notesMap.get(0).add("d--"); // 7th of Ebdim7

        // enharmonics c#, d-
        notesMap.get(1).add("c#");
        notesMap.get(1).add("d-");

        // enharmonics to d
        notesMap.get(2).add("d");
        notesMap.get(2).add("c##");
        notesMap.get(2).add("e--");

        // enharmonics d#, e-
        notesMap.get(3).add("d#");
        notesMap.get(3).add("e-");

        // enharmonics to e
        notesMap.get(4).add("e");
        notesMap.get(4).add("d##");
        notesMap.get(4).add("f-");

        // enharmonics to f
        notesMap.get(5).add("f");
        notesMap.get(5).add("e#");
        notesMap.get(5).add("g--");

        // enharmonics f#, g-
        notesMap.get(6).add("f#");
        notesMap.get(6).add("g-");

        // enharmonics to g
        notesMap.get(7).add("g");
        notesMap.get(7).add("f##");
        notesMap.get(7).add("a--");

        // enharmonics g#, a-
        notesMap.get(8).add("g#");
        notesMap.get(8).add("a-");

        // enharmonics to a
        notesMap.get(9).add("a");
        notesMap.get(9).add("g##");
        notesMap.get(9).add("b--");

        // enharmonics a#, b-
        notesMap.get(10).add("a#");
        notesMap.get(10).add("b-");

        // enharmonics b
        notesMap.get(11).add("b");
        notesMap.get(11).add("c-");
        notesMap.get(11).add("a##");

        this.notesMap = notesMap;
    }

    /**
     * The purpose of this method is to return a human readible representation
     * of this Note object
     */
    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        try {
            Note note = new Note("C#");
            System.out.println("name: " + note.getName() + " Enharmonics: " + note.getEnharmonics()
                    + "\n" + "intValue: " + note.getIntValue());
            Note note1 = new Note("G--");
            System.out.println("name: " + note1.getName() + " Enharmonics: " + note1.getEnharmonics()
                    + "\n" + "intValue: " + note1.getIntValue());
        }catch (InvalidNoteException e){
            System.out.println(e);
        }

    }
}
