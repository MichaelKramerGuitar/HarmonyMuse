package Classifiers;

import AbstractStructures.Chord;
import Builders.ChordBuilder;
import AbstractStructures.Triad;
import Builders.Interval;
import ThreeNoteStructures.InvalidTriadException;
import ThreeNoteStructures.AugmentedTriad;
import ThreeNoteStructures.DiminishedTriad;
import ThreeNoteStructures.MajorTriad;
import ThreeNoteStructures.MinorTriad;
import org.jetbrains.annotations.NotNull;

/**
 * @author Michael Kramer
 * Date: 1/23/2022
 * Course: cs622 Spring1 Advanced Programming Techniques
 * The purpose of this class is...FOR EVERY CLASS
 */
public class TriadClassifier {

    private final String[] triadQualities = new String[]{ "diminished triad",
                                                          "minor triad",
                                                          "major triad",
                                                          "augmented triad"};

    public TriadClassifier(){ }

    /**
     * Pre-conditions and post conditions and purpose of class for every function
     * KEEP YOUR METHOD DEVELOPER FRIENDLY, i.e. in method block (doc block) make it obvious
     * what this method expects and what it does
     * YOU DON'T NEED TO DEFEND FOR EVERYTHING WITH YOUR METHODS BECAUSE YOU
     * ASSUME DEVELOPERS WILL KNOW WHAT THEY'RE DOING
     * @param chordBuilder
     * @param bottom_mid
     * @param bottom_top
     * @return
     * @throws InvalidTriadException
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

    public Chord setRootPosClosed(@NotNull Triad triad, ChordBuilder chordBuilder){
        triad.setRoot(chordBuilder.getNotes()[0]);
        triad.setThird(chordBuilder.getNotes()[1]);
        triad.setFifth(chordBuilder.getNotes()[2]);
        return (Chord) triad;
    }
    public Chord setRootPosOpen(@NotNull Triad triad, ChordBuilder chordBuilder){

        triad.setRoot(chordBuilder.getNotes()[0]);
        triad.setFifth(chordBuilder.getNotes()[1]);
        triad.setThird(chordBuilder.getNotes()[2]);
        return (Chord) triad;
    }

    public Chord setFirstInvClosed(@NotNull Triad triad, ChordBuilder chordBuilder){
        triad.setThird(chordBuilder.getNotes()[0]);
        triad.setFifth(chordBuilder.getNotes()[1]);
        triad.setRoot(chordBuilder.getNotes()[2]);
        return (Chord) triad;
    }

    public Chord setFirstInvOpen(@NotNull Triad triad, ChordBuilder chordBuilder){
        triad.setThird(chordBuilder.getNotes()[0]);
        triad.setRoot(chordBuilder.getNotes()[1]);
        triad.setFifth(chordBuilder.getNotes()[2]);
        return (Chord) triad;
    }

    public Chord setSecondInvClosed(@NotNull Triad triad, ChordBuilder chordBuilder){
        triad.setFifth(chordBuilder.getNotes()[0]);
        triad.setRoot(chordBuilder.getNotes()[1]);
        triad.setThird(chordBuilder.getNotes()[2]);
        return (Chord) triad;
    }

    public Chord setSecondInvOpen(@NotNull Triad triad, ChordBuilder chordBuilder){
        triad.setFifth(chordBuilder.getNotes()[0]);
        triad.setThird(chordBuilder.getNotes()[1]);
        triad.setRoot(chordBuilder.getNotes()[2]);
        return (Chord) triad;
    }

    public String[] getTriadQualities(){return triadQualities;}
}
