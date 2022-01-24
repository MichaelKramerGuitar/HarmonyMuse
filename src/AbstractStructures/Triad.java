package AbstractStructures;

import Builders.Note;

/**
 * @author Michael Kramer
 * CS622 Spring 1, 2022 Advance Programming Techniques.
 * The purpose of the Interface is to provide an abstraction to be implemented
 * by the four triad qualities as concrete classes.
 * @param <T>
 */
public interface Triad<T> {

    public abstract T getThird();

    public abstract T getFifth();

    public abstract void setRoot(Note note);

    public abstract void setThird(Note note);

    public abstract void setFifth(Note note);

}
