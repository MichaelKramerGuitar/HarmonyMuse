package ThreeNoteStructures;

import AbstractStructures.Chord;
import AbstractStructures.Triad;
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
 * that have the Interval's signature of a Major triad
 */
public class MajorTriad extends Chord implements Triad {

    private Note root;
    private Note third;
    private Note fifth;
    private String quality;
    private String inversion;
    private boolean isOpen;

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

    public MajorTriad(Note root){

        TriadClassifier triadClassifier = new TriadClassifier();
        this.setRoot(root);
        this.setQuality(triadClassifier.getTriadQualities()[2]); // major triad
        this.setInversion("root position");

        TriadFactory tf = new TriadFactory<>();
        tf.buildTriad(this, root, this.quality);
//        CircularlyLinkedList cllNames = new CircularlyLinkedList();
//
//        String[] names = new String[]{"a", "b", "c", "d", "e", "f", "g"};
//        for(int i = 0; i < names.length; i++){
//            cllNames.add(names[i]);
//        }
//
//        Integer[] halfSteps = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
//        CircularlyLinkedList cllIntervals = new CircularlyLinkedList();
//        for (int j = 0; j < halfSteps.length; j++){
//            cllIntervals.add(halfSteps[j]);
//        }
//
//        char n = root.getName().charAt(0); //get the name family of passed root
//
//        Integer[] distances = new Integer[]{3, 5}; // 3rd family, 5th family
//
//        Integer[] majorIntervals = new Integer[]{4, 7}; //major third, perfect fifth
//
//        try {
//            Object thrdName = cllNames.distance(Character.toString(n), distances[0]);
//            Object fifthName = cllNames.distance(Character.toString(n), distances[1]);
//
//            Map<Integer, ArrayList<String>> notesMap = root.getNotesMap();
//
//            //int thrdIntValue = root.getIntValue() + majorIntervals[0];
//            Integer thrdIntValue = (Integer) cllIntervals.distance(root.getIntValue(), majorIntervals[0]);
//            Integer fifthIntValue = (Integer) cllIntervals.distance(root.getIntValue(), majorIntervals[1]);
//
//            ArrayList<String> thirdFamily = notesMap.get(thrdIntValue);
//            ArrayList<String> fifthFamily = notesMap.get(fifthIntValue);
//
//
//            for (int i = 0; i < thirdFamily.size(); i++){
//                if(thirdFamily.get(i).contains((String) thrdName)){
//                    thrdName = thirdFamily.get(i);
//                }
//            }
//            Note third = new Note((String)thrdName);
//            this.setThird(third);
//
//
//            for (int j = 0; j < fifthFamily.size(); j++){
//                if(fifthFamily.get(j).contains((String) fifthName)){
//                    fifthName = fifthFamily.get(j);
//                }
//            }
//            Note fifth = new Note((String)fifthName);
//            this.setFifth(fifth);
//
//        }catch (Exception e){
//            System.out.println(e);
//        }

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
     * The purpose of this method is to get the quality attribute for this
     * Triad
     * @return a String representation of the quality of this triad
     */
    public String getQuality() { return quality;}

    /**
     * The purpose of this method is to set a Note object as the root of this
     * triad in the Classifiers.TriadClassifier class
     * @param root a Note object
     */
    public void setRoot(Note root) {this.root = root;}

    /**
     * The purpose of this method is to set a Note object as the root of this
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
     * @param  quality is a String
     */
    public void setQuality(String quality){this.quality = quality;}

    /**
     * The purpose of this method is to set a Note object as the root of this
     * triad in the Classifiers.TriadClassifier class
     * @param  inversion is a String
     */
    public void setInversion(String inversion){this.inversion = inversion;}

    /*
    TODO write this method for all triads to return a boolean whether the triad is Open position  
     */
    public void isOpen(){ }

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
