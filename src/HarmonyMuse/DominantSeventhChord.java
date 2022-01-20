package HarmonyMuse;

public class DominantSeventhChord extends Chord implements Triad, SeventhChord {

    private Note root;
    private Note third;
    private Note fifth;
    private Note seventh;
    // group the base triad
    private Object[] triad = new Object[]{root, third, fifth};

    public DominantSeventhChord(ChordBuilder data) {
        super(data.getNotes());
        Note[] notes = data.getNotes();
        /* the below needs to be refactored to include analysis logic for
        assigning the degrees, this implementation is a stand in to show
        design principles
         */
        this.root = notes[0];
        this.third = notes[1];
        this.fifth = notes[2];
        this.seventh = notes[3];

    }

    public Note getRoot() {return this.root;}

    public Note getThird() {return this.third;}

    public Note getFifth() {return this.fifth;}

    public Note getSeventh() {return this.seventh;}


    @Override
    public String toString(){
        return String.format("I am a dominant seventh chord, here are my notes: " +
                super.toString() + "\n" +
                "here is my root: " + this.root); // showing the data sent to super and this.root locally
    }
}
