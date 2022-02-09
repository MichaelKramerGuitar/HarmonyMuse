package commandline.app;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to guard against invalid input for chord
 * qualities
 */
public class InvalidInputException extends Exception{

    /**
     * The purpose of this method is to return a customized clear and concise
     * message to the console or screen letting the user know why there input
     * is not acceptable
     * <p>Precondition: A user inputs a string that is not an acceptable quality</p>
     * <p>Postcondition: A clear message is on the screen notifying the user
     * of the input limitations</p>
     */
    public InvalidInputException(String str){
        super(str);
    }
}
