package HarmonyMuse;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Note {

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
    public Note() {
    }

    @Contract(pure = true)
    public Note(@NotNull String name) throws IllegalArgumentException {
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
            throw new IllegalArgumentException("Could not create note from input->>> " + name);
        }
    }


    // getters
    public String getName() {
        return name;
    }

    public int getIntValue() {
        return intValue;
    }

    public ArrayList<String> getEnharmonics() {return enharmonics;}

    public int getMidiInt() {
        return midiInt;
    }

    public int getVelocity() {
        return velocity;
    }

    public int getOctave(){
        return octave;
    }

    public boolean getIsGoodNote(){
        return goodNote;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public void setMidiInt(int midiInt) {
        this.midiInt = midiInt;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public void setOctave(int octave) {
        this.octave = octave;
    }

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

        this.notesMap = notesMap;
    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        Note note = new Note("C#");
        System.out.println("name: " + note.getName() + " Enharmonics: " + note.getEnharmonics()
                + "\n" + "intValue: " + note.getIntValue());
        Note note1 = new Note("G--");
        System.out.println("name: " + note1.getName() + " Enharmonics: " + note1.getEnharmonics()
                + "\n" + "intValue: " + note1.getIntValue());

    }
}
