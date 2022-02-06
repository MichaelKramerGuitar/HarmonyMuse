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
 * that have the Interval's signature of an Augmented triad
 */
public class AugmentedTriad extends ConcreteTriad {

    /**
     * The purpose of this method is to instantiate an Augmented triad from
     * a ChordBuilder object with the appropriate Interval's signature as
     * determined by the Classifiers.TriadClassifier class
     * <p>Precondition: A three note ChordBuilder object has the
     * interval signature of an augmented triad</p>
     * <p>Postcondition: an Augmented Triad is instantiated</p>
     *
     * @param data is a ChordBuilder object with three Notes and two Intervals
     */
    public AugmentedTriad(ChordBuilder data) {
        super(data.getNotes());
        Note[] notes = data.getNotes();

    }
    /**
     * The purpose of this method is to build a augmented triad given a Note root
     * <p>Precondition: there has been instantiated a valid Note root</p>
     * <p>Postcondition: The AugmentedTriad object corresponding to said root
     * is instantiated</p>
     */
    public AugmentedTriad(Note root){

        TriadClassifier triadClassifier = new TriadClassifier();
        this.setRoot(root);
        super.setQuality(triadClassifier.getTriadQualities()[3]); // augmented
        this.setInversion("root position");

        TriadFactory tf = new TriadFactory<>();
        tf.buildTriad(this, root, super.getQuality());

    }

    public static void main(String[] args) {
        try {
            Note c = new Note("c");
            AugmentedTriad C = new AugmentedTriad(c);
            System.out.println(C);
        }catch (InvalidNoteException e){
            System.out.println(e);
        }
        try {
            Note e = new Note("e");
            AugmentedTriad E = new AugmentedTriad(e);
            System.out.println(E);
        }catch (InvalidNoteException e){
            System.out.println(e);
        }
        try {
            Note gFlat = new Note("g-");
            AugmentedTriad Gflat = new AugmentedTriad(gFlat);
            System.out.println(Gflat);
        }catch (InvalidNoteException e){
            System.out.println(e);
        }
        try {
            Note eFlat = new Note("e-");
            AugmentedTriad Eflat = new AugmentedTriad(eFlat);
            System.out.println(Eflat);
        }catch (InvalidNoteException e){
            System.out.println(e);
        }
        try {
            Note dSharp = new Note("d#");
            AugmentedTriad Dsharp = new AugmentedTriad(dSharp);
            System.out.println(Dsharp);
        }catch (InvalidNoteException e){
            System.out.println(e);
        }
    }
}
