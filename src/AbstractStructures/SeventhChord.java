package AbstractStructures;
/**
 * @author Michael Kramer
 * CS622 Spring 1, 2022 Advanced Programming Techniques.
 * The purpose of this interface is to provide an abstraction for extended
 * tertian harmonies up to the 7th. An object can first extend the Chord
 * Abstract class, then implement the Triad Interface, the implement this
 * interface.
 * @param <T> a generic object
 */
public interface SeventhChord<T> {

    public abstract T getSeventh();

}
