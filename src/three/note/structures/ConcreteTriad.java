package three.note.structures;

import general.containers.Chord;
import general.containers.Triad;
import builders.ChordBuilder;
import builders.InvalidNoteException;
import builders.Note;
import builders.TriadFactory;
import classifiers.TriadClassifier;
import com.google.gson.*;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to provide concrete layer for triad instantiation
 * of any quality between Individual Triad Quality Classes and the abstract
 * Chord class and Triad interface -> useful for serialization
 */
public class ConcreteTriad extends Chord implements Triad, Serializable {

    private Note root;
    private Note third;
    private Note fifth;
    private String inversion;
    private boolean isOpen;

    public ConcreteTriad() {
    }

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
    public ConcreteTriad(ChordBuilder data) {
        super(data.getNotes());
        Note[] notes = data.getNotes();
    }

    /**
     * The purpose of this method is to build a triad of any
     * quality given a Note root and a String quality
     * <p>Precondition: there has been instantiated a valid Note root and
     * the String quality parameter has been cleaned and system approved</p>
     * <p>Postcondition: The MajorTriad object corresponding to said root
     * is instantiated</p>
     *
     * @param root a Note to be the root of this triad
     * @param quality a cleaned string providing the basis for building
     *                triads
     */
    public ConcreteTriad(Note root, String quality) {

        //TriadClassifier triadClassifier = new TriadClassifier();
        this.setRoot(root);
        super.setQuality(quality);
        this.setInversion("root position");

        TriadFactory tf = new TriadFactory<>();
        tf.buildTriad(this, root, super.getQuality());
    }

    public ConcreteTriad(String asString) {
    }

    public ConcreteTriad(Note[] data){super(data);}

    /**
     * The purpose of this method is to get the Note root for this Triad
     *
     * @return the Note object root
     */
    public Note getRoot() {
        return this.root;
    }

    /**
     * The purpose of this method is to get the Note third for this Triad
     *
     * @return the Note object third
     */
    public Note getThird() {
        return this.third;
    }

    /**
     * The purpose of this method is to get the Note fifth for this Triad
     *
     * @return the Note object fifth
     */
    public Note getFifth() {
        return this.fifth;
    }

    /**
     * The purpose of this method is to get the inversion attribute for this
     * Triad
     *
     * @return a String representation of the inversion of this triad
     */
    public String getInversion() {
        return inversion;
    }

    @Override
    public Note[] getNotes() {
        return super.getNotes();
    }

    /**
     * The purpose of this method is to set a Note object as the root of this
     * triad in the Classifiers.TriadClassifier class
     *
     * @param root a Note object
     */
    public void setRoot(Note root) {
        this.root = root;
    }

    /**
     * The purpose of this method is to set a Note object as the root of this
     * triad in the Classifiers.TriadClassifier class
     *
     * @param third a Note object
     */
    public void setThird(Note third) {
        this.third = third;
    }

    /**
     * The purpose of this method is to set a Note object as the fifth of this
     * triad in the Classifiers.TriadClassifier class
     *
     * @param fifth a Note object
     */
    public void setFifth(Note fifth) {
        this.fifth = fifth;
    }


    /**
     * The purpose of this method is to set a Note object as the root of this
     * triad in the Classifiers.TriadClassifier class
     *
     * @param inversion is a String
     */
    public void setInversion(String inversion) {
        this.inversion = inversion;
    }

    /*
    TODO write this method for all triads to return a boolean whether the triad is Open position
     */
    public void isOpen() {
    }

    /**
     * The purpose of this method is to create the format for the serialized
     * object and to override the Inherited abstract method from JsonSerializer
     * <p>Precondition: An instance of this class exits</p>
     * <p>Postcondition: a Serialized version of this object is returned to
     * write to file</p>
     *
     * @param chord                    is an instance of this class
     * @param type                     is the type of this object
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


    public ConcreteTriad deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return new MajorTriad(json.getAsJsonPrimitive().getAsString());
    }

    /**
     * The purpose of this method is to return a human-readable String
     * representation of this object. Note that if accidentals are present
     * these are replaced with their unicode strings for a prettier print
     *
     * @return a human-readable String representation of this object
     */
    @Override
    public String toString() {

        Note rt = getRoot();
        Note thrd = getThird();
        Note fif = getFifth();
        StringBuilder root = replaceAccidental(rt);
        StringBuilder third = replaceAccidental(thrd);
        StringBuilder fifth = replaceAccidental(fif);


        return String.format(root.toString().toUpperCase() + " " +
                super.getQuality().toUpperCase() + " " + getInversion().toUpperCase() + " " +
                super.toString() + "\n" +
                "root: " + root + "\n" +
                "third: " + third + "\n" +
                "fifth: " + fifth);
    }

    public static void main(String[] args) {
        TriadClassifier tc = new TriadClassifier();
        try {
            Note c = new Note("c");
            ConcreteTriad C = new ConcreteTriad(c, tc.getTriadQualities()[2]); // minor
            System.out.println(C);
        }catch (InvalidNoteException e){
            System.out.println(e);
        }
        try {
            Note eNote = new Note("e");
            ConcreteTriad E = new ConcreteTriad(eNote, tc.getTriadQualities()[1]); // minor
            System.out.println(E);
        }catch (InvalidNoteException e){
            System.out.println(e);
        }
        try {
            Note gFlat = new Note("g-");
            ConcreteTriad Gflat = new ConcreteTriad(gFlat, tc.getTriadQualities()[0]); // diminished
            System.out.println(Gflat);
        }catch (InvalidNoteException e){
            System.out.println(e);
        }
        try {
            Note dSharp = new Note("d#");
            ConcreteTriad Dsharp = new ConcreteTriad(dSharp, tc.getTriadQualities()[3]); // augmented
            System.out.println(Dsharp);
        }catch (InvalidNoteException e){
            System.out.println(e);
        }
    }
}
