package general.containers;
/*
TODO Implement this interface for Appropriate Seventh Chords to facilitate increased analysis functionality
 */

/**
 * @author Michael Kramer
 * CS622 Spring 1, 2022 Advanced Programming Techniques.
 * <p>
 * The purpose of this Interface is to provide an abstraction for extended
 * tertian harmonies. An object can first extend the Chord
 * Abstract class, then implement the Triad Interface, the implement SeventhChord
 * interface, then this Interface.</p>
 * @param <T> a generic object
 */
public interface Extensions<T> {

    /**
     * <p>Precondition: An Chord Object exists that extends this interface</p>
     * <p>Postcondition: An Object, presumably a Note object is returned
     * that represents the Ninth degree of this Chord</p>
     * @return An Object, presumably a Note object is returned
     *          that represents the Ninth degree of this Chord
     */
    public abstract T getNinth();

    /**
     * <p>Precondition: An Chord Object exists that extends this interface</p>
     * <p>Postcondition: An Object, presumably a Note object is returned
     * that represents the Eleventh degree of this Chord</p>
     * @return An Object, presumably a Note object is returned
     *          that represents the Eleventh degree of this Chord
     */
    public abstract T getEleventh();

    /**
     * <p>Precondition: An Chord Object exists that extends this interface</p>
     * <p>Postcondition: An Object, presumably a Note object is returned
     * that represents the Thirteenth degree of this Chord</p>
     * @return An Object, presumably a Note object is returned
     *          that represents the Thirteenth degree of this Chord
     */
    public abstract T getThirteenth();

}
