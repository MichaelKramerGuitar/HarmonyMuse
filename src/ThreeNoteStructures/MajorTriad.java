package ThreeNoteStructures;

import AbstractStructures.Chord;
import AbstractStructures.Triad;
import Builders.ChordBuilder;
import Builders.Note;

public class MajorTriad extends Chord implements Triad {

    private Note root;
    private Note third;
    private Note fifth;
    private String quality;
    private String inversion;
    private boolean isOpen;

    public MajorTriad(ChordBuilder data) {
        super(data.getNotes());
        Note[] notes = data.getNotes();
    }

    public Note getRoot() {return this.root;}

    public Note getThird() {return this.third;}

    public Note getFifth() {return this.fifth;}

    public String getInversion() {return inversion;}

    public String getQuality() { return quality;}

    public void setRoot(Note root) {this.root = root;}

    public void setThird(Note third) {this.third = third;}

    public void setFifth(Note fifth) {this.fifth = fifth;}

    public void setQuality(String quality){this.quality = quality;}

    public void setInversion(String inversion){this.inversion = inversion;}

    public void isOpen(){ }

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
}
