package general.containers;

/*
TODO Implement this interface in tandem with Notation rendering GUI capability
 */

/**
 * @author Michael Kramer
 * CS622 Spring 1, 2022 Advance Programming Techniques.
 * An interface that can be implemented to voice a chord.
 *
 */
public interface Voicing {

    /**
     * The purpose of this method is to voice a chord (similar to drawing a shape)
     * assigning Note objects specific octaves
     * <p>Precondition: The calling object implements this interface</p>
     * <p>Postcondition: The calling object is rendered in the manner in
     * which the implemented Object specifies</p>
     */
    public abstract void voiceChord(); // no implementation
}
