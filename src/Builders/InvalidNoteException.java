package Builders;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to throw an exception when note input to the
 * system is not in the specified format. See the Note class.
 */
public class InvalidNoteException extends Exception{

    /**
     * The purpose of this method is a customized message for the appropriate
     * calling method to provide a descriptive reason the user
     * inputted String note is not accepted by the system.
     * @param str a custom descriptive message
     */
    public InvalidNoteException(String str){
        super(str);
    }
}
