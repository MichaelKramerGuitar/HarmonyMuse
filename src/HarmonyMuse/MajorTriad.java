package HarmonyMuse;

public class MajorTriad extends Chord implements Triad {

    private Note root;
    private Note third;
    private Note fifth;

    public MajorTriad(ChordBuilder data) {
        super(data.getNotes());
        Note[] notes = data.getNotes();
        this.root = notes[0];
        this.third = notes[1];
        this.fifth = notes[2];

    }

    public Note getRoot() {return this.root;}

    public Note getThird() {return this.third;}

    public Note getFifth() {return this.fifth;}


    @Override
    public String toString(){
        return String.format("I am a major triad, here are my notes: " +
                super.toString() + "\n" +
                "here is my root: " + this.root); // showing the data sent to super and this.root locally
    }
}
