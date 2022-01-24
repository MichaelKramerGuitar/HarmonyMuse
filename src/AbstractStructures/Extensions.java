package AbstractStructures;

/**
 * @author Michael Kramer
 * CS622 Spring 1, 2022 Advanced Programming Techniques.
 * The purpose of this Interface is to provide an abstraction for extended
 * tertian harmonies. An object can first extend the Chord
 * Abstract class, then implement the Triad Interface, the implement SeventhChord
 * interface, then this Interface.
 * @param <T> a generic object
 */
public interface Extensions<T> {

    public abstract T getNinth();

    public abstract T getEleventh();

    public abstract T getThirteenth();

}
