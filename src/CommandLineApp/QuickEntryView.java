package CommandLineApp;

import Builders.InvalidNoteException;
import Builders.Note;

public class QuickEntryView {

    private static CharactersTable charTable = new CharactersTable();

    /**
     * The purpose of this method is print a friendly and inviting welcome
     * message to the console of the command line UI
     */
    public static void welcome(){
        System.out.printf("%s%n%s%n%s%n%n%s%n%s%n%n", charTable.repeatStringNTimes(charTable.getBeamedEighths(), 20),
                charTable.getTrebleClef() + "  Welcome to HarmonyMuse  " + charTable.getTrebleClef(),
                charTable.repeatStringNTimes(charTable.getBeamedEighths(), 20),
                charTable.getPiano() + " Let's quick enter some "
                        + charTable.getPiano(),
                charTable.repeatStringNTimes(charTable.getGuitar(), 2) +
                        "  chords, shall we?  " + charTable.repeatStringNTimes(charTable.getGuitar(), 2));
    }

    /**
     * The purpose of this method is to clarify to the user how the system
     * expects input in a pleasant manner and to further provide brief examples
     * <p>Precondition: The user has started the app and been presented a
     * welcome message</p>
     * <p>Postcondition: This polite and neatly formatted key of guidelines
     * for accidental input and examples is on the console</p>
     */
    public static void acceptedInput(){
        System.out.printf("%s%n%18s%n%20s%n%18s%n%20s%n%22s%n%22s%n%25s%n%24s%n%s%n%n", charTable.repeatStringNTimes(charTable.getSmiley(), 5) +
                        " Please use " + charTable.repeatStringNTimes(charTable.getSmiley(), 5),
                "\"#\" for " + charTable.getSharp(),
                "\"##\" for " + charTable.getDoubleSharp(),
                "\"-\" for " + charTable.getFlat(),
                "\"--\" for " + charTable.repeatStringNTimes(charTable.getFlat(), 2),
                "\"maj\" for \"major\"",
                "\"min\" for \"minor\"",
                "\"dim\" for \"diminished\"",
                "\"aug\" for \"augmented\"",
                charTable.repeatStringNTimes(charTable.getThumbsUp(), 1) +
                        " Ex: \"c# maj\" or \"a- dim\" " + charTable.repeatStringNTimes(charTable.getThumbsUp(), 1));
    }

    /**
     * The purpose of this method is to obtain the tonal sequence
     */
    public static String getSequenceTonalCenter() {

        System.out.printf("%n%s%n", charTable.getTrebleClef() + " Please enter the tonal center of this sequence " + charTable.getTrebleClef());
        String toFile = null;
        try {
            Note tonalCenter = CommonView.getValidNote();
            toFile = tonalCenter.getName();

        } catch (InvalidNoteException e) {
            System.out.println(e);
        }
        return toFile;
    }

    /**
     * The purpose of this method is to obtain the next chords root
     * <p>Precondition: a tonal center has been accepted by the system</p>
     * <p>Postcondition: a String of the next chords root is returned</p>
     */
    public static String getSequence(){
        System.out.printf("%n%s%n", charTable.getTrebleClef() + " Please enter next chord's root " + charTable.getTrebleClef());
        String toFile = null;
        try {
            Note root = CommonView.getValidNote();
            toFile = root.getName();

        } catch (InvalidNoteException e) {
            System.out.println(e);
        }
        return toFile;
    }

}
