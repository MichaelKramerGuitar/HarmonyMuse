package AbstractStructures;

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

    public abstract T getFlatNinth();

    public abstract T getSharpNinth();

    public abstract T getEleventh();

    public abstract T getThirteenth();

}
