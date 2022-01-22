package HarmonyMuse;


public class AugmentedTriad extends Chord implements Triad {

    private Note root;
    private Note third;
    private Note fifth;
    private String quality;
    private String inversion;

    public AugmentedTriad(ChordBuilder data) {
        super(data.getNotes());
        Note[] notes = data.getNotes();

    }

    public Note getRoot() {return this.root;}

    public Note getThird() {return this.third;}

    public Note getFifth() {return this.fifth;}

    public String getQuality() {return quality;}

    public String getInversion() {return inversion;}

    public void setRoot(Note root) {this.root = root;}

    public void setThird(Note third) {this.third = third;}

    public void setFifth(Note fifth) {this.fifth = fifth;}

    public void setQuality(String quality){this.quality = quality;}

    public void setInversion(String inversion){this.inversion = inversion;}

    @Override
    public String toString(){
        return String.format(getQuality().toUpperCase() + " " + getInversion().toUpperCase() + " " +
                super.toString() + "\n" +
                "root: " + getRoot() + "\n" +
                "third: " + getThird() + "\n" +
                "fifth: " + getFifth());
    }
}
