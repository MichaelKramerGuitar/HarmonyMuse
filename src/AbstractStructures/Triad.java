package AbstractStructures;

import Builders.Note;

/**
 * @author Michael Kramer
 * CS622 Spring 1, 2022 Advance Programming Techniques.
 * The purpose of the Interface is to provide an abstraction to be implemented
 * by the four triad qualities as concrete classes.
 * @param <T> a Generic object
 */
public interface Triad<T> {

    /**
     * The purpose of this method is to get the Note object
     * that represents the third degree of this Triad
     * <p>Precondition: This method is called on a Triad object that
     * extends this interface</p>
     * <p>Postcondition: A Note object is returned that represents the third
     * degree of this chord</p>
     *
     * @return A Note object is returned that represents the third
     *      degree of this chord
     */
    public abstract T getThird();

    /**
     * The purpose of this method is to get the Note object
     * that represents the fifth degree of this Triad
     * <p>Precondition: This method is called on a Triad object that
     * extends this interface</p>
     * <p>Postcondition: A Note object is returned that represents the fifth
     * degree of this chord</p>
     *
     * @return A Note object is returned that represents the fifth
     *      degree of this chord
     */
    public abstract T getFifth();

    /**
     * The purpose of this method is to set a particular Note object as the
     * root of this triad
     * <p>Precondition: The Triad object extending this interface has
     * been classified by the Classifiers.TriadClassifier class</p>
     * <p>Postcondition: The Note object is set as the root of the classified
     * Triad Chord object extending this class</p>
     */
    public abstract void setRoot(Note note);


    /**
     * The purpose of this method is to set a particular Note object as the
     * third of this triad
     * <p>Precondition: The Triad object extending this interface has
     * been classified by the Classifiers.TriadClassifier class</p>
     * <p>Postcondition: The Note object is set as the third of the classified
     * Triad Chord object extending this class</p>
     */
    public abstract void setThird(Note note);

    /**
     * The purpose of this method is to set a particular Note object as the
     * third of this triad
     * <p>Precondition: The Triad object extending this interface has
     * been classified by the Classifiers.TriadClassifier class</p>
     * <p>Postcondition: The Note object is set as the third of the classified
     * Triad Chord object extending this class</p>
     */
    public abstract void setFifth(Note note);

}
