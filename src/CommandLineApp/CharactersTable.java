package CommandLineApp;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to store unicode emoji-like symbols and musical
 * characters for printing in the UI
 */
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

    /**
     * The purpose of this method is to instantiate this class for access to
     * the unicode Strings stored herein through the below getter methods
     */
    public CharactersTable(){}


    /**
     * The purpose of this method is to recursively concatenate a symbol for an arbitrary number of repetitions
     * <p>Precondition: one of the above symbols has been retrieved in the UI or
     * by the model in the case of an accidental</p>
     * <p> Postcondition: The symbol is printed the specified number of times by
     * the @param repititions</p>
     *
     * @param character the unicode symbol or String character we'd like to
     *                  repeat in a print
     * @param repetitions an int specifying number of repetitions
     * @return a String with the symbol repeated for the specified repetitions
     */
    public String repeatStringNTimes(String character, int repetitions) {
        if(repetitions < 0)
            return "";
        if(repetitions == 1)
            return character;
        else
            return character + repeatStringNTimes(character, repetitions - 1);
    }

    // Getters
    /**
     * The purpose of each below method is to retrieve the descriptively named
     * unicode Strings for printing.
     */
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
