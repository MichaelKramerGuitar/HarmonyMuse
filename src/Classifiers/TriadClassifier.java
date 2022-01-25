package Classifiers;

import AbstractStructures.Chord;
import Builders.ChordBuilder;
import AbstractStructures.Triad;
import Builders.Interval;
import ThreeNoteStructures.AugmentedTriad;
import ThreeNoteStructures.DiminishedTriad;
import ThreeNoteStructures.MajorTriad;
import ThreeNoteStructures.MinorTriad;
import org.jetbrains.annotations.NotNull;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to determine whether a three note ChordBuilder
 * structure meets the criteria for being classified and instantiated as a
 * Triad
 */
public class TriadClassifier {

    private final String[] triadQualities = new String[]{ "diminished triad",
                                                          "minor triad",
                                                          "major triad",
                                                          "augmented triad"};

    /**
     * The purpose of this method is to instantiate an empty TriadClassifier
     * object
     */
    public TriadClassifier(){ }

    /**
     * The purpose of this method is to determine whether a three note ChordBuilder
     * object meets the criterion for being classified and instantiated as a
     * Triad
     * <p>Precondition: A ChordBuilder object has exactly three Notes and
     * two Intervals</p>
     * <p>Postcondition: An object that meets the criterion for implementing
     * the Triad interface is instantiated</p>
     *
     * @param chordBuilder
     * @param bottom_mid
     * @param bottom_top
     * @return a Chord object that has been classified and instantiated as
     * one of the four triad qualities and determined by its interval structure
     * @throws InvalidTriadException when the three note ChordBuilder instance
     * provided as an argument does not have an Interval signature that meets
     * the criterion to be classified through instantiation as a Triad
     */
    public Chord classifyTriad(ChordBuilder chordBuilder, Interval bottom_mid, Interval bottom_top) throws InvalidTriadException {
        Chord classified = chordBuilder; // re-point chordBuilder to classify it
        /*
        Initialize chordBuilder to each possible quality and let below conditions
        handle which gets pointed at classified
         */
        DiminishedTriad dimTriad = new DiminishedTriad(chordBuilder);
        MinorTriad minTriad = new MinorTriad(chordBuilder);
        MajorTriad majTriad = new MajorTriad(chordBuilder);
        AugmentedTriad augTriad = new AugmentedTriad(chordBuilder);

        /// CLOSED POSITION CHECKS ///
        if(bottom_mid.getIntValue() == 3){ // 4 possibilities that have minor third on bottom
            if(bottom_top.getIntValue() == 6){ // root position diminished triad (c, e-, g-)
                dimTriad.setQuality(triadQualities[0]);
                dimTriad.setInversion(chordBuilder.getInversions()[0]);
                classified = setRootPosClosed(dimTriad, chordBuilder);

            }
            else if(bottom_top.getIntValue() == 7){ // root position minor triad (c, e-, g)
                minTriad.setQuality(triadQualities[1]);
                minTriad.setInversion(chordBuilder.getInversions()[0]);
                classified = setRootPosClosed(minTriad, chordBuilder);
            }
            else if(bottom_top.getIntValue() == 8){ // first inversion major triad (e, g, c)
                majTriad.setQuality(triadQualities[2]);
                majTriad.setInversion(chordBuilder.getInversions()[1]);
                setFirstInvClosed(majTriad, chordBuilder);
                classified = majTriad;
            }
            else if(bottom_top.getIntValue() == 9){ // first inversion diminished triad (e-, g-, c)
                dimTriad.setQuality(triadQualities[0]);
                dimTriad.setInversion(chordBuilder.getInversions()[1]);
                classified = setFirstInvClosed(dimTriad, chordBuilder);
            }
        }
        else if(bottom_mid.getIntValue() == 4){ // 3 possibilities have major third on bottom
            if(bottom_top.getIntValue() == 7){ // major triad root position (c, e, g)
                majTriad.setQuality(triadQualities[2]);
                majTriad.setInversion(chordBuilder.getInversions()[0]);
                classified = setRootPosClosed(majTriad, chordBuilder);
            }
            else if(bottom_top.getIntValue() == 8){
                /* augmented triad symmetrical shape (root determined by context) closed position
                (c, e, g#)
                 */
                augTriad.setQuality(triadQualities[3]);
                augTriad.setInversion(chordBuilder.getInversions()[0]);
                classified = setRootPosClosed(augTriad, chordBuilder);
            }
            if(bottom_top.getIntValue() == 9){ // minor triad first inversion (e-, g, c)
                minTriad.setQuality(triadQualities[1]);
                minTriad.setInversion(chordBuilder.getInversions()[1]);
                classified = setFirstInvClosed(minTriad, chordBuilder);
            }
        }
        else if(bottom_mid.getIntValue() == 5){ // 2 possibilities have perfect fourth on bottom
            if(bottom_top.getIntValue() == 8){ // minor triad second inversion (g, c, e-)
                minTriad.setQuality(triadQualities[1]);
                minTriad.setInversion(chordBuilder.getInversions()[2]);
                classified = setSecondInvClosed(minTriad, chordBuilder);
            }
            else if(bottom_top.getIntValue() == 9){ // major triad second inversion (g, c, e)
                majTriad.setQuality(triadQualities[2]);
                majTriad.setInversion(chordBuilder.getInversions()[2]);
                classified = setSecondInvClosed(majTriad, chordBuilder);
            }
        }
        else if(bottom_mid.getIntValue() == 6){ // two possibilities have tritone on bottom
            if(bottom_top.getIntValue() == 9){ // second inversion diminished triad (g-, c, e-)
                dimTriad.setQuality(triadQualities[0]);
                dimTriad.setInversion(chordBuilder.getInversions()[2]);
                classified = setSecondInvClosed(dimTriad, chordBuilder);
            }
            /// END CLOSED POSITION CHECKS ///

            /// OPEN POSITION CHECKS ///
            else if(bottom_top.getIntValue() == 3){ // root position diminished triad (c, g-, e-)
                dimTriad.setQuality(triadQualities[0]);
                dimTriad.setInversion(chordBuilder.getInversions()[0]);
                classified = setRootPosOpen(dimTriad, chordBuilder);
            }
            /*
            TODO continue classifying open triads
             */
        }
        // if classified hasn't been re-assigned triad by now classification failed
        if(classified instanceof ChordBuilder){
            throw new InvalidTriadException(classified + " is not a valid triad");
        }
        return classified;
    }

