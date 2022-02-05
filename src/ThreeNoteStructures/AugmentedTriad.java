package ThreeNoteStructures;


import AbstractStructures.Chord;
import AbstractStructures.Triad;
import Builders.ChordBuilder;
import Builders.InvalidNoteException;
import Builders.Note;
import Builders.TriadFactory;
import Classifiers.TriadClassifier;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import java.lang.reflect.Type;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to provide a data structure for Chord objects
 * that have the Interval's signature of an Augmented triad
 */
public class AugmentedTriad extends Chord implements Triad {

    private Note root;
    private Note third;
    private Note fifth;
    private String inversion;

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
        Triad majorTriad = tf.buildTriad(this, root, super.getQuality());

    }
    /**
     * The purpose of this method is to get the Note root for this Triad
     * @return the Note object root
     */
    public Note getRoot() {return this.root;}

    /**
     * The purpose of this method is to get the Note third for this Triad
     * @return the Note object third
     */
    public Note getThird() {return this.third;}

    /**
     * The purpose of this method is to get the Note fifth for this Triad
     * @return the Note object fifth
     */
    public Note getFifth() {return this.fifth;}


    /**
     * The purpose of this method is to get the inversion attribute for this
     * Triad
     * @return a String representation of the inversion of this triad
     */
    public String getInversion() {return inversion;}

    /**
     * The purpose of this method is to set a Note object as the root of this
     * triad in the Classifiers.TriadClassifier class
     * @param root a Note object
     */
    public void setRoot(Note root) {this.root = root;}

    /**
     * The purpose of this method is to set a Note object as the third of this
     * triad in the Classifiers.TriadClassifier class
     * @param third a Note object
     */
    public void setThird(Note third) {this.third = third;}

    /**
     * The purpose of this method is to set a Note object as the fifth of this
     * triad in the Classifiers.TriadClassifier class
     * @param fifth a Note object
     */
    public void setFifth(Note fifth) {this.fifth = fifth;}


    /**
     * The purpose of this method is to set a Note object as the root of this
     * triad in the Classifiers.TriadClassifier class
     * @param  inversion is a String
     */
    public void setInversion(String inversion){this.inversion = inversion;}


    /**
     * The purpose of this method is to create the format for the serialized
     * object and to override the Inherited abstract method from JsonSerializer
     * <p>Precondition: An instance of this class exits</p>
     * <p>Postcondition: a Serialized version of this object is returned to
     * write to file</p>
     *
     * @param chord is an instance of this class
     *
     * @param type is the type of this object
     *
     * @param jsonSerializationContext is the context object for serialization of
     *                                 this object
     * @return JsonElement to write to file
     */
    @Override
    public JsonElement serialize(Chord chord, Type type, JsonSerializationContext jsonSerializationContext) {
        String root = "Root";
        String third = "Third";
        String fifth = "Fifth";
        String quality = "Quality";
        String inversion = "Inversion";

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(root, getRoot().toString());
        jsonObject.addProperty(third, getThird().toString());
        jsonObject.addProperty(fifth, getFifth().toString());
        jsonObject.addProperty(quality, super.getQuality());
        jsonObject.addProperty(inversion, getInversion());

        return jsonObject;
    }


    /**
     * The purpose of this method is to return a human-readable String
     * representation of this object. Note that if accidentals are present
     * these are replaced with their unicode strings for a prettier print
     * @return a human-readable String representation of this object
     */
    @Override
    public String toString(){

        Note rt = getRoot();
        Note thrd = getThird();
        Note fif = getFifth();
        StringBuilder root = replaceAccidental(rt);
        StringBuilder third = replaceAccidental(thrd);
        StringBuilder fifth = replaceAccidental(fif);


        return String.format(root.toString().toUpperCase() + " " +
                getQuality().toUpperCase() + " " + getInversion().toUpperCase() + " " +
                super.toString() + "\n" +
                "root: " + root + "\n" +
                "third: " + third + "\n" +
                "fifth: " + fifth);
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
