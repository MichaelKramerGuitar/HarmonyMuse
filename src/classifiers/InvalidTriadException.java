package classifiers;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to create a user-defined exception for a failed
 * attempt to classify a Three note structure as a Triad
 */
public class InvalidTriadException extends Exception{

    /**
     * The purpose of this method is to return a clear, human-readable message
     * letting the user know that an attempt to classify a three note structure
     * as a Triad has failed
     * <p>Precondition: A three note structure has been passed to Classifiers.TriadClassifier</p>
     * <p>Postcondition: A descriptive error is provided</p>
     *
     * @return a String descriptive error
     */
    public InvalidTriadException(String str){
        super(str);
    }
}
