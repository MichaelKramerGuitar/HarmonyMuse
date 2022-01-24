package CommandLineApp;

public class CharactersTable {
    private String beamedEighths = "\u266B";
    private String eighthNote = "\u266A";
    private String quarterNote = "\u2669";
    private String flat = "\u266D";
    private String natural = "\u266E";
    private String sharp = "\u266F";
    private String trebleClef = "\uD834\uDD1E";
    private String thumbsUp = "\uD83D\uDC4D";
    private String smiley = "\u263A";
    private String doubleSharp = "\uD834\uDD2A";
    private String guitar = "\uD83C\uDFB8";
    private String piano = "\uD83C\uDFB9";
    private String microphone = "\uD83C\uDFA4";


    public CharactersTable(){}

    /**
     * Recursively concatenate a symbol for an arbitrary number of repetitions
     * code modified from
     * https://www.freecodecamp.org/news/three-ways-to-repeat-a-string-in-javascript-2a9053b93a2d/
     */
    public String repeatStringNTimes(String character, int repetitions) {
        if(repetitions < 0)
            return "";
        if(repetitions == 1)
            return character;
        else
            return character + repeatStringNTimes(character, repetitions - 1);
    }

    public String getBeamedEighths(){
        return beamedEighths;
    }

    public String getEighthNote() {
        return eighthNote;
    }

    public String getQuarterNote() {
        return quarterNote;
    }

    public String getFlat() {
        return flat;
    }

    public String getNatural() {
        return natural;
    }

    public String getSharp() {
        return sharp;
    }

    public String getSmiley() {
        return smiley;
    }

    public String getThumbsUp() {
        return thumbsUp;
    }

    public String getTrebleClef() {
        return trebleClef;
    }

    public String getDoubleSharp() {
        return doubleSharp;
    }
    public String getGuitar(){
        return guitar;
    }

    public String getPiano() {
        return piano;
    }

    public String getMicrophone() {
        return microphone;
    }
}
