package HarmonyMuse;

/**
 * This class functions to clean input data then construct a chord which will
 * be classified into a specific quality from the subsequent object.
 *
 * @param <T> A generic Object such that multiple data types can be handled
 *
 */

public class ChordBuilder extends Chord {

    // constructor
    public ChordBuilder(Note[] data) {
        super(data);
    }

    @Override
    public Note getRoot() {
        return null;  // this class is building the chord, the chord still has to identity
    }

    @Override
    public String toString(){
        return String.format("I am a chord builder and here are my notes: " +
                super.toString());
    }
}
