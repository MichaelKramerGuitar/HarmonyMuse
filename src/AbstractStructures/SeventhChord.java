package AbstractStructures;
/**
 * @author Michael Kramer
 * CS622 Spring 1, 2022 Advanced Programming Techniques.
 * <p>The purpose of this interface is to provide an abstraction for extended
 * tertian harmonies up to the 7th. An object can first extend the Chord
 * Abstract class, then implement the Triad Interface, the implement this
 * interface.</p>
 * @param <T> a generic object
 */
public interface SeventhChord<T> {

    /**
     * The purpose of this method is to get the Object, presumably a Note
     * object that represents the seventh degree of this Chord object
     * <p>Precondition: this method is called on a Chord that implements
     * this interface</p>
     * <p>Postcondition: the object, presumably a Note object representing
     * the seventh degree of this chord is returned</p>
     *
     * @return an object, presumably a Note object representing
     *      * the seventh degree of this chord is returned
     */
    public abstract T getSeventh();

}
