package commandline.app;

import builders.ChordSequence;
import file.handling.ReadFromFile;
import file.handling.WriteToFile;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to be the controller in the MVC design pattern
 * for the QuickEntryView where users simply enter a root and a chord quality
 * and the sytem generates the chord automatically and writes to file.
 */
public class MainQuickEntry {

    public static String getCleanRoot(){
        boolean goodNote;
        goodNote = false; // reset boolean
        String root = null;
        while(!goodNote) {
            root = QuickEntryView.getSequence(); // roots of chords in sequence
            if (root != null){
                goodNote = true;
            }
        }
        return root;
    }

    public static String getCleanQuality(){
        boolean goodQuality = false;
        String quality = null;
        while (!goodQuality){
            try {
                quality = CommonView.getValidQuality();
                if(quality != null) {
                    goodQuality = true;
                }
            }catch (InvalidInputException e){
                System.out.println(e);
            }
        }
        return quality;
    }
    public static void main(String[] args) {

        QuickEntryView.welcome();
        QuickEntryView.acceptedInput();
        String filename = CommonView.getFileName();
        boolean goodNote = false;
        String tc = null;
        while (!goodNote) {
            tc = QuickEntryView.getSequenceTonalCenter();
            if (tc != null) {
                goodNote = true;
            }
        }

        WriteToFile.writeToFile(filename, tc);

        String root = getCleanRoot();

        String quality = getCleanQuality();

        WriteToFile.writeToFile(filename, root + " " + quality);

        boolean moreInput = true;
        moreInput = CommonView.moreChords();
        while (moreInput){
            String nextRoot = getCleanRoot();
            String nextQuality = getCleanQuality();
            WriteToFile.writeToFile(filename, nextRoot + " " + nextQuality);
            moreInput = CommonView.moreChords();
        }
        ChordSequence chordSequence = ReadFromFile.readFile(filename, new ChordSequence());
        String[] romans = CommonView.addRomanNumeral(chordSequence);
        System.out.println("Sequence tonal center: " + chordSequence.getTonalCenter().toString().toUpperCase());
        for (String roman: romans
        ) {
            System.out.print(roman + " ");
        }

        WriteToFile.writeToFile(filename, chordSequence);
    }
}

