package HarmonyMuse;

import java.util.ArrayList;

/**
 * This class functions to clean input data then construct a chord which will
 * be classified into a specific quality from the subsequent object.
 *
 */

public class ChordBuilder extends Chord {

    private String[] inversions = new String[]{ "root position",
                                                "first inversion",
                                                "second inversion",
                                                "third inversion"};


    // four note structures
    private String[] seventhQualities = new String[]{   "diminished 7",
                                                        "minor 7 b5",
                                                        "minor 7",
                                                        "dominant 7",
                                                        "major 7"};
    private String quality;
    // constructor
    public ChordBuilder(Note[] data) {
        super(data);
    }

    @Override
    public Note getRoot() {
        return null;  // this class is building the chord, the chord still has to identity
    }

    public Chord classify(ChordBuilder chordBuilder){
        Chord classified = chordBuilder; // container to return classified chord
        ArrayList<Interval> intervals = chordBuilder.getIntervals();

        if(intervals.size() == 2){ // we've found three note chord, let's try to classify it as a triad
            Interval bottom_mid = intervals.get(0);
            Interval bottom_top = intervals.get(1);
            try {
                TriadClassifier triadClassifier = new TriadClassifier();
                classified = triadClassifier.classifyTriad(chordBuilder, bottom_mid, bottom_top);
            } catch (InvalidTriadException e){
                System.out.println(e);
                // could continue here to try another three note Classifier
            }

        }
        System.out.println("After catch");
        return classified;
    }



    public ArrayList<Interval> getIntervals(){return super.getIntervals();}

    public String[] getInversions(){
        return inversions;
    }

//    public MajorTriad toMajorTriad(ChordBuilder chord){
//
//        String[] triadQualities = chord.getTriadQualities();
//        majTriad.setQuality(triadQualities[majTriad.intervalHalfStepsSum() - triadIndexer]);
//        return majTriad;
//    }


    public void setQuality(String quality){
        quality = null;
        this.quality = quality;
    }

    public String getQuality(){
        return quality; // return null
    }

    @Override
    public String toString(){
        return String.format(super.toString());
    }
}
