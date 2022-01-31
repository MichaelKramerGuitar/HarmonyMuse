package CommandLineApp;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to be the controller in the MVC design pattern
 * for the QuickEntryView where users simply enter a root and a chord quality
 * and the sytem generates the chord automatically and writes to file.
 */
public class MainQuickEntry {

    public static void main(String[] args) {

        QuickEntryView.welcome();
        QuickEntryView.acceptedInput();
    }
}
