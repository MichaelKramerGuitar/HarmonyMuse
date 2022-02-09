package three.note.structures;

import builders.ChordBuilder;
import builders.InvalidNoteException;
import builders.Note;
import builders.TriadFactory;
import classifiers.TriadClassifier;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to provide a data structure for Chord objects
 * that have the Interval's signature of a Major triad
 */
public class MajorTriad extends ConcreteTriad {

    public MajorTriad() {}

    /**
     * The purpose of this method is to instantiate a Major Triad from
     * a ChordBuilder object with the appropriate Interval's signature as
     * determined by the Classifiers.TriadClassifier class
     * <p>Precondition: A three note ChordBuilder object has the
     * interval signature of a Major Triad</p>
     * <p>Postcondition: a Major Triad is instantiated</p>
     *
     * @param data is a ChordBuilder object with three Notes and two Intervals
     */
    public MajorTriad(ChordBuilder data) {
        super(data.getNotes());
        Note[] notes = data.getNotes();
    }

    /**
     * The purpose of this method is to build a major triad given a Note root
     * <p>Precondition: there has been instantiated a valid Note root</p>
     * <p>Postcondition: The MajorTriad object corresponding to said root
     * is instantiated</p>
     */
    public MajorTriad(Note root){

        TriadClassifier triadClassifier = new TriadClassifier();
        this.setRoot(root);
        super.setQuality(triadClassifier.getTriadQualities()[2]); // major triad
        this.setInversion("root position");

        TriadFactory tf = new TriadFactory<>();
        tf.buildTriad(this, root, super.getQuality());
    }

    public MajorTriad(String asString) { }

    public static void main(String[] args) {
        try {
            Note c = new Note("c");
            MajorTriad C = new MajorTriad(c);
            System.out.println(C);
        }catch (InvalidNoteException e){
            System.out.println(e);
        }
        try {
            Note e = new Note("e");
            MajorTriad E = new MajorTriad(e);
            System.out.println(E);
        }catch (InvalidNoteException e){
            System.out.println(e);
        }
        try {
            Note gFlat = new Note("g-");
            MajorTriad Gflat = new MajorTriad(gFlat);
            System.out.println(Gflat);
        }catch (InvalidNoteException e){
            System.out.println(e);
        }
        try {
            Note eFlat = new Note("e-");
            MajorTriad Eflat = new MajorTriad(eFlat);
            System.out.println(Eflat);
        }catch (InvalidNoteException e){
            System.out.println(e);
        }
        try {
            Note dSharp = new Note("d#");
            MajorTriad Dsharp = new MajorTriad(dSharp);
            System.out.println(Dsharp);
        }catch (InvalidNoteException e){
            System.out.println(e);
        }

    }
}
