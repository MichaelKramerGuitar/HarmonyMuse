package ThreeNoteStructures;

import Builders.ChordBuilder;
import Builders.InvalidNoteException;
import Builders.Note;
import Builders.TriadFactory;
import Classifiers.TriadClassifier;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to provide a data structure for Chord objects
 * that have the Interval's signature of a Diminished triad
 */
public class DiminishedTriad extends ConcreteTriad {

    /**
     * The purpose of this method is to instantiate a Diminished Triad from
     * a ChordBuilder object with the appropriate Interval's signature as
     * determined by the Classifiers.TriadClassifier class
     * <p>Precondition: A three note ChordBuilder object has the
     * interval signature of a Diminished Triad</p>
     * <p>Postcondition: a Diminished Triad is instantiated</p>
     *
     * @param data is a ChordBuilder object with three Notes and two Intervals
     */
    public DiminishedTriad(ChordBuilder data) {
        super(data.getNotes());
        Note[] notes = data.getNotes();

    }

    /**
     * The purpose of this method is to build a diminished triad given a Note root
     * <p>Precondition: there has been instantiated a valid Note root</p>
     * <p>Postcondition: The DiminishedTriad object corresponding to said root
     * is instantiated</p>
     */
    public DiminishedTriad(Note root){

        TriadClassifier triadClassifier = new TriadClassifier();
        this.setRoot(root);
        super.setQuality(triadClassifier.getTriadQualities()[0]); // diminished
        this.setInversion("root position");

        TriadFactory tf = new TriadFactory<>();
        tf.buildTriad(this, root, super.getQuality());

    }

    public static void main(String[] args) {
        try {
            Note c = new Note("c");
            DiminishedTriad C = new DiminishedTriad(c);
            System.out.println(C);
        }catch (InvalidNoteException e){
            System.out.println(e);
        }
        try {
            Note e = new Note("e");
            DiminishedTriad E = new DiminishedTriad(e);
            System.out.println(E);
        }catch (InvalidNoteException e){
            System.out.println(e);
        }
        try {
            Note gFlat = new Note("g-");
            DiminishedTriad Gflat = new DiminishedTriad(gFlat);
            System.out.println(Gflat);
        }catch (InvalidNoteException e){
            System.out.println(e);
        }
        try {
            Note eFlat = new Note("e-");
            DiminishedTriad Eflat = new DiminishedTriad(eFlat);
            System.out.println(Eflat);
        }catch (InvalidNoteException e){
            System.out.println(e);
        }
        try {
            Note dSharp = new Note("d#");
            DiminishedTriad Dsharp = new DiminishedTriad(dSharp);
            System.out.println(Dsharp);
        }catch (InvalidNoteException e){
            System.out.println(e);
        }

    }
}
