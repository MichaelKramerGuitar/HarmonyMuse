package CommandLineApp;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to guard against users inputting chords with
 * less thatn 1 or more than 7 notes with a clear message
 */
public class InvalidNotesNumberException extends Exception{

    /**
     * The purpose of this method is to return a customized clear and concise
     * message to the console or screen letting the user know why there input
     * is not acceptable
     * <p>Precondition: A user inputs an integer less than 1 or greater than 7</p>
     * <p>Postcondition: A clear message is on the screen notifying the user
     * of the input limitations</p>
     */
    public InvalidNotesNumberException(String str){
        super(str);
    }
}
