package general.containers;

/**
 * @author Michael Kramer
 * CS622 Spring 1, 2022 Advanced Programming Techniques.
 * The purpose of this interface is to provide an abstraction for extended
 * tertian harmonies with altered extensions. An object can first extend the Chord
 * Abstract class, then implement the Triad Interface, the implement SeventhChord
 * interface, then this Interface.
 * @param <T> a generic object
 */
public interface AlteredExtensions<T> {
    /**
     * Precondition: Methods to be called on a Chord object that extends this
     * interface
     * @return presumably a Note object, generic Type to facilitate code
     * flexibility
     */
    public abstract T getFlatNinth();

    /**
     * Precondition: Methods to be called on a Chord object that extends this
     * interface
     * @return presumably a Note object, generic Type to facilitate code
     * flexibility
     */
    public abstract T getSharpNinth();

    /**
     * Precondition: Methods to be called on a Chord object that extends this
     * interface
     * @return presumably a Note object, generic Type to facilitate code
     * flexibility
     */
    public abstract T getEleventh();

    /**
     * Precondition: Methods to be called on a Chord object that extends this
     * interface
     * @return presumably a Note object, generic Type to facilitate code
     * flexibility
     */
    public abstract T getThirteenth();

}
