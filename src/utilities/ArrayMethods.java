package utilities;

import builders.ChordBuilder;
import builders.Note;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to define methods helpful for Array manipulation
 * not built into Java like slicing
 */
public class ArrayMethods {

    /**
     * The purpose of this method is get a slice of an array of Notes. For example,
     * if we have a ChordBuilder that is four notes and we want to classify as
     * A Seventh Chord Type, we need to slice the Triad out of the ChordBuilder
     * and use that in conjunction with the seventh to Instantiate the Seventh Chord
     * <p>Precondition: An Chord object has been instantiated</p>
     * <p>Postcondition: a slice of the notes array is instantiated as another
     * ChordBuilder and returned</p>
     *
     * @param chordBuilder a ChordBuilder object to slice
     * @param startIndex the starting index of the slice
     * @param endIndex the ending index of a slice
     * @return a Chord Builder object as a slice of the @param ChordBuilder
     */
    public static ChordBuilder getSlice(ChordBuilder chordBuilder, int startIndex, int endIndex)
    {
        // Get the slice of the Array
        Note[] slicedArray = new Note[endIndex - startIndex];
        //copying array elements from the original array to the newly created sliced array
        for (int i = 0; i < slicedArray.length; i++)
        {
            slicedArray[i] = chordBuilder.getNotes()[startIndex + i];
        }
        //returns the slice of an array
        ChordBuilder newChord = new ChordBuilder(slicedArray);
        return newChord;
    }
}