    /**
     * The purpose of this method is to set this triads degrees based on its
     * interval signature and inversion
     * <p>Precondition: This triad has been instantiated by meeting the criterion
     * of one of the blocks in the classifyTriad() method</p>
     * <p>Postcondition: The Triads' degrees are set according to its
     * Intervals signature and its inversion</p>
     * @return The Triad with degrees set
     */
    public Chord setRootPosClosed(@NotNull Triad triad, ChordBuilder chordBuilder){
        triad.setRoot(chordBuilder.getNotes()[0]);
        triad.setThird(chordBuilder.getNotes()[1]);
        triad.setFifth(chordBuilder.getNotes()[2]);
        return (Chord) triad;
    }

    /**
     * The purpose of this method is to set this triads degrees based on its
     * interval signature and inversion
     * <p>Precondition: This triad has been instantiated by meeting the criterion
     * of one of the blocks in the classifyTriad() method</p>
     * <p>Postcondition: The Triads' degrees are set according to its
     * Intervals signature and its inversion</p>
     * @return The Triad with degrees set
     */
    public Chord setRootPosOpen(@NotNull Triad triad, ChordBuilder chordBuilder){

        triad.setRoot(chordBuilder.getNotes()[0]);
        triad.setFifth(chordBuilder.getNotes()[1]);
        triad.setThird(chordBuilder.getNotes()[2]);
        return (Chord) triad;
    }

    /**
     * The purpose of this method is to set this triads degrees based on its
     * interval signature and inversion
     * <p>Precondition: This triad has been instantiated by meeting the criterion
     * of one of the blocks in the classifyTriad() method</p>
     * <p>Postcondition: The Triads' degrees are set according to its
     * Intervals signature and its inversion</p>
     * @return The Triad with degrees set
     */
    public Chord setFirstInvClosed(@NotNull Triad triad, ChordBuilder chordBuilder){
        triad.setThird(chordBuilder.getNotes()[0]);
        triad.setFifth(chordBuilder.getNotes()[1]);
        triad.setRoot(chordBuilder.getNotes()[2]);
        return (Chord) triad;
    }

    /**
     * The purpose of this method is to set this triads degrees based on its
     * interval signature and inversion
     * <p>Precondition: This triad has been instantiated by meeting the criterion
     * of one of the blocks in the classifyTriad() method</p>
     * <p>Postcondition: The Triads' degrees are set according to its
     * Intervals signature and its inversion</p>
     * @return The Triad with degrees set
     */
    public Chord setFirstInvOpen(@NotNull Triad triad, ChordBuilder chordBuilder){
        triad.setThird(chordBuilder.getNotes()[0]);
        triad.setRoot(chordBuilder.getNotes()[1]);
        triad.setFifth(chordBuilder.getNotes()[2]);
        return (Chord) triad;
    }

    /**
     * The purpose of this method is to set this triads degrees based on its
     * interval signature and inversion
     * <p>Precondition: This triad has been instantiated by meeting the criterion
     * of one of the blocks in the classifyTriad() method</p>
     * <p>Postcondition: The Triads' degrees are set according to its
     * Intervals signature and its inversion</p>
     * @return The Triad with degrees set
     */
    public Chord setSecondInvClosed(@NotNull Triad triad, ChordBuilder chordBuilder){
        triad.setFifth(chordBuilder.getNotes()[0]);
        triad.setRoot(chordBuilder.getNotes()[1]);
        triad.setThird(chordBuilder.getNotes()[2]);
        return (Chord) triad;
    }

    /**
     * The purpose of this method is to set this triads degrees based on its
     * interval signature and inversion
     * <p>Precondition: This triad has been instantiated by meeting the criterion
     * of one of the blocks in the classifyTriad() method</p>
     * <p>Postcondition: The Triads' degrees are set according to its
     * Intervals signature and its inversion</p>
     * @return The Triad with degrees set
     */
    public Chord setSecondInvOpen(@NotNull Triad triad, ChordBuilder chordBuilder){
        triad.setFifth(chordBuilder.getNotes()[0]);
        triad.setThird(chordBuilder.getNotes()[1]);
        triad.setRoot(chordBuilder.getNotes()[2]);
        return (Chord) triad;
    }

    /**
     * The purpose of this method is to get the String array representation
     * of possible triad qualities
     */
    public String[] getTriadQualities(){return triadQualities;}
}
